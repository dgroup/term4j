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
import java.util.Collection;
import java.util.LinkedList;
import org.cactoos.Text;
import org.cactoos.collection.Mapped;

/**
 * The {@link Std} which stores all messages in-memory.
 *
 * @since 0.1.0
 */
public final class Inmem extends Envelope {

    /**
     * The lines printed to the STD output.
     */
    private final Collection<String> stdout;

    /**
     * Ctor.
     */
    public Inmem() {
        this(new LinkedList<>());
    }

    /**
     * Ctor.
     * @param stdout The mutable collection to print the std out.
     */
    public Inmem(final Collection<String> stdout) {
        super(msgs -> stdout.addAll(new Mapped<>(Text::asString, msgs)));
        this.stdout = stdout;
    }

    /**
     * All lines (as is, without any modification) which were printed to the STD
     *  output.
     * @return The lines printed to the STD output.
     */
    public Collection<String> std() {
        return this.stdout;
    }

}
