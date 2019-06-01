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
import org.cactoos.scalar.True;
import org.cactoos.scalar.Unchecked;

/**
 * The single argument.
 *
 * @param <X> The type of the argument.
 *
 * @since 0.1.0
 */
public final class ArgOf<X> implements Arg<X> {

    /**
     * The label of command-line argument.
     */
    private final Scalar<String> lbl;

    /**
     * The value of command-line argument.
     */
    private final Scalar<X> val;

    /**
     * The presence of the argument's value.
     */
    private final Scalar<Boolean> spc;

    /**
     * Ctor.
     * @param lbl The label of command-line argument.
     * @param val The value of command-line argument.
     */
    public ArgOf(final String lbl, final X val) {
        this(() -> lbl, () -> val);
    }

    /**
     * Ctor.
     * @param lbl The label of command-line argument.
     * @param val The value of command-line argument.
     */
    public ArgOf(final Scalar<String> lbl, final Scalar<X> val) {
        this(lbl, val, new True());
    }

    /**
     * Ctor.
     * @param lbl The label of command-line argument.
     * @param val The value of command-line argument.
     * @param spc The presence of the argument's value.
     */
    public ArgOf(final Scalar<String> lbl, final Scalar<X> val, final Scalar<Boolean> spc) {
        this.lbl = lbl;
        this.val = val;
        this.spc = spc;
    }

    @Override
    public String label() {
        return new Unchecked<>(this.lbl).value();
    }

    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public X value() throws ArgNotFoundException {
        try {
            return this.val.value();
            // @checkstyle IllegalCatchCheck (3 lines)
        } catch (final Exception cause) {
            throw new ArgNotFoundException(cause);
        }
    }

    @Override
    public boolean specifiedByUser() {
        return new Unchecked<>(this.spc).value();
    }
}
