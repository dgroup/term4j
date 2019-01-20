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

import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;

/**
 * Application error.
 *
 * Always has the error code which supposed to be exposed to the external
 *  parent process like CLI, bash shell, etc.
 *
 * @since 0.1.0
 */
public final class AppException extends Exception {

    /**
     * Application exit code.
     */
    private final Integer exit;

    /**
     * Message.
     */
    private final Iterable<Text> msg;

    /**
     * Ctor.
     * @param msg The message to be print to the user.
     */
    public AppException(final String msg) {
        this(-1, msg);
    }

    /**
     * Ctor.
     * @param msg The message to be print to the user.
     */
    public AppException(final Text msg) {
        this(-1, msg);
    }

    /**
     * Ctor.
     * @param exit Application exit code to be print to the user.
     * @param msg The message to be print to the user.
     */
    public AppException(final Integer exit, final Text msg) {
        this(exit, new IterableOf<>(msg));
    }

    /**
     * Ctor.
     * @param exit Application exit code to be print to the user.
     * @param msg The message to be print to the user.
     */
    public AppException(final Integer exit, final String msg) {
        this(exit, new IterableOf<>(() -> msg));
    }

    /**
     * Ctor.
     * @param exit Application exit code to be print to the user.
     * @param msg The message to be print to the user.
     */
    public AppException(final Integer exit, final Iterable<Text> msg) {
        super();
        this.exit = exit;
        this.msg = msg;
    }

    /**
     * Application exit code to be exposed to the parent OS process.
     * @return The code.
     */
    public Integer exitCode() {
        return this.exit;
    }

    /**
     * Application error message split by lines.
     * @return The message.
     */
    public Iterable<Text> message() {
        return this.msg;
    }
}
