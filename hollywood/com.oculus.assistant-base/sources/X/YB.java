package X;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.debug.tracer.Tracer;
import com.facebook.quicklog.QuickPerformanceLogger;
import com.oculus.aidl.OVRServiceInterface;
import com.oculus.assistant.R;
import com.oculus.assistant.assistantutils.SystemUXUtil;
import com.oculus.assistant.service.api.attention.AssistantErrorType;
import com.oculus.assistant.service.api.attention.AssistantInteractionState;
import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;
import com.oculus.os.SettingsManager;

public final class YB {
    public int A00;
    public String A01;
    public boolean A02;
    public boolean A03 = false;
    public AssistantInteractionState A04 = AssistantInteractionState.UNKNOWN;
    public String A05;
    public String A06;
    public boolean A07;
    public final Context A08;
    public final Handler A09 = new Handler(Looper.getMainLooper());
    public final C0098Ac A0A = new C0098Ac("OculusAssistantAttentionHandler");
    public final C0098Ac A0B = new C0098Ac("OculusAssistantAttentionHandler");
    public final Runnable A0C = RunnableC0433Xk.A00;
    public final YP A0D = YP.A00();

    public static String A00(YB yb, C0098Ac ac, String str, IOculusAssistantAttentionListener iOculusAssistantAttentionListener) {
        String A002 = ac.A00(str, iOculusAssistantAttentionListener);
        AssistantInteractionState assistantInteractionState = yb.A04;
        if (assistantInteractionState != AssistantInteractionState.UNKNOWN) {
            try {
                iOculusAssistantAttentionListener.A4G(assistantInteractionState);
            } catch (RemoteException e) {
                C0139Dd.A0L("OculusAssistantAttentionHandler", e.getMessage(), e);
            }
            String str2 = yb.A06;
            if (str2 != null) {
                try {
                    iOculusAssistantAttentionListener.A4O(str2, yb.A07);
                    return A002;
                } catch (RemoteException e2) {
                    C0139Dd.A0L("OculusAssistantAttentionHandler", e2.getMessage(), e2);
                }
            }
        }
        return A002;
    }

    public static void A02(YB yb, int i) {
        Tracer.A01("updateAttentionState");
        try {
            if (i != yb.A00) {
                yb.A00 = i;
                HandlerC0422Wz wz = HandlerC0422Wz.A06;
                X0 x0 = new X0();
                x0.A00(i);
                wz.A0A(x0);
            }
        } finally {
            Tracer.A00();
        }
    }

    /* JADX INFO: finally extract failed */
    public final void A04(AssistantInteractionState assistantInteractionState) {
        int i;
        long j;
        Tracer.A01("OculusAssistantAttentionHandler.onInteractionStateChanged");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("onInteractionStateChangedImpl: state changed: ");
            sb.append(assistantInteractionState);
            C0139Dd.A09("OculusAssistantAttentionHandler", sb.toString());
            boolean z = false;
            if (assistantInteractionState != AssistantInteractionState.LISTENING || !this.A02) {
                if (!(assistantInteractionState == AssistantInteractionState.ACTIVATED || assistantInteractionState == AssistantInteractionState.UNKNOWN)) {
                    z = this.A0A.A02(new C1359xr(assistantInteractionState));
                }
                this.A04 = assistantInteractionState;
                Tracer.A01("switch");
                try {
                    switch (assistantInteractionState.ordinal()) {
                        case 0:
                        case 5:
                        case 6:
                        case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                            this.A04 = AssistantInteractionState.UNKNOWN;
                            if (SystemUXUtil.A00() >= 1) {
                                if (SystemUXUtil.A00() >= 1) {
                                    this.A09.removeCallbacks(this.A0C);
                                }
                                Handler handler = this.A09;
                                Runnable runnable = this.A0C;
                                if (this.A01 == null) {
                                    j = 3000;
                                } else {
                                    j = 15000;
                                }
                                handler.postDelayed(runnable, j);
                                break;
                            }
                            break;
                        case 1:
                            A02(this, R.drawable.ic_attn_listening);
                            if (SystemUXUtil.A00() >= 1) {
                                this.A09.removeCallbacks(this.A0C);
                            }
                            A01();
                            this.A0A.A02(C1357xp.A00);
                            break;
                        case 2:
                            YP yp = this.A0D;
                            String str = this.A06;
                            C1400ye yeVar = yp.A04;
                            if (YR.A02(yeVar.A02, YQ.INTERACTION_STARTED)) {
                                QuickPerformanceLogger quickPerformanceLogger = yeVar.A01;
                                int A002 = yeVar.A00();
                                quickPerformanceLogger.markerPoint(A002, "finished_transcript_processing");
                                quickPerformanceLogger.markerAnnotate(A002, "finished_transcript_processing", str);
                                if (TextUtils.isEmpty(str)) {
                                    yeVar.A03(4);
                                }
                            }
                            C0441Yl.A00().A03();
                            if (TextUtils.isEmpty(this.A06)) {
                                SharedPreferences sharedPreferences = BX.A00().getSharedPreferences("AssistantServiceConfig", 0);
                                C0414Wr A003 = HandlerC0422Wz.A00();
                                C0514bB.A00(A003);
                                if (3 == A003.A00) {
                                    sharedPreferences.edit().putBoolean("should_show_in_game_opt_out_flag", false).apply();
                                }
                                boolean A032 = C0398Vv.A03();
                                i = R.raw.oculus_assistant_empty_transcript;
                                if (A032) {
                                    i = R.raw.oculus_assistant_empty_transcript_loud;
                                }
                                C0443Yn.A00(i, null);
                                break;
                            }
                            break;
                        case 3:
                            A02(this, R.drawable.ic_attn_thinking);
                            if (!TextUtils.isEmpty(this.A06)) {
                                boolean A033 = C0398Vv.A03();
                                i = R.raw.oculus_assistant_stop_listening;
                                if (A033) {
                                    i = R.raw.oculus_assistant_stop_listening_loud;
                                }
                                C0443Yn.A00(i, null);
                                break;
                            }
                            break;
                        case 8:
                            this.A06 = null;
                            this.A01 = null;
                            this.A05 = null;
                            A02(this, R.drawable.ic_attn_activate);
                            break;
                    }
                    Tracer.A00();
                    Tracer.A01("mSystemAttentionSystemListeners");
                    if (!z) {
                        try {
                            this.A0B.A02(new C1356xo(assistantInteractionState));
                        } catch (Throwable th) {
                            Tracer.A00();
                            throw th;
                        }
                    }
                    Tracer.A00();
                } catch (Throwable th2) {
                    Tracer.A00();
                    throw th2;
                }
            } else {
                this.A02 = false;
            }
        } finally {
            Tracer.A00();
        }
    }

    public final void A06(String str) {
        this.A05 = str;
        HandlerC0422Wz wz = HandlerC0422Wz.A06;
        if (wz.A0C()) {
            X0 x0 = new X0();
            x0.A02 = false;
            x0.A04 = 1;
            x0.A07 = this.A05;
            x0.A05 = this.A01;
            if (W0.A00().A00.getBoolean("enable_dynamic_attn_system_time", false)) {
                x0.A03 = Integer.valueOf(Vu.A00(this.A01, this.A05));
            }
            wz.A0A(x0);
        }
    }

    public YB(Context context) {
        if (context != null) {
            this.A08 = context;
            return;
        }
        throw null;
    }

    public static void A01() {
        if (C0398Vv.A03()) {
            C0443Yn.A00(R.raw.oculus_assistant_listening_loud, C0431Xi.A00);
        } else {
            C0443Yn.A00(R.raw.oculus_assistant_listening, C0432Xj.A00);
        }
    }

    public final void A03(AssistantErrorType assistantErrorType) {
        Context context;
        int i;
        String string;
        A02(this, R.drawable.ic_attn_error);
        C0441Yl.A00().A03();
        switch (assistantErrorType.ordinal()) {
            case 1:
                context = this.A08;
                i = R.string.error_mic_not_available;
                string = context.getString(i);
                break;
            case 2:
                context = this.A08;
                i = R.string.error_no_internet;
                string = context.getString(i);
                break;
            case 3:
                if (!new SettingsManager(BX.A00()).getBoolean("first_time_nux_complete", false)) {
                    return;
                }
            case 4:
            case 5:
            default:
                string = OacrConstants.AUTO_SPEECH_DOMAIN;
                break;
            case 6:
                context = this.A08;
                i = R.string.error_not_available_in_game;
                string = context.getString(i);
                break;
            case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                context = this.A08;
                i = R.string.error_volume_too_low;
                string = context.getString(i);
                break;
            case 8:
                context = this.A08;
                i = R.string.error_old_os_version;
                string = context.getString(i);
                break;
            case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                context = this.A08;
                i = R.string.error_mic_muted;
                string = context.getString(i);
                break;
            case 10:
                context = this.A08;
                i = R.string.error_not_available_in_this_game;
                string = context.getString(i);
                break;
        }
        C0112Aq.A00().A01(new C1204vJ(assistantErrorType.name(), string));
        C0098Ac ac = this.A0A;
        if (!ac.A02(new C1354xm(this, assistantErrorType))) {
            if (TextUtils.isEmpty(string)) {
                string = this.A08.getString(R.string.error_activating);
            }
            if (!ac.A02(new C1358xq(string))) {
                this.A0B.A02(new C1355xn(string));
            }
            C0443Yn.A00(R.raw.oculus_assistant_empty_transcript, null);
        }
    }

    public final void A05(String str) {
        A02(this, R.drawable.ic_attn_thinking_response);
        this.A01 = str;
        if (!this.A0A.A02(new C1363xv(str))) {
            this.A0B.A02(new C1362xu(str));
        }
    }

    public final void A07(String str, boolean z) {
        A02(this, R.drawable.ic_attn_transcribing);
        this.A06 = str;
        this.A07 = z;
        if (!this.A0A.A02(new C1367xz(str, z))) {
            this.A0B.A02(new C1366xy(str, z));
        }
    }
}
