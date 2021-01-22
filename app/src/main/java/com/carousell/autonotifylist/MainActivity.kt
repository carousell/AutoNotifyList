package com.carousell.autonotifylist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.carousell.autonotifylist.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyAdapter()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        val list = adapter.list
        lifecycleScope.launch {
            val queue = LinkedList((1..100).toMutableList().shuffled())
            val random = Random.Default
            while (queue.isNotEmpty()) {

                queue.poll()?.let {
                    delay(100)
                    list.add(findIndex(list, it), it)
                }

                if (random.nextFloat() > 0.7) {
                    delay(100)
                    val value = list.removeAt(random.nextInt(list.size))
                    queue.offer(value)
                }
            }
        }
    }

    private fun findIndex(list: List<Int>, target: Int): Int {
        var start = 0
        var end = list.size
        while (start < end) {
            val middle = start + (end - start) / 2
            if (list[middle] < target) {
                start = middle + 1
            } else {
                end = middle
            }
        }
        return start
    }
}