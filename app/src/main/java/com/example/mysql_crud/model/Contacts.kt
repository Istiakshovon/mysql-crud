package com.example.mysql_crud.model

import com.google.gson.annotations.SerializedName

class Contacts {
    @SerializedName("id")
    private val id: String? = null

    @SerializedName("name")
    private val name: String? = null

    @SerializedName("email")
    private val email: String? = null

    @SerializedName("cell")
    private val cell: String? = null

    @SerializedName("password")
    private val password: String? = null

    @SerializedName("value")
    private val value: String? = null

    @SerializedName("message")
    private val massage: String? = null

    fun getId(): String? {
        return id
    }

    fun getName(): String? {
        return name
    }

    fun getEmail(): String? {
        return email
    }

    fun getCell(): String? {
        return cell
    }

    fun getPassword(): String? {
        return password
    }


    fun getValue(): String? {
        return value
    }

    fun getMassage(): String? {
        return massage
    }
}