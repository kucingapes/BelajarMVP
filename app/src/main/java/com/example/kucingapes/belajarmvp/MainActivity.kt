package com.example.kucingapes.belajarmvp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.example.kucingapes.belajarmvp.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private lateinit var presenter: MainContract.Presenter
    private val recyclerItemClick = object : RecyclerItemClick {
        override fun onItemClick(user: User) {
            Toast.makeText(this@MainActivity, "${user.first_name} ${user.last_name}", Toast.LENGTH_SHORT).show()
        }
    }

    private var paging: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidNetworking.initialize(this)

        val linearLayout = LinearLayoutManager(this)
        recycler_view.layoutManager = linearLayout

        presenter = MainPresenter(this, Api(paging.toString()))
        presenter.requestApi()

        /*next.setOnClickListener {
            if (paging < 5) {
                presenter = MainPresenter(this, Api((paging++).toString()))
                presenter.onRefresh()
            }
        }*/
        next.visibility = View.GONE

    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun dataToRecyclerView(list: ArrayList<User>) {
        val adapter = UserAdapter(list, recyclerItemClick)
        recycler_view.adapter = adapter
    }

    override fun onResponFailure(anError: ANError) {
        Toast.makeText(this, anError.message.toString(), Toast.LENGTH_SHORT).show()
        Log.d("anjir", anError.message)
    }
}
