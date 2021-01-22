# AutoNotifyList

## Concept

When using RecyclerView, it's our responsibility to notify the change we made when we insert/update/remove the existing dataset.
But it's also easy to make mistake accidentally because we have to sync all the operation we made all the time in both side. So there are tools like DiffUtil help us to get rid of calling notifyXXX manually. But in order to use DiffUtil, we have to copy our original dataset and modify on the new dataset to let DiffUtil do the compare things. The cost is approximate at O(N), but in most cases we can do even better since in the data modification stage we always know which part is modified. The idea of AutoNotifyList is to wrap the data we want to use and automatically calling related notifyXXX when we modify the dataset. So we can focus on the data itself and don't have to worry about how to notify the change correctly.

## Usage
Just use AutoNotifyList as your data and do as many insert/update/remove you want.
```kotlin
class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>(), OnRemoveListener<Int> {

    val list = AutoNotifyList<Int>(this)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    ...
}
```

Please go to ./app module for more information.

## Contributing

Thank you for being interested in contributing to this project. Check out the [CONTRIBUTING](https://github.com/carousell/AutoNotifyList/blob/master/CONTRIBUTING.md) document for more info.

## About

<a href="https://github.com/carousell/" target="_blank"><img src="https://avatars2.githubusercontent.com/u/3833591" width="100px" alt="Carousell" align="right"/></a>

**AutoNotifyList** is created and maintained by [Carousell](https://carousell.com/). Help us improve this project! We'd love the [feedback](https://github.com/carousell/AutoNotifyList/issues) from you.

We're hiring! Find out more at <http://careers.carousell.com/>

## License

**AutoNotifyList** is released under Apache License 2.0.
See [LICENSE](https://github.com/carousell/AutoNotifyList/blob/master/LICENSE) for more details.
