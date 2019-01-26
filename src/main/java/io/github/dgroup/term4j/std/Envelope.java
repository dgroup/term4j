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
import org.cactoos.Proc;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.FormattedText;

/**
 * The envelope of {@link Std}.
 *
 * @since 0.1.0
 */
public class Envelope implements Std {

    /**
     * The procedure to print the text to the standart output.
     */
    private final Proc<Iterable<Text>> prc;

    /**
     * Ctor.
     * @param prc The procedure to print the text to the standart output.
     */
    public Envelope(final Proc<Iterable<Text>> prc) {
        this.prc = prc;
    }

    @Override
    public final void print(final String... msgs) {
        this.print(new Mapped<>(msg -> () -> msg, msgs));
    }

    @Override
    public final void print(final String ptrn, final Object... args) {
        this.print(new FormattedText(ptrn, args));
    }

    @Override
    public final void print(final Text... msgs) {
        this.print(new IterableOf<>(msgs));
    }

    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public final void print(final Iterable<Text> msgs) {
        try {
            this.prc.exec(msgs);
            // @checkstyle IllegalCatchCheck (3 lines)
        } catch (final Exception cause) {
            throw new UncheckedStdOutputException(cause);
        }
    }
}
