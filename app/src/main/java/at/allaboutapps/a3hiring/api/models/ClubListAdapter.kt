package at.allaboutapps.a3hiring.api.models

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import at.allaboutapps.a3hiring.R
import com.bumptech.glide.Glide

open class ClubListAdapter(val activity: Activity, val clubList: List<Club>): BaseAdapter() {

    private val inflater: LayoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
       return clubList.size
    }

    override fun getItem(position: Int): Club {
        return clubList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var club: Club = clubList.get(position)
        val list_item = inflater.inflate(R.layout.list_item, parent, false)
        list_item.findViewById<TextView>(R.id.clubName).text = club.name
        list_item.findViewById<TextView>(R.id.clubCountry).text = club.country
        list_item.findViewById<TextView>(R.id.clubValue).text = club.value.toString().plus(" ${activity.resources.getString(R.string.value_entity)}")

        Glide.with(activity)
                .load(club.image)
                .into(list_item.findViewById<ImageView>(R.id.imageViewListItem))

        list_item.tag = position
        return list_item
    }
}
