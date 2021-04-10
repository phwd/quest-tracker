package defpackage;

/* renamed from: GJ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum GJ0 implements AbstractC2632g4 {
    RS256(-257),
    RS384(-258),
    RS512(-259),
    LEGACY_RS1(-262),
    PS256(-37),
    PS384(-38),
    PS512(-39),
    RS1(-65535);
    
    public final int O;

    /* access modifiers changed from: public */
    GJ0(int i) {
        this.O = i;
    }

    @Override // defpackage.AbstractC2632g4
    public final int a() {
        return this.O;
    }
}
