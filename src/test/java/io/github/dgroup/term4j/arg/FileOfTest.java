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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

/**
 * Test case for {@link FileOf}.
 *
 * @since 0.1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class FileOfTest {

    /**
     * The junit rule to catch and verify the exceptions during the unit tests.
     */
    @Rule
    public final ExpectedException cause = ExpectedException.none();

    @Test
    public void cmdArgValue() {
        new Assertion<>(
            "The ctor for the command-line arguments detects the file",
            () -> new FileOf(
                "-f", new ListOf<>("-f", "readme.md")
            ).value().exists(),
            new IsTrue()
        ).affirm();
    }

    @Test
    public void argValue() {
        new Assertion<>(
            "The ctor for the Scalar<String> detects the file",
            () -> new FileOf("-f", () -> "readme.md").value()
                .exists(),
            new IsTrue()
        ).affirm();
    }

    @Test
    public void fileNotExists() throws ArgNotFoundException {
        this.cause.expect(ArgNotFoundException.class);
        this.cause.expectMessage("-r");
        new FileOf(
            "-r", new ListOf<>("-r", "absent.md")
        ).value();
    }
}
