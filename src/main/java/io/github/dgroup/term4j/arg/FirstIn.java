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
import org.cactoos.iterable.IterableOf;

/**
 * The argument which detect the first specified argument.
 *
 * @param <X> The type of argument.
 * @since 0.3.0
 */
public final class FirstIn<X> extends ArgEnvelope<X> {

    /**
     * Ctor.
     * @param args The arguments to detect first specified argument.
     */
    @SafeVarargs
    public FirstIn(final Arg<X>... args) {
        this(new IterableOf<>(args));
    }

    /**
     * Ctor.
     * @param err The error message in the case if no arguments found.
     * @param args The arguments to detect first specified argument.
     */
    @SafeVarargs
    public FirstIn(final String err, final Arg<X>... args) {
        this(err, new IterableOf<>(args));
    }

    /**
     * Ctor.
     * @param args The arguments to detect first specified argument.
     */
    public FirstIn(final Iterable<Arg<X>> args) {
        this("No args specified", args);
    }

    /**
     * Ctor.
     * @param err The error message in the case if no arguments found.
     * @param args The arguments to detect first specified argument.
     */
    public FirstIn(final String err, final Iterable<Arg<X>> args) {
        super(() -> {
            if (args == null) {
                throw new ArgNotFoundException("Arguments are null");
            }
            for (final Arg<X> arg : args) {
                try {
                    if (arg != null && arg.specifiedByUser()) {
                        return arg;
                    }
                } catch (final UncheckedArgNotFoundException cause) {
                    continue;
                }
            }
            throw new ArgNotFoundException(err);
        });
    }
}
