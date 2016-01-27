package greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import greendao.Pagination;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PAGINATION".
*/
public class PaginationDao extends AbstractDao<Pagination, Long> {

    public static final String TABLENAME = "PAGINATION";

    /**
     * Properties of entity Pagination.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property Total = new Property(1, Long.class, "total", false, "TOTAL");
        public final static Property PerPage = new Property(2, Long.class, "perPage", false, "PER_PAGE");
        public final static Property NumPages = new Property(3, Long.class, "numPages", false, "NUM_PAGES");
        public final static Property CurrentPage = new Property(4, Long.class, "currentPage", false, "CURRENT_PAGE");
        public final static Property FirstOnPage = new Property(5, Long.class, "firstOnPage", false, "FIRST_ON_PAGE");
        public final static Property LastOnPage = new Property(6, Long.class, "lastOnPage", false, "LAST_ON_PAGE");
    };


    public PaginationDao(DaoConfig config) {
        super(config);
    }
    
    public PaginationDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PAGINATION\" (" + //
                "\"ID\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TOTAL\" INTEGER," + // 1: total
                "\"PER_PAGE\" INTEGER," + // 2: perPage
                "\"NUM_PAGES\" INTEGER," + // 3: numPages
                "\"CURRENT_PAGE\" INTEGER," + // 4: currentPage
                "\"FIRST_ON_PAGE\" INTEGER," + // 5: firstOnPage
                "\"LAST_ON_PAGE\" INTEGER);"); // 6: lastOnPage
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PAGINATION\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Pagination entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long total = entity.getTotal();
        if (total != null) {
            stmt.bindLong(2, total);
        }
 
        Long perPage = entity.getPerPage();
        if (perPage != null) {
            stmt.bindLong(3, perPage);
        }
 
        Long numPages = entity.getNumPages();
        if (numPages != null) {
            stmt.bindLong(4, numPages);
        }
 
        Long currentPage = entity.getCurrentPage();
        if (currentPage != null) {
            stmt.bindLong(5, currentPage);
        }
 
        Long firstOnPage = entity.getFirstOnPage();
        if (firstOnPage != null) {
            stmt.bindLong(6, firstOnPage);
        }
 
        Long lastOnPage = entity.getLastOnPage();
        if (lastOnPage != null) {
            stmt.bindLong(7, lastOnPage);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Pagination readEntity(Cursor cursor, int offset) {
        Pagination entity = new Pagination( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // total
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // perPage
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // numPages
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // currentPage
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5), // firstOnPage
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6) // lastOnPage
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Pagination entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTotal(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setPerPage(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setNumPages(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setCurrentPage(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setFirstOnPage(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
        entity.setLastOnPage(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Pagination entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Pagination entity) {
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
