package defpackage;

import java.util.Arrays;

/* renamed from: t7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4861t7 {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f11324a = false;
    public final int b;
    public final C2470f7 c;
    public final AbstractC1787b7 d;

    public C4861t7(C2470f7 f7Var, AbstractC1787b7 b7Var) {
        this.c = f7Var;
        this.d = b7Var;
        this.b = Arrays.hashCode(new Object[]{f7Var, b7Var});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C4861t7)) {
            return false;
        }
        C4861t7 t7Var = (C4861t7) obj;
        return !this.f11324a && !t7Var.f11324a && AbstractC0895Oq0.a(this.c, t7Var.c) && AbstractC0895Oq0.a(this.d, t7Var.d);
    }

    public final int hashCode() {
        return this.b;
    }
}
