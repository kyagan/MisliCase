package com.kaanyagan.mislicase.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kaanyagan.mislicase.R
import com.kaanyagan.mislicase.data.api.model.Data
import com.kaanyagan.mislicase.data.api.model.Response
import com.kaanyagan.mislicase.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    var receivedMatch:Data? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receivedMatch = intent.getParcelableExtra("match_detail")

        receivedMatch.let {
            binding.tvHtName.text = receivedMatch!!.ht.sn
            binding.tvAtName.text = receivedMatch!!.at.sn
            binding.tvScore.text = receivedMatch!!.sc.ht.r.toString() + " - " + receivedMatch!!.sc.at.r.toString()
        }


    }
}