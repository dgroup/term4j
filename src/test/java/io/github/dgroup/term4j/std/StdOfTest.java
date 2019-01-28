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

import io.github.dgroup.term4j.Std;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import org.junit.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasLines;

/**
 * Test case for {@link StdOf}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (200 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class StdOfTest {

    /**
     * Simulate the STD print procedure using {@link PrintStream}.
     */
    @Test
    public void print() {
        new Assertion<>(
            "5 lines of text were printed to the output",
            () -> {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {
                    final Std std = new StdOf(ps);
                    std.print("line1", "line2");
                    std.print("line3", "line4");
                    std.print("line%s", 5);
                }
                return new String(baos.toByteArray(), StandardCharsets.UTF_8);
            },
            new HasLines("line1", "line2", "line3", "line4", "line5")
        ).affirm();
    }

    /**
     * Simulate the STD print procedure using {@link StringWriter}.
     */
    @Test
    public void printToWriter() {
        new Assertion<>(
            "4 lines of text were printed to the output",
            () -> {
                final StringWriter swter = new StringWriter();
                final Std std = new StdOf(swter);
                std.print("line1", "line2");
                std.print("line3", "line4");
                return swter.toString();
            },
            new HasLines("line1", "line2", "line3", "line4")
        ).affirm();
    }
}
