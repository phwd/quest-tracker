package com.oculus.library.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.facebook.debug.log.BLog;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.library.database.DatabaseModule;
import com.oculus.library.database.contract.LibraryDBContract;
import com.oculus.library.model.App;
import com.oculus.library.utils.app.AppConverter;
import com.oculus.library.utils.app.AppConverterUtilsModule;
import com.oculus.managed.ManagedMode;
import com.oculus.managed.ManagedModule;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import com.oculus.util.constants.OculusConstants;
import com.oculus.util.lru.LinkedHashMapLRUCache;
import com.oculus.util.thread.ThreadModule;
import com.oculus.util.thread.ThreadUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_library_database_LibraryDatabaseSupplier_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_library_sync_LibrarySyncHelper_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_library_utils_app_AppConverter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID"})
@ApplicationScoped
public class LibraryStorage {
    public static final Integer CACHE_MAX_ENTRIES = 20;
    private static final String COL_PACKAGE_NAME = LibraryDBContract.Columns.PACKAGE_NAME.name;
    private static final String COL_USER_ID = LibraryDBContract.Columns.USER_ID.name;
    private static final String TABLE_NAME = "library";
    private static final String TAG = "LibraryStorage";
    private static volatile LibraryStorage _UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_INSTANCE;
    private InjectionContext _UL_mInjectionContext;
    @Inject
    private final Provider<Credentials> mCredentialsProvider;
    @GuardedBy("mEntitlementCacheLock")
    private final Map<String, Optional<App>> mEntitlementCache = new LinkedHashMapLRUCache(CACHE_MAX_ENTRIES.intValue());
    private final Object mEntitlementCacheLock = new Object();

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_library_database_LibraryStorage_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final LibraryStorage _UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (LibraryStorage) UL.factorymap.get(DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final LibraryStorage _UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_INSTANCE == null) {
            synchronized (LibraryStorage.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_INSTANCE = new LibraryStorage(injectorLike.getApplicationInjector());
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_library_database_LibraryStorage_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public LibraryStorage(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(8, injectorLike);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(injectorLike);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized void seed(ArrayList<App> arrayList) {
        synchronized (this.mEntitlementCacheLock) {
            Iterator<App> it = arrayList.iterator();
            while (it.hasNext()) {
                App next = it.next();
                this.mEntitlementCache.put(next.packageName, Optional.of(next));
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean packageInCache(String str) {
        Optional<App> cacheEntry = getCacheEntry(str);
        return cacheEntry != null && cacheEntry.isPresent();
    }

    public synchronized boolean clear() {
        Credentials credentials;
        ((ThreadUtils) FbInjector.lazyInstance(1, ThreadModule.UL_id._UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).assertIsNonUiThread();
        ensureLoggedInOrThrow();
        credentials = this.mCredentialsProvider.get();
        if (((ManagedMode) FbInjector.lazyInstance(6, ManagedModule.UL_id._UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isEnterpriseModeEnabled()) {
            credentials = new Credentials(OculusConstants.DEFAULT_ENTERPRISE_USER_ID, "");
        }
        return clear(credentials.getUserId());
    }

    public synchronized boolean set(Collection<App> collection) {
        boolean save;
        BLog.d(TAG, "set({%d})", Integer.valueOf(collection.size()));
        ((ThreadUtils) FbInjector.lazyInstance(1, ThreadModule.UL_id._UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).assertIsNonUiThread();
        ensureLoggedInOrThrow();
        HashSet hashSet = new HashSet();
        for (App app : collection) {
            hashSet.add(app.packageName);
        }
        Sets.SetView difference = Sets.difference(getAllAppsInDB().keySet(), hashSet);
        if (!difference.isEmpty()) {
            BLog.i(TAG, "Found %d removed entitlements", Integer.valueOf(difference.size()));
            if (!remove(difference)) {
                BLog.w(TAG, "Continuing set() despite failing to remove missing apps");
            }
        }
        boolean isEmpty = difference.isEmpty();
        save = save(collection, isEmpty);
        if (!isEmpty) {
            notify(hashSet);
        }
        return save;
    }

    public synchronized HashMap<String, App> getAllAppsInDB() throws SQLException {
        SQLiteDatabase sQLiteDatabase = ((LibraryDatabaseSupplier) FbInjector.lazyInstance(0, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryDatabaseSupplier_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get();
        String[] requiredColumns = AppConverter.getRequiredColumns();
        String[] optionalColumns = AppConverter.getOptionalColumns();
        String[] strArr = new String[(requiredColumns.length + optionalColumns.length)];
        System.arraycopy(requiredColumns, 0, strArr, 0, requiredColumns.length);
        System.arraycopy(optionalColumns, 0, strArr, requiredColumns.length, optionalColumns.length);
        HashMap<String, App> hashMap = new HashMap<>();
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("library", strArr, null, null, null, null, null);
            if (cursor == null) {
                BLog.w(TAG, "Cannot load database due to null cursor");
                return hashMap;
            } else if (!cursor.moveToFirst()) {
                cursor.close();
                cursor.close();
                return hashMap;
            } else {
                do {
                    App extractNextAppFromCursor = ((AppConverter) FbInjector.lazyInstance(5, AppConverterUtilsModule.UL_id._UL__ULSEP_com_oculus_library_utils_app_AppConverter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).extractNextAppFromCursor(cursor);
                    hashMap.put(extractNextAppFromCursor.packageName, extractNextAppFromCursor);
                } while (cursor.moveToNext());
                cursor.close();
                return hashMap;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public synchronized ImmutableList<App> getAll() {
        return ImmutableList.copyOf((Collection) getAllAppsInDB().values());
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public Optional<App> getCacheEntry(String str) {
        Optional<App> optional;
        synchronized (this.mEntitlementCacheLock) {
            optional = this.mEntitlementCache.get(str);
        }
        return optional;
    }

    @Nullable
    public App get(String str) {
        return get(str, true);
    }

    @Nullable
    public App get(String str, boolean z) {
        Optional<App> cacheEntry = getCacheEntry(str);
        return cacheEntry != null ? cacheEntry.orElse(null) : getAppSlowPath(str, z);
    }

    @Nullable
    private synchronized App getAppSlowPath(String str, boolean z) {
        Optional<App> cacheEntry = getCacheEntry(str);
        if (cacheEntry != null) {
            return cacheEntry.orElse(null);
        }
        App appFromDB = getAppFromDB(str);
        if (z) {
            synchronized (this.mEntitlementCacheLock) {
                this.mEntitlementCache.put(str, Optional.ofNullable(appFromDB));
            }
        }
        return appFromDB;
    }

    public synchronized boolean save(App.Editor editor) {
        return save(((AppConverter) FbInjector.lazyInstance(5, AppConverterUtilsModule.UL_id._UL__ULSEP_com_oculus_library_utils_app_AppConverter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).toContentValues(editor));
    }

    public synchronized boolean save(ContentValues contentValues) {
        ((ThreadUtils) FbInjector.lazyInstance(1, ThreadModule.UL_id._UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).assertIsNonUiThread();
        ensureLoggedInOrThrow();
        if (!contentValues.containsKey(COL_PACKAGE_NAME)) {
            BLog.w(TAG, "Invalid content values received, missing package name. Ignoring.");
            return false;
        }
        String asString = contentValues.getAsString(COL_PACKAGE_NAME);
        Optional<App> cacheEntry = getCacheEntry(asString);
        App orElse = cacheEntry != null ? cacheEntry.orElse(null) : getAppFromDB(asString);
        if (orElse == null) {
            BLog.e(TAG, "Attempting to edit app that is not entitled");
            return false;
        }
        App fromContentValuesUpdate = ((AppConverter) FbInjector.lazyInstance(5, AppConverterUtilsModule.UL_id._UL__ULSEP_com_oculus_library_utils_app_AppConverter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).fromContentValuesUpdate(orElse, contentValues);
        if (fromContentValuesUpdate.status != orElse.status) {
            BLog.i(TAG, "status changing for %s from %s to %s", fromContentValuesUpdate.packageName, orElse.status.name(), fromContentValuesUpdate.status.name());
        }
        return save(Collections.singleton(fromContentValuesUpdate), true);
    }

    private void ensureLoggedInOrThrow() {
        Credentials credentials = this.mCredentialsProvider.get();
        if (((ManagedMode) FbInjector.lazyInstance(6, ManagedModule.UL_id._UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isEnterpriseModeEnabled()) {
            return;
        }
        if (credentials == null || TextUtils.isEmpty(credentials.getUserId())) {
            throw new IllegalStateException("Invalid credentials or user id.");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x007f  */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.oculus.library.model.App getAppFromDB(java.lang.String r13) throws android.database.SQLException {
        /*
        // Method dump skipped, instructions count: 134
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.library.database.LibraryStorage.getAppFromDB(java.lang.String):com.oculus.library.model.App");
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x0286  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x00ff A[EDGE_INSN: B:108:0x00ff->B:36:0x00ff ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x013b A[Catch:{ SQLException -> 0x0259, all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x015d A[Catch:{ SQLException -> 0x0259, all -> 0x0257 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01bd A[Catch:{ SQLException -> 0x0259, all -> 0x0257 }, LOOP:2: B:58:0x01b7->B:60:0x01bd, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01f1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x027d  */
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean save(java.util.Collection<com.oculus.library.model.App> r19, boolean r20) {
        /*
        // Method dump skipped, instructions count: 655
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.library.database.LibraryStorage.save(java.util.Collection, boolean):boolean");
    }

    private void notify(Set<String> set) {
        Uri uri;
        if (set.size() == 1) {
            uri = OCMSLibraryContract.uriForPackage(set.iterator().next());
        } else {
            uri = OCMSLibraryContract.uriForAllPackages();
        }
        BLog.i(TAG, "notifying: %s", uri.toString());
        ((Context) FbInjector.lazyInstance(4, BundledAndroidModule.UL_id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getContentResolver().notifyChange(uri, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00e3  */
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean remove(java.util.Set<java.lang.String> r12) {
        /*
        // Method dump skipped, instructions count: 236
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.library.database.LibraryStorage.remove(java.util.Set):boolean");
    }

    private synchronized boolean clear(String str) {
        try {
            int delete = ((LibraryDatabaseSupplier) FbInjector.lazyInstance(0, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryDatabaseSupplier_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get().delete("library", null, null);
            synchronized (this.mEntitlementCacheLock) {
                this.mEntitlementCache.clear();
            }
            BLog.i(TAG, "Successfully cleared %d entitlements", Integer.valueOf(delete));
        } catch (SQLException e) {
            BLog.e(TAG, e, "Exception occurred when trying to clear entitlements");
            ((IErrorReporter) FbInjector.lazyInstance(2, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(TAG, "Error clearing entitlements", e);
            return false;
        }
        return true;
    }
}