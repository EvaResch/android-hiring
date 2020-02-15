package at.allaboutapps.a3hiring

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import at.allaboutapps.a3hiring.api.models.Club
import at.allaboutapps.a3hiring.api.models.ClubListAdapter
import at.allaboutapps.a3hiring.api.models.JsonFileReader
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.custom.async

class MainActivity : AppCompatActivity() {

    var list: MutableList<Club> = ArrayList()
    val reader = JsonFileReader("https://public.allaboutapps.at/hiring/clubs.json", this)
    var sortedAscending: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById<View>(R.id.toolbar) as Toolbar)

        reader.getJsonFileFromUrl()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.menuSort) {
            if (sortedAscending) {
                list.sortByDescending { it.value }
                sortedAscending = false
                setListViewAdapter(listView, list)
            } else {
                list.sortBy { it.name }
                sortedAscending = true
                setListViewAdapter(listView, list)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun setListViewAdapter(listView: ListView, list: MutableList<Club>) {
        listView.adapter = ClubListAdapter(this, list)
    }

    fun setListClickListener(listView: ListView) {
        listView.setOnItemClickListener { parent, view, position, id ->
            val club: Club = listView.adapter.getItem(position) as Club
            val intent = Intent(this, ClubDetailActivity::class.java)
            intent.putExtra("club", club)
            startActivity(intent)
        }
    }
}
