package com.okawa.pedro.rentapp.di.component;

import com.okawa.pedro.rentapp.RentApp;
import com.okawa.pedro.rentapp.database.AdTypeRepository;
import com.okawa.pedro.rentapp.database.AdvertisementRepository;
import com.okawa.pedro.rentapp.network.ApiInterface;
import com.okawa.pedro.rentapp.util.manager.ApiManager;
import com.okawa.pedro.rentapp.util.manager.ApiQueryManager;
import com.okawa.pedro.rentapp.util.manager.CallManager;

/**
 * Created by pokawa on 29/01/16.
 */
public interface AppComponent {

    void inject(RentApp rentApp);

    /* APP */
    RentApp providesRentApp();

    /* API */
    ApiInterface providesApiInterface();
    String providesApiKey();
    ApiQueryManager providesQueryManager();
    ApiManager providesApiManager();

    /* CALL */
    CallManager providesCallManager();

    /* DATABASE */
    AdTypeRepository providesAdTypeRepository();
    AdvertisementRepository providesAdvertisementRepository();

}
