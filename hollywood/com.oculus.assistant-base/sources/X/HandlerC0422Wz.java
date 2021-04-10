package X;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.acra.ErrorReporter;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.debug.tracer.Tracer;
import com.facebook.proxygen.TraceFieldType;
import com.oculus.assistant.R;
import com.oculus.assistant.assistantutils.SystemUXUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.Wz  reason: case insensitive filesystem */
public final class HandlerC0422Wz extends Handler {
    public static String A00;
    public static boolean A01;
    public static final Handler A02;
    public static final Messenger A03 = new Messenger(A06);
    public static final AY A04 = new AY();
    public static final AY A05 = new AY();
    public static final HandlerC0422Wz A06 = new HandlerC0422Wz();
    public static final HashMap A07 = new HashMap();
    public static final Uri A08 = Uri.parse("content://com.oculus.vrshell.assistant/status");
    public static final HandlerThread A09;
    public static final Object A0A = new Object();
    public static final Runnable A0B = RunnableC0415Ws.A00;

    static {
        HandlerThread handlerThread = new HandlerThread("ShowAttentionSystem");
        A09 = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(A09.getLooper());
        A02 = handler;
        handler.post(RunnableC0413Wq.A00);
    }

    public static final C0414Wr A00() {
        Tracer.A01("getPanelState");
        try {
            boolean z = true;
            int i = 1;
            if (C0398Vv.A03()) {
                i = 3;
                z = false;
            }
            return new C0414Wr(i, z);
        } finally {
            Tracer.A00();
        }
    }

    public static final void A01() {
        synchronized (A0A) {
            A02.removeCallbacks(A0B);
        }
    }

    public static final void A02() {
        BX.A00().sendBroadcast(SystemUXUtil.A04("hide"));
    }

    public static final void A03(C1300wu wuVar) {
        C0514bB.A02(wuVar, "dialog");
        A07.put(wuVar.A01.getString("id"), wuVar);
    }

    public static final void A04(X1 x1) {
        AY ay = A05;
        if (x1 != null) {
            CopyOnWriteArrayList copyOnWriteArrayList = ay.A01;
            if (!copyOnWriteArrayList.contains(x1)) {
                copyOnWriteArrayList.add(x1);
            }
        }
    }

    public static final void A05(Iterable iterable) {
        C0514bB.A02(iterable, "dialogIterable");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            C1300wu wuVar = (C1300wu) it.next();
            A07.put(wuVar.A01.getString("id"), wuVar);
        }
    }

    public static final void A06(JSONObject jSONObject) {
        Intent A042 = SystemUXUtil.A04("show");
        if (jSONObject != null) {
            A042.putExtra("data", jSONObject.toString());
        }
        BX.A00().sendBroadcast(A042);
    }

    public static final void A07(boolean z) {
        A02.post(new RunnableC0421Wy(z));
    }

    public final void A09(C1300wu wuVar, boolean z) {
        C0514bB.A02(wuVar, "dialog");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("data", wuVar.A04());
            jSONObject.put("type", wuVar.A03());
            jSONObject.put("clear-dialog-stack", z);
            A06(jSONObject);
        } catch (JSONException e) {
            C0139Dd.A0L("AssistantPanelManager", e.getMessage(), e);
        }
    }

    public final void A0A(X0 x0) {
        JSONObject jSONObject;
        boolean z;
        int i;
        int i2;
        String str;
        C0514bB.A02(x0, "builder");
        if (TextUtils.isEmpty(x0.A05)) {
            W0 A002 = W0.A00();
            C0514bB.A01(A002, "AssistantConfig.get()");
            if (!A002.A00.getBoolean("disableo_attn_message_truncation_war", false)) {
                Log.d("AssistantPanelManager", "Attention message with no content suppressed.");
                return;
            }
        }
        if (C0398Vv.A02()) {
            if (C0514bB.A05("IDLE", x0.A06)) {
                i = R.drawable.ic_voice_assistant;
                i2 = R.string.notif_title_general;
                String str2 = x0.A05;
                if (!(str2 == null || str2.length() == 0)) {
                    A00 = str2;
                }
                str = A00;
            } else {
                i = R.drawable.ic_stat_listening;
                i2 = R.string.notif_title_transcribing;
                str = x0.A05;
            }
            C00799i.A00.logAttentionSystem("showNotification");
            Vz.A00(BX.A00(), 1005, i2, str, i);
            return;
        }
        Tracer.A01("showAttentionSystem");
        try {
            Looper myLooper = Looper.myLooper();
            Handler handler = A02;
            if (!C0514bB.A05(myLooper, handler.getLooper())) {
                handler.post(new RunnableC0419Ww(x0));
            } else if (!A01 && A0C()) {
                Intent A042 = SystemUXUtil.A04("show-attention");
                try {
                    C00859p r1 = x0.A00;
                    if (r1 != null) {
                        jSONObject = new JSONObject();
                        jSONObject.put("data", r1.A04());
                        jSONObject.put("type", r1.A03());
                        jSONObject.put("clear-dialog-stack", x0.A08);
                    } else {
                        jSONObject = new JSONObject();
                    }
                    String str3 = x0.A06;
                    if (str3 != null) {
                        jSONObject.put("state", str3);
                    }
                    String str4 = x0.A05;
                    String str5 = "message";
                    if (str4 != null) {
                        jSONObject.put(str5, str4);
                    }
                    String str6 = x0.A07;
                    if (str6 != null) {
                        if (SystemUXUtil.A00() < 2) {
                            String str7 = x0.A05;
                            if (str7 != null) {
                                str6 = AnonymousClass08.A05(str7, "\n\n", str6);
                            }
                        } else {
                            str5 = "submessage";
                        }
                        jSONObject.put(str5, str6);
                    }
                    Integer num = x0.A03;
                    if (num != null) {
                        jSONObject.put(TraceFieldType.Duration, num.intValue());
                    }
                    Integer num2 = x0.A04;
                    if (num2 != null) {
                        jSONObject.put("typeface", num2.intValue());
                    }
                    Boolean bool = x0.A02;
                    boolean z2 = true;
                    if (bool != null) {
                        z = bool.booleanValue();
                    } else {
                        z = false;
                        if (SystemUXUtil.A00() >= 6) {
                            z = true;
                        }
                    }
                    jSONObject.put("animate", z);
                    C1307x1 x1Var = x0.A01;
                    if (x1Var != null) {
                        jSONObject.put("state-icon", x1Var.A00());
                    }
                    if (A00().A00 == 3) {
                        z2 = false;
                    }
                    jSONObject.put("hit-testable", z2);
                    C0514bB.A01(A042.putExtra("data", jSONObject.toString()), "panelIntent.putExtra(Att…lder.toJson().toString())");
                } catch (JSONException e) {
                    C0139Dd.A0L("AssistantPanelManager", e.getMessage(), e);
                }
                BX.A00().sendBroadcast(A042);
            }
        } finally {
            Tracer.A00();
        }
    }

    public final void handleMessage(Message message) {
        C1300wu wuVar;
        String str;
        C0514bB.A02(message, "msg");
        super.handleMessage(message);
        int i = message.what;
        if (i == 1) {
            Bundle data = message.getData();
            String string = data.getString("action", OacrConstants.AUTO_SPEECH_DOMAIN);
            String string2 = data.getString("id", OacrConstants.AUTO_SPEECH_DOMAIN);
            Bundle bundle = data.getBundle("extras");
            C0514bB.A01(string2, "id");
            C0514bB.A01(string, "action");
            C0514bB.A02(string2, "id");
            C0514bB.A02(string, "action");
            if (SystemUXUtil.A00() < 4) {
                int hashCode = string.hashCode();
                if (hashCode == -1174796206) {
                    str = "tertiary";
                } else if (hashCode == -817598092) {
                    str = "secondary";
                } else if (hashCode == -314765822) {
                    str = "primary";
                }
                if (string.equals(str)) {
                    A02();
                }
            }
            HashMap hashMap = A07;
            if (hashMap.containsKey(string2) && (wuVar = (C1300wu) hashMap.get(string2)) != null) {
                wuVar.A42(string2, string, bundle);
            }
            AY ay = A05;
            C1302ww wwVar = new C1302ww(string2, string, bundle);
            Iterator it = ay.A01.iterator();
            while (true) {
                boolean z = false;
                while (true) {
                    if (it.hasNext()) {
                        boolean A4v = wwVar.A4v(it.next());
                        if (z || A4v) {
                            z = true;
                        }
                    } else {
                        return;
                    }
                }
            }
        } else if (i == 2) {
            Bundle data2 = message.getData();
            String string3 = data2.getString("action", OacrConstants.AUTO_SPEECH_DOMAIN);
            String string4 = data2.getString("id", OacrConstants.AUTO_SPEECH_DOMAIN);
            C0514bB.A01(string4, "id");
            C0514bB.A01(string3, "action");
            AY ay2 = A04;
            C1301wv wvVar = new C1301wv(string4, string3);
            Iterator it2 = ay2.A01.iterator();
            while (true) {
                boolean z2 = false;
                while (true) {
                    if (it2.hasNext()) {
                        boolean A4v2 = wvVar.A4v(it2.next());
                        if (z2 || A4v2) {
                            z2 = true;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public HandlerC0422Wz() {
        super(Looper.getMainLooper());
    }

    public final void A08() {
        Looper myLooper = Looper.myLooper();
        Handler handler = A02;
        if (!C0514bB.A05(myLooper, handler.getLooper())) {
            handler.post(RunnableC0420Wx.A00);
            return;
        }
        C00799i.A00.logAttentionSystem("showInitializingBubble");
        X0 x0 = new X0(null, null, null, null, 15, null);
        x0.A00(R.drawable.ic_attn_activate);
        x0.A03 = Integer.valueOf((int) ErrorReporter.MAX_ANR_TRACES_TIME_DELTA_MS);
        A0A(x0);
    }

    public final void A0B(String str) {
        Looper myLooper = Looper.myLooper();
        Handler handler = A02;
        if (!C0514bB.A05(myLooper, handler.getLooper())) {
            handler.post(new RunnableC0418Wv(str));
            return;
        }
        X0 x0 = new X0(null, null, null, null, 15, null);
        x0.A05 = str;
        A0A(x0);
    }

    public final boolean A0C() {
        C0414Wr A002 = A00();
        C0514bB.A00(A002);
        if (A002.A01 || SystemUXUtil.A01("com.oculus.vrshell.assistant_shell_version", "com.oculus.vrshell") >= 1) {
            return true;
        }
        return false;
    }
}
