package com.nikhil.shaadi_demo_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.shaadi_demo_app.MainActivity
import com.nikhil.shaadi_demo_app.R
import com.nikhil.shaadi_demo_app.StatusEnum
import com.nikhil.shaadi_demo_app.db.MainDatabase
import com.nikhil.shaadi_demo_app.load
import com.nikhil.shaadi_demo_app.model.Profiles
import kotlinx.android.synthetic.main.item_carousel.view.*

class CarouselAdapter(
    var context: Context,
    private val carouselList: List<Profiles.Result>
) : RecyclerView.Adapter<CarouselAdapter.CarouselHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarouselHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return CarouselHolder(v)
    }

    override fun getItemCount(): Int {
        return carouselList.size
    }

    override fun onBindViewHolder(holder: CarouselHolder, position: Int) {
        val carousel = carouselList[position]
        val name= "${carousel.name?.first} ${carousel.name?.last}"
        holder.itemView.tvName.text = name
        holder.itemView.tvAge.text = carousel.dob?.age.toString()
        val address= "${carousel.location.city} ${carousel.location.country}"
        holder.itemView.tvAddress.text=address
        holder.itemView.ivProfile.load(carousel.picture?.large?:"")
        carousel.status.let {
            when(it){
                StatusEnum.ACCEPTED.type->{
                    holder.itemView.rlFeedback.visibility=View.INVISIBLE
                    holder.itemView.rlTextView.visibility=View.VISIBLE
                    holder.itemView.tvStatus.text="Member Accepted"

                }
                StatusEnum.REJECTED.type->{
                    holder.itemView.rlFeedback.visibility=View.INVISIBLE
                    holder.itemView.rlTextView.visibility=View.VISIBLE
                    holder.itemView.tvStatus.text="Member Rejected"
                }
                StatusEnum.IDLE.type->{
                    holder.itemView.rlTextView.visibility=View.INVISIBLE
                    holder.itemView.rlFeedback.visibility=View.VISIBLE
                }
            }
        }
        holder.itemView.btnAccept.setOnClickListener{
            (context as MainActivity).updateStatus(StatusEnum.ACCEPTED.type,carousel.phone!!)
        }
        holder.itemView.btnReject.setOnClickListener{
            (context as MainActivity).updateStatus(StatusEnum.REJECTED.type,carousel.phone!!)
        }
    }


    inner class CarouselHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {

        }
    }
}


