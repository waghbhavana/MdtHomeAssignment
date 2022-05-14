package com.example.mdthomeassignment.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.mdthomeassignment.R


class LoginFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_login, container, false)
        val registerButton: Button= view.findViewById(R.id.buttonRegister)
        registerButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
               val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.fragmentContainerView,RegisterFragment())
                 transaction?.commit()
            }})
        return view
    }


}