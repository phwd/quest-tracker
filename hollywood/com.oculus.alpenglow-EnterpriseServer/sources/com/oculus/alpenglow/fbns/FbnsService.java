package com.oculus.alpenglow.fbns;

import X.AbstractC02990bJ;
import X.AbstractC03090bY;
import X.AnonymousClass08D;
import X.AnonymousClass08h;
import X.AnonymousClass0Cg;
import X.AnonymousClass0Fo;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0P6;
import X.AnonymousClass0P8;
import X.AnonymousClass0R7;
import X.AnonymousClass0uh;
import X.C01200Dy;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.oculus.alpenglow.config.ConfigurationStore;
import com.oculus.alpenglow.fbns.HWDeviceRegisterPushTokenMutationImpl;
import com.oculus.alpenglow.fbns.HWDeviceRegisterPushTokenResponse;
import com.oculus.alpenglow.graphql.calls.HWPushTokenRegisterRequest;
import com.oculus.alpenglow.graphql.calls.SensitiveString;
import com.oculus.alpenglow.logging.ConfigLogger;
import com.oculus.alpenglow.logging.LoggerConstants;
import com.oculus.alpenglow.remotewipe.RemoteWipeManager;
import com.oculus.appmanager.assets.database.assetcontract.AssetContract;
import com.oculus.auth.device.DeviceAuthTokenStore;
import com.oculus.remotewipe.Source;
import com.oculus.remotewipe.WipeRequester;
import java.io.StringReader;
import java.util.Objects;

public class FbnsService extends AnonymousClass0uh {
    public static final String CONFIG_CHANGED = "HW_ITAP_CONFIG_CHANGED";
    public static final String DATA_FIELD = "data";
    public static final String TAG = "EnterpriseServer.FbnsService";
    public AnonymousClass0R7 _UL_mInjectionContext;

    public static class PushData {
        public final String message;
        public final long time;
        public final String type;
    }

    public static final void _UL_staticInjectMe(AbstractC02990bJ r2, FbnsService fbnsService) {
        fbnsService._UL_mInjectionContext = new AnonymousClass0R7(7, r2);
    }

    public FbnsService() {
        super("FbnsService");
    }

    public /* synthetic */ void lambda$onRegistered$0$FbnsService(AbstractC03090bY r4, AnonymousClass0Cg r5) {
        ((AnonymousClass0P6) AnonymousClass0Lh.A03(2, 51, this._UL_mInjectionContext)).A2X(r4, r5, AnonymousClass08h.INSTANCE);
    }

    @Override // X.AnonymousClass0uh
    public void onMessage(Intent intent) {
        Object A01;
        String stringExtra = intent.getStringExtra("data");
        AnonymousClass08D r2 = (AnonymousClass08D) AnonymousClass0Lh.A03(3, 66, this._UL_mInjectionContext);
        Class<PushData> cls = PushData.class;
        if (stringExtra == null) {
            A01 = null;
        } else {
            AnonymousClass0Fo r1 = new AnonymousClass0Fo(new StringReader(stringExtra));
            r1.A08 = false;
            A01 = AnonymousClass08D.A01(r2, r1, cls);
            AnonymousClass08D.A05(A01, r1);
        }
        Class<?> cls2 = C01200Dy.A00.get(cls);
        if (cls2 != null) {
            cls = cls2;
        }
        PushData cast = cls.cast(A01);
        if (cast != null) {
            String str = cast.type;
            if (str == null || !Objects.equals(str, CONFIG_CHANGED)) {
                RemoteWipeManager.A00((RemoteWipeManager) AnonymousClass0Lh.A03(6, 14, this._UL_mInjectionContext), new WipeRequester(Source.ENTERPRISE_PUSH_NOTIFICATION));
                return;
            }
            ((ConfigurationStore) AnonymousClass0Lh.A03(4, 97, this._UL_mInjectionContext)).A03(((ConfigLogger) AnonymousClass0Lh.A03(5, 45, this._UL_mInjectionContext)).A01(LoggerConstants.CONFIGURATION_FETCH_REASON_PUSH, LoggerConstants.CONFIGURATION_FETCH_TYPE_NORMAL));
        }
    }

    @Override // X.AnonymousClass0uh
    public void onMessageDeleted(int i) {
        AnonymousClass0NK.A06(TAG, "onMessageDeleted(%d)", Integer.valueOf(i));
    }

    @Override // X.AnonymousClass0uh
    public void onRegistered(String str, boolean z) {
        AnonymousClass0NK.A06(TAG, "onRegistered(%s)", str);
        HWPushTokenRegisterRequest hWPushTokenRegisterRequest = new HWPushTokenRegisterRequest();
        SensitiveString sensitiveString = new SensitiveString();
        sensitiveString.A05("sensitive_string_value", str);
        hWPushTokenRegisterRequest.A03().A05("token", sensitiveString.A03());
        hWPushTokenRegisterRequest.A05("os_version", Build.VERSION.INCREMENTAL);
        hWPushTokenRegisterRequest.A05(AssetContract.AssetTableColumns.APP_VERSION, ((PackageInfo) AnonymousClass0Lh.A03(0, 84, this._UL_mInjectionContext)).versionName);
        ((DeviceAuthTokenStore) AnonymousClass0Lh.A03(1, 112, this._UL_mInjectionContext)).fetchToken().thenRun((Runnable) new Runnable(new HWDeviceRegisterPushTokenMutationImpl.Builder().A86(hWPushTokenRegisterRequest).A1a(), new AnonymousClass0Cg<AnonymousClass0P8<HWDeviceRegisterPushTokenResponse>>() {
            /* class com.oculus.alpenglow.fbns.FbnsService.AnonymousClass1 */

            @Override // X.AnonymousClass0Cg
            public void onFailure(Throwable th) {
                AnonymousClass0NK.A04(FbnsService.TAG, "error registering push token", th);
            }

            public void onSuccess(AnonymousClass0P8<HWDeviceRegisterPushTokenResponse> r4) {
                HWDeviceRegisterPushTokenResponse.HwDeviceRegisterPushToken A3h;
                String A4I;
                HWDeviceRegisterPushTokenResponse A4R = r4.A4R();
                if (A4R != null && (A3h = A4R.A3h()) != null && (A4I = A3h.A4I()) != null) {
                    AnonymousClass0NK.A06(FbnsService.TAG, "registered push token, tokenId=%s", A4I);
                }
            }
        }) {
            /* class com.oculus.alpenglow.fbns.$$Lambda$FbnsService$t3eTpOyj9Zmt4YNsoSEx9ugrzdc2 */
            public final /* synthetic */ AbstractC03090bY f$1;
            public final /* synthetic */ AnonymousClass0Cg f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                FbnsService.this.lambda$onRegistered$0$FbnsService(this.f$1, this.f$2);
            }
        });
    }

    @Override // X.AnonymousClass0uh
    public void onRegistrationError(String str) {
        AnonymousClass0NK.A06(TAG, "onRegistrationError(%s)", str);
    }

    @Override // X.AnonymousClass0uh
    public void onUnregistered() {
        AnonymousClass0NK.A01(TAG, "onUnregistered()");
    }

    public static final void _UL_injectMe(Context context, FbnsService fbnsService) {
        _UL_staticInjectMe(AnonymousClass0Lh.get(context), fbnsService);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }
}
