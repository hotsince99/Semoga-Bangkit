package com.dicoding.semogabangkit.ui.profile_pengguna

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.databinding.ActivityProfileBinding
import com.dicoding.semogabangkit.databinding.ContentProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var bindingDetail: ContentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val url = "http://semogabangkit.topanlabs.com/poto/dragon.jpeg"

        binding.apply {
            tvFullName.text = "Josua Hutapea"
            tvUsername.text = "josua_htp"
            tvLocation.text = "Surabaya"
            tvReport.text =  "20"
            tvUpvote.text = "30"
            tvWin.text = "40"

            Glide.with(this@ProfileActivity)
                    .load(url)
                    .placeholder(R.drawable.ic_placeholder_avatar)
                    .into(imgProfilePicture)
        }
    }
}