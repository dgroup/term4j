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

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * The multiple string set-based command-line argument.
 *
 * The value is immutable.
 *
 * @since 0.4.0
 */
public final class SetOf extends ArgEnvelope<Set<String>> {

    /**
     * Ctor.
     * @param lbl The label of the multiple command-line argument.
     * @param args All command-line arguments.
     */
    public SetOf(final String lbl, final List<String> args) {
        this(lbl, ",", args);
    }

    /**
     * Ctor.
     * @param lbl The label of command-line argument.
     * @param delim The delimiter for value which suppose to be multiple.
     * @param args All command-line arguments.
     */
    public SetOf(final String lbl, final String delim, final List<String> args) {
        this(new Strings(lbl, delim, args));
    }

    /**
     * Ctor.
     * @param arg The command-line argument.
     */
    public SetOf(final Arg<Collection<String>> arg) {
        super(new ArgOf<Set<String>>(
            arg::label,
            () -> new org.cactoos.set.SetOf<>(arg.value()),
            arg::specifiedByUser
        ));
    }

}
