package com.kazakago.cleanarchitecture.presentation.view.hierarchy.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.presentation.view.databinding.FragmentForecastBinding
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.forecast.ForecastViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ForecastFragment : Fragment() {

    companion object {
        fun createInstance(): ForecastFragment {
            return ForecastFragment()
        }
    }

    private val viewModel by sharedViewModel<ForecastViewModel>()
    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!
    private val forecastRecyclerAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.descriptionButton.setOnClickListener {
            showForecastDescriptionDialog()
        }
        binding.forecastRecyclerView.adapter = forecastRecyclerAdapter

        viewModel.weather.observe(viewLifecycleOwner) {
            updateWeather(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) binding.loadingProgressBar.show() else binding.loadingProgressBar.hide()
        }
        viewModel.showError.observe(viewLifecycleOwner) {
            showExceptionSnackbar(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateWeather(weather: Weather) {
        forecastRecyclerAdapter.updateAsync(
            listOf(ForecastRecyclerSummary(weather)) + weather.forecasts.map {
                ForecastRecyclerContent(it).apply {
                    onClickItem = ::showForecastSnackbar
                }
            }
        )
    }

    private fun showExceptionSnackbar(exception: Exception) {
        Snackbar.make(binding.root, exception.localizedMessage ?: "", Snackbar.LENGTH_LONG).show()
    }

    private fun showForecastSnackbar(forecast: Forecast) {
        Snackbar.make(binding.root, forecast.telop, Snackbar.LENGTH_SHORT).show()
    }

    private fun showForecastDescriptionDialog() {
        val dialog = ForecastDescriptionDialog.createInstance()
        dialog.show(requireFragmentManager(), "")
    }

}
