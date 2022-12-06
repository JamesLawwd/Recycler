package com.example.recyclerproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide import com.bumptech.glide.request.RequestOptions

class RecyclerAdapter(var context: Context):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    var productList : List<ItemViewModel> = listOf() // empty  list

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
    //Note below code returns above class and pass the view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false)
        return ViewHolder(view)
    }
    //so far item view is same as single item
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val productName= holder.itemView.findViewById(R.id.productName) as TextView
        val productCost = holder.itemView.findViewById(R.id.productCost) as TextView
        val productDesc = holder.itemView.findViewById(R.id.productDesc) as TextView
        val image = holder.itemView.findViewById(R.id.imageView) as ImageView

        //bind
        val item = productList[position]
        productName.text=item.productName
        productCost.text = item.productCost
        productDesc.text = item.productDesc

        //
        Glide.with(context).load(item.image)
            .apply(RequestOptions().centerCrop())
            .into(image)



        //        holder.itemView.setOnClickListener {
        //
        //
        //
        //        }
    }

    override fun getItemCount(): Int { //count the number items coming from the API
        return productList.size
    }
    //we will call this function on Loopj response
    fun setProductListItems(productList: List<ItemViewModel>){
        this.productList = productList
        notifyDataSetChanged()
    }

}








//View holder holds the views in single item.xml

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class CustomAdapter(private val mList: List<ItemViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
//
//    // create new views
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        // inflates the card_view_design view
//        // that is used to hold list item
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.single_item, parent, false)
//
//        return ViewHolder(view)
//    }
//
//    // binds the list items to a view
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        val ItemViewModel = mList[position]
//
//        // sets the image to the imageview from our itemHolder class
//        holder.productImage.setImageResource(ItemViewModel.image)
//
//        // sets the text to the textview from our itemHolder class
//        holder.productName.text = ItemViewModel.text
//        holder.productCost.text = ItemViewModel.text
//        holder.productDesc.text = ItemViewModel.text
//
//    }
//
//    // return the number of the items in the list
//    override fun getItemCount(): Int {
//        return mList.size
//    }
//
//    // Holds the views for adding it to image and text
//    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
//        val productImage: ImageView = itemView.findViewById(R.id.imageView)
//        val productName: TextView = itemView.findViewById(R.id.productName)
//        val productCost: TextView = itemView.findViewById(R.id.productCost)
//        val productDesc: TextView = itemView.findViewById(R.id.productDesc)
//
//    }
//}


// contains 3 items used in recycler views
// onCreateView/holder(): sets the vies to display the items
// onBindViewHolder() : bind the list items to our views e.g ImageView
// getItemCount() -> returns the number of items present in a list
