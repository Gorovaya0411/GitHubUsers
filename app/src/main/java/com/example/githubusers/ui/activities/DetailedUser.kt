package com.example.githubusers.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubusers.R
import com.example.githubusers.data.apiService.OneUserGithubApiService
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detailed_user.*

class DetailedUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_user)
        val apiService = OneUserGithubApiService.create()
        val userLogin = intent.getStringExtra("KEY") ?: "Id Нет"
        apiService.search(userLogin)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                UserName.text = it.name
                locationUser.text = it.location
                created_at.text = it.created_at
                bioUser.text = it.bio
                following.text = it.following.toString()
                followers.text = it.followers.toString()
                Picasso.get().load(it.avatar_url).fit().into(imageView1)
            }, { error ->
                error.printStackTrace()
            })
    }
}
