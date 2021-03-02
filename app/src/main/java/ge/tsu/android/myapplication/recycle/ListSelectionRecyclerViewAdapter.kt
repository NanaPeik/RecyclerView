package ge.tsu.android.myapplication.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.tsu.android.myapplication.R

class ListSelectionRecyclerViewAdapter(private val listTitles: Array<String>):
    RecyclerView.Adapter<ListSelectionViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_selection_view_holder, parent,false)
        return ListSelectionViewHolder(view)
    }

    override fun getItemCount() = listTitles.size

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text=(position+1).toString()
        holder.listTitle.text=listTitles[position]
    }
}
