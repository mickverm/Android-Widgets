package be.mickverm.widgets.sample.recyclerview.ui.sectioned

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.mickverm.widget.recyclerview.utils.calculateObservableDiff
import be.mickverm.widget.recyclerview.utils.insertObservableSections
import be.mickverm.widgets.sample.R
import be.mickverm.widgets.sample.recyclerview.ui.ItemsViewModel
import be.mickverm.widgets.sample.recyclerview.ui.ItemsViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class SectionedItemsFragment : Fragment() {

    companion object {
        fun newInstance() = SectionedItemsFragment()
    }

    private lateinit var viewModel: ItemsViewModel
    private lateinit var adapter: SectionedItemsAdapter

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(
            this,
            ItemsViewModelFactory()
        ).get(ItemsViewModel::class.java)

        adapter = SectionedItemsAdapter()

        val rvItems = view.findViewById<RecyclerView>(R.id.rv_items)
        rvItems.layoutManager = GridLayoutManager(context, 3)
        rvItems.adapter = adapter

        viewModel.randomItemsSorted
            .compose(insertObservableSections { previous, current ->
                previous.text[0] != current.text[0]
            })
            .compose(calculateObservableDiff(::SectionedItemDiffCallback))
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(adapter)
            .addTo(disposables)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }
}
