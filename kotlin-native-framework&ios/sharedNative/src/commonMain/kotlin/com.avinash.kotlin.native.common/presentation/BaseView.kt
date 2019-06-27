package com.avinash.kotlin.native.common.presentation

interface BaseView {

    fun showProgressBar()

    fun hideProgressBar()

    fun showErrorMessage(errorMessage: String?)
}