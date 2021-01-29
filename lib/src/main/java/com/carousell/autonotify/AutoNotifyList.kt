package com.carousell.autonotify

import androidx.recyclerview.widget.RecyclerView

class AutoNotifyList<T> constructor(
    private val adapter: RecyclerView.Adapter<*>,
    private val delegate: MutableList<T> = mutableListOf()
) : MutableList<T> {

    override val size: Int
        get() = delegate.size

    override fun contains(element: T) = delegate.contains(element)

    override fun containsAll(elements: Collection<T>) = delegate.containsAll(elements)

    override fun get(index: Int) = delegate[index]

    override fun indexOf(element: T) = delegate.indexOf(element)

    override fun isEmpty() = delegate.isEmpty()

    override fun iterator() = delegate.iterator()

    override fun lastIndexOf(element: T) = delegate.lastIndexOf(element)

    override fun add(element: T): Boolean {
        val index = size
        delegate.add(element)
        adapter.notifyItemInserted(index)
        return true
    }

    override fun add(index: Int, element: T) {
        delegate.add(index, element)
        adapter.notifyItemInserted(index)
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        val result = delegate.addAll(index, elements)
        if (result) {
            adapter.notifyItemRangeInserted(index, elements.size)
        }
        return result
    }

    override fun addAll(elements: Collection<T>): Boolean {
        val start = size
        val result = delegate.addAll(elements)
        if (result) {
            adapter.notifyItemRangeInserted(start, elements.size)
        }
        return result
    }

    override fun clear() {
        val length = size
        delegate.clear()
        adapter.notifyItemRangeRemoved(0, length)
    }

    override fun listIterator() = delegate.listIterator()


    override fun listIterator(index: Int) = delegate.listIterator(index)

    override fun remove(element: T): Boolean {
        val index = delegate.indexOf(element)
        val result = delegate.remove(element)
        if (result) {
            adapter.notifyItemRemoved(index)
        }
        return result
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        elements.map {
            remove(it)
        }
        return true
    }

    override fun removeAt(index: Int): T {
        val result = delegate.removeAt(index)
        adapter.notifyItemRemoved(index)
        return result
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        val removeElements = delegate.toMutableList().also {
            it.removeAll(elements)
        }
        return removeAll(removeElements)
    }

    override fun set(index: Int, element: T): T {
        val result = delegate.set(index, element)
        adapter.notifyItemChanged(index)
        return result
    }

    override fun subList(fromIndex: Int, toIndex: Int) = delegate.subList(fromIndex, toIndex)

}