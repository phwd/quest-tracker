package defpackage;

/* renamed from: Aa  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0000Aa extends AbstractC2032cb {
    public final /* synthetic */ C0122Ca i;

    public C0000Aa(C0122Ca ca, boolean z) {
        this.i = ca;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        return Boolean.valueOf(this.i.P);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        AbstractC0061Ba ba;
        this.i.P = false;
        if (((Boolean) obj).booleanValue() && (ba = this.i.G) != null) {
            ba.g();
        }
    }
}
