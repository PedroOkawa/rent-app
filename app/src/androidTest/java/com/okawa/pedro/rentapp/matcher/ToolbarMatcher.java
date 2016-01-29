package com.okawa.pedro.rentapp.matcher;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.Toolbar;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static org.hamcrest.Matchers.is;

/**
 * Created by pokawa on 29/01/16.
 */
public class ToolbarMatcher {

    public static ViewInteraction matchToolbarTitle(String title) {
        return onView(isAssignableFrom(Toolbar.class)).check(matches(withToolbarTitle(is(title))));
    }

    private static Matcher<Object> withToolbarTitle(final Matcher<String> textMatcher) {
        return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override public boolean matchesSafely(Toolbar toolbar) {
                return textMatcher.matches(toolbar.getTitle());
            }
            @Override public void describeTo(Description description) {
                description.appendText("with toolbar title: ");
                textMatcher.describeTo(description);
            }
        };
    }

}
