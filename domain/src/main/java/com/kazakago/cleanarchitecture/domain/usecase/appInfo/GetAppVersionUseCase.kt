package com.kazakago.cleanarchitecture.domain.usecase.appInfo

import com.kazakago.cleanarchitecture.domain.usecase.UseCase

interface GetAppVersionUseCase : UseCase<Unit, String>