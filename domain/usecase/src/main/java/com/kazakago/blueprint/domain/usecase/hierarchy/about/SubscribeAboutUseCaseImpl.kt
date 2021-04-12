package com.kazakago.blueprint.domain.usecase.hierarchy.about

import com.kazakago.blueprint.domain.model.global.state.State
import com.kazakago.blueprint.domain.model.global.state.combineState
import com.kazakago.blueprint.domain.repository.hierarchy.about.AboutRepository
import com.kazakago.blueprint.domain.usecase.output.about.AboutOutput
import kotlinx.coroutines.flow.Flow

internal class SubscribeAboutUseCaseImpl(private val aboutRepository: AboutRepository) : SubscribeAboutUseCase {

    override fun invoke(): Flow<State<AboutOutput>> {
        val appInfoFlow = aboutRepository.subscribeAppInfo()
        val developerInfoFlow = aboutRepository.subscribeDeveloperInfo()
        return appInfoFlow.combineState(developerInfoFlow) { appInfo, developerInfo ->
            AboutOutput(appInfo, developerInfo)
        }
    }
}
