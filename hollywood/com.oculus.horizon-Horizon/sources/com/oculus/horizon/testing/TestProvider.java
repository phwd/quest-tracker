package com.oculus.horizon.testing;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import android.app.job.JobScheduler;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.secure.content.PublicContentProvider;
import com.oculus.common.init.AppInitLock;
import com.oculus.defaultapps.DefaultAppsPrefs;
import com.oculus.defaultapps.DefaultAppsSetupListener;
import com.oculus.defaultapps.DefaultAppsSetupManager;
import com.oculus.defaultapps.net.DefaultAppsRequest;
import com.oculus.defaultapps.net.DefaultAppsResponse;
import com.oculus.device.DeviceType;
import com.oculus.horizon.api.common.Item;
import com.oculus.testprovider.AsyncTestRunner;
import com.oculus.testprovider.TestResult;
import com.oculus.testprovider.TestRunnable;
import com.oculus.util.constants.JobSchedulerIds;
import java.util.Collections;
import javax.annotation.Nullable;

public class TestProvider extends PublicContentProvider {
    public static final String FETCH_SETUP_INSTALLS_LIST = "fetch_setup_installs_list";
    public static final String TAG = "TestProvider";
    public static final String TEST_SETUP_INSTALLS = "test_setup_installs_async";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, TestProvider testProvider) {
        testProvider._UL_mInjectionContext = new AnonymousClass0QC(3, r2);
    }

    private TestResult<String> fetchSetupInstallsList(String str) {
        return new AsyncTestRunner(new TestRunnable<String>() {
            /* class com.oculus.horizon.testing.TestProvider.AnonymousClass1 */

            @Override // com.oculus.testprovider.TestRunnable
            public final void A8Q(TestResult<String> testResult) {
                String str;
                DefaultAppsResponse A00 = ((DefaultAppsSetupListener) AnonymousClass0J2.A03(2, 176, TestProvider.this._UL_mInjectionContext)).mDefaultAppsMethods.A00(new DefaultAppsRequest(DeviceType.current().hmdType, false));
                if (A00 == null) {
                    str = "http request failed";
                } else if (A00.node.default_applications.isEmpty()) {
                    str = "empty default applications list";
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Item item : A00.node.default_applications) {
                        Item.AndroidBinary androidBinary = item.latest_supported_binary;
                        if (androidBinary == null) {
                            AnonymousClass0NO.A0E(TestProvider.TAG, "unsupported binary: %s", item.id);
                        } else {
                            sb.append(androidBinary.package_name);
                            sb.append(',');
                        }
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    testResult.mValue = (T) sb.toString();
                    testResult.mLatch.countDown();
                }
                testResult.mError = new RuntimeException(str);
                testResult.mLatch.countDown();
            }
        }).A00();
    }

    private TestResult<String> invokeSetupInstalls(String str) {
        return new AsyncTestRunner(new TestRunnable<String>() {
            /* class com.oculus.horizon.testing.TestProvider.AnonymousClass2 */

            @Override // com.oculus.testprovider.TestRunnable
            public final void A8Q(TestResult<String> testResult) {
                JobScheduler jobScheduler = (JobScheduler) TestProvider.this.getContext().getSystemService("jobscheduler");
                if (jobScheduler != null) {
                    jobScheduler.cancel(JobSchedulerIds.STANDALONE_SETUP);
                }
                DefaultAppsSetupManager.isDefaultAppsSetupJobScheduled.set(false);
                ((DefaultAppsPrefs) AnonymousClass0J2.A03(1, 0, TestProvider.this._UL_mInjectionContext)).mPrefs.edit().putBoolean(DefaultAppsPrefs.SETUP_COMPLETE, false).apply();
                ((DefaultAppsPrefs) AnonymousClass0J2.A03(1, 0, TestProvider.this._UL_mInjectionContext)).mPrefs.edit().putInt(DefaultAppsPrefs.SETUP_ATTEMPT, 0).apply();
                ((DefaultAppsPrefs) AnonymousClass0J2.A03(1, 0, TestProvider.this._UL_mInjectionContext)).A00(0);
                ((DefaultAppsPrefs) AnonymousClass0J2.A03(1, 0, TestProvider.this._UL_mInjectionContext)).mPrefs.edit().putStringSet(DefaultAppsPrefs.SETUP_COMPLETED_PACKAGES, Collections.EMPTY_SET).apply();
                ((DefaultAppsPrefs) AnonymousClass0J2.A03(1, 0, TestProvider.this._UL_mInjectionContext)).A02(true);
                ((DefaultAppsPrefs) AnonymousClass0J2.A03(1, 0, TestProvider.this._UL_mInjectionContext)).mPrefs.edit().putBoolean(DefaultAppsPrefs.NUX_OTA_DOWNLOAD_HIGH_PRI_APPS_BROADCAST, true).apply();
                ((DefaultAppsSetupListener) AnonymousClass0J2.A03(2, 176, TestProvider.this._UL_mInjectionContext)).A00();
                testResult.mValue = "success";
                testResult.mLatch.countDown();
            }
        }).A00();
    }

    @Override // X.AbstractC09361bk
    public int doDelete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override // X.AbstractC09361bk
    public String doGetType(Uri uri) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override // X.AbstractC09361bk
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override // X.AbstractC09361bk
    public Cursor doQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override // X.AbstractC09361bk
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("Not supported");
    }

    public static final void _UL_injectMe(Context context, TestProvider testProvider) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), testProvider);
    }

    public static /* synthetic */ String access$100() {
        return TAG;
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public Bundle doCall(String str, String str2, Bundle bundle) {
        Throwable th;
        Bundle bundle2;
        T message;
        String str3;
        TestResult<String> invokeSetupInstalls;
        Binder.clearCallingIdentity();
        bundle.size();
        if (TextUtils.equals(FETCH_SETUP_INSTALLS_LIST, str)) {
            invokeSetupInstalls = fetchSetupInstallsList(str);
        } else if (TextUtils.equals(TEST_SETUP_INSTALLS, str)) {
            invokeSetupInstalls = invokeSetupInstalls(str);
        } else {
            th = new IllegalArgumentException("unknown test method");
            bundle2 = new Bundle();
            bundle2.putString("method", str);
            message = th.getMessage();
            str3 = "error";
            bundle2.putString(str3, message);
            return bundle2;
        }
        th = invokeSetupInstalls.mError;
        if (th == null) {
            T t = invokeSetupInstalls.mValue;
            if (t == null) {
                message = "success";
            } else {
                message = t;
            }
            bundle2 = new Bundle();
            bundle2.putString("method", str);
            str3 = "result";
            bundle2.putString(str3, message);
            return bundle2;
        }
        bundle2 = new Bundle();
        bundle2.putString("method", str);
        message = th.getMessage();
        str3 = "error";
        bundle2.putString(str3, message);
        return bundle2;
    }

    @Override // com.facebook.secure.content.PublicContentProvider, X.AbstractC09361bk
    public boolean onCheckPermissions() {
        if (Binder.getCallingUid() == 0) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC09361bk
    public void onInitialize() {
        _UL_injectMe(getContext(), this);
        ((AppInitLock) AnonymousClass0J2.A03(0, 139, this._UL_mInjectionContext)).A01();
    }
}
