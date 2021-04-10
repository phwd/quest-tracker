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
    private native void nativeInitializePanelInstance(long j, long j2, int i);

    /* access modifiers changed from: package-private */
    public static class IncomingHandler extends Handler {
        private static final long ApiLevel_2 = 1;
        private static final long ApiLevel_2_4 = 2;
        private static final long ApiLevel_Debug = 999999999;
        private static final long ApiLevel_Legacy = 0;
        private static final long DebugVersionCode = 999999999;
        boolean legacyPanelSurfaceEmulation;
        WeakReference<PanelService> serviceWeakRef;

        IncomingHandler(PanelService service) {
            this.serviceWeakRef = new WeakReference<>(service);
            this.legacyPanelSurfaceEmulation = service.shouldSimulateLegacyPanelSurfaceAux();
        }

        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            String verificationError;
            PanelService panelService = this.serviceWeakRef.get();
            if (panelService != null) {
                verificationError = panelService.verifyVrShellUID(Binder.getCallingUid());
            } else {
                verificationError = "Service weak reference lost!";
            }
            if (verificationError == null) {
                return super.sendMessageAtTime(msg, uptimeMillis);
            }
            Log.e(PanelService.TAG, "Error processing MSG_CREATE_PANEL_APP:  " + verificationError);
            try {
                ((ParcelFileDescriptor) msg.getData().getParcelable(PanelService.CREATE_PANEL_APP_KEY_FD)).close();
                return false;
            } catch (Exception e) {
                Log.e(PanelService.TAG, "Error closing ParcelFileDescriptor after verification failure:  ", e);
                return false;
            }
        }

        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 2) {
                handleCreatePanelApp(msg);
            } else if (i != 3) {
                super.handleMessage(msg);
            } else {
                handleActivityToken(msg);
            }
        }

        private void handleActivityToken(Message msg) {
            IBinder token = msg.getData().getBinder("panelToken");
            PanelService panelService = this.serviceWeakRef.get();
            if (panelService != null) {
                panelService.onShellTokenReady(token);
            } else {
                Log.e(PanelService.TAG, "Service weak reference lost! Unable to handle activity token.");
            }
        }

        private void handleCreatePanelApp(Message msg) {
            Surface panelSurfaceAux;
            Log.d(PanelService.TAG, "BEGIN: Initializing panel application via MSG_CREATE_PANEL_APP.");
            Bundle initializationParameters = msg.getData();
            ParcelFileDescriptor stringCommandBufferPFD = (ParcelFileDescriptor) initializationParameters.getParcelable(PanelService.CREATE_PANEL_APP_KEY_FD);
            Surface panelSurface = (Surface) initializationParameters.getParcelable(PanelService.CREATE_PANEL_APP_KEY_SURFACE);
            Bundle environmentBundle = (Bundle) initializationParameters.getParcelable(PanelService.CREATE_PANEL_APP_KEY_BUNDLE);
            if (environmentBundle.containsKey("_oc_shell_panel_app_wait_for_debugger")) {
                Log.d(PanelService.TAG, "Waiting for debugger.");
                Debug.waitForDebugger();
                Log.d(PanelService.TAG, "Debugger connected.");
            }
            Map<String, Surface> multiSurface = new HashMap<>();
            if (!this.legacyPanelSurfaceEmulation) {
                multiSurface = unpackSurfaces(initializationParameters);
            }
            if (multiSurface.size() == 0 && (panelSurfaceAux = (Surface) initializationParameters.getParcelable("panelSurfaceAux")) != null) {
                multiSurface.put("aux", panelSurfaceAux);
            }
            PanelService panelRef = this.serviceWeakRef.get();
            if (!environmentBundle.containsKey("_oc_analytics_session_id") || !environmentBundle.containsKey("_oc_shell_version_code") || !environmentBundle.containsKey("_oc_shell_version_name")) {
                throw new IllegalArgumentException("Missing 1 or more required environment keys required for versioning and telemetry.");
            }
            String versionName = (String) environmentBundle.get("_oc_shell_version_name");
            int versionCode = Integer.parseInt((String) environmentBundle.get("_oc_shell_version_code"));
            long apiLevel = getShellVersionFromEvidence(versionName, versionCode);
            Log.d(PanelService.TAG, "Configuring service for Shell with versionName " + versionName + " and versionCode " + versionCode + " selected API Level " + apiLevel);
            panelRef.nativeInitializePanelInstance(panelRef.createNativePanelAppInstance(panelSurface, multiSurface, environmentBundle), apiLevel, stringCommandBufferPFD.detachFd());
            Log.d(PanelService.TAG, "END: Initializing panel application via MSG_CREATE_PANEL_APP.");
        }

        private long getShellVersionFromEvidence(String versionName, int versionCode) {
            if (((long) versionCode) == 999999999) {
                return 999999999;
            }
            String[] versionParts = versionName.split("\\.");
            int minorVersion = 0;
            int majorVersion = Integer.parseInt(versionParts[0]);
            if (versionParts.length >= 2) {
                minorVersion = Integer.parseInt(versionParts[1]);
            }
            try {
                if (versionParts.length >= 3) {
                    Integer.parseInt(versionParts[2]);
                }
            } catch (NumberFormatException e) {
                Log.d(PanelService.TAG, "Failed to parse patch value of " + versionParts[2]);
            }
            if (majorVersion == 1) {
                return 0;
            }
            if (majorVersion == 2 && minorVersion == 0) {
                return 1;
            }
            Log.d(PanelService.TAG, "Falling back to last known good shell api level for " + versionName);
            return 2;
        }

        private HashMap<String, Surface> unpackSurfaces(Bundle initializationBundle) {
            HashMap<String, Surface> multiSurface = new HashMap<>();
            for (String keyName : initializationBundle.keySet()) {
                if (keyName.startsWith(PanelService.PANEL_SURFACE_KEY_PREFIX)) {
                    multiSurface.put(keyName.substring(PanelService.PANEL_SURFACE_KEY_PREFIX.length()), (Surface) initializationBundle.getParcelable(keyName));
                }
            }
            return multiSurface;
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

    public int onStartCommand(Intent intent, int flags, int startId) {
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
        this.vrShellVerifier = new PanelVerifier(this, !Build.TYPE.equals("user"));
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
    }

    public boolean shouldSimulateLegacyPanelSurfaceAux() {
        return false;
    }

    /* access modifiers changed from: protected */
    public long createNativePanelAppInstance(Surface panelSurface, Map<String, Surface> map, Bundle environmentBundle) {
        throw new UnsupportedOperationException("Panel applications should provide an instance.");
    }

    /* access modifiers changed from: protected */
    public void onShellTokenReady(IBinder token) {
    }

    /* access modifiers changed from: protected */
    public String verifyVrShellUID(int uid) {
        long startTime = System.nanoTime();
        SigCertVerifier.SigCertInfo sci = this.vrShellVerifier.checkApkSignature(uid);
        Log.i(TAG, String.format("verifyVrShellUID(Debug Signatures=%s, uid=%d) => apkSignatureResult=%s, time=%d ms", Boolean.toString(this.vrShellVerifier.areDebugSignaturesEnabled()), Integer.valueOf(uid), sci, Long.valueOf((System.nanoTime() - startTime) / 1000000)));
        if (!sci.packageNames.contains(VerifierConstants.OCULUS_VRSHELL_PACKAGE_NAME)) {
            return String.format("Expected connection to APK %s and got %s instead; failing authentication.", VerifierConstants.OCULUS_VRSHELL_PACKAGE_NAME, Collections.min(sci.packageNames));
        } else if (sci.isTrusted) {
            return null;
        } else {
            return String.format("Expected %s APK to be signed with trusted certificate; failing authentication.", VerifierConstants.OCULUS_VRSHELL_PACKAGE_NAME);
        }
    }
}
