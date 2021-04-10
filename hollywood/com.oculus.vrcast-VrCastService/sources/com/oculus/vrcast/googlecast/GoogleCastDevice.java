package com.oculus.vrcast.googlecast;

import android.content.Context;
import android.util.Log;
import com.apple.dnssd.DNSSD;
import com.apple.dnssd.DNSSDException;
import com.apple.dnssd.DNSSDService;
import com.apple.dnssd.QueryListener;
import com.apple.dnssd.ResolveListener;
import com.apple.dnssd.TXTRecord;
import com.oculus.vrcast.CastDevice;
import com.oculus.vrcast.DiscoverProtocol;
import com.oculus.vrcast.VrCastDeviceStore;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GoogleCastDevice extends CastDevice implements ResolveListener {
    private static final long CAP_HAS_VIDEO = 1;
    private static final int LOOKUP_TIMEOUT = 3;
    private static final int RRCLASS_IN = 1;
    private static final int RRTYPE_A = 1;
    private static final int RRTYPE_AAAA = 28;
    private static final String TAG = "GoogleCastDevice";
    private long mCapabilities;
    private Context mContext;
    private final String mDomain;
    private final String mFullName;
    private String mHostname;
    private final int mIfIndex;
    private final String mModelName;
    private String mName;
    private int mPort;
    private DNSSDService mResolve;
    private final String mServiceName;
    private CastDevice.State mState = CastDevice.State.FOUND;

    public GoogleCastDevice(String str, String str2, int i, Context context) {
        this.mDiscoverProtocol = DiscoverProtocol.GOOGLE_CAST;
        this.mServiceName = str;
        this.mDomain = str2;
        this.mIfIndex = i;
        this.mContext = context;
        int lastIndexOf = this.mServiceName.lastIndexOf("-");
        if (lastIndexOf != -1) {
            this.mModelName = this.mServiceName.substring(0, lastIndexOf);
        } else {
            this.mModelName = this.mServiceName;
        }
        try {
            this.mFullName = DNSSD.constructFullName(this.mServiceName, GoogleCastDiscoveryHelper.SERVICE_TYPE, this.mDomain);
            try {
                this.mResolve = DNSSD.resolve(0, this.mIfIndex, this.mServiceName, GoogleCastDiscoveryHelper.SERVICE_TYPE, this.mDomain, this);
            } catch (DNSSDException e) {
                Log.e(TAG, "Failed to start resolution of " + this.mFullName, e);
            }
        } catch (DNSSDException e2) {
            throw new RuntimeException("Failed to construct full service name (construct)", e2);
        }
    }

    @Override // com.oculus.vrcast.CastDevice
    public CastDevice.State getState() {
        return this.mState;
    }

    @Override // com.oculus.vrcast.CastDevice
    public void setState(CastDevice.State state) {
        this.mState = state;
    }

    @Override // com.oculus.vrcast.CastDevice
    public String getName() {
        return this.mName;
    }

    @Override // com.oculus.vrcast.CastDevice
    public String getModelName() {
        return this.mModelName;
    }

    @Override // com.oculus.vrcast.CastDevice
    public String getId() {
        return this.mFullName;
    }

    @Override // com.oculus.vrcast.CastDevice
    public boolean showInList() {
        return (this.mName == null || (this.mCapabilities & 1) == 0) ? false : true;
    }

    public InetSocketAddress getAddress() throws TimeoutException, InterruptedException {
        final CompletableFuture completableFuture = new CompletableFuture();
        final String str = this.mHostname;
        try {
            DNSSDService queryRecord = DNSSD.queryRecord(0, this.mIfIndex, str, 1, 1, new QueryListener() {
                /* class com.oculus.vrcast.googlecast.GoogleCastDevice.AnonymousClass1 */

                /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|5) */
                /* JADX WARNING: Code restructure failed: missing block: B:6:0x0013, code lost:
                    r0 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
                    android.util.Log.w(com.oculus.vrcast.googlecast.GoogleCastDevice.TAG, "Got bad IP address length: " + r7.length, r0);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:8:0x002b, code lost:
                    return;
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0007 */
                @Override // com.apple.dnssd.QueryListener
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void queryAnswered(com.apple.dnssd.DNSSDService r1, int r2, int r3, java.lang.String r4, int r5, int r6, byte[] r7, int r8) {
                    /*
                        r0 = this;
                        java.lang.String r1 = r3     // Catch:{ UnknownHostException -> 0x0007 }
                        java.net.Inet6Address r1 = java.net.Inet6Address.getByAddress(r1, r7, r3)     // Catch:{ UnknownHostException -> 0x0007 }
                        goto L_0x000d
                    L_0x0007:
                        java.lang.String r1 = r3     // Catch:{ UnknownHostException -> 0x0013 }
                        java.net.InetAddress r1 = java.net.InetAddress.getByAddress(r1, r7)     // Catch:{ UnknownHostException -> 0x0013 }
                    L_0x000d:
                        java.util.concurrent.CompletableFuture r0 = r0
                        r0.complete(r1)
                        return
                    L_0x0013:
                        r0 = move-exception
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder
                        r1.<init>()
                        java.lang.String r2 = "Got bad IP address length: "
                        r1.append(r2)
                        int r2 = r7.length
                        r1.append(r2)
                        java.lang.String r1 = r1.toString()
                        java.lang.String r2 = "GoogleCastDevice"
                        android.util.Log.w(r2, r1, r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrcast.googlecast.GoogleCastDevice.AnonymousClass1.queryAnswered(com.apple.dnssd.DNSSDService, int, int, java.lang.String, int, int, byte[], int):void");
                }

                @Override // com.apple.dnssd.BaseListener
                public void operationFailed(DNSSDService dNSSDService, int i) {
                    Log.e(GoogleCastDevice.TAG, "Failed to look up address of " + GoogleCastDevice.this.mFullName + ": mDNSResolver error " + i);
                }
            });
            try {
                InetAddress inetAddress = (InetAddress) completableFuture.get(3, TimeUnit.SECONDS);
                queryRecord.stop();
                return new InetSocketAddress(inetAddress, this.mPort);
            } catch (CancellationException | ExecutionException e) {
                throw new RuntimeException("Unexpected future failure", e);
            } catch (Throwable th) {
                queryRecord.stop();
                throw th;
            }
        } catch (DNSSDException e2) {
            throw new RuntimeException("Failed to query IP of " + this.mFullName, e2);
        }
    }

    public void stopMonitoring() {
        this.mResolve.stop();
    }

    @Override // com.apple.dnssd.ResolveListener
    public void serviceResolved(DNSSDService dNSSDService, int i, int i2, String str, String str2, int i3, TXTRecord tXTRecord) {
        if (!str.equals(this.mFullName)) {
            Log.w(TAG, "fullName from serviceResolved() (" + str + ") does not match cached name (" + this.mFullName + ")");
        }
        this.mName = tXTRecord.getValueAsString("fn");
        this.mHostname = str2;
        this.mPort = i3;
        this.mCapabilities = Long.parseLong(tXTRecord.getValueAsString("ca"));
        VrCastDeviceStore.get(this.mContext).notifyDeviceChanged();
    }

    @Override // com.apple.dnssd.BaseListener
    public void operationFailed(DNSSDService dNSSDService, int i) {
        Log.e(TAG, "Failed to resolve device " + this.mFullName + ": mDNSResolver error " + i);
    }
}
