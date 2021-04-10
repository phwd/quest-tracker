package com.facebook.assistant.clientplatform.keyboard.learning;

import X.AbstractC0103Ah;
import X.AbstractC0105Aj;
import X.C0112Aq;
import X.C0755gg;
import X.C0832jW;
import X.h1;
import android.content.Context;

public final class LearningListener implements AbstractC0103Ah {
    public final long A00;
    public final Context A01;
    public final C0832jW A02;
    public final String A03 = "oculus";
    public final String A04;
    public final AbstractC0105Aj A05 = new C0755gg(this);
    public final C0112Aq A06;

    public LearningListener(Context context, long j) {
        this.A01 = context;
        this.A04 = "raw/data/vocab.txt";
        this.A02 = C0832jW.A00;
        this.A00 = j;
        this.A06 = C0112Aq.A00();
    }

    @Override // X.AbstractC0103Ah
    public final void A3e() {
        this.A06.A02(h1.class, this.A05);
    }

    @Override // X.AbstractC0103Ah
    public final void A3g() {
        this.A06.A04(h1.class, this.A05);
    }
}
