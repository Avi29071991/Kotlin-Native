package com.example.l091735.weatherappandroid.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.avinash.kotlin.native.common.networkModel.DailyData
import com.avinash.kotlin.native.common.presentation.view.WeatherView
import com.example.l091735.weatherappandroid.R
import com.example.l091735.weatherappandroid.adapter.WeatherAdapter
import com.example.l091735.weatherappandroid.dependencies.dependencies
import kotlinx.android.synthetic.main.activity_weather_main.*
import kotlinx.android.synthetic.main.content_weather_main.*
import sayHelloWorld

class MainActivity : BaseActivity(), WeatherView, WeatherAdapter.OnItemClickListener {

    private val presenter by lazy { dependencies().weatherPresenter }
    private val adapter by lazy { WeatherAdapter(this, layoutInflater, dependencies().dataConverter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRecyclerView()
        setupToolBar()
        presenter.bind(this)
        presenter.loadDailyData(lat= -33.8705036, lng= 151.1947947)
        println(sayHelloWorld())
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_weather_main
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    override fun hideProgressBar() {
        loadingBar.visibility = View.GONE
    }

    override fun showErrorMessage(errorMessage: String?) {
        msgText.text = errorMessage
        msgText.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun showProgressBar() {
        loadingBar.visibility = View.VISIBLE
        msgText.visibility = View.GONE
        recyclerView.visibility = View.GONE
    }

    override fun showWeatherData(dataList: List<DailyData>) {
        adapter.setData(dataList)
        recyclerView.visibility = View.VISIBLE
        msgText.visibility = View.GONE
    }

    override fun onRowClicked(dailyItemData: DailyData?) {
        // TODO need this function to implement click listener
    }

    private fun setRecyclerView() {
        recyclerView?.let {
            it.layoutManager = LinearLayoutManager(this)
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }

    private fun setupToolBar() {
        toolbar?.title = resources.getString(R.string.demo_name)
        toolbar_layout?.title = resources.getString(R.string.empty)
        setSupportActionBar(toolbar)
    }
}

