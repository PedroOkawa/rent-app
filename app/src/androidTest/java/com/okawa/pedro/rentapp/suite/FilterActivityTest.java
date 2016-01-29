package com.okawa.pedro.rentapp.suite;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.RentApp;
import com.okawa.pedro.rentapp.database.AdTypeRepository;
import com.okawa.pedro.rentapp.di.component.TestRentAppComponent;
import com.okawa.pedro.rentapp.ui.filter.FilterActivity;

import junit.framework.AssertionFailedError;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import greendao.AdType;

import static android.os.SystemClock.sleep;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.okawa.pedro.rentapp.matcher.ToolbarMatcher.matchToolbarTitle;
import static com.okawa.pedro.rentapp.util.TestManager.callFilterActivity;

/**
 * Created by pokawa on 29/01/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FilterActivityTest {

    private static final int INTERACTION_DELAY = 500;

    @Inject
    AdTypeRepository adTypeRepository;

    @Rule
    public ActivityTestRule<FilterActivity> activityRule = new ActivityTestRule<>(FilterActivity.class, true, false);

    @Before
    public void setup() {
        closeSoftKeyboard();
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        RentApp app = (RentApp) instrumentation.getTargetContext().getApplicationContext();
        TestRentAppComponent component = (TestRentAppComponent) app.getComponent();
        component.inject(this);
    }

    @Test
    public void openShortTermFilter() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_SHORT_TERM);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));
    }

    @Test
    public void openSharingFilter() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_SHARING);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));
    }

    @Test
    public void openSaleFilter() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_SALE);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));
    }

    @Test
    public void openRentalFilter() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_RENTAL);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));
    }

    @Test
    public void openParkingFilter() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_PARKING);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));
    }

    @Test
    public void openNewDevelopmentFilter() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_NEW_DEVELOPMENT);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));
    }

    @Test
    public void openCommercialFilter() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_COMMERCIAL);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));
    }

    @Test(expected = AssertionFailedError.class)
    public void openSearchFromShortTerm() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_SHORT_TERM);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));

        sleep(INTERACTION_DELAY);

        onView(withId(R.id.tvActivityFilterSearch)).perform(ViewActions.click());
        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @Test(expected = AssertionFailedError.class)
    public void openSearchFromSharing() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_SHARING);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));

        sleep(INTERACTION_DELAY);

        onView(withId(R.id.tvActivityFilterSearch)).perform(ViewActions.click());
        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @Test
    public void openSearchFromSale() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_SALE);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));

        sleep(INTERACTION_DELAY);

        onView(withId(R.id.tvActivityFilterSearch)).perform(ViewActions.click());
        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @Test(expected = AssertionFailedError.class)
    public void openSearchFromRental() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_RENTAL);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));

        sleep(INTERACTION_DELAY);

        onView(withId(R.id.tvActivityFilterSearch)).perform(ViewActions.click());
        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @Test(expected = AssertionFailedError.class)
    public void openSearchFromParking() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_PARKING);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));

        sleep(INTERACTION_DELAY);

        onView(withId(R.id.tvActivityFilterSearch)).perform(ViewActions.click());
        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @Test(expected = AssertionFailedError.class)
    public void openSearchFromNewDevelopment() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_NEW_DEVELOPMENT);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));

        sleep(INTERACTION_DELAY);
        
        onView(withId(R.id.tvActivityFilterSearch)).perform(ViewActions.click());
        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @Test(expected = AssertionFailedError.class)
    public void openSearchFromCommercial() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_COMMERCIAL);

        activityRule.launchActivity(callFilterActivity(adType));
        activityRule.getActivity();

        matchToolbarTitle(adType.getDescriptionShort()).check(matches(isDisplayed()));

        sleep(INTERACTION_DELAY);
        
        onView(withId(R.id.tvActivityFilterSearch)).perform(ViewActions.click());
        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @After
    public void dispose() {
        activityRule.getActivity().finish();
    }
}
