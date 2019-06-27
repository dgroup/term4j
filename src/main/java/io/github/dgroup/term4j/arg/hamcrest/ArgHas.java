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
package io.github.dgroup.term4j.arg.hamcrest;

import io.github.dgroup.term4j.arg.Arg;
import io.github.dgroup.term4j.arg.UncheckedArg;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest matcher to verify the single value of {@link Arg}.
 *
 * @param <T> Type of item.
 * @since 0.1.0
 * @checkstyle ProtectedMethodInFinalClassCheck (200 lines)
 */
public final class ArgHas<T> extends TypeSafeMatcher<Arg<T>> {

    /**
     * Expected value.
     */
    private final T expected;

    /**
     * Ctor.
     * @param expected Value within unit test.
     */
    @SuppressWarnings("PMD.CallSuperInConstructor")
    public ArgHas(final T expected) {
        this.expected = expected;
    }

    @Override
    public void describeTo(final Description desc) {
        desc.appendValue(this.expected);
    }

    @Override
    protected boolean matchesSafely(final Arg<T> item) {
        return this.expected.equals(new UncheckedArg<>(item).value());
    }

    @Override
    protected void describeMismatchSafely(
        final Arg<T> item, final Description desc
    ) {
        desc.appendValue(new UncheckedArg<>(item).value());
    }
}
