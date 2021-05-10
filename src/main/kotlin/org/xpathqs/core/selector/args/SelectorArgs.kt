package org.xpathqs.core.selector.args

class SelectorArgs(
    private val args: HashMap<String, ValueArg> = HashMap()
) : Cloneable {

    constructor(arg: ValueArg) : this() {
        add(arg)
    }

    fun toXpath(): String {
        if (args.isEmpty()) return ""

        val sorted = args.values.sortedBy { it.joinType }

        val sb = StringBuilder()
        sorted.forEach {
            sb.append(it.toXpath())
        }

        val res = sb
            .toString()
            .trim()
            .replace("  ", " ")

        return "[$res]"
    }

    fun add(arg: ValueArg): SelectorArgs {
        if (args.isNotEmpty() && arg.isNone) {
            arg.joinType = JoinType.AND
        }
        args[arg.key] = arg
        return this
    }

    @Suppress("UNCHECKED_CAST")
    public override fun clone(): SelectorArgs {
        return SelectorArgs(args.clone() as HashMap<String, ValueArg>)
    }
}