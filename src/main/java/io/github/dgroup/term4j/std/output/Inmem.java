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

import java.util.Collection;
import java.util.Collections;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.collection.CollectionOf;

/**
 * The {@link Output} which stores all messages in-memory.
 *
 * The class implements {@link Scalar} which represents the standard output,
 *  as is, without any modifications, split by the {@link System#lineSeparator()}.
 *
 * @since 0.1.0
 */
public final class Inmem extends OutputEnvelope implements Scalar<Collection<String>> {

    /**
     * The lines printed to the STD output.
     */
    private final Appendable stdout;

    /**
     * Ctor.
     */
    public Inmem() {
        this(new StringBuffer());
    }

    /**
     * Ctor.
     * @param stdout The mutable collection to print the std out.
     */
    private Inmem(final Appendable stdout) {
        super(
            msgs -> {
                for (final Text msg : msgs) {
                    stdout.append(msg.asString()).append(System.lineSeparator());
                }
            },
            msg -> stdout.append(msg.asString())
        );
        this.stdout = stdout;
    }

    @Override
    public String toString() {
        return this.stdout.toString();
    }

    @Override
    public Collection<String> value() {
        final String raw = this.toString();
        final Collection<String> val;
        if (raw.isEmpty()) {
            val = Collections.emptyList();
        } else {
            val = new CollectionOf<>(
                raw.split(System.lineSeparator())
            );
        }
        return val;
    }
}
