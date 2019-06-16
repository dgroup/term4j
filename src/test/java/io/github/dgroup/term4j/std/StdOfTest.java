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

import io.github.dgroup.term4j.Highlighted;
import io.github.dgroup.term4j.Std;
import io.github.dgroup.term4j.highlighted.Green;
import io.github.dgroup.term4j.highlighted.Red;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import org.cactoos.collection.CollectionOf;
import org.junit.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasLines;

/**
 * Test case for {@link StdOf}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (200 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class StdOfTest {

    /**
     * Simulate the STD print procedure using {@link PrintStream}.
     */
    @Test
    public void print() throws UnsupportedEncodingException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {
            final Std std = new StdOf(ps);
            std.print("line1", "line2");
            std.print("line3", "line4");
            std.printf("line%s", 5);
        }
        new Assertion<>(
            "5 lines of text were printed to the output",
            new String(baos.toByteArray(), StandardCharsets.UTF_8),
            new HasLines("line1", "line2", "line3", "line4", "line5")
        ).affirm();
    }

    /**
     * Simulate the STD print procedure using {@link StringWriter}.
     */
    @Test
    public void printToWriter() {
        final StringWriter swter = new StringWriter();
        final Std std = new StdOf(swter);
        std.print("line1", "line2");
        std.print("line3", "line4");
        new Assertion<>(
            "4 lines of text were printed to the output",
            swter.toString(),
            new HasLines("line1", "line2", "line3", "line4")
        ).affirm();
    }

    /**
     * Ensure that subtypes of {@link org.cactoos.Text} can be printed through the {@link Std}.
     */
    @Test
    public void inheritance() {
        final Collection<Highlighted> colours = new CollectionOf<>(
            new Green(" one "), new Red(" two ")
        );
        final StringWriter swter = new StringWriter();
        final Std std = new StdOf(swter);
        std.print(colours);
        new Assertion<>(
            "subtypes of text has been printed",
            swter.toString(),
            new HasLines("\u001B[92m one \u001B[m", "\u001B[91m two \u001B[m")
        ).affirm();
    }
}
