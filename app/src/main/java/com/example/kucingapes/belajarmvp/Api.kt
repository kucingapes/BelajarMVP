package com.example.kucingapes.belajarmvp

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.kucingapes.belajarmvp.model.Data

class Api(private val page: String) : MainContract.GetDataApi {
    val BASEURL = "https://reqres.in/api/users?page={paging}"

    override fun getDataList(onCompleteListener: MainContract.GetDataApi.OnCompleteListener) {
        AndroidNetworking.get(BASEURL)
                .addPathParameter("paging", page)
                .setPriority(Priority.MEDIUM)
                .setTag(this)
                .build()
                .getAsObject(Data::class.java, object : ParsedRequestListener<Data> {
                    override fun onResponse(response: Data?) {
                        onCompleteListener.onComplete(response!!.data)
                    }

                    override fun onError(anError: ANError?) {
                        onCompleteListener.onFailure(anError!!)
                    }

                })
    }
}