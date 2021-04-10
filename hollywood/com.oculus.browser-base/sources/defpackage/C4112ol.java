package defpackage;

import org.chromium.base.Callback;

/* renamed from: ol  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4112ol extends C1992cI0 implements AbstractC2504fI0 {
    public final Callback H;
    public Object I;

    public C4112ol(C1992cI0 ci0, Object obj, Callback callback) {
        super(ci0.G);
        this.H = callback;
        ci0.F.add(this);
        this.I = obj;
    }

    @Override // defpackage.AbstractC2504fI0
    public void a(int i, Object obj) {
        this.I = obj;
        Callback callback = this.H;
        if (callback != null) {
            callback.onResult(this);
        }
    }
}
