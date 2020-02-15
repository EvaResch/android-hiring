package at.allaboutapps.a3hiring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import at.allaboutapps.a3hiring.api.models.Club
import com.bumptech.glide.Glide
import org.jetbrains.anko.contentView

class ClubDetailActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_club_detail)

        val club: Club = intent.getParcelableExtra<Club>("club")
        this.title = club.name

        val infotext: String = this.resources.getString(R.string.infotext_1) + "  <b>" + club.name + "</b> " + this.resources.getString(R.string.infotext_2) + " " + club.country + " " + this.resources.getString(R.string.infotext_3) + " " + club.value + " " + this.resources.getString(R.string.value_entity) + " " + this.resources.getString(R.string.infotext_4) + "."
        this.contentView!!.findViewById<TextView>(R.id.club_info_text).text = HtmlCompat.fromHtml(infotext, HtmlCompat.FROM_HTML_MODE_LEGACY)
        this.contentView!!.findViewById<TextView>(R.id.club_country).text = club.country
        val infotext2: String = club.name + " " + this.resources.getString(R.string.infotext_5) + " " + club.european_titles + " " + this.resources.getString(R.string.infotext_6)
        this.contentView!!.findViewById<TextView>(R.id.club_titles).text = infotext2

        Glide.with(this)
                .load(club.image)
                .into(this.contentView!!.findViewById<ImageView>(R.id.club_image))

    }
}
