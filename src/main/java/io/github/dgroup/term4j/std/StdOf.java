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
import java.io.Writer;
import org.cactoos.Proc;
import org.cactoos.Text;
import org.cactoos.scalar.And;

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
        this(System.out, StdOf.NO_INDENT);
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
        this(msg -> std.printf("%s%s%n", idnt, msg.asString()));
    }

    /**
     * Ctor.
     * @param std The writer to redirect the output.
     */
    public StdOf(final Writer std) {
        this(std, StdOf.NO_INDENT);
    }

    /**
     * Ctor.
     * @param std The writer to redirect the output.
     * @param idnt The indent from the left side of terminal for more
     *  fancy/readable messages.
     */
    public StdOf(final Writer std, final String idnt) {
        this(msg -> std.append(idnt)
            .append(msg.asString())
            .append(System.lineSeparator())
        );
    }

    /**
     * Ctor.
     * @param std The procedure to handle each line of the output.
     */
    public StdOf(final Proc<Text> std) {
        super(msgs -> new And(std, msgs).value());
    }
}
