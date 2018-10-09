package com.example.kucingapes.belajarmvp

import com.androidnetworking.error.ANError
import com.example.kucingapes.belajarmvp.model.User

class MainPresenter(private val mainView: MainContract.MainView,
                    private val getDataApi: MainContract.GetDataApi) : MainContract.Presenter, MainContract.GetDataApi.OnCompleteListener {
    override fun onRefresh() {
        getDataApi.getDataList(this)
    }

    override fun onDestroy() {
       // mainView = null
    }

    override fun requestApi() {
        getDataApi.getDataList(this)
    }

    override fun onComplete(list: ArrayList<User>) {
        mainView.dataToRecyclerView(list)
        mainView.hideProgress()
    }

    override fun onFailure(anError: ANError) {
        mainView.onResponFailure(anError)
        mainView.hideProgress()
    }

}