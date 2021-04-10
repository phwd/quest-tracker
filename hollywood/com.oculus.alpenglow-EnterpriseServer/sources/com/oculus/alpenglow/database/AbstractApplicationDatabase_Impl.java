package com.oculus.alpenglow.database;

import X.AbstractC01240Fs;
import X.AnonymousClass006;
import X.AnonymousClass0FB;
import X.AnonymousClass0FX;
import X.AnonymousClass0GB;
import X.AnonymousClass0GF;
import X.AnonymousClass0GQ;
import X.AnonymousClass0GT;
import X.AnonymousClass0GV;
import X.C01250Ft;
import X.C03420cG;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import androidx.room.RoomDatabase;
import com.oculus.alpenglow.logging.LoggerConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public final class AbstractApplicationDatabase_Impl extends AbstractApplicationDatabase {
    public volatile ApplicationDao _applicationDao;
    public volatile AssetDao _assetDao;

    @Override // X.AnonymousClass0Fr
    public final AnonymousClass0FX A01() {
        return new AnonymousClass0FX(this, new HashMap(0), new HashMap(0), "application", "asset");
    }

    @Override // X.AnonymousClass0Fr
    public final AnonymousClass0GV A02(AnonymousClass0FB r5) {
        C03420cG r3 = new C03420cG(r5, new AbstractC01240Fs() {
            /* class com.oculus.alpenglow.database.AbstractApplicationDatabase_Impl.AnonymousClass1 */

            @Override // X.AbstractC01240Fs
            public final C01250Ft A00(AnonymousClass0GQ r22) {
                String str;
                HashMap hashMap = new HashMap(7);
                hashMap.put(LoggerConstants.APP_ID_KEY, new AnonymousClass0GB(LoggerConstants.APP_ID_KEY, "TEXT", true, 1, null, 1));
                hashMap.put("package_name", new AnonymousClass0GB("package_name", "TEXT", false, 0, null, 1));
                hashMap.put("last_updated_on_backend", new AnonymousClass0GB("last_updated_on_backend", "INTEGER", true, 0, null, 1));
                hashMap.put("status", new AnonymousClass0GB("status", "INTEGER", false, 0, null, 1));
                hashMap.put("downloaded_bytes", new AnonymousClass0GB("downloaded_bytes", "INTEGER", true, 0, null, 1));
                hashMap.put("total_bytes", new AnonymousClass0GB("total_bytes", "INTEGER", true, 0, null, 1));
                hashMap.put("app_source", new AnonymousClass0GB("app_source", "INTEGER", false, 0, null, 1));
                AnonymousClass0GF r6 = new AnonymousClass0GF("application", hashMap, new HashSet(0), new HashSet(0));
                AnonymousClass0GF A00 = AnonymousClass0GF.A00(r22, "application");
                if (!r6.equals(A00)) {
                    str = "application(com.oculus.alpenglow.database.Application).\n Expected:\n";
                } else {
                    HashMap hashMap2 = new HashMap(3);
                    hashMap2.put("asset_id", new AnonymousClass0GB("asset_id", "TEXT", true, 1, null, 1));
                    hashMap2.put(LoggerConstants.APP_ID_KEY, new AnonymousClass0GB(LoggerConstants.APP_ID_KEY, "TEXT", false, 0, null, 1));
                    hashMap2.put("last_updated_on_backend", new AnonymousClass0GB("last_updated_on_backend", "INTEGER", true, 0, null, 1));
                    r6 = new AnonymousClass0GF("asset", hashMap2, new HashSet(0), new HashSet(0));
                    A00 = AnonymousClass0GF.A00(r22, "asset");
                    if (r6.equals(A00)) {
                        return new C01250Ft(true, null);
                    }
                    str = "asset(com.oculus.alpenglow.database.Asset).\n Expected:\n";
                }
                return new C01250Ft(false, str + r6 + "\n Found:\n" + A00);
            }

            /* JADX INFO: finally extract failed */
            @Override // X.AbstractC01240Fs
            public final void A01(AnonymousClass0GQ r4) {
                ArrayList<String> arrayList = new ArrayList();
                Cursor A74 = r4.A74("SELECT name FROM sqlite_master WHERE type = 'trigger'");
                while (A74.moveToNext()) {
                    try {
                        arrayList.add(A74.getString(0));
                    } catch (Throwable th) {
                        A74.close();
                        throw th;
                    }
                }
                A74.close();
                for (String str : arrayList) {
                    if (str.startsWith("room_fts_content_sync_")) {
                        r4.A2Q(AnonymousClass006.A05("DROP TRIGGER IF EXISTS ", str));
                    }
                }
            }

            @Override // X.AbstractC01240Fs
            public final void A02(AnonymousClass0GQ r2) {
                r2.A2Q("CREATE TABLE IF NOT EXISTS `application` (`app_id` TEXT NOT NULL, `package_name` TEXT, `last_updated_on_backend` INTEGER NOT NULL, `status` INTEGER, `downloaded_bytes` INTEGER NOT NULL, `total_bytes` INTEGER NOT NULL, `app_source` INTEGER, PRIMARY KEY(`app_id`))");
                r2.A2Q("CREATE TABLE IF NOT EXISTS `asset` (`asset_id` TEXT NOT NULL, `app_id` TEXT, `last_updated_on_backend` INTEGER NOT NULL, PRIMARY KEY(`asset_id`))");
                r2.A2Q("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                r2.A2Q("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '30dc8dfd36b329b3ca88d547471dd359')");
            }

            @Override // X.AbstractC01240Fs
            public final void A03(AnonymousClass0GQ r4) {
                r4.A2Q("DROP TABLE IF EXISTS `application`");
                r4.A2Q("DROP TABLE IF EXISTS `asset`");
                List<RoomDatabase.Callback> list = AbstractApplicationDatabase_Impl.this.mCallbacks;
                if (list != null) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        AbstractApplicationDatabase_Impl.this.mCallbacks.get(i);
                    }
                }
            }

            @Override // X.AbstractC01240Fs
            public final void A04(AnonymousClass0GQ r4) {
                List<RoomDatabase.Callback> list = AbstractApplicationDatabase_Impl.this.mCallbacks;
                if (list != null) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        AbstractApplicationDatabase_Impl.this.mCallbacks.get(i);
                    }
                }
            }

            @Override // X.AbstractC01240Fs
            public final void A05(AnonymousClass0GQ r4) {
                AbstractApplicationDatabase_Impl.this.mDatabase = r4;
                AnonymousClass0FX r2 = AbstractApplicationDatabase_Impl.this.mInvalidationTracker;
                synchronized (r2) {
                    if (r2.A0A) {
                        Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                    } else {
                        r4.A2Q("PRAGMA temp_store = MEMORY;");
                        r4.A2Q("PRAGMA recursive_triggers='ON';");
                        r4.A2Q("CREATE TEMP TABLE room_table_modification_log(table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
                        r2.A00(r4);
                        r2.A09 = r4.A1l("UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1 ");
                        r2.A0A = true;
                    }
                }
                List<RoomDatabase.Callback> list = AbstractApplicationDatabase_Impl.this.mCallbacks;
                if (list != null) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        AbstractApplicationDatabase_Impl.this.mCallbacks.get(i);
                    }
                }
            }
        });
        Context context = r5.A00;
        String str = r5.A04;
        if (context != null) {
            return r5.A03.A1u(new AnonymousClass0GT(context, str, r3));
        }
        throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
    }

    @Override // com.oculus.alpenglow.database.AbstractApplicationDatabase
    public final ApplicationDao A08() {
        ApplicationDao applicationDao;
        if (this._applicationDao != null) {
            return this._applicationDao;
        }
        synchronized (this) {
            if (this._applicationDao == null) {
                this._applicationDao = new ApplicationDao_Impl(this);
            }
            applicationDao = this._applicationDao;
        }
        return applicationDao;
    }

    @Override // com.oculus.alpenglow.database.AbstractApplicationDatabase
    public final AssetDao A09() {
        AssetDao assetDao;
        if (this._assetDao != null) {
            return this._assetDao;
        }
        synchronized (this) {
            if (this._assetDao == null) {
                this._assetDao = new AssetDao_Impl(this);
            }
            assetDao = this._assetDao;
        }
        return assetDao;
    }
}
