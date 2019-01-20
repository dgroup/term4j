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
import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test case for {@link ArgHas}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (200 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class ArgIsTest {

    /**
     * The junit rule to catch & verify the exceptions during the unit tests.
     */
    @Rule
    public final ExpectedException cause = ExpectedException.none();

    /**
     * Check the mismatch details for the negative scenario.
     */
    @Test
    public void mismatch() {
        this.cause.expect(AssertionError.class);
        this.cause.expectMessage(
            "Expected: label is \"-t\", value is <8>, presence is <true>"
        );
        this.cause.expectMessage(
            "     but: label was \"-t\", value was <5>, presence was <true>"
        );
        MatcherAssert.assertThat(
            new Fake<>("-t", 5, true),
            new ArgIs<>("-t", 8)
        );
    }

}
