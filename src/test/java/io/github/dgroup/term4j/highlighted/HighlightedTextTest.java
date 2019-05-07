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

package io.github.dgroup.term4j.highlighted;

import org.fusesource.jansi.Ansi;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

/**
 * Test case for {@link HighlightedText}.
 *
 * @since 0.2.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class HighlightedTextTest {

    @Test
    public void hashcode() {
        new Assertion<>(
            "two texts have same hash codes",
            new HighlightedText("green", Ansi.Color.GREEN).hashCode(),
            new IsEqual<>(new Green("green").hashCode())
        ).affirm();
    }

    @Test
    public void equality() {
        new Assertion<>(
            "two texts are equal",
            new HighlightedText("green", Ansi.Color.GREEN).equals(new Green("green")),
            new IsTrue()
        ).affirm();
    }

    @Test
    public void compareTo() {
        new Assertion<>(
            "two texts are equal using compareTo",
            new HighlightedText("green", Ansi.Color.GREEN).compareTo(new Green("green")),
            new IsEqual<>(0)
        ).affirm();
    }
}
