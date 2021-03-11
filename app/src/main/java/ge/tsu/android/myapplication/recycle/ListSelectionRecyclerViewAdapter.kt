package ge.tsu.android.myapplication.recycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.tsu.android.myapplication.R
import java.util.*
import kotlin.collections.ArrayList

interface onClickInterface {
  fun onClick(position: Int)
}

class ListSelectionRecyclerViewAdapter(
  private var listTitles: MutableList<RecycleViewItem>
// , private var onclickInterface: onClickInterface
) : RecyclerView.Adapter<ListSelectionRecyclerViewAdapter.ListSelectionViewHolder>(), Filterable {
//  var onClickInterface: onClickInterface
var filteredList : MutableList<RecycleViewItem>

  init {
    this.filteredList = listTitles
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_view_recycle, parent, false)
    return ListSelectionViewHolder(view)
  }

  override fun getItemCount() = filteredList.size

  override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
//    holder.listPosition.text = (position + 1).toString()

    holder.listPosition.text = filteredList.get(position).itemNumber.toString()
    holder.listTitle.text = filteredList[position].itemText
    holder.listCheck.isChecked = filteredList.get(position).isChecked
    holder.listItemDetiles.text = filteredList.get(position).detiles

    holder.listCheck.setOnClickListener{
      filteredList.get(position).isChecked = !filteredList.get(position).isChecked
      if(RecyclerViewActivity.showChecked && !filteredList[position].isChecked){
        filteredList.removeAt(position)
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
    val listItemDetiles = itemView.findViewById(R.id.detiles) as TextView
  }

  override fun getFilter(): Filter {
    return object : Filter() {
      override fun performFiltering(constraint: CharSequence?): FilterResults {
        val charSearch = constraint.toString()
        if(charSearch.isEmpty()){
          filteredList=listTitles
        }else{
          var resultList = ArrayList<RecycleViewItem>()
          for (row in listTitles){
            if(row.itemText.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT)))
            {
              resultList.add(row)
            }
          }
          filteredList=resultList
        }
        val filterResults = FilterResults()
        filterResults.values = filteredList
        return filterResults
      }

      @Suppress("UNCHECKED_CAST")
      override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        filteredList = results?.values as ArrayList<RecycleViewItem>
        notifyDataSetChanged()
      }
    }
  }
}
