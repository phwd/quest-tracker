package oculus.internal.license.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LicenseDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "license.db";
    public static final int DEFAULT_CACHE_SIZE = -2000;
    public static final String TAG = "LicenseDbHelper";
    final int cacheSize;
    final LicenseDbSchemaMaintainer schemaMaintainer;

    public static LicenseDbHelper createInMemory(Context context, LicenseDbSchemaMaintainer schemaMaintainer2) {
        return new LicenseDbHelper(context, schemaMaintainer2, null, DEFAULT_CACHE_SIZE);
    }

    public LicenseDbHelper(Context context, LicenseDbSchemaMaintainer schemaMaintainer2) {
        this(context, schemaMaintainer2, DATABASE_NAME, DEFAULT_CACHE_SIZE);
    }

    public LicenseDbHelper(Context context, LicenseDbSchemaMaintainer schemaMaintainer2, int cacheSizeInPages) {
        this(context, schemaMaintainer2, DATABASE_NAME, cacheSizeInPages);
    }

    private LicenseDbHelper(Context context, LicenseDbSchemaMaintainer schemaMaintainer2, String databaseName, int cacheSize2) {
        super(context, databaseName, (SQLiteDatabase.CursorFactory) null, 2);
        this.schemaMaintainer = schemaMaintainer2;
        this.cacheSize = cacheSize2;
    }

    public void onConfigure(SQLiteDatabase db) {
        db.execSQL(String.format("PRAGMA cache_size = %d", Integer.valueOf(this.cacheSize)));
    }

    public void onCreate(SQLiteDatabase db) {
        this.schemaMaintainer.initialize(new DbWrapper(db));
    }

    public void onOpen(SQLiteDatabase db) {
        this.schemaMaintainer.open(new DbWrapper(db));
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.schemaMaintainer.migrateDbSchema(new DbWrapper(db));
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.schemaMaintainer.migrateDbSchema(new DbWrapper(db));
    }
}
