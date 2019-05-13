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
import java.util.List;
import org.cactoos.Func;

/**
 * The single long command-line argument.
 *
 * @since 0.1.0
 */
public final class LongOf extends ArgEnvelope<Long> {

    /**
     * Ctor.
     * @param lbl The label of command-line argument.
     * @param args All command-line arguments.
     */
    public LongOf(final String lbl, final List<String> args) {
        this(new StringOf(lbl, args));
    }

    /**
     * Ctor.
     * @param arg The command-line argument.
     */
    public LongOf(final Arg<String> arg) {
        super(new Mapped<>((Func<String, Long>) Long::parseLong, arg));
    }
}
