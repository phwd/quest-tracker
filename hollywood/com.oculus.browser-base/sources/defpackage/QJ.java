package defpackage;

/* renamed from: QJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum QJ implements AbstractC2632g4 {
    ED256(-260),
    ED512(-261),
    ES256(-7),
    ES384(-35),
    ES512(-36);
    
    public final int L;

    /* access modifiers changed from: public */
    QJ(int i) {
        this.L = i;
    }

    @Override // defpackage.AbstractC2632g4
    public final int a() {
        return this.L;
    }
}
