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
package io.github.dgroup.term4j.std.output;

import java.io.PrintStream;
import org.cactoos.Proc;
import org.cactoos.Text;

/**
 * Default implementation of std output.
 *
 * @since 0.1.0
 */
public final class Stdout extends OutputEnvelope {

    /**
     * The indent from the left side of console for more fancy/readable messages.
     */
    private static final String NO_INDENT = "";

    /**
     * Ctor.
     */
    public Stdout() {
        this(Stdout.NO_INDENT);
    }

    /**
     * Ctor.
     * @param idnt The indent from the left side of terminal for more
     *  fancy/readable messages.
     */
    public Stdout(final String idnt) {
        this(System.out, idnt);
    }

    /**
     * Ctor.
     * @param std The stream to redirect the output.
     */
    public Stdout(final PrintStream std) {
        this(std, Stdout.NO_INDENT);
    }

    /**
     * Ctor.
     * @param std The stream to redirect the output.
     * @param idnt The indent from the left side of terminal for more
     *  fancy/readable messages.
     */
    public Stdout(final PrintStream std, final String idnt) {
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
    public Stdout(final Appendable std) {
        this(std, Stdout.NO_INDENT);
    }

    /**
     * Ctor.
     * @param std The writer to redirect the output.
     * @param idnt The indent from the left side of terminal for more
     *  fancy/readable messages.
     */
    public Stdout(final Appendable std, final String idnt) {
        this(
            lines -> {
                for (final Text line : lines) {
                    std.append(idnt)
                        .append(line.asString())
                        .append(System.lineSeparator());
                }
            },
            line -> std.append(idnt).append(line.asString())
        );
    }

    /**
     * Ctor.
     * @param print The procedure to print each line to the standard output with a new line.
     * @param printf The procedure to print the text to the standard output without a new line.
     */
    public Stdout(final Proc<Iterable<? extends Text>> print, final Proc<Text> printf) {
        super(print, printf);
    }
}
