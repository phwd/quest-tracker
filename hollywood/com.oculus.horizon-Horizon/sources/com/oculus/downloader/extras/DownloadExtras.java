package com.oculus.downloader.extras;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.C003108z;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableMap;
import com.oculus.downloader.extras.contract.ExtrasDatabase;
import com.oculus.extras.Extras;
import java.util.Iterator;
import java.util.Map;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
@ApplicationScoped
public class DownloadExtras {
    public static volatile DownloadExtras _UL__ULSEP_com_oculus_downloader_extras_DownloadExtras_ULSEP_INSTANCE;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    public final DownloadExtrasSQLHelper mDownloadExtrasSQLHelper;
    public final String mIdColumnName = "download_id";
    public final String mKeyColumnName = "key";
    public final String mTableName = ExtrasDatabase.TABLE_DOWNLOAD_EXTRA;
    public final String mValueColumnName = "value";

    public final synchronized void A00(long j, Extras extras) {
        ImmutableMap<String, String> immutableMap = extras.mData;
        SQLiteDatabase writableDatabase = this.mDownloadExtrasSQLHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            Iterator<String> it = immutableMap.keySet().iterator();
            while (it.hasNext()) {
                writableDatabase.delete(this.mTableName, AnonymousClass006.A08(this.mIdColumnName, " = ? AND ", this.mKeyColumnName, " = ?"), new String[]{Long.toString(j), it.next()});
            }
            for (Map.Entry<String, String> entry : immutableMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value != null) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(this.mIdColumnName, Long.valueOf(j));
                    contentValues.put(this.mKeyColumnName, key);
                    contentValues.put(this.mValueColumnName, value);
                    writableDatabase.insert(this.mTableName, null, contentValues);
                }
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @Inject
    public DownloadExtras(AbstractC06640p5 r3) {
        Context A02 = C003108z.A02(r3);
        this.mContext = A02;
        this.mDownloadExtrasSQLHelper = new DownloadExtrasSQLHelper(A02);
    }
}
