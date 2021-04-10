package com.oculus.database.supplier;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.List;

public final class SharedSQLiteDbHelper {
    private static final String APP_BUILD_NUMBER = "app_build_number";
    private static final String APP_UPGRADE_TIME = "app_upgrade_time";
    private static final String[] COLS = {"version"};
    public static final int DATABASE_VERSION = 200;
    private static final int FIRST_DB_VERSION = 200;
    public static final int SCHEMA_PART_NOT_PRESENT = -1;
    private static final String SELECT_BY_NAME = "name=?";
    private static final String TABLE_SHARED_VERSION = "_shared_version";
    private static final String VERSION_TABLE_CREATE = "CREATE TABLE _shared_version (name TEXT PRIMARY KEY, version INTEGER)";
    private static volatile int sAppBuildNumber = 0;
    private static volatile long sLastAppUpgradeTime = -1;
    private final Context mContext;
    private final int mJournalSizeLimit;
    private final ImmutableList<SharedSQLiteSchemaPart> mParts;

    public SharedSQLiteDbHelper(List<? extends SharedSQLiteSchemaPart> list, int i, Context context) {
        this.mParts = ImmutableList.copyOf((Collection) list);
        this.mJournalSizeLimit = i;
        this.mContext = context;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(VERSION_TABLE_CREATE);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 200) {
            sQLiteDatabase.execSQL(VERSION_TABLE_CREATE);
            UnmodifiableIterator<SharedSQLiteSchemaPart> it = this.mParts.iterator();
            while (it.hasNext()) {
                writePartVersion(sQLiteDatabase, it.next().getName(), i);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        setJournalSizeLimit(sQLiteDatabase, this.mJournalSizeLimit);
        sQLiteDatabase.beginTransaction();
        try {
            boolean checkAppUpgrade = checkAppUpgrade(sQLiteDatabase);
            UnmodifiableIterator<SharedSQLiteSchemaPart> it = this.mParts.iterator();
            while (it.hasNext()) {
                SharedSQLiteSchemaPart next = it.next();
                int queryPartVersion = queryPartVersion(sQLiteDatabase, next.getName());
                if (queryPartVersion == -1) {
                    if (!sQLiteDatabase.isReadOnly()) {
                        next.onCreate(sQLiteDatabase);
                    } else {
                        throw new SQLiteException("Can't upgrade readonly database");
                    }
                } else if (queryPartVersion < next.getVersion()) {
                    if (!sQLiteDatabase.isReadOnly()) {
                        next.onUpgrade(sQLiteDatabase, queryPartVersion, next.getVersion());
                    } else {
                        throw new SQLiteException("Can't upgrade readonly database");
                    }
                } else if (queryPartVersion > next.getVersion()) {
                    throw new SQLiteException("Can't downgrade version for " + next.getName());
                }
                if (checkAppUpgrade && queryPartVersion != -1) {
                    next.onAppUpgrade(sQLiteDatabase);
                }
                if (queryPartVersion != next.getVersion()) {
                    writePartVersion(sQLiteDatabase, next.getName(), next.getVersion());
                }
            }
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            UnmodifiableIterator<SharedSQLiteSchemaPart> it2 = this.mParts.iterator();
            while (it2.hasNext()) {
                it2.next().onOpenAfterTransaction(sQLiteDatabase);
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    private boolean checkAppUpgrade(SQLiteDatabase sQLiteDatabase) {
        boolean z;
        int appBuildNumber = getAppBuildNumber(this.mContext);
        if (appBuildNumber != getAppBuildNumberFromDatabase(sQLiteDatabase)) {
            writeAppBuildNumber(sQLiteDatabase, appBuildNumber);
            z = true;
        } else {
            z = false;
        }
        long lastAppUpgradeTime = getLastAppUpgradeTime(this.mContext);
        if (lastAppUpgradeTime == getLastAppUpgradeTimeFromDatabase(sQLiteDatabase)) {
            return z;
        }
        writeLastAppUpgradeTime(sQLiteDatabase, lastAppUpgradeTime);
        return true;
    }

    private static void writePartVersion(SQLiteDatabase sQLiteDatabase, String str, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str);
        contentValues.put("version", Integer.valueOf(i));
        sQLiteDatabase.replaceOrThrow(TABLE_SHARED_VERSION, null, contentValues);
    }

    private static void writeAppBuildNumber(SQLiteDatabase sQLiteDatabase, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", APP_BUILD_NUMBER);
        contentValues.put("version", Integer.valueOf(i));
        sQLiteDatabase.replaceOrThrow(TABLE_SHARED_VERSION, null, contentValues);
    }

    private static void writeLastAppUpgradeTime(SQLiteDatabase sQLiteDatabase, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", APP_UPGRADE_TIME);
        contentValues.put("version", Long.valueOf(j));
        sQLiteDatabase.replaceOrThrow(TABLE_SHARED_VERSION, null, contentValues);
    }

    private static int queryPartVersion(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query = sQLiteDatabase.query(TABLE_SHARED_VERSION, COLS, SELECT_BY_NAME, new String[]{str}, null, null, null);
        try {
            if (query.moveToNext()) {
                return query.getInt(0);
            }
            query.close();
            return -1;
        } finally {
            query.close();
        }
    }

    private static int getAppBuildNumberFromDatabase(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query(TABLE_SHARED_VERSION, COLS, SELECT_BY_NAME, new String[]{APP_BUILD_NUMBER}, null, null, null);
        try {
            if (query.moveToNext()) {
                return query.getInt(0);
            }
            query.close();
            return -1;
        } finally {
            query.close();
        }
    }

    private static int getAppBuildNumber(Context context) {
        if (sAppBuildNumber == 0) {
            try {
                sAppBuildNumber = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return sAppBuildNumber;
    }

    private static long getLastAppUpgradeTimeFromDatabase(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query(TABLE_SHARED_VERSION, COLS, SELECT_BY_NAME, new String[]{APP_UPGRADE_TIME}, null, null, null);
        try {
            if (query.moveToNext()) {
                return query.getLong(0);
            }
            query.close();
            return -1;
        } finally {
            query.close();
        }
    }

    private static long getLastAppUpgradeTime(Context context) {
        if (sLastAppUpgradeTime == -1) {
            try {
                sLastAppUpgradeTime = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return sLastAppUpgradeTime;
    }

    protected static void setJournalSizeLimit(SQLiteDatabase sQLiteDatabase, int i) {
        setPragma(sQLiteDatabase, "wal_autocheckpoint", String.valueOf(Math.max(1, i / getPragmaInt(sQLiteDatabase, "page_size"))));
        setPragma(sQLiteDatabase, "journal_size_limit", String.valueOf(i));
    }

    protected static int getPragmaInt(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA " + str, null);
        try {
            rawQuery.moveToNext();
            int i = rawQuery.getInt(0);
            rawQuery.close();
            return i;
        } catch (Throwable th) {
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    protected static void setPragma(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA " + str + "=" + str2, null);
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
}
