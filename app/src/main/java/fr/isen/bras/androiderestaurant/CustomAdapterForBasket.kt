package fr.isen.bras.androiderestaurant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.bras.androiderestaurant.model.DishBasket


class CustomAdapterForBasket(private val List: List<DishBasket>, private val cellClickListener : CellClickListener) : RecyclerView.Adapter<CustomAdapterForBasket.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dish = List[position]

        if(dish.itemdish.images[0]!="") {
            Picasso.get()
                .load(dish.itemdish.images[0])
                .error(R.drawable.pizza)
                .into(holder.itemImage)


        }
        else{
            holder.itemImage.setImageResource(R.drawable.pizza)
        }

        val totalprice=  (dish.itemdish.prices[0].price).toFloat()* dish.quantity.toFloat()


        holder.itemText.text = dish.quantity.toString() + " "+ dish.itemdish.name_fr


        holder.itemprice.text = totalprice.toString() +" €"

        holder.itembin.setOnClickListener {
            cellClickListener.onCellClickListenerBasketRemove(dish)

        }

        holder.itemplus.setOnClickListener{
            cellClickListener.onCellClickListenerBasketPlusOrMinus(dish, "plus")

        }

        holder.itemmoins.setOnClickListener{
            if(dish.quantity>1)
            cellClickListener.onCellClickListenerBasketPlusOrMinus(dish, "moins")
            else{}
        }

    }


    override fun getItemCount(): Int {
        return List.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.itemimage)
        val itemText: TextView = itemView.findViewById(R.id.previousorder)
        val itemprice: TextView=itemView.findViewById(R.id.price)
        val itembin : ImageView =itemView.findViewById(R.id.bin)
        val itemmoins : ImageView =itemView.findViewById(R.id.basketmoins)
        val itemplus : ImageView =itemView.findViewById(R.id.basketplus)
    }



}

