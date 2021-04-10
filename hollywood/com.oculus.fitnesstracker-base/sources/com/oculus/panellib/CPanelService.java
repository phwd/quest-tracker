package com.oculus.panellib;

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
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;
import com.oculus.fitnesstracker.database.FitnessTrackerUserContract;
import com.oculus.panellib.SigCertVerifier;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;

public abstract class CPanelService extends Service implements IBinder.DeathRecipient {
    public static final String ACTIVITY_TOKEN_KEY_TOKEN = "panelToken";
    public static final String CREATE_PANEL_APP_DEFAULT_SURFACE = "#main";
    public static final String CREATE_PANEL_APP_KEY_BUNDLE = "panelBundle";
    public static final String CREATE_PANEL_APP_KEY_FD = "panelServicePFD";
    public static final String CREATE_PANEL_APP_KEY_SURFACE = "panelSurface";
    public static final String CREATE_PANEL_APP_SURFACE_KEY_PREFIX = "panelSurface:";
    public static final int MSG_ACTIVITY_TOKEN = 3;
    public static final int MSG_CREATE_PANEL_APP = 2;
    private static final String TAG = "CPanelService";
    protected Messenger messenger = null;
    private PanelVerifier vrShellVerifier = null;

    private native void nativeCreatePanelApp(int i, Surface[] surfaceArr, String[] strArr, String[] strArr2);

    private native void nativeDestroyPanelApp();

    /* access modifiers changed from: protected */
    public void onShellTokenReady(IBinder iBinder) {
    }

    /* access modifiers changed from: package-private */
    public static class PanelMessageHandler extends Handler {
        private final WeakReference<CPanelService> service;

        PanelMessageHandler(CPanelService cPanelService) {
            this.service = new WeakReference<>(cPanelService);
        }

        public boolean sendMessageAtTime(Message message, long j) {
            CPanelService cPanelService = this.service.get();
            String verifyVrShellUID = cPanelService != null ? cPanelService.verifyVrShellUID(Binder.getCallingUid()) : "Service weak reference lost!";
            if (verifyVrShellUID == null) {
                return super.sendMessageAtTime(message, j);
            }
            Log.e(CPanelService.TAG, "Error processing MSG_CREATE_PANEL_APP:  " + verifyVrShellUID);
            try {
                ((ParcelFileDescriptor) message.getData().getParcelable(CPanelService.CREATE_PANEL_APP_KEY_FD)).close();
                return false;
            } catch (IOException e) {
                Log.e(CPanelService.TAG, "Error closing ParcelFileDescriptor after verification failure:  ", e);
                return false;
            }
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 2) {
                Bundle data = message.getData();
                ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) data.getParcelable(CPanelService.CREATE_PANEL_APP_KEY_FD);
                Bundle bundle = data.getBundle(CPanelService.CREATE_PANEL_APP_KEY_BUNDLE);
                PanelServiceHelpers.waitForDebuggerIfNeeded(bundle);
                String[] envArrayFromBundle = PanelServiceHelpers.envArrayFromBundle(bundle);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                arrayList.add(CPanelService.CREATE_PANEL_APP_DEFAULT_SURFACE);
                arrayList2.add((Surface) data.getParcelable(CPanelService.CREATE_PANEL_APP_KEY_SURFACE));
                for (String str : data.keySet()) {
                    if (str.startsWith(CPanelService.CREATE_PANEL_APP_SURFACE_KEY_PREFIX)) {
                        arrayList.add(str.substring(13));
                        arrayList2.add((Surface) data.getParcelable(str));
                    }
                }
                Log.i(CPanelService.TAG, String.format("Creating panel app with environment %s", PanelServiceHelpers.envArrayToString(envArrayFromBundle)));
                Surface[] surfaceArr = (Surface[]) arrayList2.toArray(new Surface[arrayList2.size()]);
                this.service.get().createPanelApp(parcelFileDescriptor.detachFd(), surfaceArr, (String[]) arrayList.toArray(new String[arrayList.size()]), envArrayFromBundle);
                for (Surface surface : surfaceArr) {
                    if (surface != null) {
                        surface.release();
                    }
                }
            } else if (i != 3) {
                super.handleMessage(message);
            } else {
                this.service.get().onShellTokenReady(message.getData().getBinder(CPanelService.ACTIVITY_TOKEN_KEY_TOKEN));
            }
        }
    }

    public void onCreate() {
        Log.i(TAG, "onCreate()");
        super.onCreate();
        this.messenger = new Messenger(new PanelMessageHandler(this));
        this.vrShellVerifier = new PanelVerifier(this, !Build.TYPE.equals(FitnessTrackerUserContract.User.TABLE_NAME));
        Log.i(TAG, "end of onCreate()");
    }

    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i(TAG, String.format("onStartCommand(%x, %d", Integer.valueOf(i), Integer.valueOf(i2)));
        return 2;
    }

    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind()");
        try {
            IBinder binder = this.messenger.getBinder();
            binder.linkToDeath(this, 0);
            return binder;
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to install DeathRecipient on Binder instance", e);
            return null;
        }
    }

    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind()");
        super.onUnbind(intent);
        nativeDestroyPanelApp();
        return false;
    }

    public void binderDied() {
        Log.w(TAG, "Binder died; destroying panel app");
        nativeDestroyPanelApp();
    }

    /* access modifiers changed from: protected */
    public String verifyVrShellUID(int i) {
        long nanoTime = System.nanoTime();
        SigCertVerifier.SigCertInfo checkApkSignature = this.vrShellVerifier.checkApkSignature(i);
        Log.i(TAG, String.format("verifyVrShellUID(Debug Signatures=%s, uid=%d) => apkSignatureResult=%s, time=%d ms", Boolean.toString(this.vrShellVerifier.areDebugSignaturesEnabled()), Integer.valueOf(i), checkApkSignature, Long.valueOf((System.nanoTime() - nanoTime) / 1000000)));
        if (!checkApkSignature.packageNames.contains("com.oculus.vrshell")) {
            return String.format("Expected connection to APK %s and got %s instead; failing authentication.", "com.oculus.vrshell", Collections.min(checkApkSignature.packageNames));
        } else if (checkApkSignature.isTrusted) {
            return null;
        } else {
            return String.format("Expected %s APK to be signed with trusted certificate; failing authentication.", "com.oculus.vrshell");
        }
    }

    /* access modifiers changed from: protected */
    public void createPanelApp(int i, Surface[] surfaceArr, String[] strArr, String[] strArr2) {
        nativeCreatePanelApp(i, surfaceArr, strArr, strArr2);
    }

    /* access modifiers changed from: protected */
    public String getDefaultLibraryName() {
        String[] split = getApplicationInfo().packageName.split("\\.");
        return split[split.length - 1];
    }
}
