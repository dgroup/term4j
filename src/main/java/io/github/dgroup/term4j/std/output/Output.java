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
package io.github.dgroup.term4j.std.output;

import io.github.dgroup.term4j.std.UncheckedStdOutputException;
import org.cactoos.Text;

/**
 * Standard output for application progress.
 *
 * @since 0.1.0
 */
public interface Output {

    /**
     * Print each message to the separate line.
     * @param msgs The messages to pring.
     * @throws UncheckedStdOutputException In the case of STD output printing
     *  failure.
     */
    void print(String... msgs);

    /**
     * Print message to the separate line.
     * @param ptrn The pattern to build the text.
     * @param args The pattern arguments to build the text
     * @throws UncheckedStdOutputException In the case of STD output printing
     *  failure.
     * @see org.cactoos.text.FormattedText
     */
    void printf(String ptrn, Object... args);

    /**
     * Print each message to the separate line.
     * @param msgs The messages to pring.
     * @throws UncheckedStdOutputException In the case of STD output printing
     *  failure.
     */
    void print(Text... msgs);

    /**
     * Print each message to the separate line.
     * @param msgs The messages to pring.
     * @throws UncheckedStdOutputException In the case of STD output printing
     *  failure.
     */
    void print(Iterable<? extends Text> msgs);
}
