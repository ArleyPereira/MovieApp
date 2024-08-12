package br.com.hellodev.movieapp.presenter.main.bottombar.profile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.hellodev.movieapp.R
import br.com.hellodev.movieapp.databinding.ItemUserProfileBinding
import br.com.hellodev.movieapp.domain.model.menu.MenuProfile
import br.com.hellodev.movieapp.domain.model.menu.MenuProfileType

class ProfileMenuAdapter(
    private val items: List<MenuProfile>,
    private val context: Context,
    private val onClick: (MenuProfileType) -> Unit
) : RecyclerView.Adapter<ProfileMenuAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemUserProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]

        holder.binding.textItemProfile.apply {
            text = context.getString(item.text)
            if (item.type == MenuProfileType.LOGOUT) {
                setTextColor(ContextCompat.getColor(context, R.color.color_default))
            }
        }
        holder.binding.imageItemProfile.setImageDrawable(
            ContextCompat.getDrawable(context, item.icon)
        )
        holder.itemView.setOnClickListener {
            onClick(item.type)
        }
    }

    inner class MyViewHolder(val binding: ItemUserProfileBinding) :
        RecyclerView.ViewHolder(binding.root)

}