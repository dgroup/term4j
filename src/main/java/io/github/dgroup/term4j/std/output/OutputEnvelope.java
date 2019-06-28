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

import io.github.dgroup.term4j.std.UncheckedStdOutputException;
import java.io.UncheckedIOException;
import org.cactoos.Proc;
import org.cactoos.Text;
import org.cactoos.func.UncheckedProc;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.FormattedText;

/**
 * The envelope of {@link Output}.
 *
 * @since 0.1.0
 */
public class OutputEnvelope implements Output {

    /**
     * The procedure to print the text to the standard output ending with new line.
     */
    private final UncheckedProc<Iterable<? extends Text>> prnt;

    /**
     * The procedure to print the text to the standard output.
     */
    private final UncheckedProc<Text> prntf;

    /**
     * Ctor.
     * @param print The procedure to print each line to the standard output with a new line.
     * @param printf The procedure to print the text to the standard output without a new line.
     */
    public OutputEnvelope(final Proc<Iterable<? extends Text>> print, final Proc<Text> printf) {
        this.prnt = new UncheckedProc<>(print);
        this.prntf = new UncheckedProc<>(printf);
    }

    @Override
    public final void print(final String... msgs) {
        this.print(new Mapped<>(msg -> () -> msg, msgs));
    }

    @Override
    public final void printf(final String ptrn, final Object... args) {
        try {
            this.prntf.exec(new FormattedText(ptrn, args));
        } catch (final UncheckedIOException cause) {
            throw new UncheckedStdOutputException(cause);
        }
    }

    @Override
    public final void print(final Text... msgs) {
        this.print(new IterableOf<>(msgs));
    }

    @Override
    public final void print(final Iterable<? extends Text> msgs) {
        try {
            this.prnt.exec(msgs);
        } catch (final UncheckedIOException cause) {
            throw new UncheckedStdOutputException(cause);
        }
    }
}
