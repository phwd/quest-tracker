package com.oculus.database.supplier;

import X.AnonymousClass006;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.common.collect.ImmutableList;
import java.util.List;

public final class SharedSQLiteDbHelper {
    public static final String APP_BUILD_NUMBER = "app_build_number";
    public static final String APP_UPGRADE_TIME = "app_upgrade_time";
    public static final String[] COLS = {"version"};
    public static final int DATABASE_VERSION = 200;
    public static final int FIRST_DB_VERSION = 200;
    public static final int SCHEMA_PART_NOT_PRESENT = -1;
    public static final String SELECT_BY_NAME = "name=?";
    public static final String TABLE_SHARED_VERSION = "_shared_version";
    public static final String VERSION_TABLE_CREATE = "CREATE TABLE _shared_version (name TEXT PRIMARY KEY, version INTEGER)";
    public static volatile int sAppBuildNumber = 0;
    public static volatile long sLastAppUpgradeTime = -1;
    public final Context mContext;
    public final int mJournalSizeLimit = AbstractDatabaseSupplier.DEFAULT_JOURNAL_SIZE_LIMIT;
    public final ImmutableList<SharedSQLiteSchemaPart> mParts;

    public static void A00(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor rawQuery = sQLiteDatabase.rawQuery(AnonymousClass006.A08("PRAGMA ", str, "=", str2), null);
        try {
            rawQuery.moveToNext();
            rawQuery.close();
        } catch (Throwable th) {
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/List<+Lcom/oculus/database/supplier/SharedSQLiteSchemaPart;>;ILandroid/content/Context;)V */
    public SharedSQLiteDbHelper(List list, Context context) {
        this.mParts = ImmutableList.A0C(list);
        this.mContext = context;
    }
}
