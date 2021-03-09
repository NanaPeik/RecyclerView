package ge.tsu.android.myapplication.recycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.tsu.android.myapplication.R

interface onClickInterface {
  fun onClick(position: Int)
}

class ListSelectionRecyclerViewAdapter(
  private var listTitles: MutableList<RecycleViewItem>
// , private var onclickInterface: onClickInterface
) : RecyclerView.Adapter<ListSelectionRecyclerViewAdapter.ListSelectionViewHolder>() {
//  var onClickInterface: onClickInterface

//  init {
//    this.onClickInterface = onclickInterface
//  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_view_recycle, parent, false)
    return ListSelectionViewHolder(view)
  }

  override fun getItemCount() = listTitles.size

  override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
//    holder.listPosition.text = (position + 1).toString()
//  if(listTitles.get(position).isChecked) {
    holder.listPosition.text = listTitles.get(position).itemNumber.toString()
    holder.listTitle.text = listTitles[position].itemText
    holder.listCheck.isChecked = listTitles.get(position).isChecked
//  }
    holder.listCheck.setOnClickListener{
      listTitles.get(position).isChecked = !listTitles.get(position).isChecked
      if(RecyclerViewActivity.showChecked && !listTitles[position].isChecked){
        listTitles.removeAt(position)
        notifyItemRemoved(position)
      }
    }
//    holder.listTitle.setText(listTitles.get(position))

//    holder.listTitle.setOnClickListener {
//      onClickInterface.onClick(position)
//    }
  }

  class ListSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val listPosition = itemView.findViewById(R.id.itemNumber) as TextView
    val listTitle = itemView.findViewById(R.id.itemString) as TextView
    val listCheck = itemView.findViewById(R.id.list_item_checkbox) as CheckBox
  }
}
