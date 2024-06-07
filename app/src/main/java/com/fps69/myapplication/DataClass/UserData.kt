package com.fps69.myapplication.DataClass

import android.provider.ContactsContract.CommonDataKinds.Email

data class UserData(
    val name:String?=null,
    val email: String?=null,
    val password:String?=null
)
