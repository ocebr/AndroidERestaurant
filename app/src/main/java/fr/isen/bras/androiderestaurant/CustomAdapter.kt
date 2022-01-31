package fr.isen.bras.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.bras.androiderestaurant.model.DishModel
import fr.isen.bras.androiderestaurant.model.ItemsViewModel


class CustomAdapter(private val mList: List<DishModel>, private val cellClickListener : CellClickListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dish = mList[position]


        //holder.itemImage.setImageResource(DishModel.)
        holder.itemText.text = dish.name_fr
        //holder.itemDetail.text = ItemsViewModel.detail

        val data = mList[position]
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        //val itemImage: ImageView = itemView.findViewById(R.id.itemimage)
        val itemText: TextView = itemView.findViewById(R.id.itemtext)
        //val itemDetail: TextView = itemView.findViewById(R.id.itemdetail)
    }
}

