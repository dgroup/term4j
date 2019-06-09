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

package io.github.dgroup.term4j.arg;

import io.github.dgroup.term4j.Arg;
import java.util.Collection;
import org.cactoos.collection.CollectionOf;

/**
 * Joined arguments.
 *
 * @param <X> The type of argument.
 *
 * @since 0.4.0
 */
public final class Joined<X> extends ArgEnvelope<Collection<X>> {

    /**
     * Ctor.
     * @param args All command-line arguments.
     * @param items The items to be merged into original value.
     */
    public Joined(final Arg<Collection<X>> args, final X... items) {
        this(args, new CollectionOf<>(items));
    }

    /**
     * Ctor.
     * @param args All command-line arguments.
     * @param items The items to be merged into original value.
     */
    public Joined(final Arg<Collection<X>> args, final Collection<X> items) {
        super(new Arg<Collection<X>>() {
            @Override
            public String label() {
                return args.label();
            }

            @Override
            public Collection<X> value() throws ArgNotFoundException {
                return new org.cactoos.collection.Joined<>(args.value(), items);
            }

            @Override
            public boolean specifiedByUser() {
                return args.specifiedByUser();
            }
        });
    }
}
