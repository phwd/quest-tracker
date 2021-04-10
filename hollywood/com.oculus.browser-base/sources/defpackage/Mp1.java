package defpackage;

import sun.misc.Unsafe;

/* renamed from: Mp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Mp1 extends Np1 {
    public Mp1(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // defpackage.Np1
    public boolean c(Object obj, long j) {
        return this.f8578a.getBoolean(obj, j);
    }

    @Override // defpackage.Np1
    public byte d(Object obj, long j) {
        return this.f8578a.getByte(obj, j);
    }

    @Override // defpackage.Np1
    public double e(Object obj, long j) {
        return this.f8578a.getDouble(obj, j);
    }

    @Override // defpackage.Np1
    public float f(Object obj, long j) {
        return this.f8578a.getFloat(obj, j);
    }

    @Override // defpackage.Np1
    public void k(Object obj, long j, boolean z) {
        this.f8578a.putBoolean(obj, j, z);
    }

    @Override // defpackage.Np1
    public void l(Object obj, long j, byte b) {
        this.f8578a.putByte(obj, j, b);
    }

    @Override // defpackage.Np1
    public void m(Object obj, long j, double d) {
        this.f8578a.putDouble(obj, j, d);
    }

    @Override // defpackage.Np1
    public void n(Object obj, long j, float f) {
        this.f8578a.putFloat(obj, j, f);
    }
}
