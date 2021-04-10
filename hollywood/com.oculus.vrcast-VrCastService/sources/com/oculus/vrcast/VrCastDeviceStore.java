package com.oculus.vrcast;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.vrcast.CastDevice;
import com.oculus.vrcast.VrCastDeviceStore;
import com.oculus.vrcast.googlecast.GoogleCastAdapter;
import com.oculus.vrcast.telemetry.VrCastTelemetry;
import com.oculus.vrcast.wfd.VrCastWfdAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import oculus.internal.Gatekeeper;
import oculus.internal.IVrCastCallback;
import oculus.internal.VrCastDevice;

public class VrCastDeviceStore {
    private static final boolean DEBUG = false;
    private static final String TAG = "VrCastDeviceStore";
    private static VrCastDeviceStore sInstance;
    private volatile CastDevice mActiveVrCastDevice;
    private final Map<DiscoverProtocol, VrCastAdapter> mAdapterMap = new HashMap();
    private final Context mContext;
    private final Map<DiscoverProtocol, Map<String, CastDevice>> mDeviceMap = new HashMap();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final boolean mKeepConnectedOnActiveDeviceLost;
    private final Set<DiscoverProtocol> mSupportDiscoverProtocol = new HashSet();
    private final VrCastTelemetry mTelemetry;
    private final RemoteCallbackList<IVrCastCallback> mVrCastCallback = new RemoteCallbackList<>();

    /* access modifiers changed from: private */
    public interface VrCastCallbackCallable {
        void doCallCallback(IVrCastCallback iVrCastCallback) throws RemoteException;
    }

    private VrCastDeviceStore(Context context) {
        this.mContext = context;
        this.mTelemetry = new VrCastTelemetry(context);
        if (new Gatekeeper(9).isEnabled()) {
            Log.d(TAG, "Adding WFD protocol");
            this.mSupportDiscoverProtocol.add(DiscoverProtocol.WIFI_P2P);
        }
        this.mSupportDiscoverProtocol.add(DiscoverProtocol.GOOGLE_CAST);
        Log.d(TAG, "Total supported protocols: " + this.mSupportDiscoverProtocol.size());
        for (DiscoverProtocol discoverProtocol : this.mSupportDiscoverProtocol) {
            this.mDeviceMap.put(discoverProtocol, new LinkedHashMap());
        }
        this.mKeepConnectedOnActiveDeviceLost = new Gatekeeper(30).isEnabled();
        Log.d(TAG, "Should keep connection alive when active device lost: " + this.mKeepConnectedOnActiveDeviceLost);
    }

    public static VrCastDeviceStore get(Context context) {
        VrCastDeviceStore vrCastDeviceStore = sInstance;
        if (vrCastDeviceStore != null) {
            return vrCastDeviceStore;
        }
        sInstance = new VrCastDeviceStore(context);
        return sInstance;
    }

    /* access modifiers changed from: package-private */
    public void registerVrCastCallback(IVrCastCallback iVrCastCallback) {
        this.mVrCastCallback.register(iVrCastCallback);
        notifyDeviceChanged();
        if (this.mActiveVrCastDevice != null) {
            notifyDeviceStateUpdated();
        }
    }

    /* access modifiers changed from: package-private */
    public void unregisterVrCastCallback(IVrCastCallback iVrCastCallback) {
        this.mVrCastCallback.unregister(iVrCastCallback);
    }

    public void startDiscovery(Context context) {
        if (this.mActiveVrCastDevice != null) {
            Log.d(TAG, "There is active cast device connected, don't start discovery.");
            return;
        }
        for (DiscoverProtocol discoverProtocol : this.mSupportDiscoverProtocol) {
            this.mTelemetry.onStartDiscovery(discoverProtocol);
            getAdapterForProtocol(context, discoverProtocol).startDiscovery();
        }
    }

    public void stopDiscovery(Context context) {
        for (DiscoverProtocol discoverProtocol : this.mSupportDiscoverProtocol) {
            this.mTelemetry.onStopDiscovery(discoverProtocol);
            getAdapterForProtocol(context, discoverProtocol).stopDiscovery();
        }
    }

    private void pauseDiscovery() {
        for (DiscoverProtocol discoverProtocol : this.mSupportDiscoverProtocol) {
            if (!(this.mActiveVrCastDevice == null || this.mActiveVrCastDevice.mDiscoverProtocol == discoverProtocol)) {
                this.mTelemetry.onStopDiscovery(discoverProtocol);
                getAdapterForProtocol(this.mContext, discoverProtocol).stopDiscovery();
            }
        }
    }

    public void startCast(Context context, String str) {
        CastDevice castDeviceForId = getCastDeviceForId(str);
        if (castDeviceForId == null) {
            Log.e(TAG, "Dropping start cast request for id " + str);
            return;
        }
        this.mTelemetry.onCastingRequested(castDeviceForId, getDiscoveredPeersSize(castDeviceForId.mDiscoverProtocol));
        getAdapterForProtocol(context, castDeviceForId.mDiscoverProtocol).connect(castDeviceForId);
    }

    public void stopCast(Context context, String str) {
        CastDevice castDeviceToStop = getCastDeviceToStop(str);
        if (castDeviceToStop == null) {
            Log.e(TAG, "Dropping stop cast request for id " + str);
            return;
        }
        this.mTelemetry.onStopRequested(castDeviceToStop);
        getAdapterForProtocol(context, castDeviceToStop.mDiscoverProtocol).disconnect(castDeviceToStop);
    }

    public void stopCastWithError(Context context, String str, String str2) {
        CastDevice castDeviceToStop = getCastDeviceToStop(str);
        if (castDeviceToStop == null) {
            Log.e(TAG, "Dropping stop cast with error request for id " + str);
            return;
        }
        this.mTelemetry.onConnectionError(castDeviceToStop, castDeviceToStop.mDiscoverProtocol, 102, str2);
        getAdapterForProtocol(this.mContext, castDeviceToStop.mDiscoverProtocol).disconnect(castDeviceToStop);
    }

    private CastDevice getCastDeviceToStop(String str) {
        CastDevice castDeviceForId = getCastDeviceForId(str);
        if (castDeviceForId == null && (castDeviceForId = this.mActiveVrCastDevice) == null) {
            this.mTelemetry.onStopCastingFailed();
        }
        return castDeviceForId;
    }

    private synchronized int getDiscoveredPeersSize(DiscoverProtocol discoverProtocol) {
        Map<String, CastDevice> vrCastDevicesByProtocol = getVrCastDevicesByProtocol(discoverProtocol);
        if (vrCastDevicesByProtocol == null) {
            return 0;
        }
        return vrCastDevicesByProtocol.size();
    }

    public synchronized void addDevice(CastDevice castDevice) {
        getVrCastDevicesByProtocol(castDevice.mDiscoverProtocol).put(castDevice.getId(), castDevice);
        notifyDeviceChanged();
    }

    public synchronized void removeDevice(CastDevice castDevice) {
        if (this.mActiveVrCastDevice != null && this.mActiveVrCastDevice.getId() == castDevice.getId()) {
            handleActiveDeviceLost();
        }
        getVrCastDevicesByProtocol(castDevice.mDiscoverProtocol).remove(castDevice.getId());
        notifyDeviceChanged();
    }

    private void handleActiveDeviceLost() {
        this.mTelemetry.onConnectionError(this.mActiveVrCastDevice, this.mActiveVrCastDevice.mDiscoverProtocol, 101, null);
        if (!this.mKeepConnectedOnActiveDeviceLost) {
            getAdapterForProtocol(this.mContext, this.mActiveVrCastDevice.mDiscoverProtocol).disconnect(this.mActiveVrCastDevice);
            sendError(101, null);
        }
    }

    public synchronized void setDeviceList(List<CastDevice> list, DiscoverProtocol discoverProtocol) {
        Map<String, CastDevice> vrCastDevicesByProtocol = getVrCastDevicesByProtocol(discoverProtocol);
        vrCastDevicesByProtocol.clear();
        for (CastDevice castDevice : list) {
            vrCastDevicesByProtocol.put(castDevice.getId(), castDevice);
        }
        notifyDeviceChanged();
    }

    public void notifyDeviceChanged() {
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            for (Map<String, CastDevice> map : this.mDeviceMap.values()) {
                for (CastDevice castDevice : map.values()) {
                    if (castDevice.showInList()) {
                        arrayList.add(toVrCastDevice(castDevice));
                    }
                }
            }
        }
        VrCastDevice[] vrCastDeviceArr = new VrCastDevice[arrayList.size()];
        arrayList.toArray(vrCastDeviceArr);
        notifyVrCastCallback(new VrCastCallbackCallable(vrCastDeviceArr) {
            /* class com.oculus.vrcast.$$Lambda$VrCastDeviceStore$NH8u2dBGC3SfMy1k1uDvqxaCJe8 */
            private final /* synthetic */ VrCastDevice[] f$0;

            {
                this.f$0 = r1;
            }

            @Override // com.oculus.vrcast.VrCastDeviceStore.VrCastCallbackCallable
            public final void doCallCallback(IVrCastCallback iVrCastCallback) {
                VrCastDeviceStore.lambda$notifyDeviceChanged$0(this.f$0, iVrCastCallback);
            }
        });
    }

    public CastDevice getActiveCastDevice() {
        if (this.mActiveVrCastDevice == null) {
            Log.w(TAG, "There is no active cast device connected.");
        }
        return this.mActiveVrCastDevice;
    }

    public void setActiveCastDevice(CastDevice castDevice) {
        CastDevice castDevice2 = this.mActiveVrCastDevice;
        this.mActiveVrCastDevice = castDevice;
        notifyDeviceStateUpdated();
        if (castDevice2 == null && this.mActiveVrCastDevice != null) {
            pauseDiscovery();
        }
    }

    public void updateActiveCastDeviceState(CastDevice.State state) {
        updateActiveCastDeviceState(state, DEBUG);
    }

    public void updateActiveCastDeviceState(CastDevice.State state, boolean z) {
        this.mActiveVrCastDevice.setState(state);
        if (z) {
            this.mActiveVrCastDevice = null;
        }
        notifyDeviceStateUpdated();
        notifyDeviceChanged();
    }

    public synchronized CastDevice getCastDeviceForId(String str) {
        for (Map<String, CastDevice> map : this.mDeviceMap.values()) {
            CastDevice castDevice = map.get(str);
            if (castDevice != null) {
                return castDevice;
            }
        }
        return null;
    }

    public synchronized Map<String, CastDevice> getVrCastDevicesByProtocol(DiscoverProtocol discoverProtocol) {
        if (supportProtocol(discoverProtocol)) {
        } else {
            throw new RuntimeException("Unsupported discover protocol");
        }
        return this.mDeviceMap.get(discoverProtocol);
    }

    public void sendError(int i, Bundle bundle) {
        notifyVrCastCallback(new VrCastCallbackCallable(i, bundle) {
            /* class com.oculus.vrcast.$$Lambda$VrCastDeviceStore$n8wvTJR8geM_vbx435BvAHDlM */
            private final /* synthetic */ int f$0;
            private final /* synthetic */ Bundle f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.vrcast.VrCastDeviceStore.VrCastCallbackCallable
            public final void doCallCallback(IVrCastCallback iVrCastCallback) {
                VrCastDeviceStore.lambda$sendError$1(this.f$0, this.f$1, iVrCastCallback);
            }
        });
    }

    private boolean supportProtocol(DiscoverProtocol discoverProtocol) {
        return this.mSupportDiscoverProtocol.contains(discoverProtocol);
    }

    /* access modifiers changed from: package-private */
    public VrCastAdapter getAdapterForProtocol(Context context, DiscoverProtocol discoverProtocol) {
        VrCastAdapter vrCastAdapter;
        if (discoverProtocol == null) {
            return null;
        }
        VrCastAdapter vrCastAdapter2 = this.mAdapterMap.get(discoverProtocol);
        if (vrCastAdapter2 != null) {
            return vrCastAdapter2;
        }
        int i = AnonymousClass1.$SwitchMap$com$oculus$vrcast$DiscoverProtocol[discoverProtocol.ordinal()];
        if (i == 1) {
            vrCastAdapter = new VrCastWfdAdapter(context, this, this.mTelemetry);
        } else if (i == 2) {
            vrCastAdapter = new GoogleCastAdapter(context, this.mTelemetry);
        } else {
            throw new RuntimeException("discover protocol not supported");
        }
        this.mAdapterMap.put(discoverProtocol, vrCastAdapter);
        return vrCastAdapter;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.vrcast.VrCastDeviceStore$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrcast$DiscoverProtocol = new int[DiscoverProtocol.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.vrcast.DiscoverProtocol[] r0 = com.oculus.vrcast.DiscoverProtocol.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.vrcast.VrCastDeviceStore.AnonymousClass1.$SwitchMap$com$oculus$vrcast$DiscoverProtocol = r0
                int[] r0 = com.oculus.vrcast.VrCastDeviceStore.AnonymousClass1.$SwitchMap$com$oculus$vrcast$DiscoverProtocol     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.vrcast.DiscoverProtocol r1 = com.oculus.vrcast.DiscoverProtocol.WIFI_P2P     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.vrcast.VrCastDeviceStore.AnonymousClass1.$SwitchMap$com$oculus$vrcast$DiscoverProtocol     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.vrcast.DiscoverProtocol r1 = com.oculus.vrcast.DiscoverProtocol.GOOGLE_CAST     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrcast.VrCastDeviceStore.AnonymousClass1.<clinit>():void");
        }
    }

    private void notifyDeviceStateUpdated() {
        notifyVrCastCallback(new VrCastCallbackCallable() {
            /* class com.oculus.vrcast.$$Lambda$VrCastDeviceStore$R9myWklI2Vc6gu7TwdhP6c2T8QA */

            @Override // com.oculus.vrcast.VrCastDeviceStore.VrCastCallbackCallable
            public final void doCallCallback(IVrCastCallback iVrCastCallback) {
                VrCastDeviceStore.lambda$notifyDeviceStateUpdated$2(VrCastDevice.this, iVrCastCallback);
            }
        });
    }

    public VrCastTelemetry getTelemetry() {
        return this.mTelemetry;
    }

    private void notifyVrCastCallback(VrCastCallbackCallable vrCastCallbackCallable) {
        this.mHandler.post(new Runnable(vrCastCallbackCallable) {
            /* class com.oculus.vrcast.$$Lambda$VrCastDeviceStore$4IYmTXFnuYKfZm__MKXWPvCzz0 */
            private final /* synthetic */ VrCastDeviceStore.VrCastCallbackCallable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                VrCastDeviceStore.this.lambda$notifyVrCastCallback$3$VrCastDeviceStore(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$notifyVrCastCallback$3$VrCastDeviceStore(VrCastCallbackCallable vrCastCallbackCallable) {
        int beginBroadcast = this.mVrCastCallback.beginBroadcast();
        for (int i = 0; i < beginBroadcast; i++) {
            try {
                vrCastCallbackCallable.doCallCallback(this.mVrCastCallback.getBroadcastItem(i));
            } catch (RemoteException e) {
                Log.e(TAG, "Error while calling callback ", e);
            }
        }
        this.mVrCastCallback.finishBroadcast();
    }

    static VrCastDevice toVrCastDevice(CastDevice castDevice) {
        if (castDevice == null) {
            return null;
        }
        return new VrCastDevice(castDevice.getName(), castDevice.getId(), castDevice.getState().ordinal(), toVrCastType(castDevice.mDiscoverProtocol));
    }

    private static int toVrCastType(DiscoverProtocol discoverProtocol) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$vrcast$DiscoverProtocol[discoverProtocol.ordinal()];
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        throw new RuntimeException("Not supported discover protocol" + discoverProtocol);
    }
}
