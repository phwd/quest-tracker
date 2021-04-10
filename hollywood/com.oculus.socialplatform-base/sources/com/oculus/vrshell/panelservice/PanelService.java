package com.oculus.vrshell.panelservice;

import X.AnonymousClass006;
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
import com.oculus.panellib.CPanelService;
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
    public static final String PANEL_SURFACE_KEY_PREFIX = "panelSurface:";
    public static final String TAG = "CppPanelService";
    public Messenger messenger = null;
    public PanelVerifier vrShellVerifier = null;

    public static class IncomingHandler extends Handler {
        public WeakReference<PanelService> serviceWeakRef;

        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.HashMap<java.lang.String, android.view.Surface> */
        /* JADX WARN: Multi-variable type inference failed */
        private HashMap<String, Surface> unpackSurfaces(Bundle bundle) {
            HashMap<String, Surface> hashMap = new HashMap<>();
            for (String str : bundle.keySet()) {
                if (str.startsWith("panelSurface:")) {
                    hashMap.put(str.substring(13), bundle.getParcelable(str));
                }
            }
            return hashMap;
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

        public boolean sendMessageAtTime(Message message, long j) {
            String str;
            PanelService panelService = this.serviceWeakRef.get();
            if (panelService != null) {
                str = panelService.verifyVrShellUID(Binder.getCallingUid());
                if (str == null) {
                    return super.sendMessageAtTime(message, j);
                }
            } else {
                str = "Service weak reference lost!";
            }
            Log.e(PanelService.TAG, AnonymousClass006.A07("Error processing MSG_CREATE_PANEL_APP:  ", str));
            try {
                ((ParcelFileDescriptor) message.getData().getParcelable("panelServicePFD")).close();
                return false;
            } catch (Exception e) {
                Log.e(PanelService.TAG, "Error closing ParcelFileDescriptor after verification failure:  ", e);
                return false;
            }
        }

        public IncomingHandler(PanelService panelService) {
            this.serviceWeakRef = new WeakReference<>(panelService);
        }

        private void handleActivityToken(Message message) {
            message.getData().getBinder(CPanelService.ACTIVITY_TOKEN_KEY_TOKEN);
            if (this.serviceWeakRef.get() == null) {
                Log.e(PanelService.TAG, "Service weak reference lost! Unable to handle activity token.");
            }
        }

        private void handleCreatePanelApp(Message message) {
            Bundle data = message.getData();
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) data.getParcelable("panelServicePFD");
            Surface surface = (Surface) data.getParcelable("panelSurface");
            Bundle bundle = (Bundle) data.getParcelable("panelBundle");
            if (bundle.containsKey("_oc_shell_panel_app_wait_for_debugger")) {
                Debug.waitForDebugger();
            }
            HashMap<String, Surface> unpackSurfaces = unpackSurfaces(data);
            PanelService panelService = this.serviceWeakRef.get();
            if (!bundle.containsKey("_oc_analytics_session_id") || !bundle.containsKey("_oc_shell_version_code") || !bundle.containsKey("_oc_shell_version_name")) {
                throw new IllegalArgumentException("Missing 1 or more required environment keys required for versioning and telemetry.");
            }
            panelService.nativeInitializePanelInstance(panelService.createNativePanelAppInstance(surface, unpackSurfaces, bundle), parcelFileDescriptor.detachFd());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private native void nativeInitializePanelInstance(long j, int i);

    public void onShellTokenReady(IBinder iBinder) {
    }

    static {
        System.loadLibrary("panelappsdk");
    }

    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        throw new UnsupportedOperationException("Panel applications should provide an instance.");
    }

    public IBinder onBind(Intent intent) {
        return this.messenger.getBinder();
    }

    public void onDestroy() {
    }

    public void onCreate() {
        super.onCreate();
        this.messenger = new Messenger(new IncomingHandler(this));
        this.vrShellVerifier = new PanelVerifier(this, !Build.TYPE.equals("user"));
    }

    public String verifyVrShellUID(int i) {
        Object[] objArr;
        String str;
        System.nanoTime();
        SigCertVerifier.SigCertInfo checkSigCertInfo = this.vrShellVerifier.mTrustedFirstPartyCertVerifier.checkSigCertInfo(i);
        System.nanoTime();
        if (!checkSigCertInfo.packageNames.contains("com.oculus.vrshell")) {
            objArr = new Object[]{"com.oculus.vrshell", Collections.min(checkSigCertInfo.packageNames)};
            str = "Expected connection to APK %s and got %s instead; failing authentication.";
        } else if (checkSigCertInfo.isTrusted) {
            return null;
        } else {
            objArr = new Object[]{"com.oculus.vrshell"};
            str = "Expected %s APK to be signed with trusted certificate; failing authentication.";
        }
        return String.format(str, objArr);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }
}
