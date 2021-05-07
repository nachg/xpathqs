package org.xpathqs.core.reflection

import org.xpathqs.core.selector.base.ISelector
import org.xpathqs.core.selector.base.SelectorState
import org.xpathqs.core.selector.selector.Selector
import org.xpathqs.core.selector.selector.SelectorProps

internal class SelectorReflection(
    private val obj: Selector,
    private val srf: SelectorReflectionFields = SelectorReflectionFields(obj)
) {
    fun setProp(name: String, value: Any): SelectorReflection {
        val member = srf.declaredFields.find { it.name == name }

        if (member != null) {
            member.isAccessible = true
            member.set(obj, value)
        }

        return this
    }

    fun setName(value: String) = setProp("name", value)
    fun setBase(value: ISelector) = setProp("base", value)
    fun setProps(value: SelectorProps) = setProp("props", value)

    fun freeze() = setProp("state", SelectorState.FREEZE)
    fun cloned() = setProp("state", SelectorState.CLONED)
}