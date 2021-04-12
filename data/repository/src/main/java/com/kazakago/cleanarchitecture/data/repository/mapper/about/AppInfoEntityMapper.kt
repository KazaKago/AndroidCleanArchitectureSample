package com.kazakago.cleanarchitecture.data.repository.mapper.about

import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.VersionCode
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.VersionName
import java.net.URI

internal class AppInfoEntityMapper {

    fun map(versionName: String, versionCode: Long, storeAppLink: URI): AppInfo {
        return AppInfo(
            versionName = VersionName(versionName),
            versionCode = VersionCode(versionCode),
            playStoreUri = storeAppLink
        )
    }
}
