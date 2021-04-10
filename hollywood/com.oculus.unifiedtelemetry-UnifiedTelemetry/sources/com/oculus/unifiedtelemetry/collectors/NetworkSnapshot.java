package com.oculus.unifiedtelemetry.collectors;

import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import java.util.ArrayList;
import java.util.List;

public class NetworkSnapshot {
    public static final String DEFAULT_AUTH_TYPE = "";
    public static final int DEFAULT_CONNECTION_TIMESTAMP = 0;
    public static final int DEFAULT_NETWORK_SCORE = -1;
    public static final String DEFAULT_NETWORK_TYPE = "unknown";
    public static final String UNKNOWN_SSID = "<unknown ssid>";
    public String mAuthType;
    public long mConnectionEndTimeMs;
    public long mConnectionStartTimeMs;
    public List<String> mIPv4;
    public List<String> mIPv6;
    public float mNetworkFrequencyGHz;
    public NetworkInfo mNetworkInfo;
    public NetworkInfo.DetailedState mNetworkState;
    public String mNetworkType;
    public int mRssi;
    public int mScore;
    public WifiInfo mWifiInfo;

    /* renamed from: com.oculus.unifiedtelemetry.collectors.NetworkSnapshot$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$android$net$NetworkInfo$DetailedState;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                android.net.NetworkInfo$DetailedState[] r0 = android.net.NetworkInfo.DetailedState.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.unifiedtelemetry.collectors.NetworkSnapshot.AnonymousClass1.$SwitchMap$android$net$NetworkInfo$DetailedState = r2
                android.net.NetworkInfo$DetailedState r0 = android.net.NetworkInfo.DetailedState.CONNECTING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                android.net.NetworkInfo$DetailedState r0 = android.net.NetworkInfo.DetailedState.AUTHENTICATING     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                android.net.NetworkInfo$DetailedState r0 = android.net.NetworkInfo.DetailedState.OBTAINING_IPADDR     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                android.net.NetworkInfo$DetailedState r0 = android.net.NetworkInfo.DetailedState.CONNECTED     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                android.net.NetworkInfo$DetailedState r0 = android.net.NetworkInfo.DetailedState.DISCONNECTING     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                android.net.NetworkInfo$DetailedState r0 = android.net.NetworkInfo.DetailedState.DISCONNECTED     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r0 = 6
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                android.net.NetworkInfo$DetailedState r0 = android.net.NetworkInfo.DetailedState.FAILED     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r0 = 7
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.collectors.NetworkSnapshot.AnonymousClass1.<clinit>():void");
        }
    }

    public static void A00(NetworkSnapshot networkSnapshot) {
        networkSnapshot.mNetworkState = NetworkInfo.DetailedState.DISCONNECTED;
        networkSnapshot.mConnectionStartTimeMs = 0;
        networkSnapshot.mConnectionEndTimeMs = 0;
        networkSnapshot.mNetworkFrequencyGHz = 0.0f;
        networkSnapshot.mNetworkType = "unknown";
        networkSnapshot.mRssi = 0;
        networkSnapshot.mScore = -1;
        networkSnapshot.mAuthType = "";
        networkSnapshot.mIPv4 = new ArrayList();
        networkSnapshot.mIPv6 = new ArrayList();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", ");
        sb.append("Network State = ");
        sb.append(this.mNetworkState.name());
        sb.append(", ");
        sb.append("Network Type = ");
        sb.append(this.mNetworkType);
        sb.append(", ");
        sb.append("ConnStartTS = ");
        sb.append(this.mConnectionStartTimeMs);
        sb.append(", ");
        sb.append("ConnEndTS = ");
        sb.append(this.mConnectionEndTimeMs);
        sb.append(", ");
        sb.append("NtwFreq = ");
        sb.append(this.mNetworkFrequencyGHz);
        sb.append(", ");
        sb.append("RSSI = ");
        sb.append(this.mRssi);
        sb.append(", ");
        sb.append("Score = ");
        sb.append(this.mScore);
        sb.append(", ");
        sb.append("Auth Type = ");
        sb.append(this.mAuthType);
        sb.append(", ");
        sb.append("IPV4 = ");
        sb.append(this.mIPv4);
        sb.append(", ");
        sb.append("IPV6 = ");
        sb.append(this.mIPv6);
        return sb.toString();
    }

    public NetworkSnapshot() {
        A00(this);
    }
}
