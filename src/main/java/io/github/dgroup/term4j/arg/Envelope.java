/*
 * MIT License
 *
 * Copyright (c) 2019 Yurii Dubinka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom
 * the Software is  furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.dgroup.term4j.arg;

import io.github.dgroup.term4j.Arg;
import io.github.dgroup.term4j.arg.scalar.ArgAt;
import io.github.dgroup.term4j.arg.scalar.ArgIn;
import java.util.List;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.StickyScalar;
import org.cactoos.scalar.UncheckedScalar;
import org.cactoos.text.FormattedText;

/**
 * Envelope for {@link Arg}.
 *
 * @param <X> Type of item.
 * @since 0.1.0
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
public class Envelope<X> implements Arg<X> {

    /**
     * Origin.
     */
    private final UncheckedScalar<Arg<X>> origin;

    /**
     * Ctor.
     * @param label The label of the command-line argument.
     * @param args All command-line arguments.
     * @param fnc To convert string argument value to instance of {@code <X>}.
     */
    public Envelope(
        final String label, final List<String> args, final Func<String, X> fnc
    ) {
        this(
            label, args, fnc,
            new FormattedText("Argument `%s` wasn't specified", label)
        );
    }

    /**
     * Ctor.
     * @param label The label of the command-line argument.
     * @param args All command-line arguments.
     * @param fnc To convert string argument value to instance of {@code <X>}.
     * @param err Error message in case if argument is absent/missing.
     * @checkstyle ParameterNumberCheck (5 lines)
     */
    public Envelope(
        final String label, final List<String> args, final Func<String, X> fnc,
        final Text err
    ) {
        this(
            new StickyScalar<>(
                () -> new Mapped<>(
                    fnc,
                    new Arg<String>() {

                        @Override
                        public String label() {
                            return label;
                        }

                        @Override
                        public String value() throws ArgNotFoundException {
                            if (!this.specifiedByUser()) {
                                throw new ArgNotFoundException(err);
                            }
                            return new ArgAt(label, args).value();
                        }

                        @Override
                        public boolean specifiedByUser() {
                            return new ArgIn(label, args).value();
                        }
                    }
                )
            )
        );
    }

    /**
     * Ctor.
     * @param origin Origin.
     */
    public Envelope(final Scalar<Arg<X>> origin) {
        this.origin = new UncheckedScalar<>(origin);
    }

    @Override
    public final String label() {
        return this.origin.value().label();
    }

    @Override
    public final X value() throws ArgNotFoundException {
        return this.origin.value().value();
    }

    @Override
    public final boolean specifiedByUser() {
        return this.origin.value().specifiedByUser();
    }

}
