package com.kazakago.cleanarchitecture

import android.support.multidex.MultiDexApplication
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.android.autoAndroidModule
import com.github.salomonbrys.kodein.lazy
import com.kazakago.cleanarchitecture.di.applicationModule
import com.kazakago.cleanarchitecture.di.dataModule
import com.kazakago.cleanarchitecture.di.domainModule
import com.kazakago.cleanarchitecture.di.webModule

class CleanApplication : MultiDexApplication(), KodeinAware {

    override val kodein: Kodein by Kodein.lazy {
        import(autoAndroidModule(this@CleanApplication))
        import(applicationModule(this@CleanApplication))
        import(domainModule())
        import(dataModule())
        import(webModule())
    }

}
