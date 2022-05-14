package com.example.mdthomeassignment.ui.auth.util

import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Test

class LoginUtilTest{

    @Test
    fun emptyUserReturnsFalse() {

        val result = LoginUtil.validateLoginInput(
            "", "123"
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun emptyPasswordsReturnsFalse() {

        val result =  LoginUtil.validateLoginInput(
            "jack", ""
        )
        Truth.assertThat(result).isFalse()
    }
}