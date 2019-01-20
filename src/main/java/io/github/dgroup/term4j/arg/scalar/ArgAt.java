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
package io.github.dgroup.term4j.arg.scalar;

import io.github.dgroup.term4j.arg.ArgNotFoundException;
import java.util.List;
import org.cactoos.Scalar;
import org.cactoos.scalar.ItemAt;

/**
 * Find particular argument in command-line arguments specified by the user.
 *
 * @since 0.1.0
 */
public final class ArgAt implements Scalar<String> {

    /**
     * The label of the particular command-line argument.
     */
    private final String label;

    /**
     * All command-line arguments specified by the user.
     */
    private final List<String> args;

    /**
     * Ctor.
     * @param label The label of the particular cmd argument.
     * @param args All command-line arguments specified by the user.
     */
    public ArgAt(final String label, final List<String> args) {
        this.label = label;
        this.args = args;
    }

    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public String value() throws ArgNotFoundException {
        try {
            return new ItemAt<>(
                this.args.indexOf(this.label) + 1,
                this.args
            ).value();
            // @checkstyle IllegalCatchCheck (3 lines)
        } catch (final Exception cause) {
            throw new ArgNotFoundException(cause);
        }
    }
}
