package com.oculus.horizon.cast;

import X.AbstractC06600ny;
import X.AbstractC09411eb;
import X.AnonymousClass006;
import X.AnonymousClass0I1;
import X.AnonymousClass0J2;
import X.AnonymousClass0KD;
import X.AnonymousClass0NO;
import X.AnonymousClass0b9;
import X.AnonymousClass1Xz;
import X.AnonymousClass1eW;
import X.AnonymousClass1eX;
import X.AnonymousClass1ea;
import X.AnonymousClass1ec;
import X.AnonymousClass1fE;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.Pair;
import com.oculus.auth.e2etestharness.Constants;
import com.oculus.debug.DebugMode;
import com.oculus.horizon.cast.Message;
import com.oculus.horizon.logging.contract.FunnelContract;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.FunnelData;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;
import com.oculus.util.network.NetworkUtils;
import com.squareup.okhttp.internal.DiskLruCache;
import java.io.IOException;
import java.net.BindException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.annotation.Nullable;

public abstract class CastHTTPServerBase extends AnonymousClass1eW {
    public static final AnonymousClass1eX ERROR_RESPONSE = AnonymousClass1ea.A00(AnonymousClass1Xz.NOT_FOUND, Constants.EXTRA_KEY_ERROR);
    public static final String LOCALHOST = "http://127.0.0.1:8889/";
    public static final String OVRPLATFORM_KEY_ANSWER_SDP = "answer_sdp";
    public static final String OVRPLATFORM_KEY_OFFER_SDP = "offer_sdp";
    public static final String OVRPLATFORM_KEY_RTC_CONNECTION_ID = "rtc_connection_id";
    public static final String OVRPLATFORM_KEY_RTC_SESSION_ID = "rtc_session_id";
    public static final String OVRPLATFORM_OFFER_URI = "/rtc/connections";
    public static final int PORT = 8889;
    public static final String TAG = "CastHTTPServerBase";
    public static final String WIFI_NOT_CONNECTED_ERR = "Wifi is not connected";
    public final CastAnalytics mCastAnalytics;
    public final Context mContext;
    public final DebugMode mDebugMode;
    public final Handler mEventHandler;
    public final Object mLock = new Object();
    public final AbstractC06600ny mMobileConfig;
    @Nullable
    public String mSessionId;
    @Nullable
    public ShutdownBroadcastReceiver mShutdownReceiver;
    public long mStartTimeMs;

    public class ShutdownBroadcastReceiver extends OculusPublicBroadcastReceiver {
        public ShutdownBroadcastReceiver() {
            super("android.intent.action.ACTION_SHUTDOWN", "android.intent.action.BATTERY_LOW");
        }

        @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
        public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r7) {
            String action = intent.getAction();
            if ("android.intent.action.ACTION_SHUTDOWN".equals(action)) {
                CastHTTPServerBase castHTTPServerBase = CastHTTPServerBase.this;
                castHTTPServerBase.mCastAnalytics.A04(castHTTPServerBase.mSessionId, "shutdown");
                if (CastHTTPServerBase.this.mMobileConfig.A37(36310302060707864L, false)) {
                    CastHTTPServerBase.this.A07(CastStopSource.HMD);
                }
            } else if ("android.intent.action.BATTERY_LOW".equals(action)) {
                CastHTTPServerBase castHTTPServerBase2 = CastHTTPServerBase.this;
                castHTTPServerBase2.mCastAnalytics.A04(castHTTPServerBase2.mSessionId, "battery low");
            }
        }
    }

    private final void A08(String str) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        synchronized (this.mLock) {
            ShutdownBroadcastReceiver shutdownBroadcastReceiver = this.mShutdownReceiver;
            if (shutdownBroadcastReceiver != null) {
                shutdownBroadcastReceiver.unregisterReceiver(this.mContext);
                this.mShutdownReceiver = null;
            }
            this.mEventHandler.post(new Runnable() {
                /* class com.oculus.horizon.cast.CastHTTPServerBase.AnonymousClass1 */

                public final void run() {
                    synchronized (CastHTTPServerBase.this.mLock) {
                        CastHTTPServerBase castHTTPServerBase = CastHTTPServerBase.this;
                        try {
                            AnonymousClass1ea.A02(castHTTPServerBase.myServerSocket);
                            castHTTPServerBase.asyncRunner.A1j();
                            Thread thread = castHTTPServerBase.myThread;
                            if (thread != null) {
                                thread.join();
                            }
                        } catch (Exception e) {
                            AnonymousClass1ea.LOG.log(Level.SEVERE, "Could not stop all connections", (Throwable) e);
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            long currentTimeMillis = System.currentTimeMillis() - this.mStartTimeMs;
            CastAnalytics castAnalytics = this.mCastAnalytics;
            String str2 = this.mSessionId;
            if (currentTimeMillis < 0) {
                currentTimeMillis = 0;
            }
            String str3 = str;
            if (str2 == null) {
                str2 = "";
            }
            FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics._UL_mInjectionContext)).A24();
            A24.A18(CastAnalytics.KEY_SERVER_SESSION_ID, str2);
            if (str == null) {
                str3 = "";
            }
            A24.A18(CastAnalytics.KEY_STOP_CAST_SOURCE, str3);
            A24.A17("duration_ms", currentTimeMillis);
            ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics._UL_mInjectionContext)).A8F(FunnelContract.CAST_SERVER_SESSION_FUNNEL_NAME, CastAnalytics.ACTION_STOP_CAST_SERVER, str2, A24);
            ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics._UL_mInjectionContext)).A2Q(FunnelContract.CAST_SERVER_SESSION_FUNNEL_NAME);
            this.mStartTimeMs = 0;
            A06(2, new Message(this.mSessionId, Message.Type.STOP, str).A01().toString());
            this.mSessionId = null;
        }
        try {
            countDownLatch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            AnonymousClass0NO.A0B(TAG, "Failed to wait stop cast http server", e);
        }
    }

    public enum StartSource {
        TWILIGHT("twilight"),
        HMD("hmd"),
        BROADCAST("broadcast");
        
        public final String mName;

        /* access modifiers changed from: public */
        StartSource(String str) {
            this.mName = str;
        }
    }

    public final Pair<String, String> A05(StartSource startSource, @Nullable Boolean bool) throws IOException {
        String str;
        Pair<String, String> create;
        InetAddress nextElement;
        synchronized (this.mLock) {
            String obj = AnonymousClass0KD.A00().toString();
            this.mSessionId = obj;
            CastAnalytics castAnalytics = this.mCastAnalytics;
            String str2 = startSource.mName;
            String A00 = CastAnalytics.A00(bool);
            ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics._UL_mInjectionContext)).A9H(A00);
            FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics._UL_mInjectionContext)).A24();
            String str3 = obj;
            if (obj == null) {
                str3 = "";
            }
            A24.A18(CastAnalytics.KEY_SERVER_SESSION_ID, str3);
            if (str2 == null) {
                str2 = "";
            }
            A24.A18(CastAnalytics.KEY_START_CAST_SOURCE, str2);
            ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics._UL_mInjectionContext)).A8F(A00, CastAnalytics.ACTION_START_CAST, obj, A24);
            ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
            boolean z = true;
            if (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null || connectivityManager.getActiveNetworkInfo().getType() != 1) {
                z = false;
            }
            if (z) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("ws://");
                    if (this.mMobileConfig.A37(36310302060773401L, false)) {
                        try {
                            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                            str = "";
                            loop0:
                            while (true) {
                                if (networkInterfaces.hasMoreElements()) {
                                    NetworkInterface nextElement2 = networkInterfaces.nextElement();
                                    if (nextElement2 != null) {
                                        Enumeration<InetAddress> inetAddresses = nextElement2.getInetAddresses();
                                        while (inetAddresses.hasMoreElements()) {
                                            nextElement = inetAddresses.nextElement();
                                            if (nextElement != null && !nextElement.isLoopbackAddress()) {
                                                if (!(nextElement instanceof Inet6Address) || !str.equals("")) {
                                                    str = nextElement.getHostAddress();
                                                } else {
                                                    String hostAddress = nextElement.getHostAddress();
                                                    if (!hostAddress.contains("%")) {
                                                        str = AnonymousClass006.A07("[", hostAddress.replace("%", "%25"), "]");
                                                    }
                                                }
                                            }
                                        }
                                        continue;
                                    }
                                } else if (str.equals("")) {
                                    throw new IOException(NetworkUtils.NO_INTERNET_CONNECTION_ERR);
                                }
                            }
                            str = nextElement.getHostAddress();
                        } catch (SocketException unused) {
                            throw new IOException(NetworkUtils.FAILED_TO_GET_NETWORK_INTERFACES);
                        }
                    } else {
                        str = Formatter.formatIpAddress(((WifiManager) this.mContext.getApplicationContext().getSystemService(NetworkUtils.WIFI)).getConnectionInfo().getIpAddress());
                    }
                    sb.append(str);
                    sb.append(":");
                    sb.append(PORT);
                    String obj2 = sb.toString();
                    int A3d = this.mMobileConfig.A3d(36591777037221904L, 0);
                    if (A3d > 5000) {
                        A04(A3d);
                    } else {
                        A04(5000);
                    }
                    CastAnalytics castAnalytics2 = this.mCastAnalytics;
                    String str4 = this.mSessionId;
                    String str5 = startSource.mName;
                    String str6 = obj2;
                    FunnelData A242 = ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics2._UL_mInjectionContext)).A24();
                    if (obj2 == null) {
                        str6 = "";
                    }
                    A242.A18("url", str6);
                    String str7 = str4;
                    if (str4 == null) {
                        str7 = "";
                    }
                    A242.A18(CastAnalytics.KEY_SERVER_SESSION_ID, str7);
                    if (str5 == null) {
                        str5 = "";
                    }
                    A242.A18(CastAnalytics.KEY_START_CAST_SOURCE, str5);
                    ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics2._UL_mInjectionContext)).A8F(A00, CastAnalytics.ACTION_START_CAST_SERVER, str4, A242);
                    create = Pair.create(obj2, this.mSessionId);
                } catch (BindException e) {
                    this.mCastAnalytics.A06(this.mSessionId, e.getMessage(), bool);
                    A07(CastStopSource.HMD_ERROR);
                    throw new IOException("failed to start server on port 8889");
                } catch (IOException e2) {
                    this.mCastAnalytics.A06(this.mSessionId, e2.getMessage(), bool);
                    A07(CastStopSource.HMD_ERROR);
                    throw e2;
                }
            } else {
                AnonymousClass0NO.A08(TAG, WIFI_NOT_CONNECTED_ERR);
                CastAnalytics castAnalytics3 = this.mCastAnalytics;
                String str8 = this.mSessionId;
                if (str8 == null) {
                    str8 = "";
                }
                FunnelData A243 = ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics3._UL_mInjectionContext)).A24();
                A243.A18(CastAnalytics.KEY_SERVER_SESSION_ID, str8);
                A243.A18("error", WIFI_NOT_CONNECTED_ERR);
                ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics3._UL_mInjectionContext)).A8F(A00, CastAnalytics.ACTION_SERVER_INVALID_NETWORK, str8, A243);
                throw new IOException(WIFI_NOT_CONNECTED_ERR);
            }
        }
        return create;
    }

    public final void A06(int i, @Nullable Object obj) {
        android.os.Message obtain = android.os.Message.obtain(this.mEventHandler);
        obtain.what = i;
        obtain.obj = obj;
        obtain.sendToTarget();
    }

    public final void A07(CastStopSource castStopSource) {
        String str;
        if (!(this instanceof CastHTTPServerForWeb)) {
            CastHTTPServerForMobileDevice castHTTPServerForMobileDevice = (CastHTTPServerForMobileDevice) this;
            synchronized (castHTTPServerForMobileDevice.mLock) {
                String str2 = castHTTPServerForMobileDevice.mSessionId;
                if (str2 != null) {
                    if (!(castHTTPServerForMobileDevice.mSocket == null || CastStopSource.TWILIGHT == castStopSource || CastStopSource.CLIENT_ERROR == castStopSource)) {
                        Message message = new Message(str2, Message.Type.STOP, castStopSource.getName());
                        if (!castHTTPServerForMobileDevice.mIsDataChannelEnabled.get()) {
                            castHTTPServerForMobileDevice.mEventHandler.post(new Runnable(message.A01().toString()) {
                                /* class com.oculus.horizon.cast.CastHTTPServerForMobileDevice.AnonymousClass1 */
                                public final /* synthetic */ String val$payload;

                                {
                                    this.val$payload = r2;
                                }

                                public final void run() {
                                    synchronized (CastHTTPServerForMobileDevice.this.mLock) {
                                        try {
                                            AbstractC09411eb r6 = CastHTTPServerForMobileDevice.this.mSocket;
                                            if (r6 != null) {
                                                String str = this.val$payload;
                                                AnonymousClass1ec r1 = new AnonymousClass1ec(AnonymousClass1fE.Text, true);
                                                r1.A02 = null;
                                                r1.A03 = str.getBytes(AnonymousClass1ec.A06);
                                                r1.A04 = str.length();
                                                r1.A05 = str;
                                                AbstractC09411eb.A01(r6, r1);
                                            }
                                        } catch (IOException e) {
                                            AnonymousClass0NO.A0B(CastHTTPServerForMobileDevice.TAG, "Failed to send message", e);
                                            CastHTTPServerForMobileDevice castHTTPServerForMobileDevice = CastHTTPServerForMobileDevice.this;
                                            castHTTPServerForMobileDevice.mCastAnalytics.A06(castHTTPServerForMobileDevice.mSessionId, e.getMessage(), null);
                                        }
                                    }
                                }
                            });
                        }
                    }
                    castHTTPServerForMobileDevice.A08(castStopSource.getName());
                    castHTTPServerForMobileDevice.mIsTwilightDataChannelReady.set(false);
                    return;
                }
                return;
            }
        }
        synchronized (this.mLock) {
            str = this.mSessionId;
        }
        if (str != null) {
            A08(castStopSource.getName());
        }
    }

    public final boolean A09(@Nullable String str) {
        boolean z;
        synchronized (this.mLock) {
            if (!AnonymousClass0I1.A03("debug.oculus.unauthorizedCast", "0").equals(DiskLruCache.VERSION_1) || !this.mDebugMode.A02()) {
                String str2 = this.mSessionId;
                if (str2 == null || str == null || !MessageDigest.isEqual(str2.getBytes(), str.getBytes())) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public CastHTTPServerBase(Context context, CastAnalytics castAnalytics, DebugMode debugMode, AbstractC06600ny r6, Handler handler) {
        this.mContext = context;
        this.mCastAnalytics = castAnalytics;
        this.mDebugMode = debugMode;
        this.mMobileConfig = r6;
        this.mEventHandler = handler;
        ShutdownBroadcastReceiver shutdownBroadcastReceiver = new ShutdownBroadcastReceiver();
        this.mShutdownReceiver = shutdownBroadcastReceiver;
        shutdownBroadcastReceiver.registerReceiver(this.mContext);
    }
}
