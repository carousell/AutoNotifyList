package com.carousell.autonotifylist

import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class AutoNotifyList<T> private constructor(
    private val list: MutableList<T>,
    private val adapter: RecyclerView.Adapter<*>
) : InvocationHandler {

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun <T> bind(list: MutableList<T>, adapter: RecyclerView.Adapter<*>): MutableList<T> {
            return Proxy.newProxyInstance(
                list.javaClass.classLoader,
                list.javaClass.interfaces,
                AutoNotifyList(list, adapter)
            ) as MutableList<T>
        }
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        return method?.invoke(list, *(args ?: arrayOfNulls<Any>(0))).also {
            when (method?.name) {
                "add" -> {
                    if (args?.size == 1) {
                        adapter.notifyItemInserted(list.size)
                    } else if (args?.size == 2) {
                        adapter.notifyItemInserted(args[0] as Int)
                    }
                }
                "remove" -> {
                    adapter.notifyItemRemoved(args?.get(0) as Int)
                }
            }
        }
    }
}