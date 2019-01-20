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

package io.github.dgroup.term4j.arg.hamcrest;

import io.github.dgroup.term4j.arg.Fake;
import org.cactoos.iterable.IterableOf;
import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.llorllale.cactoos.matchers.Assertion;

/**
 * Test case for {@link ArgHave}.
 *
 * @since 0.1.0
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class ArgHaveTest {

    /**
     * The junit rule to catch & verify the exceptions during the unit tests.
     */
    @Rule
    public final ExpectedException cause = ExpectedException.none();

    /**
     * Check the positive scenario.
     */
    @Test
    public void matches() {
        new Assertion<>(
            "argument has proper multiple values",
            () -> new Fake<>("--admin", new IterableOf<>("Tom", "Alex")),
            new ArgHave<>("Tom", "Alex")
        );
    }

    /**
     * Check the mismatch details for the negative scenario.
     */
    @Test
    public void mismatch() {
        this.cause.expect(AssertionError.class);
        this.cause.expectMessage("Expected: <Mike>");
        this.cause.expectMessage("     but: <Tom, Alex>");
        MatcherAssert.assertThat(
            new Fake<>("--admin", new IterableOf<>("Tom", "Alex")),
            new ArgHave<>("Mike")
        );
    }
}
