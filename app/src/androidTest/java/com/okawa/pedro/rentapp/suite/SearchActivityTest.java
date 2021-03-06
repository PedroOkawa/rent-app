package com.okawa.pedro.rentapp.suite;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.RentApp;
import com.okawa.pedro.rentapp.database.AdTypeRepository;
import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.di.component.TestRentAppComponent;
import com.okawa.pedro.rentapp.ui.search.SearchActivity;
import com.okawa.pedro.rentapp.util.listener.OnApiServiceListener;
import com.okawa.pedro.rentapp.util.manager.ApiManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import greendao.AdType;

import static android.os.SystemClock.sleep;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.okawa.pedro.rentapp.matcher.ToolbarMatcher.matchToolbarTitle;
import static com.okawa.pedro.rentapp.util.TestManager.callSearchActivity;
import static com.okawa.pedro.rentapp.util.TestManager.checkRecyclerItem;

/**
 * Created by pokawa on 29/01/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchActivityTest implements OnApiServiceListener {

    private static final int INITIAL_DELAY = 5000;
    private static final int INTERACTION_DELAY = 500;
    private static final int TOTAL_SEARCH_TEST = 5;

    @Inject
    ApiManager apiManager;

    @Inject
    AdTypeRepository adTypeRepository;

    @Inject
    AdvertisementRepository advertisementRepository;

    @Rule
    public ActivityTestRule<SearchActivity> activityRule = new ActivityTestRule<>(SearchActivity.class, true, false);

    @Before
    public void setup() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        RentApp app = (RentApp) instrumentation.getTargetContext().getApplicationContext();
        TestRentAppComponent component = (TestRentAppComponent) app.getComponent();
        component.inject(this);

        apiManager.requestAdTypes(this);
        apiManager.requestAdvertisements(this);

        closeSoftKeyboard();
        sleep(INITIAL_DELAY);
    }

    @Test
    public void openShortTermSearch() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_SHORT_TERM);

        activityRule.launchActivity(callSearchActivity(adType));

        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @Test
    public void openSharingSearch() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_SHARING);

        activityRule.launchActivity(callSearchActivity(adType));

        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @Test
    public void openSaleSearch() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_SALE);

        activityRule.launchActivity(callSearchActivity(adType));

        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @Test
    public void openRentalSearch() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_RENTAL);

        activityRule.launchActivity(callSearchActivity(adType));

        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @Test
    public void openParkingSearch() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_PARKING);

        activityRule.launchActivity(callSearchActivity(adType));

        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @Test
    public void openNewDevelopmentSearch() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_NEW_DEVELOPMENT);

        activityRule.launchActivity(callSearchActivity(adType));

        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

    @Test
    public void openCommercialSearch() {
        AdType adType = adTypeRepository.selectAdTypeByName(AdType.TYPE_COMMERCIAL);

        activityRule.launchActivity(callSearchActivity(adType));

        matchToolbarTitle(adType.getDescriptionPlural()).check(matches(isDisplayed()));
    }

//    @Test
//    public void validateShortTermAdvertisements() {
//        openShortTermSearch();
//
//        for(int i = 0; i < TOTAL_SEARCH_TEST; i++) {
//            checkRecyclerItem(i, R.id.rvActivitySearch);
//
//            sleep(INTERACTION_DELAY);
//        }
//    }
//
//    @Test
//    public void validateSharingAdvertisements() {
//        openSharingSearch();
//
//        for(int i = 0; i < TOTAL_SEARCH_TEST; i++) {
//            checkRecyclerItem(i, R.id.rvActivitySearch);
//
//            sleep(INTERACTION_DELAY);
//        }
//    }

    @Test
    public void validateSaleAdvertisements() {
        openSaleSearch();

        sleep(INTERACTION_DELAY);

        for(int i = 0; i < TOTAL_SEARCH_TEST; i++) {
            checkRecyclerItem(i, R.id.rvActivitySearch);

            sleep(INTERACTION_DELAY);
        }
    }

//    @Test
//    public void validateRentalAdvertisements() {
//        openRentalSearch();
//
//        for(int i = 0; i < TOTAL_SEARCH_TEST; i++) {
//            checkRecyclerItem(i, R.id.rvActivitySearch);
//
//            sleep(INTERACTION_DELAY);
//        }
//    }
//
//    @Test
//    public void validateParkingAdvertisements() {
//        openParkingSearch();
//
//        for(int i = 0; i < TOTAL_SEARCH_TEST; i++) {
//            checkRecyclerItem(i, R.id.rvActivitySearch);
//
//            sleep(INTERACTION_DELAY);
//        }
//    }
//
//    @Test
//    public void validateNewDevelopmentAdvertisements() {
//        openNewDevelopmentSearch();
//
//        for(int i = 0; i < TOTAL_SEARCH_TEST; i++) {
//            checkRecyclerItem(i, R.id.rvActivitySearch);
//
//            sleep(INTERACTION_DELAY);
//        }
//    }
//
//    @Test
//    public void validateCommercialAdvertisements() {
//        openCommercialSearch();
//
//        for(int i = 0; i < TOTAL_SEARCH_TEST; i++) {
//            checkRecyclerItem(i, R.id.rvActivitySearch);
//
//            sleep(INTERACTION_DELAY);
//        }
//    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(String message) {
        activityRule.getActivity().finish();
        System.out.print(message);
    }
}
