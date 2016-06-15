package com.weathercock.android_cleanarchitecture.domain.usecase;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.weathercock.android_cleanarchitecture.CleanApplication;
import com.weathercock.android_cleanarchitecture.di.component.DaggerTestApplicationComponent;
import com.weathercock.android_cleanarchitecture.di.module.ApplicationModule;
import com.weathercock.android_cleanarchitecture.di.module.DataModule;
import com.weathercock.android_cleanarchitecture.di.module.DomainModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * AboutUseCase Test
 * <p>
 * Created by tamura_k on 2016/06/14.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class AboutUseCaseTest {

    @Inject
    public AboutUseCase aboutUseCase;

    @Before
    public void setUp() throws Exception {
        DaggerTestApplicationComponent.builder()
                .applicationModule(new ApplicationModule(CleanApplication.getInstance(InstrumentationRegistry.getTargetContext())))
                .domainModule(new DomainModule())
                .dataModule(new DataModule())
                .build()
                .inject(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetPlayStoreUrl() throws Exception {
        assertThat(aboutUseCase.getPlayStoreUrl(), notNullValue());
    }

    @Test
    public void testGetMailUrl() throws Exception {
        assertThat(aboutUseCase.getMailUrl(), notNullValue());
    }

    @Test
    public void testGetWebSiteUrl() throws Exception {
        assertThat(aboutUseCase.getWebSiteUrl(), notNullValue());
    }

    @Test
    public void testGetCurrentVersion() throws Exception {
        assertThat(aboutUseCase.getCurrentVersion(), notNullValue());
    }

    @Test
    public void testGetCurrentYear() throws Exception {
        assertThat(aboutUseCase.getCurrentYear(), notNullValue());
    }

}