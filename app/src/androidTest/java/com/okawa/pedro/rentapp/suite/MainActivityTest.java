package com.okawa.pedro.rentapp.suite;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.okawa.pedro.rentapp.R;
import com.okawa.pedro.rentapp.RentApp;
import com.okawa.pedro.rentapp.database.AdTypeRepository;
import com.okawa.pedro.rentapp.di.component.TestRentAppComponent;
import com.okawa.pedro.rentapp.ui.main.MainActivity;
import com.okawa.pedro.rentapp.util.listener.OnApiServiceListener;
import com.okawa.pedro.rentapp.util.manager.ApiManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static android.os.SystemClock.sleep;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static com.okawa.pedro.rentapp.util.TestManager.checkRecyclerItem;

/**
 * Created by pokawa on 29/01/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest implements OnApiServiceListener {

    private static final int INITIAL_DELAY = 1000;
    private static final int INTERACTION_DELAY = 500;

    @Inject
    ApiManager apiManager;

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

        apiManager.requestAdTypes(this);

        closeSoftKeyboard();
        sleep(INITIAL_DELAY);
    }

    @Test
    public void validateAdTypeList() {
        for(int i = 0; i < adTypeRepository.count(); i++) {
            checkRecyclerItem(i, R.id.rvActivityMain);
            sleep(INTERACTION_DELAY);
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(String message) {
        activityRule.getActivity().finish();
        System.out.print(message);
    }
}
