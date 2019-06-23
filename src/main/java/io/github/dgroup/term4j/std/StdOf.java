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

import java.io.PrintStream;
import org.cactoos.Proc;
import org.cactoos.Text;

/**
 * Default implementation of std output.
 *
 * @since 0.1.0
 */
public final class StdOf extends StdEnvelope {

    /**
     * The indent from the left side for more fancy/readable messages.
     */
    private static final String NO_INDENT = "";

    /**
     * Ctor.
     */
    public StdOf() {
        this(StdOf.NO_INDENT);
    }

    /**
     * Ctor.
     * @param idnt The indent from the left side of terminal for more
     *  fancy/readable messages.
     */
    public StdOf(final String idnt) {
        this(System.out, idnt);
    }

    /**
     * Ctor.
     * @param std The stream to redirect the output.
     */
    public StdOf(final PrintStream std) {
        this(std, StdOf.NO_INDENT);
    }

    /**
     * Ctor.
     * @param std The stream to redirect the output.
     * @param idnt The indent from the left side of terminal for more
     *  fancy/readable messages.
     */
    public StdOf(final PrintStream std, final String idnt) {
        this(
            lines -> {
                for (final Text msg : lines) {
                    std.printf("%s%s%n", idnt, msg.asString());
                }
            },
            line -> std.printf("%s%s", idnt, line.asString())
        );
    }

    /**
     * Ctor.
     * @param std The writer to redirect the output.
     */
    public StdOf(final Appendable std) {
        this(std, StdOf.NO_INDENT);
    }

    /**
     * Ctor.
     * @param std The writer to redirect the output.
     * @param idnt The indent from the left side of terminal for more
     *  fancy/readable messages.
     */
    public StdOf(final Appendable std, final String idnt) {
        this(msg -> std.append(idnt)
            .append(msg.asString())
            .append(System.lineSeparator())
        );
    }

    /**
     * Ctor.
     * @param print The procedure to handle each line of the output.
     */
    public StdOf(final Proc<Text> print) {
        this(
            lines -> {
                for (final Text line : lines) {
                    print.exec(line);
                }
            },
            print
        );
    }

    /**
     * Ctor.
     * @param print The procedure to print each line to the standard output with a new line.
     * @param printf The procedure to print the text to the standard output without a new line.
     */
    public StdOf(final Proc<Iterable<? extends Text>> print, final Proc<Text> printf) {
        super(print, printf);
    }
}
