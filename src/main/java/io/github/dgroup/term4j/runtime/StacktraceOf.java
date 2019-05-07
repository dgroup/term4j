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

package io.github.dgroup.term4j.runtime;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.cactoos.Scalar;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;

/**
 * Exception stacktrace.
 *
 * @since 0.4.0
 */
public final class StacktraceOf implements Scalar<String> {

    /**
     * The text.
     */
    private final Unchecked<String> txt;

    /**
     * Ctor.
     * @param cause The original root cause.
     */
    public StacktraceOf(final Throwable cause) {
        this(
            new Sticky<>(
                () -> {
                    final StringWriter swr = new StringWriter();
                    final PrintWriter pwr = new PrintWriter(swr, true);
                    cause.printStackTrace(pwr);
                    return swr.getBuffer().toString();
                }
            )
        );
    }

    /**
     * Ctor.
     * @param txt The stacktrace as text.
     */
    public StacktraceOf(final Scalar<String> txt) {
        this.txt = new Unchecked<>(
            new Sticky<>(txt)
        );
    }

    @Override
    public String toString() {
        return this.value();
    }

    @Override
    public String value() {
        return this.txt.value();
    }
}
