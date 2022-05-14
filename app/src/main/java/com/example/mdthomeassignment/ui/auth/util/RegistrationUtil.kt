package com.example.mdthomeassignment.ui.auth.util

object RegistrationUtil {

    private val existingUser = listOf("abc","xyz")

    /**
     * Input is not valid if
     * -username, password or confirmPassword are empty
     * -password and confirmPassword are not same
     * -password is less than 2 digits like that
     * username already exist
     **/
    fun validateRegistrationInput(
        userName: String,
        password: String,
        confirmPassword: String
    ): Boolean{
        if(userName.isEmpty()|| password.isEmpty()){
            return false;
        }
        if(password!=confirmPassword){
            return false;
        }
        if(userName in existingUser){
            return false;
        }
        if(password.count(){it.isDigit()}<2){
            return false;
        }
        return true
    }
}