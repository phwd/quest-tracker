package defpackage;

import org.chromium.base.Callback;

/* renamed from: LP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LP extends SP {
    public Callback d;
    public final /* synthetic */ TP e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LP(TP tp, int i, String str, Callback callback) {
        super(tp, i, str);
        this.e = tp;
        this.d = callback;
    }

    @Override // defpackage.SP
    public AbstractC2032cb a() {
        return new KP(this);
    }

    @Override // defpackage.SP
    public boolean equals(Object obj) {
        if (!(obj instanceof LP)) {
            return false;
        }
        return super.equals(obj);
    }
}
