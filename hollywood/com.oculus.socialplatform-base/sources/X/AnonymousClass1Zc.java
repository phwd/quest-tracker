package X;

import com.facebook.msys.mca.Mailbox;

/* renamed from: X.1Zc  reason: invalid class name */
public class AnonymousClass1Zc implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ AnonymousClass1Zb A00;

    public AnonymousClass1Zc(AnonymousClass1Zb r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final void onCompletion(Mailbox mailbox) {
        mailbox.mCallbackExecutor.execute(this.A00.A07);
    }
}
