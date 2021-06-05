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

package org.xpathqs.core.selector.extensions

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNotSameAs
import assertk.assertions.isSameAs
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.xpathqs.core.reflection.PageWithGroupBase
import org.xpathqs.core.reflection.SelectorParser
import org.xpathqs.core.selector.block.deepClone

class PageObjectCloneGroupTests {
    @BeforeEach
    fun before() {
        SelectorParser(PageWithGroupBase).parse()
    }

    @Test
    fun checkClone() {
        val origin = PageWithGroupBase
        val cloned = PageWithGroupBase.deepClone()

        assertAll {
            assertThat(origin)
                .isNotSameAs(cloned)

            assertThat(origin.props)
                .isNotSameAs(cloned.props)

            assertThat(origin.children)
                .isSameAs(cloned.children)

            assertThat(origin.children.size)
                .isEqualTo(cloned.children.size)

            assertThat(cloned.s1.field)
                .isNotNull()
        }
    }
}