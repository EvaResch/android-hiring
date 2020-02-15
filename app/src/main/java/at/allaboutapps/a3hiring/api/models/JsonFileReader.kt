package at.allaboutapps.a3hiring.api.models

import android.widget.ListView
import at.allaboutapps.a3hiring.MainActivity
import at.allaboutapps.a3hiring.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import java.net.URL

open class JsonFileReader(val url: String, val activity: MainActivity) {

    var jsonFile: String = ""
    var clubList: MutableList<Club> = ArrayList()

    fun getJsonFileFromUrl() {

        doAsync{
            jsonFile = URL(url).readText()
            val jsonArray = JSONArray(jsonFile)
            clubList = MutableList(jsonArray.length() - 1){i -> Club(jsonArray.getJSONObject(i).getString("name"), jsonArray.getJSONObject(i).getString("country"), jsonArray.getJSONObject(i).getLong("value"), jsonArray.getJSONObject(i).getString("image"), jsonArray.getJSONObject(i).getInt("european_titles"))}
            clubList.sortBy {it.name}
            uiThread {
                activity.list = clubList
                val listView: ListView = activity.findViewById<ListView>(R.id.listView)
                activity.setListViewAdapter(listView, clubList)
                activity.setListClickListener(listView)
            }
        }
    }

}