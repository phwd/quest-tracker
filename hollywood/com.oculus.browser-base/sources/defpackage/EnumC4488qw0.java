package defpackage;

/* renamed from: qw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum EnumC4488qw0 {
    LEFT(1),
    CENTER(2),
    RIGHT(4);
    

    /* renamed from: J  reason: collision with root package name */
    public int f11172J;

    /* access modifiers changed from: public */
    EnumC4488qw0(int i) {
        this.f11172J = i;
    }

    public static EnumC4488qw0 a(int i) {
        EnumC4488qw0 qw0 = CENTER;
        if (i == 0) {
            return LEFT;
        }
        if (i == 1 || i != 2) {
            return qw0;
        }
        return RIGHT;
    }
}
