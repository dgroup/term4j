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
package io.github.dgroup.term4j;

import io.github.dgroup.term4j.arg.ArgNotFoundException;

/**
 * Represents the command-line argument.
 *
 * @param <X> Type of item.
 * @since 0.1.0
 * @todo #/DEV Arg should extends {@link org.cactoos.Scalar}
 */
public interface Arg<X> {

    /**
     * The label of the command-line argument.
     *
     * For example, for {@code -t 20} the label is <em>-f</em>.
     * @return The argument's label.
     * @todo #/DEV Multiple labels support: Replace 'String label()' by object.
     *  Sometimes the arguments have several labels with short and long names
     *  like "-t" and "--threads". Now the arguments are supports only 1 label
     *  per argument.
     */
    String label();

    /**
     * The value of the command-line argument.
     *
     * The value should be specified after the label.
     * For example, for {@code -t 20} the value is <em>20</em>.
     *
     * @return The argument's value.
     * @throws ArgNotFoundException in case if the argument wasn't
     *  specified by the user or arguments itself are empty.
     */
    X value() throws ArgNotFoundException;

    /**
     * The presence of the argument's value.
     * @return The <em>true</em> in the case, when the user specified the
     *  argument from the shell.
     */
    boolean specifiedByUser();
}
