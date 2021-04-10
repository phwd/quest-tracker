package com.oculus.horizon.cast;

import X.AbstractC06600ny;
import X.AbstractC09411eb;
import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass1fR;
import X.AnonymousClass1fb;
import X.C09431el;
import android.content.Context;
import android.os.Handler;
import com.oculus.debug.DebugMode;
import com.oculus.horizon.cast.CastHTTPServerManager;
import com.oculus.horizon.cast.CastWebSocket;
import com.oculus.horizon.logging.contract.FunnelContract;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.FunnelData;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class CastHTTPServerForMobileDevice extends CastHTTPServerBase implements CastWebSocket.SocketMessageListener {
    public static final String INTENT_KEY_CAST_CUSTOM_VIDEO_SPEC_FLAG = "cast_custom_video_spec";
    public static final String INTENT_KEY_CAST_SERVER_ERROR = "cast_server_error";
    public static final String INTENT_KEY_CAST_SERVER_URL = "cast_server_url";
    public static final String INTENT_KEY_CAST_SESSION_ID = "cast_session_id";
    public static final String KEY_RESULT_RECEIVER = "RESULT_RECEIVER";
    public static final String TAG = "CastHTTPServerForMobileDevice";
    public static final int WEBSOCKET_ANSWER_TIMEOUT = 3;
    public String mAnswerJSONString;
    public final AtomicBoolean mEnableSendAppInfo = new AtomicBoolean(false);
    public final AtomicBoolean mIsDataChannelEnabled = new AtomicBoolean(false);
    public final AtomicBoolean mIsTwilightDataChannelReady = new AtomicBoolean(false);
    public boolean mIsVRCameraAvailable = false;
    @Nullable
    public CountDownLatch mOfferAnswerFence;
    public String mOfferJSONString;
    @Nullable
    public AbstractC09411eb mSocket;

    /* renamed from: com.oculus.horizon.cast.CastHTTPServerForMobileDevice$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizon$cast$Message$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.horizon.cast.Message$Type[] r0 = com.oculus.horizon.cast.Message.Type.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.horizon.cast.CastHTTPServerForMobileDevice.AnonymousClass3.$SwitchMap$com$oculus$horizon$cast$Message$Type = r2
                com.oculus.horizon.cast.Message$Type r0 = com.oculus.horizon.cast.Message.Type.STOP     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizon.cast.Message$Type r0 = com.oculus.horizon.cast.Message.Type.ERROR     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cast.CastHTTPServerForMobileDevice.AnonymousClass3.<clinit>():void");
        }
    }

    @Override // com.oculus.horizon.cast.CastWebSocket.SocketMessageListener
    public final void A5h(String str, String str2) {
        boolean A09;
        String str3;
        synchronized (this.mLock) {
            A09 = A09(str);
        }
        if (A09) {
            try {
                str3 = new JSONObject().put(CastHTTPServerBase.OVRPLATFORM_KEY_ANSWER_SDP, str2).put(CastHTTPServerBase.OVRPLATFORM_KEY_RTC_CONNECTION_ID, "").put(CastHTTPServerBase.OVRPLATFORM_KEY_RTC_SESSION_ID, str).toString();
            } catch (JSONException e) {
                this.mCastAnalytics.A06(this.mSessionId, e.getMessage(), null);
                AnonymousClass0NO.A0B(TAG, "failed to create answer json object", e);
                str3 = "";
            }
            synchronized (this.mLock) {
                this.mAnswerJSONString = str3;
                CountDownLatch countDownLatch = this.mOfferAnswerFence;
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
                this.mCastAnalytics.A05(this.mSessionId, str, null);
            }
        }
    }

    @Override // com.oculus.horizon.cast.CastWebSocket.SocketMessageListener
    public final void A61(String str, String str2) {
        synchronized (this.mLock) {
            CastAnalytics castAnalytics = this.mCastAnalytics;
            String str3 = this.mSessionId;
            String str4 = str;
            if (str3 == null) {
                str3 = "";
            }
            FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics._UL_mInjectionContext)).A24();
            A24.A18(CastAnalytics.KEY_SERVER_SESSION_ID, str3);
            if (str == null) {
                str4 = "";
            }
            A24.A18(CastAnalytics.KEY_CLIENT_SESSION_ID, str4);
            if (str2 == null) {
                str2 = "";
            }
            A24.A18("error", str2);
            ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics._UL_mInjectionContext)).A8F(FunnelContract.CAST_SERVER_SESSION_FUNNEL_NAME, CastAnalytics.ACTION_RECEIVED_CLIENT_ERROR, str3, A24);
        }
        if (A09(str)) {
            A07(CastStopSource.CLIENT_ERROR);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0046, code lost:
        if (r8.mIsTwilightDataChannelReady.get() != false) goto L_0x0048;
     */
    @Override // com.oculus.horizon.cast.CastWebSocket.SocketMessageListener
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A64(java.lang.String r9) {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cast.CastHTTPServerForMobileDevice.A64(java.lang.String):void");
    }

    @Override // com.oculus.horizon.cast.CastWebSocket.SocketMessageListener
    public final void A71(String str) {
        boolean A09;
        synchronized (this.mLock) {
            CastAnalytics castAnalytics = this.mCastAnalytics;
            String str2 = this.mSessionId;
            String str3 = str;
            if (str2 == null) {
                str2 = "";
            }
            FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics._UL_mInjectionContext)).A24();
            A24.A18(CastAnalytics.KEY_SERVER_SESSION_ID, str2);
            if (str == null) {
                str3 = "";
            }
            A24.A18(CastAnalytics.KEY_CLIENT_SESSION_ID, str3);
            ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics._UL_mInjectionContext)).A8F(FunnelContract.CAST_SERVER_SESSION_FUNNEL_NAME, CastAnalytics.ACTION_RECEIVED_STOP_CAST_REQUEST, str2, A24);
            A09 = A09(str);
        }
        if (A09) {
            A07(CastStopSource.TWILIGHT);
        }
    }

    @Override // com.oculus.horizon.cast.CastWebSocket.SocketMessageListener
    public final void onClose() {
        AbstractC09411eb r0;
        synchronized (this.mLock) {
            if (this.mIsDataChannelEnabled.get() && (r0 = this.mSocket) != null && r0.state == AnonymousClass1fb.OPEN) {
                final AnonymousClass1fR r02 = AnonymousClass1fR.NormalClosure;
                this.mEventHandler.post(new Runnable() {
                    /* class com.oculus.horizon.cast.CastHTTPServerForMobileDevice.AnonymousClass2 */
                    public final /* synthetic */ boolean val$initiatedByRemote = false;
                    public final /* synthetic */ String val$reason = "WebSocket closing with data channel enabled";

                    public final void run() {
                        synchronized (CastHTTPServerForMobileDevice.this.mLock) {
                            try {
                                AbstractC09411eb r7 = CastHTTPServerForMobileDevice.this.mSocket;
                                if (r7 != null) {
                                    AnonymousClass1fR r6 = r02;
                                    String str = this.val$reason;
                                    boolean z = this.val$initiatedByRemote;
                                    AnonymousClass1fb r1 = r7.state;
                                    r7.state = AnonymousClass1fb.CLOSING;
                                    if (r1 == AnonymousClass1fb.OPEN) {
                                        AbstractC09411eb.A01(r7, new C09431el(r6, str));
                                    } else {
                                        AbstractC09411eb.A00(r7, r6, str, z);
                                    }
                                    CastHTTPServerForMobileDevice.this.mSocket = null;
                                }
                            } catch (IOException e) {
                                String message = e.getMessage();
                                if (message == null) {
                                    message = "Unkown IOException";
                                }
                                CastHTTPServerForMobileDevice castHTTPServerForMobileDevice = CastHTTPServerForMobileDevice.this;
                                castHTTPServerForMobileDevice.mCastAnalytics.A06(castHTTPServerForMobileDevice.mSessionId, AnonymousClass006.A05("IOException when closing WebSocket: ", message), null);
                            }
                        }
                    }
                });
            }
        }
    }

    public CastHTTPServerForMobileDevice(Context context, CastAnalytics castAnalytics, DebugMode debugMode, AbstractC06600ny r6, Handler handler) {
        super(context, castAnalytics, debugMode, r6, handler);
    }

    @Override // com.oculus.horizon.cast.CastWebSocket.SocketMessageListener
    public final void A6x(String str, VideoSpec videoSpec, boolean z, boolean z2) {
        if (A09(str)) {
            synchronized (this.mLock) {
                String str2 = this.mSessionId;
                if (str2 == null) {
                    AnonymousClass0NO.A08(TAG, "onStartCast mSessionId is null");
                } else {
                    CastAnalytics castAnalytics = this.mCastAnalytics;
                    if (str2 == null) {
                        str2 = "";
                    }
                    FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics._UL_mInjectionContext)).A24();
                    A24.A18(CastAnalytics.KEY_SERVER_SESSION_ID, str2);
                    if (str == null) {
                        str = "";
                    }
                    A24.A18(CastAnalytics.KEY_CLIENT_SESSION_ID, str);
                    ((EventManager) AnonymousClass0J2.A03(0, 242, castAnalytics._UL_mInjectionContext)).A8F(FunnelContract.CAST_SERVER_SESSION_FUNNEL_NAME, CastAnalytics.ACTION_RECEIVED_START_CAST_REQUEST, str2, A24);
                    this.mIsDataChannelEnabled.set(z);
                    this.mEnableSendAppInfo.set(z2);
                    A06(1, new CastHTTPServerManager.StartCastParams(this.mSessionId, videoSpec, z));
                }
            }
        }
    }
}
