package ge.tsu.android.myapplication.recycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.tsu.android.myapplication.R

interface onClickInterface {
    fun setClick(abc: Int)
}
class ListSelectionRecyclerViewAdapter(
        private val listTitles: ArrayList<String>,
        private var onclickInterface: onClickInterface) : RecyclerView.Adapter<ListSelectionRecyclerViewAdapter.ListSelectionViewHolder>() {
    var onClickInterface: onClickInterface

    init{
        this.onClickInterface = onclickInterface
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_selection_view_holder, parent, false)
        return ListSelectionViewHolder(view)
    }

    override fun getItemCount() = listTitles.size

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = (position + 1).toString()
        holder.listTitle.text = listTitles[position]
        holder.listTitle.setText(listTitles.get(position))
        holder.listTitle.setOnClickListener {
            onClickInterface.setClick(position)
        }
    }

    class ListSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listPosition = itemView.findViewById(R.id.itemNumber) as TextView
        val listTitle = itemView.findViewById(R.id.itemString) as TextView
    }

}
