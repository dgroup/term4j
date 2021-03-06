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

import java.util.List;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;

/**
 * The single boolean argument which check the existence of particular flag.
 *
 * @since 0.4.0
 */
public final class Specified implements Arg<Boolean> {

    /**
     * The label of the command-line argument.
     */
    private final String lbl;

    /**
     * The flag which shows that argument specified or not.
     */
    private final Unchecked<Boolean> spcfd;

    /**
     * Ctor.
     * @param lbl The label of command-line argument.
     * @param args All command-line arguments.
     */
    public Specified(final String lbl, final List<String> args) {
        this.lbl = lbl;
        this.spcfd = new Unchecked<>(
            new Sticky<>(
                () -> args.contains(lbl)
            )
        );
    }

    @Override
    public String label() {
        return this.lbl;
    }

    @Override
    public Boolean value() {
        return this.specifiedByUser();
    }

    @Override
    public boolean specifiedByUser() {
        return this.spcfd.value();
    }
}
