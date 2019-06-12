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

import io.github.dgroup.term4j.arg.hamcrest.ArgHas;
import java.io.UncheckedIOException;
import java.nio.file.Paths;
import org.cactoos.list.ListOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.llorllale.cactoos.matchers.Assertion;

/**
 * Test case for {@link FirstIn}.
 *
 * @since 0.3.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class FirstInTest {

    /**
     * The junit rule to catch and verify the exceptions during the unit tests.
     */
    @Rule
    public final ExpectedException cause = ExpectedException.none();

    @Test
    public void firstValue() {
        new Assertion<>(
            "the 1st file was found",
            new FirstIn<>(
                new PathOf("-f", new ListOf<>("-f", "readme.md")),
                new PathOf("-f", () -> Paths.get("readmeeee.md")),
                new PathOf("-f", () -> Paths.get("readmeee.mdd"))
            ),
            new ArgHas<>(Paths.get("readme.md"))
        ).affirm();
    }

    @Test
    public void secondValue() {
        new Assertion<>(
            "the 2nd file was found",
            new FirstIn<>(
                new PathOf("-f", new ListOf<>("-f", "red.md")),
                new PathOf("-f", () -> Paths.get("readme.md")),
                new PathOf("-f", () -> Paths.get("read.mddd"))
            ),
            new ArgHas<>(Paths.get("readme.md"))
        ).affirm();
    }

    @Test
    public void thirdValue() {
        new Assertion<>(
            "the 3rd file was found",
            new FirstIn<>(
                new PathOf("-f", new ListOf<>("-f", "red.md")),
                new PathOf("-f", () -> Paths.get("read.mddd")),
                new PathOf("-f", () -> Paths.get("readme.md"))
            ),
            new ArgHas<>(Paths.get("readme.md"))
        ).affirm();
    }

    @Test
    public void errorMessageIsCorrect() throws ArgNotFoundException {
        this.cause.expect(UncheckedIOException.class);
        this.cause.expectMessage("ArgNotFoundException: The '-f' argument wasn't specified");
        new FirstIn<>(
            "The '-f' argument wasn't specified",
            new PathOf("-f", () -> null),
            new PathOf("-f", () -> null)
        ).value();
    }
}
