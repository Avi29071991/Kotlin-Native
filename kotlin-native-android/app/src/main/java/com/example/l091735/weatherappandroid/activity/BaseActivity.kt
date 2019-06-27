package com.example.l091735.weatherappandroid.activity

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.Nullable
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

abstract class BaseActivity: AppCompatActivity() {

    @LayoutRes
    protected abstract fun getLayoutResource(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutResId = getLayoutResource()
        if (layoutResId != 0) {
            setContentView(layoutResId)
        }
    }

    override fun setSupportActionBar(@Nullable toolbar: Toolbar?) {
        super.setSupportActionBar(toolbar)
        configureActionBar()
    }

    override fun setTitle(title: CharSequence) {
        super.setTitle(title)
        val actionBar = supportActionBar
        actionBar?.title = title
    }

    @VisibleForTesting
    protected fun configureActionBar() {
        val actionBar = supportActionBar
        actionBar?.elevation = 0F
    }
}