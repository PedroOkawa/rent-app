package greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import greendao.Advertisement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ADVERTISEMENT".
*/
public class AdvertisementDao extends AbstractDao<Advertisement, Long> {

    public static final String TABLENAME = "ADVERTISEMENT";

    /**
     * Properties of entity Advertisement.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property AdId = new Property(1, Long.class, "adId", false, "AD_ID");
        public final static Property Url = new Property(2, String.class, "url", false, "URL");
        public final static Property PropertyType = new Property(3, String.class, "propertyType", false, "PROPERTY_TYPE");
        public final static Property HouseType = new Property(4, String.class, "houseType", false, "HOUSE_TYPE");
        public final static Property SellingType = new Property(5, String.class, "sellingType", false, "SELLING_TYPE");
        public final static Property PriceType = new Property(6, String.class, "priceType", false, "PRICE_TYPE");
        public final static Property Agreed = new Property(7, Integer.class, "agreed", false, "AGREED");
        public final static Property Priority = new Property(8, Integer.class, "priority", false, "PRIORITY");
        public final static Property Description = new Property(9, String.class, "description", false, "DESCRIPTION");
        public final static Property Price = new Property(10, Integer.class, "price", false, "PRICE");
        public final static Property Bedrooms = new Property(11, Integer.class, "bedrooms", false, "BEDROOMS");
        public final static Property Bathrooms = new Property(12, Integer.class, "bathrooms", false, "BATHROOMS");
        public final static Property SquareMeters = new Property(13, Float.class, "squareMeters", false, "SQUARE_METERS");
        public final static Property Acres = new Property(14, Float.class, "acres", false, "ACRES");
        public final static Property FullAddress = new Property(15, String.class, "fullAddress", false, "FULL_ADDRESS");
        public final static Property GeneralArea = new Property(16, String.class, "generalArea", false, "GENERAL_AREA");
        public final static Property Latitude = new Property(17, Double.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(18, Double.class, "longitude", false, "LONGITUDE");
        public final static Property AgencyName = new Property(19, String.class, "agencyName", false, "AGENCY_NAME");
        public final static Property ContactName = new Property(20, String.class, "contactName", false, "CONTACT_NAME");
        public final static Property PhoneOne = new Property(21, String.class, "phoneOne", false, "PHONE_ONE");
        public final static Property PhoneTwo = new Property(22, String.class, "phoneTwo", false, "PHONE_TWO");
        public final static Property PhoneInfo = new Property(23, String.class, "phoneInfo", false, "PHONE_INFO");
        public final static Property EmailMain = new Property(24, String.class, "emailMain", false, "EMAIL_MAIN");
        public final static Property EmailCC = new Property(25, String.class, "emailCC", false, "EMAIL_CC");
        public final static Property StartDate = new Property(26, Long.class, "startDate", false, "START_DATE");
        public final static Property ListingDate = new Property(27, Long.class, "listingDate", false, "LISTING_DATE");
        public final static Property AgreedDate = new Property(28, Long.class, "agreedDate", false, "AGREED_DATE");
        public final static Property ImageSmall = new Property(29, String.class, "imageSmall", false, "IMAGE_SMALL");
        public final static Property ImageMedium = new Property(30, String.class, "imageMedium", false, "IMAGE_MEDIUM");
        public final static Property ImageLarge = new Property(31, String.class, "imageLarge", false, "IMAGE_LARGE");
    };

    private DaoSession daoSession;


    public AdvertisementDao(DaoConfig config) {
        super(config);
    }
    
    public AdvertisementDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ADVERTISEMENT\" (" + //
                "\"ID\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"AD_ID\" INTEGER UNIQUE ," + // 1: adId
                "\"URL\" TEXT," + // 2: url
                "\"PROPERTY_TYPE\" TEXT," + // 3: propertyType
                "\"HOUSE_TYPE\" TEXT," + // 4: houseType
                "\"SELLING_TYPE\" TEXT," + // 5: sellingType
                "\"PRICE_TYPE\" TEXT," + // 6: priceType
                "\"AGREED\" INTEGER," + // 7: agreed
                "\"PRIORITY\" INTEGER," + // 8: priority
                "\"DESCRIPTION\" TEXT," + // 9: description
                "\"PRICE\" INTEGER," + // 10: price
                "\"BEDROOMS\" INTEGER," + // 11: bedrooms
                "\"BATHROOMS\" INTEGER," + // 12: bathrooms
                "\"SQUARE_METERS\" REAL," + // 13: squareMeters
                "\"ACRES\" REAL," + // 14: acres
                "\"FULL_ADDRESS\" TEXT," + // 15: fullAddress
                "\"GENERAL_AREA\" TEXT," + // 16: generalArea
                "\"LATITUDE\" REAL," + // 17: latitude
                "\"LONGITUDE\" REAL," + // 18: longitude
                "\"AGENCY_NAME\" TEXT," + // 19: agencyName
                "\"CONTACT_NAME\" TEXT," + // 20: contactName
                "\"PHONE_ONE\" TEXT," + // 21: phoneOne
                "\"PHONE_TWO\" TEXT," + // 22: phoneTwo
                "\"PHONE_INFO\" TEXT," + // 23: phoneInfo
                "\"EMAIL_MAIN\" TEXT," + // 24: emailMain
                "\"EMAIL_CC\" TEXT," + // 25: emailCC
                "\"START_DATE\" INTEGER," + // 26: startDate
                "\"LISTING_DATE\" INTEGER," + // 27: listingDate
                "\"AGREED_DATE\" INTEGER," + // 28: agreedDate
                "\"IMAGE_SMALL\" TEXT," + // 29: imageSmall
                "\"IMAGE_MEDIUM\" TEXT," + // 30: imageMedium
                "\"IMAGE_LARGE\" TEXT);"); // 31: imageLarge
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ADVERTISEMENT\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Advertisement entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long adId = entity.getAdId();
        if (adId != null) {
            stmt.bindLong(2, adId);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(3, url);
        }
 
        String propertyType = entity.getPropertyType();
        if (propertyType != null) {
            stmt.bindString(4, propertyType);
        }
 
        String houseType = entity.getHouseType();
        if (houseType != null) {
            stmt.bindString(5, houseType);
        }
 
        String sellingType = entity.getSellingType();
        if (sellingType != null) {
            stmt.bindString(6, sellingType);
        }
 
        String priceType = entity.getPriceType();
        if (priceType != null) {
            stmt.bindString(7, priceType);
        }
 
        Integer agreed = entity.getAgreed();
        if (agreed != null) {
            stmt.bindLong(8, agreed);
        }
 
        Integer priority = entity.getPriority();
        if (priority != null) {
            stmt.bindLong(9, priority);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(10, description);
        }
 
        Integer price = entity.getPrice();
        if (price != null) {
            stmt.bindLong(11, price);
        }
 
        Integer bedrooms = entity.getBedrooms();
        if (bedrooms != null) {
            stmt.bindLong(12, bedrooms);
        }
 
        Integer bathrooms = entity.getBathrooms();
        if (bathrooms != null) {
            stmt.bindLong(13, bathrooms);
        }
 
        Float squareMeters = entity.getSquareMeters();
        if (squareMeters != null) {
            stmt.bindDouble(14, squareMeters);
        }
 
        Float acres = entity.getAcres();
        if (acres != null) {
            stmt.bindDouble(15, acres);
        }
 
        String fullAddress = entity.getFullAddress();
        if (fullAddress != null) {
            stmt.bindString(16, fullAddress);
        }
 
        String generalArea = entity.getGeneralArea();
        if (generalArea != null) {
            stmt.bindString(17, generalArea);
        }
 
        Double latitude = entity.getLatitude();
        if (latitude != null) {
            stmt.bindDouble(18, latitude);
        }
 
        Double longitude = entity.getLongitude();
        if (longitude != null) {
            stmt.bindDouble(19, longitude);
        }
 
        String agencyName = entity.getAgencyName();
        if (agencyName != null) {
            stmt.bindString(20, agencyName);
        }
 
        String contactName = entity.getContactName();
        if (contactName != null) {
            stmt.bindString(21, contactName);
        }
 
        String phoneOne = entity.getPhoneOne();
        if (phoneOne != null) {
            stmt.bindString(22, phoneOne);
        }
 
        String phoneTwo = entity.getPhoneTwo();
        if (phoneTwo != null) {
            stmt.bindString(23, phoneTwo);
        }
 
        String phoneInfo = entity.getPhoneInfo();
        if (phoneInfo != null) {
            stmt.bindString(24, phoneInfo);
        }
 
        String emailMain = entity.getEmailMain();
        if (emailMain != null) {
            stmt.bindString(25, emailMain);
        }
 
        String emailCC = entity.getEmailCC();
        if (emailCC != null) {
            stmt.bindString(26, emailCC);
        }
 
        Long startDate = entity.getStartDate();
        if (startDate != null) {
            stmt.bindLong(27, startDate);
        }
 
        Long listingDate = entity.getListingDate();
        if (listingDate != null) {
            stmt.bindLong(28, listingDate);
        }
 
        Long agreedDate = entity.getAgreedDate();
        if (agreedDate != null) {
            stmt.bindLong(29, agreedDate);
        }
 
        String imageSmall = entity.getImageSmall();
        if (imageSmall != null) {
            stmt.bindString(30, imageSmall);
        }
 
        String imageMedium = entity.getImageMedium();
        if (imageMedium != null) {
            stmt.bindString(31, imageMedium);
        }
 
        String imageLarge = entity.getImageLarge();
        if (imageLarge != null) {
            stmt.bindString(32, imageLarge);
        }
    }

    @Override
    protected void attachEntity(Advertisement entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Advertisement readEntity(Cursor cursor, int offset) {
        Advertisement entity = new Advertisement( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // adId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // url
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // propertyType
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // houseType
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // sellingType
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // priceType
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // agreed
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // priority
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // description
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // price
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // bedrooms
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // bathrooms
            cursor.isNull(offset + 13) ? null : cursor.getFloat(offset + 13), // squareMeters
            cursor.isNull(offset + 14) ? null : cursor.getFloat(offset + 14), // acres
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // fullAddress
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // generalArea
            cursor.isNull(offset + 17) ? null : cursor.getDouble(offset + 17), // latitude
            cursor.isNull(offset + 18) ? null : cursor.getDouble(offset + 18), // longitude
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // agencyName
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // contactName
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // phoneOne
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // phoneTwo
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // phoneInfo
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // emailMain
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // emailCC
            cursor.isNull(offset + 26) ? null : cursor.getLong(offset + 26), // startDate
            cursor.isNull(offset + 27) ? null : cursor.getLong(offset + 27), // listingDate
            cursor.isNull(offset + 28) ? null : cursor.getLong(offset + 28), // agreedDate
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // imageSmall
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // imageMedium
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31) // imageLarge
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Advertisement entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAdId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPropertyType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setHouseType(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSellingType(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPriceType(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAgreed(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setPriority(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setDescription(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPrice(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setBedrooms(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setBathrooms(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setSquareMeters(cursor.isNull(offset + 13) ? null : cursor.getFloat(offset + 13));
        entity.setAcres(cursor.isNull(offset + 14) ? null : cursor.getFloat(offset + 14));
        entity.setFullAddress(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setGeneralArea(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setLatitude(cursor.isNull(offset + 17) ? null : cursor.getDouble(offset + 17));
        entity.setLongitude(cursor.isNull(offset + 18) ? null : cursor.getDouble(offset + 18));
        entity.setAgencyName(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setContactName(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setPhoneOne(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setPhoneTwo(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setPhoneInfo(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setEmailMain(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setEmailCC(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setStartDate(cursor.isNull(offset + 26) ? null : cursor.getLong(offset + 26));
        entity.setListingDate(cursor.isNull(offset + 27) ? null : cursor.getLong(offset + 27));
        entity.setAgreedDate(cursor.isNull(offset + 28) ? null : cursor.getLong(offset + 28));
        entity.setImageSmall(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setImageMedium(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setImageLarge(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Advertisement entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Advertisement entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
