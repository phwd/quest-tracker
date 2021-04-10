package com.oculus.vrshell.panel;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Surface;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.vrshell.panel.SigCertVerifier;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;

public abstract class PanelService extends Service {
    public static final String ACTIVITY_TOKEN_KEY_TOKEN = "panelToken";
    public static final String CREATE_PANEL_APP_DEFAULT_SURFACE = "#main";
    public static final String CREATE_PANEL_APP_KEY_BUNDLE = "panelBundle";
    public static final String CREATE_PANEL_APP_KEY_FD = "panelServicePFD";
    public static final String CREATE_PANEL_APP_KEY_SURFACE = "panelSurface";
    public static final String CREATE_PANEL_APP_SURFACE_KEY_PREFIX = "panelSurface:";
    public static final int MSG_ACTIVITY_TOKEN = 3;
    public static final int MSG_CREATE_PANEL_APP = 2;
    private static final String TAG = "PanelService";
    protected Messenger messenger = null;
    private PanelVerifier vrShellVerifier = null;

    /* access modifiers changed from: protected */
    public abstract void createPanelApp(int i, Surface[] surfaceArr, String[] strArr, String[] strArr2);

    /* access modifiers changed from: private */
    public static class PanelMessageHandler extends Handler {
        private final WeakReference<PanelService> service;

        PanelMessageHandler(PanelService cps) {
            this.service = new WeakReference<>(cps);
        }

        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            String verificationError;
            PanelService panelService = this.service.get();
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
            } catch (Exception e) {
                Log.e(PanelService.TAG, "Error closing ParcelFileDescriptor after verification failure:  ", e);
            }
            return false;
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Bundle b = msg.getData();
                    ParcelFileDescriptor panelFd = (ParcelFileDescriptor) b.getParcelable(PanelService.CREATE_PANEL_APP_KEY_FD);
                    Bundle panelBundle = b.getBundle(PanelService.CREATE_PANEL_APP_KEY_BUNDLE);
                    PanelServiceHelpers.waitForDebuggerIfNeeded(panelBundle);
                    String[] panelEnv = PanelServiceHelpers.envArrayFromBundle(panelBundle);
                    ArrayList<String> multiSurfaceNames = new ArrayList<>();
                    ArrayList<Surface> multiSurfaceSurfaces = new ArrayList<>();
                    multiSurfaceNames.add(PanelService.CREATE_PANEL_APP_DEFAULT_SURFACE);
                    multiSurfaceSurfaces.add((Surface) b.getParcelable(PanelService.CREATE_PANEL_APP_KEY_SURFACE));
                    for (String keyName : b.keySet()) {
                        if (keyName.startsWith(PanelService.CREATE_PANEL_APP_SURFACE_KEY_PREFIX)) {
                            multiSurfaceNames.add(keyName.substring(PanelService.CREATE_PANEL_APP_SURFACE_KEY_PREFIX.length()));
                            multiSurfaceSurfaces.add((Surface) b.getParcelable(keyName));
                        }
                    }
                    Log.i(PanelService.TAG, String.format("Creating panel app with environment %s", PanelServiceHelpers.envArrayToString(panelEnv)));
                    this.service.get().createPanelApp(panelFd.detachFd(), (Surface[]) multiSurfaceSurfaces.toArray(new Surface[multiSurfaceSurfaces.size()]), (String[]) multiSurfaceNames.toArray(new String[multiSurfaceNames.size()]), panelEnv);
                    return;
                case 3:
                    this.service.get().onShellTokenReady(msg.getData().getBinder(PanelService.ACTIVITY_TOKEN_KEY_TOKEN));
                    return;
                default:
                    super.handleMessage(msg);
                    return;
            }
        }
    }

    public void onCreate() {
        Log.i(TAG, "onCreate()");
        super.onCreate();
        this.messenger = new Messenger(new PanelMessageHandler(this));
        this.vrShellVerifier = new PanelVerifier(this, !Build.TYPE.equals(ServiceContract.EXTRA_USER));
        Log.i(TAG, "end of onCreate()");
    }

    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, String.format("onStartCommand(%x, %d", Integer.valueOf(flags), Integer.valueOf(startId)));
        return 2;
    }

    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind()");
        return this.messenger.getBinder();
    }

    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    /* access modifiers changed from: protected */
    public String verifyVrShellUID(int uid) {
        long startTime = System.nanoTime();
        SigCertVerifier.SigCertInfo sci = this.vrShellVerifier.checkApkSignature(uid);
        Log.i(TAG, String.format("verifyVrShellUID(Debug Signatures=%s, uid=%d) => apkSignatureResult=%s, time=%d ms", Boolean.toString(this.vrShellVerifier.areDebugSignaturesEnabled()), Integer.valueOf(uid), sci, Long.valueOf((System.nanoTime() - startTime) / 1000000)));
        if (!sci.packageNames.contains("com.oculus.vrshell")) {
            return String.format("Expected connection to APK %s and got %s instead; failing authentication.", "com.oculus.vrshell", Collections.min(sci.packageNames));
        } else if (sci.isTrusted) {
            return null;
        } else {
            return String.format("Expected %s APK to be signed with trusted certificate; failing authentication.", "com.oculus.vrshell");
        }
    }

    /* access modifiers changed from: protected */
    public void onShellTokenReady(IBinder token) {
    }

    /* access modifiers changed from: protected */
    public String getDefaultLibraryName() {
        String[] packageComponents = getApplicationInfo().packageName.split("\\.");
        return packageComponents[packageComponents.length - 1];
    }
}
