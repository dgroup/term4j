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

import io.github.dgroup.term4j.Arg;
import io.github.dgroup.term4j.arg.Unchecked;
import org.cactoos.collection.CollectionOf;
import org.cactoos.iterable.IterableOf;
import org.cactoos.text.TextOf;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest matcher to verify the multiple value of {@link Arg}.
 *
 * @param <T> Type of item.
 * @since 0.1.0
 * @checkstyle ProtectedMethodInFinalClassCheck (200 lines)
 */
public final class ArgHave<T> extends TypeSafeMatcher<Arg<Iterable<T>>> {

    /**
     * Expected value.
     */
    private final Iterable<T> expected;

    /**
     * Ctor.
     * @param expected Value within unit test.
     */
    public ArgHave(final T... expected) {
        this(new IterableOf<>(expected));
    }

    /**
     * Ctor.
     * @param expected Value within unit test.
     */
    public ArgHave(final Iterable<T> expected) {
        super();
        this.expected = expected;
    }

    @Override
    public void describeTo(final Description dsc) {
        dsc.appendValue(new TextOf(this.expected));
    }

    @Override
    protected boolean matchesSafely(final Arg<Iterable<T>> item) {
        return new CollectionOf<>(
            new Unchecked<>(item).value()
        ).containsAll(
            new CollectionOf<>(this.expected)
        );
    }

    @Override
    protected void describeMismatchSafely(
        final Arg<Iterable<T>> item, final Description dsc
    ) {
        dsc.appendValue(new TextOf(new Unchecked<>(item).value()));
    }
}
