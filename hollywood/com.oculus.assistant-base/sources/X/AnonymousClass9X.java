package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;

/* renamed from: X.9X  reason: invalid class name */
public final /* synthetic */ class AnonymousClass9X implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$AssistantLogger$naYn8q8EENoZFiVSn-zgOf07q8I";
    public final /* synthetic */ AssistantLogger A00;
    public final /* synthetic */ String A01;
    public final /* synthetic */ String A02;
    public final /* synthetic */ String A03;
    public final /* synthetic */ boolean A04;
    public final /* synthetic */ boolean A05;
    public final /* synthetic */ boolean A06;
    public final /* synthetic */ boolean A07;

    public /* synthetic */ AnonymousClass9X(AssistantLogger assistantLogger, String str, String str2, boolean z, String str3, boolean z2, boolean z3, boolean z4) {
        this.A00 = assistantLogger;
        this.A01 = str;
        this.A02 = str2;
        this.A04 = z;
        this.A03 = str3;
        this.A05 = z2;
        this.A06 = z3;
        this.A07 = z4;
    }

    public final void run() {
        this.A00.logAssistantResponse(this.A01, this.A02, this.A04, this.A03, this.A05, this.A06, this.A07);
    }
}
