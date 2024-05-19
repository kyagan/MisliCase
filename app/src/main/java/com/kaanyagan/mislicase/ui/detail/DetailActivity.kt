package com.kaanyagan.mislicase.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.kaanyagan.mislicase.data.api.model.Data
import com.kaanyagan.mislicase.databinding.ActivityDetailBinding

// Activity for displaying information about a match
class DetailActivity : AppCompatActivity() {

    // View binding instance for accessing layout views
    private lateinit var binding: ActivityDetailBinding
    // The received match data from the intent
    private var receivedMatch:Data? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieves the match data from the intent
        receivedMatch = intent.getParcelableExtra("match_detail")

        // Fill the UI with the received match data
        // ivFlag means league flag
        // tvHtName means Home Team name
        // tvAtName means Away Team name
        // tvScore means match score
        receivedMatch.let {
            binding.ivFlag.load(receivedMatch!!.to.flag)
            binding.tvLeagueName.text = receivedMatch!!.to.n
            binding.tvHtName.text = receivedMatch!!.ht.sn
            binding.tvAtName.text = receivedMatch!!.at.sn
            binding.tvScore.text = receivedMatch!!.sc.ht.r.toString() + " - " + receivedMatch!!.sc.at.r.toString()
        }
    }
}