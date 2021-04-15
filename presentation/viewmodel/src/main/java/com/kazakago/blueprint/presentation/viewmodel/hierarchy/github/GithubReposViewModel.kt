package com.kazakago.blueprint.presentation.viewmodel.hierarchy.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.blueprint.domain.usecase.hierarchy.github.FollowGithubReposUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.github.RefreshGithubReposUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.github.RequestAdditionalGithubReposUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GithubReposViewModel @AssistedInject constructor(
    private val followGithubReposUseCase: FollowGithubReposUseCase,
    private val refreshGithubReposUseCase: RefreshGithubReposUseCase,
    private val requestAdditionalGithubReposUseCase: RequestAdditionalGithubReposUseCase,
    @Assisted private val githubOrgName: GithubOrgName,
) : ViewModel() {

    companion object {
        fun provideFactory(assistedFactory: Factory, githubOrgName: GithubOrgName): ViewModelProvider.Factory = object : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(githubOrgName) as T
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(githubOrgName: GithubOrgName): GithubReposViewModel
    }

    private val _githubRepos = MutableStateFlow<List<GithubRepo>>(emptyList())
    val githubRepos get() = _githubRepos.asStateFlow()
    private val _isMainLoading = MutableStateFlow(false)
    val isMainLoading get() = _isMainLoading.asStateFlow()
    private val _isAdditionalLoading = MutableStateFlow(false)
    val isAdditionalLoading get() = _isAdditionalLoading.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing get() = _isRefreshing.asStateFlow()
    private val _mainError = MutableStateFlow<Exception?>(null)
    val mainError get() = _mainError.asStateFlow()
    private val _additionalError = MutableStateFlow<Exception?>(null)
    val additionalError = _additionalError.asStateFlow()

    init {
        viewModelScope.launch { followGithubRepos() }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            refreshGithubReposUseCase(githubOrgName)
            _isRefreshing.value = false
        }
    }

    fun retry() {
        viewModelScope.launch {
            refreshGithubReposUseCase(githubOrgName)
        }
    }

    fun requestAdditional() {
        viewModelScope.launch {
            requestAdditionalGithubReposUseCase(githubOrgName, continueWhenError = false)
        }
    }

    fun retryAdditional() {
        viewModelScope.launch {
            requestAdditionalGithubReposUseCase(githubOrgName, continueWhenError = true)
        }
    }

    private suspend fun followGithubRepos() {
        followGithubReposUseCase(githubOrgName).collect {
            it.doAction(
                onFixed = {
                    it.content.doAction(
                        onExist = { githubRepos ->
                            _githubRepos.value = githubRepos
                            _isMainLoading.value = false
                            _isAdditionalLoading.value = false
                            _mainError.value = null
                            _additionalError.value = null
                        },
                        onNotExist = {
                            _githubRepos.value = emptyList()
                            _isMainLoading.value = true
                            _isAdditionalLoading.value = false
                            _mainError.value = null
                            _additionalError.value = null
                        }
                    )
                },
                onLoading = {
                    it.content.doAction(
                        onExist = { githubRepos ->
                            _githubRepos.value = githubRepos
                            _isMainLoading.value = false
                            _isAdditionalLoading.value = true
                            _mainError.value = null
                            _additionalError.value = null
                        },
                        onNotExist = {
                            _githubRepos.value = emptyList()
                            _isMainLoading.value = true
                            _isAdditionalLoading.value = false
                            _mainError.value = null
                            _additionalError.value = null
                        }
                    )
                },
                onError = { exception ->
                    it.content.doAction(
                        onExist = { githubRepos ->
                            _githubRepos.value = githubRepos
                            _isMainLoading.value = false
                            _isAdditionalLoading.value = false
                            _mainError.value = null
                            _additionalError.value = exception
                        },
                        onNotExist = {
                            _githubRepos.value = emptyList()
                            _isMainLoading.value = false
                            _isAdditionalLoading.value = false
                            _mainError.value = exception
                            _additionalError.value = null
                        }
                    )
                }
            )
        }
    }
}
