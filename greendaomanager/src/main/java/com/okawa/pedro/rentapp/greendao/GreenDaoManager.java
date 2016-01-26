package com.okawa.pedro.rentapp.greendao;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoManager {

    /* DATABASE */
    private static final String DATABASE_OUTPUT = "../app/src/main/java-gen";
    private static final String DATABASE_PACKAGE = "greendao";
    private static final int DATABASE_VERSION = 1;

    /* PAGINATION */
    private static final String TABLE_PAGINATION = "Pagination";
    private static final String TABLE_PAGINATION_ID = "id";
    private static final String TABLE_PAGINATION_TOTAL = "total";
    private static final String TABLE_PAGINATION_PER_PAGE = "perPage";
    private static final String TABLE_PAGINATION_NUM_PAGES = "numPages";
    private static final String TABLE_PAGINATION_CURRENT_PAGE = "currentPage";
    private static final String TABLE_PAGINATION_FIRST_ON_PAGE = "firstOnPage";
    private static final String TABLE_PAGINATION_LAST_ON_PAGE = "lastOnPage";

    /* ADVERTISEMENT */
    private static final String TABLE_ADVERTISEMENT = "Advertisement";
    private static final String TABLE_ADVERTISEMENT_ID = "id";
    private static final String TABLE_ADVERTISEMENT_URL = "url";
    private static final String TABLE_ADVERTISEMENT_PROPERTY_TYPE = "propertyType";
    private static final String TABLE_ADVERTISEMENT_HOUSE_TYPE = "houseType";
    private static final String TABLE_ADVERTISEMENT_SELLING_TYPE = "sellingType";
    private static final String TABLE_ADVERTISEMENT_PRICE_TYPE = "priceType";
    private static final String TABLE_ADVERTISEMENT_AGREED = "agreed";
    private static final String TABLE_ADVERTISEMENT_PRIORITY = "priority";
    private static final String TABLE_ADVERTISEMENT_DESCRIPTION = "description";
    private static final String TABLE_ADVERTISEMENT_PRICE = "price";
    private static final String TABLE_ADVERTISEMENT_BEDROOMS = "bedrooms";
    private static final String TABLE_ADVERTISEMENT_BATHROOMS = "bathrooms";
    private static final String TABLE_ADVERTISEMENT_SQUARE_METERS = "squareMetres";
    private static final String TABLE_ADVERTISEMENT_ACRES = "acres";
    private static final String TABLE_ADVERTISEMENT_FULL_ADDRESS = "fullAddress";
    private static final String TABLE_ADVERTISEMENT_GENERAL_AREA = "generalArea";
    private static final String TABLE_ADVERTISEMENT_LATITUDE = "latitude";
    private static final String TABLE_ADVERTISEMENT_LONGITUDE = "longitude";
    private static final String TABLE_ADVERTISEMENT_AGENCY_NAME = "agencyName";
    private static final String TABLE_ADVERTISEMENT_CONTACT_NAME = "contactName";
    private static final String TABLE_ADVERTISEMENT_PHONE_ONE = "phoneOne";
    private static final String TABLE_ADVERTISEMENT_PHONE_TWO = "phoneTwo";
    private static final String TABLE_ADVERTISEMENT_PHONE_INFO = "phoneInfo";
    private static final String TABLE_ADVERTISEMENT_EMAIL_MAIN = "emailMain";
    private static final String TABLE_ADVERTISEMENT_EMAIL_CC = "emailCC";
    private static final String TABLE_ADVERTISEMENT_START_DATE = "startDate";
    private static final String TABLE_ADVERTISEMENT_LISTING_DATE = "listingDate";
    private static final String TABLE_ADVERTISEMENT_AGREED_DATE = "agreedDate";
    private static final String TABLE_ADVERTISEMENT_IMAGE_SMALL = "imageSmall";
    private static final String TABLE_ADVERTISEMENT_IMAGE_MEDIUM = "imageMedium";
    private static final String TABLE_ADVERTISEMENT_IMAGE_LARGE = "imageLarge";

    /* FEATURE */
    private static final String TABLE_FEATURE = "Feature";
    private static final String TABLE_FEATURE_ID = "id";
    private static final String TABLE_FEATURE_ADVERTISEMENT_ID = "advertisementId";
    private static final String TABLE_FEATURE_DESCRIPTION = "description";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(DATABASE_VERSION, DATABASE_PACKAGE);
        setupDatabase(schema);
        new DaoGenerator().generateAll(schema, DATABASE_OUTPUT);
    }

    private static void setupDatabase(Schema schema) {
        /* PAGINATION */
        Entity pagination = schema.addEntity(TABLE_PAGINATION);

        pagination.addLongProperty(TABLE_PAGINATION_ID).primaryKey().autoincrement();
        pagination.addLongProperty(TABLE_PAGINATION_TOTAL);
        pagination.addLongProperty(TABLE_PAGINATION_PER_PAGE);
        pagination.addLongProperty(TABLE_PAGINATION_NUM_PAGES);
        pagination.addLongProperty(TABLE_PAGINATION_CURRENT_PAGE);
        pagination.addLongProperty(TABLE_PAGINATION_FIRST_ON_PAGE);
        pagination.addLongProperty(TABLE_PAGINATION_LAST_ON_PAGE);

        /* ADVERTISEMENT */
        Entity advertisement = schema.addEntity(TABLE_ADVERTISEMENT);

        Property advertisementPK = advertisement.addLongProperty(TABLE_ADVERTISEMENT_ID).primaryKey().getProperty();
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_URL);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_PROPERTY_TYPE);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_HOUSE_TYPE);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_SELLING_TYPE);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_PRICE_TYPE);
        advertisement.addIntProperty(TABLE_ADVERTISEMENT_AGREED);
        advertisement.addIntProperty(TABLE_ADVERTISEMENT_PRIORITY);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_DESCRIPTION);
        advertisement.addIntProperty(TABLE_ADVERTISEMENT_PRICE);
        advertisement.addIntProperty(TABLE_ADVERTISEMENT_BEDROOMS);
        advertisement.addIntProperty(TABLE_ADVERTISEMENT_BATHROOMS);
        advertisement.addFloatProperty(TABLE_ADVERTISEMENT_SQUARE_METERS);
        advertisement.addFloatProperty(TABLE_ADVERTISEMENT_ACRES);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_FULL_ADDRESS);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_GENERAL_AREA);
        advertisement.addDoubleProperty(TABLE_ADVERTISEMENT_LATITUDE);
        advertisement.addDoubleProperty(TABLE_ADVERTISEMENT_LONGITUDE);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_AGENCY_NAME);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_CONTACT_NAME);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_PHONE_ONE);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_PHONE_TWO);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_PHONE_INFO);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_EMAIL_MAIN);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_EMAIL_CC);
        advertisement.addLongProperty(TABLE_ADVERTISEMENT_START_DATE);
        advertisement.addLongProperty(TABLE_ADVERTISEMENT_LISTING_DATE);
        advertisement.addLongProperty(TABLE_ADVERTISEMENT_AGREED_DATE);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_IMAGE_SMALL);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_IMAGE_MEDIUM);
        advertisement.addStringProperty(TABLE_ADVERTISEMENT_IMAGE_LARGE);

        /* FEATURE */
        Entity feature = schema.addEntity(TABLE_FEATURE);

        feature.addLongProperty(TABLE_FEATURE_ID).primaryKey().autoincrement();
        Property featureFK = feature.addLongProperty(TABLE_FEATURE_ADVERTISEMENT_ID).getProperty();
        feature.addStringProperty(TABLE_FEATURE_DESCRIPTION);

        /* RELATIONSHIP (ADVERTISEMENT 1 x FEATURE M) */
        feature.addToOne(advertisement, advertisementPK);
        advertisement.addToMany(feature, featureFK);
    }

}
