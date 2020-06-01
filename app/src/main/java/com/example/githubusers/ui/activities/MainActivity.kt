package com.example.githubusers.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.R
import com.example.githubusers.data.apiService.AllUsersGithubApiService
import com.example.githubusers.data.model.Users
import com.example.githubusers.ui.Adapter
import com.example.githubusers.ui.PaginationScrollListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val myAdapter =
        Adapter { openingNewActivity(it) }
    private val apiService = AllUsersGithubApiService.create()
    var isRequest: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        workWithAdapter()
        RecyclerMain.addOnScrollListener(
            PaginationScrollListener(
                { getMoreItems() },
                30
            )
        )

        apiService.search(0.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                populateData(it)
            }, { error ->
                error.printStackTrace()
            })
    }

    private fun openingNewActivity(User: Users) {
        val intent = Intent(this, DetailedUser::class.java)
        intent.putExtra("KEY", User.login)
        startActivity(intent)
    }

    private fun workWithAdapter() {
        RecyclerMain.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        RecyclerMain.adapter = myAdapter
    }

    private fun populateData(Users: List<Users>) {
        myAdapter.setData(Users)
    }

    private fun getMoreItems() {
        if (!isRequest) {
            isRequest = true
            val lastUser = myAdapter.dataGit.last().id.toString()
            apiService.search(lastUser)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    myAdapter.addData(it)
                    isRequest = false
                }, { error ->
                    error.printStackTrace()
                })
        }
    }
}



