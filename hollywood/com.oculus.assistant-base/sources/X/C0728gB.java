package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.hyperthrift.HyperThriftBase;

/* renamed from: X.gB  reason: case insensitive filesystem */
public final class C0728gB implements AbstractC0105Aj {
    public final /* synthetic */ C0740gP A00;

    public C0728gB(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        boolean z;
        String str;
        FS fs = new FS();
        C0740gP gPVar = this.A00;
        AnonymousClass8P r3 = gPVar.A0H;
        int i = r3.A01;
        fs.A00 = i;
        C0799hn hnVar = (C0799hn) ak.A2L();
        String str2 = hnVar.A01;
        if (str2 != null) {
            fs.A07 = str2;
            fs.A03 = Integer.valueOf(hnVar.A00);
        }
        FT ft = new FT(fs.A02, fs.A07, fs.A03, i, fs.A01);
        synchronized (gPVar.A0I) {
        }
        AssistantLogger assistantLogger = C00799i.A00;
        assistantLogger.startInteraction();
        String str3 = assistantLogger.mInteractionId;
        assistantLogger.setFlags("oacr");
        int i2 = ft.A03;
        hG hGVar = C00879r.A00;
        hGVar.A05(EnumC00909u.STARTED_LISTENING);
        hGVar.A03(EnumC00899t.OACR_INTERACTION_ID, str3);
        C1383yK yKVar = gPVar.A0t;
        if (yKVar.A01().A04().A00(5) == null || ((HyperThriftBase) yKVar.A01().A04().A00(5)).A00(0) == null) {
            z = false;
        } else {
            z = ((Boolean) ((HyperThriftBase) yKVar.A01().A04().A00(5)).A00(0)).booleanValue();
        }
        hGVar.A04(EnumC00899t.IS_TEST_TRAFFIC, z);
        hGVar.A04(EnumC00899t.IS_DEBUG_BUILD, false);
        hGVar.A04(EnumC00899t.IS_PERF_TEST_BUILD, false);
        hGVar.A03(EnumC00899t.JOIN_ID, str3);
        hGVar.A03(EnumC00899t.JOIN_SOURCE, "client");
        C0782hF hFVar = C00839n.A00;
        C00568g r6 = gPVar.A0N;
        C00849o r4 = new C00849o(i2);
        hFVar.A00 = r4;
        r6.A04 = new C0781hE(r4, hGVar);
        l0 l0Var = gPVar.A0B;
        if (l0Var != null) {
            l0Var.A03();
        }
        if (r3.A01 > 0) {
            r6.A06.set(true);
            r6.A02 = new C0724g6(gPVar);
            r6.A01 = new C0725g7(gPVar);
            if (ft.A05 != null) {
                str = "required";
            } else {
                str = "not_required";
            }
            int i3 = r3.A00;
            Integer num = ft.A04;
            if (num == null) {
                if (0 != null) {
                    num = 0;
                } else {
                    throw new NullPointerException("Both parameters are null");
                }
            }
            C0740gP.A16.post(new C0733gI(gPVar, new iF(r6, "voice_command", i2, OacrConstants.AUTO_SPEECH_DOMAIN, false, str, i3, num.intValue(), str3, gPVar.A08, 20)));
            return;
        }
        throw new IllegalStateException("Microphone Sample Rate not set");
    }
}
