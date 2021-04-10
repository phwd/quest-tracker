package com.oculus.assistant.panel;

import X.BX;
import X.C00538d;
import X.C0446Yq;
import X.C1300wu;
import X.C1308x2;
import X.HandlerC0422Wz;
import X.XA;
import X.Z1;
import X.w1;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.oculus.assistant.R;
import java.util.HashMap;

public final class VoiceSelectionDialog extends C1300wu {
    public w1 A00;
    public C00538d A01;
    public C00538d A02;
    public C0446Yq A03;
    public Context A04;
    public final C1308x2 A05;
    public final HashMap A06 = new HashMap();
    public final Handler A07 = new Handler(Looper.getMainLooper());
    public final Z1 A08 = new Z1();

    public static void A00(VoiceSelectionDialog voiceSelectionDialog, C00538d r4) {
        C00538d r2 = voiceSelectionDialog.A01;
        if (r2 != null && ((XA) r2).A03.equals(((XA) r4).A03)) {
            r2.A02(false);
            voiceSelectionDialog.A01 = null;
            HandlerC0422Wz.A06.A09(voiceSelectionDialog, true);
        }
    }

    private void A01(boolean z) {
        this.A03.A00();
        boolean z2 = false;
        for (C00538d r1 : this.A06.values()) {
            if (r1.A01) {
                r1.A02(false);
                z2 = true;
            }
        }
        this.A01 = null;
        if (z2 && z) {
            HandlerC0422Wz.A06.A09(this, true);
        }
    }

    public VoiceSelectionDialog(Context context, w1 w1Var) {
        this.A04 = context;
        this.A00 = w1Var;
        A0G("dlg-voice-selection");
        A0C(R.string.voice_selection_title);
        A0A(R.string.voice_selection_primary_button);
        A0B(R.string.voice_selection_secondary_button);
        A0E("dialog");
        super.A01.putInt("height", 596);
        C1308x2 x2Var = new C1308x2();
        this.A05 = x2Var;
        x2Var.A03 = BX.A00().getString(R.string.voice_selection_getting_voices);
        Integer[] numArr = ((XA) this.A05).A04.A00;
        numArr[0] = null;
        numArr[4] = 12;
        numArr[0] = null;
        numArr[2] = 12;
        super.A02.clear();
        A0D(this.A05);
        if (w1Var == null || w1Var.A00() == null) {
            throw new IllegalArgumentException("A valid datastore must be provided.");
        }
        this.A03 = new C0446Yq(w1Var, this.A07, true);
    }

    @Override // X.C1300wu
    public final void A05() {
        super.A05();
        A01(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0098, code lost:
        if (r12.equals("secondary") != false) goto L_0x0087;
     */
    @Override // X.X1, X.C1300wu
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A42(java.lang.String r11, java.lang.String r12, android.os.Bundle r13) {
        /*
        // Method dump skipped, instructions count: 400
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.assistant.panel.VoiceSelectionDialog.A42(java.lang.String, java.lang.String, android.os.Bundle):boolean");
    }
}
