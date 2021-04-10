package com.oculus.panellib;

import X.AnonymousClass006;
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
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;
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
    public static final String TAG = "CPanelService";
    public Messenger messenger = null;
    public PanelVerifier vrShellVerifier = null;

    public static class PanelMessageHandler extends Handler {
        public final WeakReference<CPanelService> service;

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 2) {
                Bundle data = message.getData();
                ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) data.getParcelable("panelServicePFD");
                Parcelable parcelable = data.getParcelable("panelSurface");
                Bundle bundle = data.getBundle("panelBundle");
                PanelServiceHelpers.waitForDebuggerIfNeeded(bundle);
                String[] envArrayFromBundle = PanelServiceHelpers.envArrayFromBundle(bundle);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                arrayList.add("#main");
                arrayList2.add(parcelable);
                for (String str : data.keySet()) {
                    if (str.startsWith("panelSurface:")) {
                        arrayList.add(str.substring(13));
                        arrayList2.add(data.getParcelable(str));
                    }
                }
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
                Bundle data2 = message.getData();
                this.service.get();
                data2.getBinder(CPanelService.ACTIVITY_TOKEN_KEY_TOKEN);
            }
        }

        public boolean sendMessageAtTime(Message message, long j) {
            String str;
            CPanelService cPanelService = this.service.get();
            if (cPanelService != null) {
                str = cPanelService.verifyVrShellUID(Binder.getCallingUid());
                if (str == null) {
                    return super.sendMessageAtTime(message, j);
                }
            } else {
                str = "Service weak reference lost!";
            }
            Log.e("CPanelService", AnonymousClass006.A07("Error processing MSG_CREATE_PANEL_APP:  ", str));
            try {
                ((ParcelFileDescriptor) message.getData().getParcelable("panelServicePFD")).close();
                return false;
            } catch (IOException e) {
                Log.e("CPanelService", "Error closing ParcelFileDescriptor after verification failure:  ", e);
                return false;
            }
        }

        public PanelMessageHandler(CPanelService cPanelService) {
            this.service = new WeakReference<>(cPanelService);
        }
    }

    private native void nativeCreatePanelApp(int i, Surface[] surfaceArr, String[] strArr, String[] strArr2);

    private native void nativeDestroyPanelApp();

    public void onShellTokenReady(IBinder iBinder) {
    }

    public void binderDied() {
        Log.w("CPanelService", "Binder died; destroying panel app");
        nativeDestroyPanelApp();
    }

    public IBinder onBind(Intent intent) {
        try {
            IBinder binder = this.messenger.getBinder();
            binder.linkToDeath(this, 0);
            return binder;
        } catch (RemoteException e) {
            Log.e("CPanelService", "Failed to install DeathRecipient on Binder instance", e);
            return null;
        }
    }

    public String getDefaultLibraryName() {
        String[] split = getApplicationInfo().packageName.split("\\.");
        return split[split.length - 1];
    }

    public void onCreate() {
        super.onCreate();
        this.messenger = new Messenger(new PanelMessageHandler(this));
        this.vrShellVerifier = new PanelVerifier(this, !Build.TYPE.equals("user"));
    }

    public void onDestroy() {
    }

    public boolean onUnbind(Intent intent) {
        super.onUnbind(intent);
        nativeDestroyPanelApp();
        return false;
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

    public void createPanelApp(int i, Surface[] surfaceArr, String[] strArr, String[] strArr2) {
        nativeCreatePanelApp(i, surfaceArr, strArr, strArr2);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }
}
