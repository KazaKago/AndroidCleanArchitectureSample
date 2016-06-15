package com.weathercock.cleanarchitecture.domain.usecase;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.weathercock.cleanarchitecture.CleanApplication;
import com.weathercock.cleanarchitecture.di.component.DaggerTestApplicationComponent;
import com.weathercock.cleanarchitecture.di.module.ApplicationModule;
import com.weathercock.cleanarchitecture.di.module.DataModule;
import com.weathercock.cleanarchitecture.di.module.DomainModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * WeatherUseCase Test
 * <p>
 * Created by tamura_k on 2016/06/15.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class WeatherUseCaseTest {

    @Inject
    public WeatherUseCase weatherUseCase;

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
    public void testFetch() throws Exception {
        assertThat(weatherUseCase.fetch("400040").toBlocking().single(), notNullValue());
    }

    @Test
    public void testFind() throws Exception {
        assertThat(weatherUseCase.find("400040"), notNullValue());
    }

}