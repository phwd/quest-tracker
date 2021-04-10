package com.oculus.vrcast.wfd;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;
import com.oculus.vrcast.CastDevice;
import com.oculus.vrcast.DiscoverProtocol;
import com.oculus.vrcast.OVRMediaServiceIntentHelper;
import com.oculus.vrcast.VrCastAdapter;
import com.oculus.vrcast.VrCastDeviceStore;
import com.oculus.vrcast.telemetry.VrCastTelemetry;
import com.oculus.vrcast.wfd.net.ResultListener;
import com.oculus.vrcast.wfd.net.ServiceUtil;
import com.oculus.vrcast.wfd.net.WifiConstants;
import com.oculus.vrcast.wfd.net.WifiP2pNetDevice;
import com.oculus.vrcast.wfd.net.WifiP2pNetManager;
import com.qualcomm.wfd.WfdDevice;
import com.qualcomm.wfd.WfdEnums$AVPlaybackMode;
import com.qualcomm.wfd.WfdEnums$ErrorType;
import com.qualcomm.wfd.WfdEnums$SessionState;
import com.qualcomm.wfd.WfdEnums$WFDDeviceType;
import com.qualcomm.wfd.WfdStatus;
import com.qualcomm.wfd.service.IWfdActionListener;
import java.util.ArrayList;
import java.util.Collection;

public class VrCastWfdAdapter implements VrCastAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "VrCastWfdAdapter";
    private IWfdActionListener mActionListener;
    private WfdCastDevice mCancellingDevice;
    private boolean mCastingActive;
    private WfdCastDevice mConnectedDevice;
    private WfdCastDevice mConnectingDevice;
    private Context mContext;
    private WfdCastDevice mDesiredDevice;
    private WfdCastDevice mDisconnectingDevice;
    private boolean mDiscoverInProgress = DEBUG;
    private boolean mDiscoverRequested = DEBUG;
    private EventHandler mHandler;
    private boolean mServiceInitialized = DEBUG;
    private boolean mTearingDown;
    private final VrCastTelemetry mTelemetry;
    private final VrCastDeviceStore mVrCastDeviceStore;
    private WifiP2pNetManager mWifiP2pNetManager;

    public VrCastWfdAdapter(Context context, VrCastDeviceStore vrCastDeviceStore, VrCastTelemetry vrCastTelemetry) {
        this.mContext = context;
        this.mHandler = new EventHandler();
        this.mVrCastDeviceStore = vrCastDeviceStore;
        this.mTelemetry = vrCastTelemetry;
        ensureServiceInitialized();
    }

    /* access modifiers changed from: private */
    public static class VrCastWfdActionListenerImpl extends IWfdActionListener.Stub {
        Handler mHandler;

        @Override // com.qualcomm.wfd.service.IWfdActionListener
        public void notifyEvent(int i, int i2) throws RemoteException {
        }

        @Override // com.qualcomm.wfd.service.IWfdActionListener
        public void onStateUpdate(int i, int i2) throws RemoteException {
        }

        VrCastWfdActionListenerImpl(Handler handler) {
            this.mHandler = handler;
        }

        @Override // com.qualcomm.wfd.service.IWfdActionListener
        public void notify(Bundle bundle, int i) throws RemoteException {
            if (bundle != null && "MMStreamStarted".equalsIgnoreCase(bundle.getString("event"))) {
                Message obtainMessage = this.mHandler.obtainMessage(WifiConstants.RECEIVED_SURFACE);
                obtainMessage.setData(bundle);
                this.mHandler.sendMessage(obtainMessage);
            }
        }
    }

    public boolean isDiscoveryRequested() {
        return this.mDiscoverRequested;
    }

    public boolean isDiscoverInProgress() {
        return this.mDiscoverInProgress;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateCastDeviceList(Collection<WifiP2pNetDevice> collection) {
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            for (WifiP2pNetDevice wifiP2pNetDevice : collection) {
                if (deviceSupportVrCast(wifiP2pNetDevice)) {
                    arrayList.add(new WfdCastDevice(wifiP2pNetDevice));
                }
            }
        }
        this.mVrCastDeviceStore.setDeviceList(arrayList, DiscoverProtocol.WIFI_P2P);
    }

    @Override // com.oculus.vrcast.VrCastAdapter
    public void startDiscovery() {
        if (!this.mDiscoverRequested) {
            this.mDiscoverRequested = true;
            updateDiscoverState();
            return;
        }
        Log.d(TAG, "startDiscovery() called, but discover in progress, return");
    }

    @Override // com.oculus.vrcast.VrCastAdapter
    public void stopDiscovery() {
        if (this.mDiscoverRequested) {
            this.mDiscoverRequested = DEBUG;
            updateDiscoverState();
        }
    }

    private boolean deviceSupportVrCast(WifiP2pNetDevice wifiP2pNetDevice) {
        if (wifiP2pNetDevice.isLocalDevice() || !wifiP2pNetDevice.isWifiDisplay()) {
            return DEBUG;
        }
        return true;
    }

    private void ensureServiceInitialized() {
        this.mWifiP2pNetManager = new WifiP2pNetManager(this.mHandler, this.mContext);
        if (!this.mServiceInitialized) {
            try {
                ServiceUtil.bindService(this.mContext.getApplicationContext(), this.mHandler);
                this.mServiceInitialized = true;
            } catch (ServiceUtil.ServiceFailedToBindException unused) {
                Log.e(TAG, "ServiceFailedToBindException received");
            }
        }
    }

    @Override // com.oculus.vrcast.VrCastAdapter
    public void connect(CastDevice castDevice) {
        if (!(castDevice instanceof WfdCastDevice)) {
            Log.e(TAG, "connect failed, expected device with type: " + DiscoverProtocol.WIFI_P2P);
            return;
        }
        WfdCastDevice wfdCastDevice = this.mDesiredDevice;
        if (wfdCastDevice == null) {
            if (wfdCastDevice != null || (this.mDisconnectingDevice == null && this.mCancellingDevice == null)) {
                this.mDesiredDevice = (WfdCastDevice) castDevice;
                lambda$onError$0$VrCastWfdAdapter();
            }
        }
    }

    private void ensureDiscoveryStarted() {
        if (!this.mDiscoverInProgress) {
            this.mDiscoverInProgress = true;
            discoverPeers();
        }
    }

    private void ensureDiscoveryStopped() {
        if (this.mDiscoverInProgress) {
            this.mDiscoverInProgress = DEBUG;
            stopDiscoverPeers();
        }
    }

    private void updateDiscoverState() {
        if (this.mDesiredDevice != null || this.mConnectedDevice != null || this.mConnectingDevice != null || this.mDisconnectingDevice != null || this.mCancellingDevice != null) {
            WfdCastDevice wfdCastDevice = this.mDesiredDevice;
            if (wfdCastDevice == null || WfdCastDevice.deviceEquals(wfdCastDevice, this.mConnectedDevice)) {
                ensureDiscoveryStopped();
            }
        } else if (this.mDiscoverRequested) {
            ensureDiscoveryStarted();
        } else {
            ensureDiscoveryStopped();
        }
    }

    private void discoverPeers() {
        this.mWifiP2pNetManager.send_wfd_set(true, WfdEnums$WFDDeviceType.SOURCE);
        this.mWifiP2pNetManager.startBroadcastListening();
        this.mWifiP2pNetManager.discoveryPeers(new ResultListener() {
            /* class com.oculus.vrcast.wfd.VrCastWfdAdapter.AnonymousClass1 */

            @Override // com.oculus.vrcast.wfd.net.ResultListener
            public void onSuccess(Object obj) {
                if (VrCastWfdAdapter.this.mDiscoverInProgress) {
                    VrCastWfdAdapter.this.mWifiP2pNetManager.requestPeers();
                }
            }

            @Override // com.oculus.vrcast.wfd.net.ResultListener
            public void onFailure(Object obj) {
                Log.e(VrCastWfdAdapter.TAG, "wfd service discovering peers failed.");
                VrCastWfdAdapter.this.onError(100, obj != null ? obj.toString() : null);
            }
        });
    }

    private void stopDiscoverPeers() {
        this.mWifiP2pNetManager.stopPeerDiscovery(ResultListener.NullListener);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: proceedConnectStep */
    public void lambda$onError$0$VrCastWfdAdapter() {
        try {
            updateDiscoverState();
            if (!WfdCastDevice.deviceEquals(this.mConnectedDevice, this.mDesiredDevice)) {
                if (this.mCastingActive) {
                    OVRMediaServiceIntentHelper.sendStopSurfaceCaptureIntent(this.mContext, getClass().getSimpleName());
                    this.mCastingActive = DEBUG;
                    this.mWifiP2pNetManager.setMiracastMode(0);
                }
                if (ServiceUtil.getServiceAlreadyBound()) {
                    try {
                        WfdStatus status = ServiceUtil.getInstance().getStatus();
                        if (!(status == null || status.state == WfdEnums$SessionState.INVALID.ordinal() || status.state == WfdEnums$SessionState.INITIALIZED.ordinal() || status.state == WfdEnums$SessionState.TEARING_DOWN.ordinal())) {
                            this.mTearingDown = true;
                            updateCurrentDeviceState();
                            ServiceUtil.getInstance().teardown();
                            this.mTearingDown = DEBUG;
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (this.mDisconnectingDevice != null) {
                updateCurrentDeviceState();
            } else if (this.mConnectedDevice != null && !WfdCastDevice.deviceEquals(this.mConnectedDevice, this.mDesiredDevice)) {
                this.mDisconnectingDevice = this.mConnectedDevice;
                this.mConnectedDevice = null;
                final WfdCastDevice wfdCastDevice = this.mDisconnectingDevice;
                this.mWifiP2pNetManager.disconnect(new ResultListener() {
                    /* class com.oculus.vrcast.wfd.VrCastWfdAdapter.AnonymousClass2 */

                    @Override // com.oculus.vrcast.wfd.net.ResultListener
                    public void onSuccess(Object obj) {
                        next();
                    }

                    @Override // com.oculus.vrcast.wfd.net.ResultListener
                    public void onFailure(Object obj) {
                        Log.e(VrCastWfdAdapter.TAG, "disconnect failed with err=" + obj);
                        VrCastWfdAdapter.this.onError(135, obj != null ? obj.toString() : null);
                        next();
                    }

                    private void next() {
                        if (WfdCastDevice.deviceEquals(VrCastWfdAdapter.this.mDisconnectingDevice, wfdCastDevice)) {
                            VrCastWfdAdapter.this.mDisconnectingDevice = null;
                            VrCastWfdAdapter.this.lambda$onError$0$VrCastWfdAdapter();
                        }
                    }
                });
                updateCurrentDeviceState();
            } else if (this.mCancellingDevice != null) {
                updateCurrentDeviceState();
            } else if (this.mConnectingDevice != null && !WfdCastDevice.deviceEquals(this.mConnectingDevice, this.mDesiredDevice)) {
                this.mCancellingDevice = this.mConnectingDevice;
                this.mConnectingDevice = null;
                final WfdCastDevice wfdCastDevice2 = this.mCancellingDevice;
                this.mWifiP2pNetManager.cancelConnect(new ResultListener() {
                    /* class com.oculus.vrcast.wfd.VrCastWfdAdapter.AnonymousClass3 */

                    @Override // com.oculus.vrcast.wfd.net.ResultListener
                    public void onSuccess(Object obj) {
                        next();
                    }

                    @Override // com.oculus.vrcast.wfd.net.ResultListener
                    public void onFailure(Object obj) {
                        Log.e(VrCastWfdAdapter.TAG, "Failed to cancel connection to Wifi display: " + wfdCastDevice2.getName() + ", reason=" + obj);
                        VrCastWfdAdapter.this.onError(134, obj != null ? obj.toString() : null);
                        next();
                    }

                    private void next() {
                        if (WfdCastDevice.deviceEquals(VrCastWfdAdapter.this.mCancellingDevice, wfdCastDevice2)) {
                            VrCastWfdAdapter.this.mCancellingDevice = null;
                            VrCastWfdAdapter.this.lambda$onError$0$VrCastWfdAdapter();
                        }
                    }
                });
                updateCurrentDeviceState();
            } else if (this.mDesiredDevice == null) {
                updateCurrentDeviceState();
            } else if (this.mConnectingDevice == null && this.mConnectedDevice == null) {
                this.mConnectingDevice = this.mDesiredDevice;
                connectInternal(this.mConnectingDevice);
                updateCurrentDeviceState();
            } else {
                if (this.mConnectedDevice != null && !this.mCastingActive) {
                    startSession(this.mConnectedDevice);
                }
                updateCurrentDeviceState();
            }
        } catch (Throwable th) {
            updateCurrentDeviceState();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateCurrentDeviceState() {
        CastDevice activeCastDevice = this.mVrCastDeviceStore.getActiveCastDevice();
        if (activeCastDevice == null || (activeCastDevice instanceof WfdCastDevice)) {
            WfdCastDevice wfdCastDevice = (WfdCastDevice) activeCastDevice;
            WfdCastDevice wfdCastDevice2 = this.mDesiredDevice;
            if (wfdCastDevice2 != null) {
                if (!WfdCastDevice.deviceEquals(wfdCastDevice2, wfdCastDevice)) {
                    this.mVrCastDeviceStore.setActiveCastDevice(this.mDesiredDevice);
                }
                if (this.mConnectingDevice != null) {
                    this.mVrCastDeviceStore.updateActiveCastDeviceState(CastDevice.State.CONNECTING_TO_PEER);
                } else if (this.mConnectedDevice == null) {
                } else {
                    if (!this.mCastingActive) {
                        this.mVrCastDeviceStore.updateActiveCastDeviceState(CastDevice.State.STARTING_SESSION);
                    } else {
                        this.mVrCastDeviceStore.updateActiveCastDeviceState(CastDevice.State.CASTING);
                    }
                }
            } else if (!this.mTearingDown && this.mCancellingDevice == null && this.mDisconnectingDevice == null) {
                this.mVrCastDeviceStore.setActiveCastDevice(null);
            } else {
                this.mVrCastDeviceStore.updateActiveCastDeviceState(CastDevice.State.DISCONNECTING);
            }
        } else {
            Log.e(TAG, "called updateCurrentDeviceState when current device is not a wfd device.");
        }
    }

    private void connectInternal(final WfdCastDevice wfdCastDevice) {
        if (!wfdCastDevice.getNetDevice().supportWfd() || !wfdCastDevice.getNetDevice().isWfdSessionAvailable()) {
            Log.e(TAG, "Device does not support wfd!");
            this.mConnectingDevice = null;
            onError(132, "Device does not support wfd");
            return;
        }
        wfdCastDevice.getNetDevice().connect(new ResultListener() {
            /* class com.oculus.vrcast.wfd.VrCastWfdAdapter.AnonymousClass4 */

            @Override // com.oculus.vrcast.wfd.net.ResultListener
            public void onSuccess(Object obj) {
                Log.d(VrCastWfdAdapter.TAG, "connect to device initiation completed!");
            }

            @Override // com.oculus.vrcast.wfd.net.ResultListener
            public void onFailure(Object obj) {
                Log.e(VrCastWfdAdapter.TAG, "onError " + obj);
                if (WfdCastDevice.deviceEquals(wfdCastDevice, VrCastWfdAdapter.this.mConnectingDevice)) {
                    String str = null;
                    VrCastWfdAdapter.this.mConnectingDevice = null;
                    VrCastWfdAdapter vrCastWfdAdapter = VrCastWfdAdapter.this;
                    if (obj != null) {
                        str = obj.toString();
                    }
                    vrCastWfdAdapter.onError(132, str);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onConnectionInfoAvailable() {
        WfdCastDevice wfdCastDevice = this.mConnectingDevice;
        if (wfdCastDevice != null && WfdCastDevice.deviceEquals(wfdCastDevice, this.mDesiredDevice)) {
            this.mConnectedDevice = this.mConnectingDevice;
            this.mConnectingDevice = null;
            lambda$onError$0$VrCastWfdAdapter();
        }
    }

    @Override // com.oculus.vrcast.VrCastAdapter
    public void disconnect(CastDevice castDevice) {
        disconnect();
    }

    public void disconnect() {
        this.mDesiredDevice = null;
        lambda$onError$0$VrCastWfdAdapter();
    }

    private void startSession(WfdCastDevice wfdCastDevice) {
        if (!wfdCastDevice.canStartSession()) {
            Log.e(TAG, "startSession() failed because device is not allowed to start session");
            onError(132, "device is not allowed to start session");
            return;
        }
        WifiP2pNetDevice netDevice = wfdCastDevice.getNetDevice();
        WifiP2pNetDevice localDevice = netDevice.getNetTypeManager().getLocalDevice();
        if (localDevice == null) {
            Log.e(TAG, "Error localDevInfo is null");
            onError(131, "localDevInfo is null");
        } else if (!localDevice.hasDeviceInfo()) {
            Log.e(TAG, "No device info yet");
            onError(131, "No device info");
        } else {
            this.mWifiP2pNetManager.setMiracastMode(1);
            if (ServiceUtil.getServiceAlreadyBound()) {
                try {
                    ServiceUtil.getInstance().setAvPlaybackMode(WfdEnums$AVPlaybackMode.AUDIO_VIDEO.ordinal());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            AnonymousClass5 r1 = new WfdStartSessionTask() {
                /* class com.oculus.vrcast.wfd.VrCastWfdAdapter.AnonymousClass5 */

                /* access modifiers changed from: protected */
                public void onPostExecute(Integer num) {
                    if (num.intValue() < 0 || num.intValue() == WfdEnums$ErrorType.OPERATION_TIMED_OUT.getCode()) {
                        Log.e(VrCastWfdAdapter.TAG, "WfdStartSessionTask: onPostExecute- sessionId < 0, Failed to start session with error: " + num);
                        VrCastWfdAdapter vrCastWfdAdapter = VrCastWfdAdapter.this;
                        vrCastWfdAdapter.onError(132, "sessionId is invalid: " + num);
                    }
                }
            };
            WfdStartSessionParams wfdStartSessionParams = new WfdStartSessionParams();
            wfdStartSessionParams.mDevice = netDevice;
            wfdStartSessionParams.mLocalDevice = localDevice;
            r1.execute(wfdStartSessionParams);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onServiceBound() {
        this.mActionListener = new VrCastWfdActionListenerImpl(this.mHandler);
        try {
            int deviceType = ServiceUtil.getInstance().setDeviceType(WfdEnums$WFDDeviceType.SOURCE.getCode());
            if (deviceType != 0) {
                Log.e(TAG, "WfdService.setDeviceType failed with error code: " + deviceType);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "WfdService init() failed", e);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void deinitService() {
        if (ServiceUtil.getServiceAlreadyBound()) {
            try {
                ServiceUtil.getInstance().deinit();
            } catch (RemoteException e) {
                Log.e(TAG, "deinitService: ", e);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onError(int i, String str) {
        this.mVrCastDeviceStore.sendError(i, null);
        this.mTelemetry.onWfdConnectionError(i, str);
        this.mDesiredDevice = null;
        this.mHandler.post(new Runnable() {
            /* class com.oculus.vrcast.wfd.$$Lambda$VrCastWfdAdapter$4tag5i_95wyrxxtjwFZVEM9G1Y */

            public final void run() {
                VrCastWfdAdapter.this.lambda$onError$0$VrCastWfdAdapter();
            }
        });
    }

    /* access modifiers changed from: private */
    public class EventHandler extends Handler {
        private EventHandler() {
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 4) {
                VrCastWfdAdapter.this.disconnect();
                VrCastWfdAdapter.this.deinitService();
            } else if (i == 7) {
                VrCastWfdAdapter.this.onServiceBound();
            } else if (i == 114) {
                Bundle data = message.getData();
                onSurfaceReceived((Surface) data.getParcelable("surface"), data.getInt("width", -1), data.getInt("height", -1));
            } else if (i != 201 && i != 208) {
                switch (i) {
                    case WifiConstants.WIFI_UTIL_P2P_STATE_CHANGED /*{ENCODED_INT: 203}*/:
                    case WifiConstants.WIFI_UTIL_DISCONNECTED /*{ENCODED_INT: 204}*/:
                    default:
                        return;
                    case WifiConstants.WIFI_UTIL_CONNECTION_INFO /*{ENCODED_INT: 205}*/:
                        VrCastWfdAdapter.this.onConnectionInfoAvailable();
                        return;
                    case WifiConstants.WIFI_UTIL_PEERS_CHANGED /*{ENCODED_INT: 206}*/:
                        VrCastWfdAdapter.this.updateCastDeviceList((Collection) message.obj);
                        return;
                }
            }
        }

        private void onSurfaceReceived(Surface surface, int i, int i2) {
            VrCastWfdAdapter.this.mCastingActive = true;
            VrCastWfdAdapter.this.updateCurrentDeviceState();
            OVRMediaServiceIntentHelper.sendStartSurfaceCaptureIntent(VrCastWfdAdapter.this.mContext, surface, EventHandler.class.getSimpleName());
            VrCastWfdAdapter.this.mTelemetry.onSurfaceReceived(VrCastWfdAdapter.this.mVrCastDeviceStore.getActiveCastDevice(), i, i2);
        }
    }

    /* access modifiers changed from: private */
    public static class WfdStartSessionParams {
        public WifiP2pNetDevice mDevice;
        public WifiP2pNetDevice mLocalDevice;

        private WfdStartSessionParams() {
        }
    }

    private class WfdStartSessionTask extends AsyncTask<WfdStartSessionParams, Void, Integer> {
        /* access modifiers changed from: protected */
        public void onCancelled() {
        }

        private WfdStartSessionTask() {
        }

        /* access modifiers changed from: protected */
        public Integer doInBackground(WfdStartSessionParams... wfdStartSessionParamsArr) {
            int i = 0;
            WfdStartSessionParams wfdStartSessionParams = wfdStartSessionParamsArr[0];
            if (!ServiceUtil.getServiceAlreadyBound()) {
                Log.e(VrCastWfdAdapter.TAG, "Service is not already bound, do it now");
                try {
                    ServiceUtil.bindService(VrCastWfdAdapter.this.mContext, VrCastWfdAdapter.this.mHandler);
                } catch (ServiceUtil.ServiceFailedToBindException unused) {
                    Log.e(VrCastWfdAdapter.TAG, "ServiceFailedToBindException received");
                }
            }
            WifiP2pNetDevice wifiP2pNetDevice = wfdStartSessionParams.mDevice;
            int i2 = -1;
            if (!wifiP2pNetDevice.supportWfd()) {
                Log.e(VrCastWfdAdapter.TAG, "WfdStartSessionTask - doInBackground - error - does not support wfd!");
                return -1;
            }
            try {
                if (!wifiP2pNetDevice.getNetTypeManager().getLocalDevice().hasDeviceInfo()) {
                    Log.e(VrCastWfdAdapter.TAG, "WfdStartSessionTask - doInBackground - error - no local device info!");
                    return -1;
                }
                WfdDevice convertToWfdDevice = wfdStartSessionParams.mDevice.convertToWfdDevice();
                convertToWfdDevice.decoderLatency = 5;
                WfdDevice convertToWfdDevice2 = wfdStartSessionParams.mLocalDevice.convertToWfdDevice();
                WifiP2pNetManager unused2 = VrCastWfdAdapter.this.mWifiP2pNetManager;
                convertToWfdDevice2.ipAddress = WifiP2pNetManager.getLocalIp();
                try {
                    i2 = ServiceUtil.getInstance().init(VrCastWfdAdapter.this.mActionListener, convertToWfdDevice2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (i2 == 0 || i2 == WfdEnums$ErrorType.ALREADY_INITIALIZED.getCode()) {
                    i = ServiceUtil.getInstance().startWfdSession(convertToWfdDevice);
                    return Integer.valueOf(i);
                }
                Log.e(VrCastWfdAdapter.TAG, "WfdStartSessionTask - doInBackground: init failed with error - " + i2);
                VrCastWfdAdapter vrCastWfdAdapter = VrCastWfdAdapter.this;
                vrCastWfdAdapter.onError(133, "ErrorType = " + i2);
                return -2;
            } catch (RemoteException e2) {
                Log.e(VrCastWfdAdapter.TAG, "Remote exception", e2);
            }
        }
    }
}
