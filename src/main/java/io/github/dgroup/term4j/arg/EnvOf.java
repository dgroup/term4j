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

/**
 * The system environment variable.
 *
 * Examples are <em>JAVA_HOME</em>, <em>PATH</em>.
 * @see System#getenv()
 *
 * @since 0.1.0
 */
public final class EnvOf extends ArgEnvelope<String> {

    /**
     * Ctor.
     * @param variable The system environment variable.
     */
    public EnvOf(final String variable) {
        super(
            new ArgOf<>(
                () -> variable,
                () -> {
                    final String val = System.getenv(variable);
                    if (val == null) {
                        throw new ArgNotFoundException(
                            "The '%s' variable isn't set to the environment",
                            variable
                        );
                    }
                    return val;
                },
                () -> System.getenv(variable) != null
            )
        );
    }
}
