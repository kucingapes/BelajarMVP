package com.example.kucingapes.belajarmvp

import com.example.kucingapes.belajarmvp.model.User

interface RecyclerItemClick {
    fun onItemClick(user: User)
}