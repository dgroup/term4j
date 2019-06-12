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

import org.cactoos.list.ListOf;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.llorllale.cactoos.matchers.Assertion;

/**
 * Test case for {@link UrlOf}.
 *
 * @since 0.4.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class UrlOfTest {

    /**
     * The junit rule to catch and verify the exceptions during the unit tests.
     */
    @Rule
    public final ExpectedException cause = ExpectedException.none();

    @Test
    public void value() throws ArgNotFoundException {
        new Assertion<>(
            "the uri is parsed",
            new UrlOf(
                "--url", new ListOf<>("--url", "https://google.com")
            ).value().toString(),
            new IsEqual<>("https://google.com")
        ).affirm();
    }

    @Test
    public void wrongFormat() throws ArgNotFoundException {
        this.cause.expect(ArgNotFoundException.class);
        this.cause.expectMessage("java.net.MalformedURLException: unknown protocol: httpss");
        new UrlOf(
            "--url", new ListOf<>("--url", "httpss://google.com")
        ).value();
    }
}
