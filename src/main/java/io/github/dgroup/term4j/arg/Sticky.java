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
import org.cactoos.scalar.Unchecked;

/**
 * .
 *
 * @since 0.1.0
 */
public final class Sticky<X> implements Arg<X> {

    private final Unchecked<String> lbl;
    private final Unchecked<X> val;
    private final Unchecked<Boolean> spcf;

    /**
     * Ctor.
     * @param origin The original command-line argument.
     */
    public Sticky(final Arg<X> origin) {
        this.lbl =  new Unchecked<>(
            new org.cactoos.scalar.Sticky<>(origin::label)
        );
        this.val = new Unchecked<>(
            new org.cactoos.scalar.Sticky<>(origin)
        );
        this.spcf = new Unchecked<>(
            new org.cactoos.scalar.Sticky<>(origin::specifiedByUser)
        );
    }

    @Override
    public String label() {
        return this.lbl.value();
    }

    @Override
    public X value() {
        return this.val.value();
    }

    @Override
    public boolean specifiedByUser() {
        return this.spcf.value();
    }
}
