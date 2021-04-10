package X;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import com.oculus.assistant.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.Wf  reason: case insensitive filesystem */
public final class C0403Wf {
    public static final C0402We A0C = new C0402We();
    public AbstractC0105Aj A00;
    public AbstractC0105Aj A01;
    public AbstractC0105Aj A02;
    public AbstractC0105Aj A03;
    public AbstractC0105Aj A04;
    public AbstractC0105Aj A05;
    public AbstractC0105Aj A06;
    public C0112Aq A07;
    public boolean A08;
    public final Context A09;
    public final WO A0A;
    public final C1278wY A0B = new C1278wY(this);

    public C0403Wf(Context context) {
        C0514bB.A02(context, "ctx");
        this.A09 = context;
        C0112Aq A002 = C0112Aq.A00();
        C0514bB.A01(A002, "AssistantMessageBus.getInstance()");
        this.A07 = A002;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C1282wc());
        arrayList.add(new C1268wO());
        arrayList.add(new C1265wL());
        arrayList.add(new C1264wK());
        arrayList.add(new A1());
        arrayList.add(new A7());
        arrayList.add(new C1263wJ());
        arrayList.add(new C00959z(null));
        arrayList.add(new C1281wb());
        this.A0A = new WO(arrayList);
        C0112Aq A003 = C0112Aq.A00();
        C0514bB.A01(A003, "AssistantMessageBus.getInstance()");
        this.A07 = A003;
        C1271wR wRVar = new C1271wR(this);
        this.A03 = wRVar;
        this.A06 = new C1272wS(this);
        this.A02 = new C1273wT(this);
        this.A05 = new C1274wU(this);
        this.A00 = new C1275wV(this);
        this.A01 = new C1276wW(this);
        this.A04 = new C1277wX(this);
        A003.A02(C1290wk.class, wRVar);
        C0112Aq aq = this.A07;
        AbstractC0105Aj aj = this.A06;
        if (aj == null) {
            C0514bB.A03("assistantValidVoiceCommandSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq.A02(C1298ws.class, aj);
        C0112Aq aq2 = this.A07;
        AbstractC0105Aj aj2 = this.A02;
        if (aj2 == null) {
            C0514bB.A03("assistantIncorrectVoiceCommandSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq2.A02(C1287wh.class, aj2);
        C0112Aq aq3 = this.A07;
        AbstractC0105Aj aj3 = this.A00;
        if (aj3 == null) {
            C0514bB.A03("assistantCloseNuxCloseBusSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq3.A02(C1284we.class, aj3);
        C0112Aq aq4 = this.A07;
        AbstractC0105Aj aj4 = this.A01;
        if (aj4 == null) {
            C0514bB.A03("assistantDoubleTapTimeoutMessage");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq4.A02(C1285wf.class, aj4);
        C0112Aq aq5 = this.A07;
        AbstractC0105Aj aj5 = this.A05;
        if (aj5 == null) {
            C0514bB.A03("assistantUnknownCommand");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq5.A02(C1297wr.class, aj5);
        C0112Aq aq6 = this.A07;
        AbstractC0105Aj aj6 = this.A04;
        if (aj6 == null) {
            C0514bB.A03("assistantNuxSilenceMessage");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq6.A02(C1292wm.class, aj6);
        HandlerC0422Wz.A05(this.A0A.A03());
        WO wo = this.A0A;
        if (wo.A00 == -1) {
            wo.A00 = 0;
            wo.A01 = (AbstractC0400Wb) wo.A06.get(0);
            return;
        }
        throw new IllegalStateException("Nux has already started");
    }

    public final void A00() {
        Integer num;
        AbstractC0400Wb wb;
        WO wo = this.A0A;
        if (wo == null || (wb = wo.A01) == null) {
            num = null;
        } else {
            num = wb.A01;
        }
        A01(num, false);
        if (this.A08) {
            this.A09.unregisterReceiver(this.A0B);
            this.A08 = false;
        }
    }

    public final void A01(Integer num, boolean z) {
        boolean z2;
        int i;
        WO wo = this.A0A;
        Iterable<C1300wu> A032 = wo.A03();
        C0514bB.A02(A032, "dialogIterable");
        for (C1300wu wuVar : A032) {
            HandlerC0422Wz.A07.put(wuVar.A01.getString("id"), wuVar);
        }
        Resources resources = this.A09.getResources();
        C0514bB.A01(resources, "ctx.resources");
        if (num == null) {
            num = AnonymousClass09.A01;
        }
        C0514bB.A02(resources, "resources");
        C0514bB.A02(num, "playTtsCondition");
        List<AbstractC0400Wb> list = wo.A06;
        for (AbstractC0400Wb wb : list) {
            wb.A05();
        }
        AssistantLogger assistantLogger = C0410Wn.A00;
        assistantLogger.logNuxEvent("nux_vc_close_nux");
        WO.A00(wo);
        if (wo.A00 == list.size()) {
            z2 = true;
            assistantLogger.logNuxEvent("nux_vc_completed");
        } else {
            z2 = false;
            if (Z4.A00.getBoolean("interaction_privacy_acknowledge", false)) {
                assistantLogger.logNuxEvent("nux_vc_activated_nux_incomplete");
            }
        }
        SharedPreferences sharedPreferences = Z4.A00;
        sharedPreferences.edit().putBoolean("user_in_nux", false).apply();
        if (z2) {
            C0112Aq.A00().A01(new C1286wg());
            i = R.raw.tts_what_can_i_say_instruction;
        } else {
            boolean z3 = wo.A02;
            i = R.raw.tts_close_nux;
            if (z3) {
                i = R.raw.tts_silence_close_nux;
            }
        }
        AssetFileDescriptor openRawResourceFd = resources.openRawResourceFd(i);
        Z4.A01(null);
        wo.A01 = null;
        WS ws = wo.A05;
        ws.A00 = true;
        ws.A01.cancel();
        wo.A00 = -1;
        if (AnonymousClass09.A00 == num) {
            WO.A01(wo, openRawResourceFd, new WJ(z2));
        } else if (z) {
            HandlerC0422Wz.A02();
        } else if (AnonymousClass09.A0C == num && !sharedPreferences.getBoolean("interaction_privacy_acknowledge", false)) {
            WO.A01(wo, openRawResourceFd, new WK(wo));
        }
        if (openRawResourceFd != null) {
            openRawResourceFd.close();
        }
        C0112Aq aq = this.A07;
        AbstractC0105Aj aj = this.A03;
        if (aj == null) {
            C0514bB.A03("assistantNextStepBusSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq.A04(C1290wk.class, aj);
        C0112Aq aq2 = this.A07;
        AbstractC0105Aj aj2 = this.A06;
        if (aj2 == null) {
            C0514bB.A03("assistantValidVoiceCommandSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq2.A04(C1298ws.class, aj2);
        C0112Aq aq3 = this.A07;
        AbstractC0105Aj aj3 = this.A02;
        if (aj3 == null) {
            C0514bB.A03("assistantIncorrectVoiceCommandSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq3.A04(C1287wh.class, aj3);
        C0112Aq aq4 = this.A07;
        AbstractC0105Aj aj4 = this.A00;
        if (aj4 == null) {
            C0514bB.A03("assistantCloseNuxCloseBusSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq4.A04(C1284we.class, aj4);
        C0112Aq aq5 = this.A07;
        AbstractC0105Aj aj5 = this.A01;
        if (aj5 == null) {
            C0514bB.A03("assistantDoubleTapTimeoutMessage");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq5.A04(C1285wf.class, aj5);
        C0112Aq aq6 = this.A07;
        AbstractC0105Aj aj6 = this.A05;
        if (aj6 == null) {
            C0514bB.A03("assistantUnknownCommand");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq6.A04(C1297wr.class, aj6);
        C0112Aq aq7 = this.A07;
        AbstractC0105Aj aj7 = this.A04;
        if (aj7 == null) {
            C0514bB.A03("assistantNuxSilenceMessage");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq7.A04(C1292wm.class, aj7);
    }
}
