package org.xpathqs.core.reflection

import org.xpathqs.core.selector.Block
import org.xpathqs.core.selector.extensions.plus
import org.xpathqs.core.selector.extensions.repeat
import org.xpathqs.core.selector.selector.Selector
import org.xpathqs.core.selector.selector.SelectorProps
import org.xpathqs.core.util.SelectorFactory.tagSelector

object PageWithBase : Block(
    Selector(
        props = SelectorProps(tag = "base")
    )
) {
    val s1 = Selector(props = SelectorProps(tag = "s1"))
}

object PageNoBase : Block() {
    val s1 = Selector(props = SelectorProps(tag = "s1"))
}

object PageWithBaseWithChainXpath : Block(tagSelector("div").repeat(3)) {
    val s1 = Selector(props = SelectorProps(tag = "s1"))
}

object PageWithBaseWithChain : Block(tagSelector("div") + tagSelector("div")) {
    val s1 = Selector(props = SelectorProps(tag = "s1"))
}

object PageWithBaseAndInnerObject : Block(
    Selector(
        props = SelectorProps(tag = "base")
    )
) {
    val s1_base = Selector(props = SelectorProps(tag = "base_tag"))

    object Inner : Block(
        Selector(
            props = SelectorProps(tag = "inner")
        )
    ) {
        val s1_inner = Selector(props = SelectorProps(tag = "inner_tag"))
    }
}