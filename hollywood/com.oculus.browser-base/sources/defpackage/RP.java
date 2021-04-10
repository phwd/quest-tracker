package defpackage;

import org.chromium.base.Callback;

/* renamed from: RP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RP extends SP {
    public byte[] d;
    public Callback e;
    public final /* synthetic */ TP f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RP(TP tp, int i, String str, byte[] bArr, Callback callback) {
        super(tp, i, str);
        this.f = tp;
        this.d = bArr;
        this.e = callback;
    }

    @Override // defpackage.SP
    public AbstractC2032cb a() {
        return new QP(this);
    }

    @Override // defpackage.SP
    public boolean equals(Object obj) {
        if (!(obj instanceof RP)) {
            return false;
        }
        return super.equals(obj);
    }
}
