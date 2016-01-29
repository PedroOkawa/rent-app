package com.okawa.pedro.rentapp.common;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.RentApp;
import com.okawa.pedro.rentapp.database.AdTypeRepository;
import com.okawa.pedro.rentapp.di.component.DaggerMainComponent;
import com.okawa.pedro.rentapp.di.component.MainComponent;
import com.okawa.pedro.rentapp.di.component.RentAppComponent;
import com.okawa.pedro.rentapp.di.component.TestRentAppComponent;
import com.okawa.pedro.rentapp.di.module.MainModule;
import com.okawa.pedro.rentapp.ui.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

import static android.os.SystemClock.sleep;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by pokawa on 29/01/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private static final int INITIAL_DELAY = 1000;
    private static final int INTERACTION_DELAY = 500;

    @Inject
    AdTypeRepository adTypeRepository;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        RentApp app = (RentApp) instrumentation.getTargetContext().getApplicationContext();
        TestRentAppComponent component = (TestRentAppComponent) app.getComponent();
        component.inject(this);
    }

    @Test
    public void validateAdTypeList() {
        for(int i = 0; i < adTypeRepository.count(); i++) {
            checkRecyclerItem(i);
        }
    }

    private void checkRecyclerItem(int position) {
        sleep(INTERACTION_DELAY);

        onView(allOf(ViewMatchers.withId(R.id.rvActivityMain), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition(position),
                        RecyclerViewActions.actionOnItemAtPosition(position, click()));

        sleep(INTERACTION_DELAY);

        pressBack();
    }
}
