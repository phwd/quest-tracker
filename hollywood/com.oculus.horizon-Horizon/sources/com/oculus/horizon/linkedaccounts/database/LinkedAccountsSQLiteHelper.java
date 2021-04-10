package com.oculus.horizon.linkedaccounts.database;

import X.AbstractC06640p5;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.base.Strings;
import com.oculus.horizon.linkedaccounts.database.contract.LinkedAccountsSQLiteContract;
import com.oculus.horizon.linkedaccounts.model.ServiceProvider;
import com.oculus.util.thread.ThreadUtils;
import java.util.Arrays;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID"})
@ApplicationScoped
public class LinkedAccountsSQLiteHelper extends SQLiteOpenHelper {
    public static final String TAG = "LinkedAccountsSQLiteHelper";
    public static volatile LinkedAccountsSQLiteHelper _UL__ULSEP_com_oculus_horizon_linkedaccounts_database_LinkedAccountsSQLiteHelper_ULSEP_INSTANCE;
    @Inject
    @Eager
    public final ThreadUtils mThreadUtils;

    public static final class LinkedAccountsSQLiteQueryHolder {
        public final String mSelection;
        public final String[] mSelectionArgs;

        public static LinkedAccountsSQLiteQueryHolder A00(ServiceProvider... serviceProviderArr) {
            StringBuilder sb = new StringBuilder();
            int length = serviceProviderArr.length;
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                if (i != 0) {
                    sb.append(" OR ");
                }
                strArr[i] = serviceProviderArr[i].toString();
                sb.append(LinkedAccountsSQLiteContract.SQL_SELECTION_SERVICE_PROVIDER);
            }
            return new LinkedAccountsSQLiteQueryHolder(sb.toString(), strArr);
        }

        public LinkedAccountsSQLiteQueryHolder(String str, String[] strArr) {
            this.mSelection = str;
            this.mSelectionArgs = strArr;
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 2) {
            sQLiteDatabase.beginTransaction();
            try {
                sQLiteDatabase.execSQL(LinkedAccountsSQLiteContract.SQL_TABLE_RENAME);
                sQLiteDatabase.execSQL(LinkedAccountsSQLiteContract.SQL_TABLE_CREATE);
                sQLiteDatabase.execSQL(LinkedAccountsSQLiteContract.SQL_TABLE_COPY);
                sQLiteDatabase.execSQL(LinkedAccountsSQLiteContract.SQL_TABLE_DROP);
                sQLiteDatabase.setTransactionSuccessful();
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }
    }

    @Inject
    public LinkedAccountsSQLiteHelper(AbstractC06640p5 r4, @ForAppContext Context context) {
        super(context, LinkedAccountsSQLiteContract.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 2);
        this.mThreadUtils = ThreadUtils.A01(r4);
    }

    public final int A00(ServiceProvider... serviceProviderArr) {
        this.mThreadUtils.A05();
        if (serviceProviderArr.length == 0) {
            return 0;
        }
        Arrays.toString(serviceProviderArr);
        LinkedAccountsSQLiteQueryHolder A00 = LinkedAccountsSQLiteQueryHolder.A00(serviceProviderArr);
        return getWritableDatabase().delete(LinkedAccountsSQLiteContract.TABLE_NAME, A00.mSelection, A00.mSelectionArgs);
    }

    public final long A01(String str, String str2, ServiceProvider serviceProvider) {
        String str3;
        this.mThreadUtils.A05();
        if (Strings.isNullOrEmpty(str)) {
            str3 = "Invalid user ID";
        } else if (serviceProvider == null || serviceProvider == ServiceProvider.UNKNOWN) {
            str3 = "Invalid service provider";
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("user_id", str);
            contentValues.put("token", str2);
            contentValues.put("service_provider", serviceProvider.toString());
            return getWritableDatabase().insertWithOnConflict(LinkedAccountsSQLiteContract.TABLE_NAME, null, contentValues, 5);
        }
        throw new IllegalArgumentException(str3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0054, code lost:
        if (0 == 0) goto L_0x0059;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<android.content.ContentValues> A02(com.oculus.horizon.linkedaccounts.model.ServiceProvider... r17) {
        /*
            r16 = this;
            r1 = r16
            com.oculus.util.thread.ThreadUtils r0 = r1.mThreadUtils
            r0.A05()
            r7 = r17
            int r0 = r7.length
            if (r0 != 0) goto L_0x0012
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            return r0
        L_0x0012:
            java.util.Arrays.toString(r7)
            r10 = 0
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            com.oculus.horizon.linkedaccounts.database.LinkedAccountsSQLiteHelper$LinkedAccountsSQLiteQueryHolder r0 = com.oculus.horizon.linkedaccounts.database.LinkedAccountsSQLiteHelper.LinkedAccountsSQLiteQueryHolder.A00(r7)
            android.database.sqlite.SQLiteDatabase r8 = r1.getWritableDatabase()     // Catch:{ Exception -> 0x0042 }
            java.lang.String r9 = "accounts"
            java.lang.String r11 = r0.mSelection     // Catch:{ Exception -> 0x0042 }
            java.lang.String[] r12 = r0.mSelectionArgs     // Catch:{ Exception -> 0x0042 }
            r13 = r10
            r14 = r10
            r15 = r10
            android.database.Cursor r10 = r8.query(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x0042 }
        L_0x0030:
            boolean r0 = r10.moveToNext()     // Catch:{ Exception -> 0x0042 }
            if (r0 == 0) goto L_0x0056
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch:{ Exception -> 0x0042 }
            r0.<init>()     // Catch:{ Exception -> 0x0042 }
            android.database.DatabaseUtils.cursorRowToContentValues(r10, r0)     // Catch:{ Exception -> 0x0042 }
            r6.add(r0)     // Catch:{ Exception -> 0x0042 }
            goto L_0x0030
        L_0x0042:
            r5 = move-exception
            java.lang.String r4 = "LinkedAccountsSQLiteHelper"
            java.lang.String r3 = "Unable to query linked accounts for %s"
            r0 = 1
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ all -> 0x005a }
            r1 = 0
            java.lang.String r0 = java.util.Arrays.toString(r7)     // Catch:{ all -> 0x005a }
            r2[r1] = r0     // Catch:{ all -> 0x005a }
            X.AnonymousClass0NO.A0K(r4, r5, r3, r2)     // Catch:{ all -> 0x005a }
            if (r10 == 0) goto L_0x0059
        L_0x0056:
            r10.close()
        L_0x0059:
            return r6
        L_0x005a:
            r0 = move-exception
            if (r10 == 0) goto L_0x0060
            r10.close()
        L_0x0060:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.linkedaccounts.database.LinkedAccountsSQLiteHelper.A02(com.oculus.horizon.linkedaccounts.model.ServiceProvider[]):java.util.List");
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(LinkedAccountsSQLiteContract.SQL_TABLE_CREATE);
    }
}
