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

package org.xpathqs.core.util

import org.xpathqs.core.constants.Global
import org.xpathqs.core.selector.XpathSelector
import org.xpathqs.core.selector.args.KVSelectorArg
import org.xpathqs.core.selector.args.SelectorArgs
import org.xpathqs.core.selector.args.decorators.CommaDecorator
import org.xpathqs.core.selector.args.decorators.ContainsDecorator
import org.xpathqs.core.selector.base.ISelector
import org.xpathqs.core.selector.compose.ComposeSelector
import org.xpathqs.core.selector.compose.ComposeSelectorProps
import org.xpathqs.core.selector.selector.Selector
import org.xpathqs.core.selector.selector.SelectorProps

object SelectorFactory {
    fun tagSelector(tag: String = "*") = Selector(props = SelectorProps(tag = tag))

    fun xpathSelector(xpath: String) = XpathSelector(xpath)

    fun textSelector(text: String) = Selector(
        props = SelectorProps(
            args = SelectorArgs(
                CommaDecorator(
                    KVSelectorArg(
                        k = Global.TEXT_ARG,
                        v = text
                    )
                )
            )
        )
    )

    fun textContainsSelector(text: String) = Selector(
        props = SelectorProps(
            args = SelectorArgs(
                ContainsDecorator(
                    CommaDecorator(
                        KVSelectorArg(
                            k = Global.TEXT_ARG,
                            v = text
                        )
                    )
                )
            )
        )
    )

    fun idSelector(id: String) = Selector(
        props = SelectorProps(
            args = SelectorArgs(
                CommaDecorator(
                    KVSelectorArg(
                        k = Global.ID_ARG,
                        v = id
                    )
                )
            )
        )
    )

    fun idContainsSelector(id: String) = Selector(
        props = SelectorProps(
            args = SelectorArgs(
                ContainsDecorator(
                    CommaDecorator(
                        KVSelectorArg(
                            k = Global.ID_ARG,
                            v = id
                        )
                    )
                )
            )
        )
    )

    fun compose(vararg selectors: ISelector) =
        ComposeSelector(
            props = ComposeSelectorProps(
                selectors = ArrayList<ISelector>().apply {
                    addAll(selectors)
                }
            )
        )
}