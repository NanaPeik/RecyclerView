package ge.tsu.android.myapplication.recycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.tsu.android.myapplication.R

class ListSelectionRecyclerViewAdapter(
    private val listTitles: MutableList<RecycleViewItem>,
) : RecyclerView.Adapter<ListSelectionRecyclerViewAdapter.ListSelectionViewHolder>() {
    var showChecked = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_recycle, parent, false)
        return ListSelectionViewHolder(view)
    }

    override fun getItemCount() = listTitles.size

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = listTitles[position].itemNumber.toString()
        holder.listTitle.text = listTitles[position].itemText
        holder.listCheck.isChecked = listTitles[position].isChecked

        holder.listCheck.setOnCheckedChangeListener { _, isChecked ->
            listTitles[position].isChecked = isChecked

            if (isChecked != showChecked) {
                listTitles.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    class ListSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listPosition = itemView.findViewById(R.id.itemNumber) as TextView
        val listTitle = itemView.findViewById(R.id.itemString) as TextView
        val listCheck = itemView.findViewById(R.id.list_item_checkbox) as CheckBox
    }
}
