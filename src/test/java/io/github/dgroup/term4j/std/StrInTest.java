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

package io.github.dgroup.term4j.std;

import org.cactoos.Scalar;
import org.cactoos.io.InputOf;
import org.cactoos.text.Joined;
import org.junit.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.ScalarHasValue;

/**
 * Test case for {@link StdIn}.
 *
 * @since 0.4.0
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class StrInTest {

    /**
     * The singe text line was read from {@link org.cactoos.Input} which represents
     *  the standard system input (stdin).
     */
    @Test
    public void value() {
        new Assertion<>(
            "the value was read",
            new StdIn(new InputOf("5")),
            new ScalarHasValue<>("5")
        ).affirm();
    }

    /**
     * The multiple text lines were read from {@link org.cactoos.Input} which represents
     *  the standard system input (stdin).
     */
    @Test
    public void multiple() {
        final Scalar<String> lines = new StdIn(
            new InputOf(
                new Joined(
                    System.lineSeparator(), "1", "2", "3"
                )
            )
        );
        new Assertion<>(
            "the first line was read",
            lines,
            new ScalarHasValue<>("1")
        ).affirm();
        new Assertion<>(
            "the second line was read",
            lines,
            new ScalarHasValue<>("2")
        ).affirm();
        new Assertion<>(
            "the third line was read",
            lines,
            new ScalarHasValue<>("3")
        ).affirm();
    }
}
