package com.okawa.pedro.rentapp.ui.search;

/**
 * Created by pokawa on 27/01/16.
 */
public interface SearchView {

    void initializeToolbar(String title);
    void showProgress();
    void hideProgress();
    void displayError(String message);

}
