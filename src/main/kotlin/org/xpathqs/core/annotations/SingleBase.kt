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

package org.xpathqs.core.annotations

import org.xpathqs.core.selector.selector.Selector
import org.xpathqs.core.selector.block.Block

/**
 * [Selector]s and [Block]s marked with a [SingleBase] annotation
 * will be linked to theirs root with a single '/' relation
 *
 * Require #1 - field marked with [SingleBase] should be linked with '/'
 * @sample org.xpathqs.core.reflection.SingleBaseTest.r1_forSelector
 *
 * Require #2 - all inner selectors of [Block] marked with [SingleBase] should be linked with '/'
 * @sample org.xpathqs.core.reflection.SingleBaseTest.r2_forBlock
 */
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class SingleBase
