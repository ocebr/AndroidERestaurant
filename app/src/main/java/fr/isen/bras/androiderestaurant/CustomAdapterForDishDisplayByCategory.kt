package fr.isen.bras.androiderestaurant
import fr.isen.bras.androiderestaurant.model.DishModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso



class CustomAdapterForDishDisplayByCategory(private val mList: List<DishModel>, private val cellClickListener : CellClickListener) : RecyclerView.Adapter<CustomAdapterForDishDisplayByCategory.ViewHolder>() {


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dish = mList[position]

        if (dish.images[0] != "") {
            Picasso.get()
                .load(dish.images[0])
                .error(R.drawable.pizza)
                .into(holder.itemImage)


        } else {
            holder.itemImage.setImageResource(R.drawable.pizza)
        }
        holder.itemText.text = dish.name_fr
        holder.itemprice.text = dish.prices[0].price + "€"
        val data = mList[position]
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }
        holder.itembin.setVisibility(View.INVISIBLE)
        holder.itemmoins.setVisibility(View.INVISIBLE)
        holder.itemplus.setVisibility(View.INVISIBLE)
    }


    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.itemimage)
        val itemText: TextView = itemView.findViewById(R.id.previousorder)
        val itemprice: TextView = itemView.findViewById(R.id.price)
        val itembin: ImageView = itemView.findViewById(R.id.bin)
        val itemplus: ImageView = itemView.findViewById(R.id.basketplus)
        val itemmoins: ImageView = itemView.findViewById(R.id.basketmoins)
    }
}

