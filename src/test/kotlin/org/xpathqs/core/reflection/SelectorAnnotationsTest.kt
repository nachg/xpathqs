/*
 * Copyright (c) 2022 XPATH-QS
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

package org.xpathqs.core.reflection

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.xpathqs.core.selector.block.Block
import org.xpathqs.core.selector.extensions.core.get
import org.xpathqs.core.util.SelectorFactory.tagSelector

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY
)
@Retention(AnnotationRetention.RUNTIME)
annotation class TestAnnotation1

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY
)
@Retention(AnnotationRetention.RUNTIME)
annotation class TestAnnotation2

class Container: Block() {
    val s1 = tagSelector("div")
}

@TestAnnotation1
object PageWithAnnotations : Block() {
    @TestAnnotation2
    val s1 = tagSelector()
    val s2 = tagSelector()

    @TestAnnotation2
    @TestAnnotation1
    val s3 = tagSelector()
}

object WithContainer: Block() {
    @TestAnnotation2
    val s4 = Container()
}

@TestAnnotation1
open class Base: Block()

@TestAnnotation2
object Inherited: Base()

class SelectorAnnotationsTest : AnnotationSpec() {

    @Test
    fun checkBlock() {
        PageWithAnnotations.annotations shouldHaveSize 1
    }

    @Test
    fun checkSelector() {
        PageWithAnnotations.s1.annotations shouldHaveSize 1
    }

    @Test
    fun checkSelectorWithoutAnnotation() {
        PageWithAnnotations.s2.annotations shouldHaveSize 0
    }

    @Test
    fun checkSelectorWithTwoAnnotation() {
        PageWithAnnotations.s3.annotations shouldHaveSize 2
    }

    @Test
    fun annotationsShouldBeCloned() {
        PageWithAnnotations.s3[2].annotations shouldHaveSize 2
    }

    @Test
    fun noFieldForRootObj() {
        PageWithAnnotations.field.shouldBeNull()
    }

    @Test
    fun fieldForSelectors() {
        assertSoftly {
            PageWithAnnotations.s1.field.shouldNotBeNull()
            PageWithAnnotations.s1.field?.name shouldBe "s1"
        }
    }

    @Test
    fun parseAnnotationsForBlockMember() {
        WithContainer.s4.annotations shouldHaveSize 1
    }

    @Test
    fun checkInheritedBlock() {
        Inherited.annotations shouldHaveSize 2
    }

    init {
        SelectorParser(PageWithAnnotations).parse()
        SelectorParser(WithContainer).parse()
        Inherited.parse()
    }
}