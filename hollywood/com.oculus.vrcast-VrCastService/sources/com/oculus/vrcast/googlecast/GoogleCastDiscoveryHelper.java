package com.oculus.vrcast.googlecast;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.util.Log;
import com.apple.dnssd.BrowseListener;
import com.apple.dnssd.DNSSD;
import com.apple.dnssd.DNSSDException;
import com.apple.dnssd.DNSSDService;
import com.oculus.vrcast.CastDevice;
import com.oculus.vrcast.DiscoverProtocol;
import com.oculus.vrcast.VrCastDeviceStore;
import com.oculus.vrcast.telemetry.VrCastTelemetry;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;
import oculus.internal.Gatekeeper;

public class GoogleCastDiscoveryHelper implements BrowseListener {
    private static final Gatekeeper ENABLE_CHROMECAST_INIT_NSD_ON_DISCOVERY = new Gatekeeper(33);
    private static final int NUM_RETRIES = 2;
    private static final int SERVICE_NOT_RUNNING_ERROR_CODE = -65563;
    public static final String SERVICE_TYPE = "_googlecast._tcp.";
    private static final String TAG = "GoogleCastDiscoveryHelper";
    private DNSSDService mBrowser;
    private final Context mContext;
    private NsdManager mManagerRef;
    private int mRetryCount = 0;
    private String mSessionId = null;
    private final VrCastTelemetry mTelemetry;
    private final VrCastDeviceStore mVrCastDeviceStore;

    public GoogleCastDiscoveryHelper(Context context, VrCastTelemetry vrCastTelemetry) {
        this.mContext = context;
        this.mTelemetry = vrCastTelemetry;
        this.mVrCastDeviceStore = VrCastDeviceStore.get(context);
        if (!ENABLE_CHROMECAST_INIT_NSD_ON_DISCOVERY.isEnabled()) {
            this.mManagerRef = (NsdManager) this.mContext.getSystemService("servicediscovery");
        }
    }

    public void startDiscovery() {
        if (this.mBrowser == null) {
            this.mSessionId = UUID.randomUUID().toString();
            this.mRetryCount = 0;
            startDiscoveryImpl();
        }
    }

    public void startDiscoveryImpl() {
        this.mTelemetry.onStartChromecastDiscovery(this.mSessionId, this.mRetryCount);
        Log.i(TAG, "Starting discovery");
        if (ENABLE_CHROMECAST_INIT_NSD_ON_DISCOVERY.isEnabled()) {
            this.mManagerRef = (NsdManager) this.mContext.getSystemService("servicediscovery");
        }
        try {
            this.mBrowser = DNSSD.browse(SERVICE_TYPE, this);
        } catch (DNSSDException e) {
            Log.e(TAG, "Failed to start discovery", e);
            this.mTelemetry.onGoogleCastDNSSDError(e.getMessage(), this.mSessionId, this.mRetryCount);
            if (!maybeRetryDiscovery(e.getErrorCode())) {
                this.mVrCastDeviceStore.sendError(100, null);
            }
        }
    }

    private boolean maybeRetryDiscovery(int i) {
        int i2;
        if (!ENABLE_CHROMECAST_INIT_NSD_ON_DISCOVERY.isEnabled() || (i2 = this.mRetryCount) >= 2 || i != -65563) {
            return false;
        }
        this.mRetryCount = i2 + 1;
        DNSSDService dNSSDService = this.mBrowser;
        if (dNSSDService != null) {
            dNSSDService.stop();
        }
        this.mManagerRef = (NsdManager) this.mContext.getSystemService("servicediscovery");
        startDiscoveryImpl();
        return true;
    }

    public void stopDiscovery() {
        if (this.mBrowser != null) {
            Log.i(TAG, "Stopping discovery");
            this.mTelemetry.onStopChromecastDiscovery(this.mSessionId, this.mRetryCount);
            this.mBrowser.stop();
            this.mBrowser = null;
            this.mSessionId = null;
            if (ENABLE_CHROMECAST_INIT_NSD_ON_DISCOVERY.isEnabled()) {
                this.mManagerRef = null;
            }
            Iterator<CastDevice> it = this.mVrCastDeviceStore.getVrCastDevicesByProtocol(DiscoverProtocol.GOOGLE_CAST).values().iterator();
            while (it.hasNext()) {
                ((GoogleCastDevice) it.next()).stopMonitoring();
            }
            this.mVrCastDeviceStore.setDeviceList(Collections.EMPTY_LIST, DiscoverProtocol.GOOGLE_CAST);
        }
    }

    @Override // com.apple.dnssd.BrowseListener
    public void serviceFound(DNSSDService dNSSDService, int i, int i2, String str, String str2, String str3) {
        if (!str2.equals(SERVICE_TYPE)) {
            Log.w(TAG, "serviceFound() called with wrong regType " + str2);
            return;
        }
        GoogleCastDevice googleCastDevice = new GoogleCastDevice(str, str3, i2, this.mContext);
        Log.i(TAG, "Found device " + googleCastDevice.getId());
        this.mVrCastDeviceStore.addDevice(googleCastDevice);
    }

    @Override // com.apple.dnssd.BrowseListener
    public void serviceLost(DNSSDService dNSSDService, int i, int i2, String str, String str2, String str3) {
        try {
            String constructFullName = DNSSD.constructFullName(str, str2, str3);
            Log.i(TAG, "Lost device " + constructFullName);
            GoogleCastDevice googleCastDevice = (GoogleCastDevice) this.mVrCastDeviceStore.getCastDeviceForId(constructFullName);
            if (googleCastDevice == null) {
                Log.w(TAG, "Device " + constructFullName + " does not exist in store, cannot remove");
                return;
            }
            googleCastDevice.stopMonitoring();
            this.mVrCastDeviceStore.removeDevice(googleCastDevice);
        } catch (DNSSDException e) {
            throw new RuntimeException("Failed to construct full service name (lookup)", e);
        }
    }

    @Override // com.apple.dnssd.BaseListener
    public void operationFailed(DNSSDService dNSSDService, int i) {
        Log.e(TAG, "Failed to browse for devices: mDNSResolver error " + i);
        VrCastTelemetry vrCastTelemetry = this.mTelemetry;
        vrCastTelemetry.onGoogleCastDNSSDError("dnsd_error" + i, this.mSessionId, this.mRetryCount);
        if (!maybeRetryDiscovery(i)) {
            this.mVrCastDeviceStore.sendError(100, null);
        }
    }
}
