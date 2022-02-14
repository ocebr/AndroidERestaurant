package fr.isen.bras.androiderestaurant

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.bras.androiderestaurant.model.OrderResults



class CustomAdapterForPreviousOrders(private val List: List <OrderResults>) : RecyclerView.Adapter<CustomAdapterForPreviousOrders.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_orders, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val orders = List[position]
        var orderPrice = 0F
        Log.d("njre","$orders")
        orders.messageList.forEach { orderPrice+= it.quantity.toFloat() *it.itemdish.prices[0].price.toFloat()}

        holder.price.setText(orderPrice.toString()+" €")
        holder.title.setText(orders.receiver)

        orders.messageList.forEach { holder.detail.append(it.itemdish.name_fr + " :  " + it.quantity +" \n") }

    }


    override fun getItemCount(): Int {
        return List.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val title: TextView = itemView.findViewById(R.id.previousorder)
        val price: TextView=itemView.findViewById(R.id.price)
        val detail: TextView= itemView.findViewById(R.id.previousorderdetail)


    }



}



