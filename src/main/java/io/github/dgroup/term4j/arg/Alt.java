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

/**
 * The argument with alternative value in case if original is absent.
 *
 * @param <X> Type of command-line argument.
 * @since 0.1.0
 */
public final class Alt<X> implements Arg<X> {

    /**
     * The origin.
     */
    private final UncheckedArg<X> origin;

    /**
     * Ctor.
     * @param orig The original argument.
     * @param alt The alternative argument value.
     */
    public Alt(final Arg<X> orig, final X alt) {
        this(orig, () -> alt);
    }

    /**
     * Ctor.
     * @param orig The original argument.
     * @param alt The alternative argument.
     */
    public Alt(final Arg<X> orig, final Scalar<X> alt) {
        this(orig, new ArgOf<>(orig::label, alt));
    }

    /**
     * Ctor.
     * @param lbl The label of argument.
     * @param orig The original argument's value.
     * @param alt The alternative argument.
     */
    public Alt(final String lbl, final Scalar<X> orig, final Arg<X> alt) {
        this(new ArgOf<>(() -> lbl, orig), alt);
    }

    /**
     * Ctor.
     * @param lbl The label of argument.
     * @param orig The original argument's value.
     * @param alt The alternative argument's value.
     */
    public Alt(final String lbl, final Scalar<X> orig, final Scalar<X> alt) {
        this(new ArgOf<>(() -> lbl, orig), alt);
    }

    /**
     * Ctor.
     * @param orig The original argument.
     * @param alt The alternative argument.
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public Alt(final Arg<X> orig, final Arg<X> alt) {
        this.origin = new UncheckedArg<>(
            new ArgOf<>(
                orig::label,
                () -> {
                    X val;
                    try {
                        val = orig.value();
                        // @checkstyle IllegalCatchCheck (3 lines)
                    } catch (final Exception cause) {
                        val = alt.value();
                    }
                    return val;
                },
                () -> orig.specifiedByUser() || alt.specifiedByUser()
            )
        );
    }

    @Override
    public String label() {
        return this.origin.label();
    }

    @Override
    public X value() {
        return this.origin.value();
    }

    @Override
    public boolean specifiedByUser() {
        return this.origin.specifiedByUser();
    }
}
