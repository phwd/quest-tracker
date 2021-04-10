package com.oculus.horizon.cast;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.logging.contract.FunnelContract;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.FunnelData;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class CastAnalytics {
    public static final String ACTION_CAST_HANDSHAKE_COMPLETED = "cast_handshake_completed";
    public static final String ACTION_CAST_TO_BROWSER_ANSWER_TIMEOUT = "www_answer_timeout";
    public static final String ACTION_GET_VRCAMERA_AVAILABLE = "get_vrcamera_available";
    public static final String ACTION_HMD_SHUT_DOWN = "hmd_shut_down";
    public static final String ACTION_RECEIVED_ANSWER_FROM_CLIENT = "received_answer_from_client";
    public static final String ACTION_RECEIVED_CLIENT_ERROR = "received_client_error";
    public static final String ACTION_RECEIVED_START_CAST_REQUEST = "received_start_cast_request_from_client";
    public static final String ACTION_RECEIVED_STOP_CAST_REQUEST = "received_stop_cast_request_from_client";
    public static final String ACTION_SENT_OFFER_TO_CLIENT = "sent_offer_to_client";
    public static final String ACTION_SERVER_ERROR = "server_error";
    public static final String ACTION_SERVER_INVALID_NETWORK = "server_invalid_network";
    public static final String ACTION_SET_VRCAMERA_AVAILABLE = "set_vrcamera_available";
    public static final String ACTION_START_CAST = "start_cast";
    public static final String ACTION_START_CAST_SERVER = "start_cast_server";
    public static final String ACTION_STOP_CAST_SERVER = "stop_cast_server";
    public static final String ACTION_WEBSOCKET_EXCEPTION = "websocket_exception";
    public static final String KEY_CAST_SERVER_URL = "url";
    public static final String KEY_CLIENT_SESSION_ID = "client_session_id";
    public static final String KEY_ERROR = "error";
    public static final String KEY_SERVER_SESSION_ID = "server_session_id";
    public static final String KEY_SESSION_DURATION_MS = "duration_ms";
    public static final String KEY_SHUT_DOWN_SOURCE = "shut_down_source";
    public static final String KEY_START_CAST_SOURCE = "start_cast_source";
    public static final String KEY_STOP_CAST_SOURCE = "stop_cast_source";
    public static final String KEY_VRCAMERA_AVAILABLE = "vrcamera_available";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static String A00(@Nullable Boolean bool) {
        if (bool == null || !bool.booleanValue()) {
            return FunnelContract.CAST_SERVER_SESSION_FUNNEL_NAME;
        }
        return FunnelContract.CAST_TO_BROWSER_SERVER_SESSION_FUNNEL_NAME;
    }

    public final void A01(@Nullable String str, @Nullable Boolean bool) {
        if (str == null) {
            str = "";
        }
        FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A24();
        A24.A18(KEY_SERVER_SESSION_ID, str);
        A24.A18("error", "Failed to wait for peer answer on server (timeout)");
        ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A8F(A00(bool), ACTION_CAST_TO_BROWSER_ANSWER_TIMEOUT, str, A24);
    }

    public final void A02(@Nullable String str, @Nullable Boolean bool) {
        if (str == null) {
            str = "";
        }
        FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A24();
        A24.A18(KEY_SERVER_SESSION_ID, str);
        ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A8F(A00(bool), ACTION_CAST_HANDSHAKE_COMPLETED, str, A24);
    }

    public final void A03(@Nullable String str, @Nullable Boolean bool) {
        if (str == null) {
            str = "";
        }
        FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A24();
        A24.A18(KEY_SERVER_SESSION_ID, str);
        ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A8F(A00(bool), ACTION_SENT_OFFER_TO_CLIENT, str, A24);
    }

    public final void A04(@Nullable String str, String str2) {
        if (str == null) {
            str = "";
        }
        FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A24();
        A24.A18(KEY_SERVER_SESSION_ID, str);
        if (str2 == null) {
            str2 = "";
        }
        A24.A18(KEY_SHUT_DOWN_SOURCE, str2);
        ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A8F(FunnelContract.CAST_SERVER_SESSION_FUNNEL_NAME, ACTION_HMD_SHUT_DOWN, str, A24);
    }

    public final void A05(@Nullable String str, @Nullable String str2, @Nullable Boolean bool) {
        if (str == null) {
            str = "";
        }
        FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A24();
        A24.A18(KEY_SERVER_SESSION_ID, str);
        if (str2 == null) {
            str2 = "";
        }
        A24.A18(KEY_CLIENT_SESSION_ID, str2);
        ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A8F(A00(bool), ACTION_RECEIVED_ANSWER_FROM_CLIENT, str, A24);
    }

    public final void A06(@Nullable String str, @Nullable String str2, @Nullable Boolean bool) {
        if (str == null) {
            str = "";
        }
        FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A24();
        A24.A18(KEY_SERVER_SESSION_ID, str);
        if (str2 == null) {
            str2 = "";
        }
        A24.A18("error", str2);
        ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A8F(A00(bool), ACTION_SERVER_ERROR, str, A24);
    }

    @Inject
    public CastAnalytics(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
