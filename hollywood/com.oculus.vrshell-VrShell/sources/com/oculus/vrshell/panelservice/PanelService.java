package com.oculus.vrshell.panelservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Surface;
import com.oculus.android.exoplayer2.C;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.vrshell.panelservice.SigCertVerifier;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PanelService extends Service {
    public static final String CREATE_PANEL_APP_KEY_BUNDLE = "panelBundle";
    public static final String CREATE_PANEL_APP_KEY_FD = "panelServicePFD";
    public static final String CREATE_PANEL_APP_KEY_SURFACE = "panelSurface";
    public static final int MSG_ACTIVITY_TOKEN = 3;
    public static final int MSG_CREATE_PANEL_APP = 2;
    public static final int MSG_SOCKET_PFD = 1;
    private static final String PANEL_SURFACE_KEY_PREFIX = "panelSurface:";
    private static final String TAG = "CppPanelService";
    private Messenger messenger = null;
    private PanelVerifier vrShellVerifier = null;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private native void nativeInitializePanelInstance(long j, int i);

    /* access modifiers changed from: protected */
    public void onShellTokenReady(IBinder iBinder) {
    }

    public boolean shouldSimulateLegacyPanelSurfaceAux() {
        return false;
    }

    static class IncomingHandler extends Handler {
        boolean legacyPanelSurfaceEmulation;
        WeakReference<PanelService> serviceWeakRef;

        IncomingHandler(PanelService panelService) {
            this.serviceWeakRef = new WeakReference<>(panelService);
            this.legacyPanelSurfaceEmulation = panelService.shouldSimulateLegacyPanelSurfaceAux();
        }

        public boolean sendMessageAtTime(Message message, long j) {
            PanelService panelService = this.serviceWeakRef.get();
            String verifyVrShellUID = panelService != null ? panelService.verifyVrShellUID(Binder.getCallingUid()) : "Service weak reference lost!";
            if (verifyVrShellUID == null) {
                return super.sendMessageAtTime(message, j);
            }
            Log.e(PanelService.TAG, "Error processing MSG_CREATE_PANEL_APP:  " + verifyVrShellUID);
            try {
                ((ParcelFileDescriptor) message.getData().getParcelable(PanelService.CREATE_PANEL_APP_KEY_FD)).close();
                return false;
            } catch (Exception e) {
                Log.e(PanelService.TAG, "Error closing ParcelFileDescriptor after verification failure:  ", e);
                return false;
            }
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 2) {
                handleCreatePanelApp(message);
            } else if (i != 3) {
                super.handleMessage(message);
            } else {
                handleActivityToken(message);
            }
        }

        private void handleActivityToken(Message message) {
            IBinder binder = message.getData().getBinder("panelToken");
            PanelService panelService = this.serviceWeakRef.get();
            if (panelService != null) {
                panelService.onShellTokenReady(binder);
            } else {
                Log.e(PanelService.TAG, "Service weak reference lost! Unable to handle activity token.");
            }
        }

        private void handleCreatePanelApp(Message message) {
            Surface surface;
            Log.d(PanelService.TAG, "BEGIN: Initializing panel application via MSG_CREATE_PANEL_APP.");
            Bundle data = message.getData();
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) data.getParcelable(PanelService.CREATE_PANEL_APP_KEY_FD);
            Surface surface2 = (Surface) data.getParcelable(PanelService.CREATE_PANEL_APP_KEY_SURFACE);
            Bundle bundle = (Bundle) data.getParcelable(PanelService.CREATE_PANEL_APP_KEY_BUNDLE);
            if (bundle.containsKey("_oc_shell_panel_app_wait_for_debugger")) {
                Log.d(PanelService.TAG, "Waiting for debugger.");
                Debug.waitForDebugger();
                Log.d(PanelService.TAG, "Debugger connected.");
            }
            HashMap<String, Surface> hashMap = new HashMap<>();
            if (!this.legacyPanelSurfaceEmulation) {
                hashMap = unpackSurfaces(data);
            }
            if (hashMap.size() == 0 && (surface = (Surface) data.getParcelable("panelSurfaceAux")) != null) {
                hashMap.put("aux", surface);
            }
            PanelService panelService = this.serviceWeakRef.get();
            if (!bundle.containsKey("_oc_analytics_session_id") || !bundle.containsKey("_oc_shell_version_code") || !bundle.containsKey("_oc_shell_version_name")) {
                throw new IllegalArgumentException("Missing 1 or more required environment keys required for versioning and telemetry.");
            }
            panelService.nativeInitializePanelInstance(panelService.createNativePanelAppInstance(surface2, hashMap, bundle), parcelFileDescriptor.detachFd());
            Log.d(PanelService.TAG, "END: Initializing panel application via MSG_CREATE_PANEL_APP.");
        }

        private HashMap<String, Surface> unpackSurfaces(Bundle bundle) {
            HashMap<String, Surface> hashMap = new HashMap<>();
            for (String str : bundle.keySet()) {
                if (str.startsWith(PanelService.PANEL_SURFACE_KEY_PREFIX)) {
                    hashMap.put(str.substring(13), (Surface) bundle.getParcelable(str));
                }
            }
            return hashMap;
        }
    }

    static {
        Log.d(TAG, "START load of libpanelappsdk.so");
        System.loadLibrary("panelappsdk");
        Log.d(TAG, "END load of libpanelappsdk.so");
    }

    public PanelService() {
        Log.d(TAG, "PanelService()");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Log.d(TAG, "onStartCommand()");
        return 2;
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");
        return this.messenger.getBinder();
    }

    public void onCreate() {
        super.onCreate();
        this.messenger = new Messenger(new IncomingHandler(this));
        this.vrShellVerifier = new PanelVerifier(this, !Build.TYPE.equals(ServiceContract.EXTRA_USER));
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
    }

    /* access modifiers changed from: protected */
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        throw new UnsupportedOperationException("Panel applications should provide an instance.");
    }

    /* access modifiers changed from: protected */
    public String verifyVrShellUID(int i) {
        long nanoTime = System.nanoTime();
        SigCertVerifier.SigCertInfo checkApkSignature = this.vrShellVerifier.checkApkSignature(i);
        Log.i(TAG, String.format("verifyVrShellUID(Debug Signatures=%s, uid=%d) => apkSignatureResult=%s, time=%d ms", Boolean.toString(this.vrShellVerifier.areDebugSignaturesEnabled()), Integer.valueOf(i), checkApkSignature, Long.valueOf((System.nanoTime() - nanoTime) / C.MICROS_PER_SECOND)));
        if (!checkApkSignature.packageNames.contains("com.oculus.vrshell")) {
            return String.format("Expected connection to APK %s and got %s instead; failing authentication.", "com.oculus.vrshell", Collections.min(checkApkSignature.packageNames));
        } else if (checkApkSignature.isTrusted) {
            return null;
        } else {
            return String.format("Expected %s APK to be signed with trusted certificate; failing authentication.", "com.oculus.vrshell");
        }
    }
}
