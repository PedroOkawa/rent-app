package com.okawa.pedro.rentapp.ui.main;

/**
 * Created by pokawa on 26/01/16.
 */
public interface MainView {

    void initializeToolbar();
    void hideProgress();
    void displayError(String message);

}
