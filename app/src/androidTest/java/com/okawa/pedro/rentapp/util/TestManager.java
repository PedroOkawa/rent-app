package com.okawa.pedro.rentapp.util;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.util.manager.CallManager;

import greendao.AdType;

import static android.os.SystemClock.sleep;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.okawa.pedro.rentapp.matcher.ToolbarMatcher.matchToolbarTitle;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by pokawa on 29/01/16.
 */
public class TestManager {

    public static Intent callSearchActivity(AdType adType) {
        Intent intent = new Intent();
        intent.putExtra(CallManager.BUNDLE_SEARCH, adType.getDescriptionPlural());

        return intent;
    }

    public static Intent callFilterActivity(AdType adType) {
        Intent intent = new Intent();
        intent.putExtra(CallManager.BUNDLE_FILTER, adType.getName());

        return intent;
    }

    public static void checkRecyclerItem(int position, @IdRes int layoutId) {
        onView(allOf(ViewMatchers.withId(layoutId), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition(position),
                        RecyclerViewActions.actionOnItemAtPosition(position, click()));

        pressBack();
    }
}
