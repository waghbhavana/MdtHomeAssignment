package com.example.mdthomeassignment.ui.auth.util

object LoginUtil {

    /**
     * Input is not valid if
     * -username, password or confirmPassword are empty
     **/
    fun validateLoginInput(
        userName: String,
        password: String
    ): Boolean{
        if(userName.isEmpty()|| password.isEmpty()){
            return false;
        }
        return true
    }
}