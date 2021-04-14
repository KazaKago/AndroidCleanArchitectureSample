package com.kazakago.blueprint.presentation.view.hierarchy.github

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.databinding.ActivityGithubOrgsBinding
import com.kazakago.blueprint.presentation.view.global.view.ErrorItem
import com.kazakago.blueprint.presentation.view.global.view.LoadingItem
import com.kazakago.blueprint.presentation.view.global.view.addOnBottomReached
import com.kazakago.blueprint.presentation.view.hierarchy.about.AboutActivity
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.github.GithubOrgsViewModel
import com.xwray.groupie.Group
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubOrgsActivity : AppCompatActivity() {

    private val viewBinding by lazy { ActivityGithubOrgsBinding.inflate(layoutInflater) }
    private val githubOrgsAdapter = GroupieAdapter()
    private val githubReposActivityLauncher = registerForActivityResult(GithubReposActivity.Contract()) {}
    private val aboutActivityLauncher = registerForActivityResult(AboutActivity.Contract()) {}
    private val githubOrgsViewModel by viewModel<GithubOrgsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)

        viewBinding.githubOrgsRecyclerView.adapter = githubOrgsAdapter
        viewBinding.githubOrgsRecyclerView.addOnBottomReached {
            githubOrgsViewModel.requestAddition()
        }
        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            githubOrgsViewModel.refresh()
        }
        viewBinding.retryButton.setOnClickListener {
            githubOrgsViewModel.retry()
        }

        lifecycleScope.launchWhenStarted {
            combine(githubOrgsViewModel.githubOrgs, githubOrgsViewModel.isAdditionalLoading, githubOrgsViewModel.additionalError) { a, b, c -> Triple(a, b, c) }.collect {
                val items: List<Group> = mutableListOf<Group>().apply {
                    this += createGithubOrgItems(it.first)
                    if (it.second) this += createLoadingItem()
                    if (it.third != null) this += createErrorItem(it.third!!)
                }
                githubOrgsAdapter.updateAsync(items)
            }
        }
        lifecycleScope.launchWhenStarted {
            githubOrgsViewModel.isMainLoading.collect {
                viewBinding.progressBar.isVisible = it
            }
        }
        lifecycleScope.launchWhenStarted {
            githubOrgsViewModel.mainError.collect {
                viewBinding.errorGroup.isVisible = (it != null)
                viewBinding.errorTextView.text = it?.toString()
            }
        }
        lifecycleScope.launchWhenStarted {
            githubOrgsViewModel.isRefreshing.collect {
                viewBinding.swipeRefreshLayout.isRefreshing = it
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                goAbout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createGithubOrgItems(githubOrgs: List<GithubOrg>): List<GithubOrgItem> {
        return githubOrgs.map { githubOrg ->
            GithubOrgItem(githubOrg).apply {
                onClick = { goGithubRepos(it.name) }
            }
        }
    }

    private fun createLoadingItem(): LoadingItem {
        return LoadingItem()
    }

    private fun createErrorItem(exception: Exception): ErrorItem {
        return ErrorItem(exception).apply {
            onRetry = { githubOrgsViewModel.retryAddition() }
        }
    }

    private fun goGithubRepos(githubOrgName: GithubOrgName) {
        githubReposActivityLauncher.launch(githubOrgName)
    }

    private fun goAbout() {
        aboutActivityLauncher.launch(Unit)
    }
}
