package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import kotlinx.android.synthetic.main.activity_forecast.*
import org.koin.android.architecture.ext.getViewModel

class ForecastActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context, city: City): Intent {
            val intent = Intent(context, ForecastActivity::class.java)
            intent.putExtra(Key.City.name, city)
            return intent
        }
    }

    private enum class Key {
        City
    }

    private lateinit var viewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        val city = intent.getSerializableExtra(Key.City.name) as City
        viewModel = getViewModel { mapOf("city" to city) }

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.finish.observe(this, "", Observer {
            finish()
        })
        viewModel.title.observe(this, Observer {
            title = it
        })

        if (savedInstanceState == null) {
            replaceForecastFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> viewModel.onClickBackIcon()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceForecastFragment() {
        val fragment = ForecastFragment.createInstance()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

}
