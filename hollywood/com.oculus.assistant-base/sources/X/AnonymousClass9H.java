package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import java.util.ArrayList;

/* renamed from: X.9H  reason: invalid class name */
public final /* synthetic */ class AnonymousClass9H implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$AssistantLogger$2nuaLR3eNPiISZxQGKlAU3LQBuo";
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ int A02;
    public final /* synthetic */ int A03;
    public final /* synthetic */ int A04;
    public final /* synthetic */ int A05;
    public final /* synthetic */ int A06;
    public final /* synthetic */ int A07;
    public final /* synthetic */ AssistantLogger A08;
    public final /* synthetic */ String A09;
    public final /* synthetic */ String A0A;
    public final /* synthetic */ String A0B;
    public final /* synthetic */ ArrayList A0C;

    public /* synthetic */ AnonymousClass9H(AssistantLogger assistantLogger, String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, ArrayList arrayList) {
        this.A08 = assistantLogger;
        this.A09 = str;
        this.A0A = str2;
        this.A0B = str3;
        this.A02 = i;
        this.A03 = i2;
        this.A04 = i3;
        this.A05 = i4;
        this.A06 = i5;
        this.A07 = i6;
        this.A00 = i7;
        this.A01 = i8;
        this.A0C = arrayList;
    }

    public final void run() {
        this.A08.logSmartKeyboardInteractionsOneSession(this.A09, this.A0A, this.A0B, this.A02, this.A03, this.A04, this.A05, this.A06, this.A07, this.A00, this.A01, this.A0C);
    }
}
