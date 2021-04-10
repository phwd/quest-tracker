package X;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class WV {
    public static final WU A05 = new WU();
    public static final List A06;
    public AbstractC0105Aj A00;
    public AbstractC0105Aj A01;
    public C0112Aq A02;
    public final WX A03;
    public final List A04;

    static {
        List singletonList = Collections.singletonList("WakeWordIntro");
        C0514bB.A01(singletonList, "java.util.Collections.singletonList(element)");
        A06 = singletonList;
    }

    public WV(HandlerC0422Wz wz) {
        C0514bB.A02(wz, "assistantPanelManager");
        C0112Aq A002 = C0112Aq.A00();
        C0514bB.A01(A002, "AssistantMessageBus.getInstance()");
        this.A02 = A002;
        ArrayList arrayList = new ArrayList();
        W0 A003 = W0.A00();
        C0514bB.A01(A003, "AssistantConfig.get()");
        if (A003.A00.getBoolean("enable_assistant_wakeword", false)) {
            arrayList.add(new C00959z(null));
        }
        W0 A004 = W0.A00();
        C0514bB.A01(A004, "AssistantConfig.get()");
        if (A004.A00.getBoolean("enable_voice_selection", false)) {
            arrayList.add(new C1281wb());
        }
        this.A04 = arrayList;
        this.A03 = new WX(arrayList);
        C0112Aq A005 = C0112Aq.A00();
        C0514bB.A01(A005, "AssistantMessageBus.getInstance()");
        this.A02 = A005;
        C1266wM wMVar = new C1266wM(this);
        this.A01 = wMVar;
        this.A00 = new C1267wN(this);
        A005.A02(C1289wj.class, wMVar);
        C0112Aq aq = this.A02;
        AbstractC0105Aj aj = this.A00;
        if (aj == null) {
            C0514bB.A03("assistantCloseNuxCloseBusSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq.A02(C1288wi.class, aj);
        WX wx = this.A03;
        ArrayList arrayList2 = new ArrayList();
        for (AbstractC0400Wb wb : wx.A02) {
            C1300wu A022 = wb.A02();
            if (A022 != null) {
                arrayList2.add(A022);
            }
        }
        HandlerC0422Wz.A05(arrayList2);
        WX wx2 = this.A03;
        if (wx2.A00 == -1) {
            wx2.A00 = 0;
            List list = wx2.A02;
            if (0 < list.size()) {
                wx2.A01 = (AbstractC0400Wb) list.get(wx2.A00);
                return;
            }
            wx2.A01 = null;
            wx2.A00 = -1;
            return;
        }
        throw new IllegalStateException("Mini Nux has already started");
    }

    public static final void A00(WV wv) {
        C0112Aq aq = wv.A02;
        AbstractC0105Aj aj = wv.A01;
        if (aj == null) {
            C0514bB.A03("assistantNextStepBusSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        aq.A04(C1289wj.class, aj);
        C0112Aq aq2 = wv.A02;
        AbstractC0105Aj aj2 = wv.A00;
        if (aj2 == null) {
            C0514bB.A03("assistantCloseNuxCloseBusSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else {
            aq2.A04(C1288wi.class, aj2);
        }
    }

    public final void A01() {
        C0414Wr A002 = HandlerC0422Wz.A00();
        if (A002.A01 && A002.A00 != 3) {
            HandlerC0422Wz.A02.post(RunnableC0416Wt.A00);
            HandlerC0422Wz.A02();
            AbstractC0400Wb wb = this.A03.A01;
            if (wb != null) {
                C1300wu A022 = wb.A02();
                if (A022 != null) {
                    HandlerC0422Wz.A06.A09(A022, true);
                }
                wb.A06();
            }
            C00799i.A00.logNuxEvent("show_voice_intro_dialog");
        }
    }
}
