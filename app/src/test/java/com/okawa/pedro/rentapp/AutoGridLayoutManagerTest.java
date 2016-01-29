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

    @Mock Context context;
    @Mock Resources resources;
    @Mock Configuration configuration;
    private AutoGridLayoutManager autoGridLayoutManager;

    @Before
    public void setup() {
        initMocks(this);
        configuration.orientation = Configuration.ORIENTATION_PORTRAIT;
        when(resources.getConfiguration()).thenReturn(configuration);
        when(context.getResources()).thenReturn(resources);
        autoGridLayoutManager = new AutoGridLayoutManager(context);
    }

    @Test
    public void testColumnNumberPortrait() {
        autoGridLayoutManager.changeColumnsNumber(Configuration.ORIENTATION_PORTRAIT);
        assertEquals(autoGridLayoutManager.getSpanCount(), COLUMNS_NUMBER_PORTRAIT);
    }

    @Test
    public void testColumnNumberLandscape() {
        autoGridLayoutManager.changeColumnsNumber(Configuration.ORIENTATION_LANDSCAPE);
        assertEquals(autoGridLayoutManager.getSpanCount(), COLUMNS_NUMBER_LANDSCAPE);
    }
}
