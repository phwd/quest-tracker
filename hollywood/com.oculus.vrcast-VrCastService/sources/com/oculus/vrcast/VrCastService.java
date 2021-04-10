package com.oculus.vrcast;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.common.collect.HashMultimap;
import com.oculus.trustedcallerverifier.SignatureHelper;
import com.oculus.trustedcallerverifier.TrustedCallerVerifier;
import oculus.internal.Gatekeeper;
import oculus.internal.IVrCastCallback;
import oculus.internal.IVrCastService;
import oculus.internal.VrCastDevice;

public class VrCastService extends Service {
    private static final boolean DEBUG = false;
    private static final boolean IS_DEBUG_BUILD = (!Build.TYPE.equals("user"));
    private static final String KEY_CALLER_CONTEXT = "caller_context";
    private static final String KEY_ERROR_MESSAGE = "error_message";
    private static final int MSG_REGISTER_CALLBACK = 5;
    private static final int MSG_START_CAST = 3;
    private static final int MSG_START_DISCOVERY = 1;
    private static final int MSG_STOP_CAST = 4;
    private static final int MSG_STOP_CAST_WITH_ERROR = 7;
    private static final int MSG_STOP_DISCOVERY = 2;
    private static final int MSG_UNREGISTER_CALLBACK = 6;
    private static final String TAG = "VrCastService";
    private Handler mHandler;
    private HandlerThread mThread = new HandlerThread("vrcast_handler_t");
    private VrCastDeviceStore sVrCastDeviceStore;

    public IBinder onBind(Intent intent) {
        ensureHandlerThreadStart();
        return new LocalBinder(this);
    }

    private void ensureHandlerThreadStart() {
        this.sVrCastDeviceStore = VrCastDeviceStore.get(this);
        this.mThread.start();
        this.mHandler = new CastHandler(this.mThread.getLooper());
    }

    /* access modifiers changed from: private */
    public class CastHandler extends Handler {
        public CastHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            String string = message.getData().getString(VrCastService.KEY_CALLER_CONTEXT);
            if (TextUtils.isEmpty(string)) {
                string = "vrui";
            }
            switch (message.what) {
                case 1:
                    VrCastService.this.sVrCastDeviceStore.getTelemetry().setCallerContext(string);
                    VrCastService.this.sVrCastDeviceStore.startDiscovery(VrCastService.this);
                    return;
                case 2:
                    VrCastService.this.sVrCastDeviceStore.getTelemetry().setCallerContext(string);
                    VrCastService.this.sVrCastDeviceStore.stopDiscovery(VrCastService.this);
                    return;
                case 3:
                    VrCastService.this.sVrCastDeviceStore.getTelemetry().setCallerContext(string);
                    VrCastService.this.sVrCastDeviceStore.startCast(VrCastService.this, (String) message.obj);
                    return;
                case 4:
                    VrCastService.this.sVrCastDeviceStore.getTelemetry().setCallerContext(string);
                    VrCastService.this.sVrCastDeviceStore.stopCast(VrCastService.this, (String) message.obj);
                    return;
                case 5:
                    VrCastService.this.sVrCastDeviceStore.registerVrCastCallback((IVrCastCallback) message.obj);
                    return;
                case 6:
                    VrCastService.this.sVrCastDeviceStore.unregisterVrCastCallback((IVrCastCallback) message.obj);
                    return;
                case 7:
                    VrCastService.this.sVrCastDeviceStore.getTelemetry().setCallerContext(string);
                    String string2 = message.getData().getString(VrCastService.KEY_ERROR_MESSAGE);
                    VrCastService.this.sVrCastDeviceStore.stopCastWithError(VrCastService.this, (String) message.obj, string2);
                    return;
                default:
                    return;
            }
        }
    }

    public class LocalBinder extends IVrCastService.Stub {
        private final boolean isRetailDemo;
        private final TrustedCallerVerifier trustedCallerVerifier;

        public LocalBinder(Context context) {
            HashMultimap create = HashMultimap.create();
            create.put(SignatureHelper.OCULUS_CORE_APPS_RELEASE_SIGNATURE, "com.oculus.horizon");
            create.put(SignatureHelper.OCULUS_CORE_APPS_RELEASE_SIGNATURE, "com.oculus.vrshell");
            create.put(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, "com.oculus.vrshell");
            create.put(SignatureHelper.OCULUS_CORE_SYSTEM_APPS_RELEASE_SIG, "com.oculus.systemux");
            if (VrCastService.IS_DEBUG_BUILD) {
                create.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.horizon");
                create.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.vrshell");
                create.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.systemux");
            }
            this.trustedCallerVerifier = new TrustedCallerVerifier(create, context, context.getPackageManager());
            this.isRetailDemo = new Gatekeeper(20).isEnabled();
        }

        @Override // oculus.internal.IVrCastService
        public void registerCallback(IVrCastCallback iVrCastCallback) {
            sendMessageToTarget(null, 5, iVrCastCallback);
        }

        @Override // oculus.internal.IVrCastService
        public void unregisterCallback(IVrCastCallback iVrCastCallback) {
            sendMessageToTarget(null, 6, iVrCastCallback);
        }

        @Override // oculus.internal.IVrCastService
        public void startDiscovery(String str) {
            sendMessageToTarget(str, 1, null);
        }

        @Override // oculus.internal.IVrCastService
        public void stopDiscovery(String str) {
            sendMessageToTarget(str, 2, null);
        }

        @Override // oculus.internal.IVrCastService
        public void startCast(String str, String str2, boolean z) {
            sendMessageToTarget(str2, 3, str);
        }

        @Override // oculus.internal.IVrCastService
        public void stopCast(String str, String str2) {
            sendMessageToTarget(str2, 4, str);
        }

        @Override // oculus.internal.IVrCastService
        public void stopCastWithError(String str, String str2, String str3) {
            sendMessageToTarget(str2, 7, str, str3);
        }

        @Override // oculus.internal.IVrCastService
        public VrCastDevice getCurrentDevice() throws RemoteException {
            return VrCastDeviceStore.toVrCastDevice(VrCastService.this.sVrCastDeviceStore.getActiveCastDevice());
        }

        private void sendMessageToTarget(String str, int i, Object obj) {
            sendMessageToTarget(str, i, obj, null);
        }

        private void sendMessageToTarget(String str, int i, Object obj, String str2) {
            if (!this.isRetailDemo) {
                this.trustedCallerVerifier.enforceTrustedCaller();
            }
            Message obtain = Message.obtain(VrCastService.this.mHandler, i, obj);
            Bundle bundle = new Bundle();
            bundle.putString(VrCastService.KEY_CALLER_CONTEXT, str);
            bundle.putString(VrCastService.KEY_ERROR_MESSAGE, str2);
            obtain.setData(bundle);
            obtain.sendToTarget();
        }
    }
}
