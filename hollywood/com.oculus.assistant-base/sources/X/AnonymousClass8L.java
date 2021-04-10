package X;

import java.util.Iterator;

/* renamed from: X.8L  reason: invalid class name */
public final class AnonymousClass8L implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$33$1";
    public final /* synthetic */ double A00;
    public final /* synthetic */ C0724g6 A01;

    public AnonymousClass8L(C0724g6 g6Var, double d) {
        this.A01 = g6Var;
        this.A00 = d;
    }

    public final void run() {
        C0740gP gPVar = this.A01.A01;
        gPVar.A0h.A01(new C0808hw((float) this.A00));
        Iterator it = gPVar.A10.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }
}
