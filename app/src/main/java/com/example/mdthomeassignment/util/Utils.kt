package com.example.mdthomeassignment.util

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Adapter
import androidx.fragment.app.Fragment
import com.example.mdthomeassignment.data.network.Resource
import com.example.mdthomeassignment.ui.auth.LoginFragment
import com.google.android.material.snackbar.Snackbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun <A: Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this,activity).also{
        it.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.enable(enabled:Boolean){
    isEnabled=enabled
    alpha = if (enabled) 1f else 0.5f
}
fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry:(()->Unit)?=null){
    when{
        failure.isNetworkError -> requireView().snackbar("Please check your internet connection",retry)
        failure.errorCode == 401 -> {
            if (this is LoginFragment){
                requireView().snackbar("You've entered incorrect email address and password")
            }else{
                //logout
            }

        }else ->{
            val error= failure.errorBody?.string()
        if (error != null) {
            requireView().snackbar(error)
        }
        }
    }

}
fun View.snackbar(message: String, action:(()->Unit)?=null){
    val snackbar= Snackbar.make(this,message,Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry"){
            it()
        }
    }
    snackbar.show()
}

fun convertDate(dateString:String):String {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val formatterOut = SimpleDateFormat("dd MMM yyyy")


        val date: Date = formatter.parse(dateString) as Date
        println(date)
        println(formatterOut.format(date))
        return formatterOut.format(date)

}