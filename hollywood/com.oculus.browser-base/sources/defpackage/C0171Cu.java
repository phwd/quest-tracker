package defpackage;

import java.lang.reflect.Method;

/* renamed from: Cu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0171Cu {

    /* renamed from: a  reason: collision with root package name */
    public final int f7847a;
    public final Method b;

    public C0171Cu(int i, Method method) {
        this.f7847a = i;
        this.b = method;
        method.setAccessible(true);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0171Cu.class != obj.getClass()) {
            return false;
        }
        C0171Cu cu = (C0171Cu) obj;
        return this.f7847a == cu.f7847a && this.b.getName().equals(cu.b.getName());
    }

    public int hashCode() {
        return this.b.getName().hashCode() + (this.f7847a * 31);
    }
}
