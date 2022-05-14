package com.example.mdthomeassignment.ui.auth.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{

    @Test
    fun emptyUsernameReturnsFalse() {

        val result = RegistrationUtil.validateRegistrationInput(
            "", "123", "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun validUsernameCorrectPasswordsReturnsTrue() {

        val result = RegistrationUtil.validateRegistrationInput(
            "jack", "123", "123"
        )
        assertThat(result).isTrue()
    }
    @Test
    fun userNameAlreadyExistReturnsFalse() {

        val result = RegistrationUtil.validateRegistrationInput(
            "abc", "123", "123"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun confirmPasswordAndPasswordNotSameReturnsFalse() {

        val result = RegistrationUtil.validateRegistrationInput(
            "abc", "123vcv", "456vbv"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun passwordLessThanTwoDigitReturnsFalse() {

        val result = RegistrationUtil.validateRegistrationInput(
            "abc", "1anncmn", "sxscd2"
        )
        assertThat(result).isFalse()
    }
}