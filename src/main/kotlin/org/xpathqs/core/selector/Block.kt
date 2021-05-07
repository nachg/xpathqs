package org.xpathqs.core.selector

import org.xpathqs.core.reflection.freeze
import org.xpathqs.core.reflection.setBase
import org.xpathqs.core.selector.base.ISelector
import org.xpathqs.core.selector.base.SelectorState
import org.xpathqs.core.selector.extensions.clone
import org.xpathqs.core.selector.selector.Selector
import org.xpathqs.core.selector.selector.SelectorProps

open class Block(
    internal val isBlank: Boolean = true,
    base: ISelector = NullSelector(),
    props: SelectorProps = SelectorProps(),
) : Selector(
    base = base,
    props = props
) {
    internal var originBlock: ISelector = NullSelector()
    internal var children: Collection<Selector> = emptyList()

    constructor(sel: Selector) : this(
        isBlank = false,
        base = sel.base.clone(),
        props = sel.props.clone()
    )

    override fun toXpath(): String {
        if (isBlank) {
            return ""
        }

        val res = super.toXpath()

        fixChildrenSelectors()

        return res
    }

    protected fun fixChildrenSelectors() {
        if (state == SelectorState.CLONED) {
            children.forEach {
                it.setBase(originBlock)
            }
            freeze()
        }
    }
}