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

import java.util.ArrayList;
import java.util.List;
import org.cactoos.Scalar;
import org.cactoos.scalar.StickyScalar;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Represents root cause exception for particular exception.
 *
 * @since 0.1.0
 */
public final class RootcauseOf implements Scalar<Throwable> {

    /**
     * The root cause exception.
     */
    private final Scalar<Throwable> cause;

    /**
     * Ctor.
     * @param wrapped Top-level exception which may have hierarchy of children
     *  exceptions.
     */
    public RootcauseOf(final Throwable wrapped) {
        this(
            new StickyScalar<>(
                () -> {
                    Throwable rcause = new Ternary<>(
                        () -> wrapped.getCause() == null,
                        () -> wrapped,
                        wrapped::getCause
                    ).value();
                    final List<Throwable> visited = new ArrayList<>(5);
                    while (rcause.getCause() != null && !visited.contains(rcause.getCause())) {
                        rcause = rcause.getCause();
                        visited.add(rcause);
                    }
                    return rcause;
                }
            )
        );
    }

    /**
     * Ctor.
     * @param fcause Function to evaluate the root cause exception.
     *  This constructor also can be used for the unit-testing purposes.
     */
    public RootcauseOf(final Scalar<Throwable> fcause) {
        this.cause = fcause;
    }

    @Override
    public Throwable value() {
        return new UncheckedScalar<>(this.cause).value();
    }
}
