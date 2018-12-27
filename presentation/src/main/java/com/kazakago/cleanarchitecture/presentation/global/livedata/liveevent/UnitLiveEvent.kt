package com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent

import androidx.annotation.MainThread

class UnitLiveEvent : LiveEvent<Unit>() {

    @MainThread
    @Deprecated(
        message = "use call()",
        replaceWith = ReplaceWith("call()"),
        level = DeprecationLevel.HIDDEN
    )
    override fun call(t: Unit?) {
        super.call(t)
    }

    @MainThread
    fun call() {
        super.call(Unit)
    }

}