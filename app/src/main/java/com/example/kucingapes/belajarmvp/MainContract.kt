package com.example.kucingapes.belajarmvp

import com.androidnetworking.error.ANError
import com.example.kucingapes.belajarmvp.model.User

class MainContract {
    interface Presenter {
        fun onDestroy()
        fun requestApi()
        fun onRefresh()
    }

    interface MainView {
        fun showProgress()
        fun hideProgress()
        fun dataToRecyclerView(list: ArrayList<User>)
        fun onResponFailure(anError: ANError)
    }

    interface GetDataApi {
        interface OnCompleteListener {
            fun onComplete(list: ArrayList<User>)
            fun onFailure(anError: ANError)
        }

        fun getDataList(onCompleteListener: OnCompleteListener)
    }
}