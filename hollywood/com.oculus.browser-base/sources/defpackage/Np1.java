package defpackage;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* renamed from: Np1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Np1 {

    /* renamed from: a  reason: collision with root package name */
    public Unsafe f8578a;

    public Np1(Unsafe unsafe) {
        this.f8578a = unsafe;
    }

    public final int a(Class cls) {
        return this.f8578a.arrayBaseOffset(cls);
    }

    public final int b(Class cls) {
        return this.f8578a.arrayIndexScale(cls);
    }

    public abstract boolean c(Object obj, long j);

    public abstract byte d(Object obj, long j);

    public abstract double e(Object obj, long j);

    public abstract float f(Object obj, long j);

    public final int g(Object obj, long j) {
        return this.f8578a.getInt(obj, j);
    }

    public final long h(Object obj, long j) {
        return this.f8578a.getLong(obj, j);
    }

    public final Object i(Object obj, long j) {
        return this.f8578a.getObject(obj, j);
    }

    public final long j(Field field) {
        return this.f8578a.objectFieldOffset(field);
    }

    public abstract void k(Object obj, long j, boolean z);

    public abstract void l(Object obj, long j, byte b);

    public abstract void m(Object obj, long j, double d);

    public abstract void n(Object obj, long j, float f);

    public final void o(Object obj, long j, int i) {
        this.f8578a.putInt(obj, j, i);
    }

    public final void p(Object obj, long j, long j2) {
        this.f8578a.putLong(obj, j, j2);
    }

    public final void q(Object obj, long j, Object obj2) {
        this.f8578a.putObject(obj, j, obj2);
    }
}
