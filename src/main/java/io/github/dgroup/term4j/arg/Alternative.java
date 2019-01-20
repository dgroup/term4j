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
import org.cactoos.Scalar;
import org.cactoos.scalar.StickyScalar;
import org.cactoos.scalar.Ternary;

/**
 * The command-line argument with alternative value in case if original is
 *  missing.
 *
 * @param <X> Type of command-line argument.
 * @since 0.1.0
 */
public final class Alternative<X> extends Envelope<X> {

    /**
     * Ctor.
     * @param src The original argument value.
     * @param alt The alternative argument value.
     */
    public Alternative(final Arg<X> src, final X alt) {
        this(src, () -> alt);
    }

    /**
     * Ctor.
     * @param src The original argument value.
     * @param alt The alternative argument value.
     */
    public Alternative(final Arg<X> src, final Scalar<X> alt) {
        super(
            new StickyScalar<>(
                // @checkstyle AnonInnerLengthCheck (25 lines)
                () -> new Arg<X>() {

                    @Override
                    public String label() {
                        return src.label();
                    }

                    @Override
                    @SuppressWarnings("PMD.AvoidCatchingGenericException")
                    public X value() throws ArgNotFoundException {
                        try {
                            return new Ternary<>(
                                src::specifiedByUser, src::value, alt
                            ).value();
                            // @checkstyle IllegalCatchCheck (3 lines)
                        } catch (final Exception cause) {
                            throw new ArgNotFoundException(cause);
                        }
                    }

                    @Override
                    public boolean specifiedByUser() {
                        return true;
                    }
                }
            )
        );
    }
}
