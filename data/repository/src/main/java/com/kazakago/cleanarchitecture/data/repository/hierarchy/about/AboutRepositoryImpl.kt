package com.kazakago.cleanarchitecture.data.repository.hierarchy.about

import android.content.Context
import com.kazakago.cleanarchitecture.data.repository.global.dispatcher.FlowDispatcher
import com.kazakago.cleanarchitecture.data.repository.mapper.about.AppInfoEntityMapper
import com.kazakago.cleanarchitecture.data.repository.mapper.about.DeveloperInfoEntityMapper
import com.kazakago.cleanarchitecture.data.resource.hierarchy.about.DeveloperInfoDao
import com.kazakago.cleanarchitecture.data.resource.hierarchy.device.StoreLinkDao
import com.kazakago.cleanarchitecture.data.resource.hierarchy.device.VersionDao
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.global.state.mapContent
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.about.AboutRepository
import com.os.operando.guild.kt.to
import kotlinx.coroutines.flow.Flow
import java.net.URI
import java.net.URL

internal class AboutRepositoryImpl(private val context: Context) : AboutRepository {

    private val versionDao = VersionDao(context)
    private val storeLinkDao = StoreLinkDao(context)
    private val developerInfoDao = DeveloperInfoDao(context)
    private val appInfoEntityMapper = AppInfoEntityMapper()
    private val developerInfoEntityMapper = DeveloperInfoEntityMapper()

    override fun subscribeAppInfo(): Flow<State<AppInfo>> {
        return FlowDispatcher(fetchOrigin = { getAppInfo() })
            .subscribe()
            .mapContent {
                appInfoEntityMapper.map(it.first, it.second, it.third)
            }
    }

    override fun subscribeDeveloperInfo(): Flow<State<DeveloperInfo>> {
        return FlowDispatcher(fetchOrigin = { getDeveloperInfo() })
            .subscribe()
            .mapContent {
                developerInfoEntityMapper.map(it.first, it.second, it.third)
            }
    }

    private fun getAppInfo(): Triple<String, Long, URI> {
        return versionDao.getVersionName() to versionDao.getVersionCode() to storeLinkDao.getStoreAppLink()
    }

    private fun getDeveloperInfo(): Triple<String, URI, URL> {
        return developerInfoDao.getName() to developerInfoDao.getEmailAddress() to developerInfoDao.getWebSiteUrl()
    }
}
