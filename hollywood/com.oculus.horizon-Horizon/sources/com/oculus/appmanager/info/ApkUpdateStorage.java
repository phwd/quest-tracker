package com.oculus.appmanager.info;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0Pi;
import X.AnonymousClass0QC;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.database.ApkUpdateDatabaseSupplier;
import com.oculus.appmanager.info.database.ApkUpdateExtrasManager;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.common.init.INeedInit;
import com.oculus.debug.DebugMode;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.extras.Extras;
import com.oculus.extras.ExtrasBuilder;
import com.oculus.util.thread.ThreadUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

@Dependencies({"_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateExtrasManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_ApkUpdateInfoDispatcher_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_ApkUpdateInfoProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID"})
@ApplicationScoped
@VisibleForTesting
public class ApkUpdateStorage implements INeedInit {
    public static final String COL_ACCESS_TOKEN = ApkUpdateInfoContract.ApkUpdateTable.Columns.ACCESS_TOKEN.name;
    public static final String COL_BASE_VERSION = ApkUpdateInfoContract.ApkUpdateTable.Columns.BASE_VERSION.name;
    public static final String COL_CHECKSUM_HASH = ApkUpdateInfoContract.ApkUpdateTable.Columns.DOWNLOAD_CHECKSUM.name;
    public static final String COL_CHECKSUM_HASH_ALG = ApkUpdateInfoContract.ApkUpdateTable.Columns.DOWNLOAD_CHECKSUM_ALG.name;
    public static final String COL_DEPENDENCIES = ApkUpdateInfoContract.ApkUpdateTable.Columns.DEPENDENCIES.name;
    public static final String COL_DISPLAY_TITLE = ApkUpdateInfoContract.ApkUpdateTable.Columns.DISPLAY_TITLE.name;
    public static final String COL_DOWNLOAD_SIZE = ApkUpdateInfoContract.ApkUpdateTable.Columns.DOWNLOAD_SIZE.name;
    public static final String COL_DOWNLOAD_URL = ApkUpdateInfoContract.ApkUpdateTable.Columns.DOWNLOAD_URL.name;
    public static final String COL_EXTERNAL_SIGNATURES = ApkUpdateInfoContract.ApkUpdateTable.Columns.EXTERNAL_SIGNATURES.name;
    public static final String COL_IDENTIFIER = ApkUpdateInfoContract.ApkUpdateTable.Columns.IDENTIFIER.name;
    public static final String COL_IS_UPDATE = ApkUpdateInfoContract.ApkUpdateTable.Columns.IS_UPDATE.name;
    public static final String COL_ITEM_ID = ApkUpdateInfoContract.ApkUpdateTable.Columns.ITEM_ID.name;
    public static final String COL_REQUESTING_PACKAGE = ApkUpdateInfoContract.ApkUpdateTable.Columns.REQUESTING_PACKAGE.name;
    public static final String COL_REQUEST_HEADERS = ApkUpdateInfoContract.ApkUpdateTable.Columns.REQUEST_HEADERS.name;
    public static final String COL_REQUEST_ORIGIN = ApkUpdateInfoContract.ApkUpdateTable.Columns.REQUEST_ORIGIN.name;
    public static final String COL_SIGNATURE = ApkUpdateInfoContract.ApkUpdateTable.Columns.SIGNATURE.name;
    public static final String COL_TARGET_VERSION = ApkUpdateInfoContract.ApkUpdateTable.Columns.TARGET_VERSION.name;
    public static final String COL_UPDATE_ID = ApkUpdateInfoContract.ApkUpdateTable.Columns.ID.name;
    public static final String COL_UPDATE_TYPE = ApkUpdateInfoContract.ApkUpdateTable.Columns.UPDATE_TYPE.name;
    public static final String LIST_DELIMITER = ":";
    public static final String TABLE_NAME = "apk_updates";
    public static volatile ApkUpdateStorage _UL__ULSEP_com_oculus_appmanager_info_ApkUpdateStorage_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @GuardedBy("this")
    public final Map<Long, ApkUpdateInfo> mCachedUpdates = Collections.synchronizedMap(new HashMap());
    @Inject
    @Eager
    public final ApkUpdateDatabaseSupplier mDatabaseSupplier;
    @Inject
    @Eager
    public final ApkUpdateExtrasManager mExtrasManager;
    @Inject
    @Eager
    public final ApkUpdateInfoDispatcher mListenerDispatcher;
    @GuardedBy("this")
    public boolean mLoadedUpdatesFromDb = false;
    @Inject
    @Eager
    public final ThreadUtils mThreadUtils;
    @Inject
    @Eager
    public final ApkUpdateInfoProvider mUpdateInfoProvider;

    @AutoGeneratedFactoryMethod
    public static final ApkUpdateStorage A00(AbstractC06640p5 r4) {
        if (_UL__ULSEP_com_oculus_appmanager_info_ApkUpdateStorage_ULSEP_INSTANCE == null) {
            synchronized (ApkUpdateStorage.class) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_com_oculus_appmanager_info_ApkUpdateStorage_ULSEP_INSTANCE, r4);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_oculus_appmanager_info_ApkUpdateStorage_ULSEP_INSTANCE = new ApkUpdateStorage(r4.getApplicationInjector());
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_appmanager_info_ApkUpdateStorage_ULSEP_INSTANCE;
    }

    @VisibleForTesting
    public final synchronized Map<Long, ApkUpdateInfo> A01() {
        Map<Long, ApkUpdateInfo> map;
        Extras extras;
        ImmutableList immutableList;
        if (this.mLoadedUpdatesFromDb) {
            map = this.mCachedUpdates;
        } else {
            Cursor query = this.mDatabaseSupplier.A03().query("apk_updates", new String[]{COL_UPDATE_ID, COL_ITEM_ID, COL_BASE_VERSION, COL_TARGET_VERSION, COL_ACCESS_TOKEN, COL_IDENTIFIER, COL_DOWNLOAD_URL, COL_DOWNLOAD_SIZE, COL_DISPLAY_TITLE, COL_UPDATE_TYPE, COL_IS_UPDATE, COL_DEPENDENCIES, COL_REQUESTING_PACKAGE, COL_REQUEST_ORIGIN, COL_SIGNATURE, COL_EXTERNAL_SIGNATURES, COL_CHECKSUM_HASH, COL_CHECKSUM_HASH_ALG, COL_REQUEST_HEADERS}, null, null, null, null, null);
            if (query == null) {
                this.mLoadedUpdatesFromDb = true;
                map = this.mCachedUpdates;
            } else if (!query.moveToFirst()) {
                query.close();
                this.mLoadedUpdatesFromDb = true;
                map = this.mCachedUpdates;
            } else {
                int columnIndex = query.getColumnIndex(COL_UPDATE_ID);
                int columnIndex2 = query.getColumnIndex(COL_ITEM_ID);
                int columnIndex3 = query.getColumnIndex(COL_BASE_VERSION);
                int columnIndex4 = query.getColumnIndex(COL_TARGET_VERSION);
                int columnIndex5 = query.getColumnIndex(COL_ACCESS_TOKEN);
                int columnIndex6 = query.getColumnIndex(COL_IDENTIFIER);
                int columnIndex7 = query.getColumnIndex(COL_DOWNLOAD_URL);
                int columnIndex8 = query.getColumnIndex(COL_DOWNLOAD_SIZE);
                int columnIndex9 = query.getColumnIndex(COL_DISPLAY_TITLE);
                int columnIndex10 = query.getColumnIndex(COL_UPDATE_TYPE);
                int columnIndex11 = query.getColumnIndex(COL_IS_UPDATE);
                int columnIndex12 = query.getColumnIndex(COL_DEPENDENCIES);
                int columnIndex13 = query.getColumnIndex(COL_REQUESTING_PACKAGE);
                int columnIndex14 = query.getColumnIndex(COL_REQUEST_ORIGIN);
                int columnIndex15 = query.getColumnIndex(COL_SIGNATURE);
                int columnIndex16 = query.getColumnIndex(COL_EXTERNAL_SIGNATURES);
                int columnIndex17 = query.getColumnIndex(COL_CHECKSUM_HASH);
                int columnIndex18 = query.getColumnIndex(COL_CHECKSUM_HASH_ALG);
                int columnIndex19 = query.getColumnIndex(COL_REQUEST_HEADERS);
                while (!query.isAfterLast()) {
                    long j = query.getLong(columnIndex);
                    Map<Long, ApkUpdateInfo> map2 = this.mCachedUpdates;
                    Long valueOf = Long.valueOf(j);
                    if (!map2.containsKey(valueOf)) {
                        long j2 = query.getLong(columnIndex3);
                        long j3 = query.getLong(columnIndex4);
                        String string = query.getString(columnIndex2);
                        String string2 = query.getString(columnIndex5);
                        String string3 = query.getString(columnIndex6);
                        String string4 = query.getString(columnIndex7);
                        long j4 = query.getLong(columnIndex8);
                        String string5 = query.getString(columnIndex9);
                        ApkUpdateInfoContract.UpdateType fromInt = ApkUpdateInfoContract.UpdateType.fromInt(query.getInt(columnIndex10));
                        boolean z = false;
                        if (query.getInt(columnIndex11) == 1) {
                            z = true;
                        }
                        String string6 = query.getString(columnIndex12);
                        String string7 = query.getString(columnIndex13);
                        RequestOrigin valueOf2 = RequestOrigin.valueOf(query.getString(columnIndex14));
                        String string8 = query.getString(columnIndex15);
                        String string9 = query.getString(columnIndex16);
                        String string10 = query.getString(columnIndex17);
                        String string11 = query.getString(columnIndex18);
                        String string12 = query.getString(columnIndex19);
                        ApkUpdateExtrasManager apkUpdateExtrasManager = this.mExtrasManager;
                        Cursor query2 = apkUpdateExtrasManager.mDatabaseSupplier.A03().query(apkUpdateExtrasManager.mTableName, new String[]{apkUpdateExtrasManager.mKeyColumnName, apkUpdateExtrasManager.mValueColumnName}, AnonymousClass006.A05(apkUpdateExtrasManager.mIdColumnName, " = ?"), new String[]{Long.toString(j)}, null, null, null);
                        if (query2 == null) {
                            extras = new Extras(new ExtrasBuilder().mData);
                        } else if (!query2.moveToFirst()) {
                            query2.close();
                            extras = new Extras(new ExtrasBuilder().mData);
                        } else {
                            HashMap hashMap = new HashMap();
                            int columnIndex20 = query2.getColumnIndex(apkUpdateExtrasManager.mKeyColumnName);
                            int columnIndex21 = query2.getColumnIndex(apkUpdateExtrasManager.mValueColumnName);
                            while (!query2.isAfterLast()) {
                                String string13 = query2.getString(columnIndex20);
                                String string14 = query2.getString(columnIndex21);
                                if (!(string13 == null || string14 == null)) {
                                    hashMap.put(string13, string14);
                                }
                                query2.moveToNext();
                            }
                            query2.close();
                            extras = new Extras(hashMap);
                        }
                        if (!TextUtils.isEmpty(string6)) {
                            String[] split = string6.split(":");
                            int length = split.length;
                            ArrayList arrayList = new ArrayList(length);
                            for (String str : split) {
                                arrayList.add(Long.valueOf(Long.parseLong(str)));
                            }
                            immutableList = ImmutableList.A0C(arrayList);
                        } else {
                            immutableList = ImmutableList.of();
                        }
                        ImmutableMap.Builder A01 = ImmutableMap.A01();
                        String[] split2 = TextUtils.split(string12, ":");
                        for (int i = 0; i < (split2.length >> 1); i++) {
                            int i2 = i << 1;
                            A01.put(split2[i2], split2[i2 + 1]);
                        }
                        ImmutableMap build = A01.build();
                        ApkUpdateInfoProvider apkUpdateInfoProvider = this.mUpdateInfoProvider;
                        ApkUpdateInfo apkUpdateInfo = new ApkUpdateInfo(j, string3, fromInt, j2, j3, z, j4, string4, valueOf2, immutableList, string2, string, string10, string11, string5, string7, string8, string9, extras, build, ApkUpdateInfoDispatcher.A00(apkUpdateInfoProvider), ApkUpdateExtrasManager.A00(apkUpdateInfoProvider), DebugMode.A00(apkUpdateInfoProvider), InterfacesModule.A00(apkUpdateInfoProvider));
                        if (!this.mCachedUpdates.containsKey(valueOf)) {
                            this.mCachedUpdates.put(valueOf, apkUpdateInfo);
                            ApkUpdateInfoDispatcher apkUpdateInfoDispatcher = this.mListenerDispatcher;
                            Iterator it = apkUpdateInfoDispatcher.A02().iterator();
                            while (it.hasNext()) {
                                it.next();
                                try {
                                    throw null;
                                } catch (Throwable th) {
                                    apkUpdateInfoDispatcher.mErrorReporter.A97(ApkUpdateInfoDispatcher.ERROR_CATEGORY, "exception in update listener", th);
                                }
                            }
                        }
                    }
                    query.moveToNext();
                }
                query.close();
                this.mLoadedUpdatesFromDb = true;
                map = this.mCachedUpdates;
            }
        }
        return map;
    }

    @Inject
    public ApkUpdateStorage(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mDatabaseSupplier = ApkUpdateDatabaseSupplier.A00(r3);
        this.mExtrasManager = ApkUpdateExtrasManager.A00(r3);
        this.mListenerDispatcher = ApkUpdateInfoDispatcher.A00(r3);
        this.mUpdateInfoProvider = ApkUpdateInfo.A00(r3);
        this.mThreadUtils = ThreadUtils.A01(r3);
    }

    @Override // com.oculus.common.init.INeedInit
    public final void init() {
        OculusThreadExecutor.A00().execute(new Runnable() {
            /* class com.oculus.appmanager.info.ApkUpdateStorage.AnonymousClass1 */

            public final void run() {
                ApkUpdateStorage.this.A01();
            }
        });
    }
}
