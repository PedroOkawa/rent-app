package com.okawa.pedro.rentapp;


import com.okawa.pedro.rentapp.di.module.ApiModule;
import com.okawa.pedro.rentapp.di.module.DatabaseModule;
import com.okawa.pedro.rentapp.network.ApiInterface;
import com.okawa.pedro.rentapp.util.manager.ApiQueryManager;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by pokawa on 29/01/16.
 */
public class ApiQueryManagerTest {

    private static final String API_KEY_TEST = "TEST";
    private static final String EXPECTED_RESULT = "{\"api_key\":\"TEST\"}";
    private static final String EXPECTED_RESULT_PARAMS = "{\"api_key\":\"TEST\",\"query\":{\"page\":\"1\",\"perpage\":\"20\"}}";

    private ApiQueryManager apiQueryManager;

    @Before
    public void setup() {
        apiQueryManager = new ApiQueryManager(API_KEY_TEST);
    }

    @Test
    public void testQueryManager() {
        assertEquals(apiQueryManager.generateQuery(), (EXPECTED_RESULT));
    }

    @Test
    public void testQueryManagerWithParams() {
        Map<String, Object> params = new HashMap<>();

        params.put(ApiQueryManager.PARAMETER_PAGE, 1);
        params.put(ApiQueryManager.PARAMETER_PER_PAGE, DatabaseModule.SELECT_LIMIT);

        assertEquals(apiQueryManager.generateQuery(params), (EXPECTED_RESULT_PARAMS));
    }
}
