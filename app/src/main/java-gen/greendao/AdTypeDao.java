package greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import greendao.AdType;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "AD_TYPE".
*/
public class AdTypeDao extends AbstractDao<AdType, String> {

    public static final String TABLENAME = "AD_TYPE";

    /**
     * Properties of entity AdType.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Name = new Property(0, String.class, "name", true, "NAME");
        public final static Property DescriptionShort = new Property(1, String.class, "descriptionShort", false, "DESCRIPTION_SHORT");
        public final static Property Description = new Property(2, String.class, "description", false, "DESCRIPTION");
    };


    public AdTypeDao(DaoConfig config) {
        super(config);
    }
    
    public AdTypeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"AD_TYPE\" (" + //
                "\"NAME\" TEXT PRIMARY KEY NOT NULL ," + // 0: name
                "\"DESCRIPTION_SHORT\" TEXT," + // 1: descriptionShort
                "\"DESCRIPTION\" TEXT);"); // 2: description
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"AD_TYPE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, AdType entity) {
        stmt.clearBindings();
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(1, name);
        }
 
        String descriptionShort = entity.getDescriptionShort();
        if (descriptionShort != null) {
            stmt.bindString(2, descriptionShort);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(3, description);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public AdType readEntity(Cursor cursor, int offset) {
        AdType entity = new AdType( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // name
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // descriptionShort
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // description
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, AdType entity, int offset) {
        entity.setName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setDescriptionShort(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDescription(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(AdType entity, long rowId) {
        return entity.getName();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(AdType entity) {
        if(entity != null) {
            return entity.getName();
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
