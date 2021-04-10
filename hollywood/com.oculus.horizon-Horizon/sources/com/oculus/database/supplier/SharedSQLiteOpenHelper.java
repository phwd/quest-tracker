package com.oculus.database.supplier;

import X.AbstractC07380s1;
import X.AnonymousClass006;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;

@SuppressLint({"BadSuperClassSQLiteOpenHelper.SharedSQLiteOpenHelper"})
public class SharedSQLiteOpenHelper extends SQLiteOpenHelper {
    public final SharedSQLiteDbHelper mSharedSQLiteDbHelper;

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;Ljava/lang/String;Ljava/util/List<+Lcom/oculus/database/supplier/SharedSQLiteSchemaPart;>;ILandroid/database/DatabaseErrorHandler;)V */
    public SharedSQLiteOpenHelper(Context context, String str, List list, DatabaseErrorHandler databaseErrorHandler) {
        super(context, str, null, 200, databaseErrorHandler);
        this.mSharedSQLiteDbHelper = new SharedSQLiteDbHelper(list, context);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(SharedSQLiteDbHelper.VERSION_TABLE_CREATE);
    }

    /* JADX INFO: finally extract failed */
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        int i;
        boolean z;
        long j;
        int i2;
        SharedSQLiteDbHelper sharedSQLiteDbHelper = this.mSharedSQLiteDbHelper;
        int i3 = sharedSQLiteDbHelper.mJournalSizeLimit;
        Cursor rawQuery = sQLiteDatabase.rawQuery(AnonymousClass006.A05("PRAGMA ", "page_size"), null);
        try {
            rawQuery.moveToNext();
            int i4 = rawQuery.getInt(0);
            rawQuery.close();
            SharedSQLiteDbHelper.A00(sQLiteDatabase, "wal_autocheckpoint", String.valueOf(Math.max(1, i3 / i4)));
            SharedSQLiteDbHelper.A00(sQLiteDatabase, "journal_size_limit", String.valueOf(i3));
            sQLiteDatabase.beginTransaction();
            try {
                Context context = sharedSQLiteDbHelper.mContext;
                if (SharedSQLiteDbHelper.sAppBuildNumber == 0) {
                    try {
                        SharedSQLiteDbHelper.sAppBuildNumber = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                int i5 = SharedSQLiteDbHelper.sAppBuildNumber;
                String[] strArr = SharedSQLiteDbHelper.COLS;
                Cursor query = sQLiteDatabase.query(SharedSQLiteDbHelper.TABLE_SHARED_VERSION, strArr, SharedSQLiteDbHelper.SELECT_BY_NAME, new String[]{SharedSQLiteDbHelper.APP_BUILD_NUMBER}, null, null, null);
                try {
                    if (query.moveToNext()) {
                        i = query.getInt(0);
                        query.close();
                    } else {
                        query.close();
                        i = -1;
                    }
                    if (i5 != i) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("name", SharedSQLiteDbHelper.APP_BUILD_NUMBER);
                        contentValues.put("version", Integer.valueOf(i5));
                        sQLiteDatabase.replaceOrThrow(SharedSQLiteDbHelper.TABLE_SHARED_VERSION, null, contentValues);
                        z = true;
                    } else {
                        z = false;
                    }
                    Context context2 = sharedSQLiteDbHelper.mContext;
                    if (SharedSQLiteDbHelper.sLastAppUpgradeTime == -1) {
                        try {
                            SharedSQLiteDbHelper.sLastAppUpgradeTime = context2.getPackageManager().getPackageInfo(context2.getPackageName(), 0).lastUpdateTime;
                        } catch (PackageManager.NameNotFoundException unused2) {
                        }
                    }
                    long j2 = SharedSQLiteDbHelper.sLastAppUpgradeTime;
                    Cursor query2 = sQLiteDatabase.query(SharedSQLiteDbHelper.TABLE_SHARED_VERSION, strArr, SharedSQLiteDbHelper.SELECT_BY_NAME, new String[]{SharedSQLiteDbHelper.APP_UPGRADE_TIME}, null, null, null);
                    if (query2.moveToNext()) {
                        j = query2.getLong(0);
                        query2.close();
                    } else {
                        query2.close();
                        j = -1;
                    }
                    if (j2 != j) {
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("name", SharedSQLiteDbHelper.APP_UPGRADE_TIME);
                        contentValues2.put("version", Long.valueOf(j2));
                        sQLiteDatabase.replaceOrThrow(SharedSQLiteDbHelper.TABLE_SHARED_VERSION, null, contentValues2);
                        z = true;
                    }
                    AbstractC07380s1<SharedSQLiteSchemaPart> A0K = sharedSQLiteDbHelper.mParts.iterator();
                    while (A0K.hasNext()) {
                        SharedSQLiteSchemaPart next = A0K.next();
                        Cursor query3 = sQLiteDatabase.query(SharedSQLiteDbHelper.TABLE_SHARED_VERSION, strArr, SharedSQLiteDbHelper.SELECT_BY_NAME, new String[]{next.name}, null, null, null);
                        try {
                            if (query3.moveToNext()) {
                                i2 = query3.getInt(0);
                                query3.close();
                            } else {
                                query3.close();
                                i2 = -1;
                            }
                            if (i2 != -1) {
                                int i6 = next.version;
                                if (i2 < i6) {
                                    if (!sQLiteDatabase.isReadOnly()) {
                                        next.A04(sQLiteDatabase, i2, next.version);
                                    } else {
                                        throw new SQLiteException("Can't upgrade readonly database");
                                    }
                                } else if (i2 > i6) {
                                    throw new SQLiteException(AnonymousClass006.A05("Can't downgrade version for ", next.name));
                                }
                            } else if (!sQLiteDatabase.isReadOnly()) {
                                next.A03(sQLiteDatabase);
                            } else {
                                throw new SQLiteException("Can't upgrade readonly database");
                            }
                            if (z && i2 != -1) {
                                next.A01(sQLiteDatabase);
                            }
                            int i7 = next.version;
                            if (i2 != i7) {
                                String str = next.name;
                                ContentValues contentValues3 = new ContentValues();
                                contentValues3.put("name", str);
                                contentValues3.put("version", Integer.valueOf(i7));
                                sQLiteDatabase.replaceOrThrow(SharedSQLiteDbHelper.TABLE_SHARED_VERSION, null, contentValues3);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            query3.close();
                            throw th;
                        }
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    AbstractC07380s1<SharedSQLiteSchemaPart> A0K2 = sharedSQLiteDbHelper.mParts.iterator();
                    while (A0K2.hasNext()) {
                        A0K2.next().A02(sQLiteDatabase);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    query.close();
                    throw th;
                }
            } catch (Throwable th4) {
                sQLiteDatabase.endTransaction();
                throw th4;
            }
        } catch (Throwable th5) {
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th5;
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        SharedSQLiteDbHelper sharedSQLiteDbHelper = this.mSharedSQLiteDbHelper;
        if (i < 200) {
            sQLiteDatabase.execSQL(SharedSQLiteDbHelper.VERSION_TABLE_CREATE);
            AbstractC07380s1<SharedSQLiteSchemaPart> A0K = sharedSQLiteDbHelper.mParts.iterator();
            while (A0K.hasNext()) {
                String str = A0K.next().name;
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", str);
                contentValues.put("version", Integer.valueOf(i));
                sQLiteDatabase.replaceOrThrow(SharedSQLiteDbHelper.TABLE_SHARED_VERSION, null, contentValues);
            }
        }
    }
}
