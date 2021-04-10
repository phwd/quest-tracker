package defpackage;

/* renamed from: E9  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class E9 implements Runnable {
    public final AbstractC5717y9 F;

    public E9(AbstractC5717y9 y9Var) {
        this.F = y9Var;
    }

    public void run() {
        C4185p9 p9Var;
        View$OnKeyListenerC2476f9 f9Var = ((C5887z9) this.F).F;
        if (f9Var != null && (p9Var = f9Var.O) != null) {
            p9Var.notifyDataSetChanged();
        }
    }
}
