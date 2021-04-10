package com.oculus.defaultapps;

import X.AbstractC06640p5;
import X.AnonymousClass0D3;
import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.C003108z;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableMap;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.common.init.INeedInit;
import com.oculus.defaultapps.graphql.DefaultAppsGraphQLQuery;
import com.oculus.defaultapps.net.DefaultAppsConfigResponse;
import com.oculus.defaultapps.net.DefaultAppsRequest;
import com.oculus.defaultapps.net.DefaultAppsResponse;
import com.oculus.device.DeviceType;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.horizon.api.common.Item;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import com.oculus.nuxpreferencesapi.OVRNuxPreferences;
import com.oculus.os.SettingsManager;
import com.oculus.provider.OculusContent;
import com.oculus.util.constants.OculusConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_defaultapps_DefaultAppsPrefs_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_defaultapps_DefaultAppsSetupManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_defaultapps_DefaultAppsMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID"})
public class DefaultAppsSetupListener implements INeedInit, LoginHandler {
    public static final String ERROR_CATEGORY_FETCH_HIGH_PRI_LIST_EXCEPTION = "fetch_high_pri_wait_exception";
    public static final String ERROR_CATEGORY_FETCH_HIGH_PRI_LIST_MALFORMED_RESPONSE = "fetch_high_pri_wait_malformed";
    public static final String ERROR_CATEGORY_FETCH_WAIT_TIME_MALFORMED_RESPONSE = "fetch_high_pri_wait_malformed";
    public static final int MAX_FETCH_RETRIES = 5;
    public static final String TAG = "DefaultAppsSetupListener";
    public static final AtomicBoolean isDefaultAppsSetupInProgress = new AtomicBoolean(false);
    public AnonymousClass0QC _UL_mInjectionContext;
    public int fetchAppListRetries = 0;
    public int fetchWaitTimeRetries = 0;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Inject
    public final Provider<Credentials> mCredentialsProvider;
    @Inject
    @Eager
    public final DefaultAppsMethods mDefaultAppsMethods;
    @Inject
    @Eager
    public final DefaultAppsSetupManager mDefaultAppsSetupManager;
    @Inject
    @Eager
    public final DefaultAppsPrefs mPrefs;

    @Override // com.oculus.auth.handler.LoginHandler
    public final AnonymousClass0DC<Void> afterLoginAsync() {
        return AnonymousClass0DC.A04(null);
    }

    public final void A00() {
        String string;
        int i;
        String string2;
        String string3;
        long j;
        String string4;
        String string5;
        OVRNuxPreferences.ClientPreferenceData clientPreferenceData;
        if (this.mPrefs.mPrefs.getBoolean(DefaultAppsPrefs.HIGH_PRIORITY_APPS_FETCH_FAILURE, false)) {
            this.mPrefs.mPrefs.edit().putBoolean(DefaultAppsPrefs.HIGH_PRIORITY_APPS_FETCH_FAILURE, false).apply();
            this.mDefaultAppsSetupManager.A03(OVRNuxPreferences.Status.UNSET);
            this.mPrefs.A02(false);
        }
        if (!this.mPrefs.mPrefs.getBoolean(DefaultAppsPrefs.SETUP_COMPLETE, false) && this.mPrefs.mPrefs.getBoolean(DefaultAppsPrefs.NUX_OTA_DOWNLOAD_HIGH_PRI_APPS_BROADCAST, false) && isDefaultAppsSetupInProgress.compareAndSet(false, true)) {
            if (this.mPrefs.mPrefs.getBoolean(DefaultAppsPrefs.HIGH_PRIORITY_APPS_FETCH_FAILURE, false)) {
                this.mDefaultAppsSetupManager.A03(OVRNuxPreferences.Status.UNSET);
                this.mPrefs.A02(false);
                this.mPrefs.mPrefs.edit().putBoolean(DefaultAppsPrefs.HIGH_PRIORITY_APPS_FETCH_FAILURE, false).apply();
            }
            ArrayList arrayList = new ArrayList();
            String str = null;
            Cursor query = this.mContext.getContentResolver().query(OculusContent.Profile.CONTENT_URI, null, null, null, null);
            if (query == null) {
                clientPreferenceData = new OVRNuxPreferences.ClientPreferenceData(null, false, false, false, false, false, false, false, false, false, false, false, false, false, false, OVRNuxPreferences.Status.UNSET, 0, null, 0, null, false, false, null, false, null, null);
            } else {
                query.moveToFirst();
                int columnIndex = query.getColumnIndex(OculusContent.Profile.DEFAULT_BROWSER);
                if (columnIndex >= 0) {
                    str = query.getString(columnIndex);
                }
                int columnIndex2 = query.getColumnIndex(OculusContent.Profile.NUX_RESULT);
                if (columnIndex2 < 0) {
                    string = null;
                } else {
                    string = query.getString(columnIndex2);
                }
                int columnIndex3 = query.getColumnIndex(OculusContent.Profile.NUX_SEEN_COUNT);
                if (columnIndex3 < 0) {
                    i = 0;
                } else {
                    i = query.getInt(columnIndex3);
                }
                int columnIndex4 = query.getColumnIndex(OculusContent.Profile.NUX_STATE);
                if (columnIndex4 < 0) {
                    string2 = null;
                } else {
                    string2 = query.getString(columnIndex4);
                }
                int columnIndex5 = query.getColumnIndex("id");
                if (columnIndex5 < 0) {
                    string3 = null;
                } else {
                    string3 = query.getString(columnIndex5);
                }
                boolean A00 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_CONFIRM_QUIT));
                boolean A002 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_CONFIRM_QUIT_NOTIF));
                boolean A003 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_FOCUS));
                boolean A004 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_INTERNET_BROWSER_PROMPT));
                boolean A005 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_TUTORIAL_PROMPT));
                boolean A006 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_PARTY_CALLS_NUX));
                boolean A007 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_SAVED_PROMPT));
                boolean A008 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_LONG_HSW));
                boolean A009 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_OPTED_OUT_HSW));
                boolean A0010 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_OPTED_OUT_OF_MALIBU_RECENTER));
                int columnIndex6 = query.getColumnIndex(OculusContent.Profile.HIGH_PRIORITY_APPS_DOWNLOAD_STATUS);
                OVRNuxPreferences.Status status = OVRNuxPreferences.Status.UNSET;
                if (columnIndex6 >= 0) {
                    status = OVRNuxPreferences.Status.values()[query.getInt(columnIndex6)];
                }
                int columnIndex7 = query.getColumnIndex(OculusContent.Profile.HIGH_PRIORITY_APPS_DOWNLOAD_WAITTIME);
                if (columnIndex7 < 0) {
                    j = 0;
                } else {
                    j = query.getLong(columnIndex7);
                }
                boolean A0011 = OVRNuxPreferences.A00(query, query.getColumnIndex("has_docked"));
                boolean A0012 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_HAND_TRACKING_NUX));
                boolean A0013 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_FINISHED_FULL_VR_NUX));
                boolean A0014 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_FINISHED_NUX));
                boolean A0015 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_NUX));
                boolean A0016 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_FINISHED_IPD_ADJUST));
                boolean A0017 = OVRNuxPreferences.A00(query, query.getColumnIndex(OculusContent.Profile.HAS_FINISHED_MONTEREY_NUX));
                int columnIndex8 = query.getColumnIndex(OculusContent.Profile.ROLLOUT_TOKEN);
                if (columnIndex8 < 0) {
                    string4 = null;
                } else {
                    string4 = query.getString(columnIndex8);
                }
                int columnIndex9 = query.getColumnIndex(OculusContent.Profile.HIGH_PRIORITY_APP_DOWNLOAD_PACKAGES);
                if (columnIndex9 < 0) {
                    string5 = null;
                } else {
                    string5 = query.getString(columnIndex9);
                }
                query.close();
                clientPreferenceData = new OVRNuxPreferences.ClientPreferenceData(str, A0012, A0013, A0014, A00, A002, A003, A004, A0015, A005, A006, A007, A008, A009, A0010, status, j, string, i, string2, A0016, A0017, string3, A0011, string4, string5);
            }
            if (0 == clientPreferenceData.highPriorityAppsDownloadWaitTime) {
                final AnonymousClass0D3 r5 = new AnonymousClass0D3();
                r5.A00 = (T) false;
                arrayList.add(AnonymousClass0DC.A04(null).A0E(new Callable<Boolean>() {
                    /* class com.oculus.defaultapps.DefaultAppsSetupListener.AnonymousClass6 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0016, code lost:
                        if (r2 > 5) goto L_0x0018;
                     */
                    @Override // java.util.concurrent.Callable
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Boolean call() throws java.lang.Exception {
                        /*
                            r3 = this;
                            X.0D3 r0 = r5
                            T r0 = r0.A00
                            java.lang.Boolean r0 = (java.lang.Boolean) r0
                            boolean r0 = r0.booleanValue()
                            if (r0 != 0) goto L_0x0018
                            com.oculus.defaultapps.DefaultAppsSetupListener r1 = com.oculus.defaultapps.DefaultAppsSetupListener.this
                            int r2 = r1.fetchWaitTimeRetries
                            int r0 = r2 + 1
                            r1.fetchWaitTimeRetries = r0
                            r1 = 5
                            r0 = 1
                            if (r2 <= r1) goto L_0x0019
                        L_0x0018:
                            r0 = 0
                        L_0x0019:
                            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.oculus.defaultapps.DefaultAppsSetupListener.AnonymousClass6.call():java.lang.Object");
                    }
                }, new AnonymousClass0D4<Void, AnonymousClass0DC<Void>>() {
                    /* class com.oculus.defaultapps.DefaultAppsSetupListener.AnonymousClass7 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                    @Override // X.AnonymousClass0D4
                    public final AnonymousClass0DC<Void> then(AnonymousClass0DC<Void> r10) throws Exception {
                        DefaultAppsConfigResponse defaultAppsConfigResponse;
                        DefaultAppsConfigResponse.DefaultAppsConfig defaultAppsConfig;
                        try {
                            DefaultAppsSetupListener defaultAppsSetupListener = DefaultAppsSetupListener.this;
                            if (DeviceType.current() == DeviceType.Monterey) {
                                defaultAppsConfigResponse = new DefaultAppsConfigResponse();
                            } else {
                                defaultAppsConfigResponse = defaultAppsSetupListener.mDefaultAppsMethods.mMethods.getDefaultAppsConfig(DefaultAppsGraphQLQuery.DEFAULT_APPS_CONFIG_QUERY, GraphQLParamsHelper.GSON_CONVERTER.A06(ImmutableMap.A02("hmd", "PACIFIC")));
                            }
                            if (DeviceType.current() == DeviceType.Pacific) {
                                if (defaultAppsConfigResponse == null || (defaultAppsConfig = defaultAppsConfigResponse.default_apps_nux_config) == null) {
                                    AnonymousClass0NO.A08(DefaultAppsSetupListener.TAG, "Got unexpected result from high pri download wait time");
                                    ((IErrorReporter) AnonymousClass0J2.A03(0, 428, DefaultAppsSetupListener.this._UL_mInjectionContext)).A96("fetch_high_pri_wait_malformed", "Got unexpected result from high pri download wait time");
                                    return null;
                                }
                                long millis = TimeUnit.SECONDS.toMillis((long) defaultAppsConfig.hi_pri_apps_wait_timeout);
                                ContentResolver contentResolver = DefaultAppsSetupListener.this.mContext.getContentResolver();
                                Uri uri = OculusContent.Profile.CONTENT_URI;
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(OculusContent.Profile.HIGH_PRIORITY_APPS_DOWNLOAD_WAITTIME, Long.valueOf(millis));
                                contentResolver.update(uri, contentValues, null, null);
                            }
                            r5.A00 = (T) true;
                            return null;
                        } catch (Exception e) {
                            AnonymousClass0NO.A0B(DefaultAppsSetupListener.TAG, "Failed to fetch the high-pri apps download wait time", e);
                            return null;
                        }
                    }
                }).A09(new AnonymousClass0D4<Void, Void>() {
                    /* class com.oculus.defaultapps.DefaultAppsSetupListener.AnonymousClass5 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                    @Override // X.AnonymousClass0D4
                    public final Void then(AnonymousClass0DC<Void> r3) throws Exception {
                        if (r5.A00.booleanValue()) {
                            return null;
                        }
                        DefaultAppsSetupListener.this.mDefaultAppsSetupManager.A03(OVRNuxPreferences.Status.FAILED);
                        DefaultAppsSetupListener.this.mPrefs.A02(true);
                        throw new IllegalStateException("failed to fetch high pri app wait time");
                    }
                }));
            }
            if (this.mPrefs.mPrefs.getStringSet(DefaultAppsPrefs.MODIFIABLE_HIGH_PRI_APP_LIST, null) == null) {
                final AnonymousClass0D3 r52 = new AnonymousClass0D3();
                r52.A00 = (T) false;
                arrayList.add(AnonymousClass0DC.A04(null).A0E(new Callable<Boolean>() {
                    /* class com.oculus.defaultapps.DefaultAppsSetupListener.AnonymousClass3 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0016, code lost:
                        if (r2 > 5) goto L_0x0018;
                     */
                    @Override // java.util.concurrent.Callable
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Boolean call() throws java.lang.Exception {
                        /*
                            r3 = this;
                            X.0D3 r0 = r5
                            T r0 = r0.A00
                            java.lang.Boolean r0 = (java.lang.Boolean) r0
                            boolean r0 = r0.booleanValue()
                            if (r0 != 0) goto L_0x0018
                            com.oculus.defaultapps.DefaultAppsSetupListener r1 = com.oculus.defaultapps.DefaultAppsSetupListener.this
                            int r2 = r1.fetchAppListRetries
                            int r0 = r2 + 1
                            r1.fetchAppListRetries = r0
                            r1 = 5
                            r0 = 1
                            if (r2 <= r1) goto L_0x0019
                        L_0x0018:
                            r0 = 0
                        L_0x0019:
                            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.oculus.defaultapps.DefaultAppsSetupListener.AnonymousClass3.call():java.lang.Object");
                    }
                }, new AnonymousClass0D4<Void, AnonymousClass0DC<Void>>() {
                    /* class com.oculus.defaultapps.DefaultAppsSetupListener.AnonymousClass4 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                    @Override // X.AnonymousClass0D4
                    public final AnonymousClass0DC<Void> then(AnonymousClass0DC<Void> r9) throws Exception {
                        String str;
                        String str2;
                        try {
                            DefaultAppsResponse A00 = DefaultAppsSetupListener.this.mDefaultAppsMethods.A00(new DefaultAppsRequest(DeviceType.current().hmdType, true));
                            if (A00 != null) {
                                ArrayList arrayList = new ArrayList();
                                for (Item item : A00.node.default_applications) {
                                    arrayList.add(item.getPackageName());
                                }
                                if (DeviceType.is6DOF()) {
                                    Item item2 = A00.node.default_environment;
                                    String str3 = "";
                                    if (item2 != null) {
                                        str = item2.getPackageName();
                                    } else {
                                        str = str3;
                                    }
                                    Item item3 = A00.node.default_environment;
                                    if (!(item3 == null || (str2 = item3.environment_path) == null)) {
                                        str3 = str2;
                                    }
                                    ((SettingsManager) AnonymousClass0J2.A03(1, 101, DefaultAppsSetupListener.this._UL_mInjectionContext)).setString(OculusConstants.SETTINGS_KEY_ENVIRONMENT_DEFAULT, str3);
                                    if (!TextUtils.isEmpty(str)) {
                                        arrayList.add(str);
                                        DefaultAppsSetupListener.this.mPrefs.mPrefs.edit().putString(DefaultAppsPrefs.DEFAULT_ENVIRONMENT_PACKAGE_NAME, str).apply();
                                    }
                                }
                                arrayList.size();
                                DefaultAppsSetupListener.this.mPrefs.mPrefs.edit().putBoolean(DefaultAppsPrefs.HIGH_PRIORITY_APPS_FETCH, true).apply();
                                DefaultAppsSetupListener.this.mPrefs.A01(arrayList);
                                r52.A00 = (T) true;
                                return null;
                            }
                            AnonymousClass0NO.A08(DefaultAppsSetupListener.TAG, "Got unexpected result from high pri download app list");
                            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, DefaultAppsSetupListener.this._UL_mInjectionContext)).A96("fetch_high_pri_wait_malformed", "Got unexpected result from high pri download app list");
                            return null;
                        } catch (Exception e) {
                            AnonymousClass0NO.A0B(DefaultAppsSetupListener.TAG, "Failed to fetch the high-pri app list", e);
                            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, DefaultAppsSetupListener.this._UL_mInjectionContext)).A97(DefaultAppsSetupListener.ERROR_CATEGORY_FETCH_HIGH_PRI_LIST_EXCEPTION, "Failed to fetch the high-pri app list", e);
                            return null;
                        }
                    }
                }).A09(new AnonymousClass0D4<Void, Void>() {
                    /* class com.oculus.defaultapps.DefaultAppsSetupListener.AnonymousClass2 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                    @Override // X.AnonymousClass0D4
                    public final Void then(AnonymousClass0DC<Void> r4) throws Exception {
                        if (!r4.A0K() && r52.A00.booleanValue()) {
                            return null;
                        }
                        DefaultAppsSetupListener.this.mDefaultAppsSetupManager.A03(OVRNuxPreferences.Status.FAILED);
                        DefaultAppsSetupListener.this.mPrefs.A02(true);
                        DefaultAppsSetupListener.this.mPrefs.mPrefs.edit().putBoolean(DefaultAppsPrefs.HIGH_PRIORITY_APPS_FETCH_FAILURE, true).apply();
                        throw new IllegalStateException("failed to fetch high pri app list", r4.A0F());
                    }
                }));
            }
            AnonymousClass0DC.A05(arrayList).A09(new AnonymousClass0D4<Void, Void>() {
                /* class com.oculus.defaultapps.DefaultAppsSetupListener.AnonymousClass8 */

                /* Return type fixed from 'java.lang.Object' to match base method */
                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                @Override // X.AnonymousClass0D4
                public final Void then(AnonymousClass0DC<Void> r10) throws Exception {
                    boolean z = false;
                    if (r10.A0K()) {
                        AnonymousClass0NO.A0B(DefaultAppsSetupListener.TAG, "Unable to continue setup due to failure in pre-flight tasks", r10.A0F());
                        DefaultAppsSetupListener.isDefaultAppsSetupInProgress.compareAndSet(true, false);
                        return null;
                    } else if (DeviceType.current() == DeviceType.Pacific) {
                        DefaultAppsSetupListener.this.mDefaultAppsSetupManager.A00();
                        return null;
                    } else {
                        boolean z2 = DefaultAppsSetupListener.this.mPrefs.mPrefs.getBoolean(DefaultAppsPrefs.PRELOAD_INSTALLER_COMPLETE, false);
                        Set<String> stringSet = DefaultAppsSetupListener.this.mPrefs.mPrefs.getStringSet(DefaultAppsPrefs.MODIFIABLE_HIGH_PRI_APP_LIST, null);
                        if (stringSet == null) {
                            AnonymousClass0NO.A09(DefaultAppsSetupListener.TAG, "The high Priority Apps List is null after the fetch is complete!");
                            stringSet = new HashSet<>();
                        }
                        SharedPreferences sharedPreferences = DefaultAppsSetupListener.this.mPrefs.mPrefs;
                        Set<String> set = Collections.EMPTY_SET;
                        Set<String> stringSet2 = sharedPreferences.getStringSet(DefaultAppsPrefs.COMPLETED_PRELOAD_APP_LIST, set);
                        Set<String> stringSet3 = DefaultAppsSetupListener.this.mPrefs.mPrefs.getStringSet(DefaultAppsPrefs.FAILED_PRELOAD_APP_LIST, set);
                        String string = DefaultAppsSetupListener.this.mPrefs.mPrefs.getString(DefaultAppsPrefs.DEFAULT_ENVIRONMENT_PACKAGE_NAME, "");
                        Iterator<String> it = stringSet.iterator();
                        while (it.hasNext()) {
                            String next = it.next();
                            if (!stringSet3.contains(next)) {
                                if (!next.equals(string) && !stringSet2.contains(next)) {
                                    if (!z2) {
                                        z = true;
                                    }
                                }
                            }
                            it.remove();
                        }
                        DefaultAppsSetupListener.this.mPrefs.A01(stringSet);
                        if (!z2 && z) {
                            return null;
                        }
                        DefaultAppsSetupListener.this.mDefaultAppsSetupManager.A02();
                        return null;
                    }
                }
            });
        }
    }

    @Override // com.oculus.common.init.INeedInit
    public final void init() {
        AnonymousClass0DC.A06(new Callable<Void>() {
            /* class com.oculus.defaultapps.DefaultAppsSetupListener.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final Void call() throws Exception {
                DefaultAppsSetupListener.this.A00();
                return null;
            }
        });
    }

    @Inject
    public DefaultAppsSetupListener(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
        this.mContext = C003108z.A02(r3);
        this.mPrefs = (DefaultAppsPrefs) AnonymousClass117.A00(0, r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
        this.mDefaultAppsSetupManager = (DefaultAppsSetupManager) AnonymousClass117.A00(436, r3);
        this.mDefaultAppsMethods = (DefaultAppsMethods) AnonymousClass117.A00(124, r3);
    }
}
