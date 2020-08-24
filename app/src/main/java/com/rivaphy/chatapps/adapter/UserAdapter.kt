package com.rivaphy.chatapps.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rivaphy.chatapps.R
import com.rivaphy.chatapps.model.Users
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_search.view.*

class UserAdapter(mContext : Context, mUser: List<Users>, isChatcheck: Boolean) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        var username : TextView
        var profile : CircleImageView
        var online : CircleImageView
        var offline : CircleImageView

        init {
            username = itemView.findViewById(R.id.tv_user_search)
            profile = itemView.findViewById(R.id.iv_profile_search)
            online = itemView.findViewById(R.id.iv_online_search)
            offline = itemView.findViewById(R.id.iv_offline_search)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}