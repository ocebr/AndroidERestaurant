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
import fr.isen.bras.androiderestaurant.model.Order
import fr.isen.bras.androiderestaurant.model.PreviousOrdersResult


class CustomAdapterForPreviousOrders(private val List: List <Order>) : RecyclerView.Adapter<CustomAdapterForPreviousOrders.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_orders, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val orders = List[position]
        var orderPrice :Float = 0F
        orders.message.forEach { orderPrice+= it.quantity.toFloat() *it.itemdish.prices[0].price.toFloat()}

        holder.price.setText(orderPrice.toString())



    }


    override fun getItemCount(): Int {
        return List.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val title: TextView = itemView.findViewById(R.id.previousorder)
        val price: TextView=itemView.findViewById(R.id.price)


    }



}



