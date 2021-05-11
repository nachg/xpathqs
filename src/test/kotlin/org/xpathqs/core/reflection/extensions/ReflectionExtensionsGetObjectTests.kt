/*
 * Copyright (c) 2021 Nikita A. Chegodaev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.xpathqs.core.reflection.extensions

import assertk.assertThat
import assertk.assertions.*
import org.junit.jupiter.api.Test
import org.xpathqs.core.reflection.getObject
import org.xpathqs.core.reflection.isObject
import org.xpathqs.core.selector.Block
import java.lang.IllegalArgumentException

internal class ReflectionExtensionsGetObjectTests {

    open class PageCls
    object Page: Block()
    object NoBasePage: PageCls()

    @Test
    fun getObjectForObject() {
        assertThat(Page::class.java.getObject())
            .isSameAs(Page)
    }

    @Test
    fun getObjectForNotPageObject() {
        assertThat {
            NoBasePage::class.java.getObject()
        }
            .isFailure()
            .hasClass(IllegalArgumentException::class.java)
    }

    @Test
    fun getObjectForClass() {
        assertThat {
            PageCls()::class.java.getObject()
        }
            .isFailure()
            .hasClass(IllegalArgumentException::class.java)
    }
}