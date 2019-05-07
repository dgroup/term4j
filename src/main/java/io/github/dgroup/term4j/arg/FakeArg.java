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
import org.cactoos.scalar.Unchecked;

/**
 * Fake implementation of {@link Arg} for unit testing purposes.
 *
 * @param <X> Type of item.
 * @since 0.1.0
 */
public final class FakeArg<X> implements Arg<X> {

    /**
     * The label of the command-line argument.
     * @see Arg#label()
     */
    private final String lbl;

    /**
     * The value of the command-line argument.
     * @see Arg#value()
     */
    private final Scalar<X> val;

    /**
     * The presence of the argument's value.
     * @see Arg#specifiedByUser()
     */
    private final Boolean specified;

    /**
     * Ctor.
     * @param lbl The label of the command-line argument.
     * @param val The value of the command-line argument.
     */
    public FakeArg(final String lbl, final X val) {
        this(lbl, () -> val);
    }

    /**
     * Ctor.
     * @param lbl The label of the command-line argument.
     * @param val The value of the command-line argument.
     */
    public FakeArg(final String lbl, final Scalar<X> val) {
        this(lbl, val, true);
    }

    /**
     * Ctor.
     * @param lbl The label of the command-line argument.
     * @param val The value of the command-line argument.
     * @param spcfd The presence of the argument's value.
     */
    public FakeArg(final String lbl, final X val, final boolean spcfd) {
        this(lbl, () -> val, spcfd);
    }

    /**
     * Ctor.
     * @param lbl The label of the command-line argument.
     * @param val The value of the command-line argument.
     * @param spcfd The presence of the argument's value.
     */
    public FakeArg(final String lbl, final Scalar<X> val, final boolean spcfd) {
        this.lbl = lbl;
        this.val = val;
        this.specified = spcfd;
    }

    @Override
    public String label() {
        return this.lbl;
    }

    @Override
    public X value() {
        return new Unchecked<>(this.val).value();
    }

    @Override
    public boolean specifiedByUser() {
        return this.specified;
    }
}
