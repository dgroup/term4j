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
import java.net.URI;
import java.net.URL;
import java.util.List;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;

/**
 * The single URL command-line argument.
 *
 * The argument value evaluation is executed once.
 *
 * @since 0.4.0
 */
public final class UrlOf implements Arg<URL> {

    /**
     * The resource url as plain string.
     */
    private final Arg<String> url;

    /**
     * The evaluated url.
     */
    private final Unchecked<URL> val;

    /**
     * Ctor.
     * @param lbl The label of command-line argument.
     * @param args All command-line arguments.
     */
    public UrlOf(final String lbl, final List<String> args) {
        this.url = new StringOf(lbl, args);
        this.val = new Unchecked<>(
            new Sticky<>(
                () -> {
                    if (!this.specifiedByUser()) {
                        throw new ArgNotFoundException("Argument `%s` wasn't specified", lbl);
                    }
                    return new URI(this.url.value()).toURL();
                }
            )
        );
    }

    @Override
    public String label() {
        return this.url.label();
    }

    @Override
    public URL value() {
        return this.val.value();
    }

    @Override
    public boolean specifiedByUser() {
        return this.url.specifiedByUser();
    }
}
