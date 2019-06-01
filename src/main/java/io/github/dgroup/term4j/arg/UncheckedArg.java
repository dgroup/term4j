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
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

/**
 * Argument that doesn't throw the checked {@link Exception}.
 *
 * @param <T> Type of command-line argument.
 * @since 0.1.0
 */
public class UncheckedArg<T> implements Arg<T> {

    /**
     * Origin.
     */
    private final Scalar<Arg<T>> origin;

    /**
     * Ctor.
     * @param arg Origin.
     */
    public UncheckedArg(final Arg<T> arg) {
        this(() -> arg);
    }

    /**
     * Ctor.
     * @param arg Origin.
     */
    public UncheckedArg(final Scalar<Arg<T>> arg) {
        this.origin = arg;
    }

    @Override
    public final String label() {
        return new Unchecked<>(this.origin).value().label();
    }

    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public final T value() {
        // @checkstyle IllegalCatchCheck (5 lines)
        try {
            return this.origin.value().value();
        } catch (final Exception exp) {
            throw new UncheckedArgNotFoundException(exp);
        }
    }

    @Override
    public final boolean specifiedByUser() {
        return new Unchecked<>(this.origin).value().specifiedByUser();
    }
}
