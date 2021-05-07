package org.xpathqs.core.selector.args.decorators

import org.xpathqs.core.selector.args.KVSelectorArg
import org.xpathqs.core.selector.args.ValueArg

class ContainsDecorator(private val wrapper: KVSelectorArg) : ValueArg() {
    override val value: String
        get() = "contains(${wrapper.k}, ${wrapper.v})"
}