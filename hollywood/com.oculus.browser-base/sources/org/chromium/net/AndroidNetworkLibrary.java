package org.chromium.net;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.security.NetworkSecurityPolicy;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.List;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AndroidNetworkLibrary {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f11000a;
    public static Boolean b;

    public static boolean a() {
        if (b == null) {
            b = Boolean.valueOf(AbstractC3153j7.a(ContextUtils.getApplicationContext(), "android.permission.ACCESS_WIFI_STATE", Process.myPid(), Process.myUid()) == 0);
        }
        return b.booleanValue();
    }

    public static void addTestRootCertificate(byte[] bArr) {
        Dz1.d();
        X509Certificate b2 = Dz1.b(bArr);
        synchronized (Dz1.j) {
            KeyStore keyStore = Dz1.e;
            keyStore.setCertificateEntry("root_cert_" + Integer.toString(Dz1.e.size()), b2);
            Dz1.d = Dz1.c(Dz1.e);
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0017 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void clearTestRootCertificates() {
        /*
            defpackage.Dz1.d()
            java.lang.Object r0 = defpackage.Dz1.j
            monitor-enter(r0)
            java.security.KeyStore r1 = defpackage.Dz1.e     // Catch:{ IOException -> 0x0017 }
            r2 = 0
            r1.load(r2)     // Catch:{ IOException -> 0x0017 }
            java.security.KeyStore r1 = defpackage.Dz1.e     // Catch:{ IOException -> 0x0017 }
            Bz1 r1 = defpackage.Dz1.c(r1)     // Catch:{ IOException -> 0x0017 }
            defpackage.Dz1.d = r1     // Catch:{ IOException -> 0x0017 }
            goto L_0x0017
        L_0x0015:
            r1 = move-exception
            goto L_0x0019
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            return
        L_0x0019:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.AndroidNetworkLibrary.clearTestRootCertificates():void");
    }

    public static DnsStatus getDnsStatus(Network network) {
        ConnectivityManager connectivityManager;
        if (f11000a == null) {
            f11000a = Boolean.valueOf(AbstractC3153j7.a(ContextUtils.getApplicationContext(), "android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0);
        }
        if (!f11000a.booleanValue() || (connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity")) == null) {
            return null;
        }
        if (network == null) {
            network = connectivityManager.getActiveNetwork();
        }
        if (network == null) {
            return null;
        }
        try {
            LinkProperties linkProperties = connectivityManager.getLinkProperties(network);
            if (linkProperties == null) {
                return null;
            }
            List<InetAddress> dnsServers = linkProperties.getDnsServers();
            if (Build.VERSION.SDK_INT >= 28) {
                return new DnsStatus(dnsServers, C4179p7.d(linkProperties), C4179p7.b(linkProperties));
            }
            return new DnsStatus(dnsServers, false, "");
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public static boolean getIsCaptivePortal() {
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager == null || (activeNetwork = connectivityManager.getActiveNetwork()) == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null || !networkCapabilities.hasCapability(17)) {
            return false;
        }
        return true;
    }

    public static boolean getIsRoaming() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isRoaming();
    }

    public static String getMimeTypeFromExtension(String str) {
        return URLConnection.guessContentTypeFromName("foo." + str);
    }

    public static String getNetworkOperator() {
        C5708y6 a2 = C5708y6.a();
        if (a2.b == null) {
            TelephonyManager b2 = C5708y6.b();
            if (b2 == null) {
                return "";
            }
            a2.b = b2.getNetworkOperator();
        }
        return a2.b;
    }

    public static String getSimOperator() {
        C5708y6 a2 = C5708y6.a();
        if (a2.c == null) {
            TelephonyManager b2 = C5708y6.b();
            if (b2 == null) {
                return "";
            }
            a2.c = b2.getSimOperator();
        }
        return a2.c;
    }

    public static String getWifiSSID() {
        String ssid;
        WifiInfo wifiInfo = null;
        if (a()) {
            wifiInfo = ((WifiManager) ContextUtils.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        } else {
            Intent registerReceiver = ContextUtils.getApplicationContext().registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            if (registerReceiver != null) {
                wifiInfo = (WifiInfo) registerReceiver.getParcelableExtra("wifiInfo");
            }
        }
        return (wifiInfo == null || (ssid = wifiInfo.getSSID()) == null || ssid.equals("<unknown ssid>")) ? "" : ssid;
    }

    public static int getWifiSignalLevel(int i) {
        int i2;
        int calculateSignalLevel;
        if (ContextUtils.getApplicationContext() == null || ContextUtils.getApplicationContext().getContentResolver() == null) {
            return -1;
        }
        if (a()) {
            WifiInfo connectionInfo = ((WifiManager) ContextUtils.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null) {
                return -1;
            }
            i2 = connectionInfo.getRssi();
        } else {
            try {
                Intent registerReceiver = ContextUtils.getApplicationContext().registerReceiver(null, new IntentFilter("android.net.wifi.RSSI_CHANGED"));
                if (registerReceiver == null) {
                    return -1;
                }
                i2 = registerReceiver.getIntExtra("newRssi", Integer.MIN_VALUE);
            } catch (IllegalArgumentException unused) {
            }
        }
        if (i2 != Integer.MIN_VALUE && (calculateSignalLevel = WifiManager.calculateSignalLevel(i2, i)) >= 0 && calculateSignalLevel < i) {
            return calculateSignalLevel;
        }
        return -1;
    }

    public static boolean haveOnlyLoopbackAddresses() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return false;
            }
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                try {
                    if (nextElement.isUp() && !nextElement.isLoopback()) {
                        return false;
                    }
                } catch (SocketException unused) {
                }
            }
            return true;
        } catch (Exception e) {
            Log.w("AndroidNetworkLibrary", "could not get network interfaces: " + e);
            return false;
        }
    }

    public static boolean isCleartextPermitted(String str) {
        try {
            return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
        } catch (IllegalArgumentException unused) {
            return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
        }
    }

    public static boolean reportBadDefaultNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        connectivityManager.reportNetworkConnectivity(null, false);
        return true;
    }

    public static void tagSocket(int i, int i2, int i3) {
        int threadStatsTag = TrafficStats.getThreadStatsTag();
        if (i3 != threadStatsTag) {
            TrafficStats.setThreadStatsTag(i3);
        }
        if (i2 != -1) {
            Method method = AbstractC1701ah1.f9443a;
            try {
                AbstractC1701ah1.f9443a.invoke(null, Integer.valueOf(i2));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("TrafficStats.setThreadStatsUid failed", e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException("TrafficStats.setThreadStatsUid failed", e2);
            }
        }
        ParcelFileDescriptor adoptFd = ParcelFileDescriptor.adoptFd(i);
        C3489l5 l5Var = new C3489l5(adoptFd.getFileDescriptor());
        TrafficStats.tagSocket(l5Var);
        l5Var.close();
        adoptFd.detachFd();
        if (i3 != threadStatsTag) {
            TrafficStats.setThreadStatsTag(threadStatsTag);
        }
        if (i2 != -1) {
            Method method2 = AbstractC1701ah1.f9443a;
            try {
                AbstractC1701ah1.b.invoke(null, new Object[0]);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException("TrafficStats.clearThreadStatsUid failed", e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("TrafficStats.clearThreadStatsUid failed", e4);
            }
        }
    }

    public static AndroidCertVerifyResult verifyServerCertificates(byte[][] bArr, String str, String str2) {
        try {
            return Dz1.h(bArr, str, str2);
        } catch (KeyStoreException unused) {
            return new AndroidCertVerifyResult(-1);
        } catch (NoSuchAlgorithmException unused2) {
            return new AndroidCertVerifyResult(-1);
        } catch (IllegalArgumentException unused3) {
            return new AndroidCertVerifyResult(-1);
        }
    }
}
