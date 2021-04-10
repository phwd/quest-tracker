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
import com.oculus.panellib.SigCertVerifier;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;

public abstract class CPanelService extends Service implements IBinder.DeathRecipient {
    protected Messenger messenger = null;
    private PanelVerifier vrShellVerifier = null;

    private native void nativeCreatePanelApp(int i, Surface[] surfaceArr, String[] strArr, String[] strArr2);

    private native void nativeDestroyPanelApp();

    /* access modifiers changed from: private */
    public static class PanelMessageHandler extends Handler {
        private final WeakReference<CPanelService> service;

        PanelMessageHandler(CPanelService cps) {
            this.service = new WeakReference<>(cps);
        }

        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            String verificationError;
            CPanelService panelService = this.service.get();
            if (panelService != null) {
                verificationError = panelService.verifyVrShellUID(Binder.getCallingUid());
            } else {
                verificationError = "Service weak reference lost!";
            }
            if (verificationError == null) {
                return super.sendMessageAtTime(msg, uptimeMillis);
            }
            Log.e("CPanelService", "Error processing MSG_CREATE_PANEL_APP:  " + verificationError);
            try {
                ((ParcelFileDescriptor) msg.getData().getParcelable("panelServicePFD")).close();
            } catch (IOException e) {
                Log.e("CPanelService", "Error closing ParcelFileDescriptor after verification failure:  ", e);
            }
            return false;
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Qpl.ACTION_SUCCESS /*{ENCODED_INT: 2}*/:
                    Bundle b = msg.getData();
                    ParcelFileDescriptor panelFd = (ParcelFileDescriptor) b.getParcelable("panelServicePFD");
                    Bundle panelBundle = b.getBundle("panelBundle");
                    PanelServiceHelpers.waitForDebuggerIfNeeded(panelBundle);
                    String[] panelEnv = PanelServiceHelpers.envArrayFromBundle(panelBundle);
                    ArrayList<String> surfaceNames = new ArrayList<>();
                    ArrayList<Surface> surfaces = new ArrayList<>();
                    surfaceNames.add("#main");
                    surfaces.add((Surface) b.getParcelable("panelSurface"));
                    for (String keyName : b.keySet()) {
                        if (keyName.startsWith("panelSurface:")) {
                            surfaceNames.add(keyName.substring("panelSurface:".length()));
                            surfaces.add((Surface) b.getParcelable(keyName));
                        }
                    }
                    Log.i("CPanelService", String.format("Creating panel app with environment %s", PanelServiceHelpers.envArrayToString(panelEnv)));
                    Surface[] surfacesArr = (Surface[]) surfaces.toArray(new Surface[surfaces.size()]);
                    this.service.get().createPanelApp(panelFd.detachFd(), surfacesArr, (String[]) surfaceNames.toArray(new String[surfaceNames.size()]), panelEnv);
                    for (Surface surface : surfacesArr) {
                        if (surface != null) {
                            surface.release();
                        }
                    }
                    return;
                case 3:
                    this.service.get().onShellTokenReady(msg.getData().getBinder("panelToken"));
                    return;
                default:
                    super.handleMessage(msg);
                    return;
            }
        }
    }

    public void onCreate() {
        Log.i("CPanelService", "onCreate()");
        super.onCreate();
        this.messenger = new Messenger(new PanelMessageHandler(this));
        this.vrShellVerifier = new PanelVerifier(this, !Build.TYPE.equals("user"));
        Log.i("CPanelService", "end of onCreate()");
    }

    public void onDestroy() {
        Log.i("CPanelService", "onDestroy()");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("CPanelService", String.format("onStartCommand(%x, %d", Integer.valueOf(flags), Integer.valueOf(startId)));
        return 2;
    }

    public IBinder onBind(Intent intent) {
        Log.i("CPanelService", "onBind()");
        try {
            IBinder ib = this.messenger.getBinder();
            ib.linkToDeath(this, 0);
            return ib;
        } catch (RemoteException e) {
            Log.e("CPanelService", "Failed to install DeathRecipient on Binder instance", e);
            return null;
        }
    }

    public boolean onUnbind(Intent intent) {
        Log.i("CPanelService", "onUnbind()");
        super.onUnbind(intent);
        nativeDestroyPanelApp();
        return false;
    }

    public void binderDied() {
        Log.w("CPanelService", "Binder died; destroying panel app");
        nativeDestroyPanelApp();
    }

    /* access modifiers changed from: protected */
    public String verifyVrShellUID(int uid) {
        long startTime = System.nanoTime();
        SigCertVerifier.SigCertInfo sci = this.vrShellVerifier.checkApkSignature(uid);
        Log.i("CPanelService", String.format("verifyVrShellUID(Debug Signatures=%s, uid=%d) => apkSignatureResult=%s, time=%d ms", Boolean.toString(this.vrShellVerifier.areDebugSignaturesEnabled()), Integer.valueOf(uid), sci, Long.valueOf((System.nanoTime() - startTime) / 1000000)));
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
    public void createPanelApp(int fd, Surface[] surfaces, String[] surfaceNames, String[] env) {
        nativeCreatePanelApp(fd, surfaces, surfaceNames, env);
    }
}
