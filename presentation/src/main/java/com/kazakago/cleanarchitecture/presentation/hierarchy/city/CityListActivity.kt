package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.hierarchy.about.AboutActivity
import kotlinx.android.synthetic.main.activity_forecast.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityListActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, CityListActivity::class.java)
        }
    }

    private val viewModel by viewModel<CityListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)

        setSupportActionBar(toolbar)

        viewModel.goAbout.observe(this, "", Observer {
            goAboutActivity()
        })

        if (savedInstanceState == null) {
            replaceCityListFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.city_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> viewModel.onClickAbout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceCityListFragment() {
        val fragment = CityListFragment.createInstance()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun goAboutActivity() {
        val intent = AboutActivity.createIntent(this)
        startActivity(intent)
    }

}