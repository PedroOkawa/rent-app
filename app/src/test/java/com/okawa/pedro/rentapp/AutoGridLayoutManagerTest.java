package com.okawa.pedro.rentapp;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.okawa.pedro.rentapp.util.manager.AutoGridLayoutManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by pokawa on 24/11/15.
 */
public class AutoGridLayoutManagerTest {

    private static final int COLUMNS_NUMBER_PORTRAIT = 1;
    private static final int COLUMNS_NUMBER_LANDSCAPE = 2;

    @Mock Context mContext;
    @Mock Resources mResources;
    @Mock Configuration mConfiguration;
    private AutoGridLayoutManager mAutoGridLayoutManager;

    @Before
    public void setup() {
        initMocks(this);
        mConfiguration.orientation = Configuration.ORIENTATION_PORTRAIT;
        when(mResources.getConfiguration()).thenReturn(mConfiguration);
        when(mContext.getResources()).thenReturn(mResources);
        mAutoGridLayoutManager = new AutoGridLayoutManager(mContext);
    }

    @Test
    public void testColumnNumberPortrait() {
        mAutoGridLayoutManager.changeColumnsNumber(Configuration.ORIENTATION_PORTRAIT);
        assertEquals(mAutoGridLayoutManager.getSpanCount(), COLUMNS_NUMBER_PORTRAIT);
    }

    @Test
    public void testColumnNumberLandscape() {
        mAutoGridLayoutManager.changeColumnsNumber(Configuration.ORIENTATION_LANDSCAPE);
        assertEquals(mAutoGridLayoutManager.getSpanCount(), COLUMNS_NUMBER_LANDSCAPE);
    }
}
