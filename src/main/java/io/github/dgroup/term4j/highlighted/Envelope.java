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
package io.github.dgroup.term4j.highlighted;

import io.github.dgroup.term4j.Highlighted;
import java.util.Objects;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.text.UncheckedText;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

/**
 * The envelope for {@link Highlighted}.
 *
 * @since 0.1.0
 */
public class Envelope implements Highlighted, Comparable<Text> {

    /**
     * Origin.
     */
    private final Scalar<Text> msg;

    /**
     * Ctor.
     * @param msg Message which should be highlighted.
     * @param color Color which should be used for highlighting.
     */
    public Envelope(final Object msg, final Color color) {
        this(msg, ansi -> ansi.fgBright(color));
    }

    /**
     * Ctor.
     * @param msg Message which should be highlighted.
     * @param fnc The function to
     */
    public Envelope(final Object msg, final Func<Ansi, Ansi> fnc) {
        this(() -> () -> fnc.apply(Ansi.ansi()).a(msg).reset().toString());
    }

    /**
     * Ctor.
     * @param msg The original message.
     */
    public Envelope(final Scalar<Text> msg) {
        this.msg = msg;
    }

    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public final String asString() {
        try {
            return this.msg.value().asString();
            // @checkstyle IllegalCatchCheck (3 lines)
        } catch (final Exception cause) {
            throw new UncheckedHighlightingException(cause);
        }
    }

    @Override
    public final String toString() {
        return this.asString();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.asString());
    }

    @Override
    @SuppressWarnings("PMD.OnlyOneReturn")
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Text)) {
            return false;
        }
        final Text that = (Text) obj;
        return Objects.equals(
            this.asString(), new UncheckedText(that).asString()
        );
    }

    @Override
    public final int compareTo(final Text that) {
        return this.asString().compareTo(
            new UncheckedText(that).asString()
        );
    }
}
