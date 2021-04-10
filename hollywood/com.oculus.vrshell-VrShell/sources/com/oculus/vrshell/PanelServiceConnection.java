package com.oculus.vrshell;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Surface;
import com.oculus.android.exoplayer2.C;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.vrshell.config.PanelAppConfiguration;
import com.oculus.vrshell.panelservice.PanelService;
import com.oculus.vrshell.panelservice.PanelVerifier;
import com.oculus.vrshell.panelservice.SigCertVerifier;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

public class PanelServiceConnection implements ServiceConnection {
    private static final String TAG = LoggingUtil.tag(PanelServiceConnection.class);
    private final IBinder mActivityToken;
    private final PanelAppConfiguration mAppConfig;
    private final PanelVerifier mFirstPartyVerifier;
    private boolean mIsServiceBound = false;
    private final PanelServiceConnectionListener mListener;
    private final HashMap mMultiSurface;
    private final int mPanelAppId;
    private final Bundle mPanelBundle;
    private final ParcelFileDescriptor mPanelServicePFD;
    private final Surface mPanelSurface;
    private Messenger mServiceMessenger = null;
    private final int mSystemUid;
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public interface PanelServiceConnectionListener {
        void onServiceConnected(ComponentName componentName);

        void onServiceDisconnected(int i);
    }

    PanelServiceConnection(UnifiedTelemetryLogger unifiedTelemetryLogger, PanelVerifier panelVerifier, int i, Surface surface, HashMap hashMap, PanelAppConfiguration panelAppConfiguration, int i2, Bundle bundle, Context context, ContentResolver contentResolver, IBinder iBinder, PanelServiceConnectionListener panelServiceConnectionListener) {
        this.mPanelAppId = i;
        this.mAppConfig = panelAppConfiguration;
        this.mPanelServicePFD = ParcelFileDescriptor.adoptFd(i2);
        this.mPanelSurface = surface;
        this.mUnifiedTelemetryLogger = unifiedTelemetryLogger;
        this.mFirstPartyVerifier = panelVerifier;
        this.mMultiSurface = hashMap;
        this.mPanelBundle = bundle;
        this.mActivityToken = iBinder;
        this.mListener = panelServiceConnectionListener;
        this.mSystemUid = getSystemProcessUidForCurrentUser(context);
    }

    public void sendActivityToken() {
        if (!this.mIsServiceBound) {
            String str = TAG;
            Log.e(str, "Could not send the activity token to panel app id " + this.mPanelAppId + " because service was not bound");
            return;
        }
        Message obtain = Message.obtain(null, 3, 0, 0);
        Bundle bundle = new Bundle();
        bundle.putBinder("panelToken", this.mActivityToken);
        obtain.setData(bundle);
        try {
            this.mServiceMessenger.send(obtain);
        } catch (Exception e) {
            Log.e(TAG, "Error sending message to service.", e);
        }
    }

    public String verifyFirstPartyUID(int i) {
        if (i == this.mSystemUid) {
            Log.i(TAG, "Panel app running with system privileges, allowing.");
            return null;
        }
        long nanoTime = System.nanoTime();
        SigCertVerifier.SigCertInfo checkApkSignature = this.mFirstPartyVerifier.checkApkSignature(i);
        String str = (String) Collections.min(checkApkSignature.packageNames);
        long nanoTime2 = System.nanoTime();
        Log.i(TAG, String.format("verifyFirstPartyUID(Debug Signatures=%s, uid=%d) => apkSignatureResult=%s, time=%d ms", Boolean.toString(this.mFirstPartyVerifier.areDebugSignaturesEnabled()), Integer.valueOf(i), checkApkSignature, Long.valueOf((nanoTime2 - nanoTime) / C.MICROS_PER_SECOND)));
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_server_auth");
        analyticsEvent.setExtra("package_name", str);
        analyticsEvent.setExtra("first_party_trusted", Boolean.valueOf(checkApkSignature.isTrusted));
        analyticsEvent.setExtra("developer_build", Boolean.valueOf(this.mFirstPartyVerifier.areDebugSignaturesEnabled()));
        if (!checkApkSignature.isTrusted) {
            analyticsEvent.setExtra("apk_signature", checkApkSignature.signature.toCharsString());
        }
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
        if (checkApkSignature.isTrusted) {
            return null;
        }
        return String.format("Expected APK '%s' to be signed with trusted certificate; failing authentication.", str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004f, code lost:
        if (r0 == null) goto L_0x0051;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onServiceConnected(android.content.ComponentName r7, android.os.IBinder r8) {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.PanelServiceConnection.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Log.d(TAG, String.format("onSeviceDisconnected - component name (%s), panel app id (%d)", String.valueOf(componentName), Integer.valueOf(this.mPanelAppId)));
        this.mServiceMessenger = null;
        this.mIsServiceBound = false;
        PanelServiceConnectionListener panelServiceConnectionListener = this.mListener;
        if (panelServiceConnectionListener != null) {
            panelServiceConnectionListener.onServiceDisconnected(this.mPanelAppId);
        }
    }

    private void sendPanelConnectionMessages() {
        if (this.mIsServiceBound) {
            Message obtain = Message.obtain(null, 2, 0, 0);
            Bundle bundle = new Bundle();
            bundle.putParcelable(PanelService.CREATE_PANEL_APP_KEY_FD, this.mPanelServicePFD);
            bundle.putParcelable(PanelService.CREATE_PANEL_APP_KEY_SURFACE, this.mPanelSurface);
            HashMap hashMap = this.mMultiSurface;
            if (hashMap != null) {
                for (Object obj : hashMap.keySet()) {
                    String str = (String) obj;
                    bundle.putParcelable("panelSurface:" + str, (Surface) this.mMultiSurface.get(obj));
                    if (str.equals("aux") && this.mMultiSurface.keySet().size() == 1) {
                        Log.d(TAG, "Adding legacy aux panel surface as panelSurfaceAux.");
                        bundle.putParcelable("panelSurfaceAux", (Surface) this.mMultiSurface.get(obj));
                    }
                }
            }
            bundle.putParcelable(PanelService.CREATE_PANEL_APP_KEY_BUNDLE, this.mPanelBundle);
            obtain.setData(bundle);
            try {
                this.mServiceMessenger.send(obtain);
            } catch (Exception e) {
                Log.e(TAG, "Error sending message to service.", e);
            }
            new Handler().post(new Runnable() {
                /* class com.oculus.vrshell.PanelServiceConnection.AnonymousClass1 */

                public void run() {
                    try {
                        PanelServiceConnection.this.mPanelServicePFD.close();
                    } catch (IOException e) {
                        Log.e(PanelServiceConnection.TAG, "Failure to close panel service file descriptor ", e);
                    }
                }
            });
        }
    }

    public static int getSystemProcessUidForCurrentUser(Context context) {
        int i = 1000;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("android", 128);
            if (!(packageInfo == null || packageInfo.applicationInfo == null)) {
                i = packageInfo.applicationInfo.uid;
            }
        } catch (PackageManager.NameNotFoundException e) {
            String str = TAG;
            Log.w(str, "getSystemProcessUidForCurrentUser: getPackageInfo failed: " + e);
        }
        String str2 = TAG;
        Log.d(str2, "getSystemProcessUidForCurrentUser: 'android' systemUid = " + i);
        return i;
    }
}
