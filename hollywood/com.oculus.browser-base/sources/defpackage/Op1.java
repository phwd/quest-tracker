package defpackage;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import sun.misc.Unsafe;

/* renamed from: Op1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Op1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Unsafe f8651a;
    public static final Class b = P4.f8663a;
    public static final boolean c;
    public static final boolean d;
    public static final Np1 e;
    public static final boolean f;
    public static final boolean g;
    public static final long h = ((long) b(byte[].class));
    public static final boolean i;

    /* JADX WARNING: Removed duplicated region for block: B:12:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x006f A[SYNTHETIC, Splitter:B:13:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0104 A[SYNTHETIC, Splitter:B:25:0x0104] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0233  */
    static {
        /*
        // Method dump skipped, instructions count: 630
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Op1.<clinit>():void");
    }

    public static Object a(Class cls) {
        try {
            return f8651a.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static int b(Class cls) {
        if (g) {
            return e.a(cls);
        }
        return -1;
    }

    public static int c(Class cls) {
        if (g) {
            return e.b(cls);
        }
        return -1;
    }

    public static Field d() {
        Field field;
        Field field2;
        if (P4.a()) {
            try {
                field2 = Buffer.class.getDeclaredField("effectiveDirectAddress");
            } catch (Throwable unused) {
                field2 = null;
            }
            if (field2 != null) {
                return field2;
            }
        }
        try {
            field = Buffer.class.getDeclaredField("address");
        } catch (Throwable unused2) {
            field = null;
        }
        if (field == null || field.getType() != Long.TYPE) {
            return null;
        }
        return field;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Class */
    /* JADX WARN: Multi-variable type inference failed */
    public static boolean e(Class cls) {
        if (!P4.a()) {
            return false;
        }
        try {
            Class cls2 = b;
            Class cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class cls4 = Integer.TYPE;
            cls2.getMethod("pokeInt", cls, cls4, cls3);
            cls2.getMethod("peekInt", cls, cls3);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, cls4, cls4);
            cls2.getMethod("peekByteArray", cls, byte[].class, cls4, cls4);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean f(Object obj, long j) {
        return e.c(obj, j);
    }

    public static byte g(byte[] bArr, long j) {
        return e.d(bArr, h + j);
    }

    public static byte h(Object obj, long j) {
        return (byte) ((l(obj, -4 & j) >>> ((int) (((~j) & 3) << 3))) & 255);
    }

    public static byte i(Object obj, long j) {
        return (byte) ((l(obj, -4 & j) >>> ((int) ((j & 3) << 3))) & 255);
    }

    public static double j(Object obj, long j) {
        return e.e(obj, j);
    }

    public static float k(Object obj, long j) {
        return e.f(obj, j);
    }

    public static int l(Object obj, long j) {
        return e.g(obj, j);
    }

    public static long m(Object obj, long j) {
        return e.h(obj, j);
    }

    public static Object n(Object obj, long j) {
        return e.i(obj, j);
    }

    public static Unsafe o() {
        try {
            return (Unsafe) AccessController.doPrivileged(new Jp1());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void p(byte[] bArr, long j, byte b2) {
        e.l(bArr, h + j, b2);
    }

    public static void q(Object obj, long j, byte b2) {
        long j2 = -4 & j;
        int l = l(obj, j2);
        int i2 = ((~((int) j)) & 3) << 3;
        Np1 np1 = e;
        np1.o(obj, j2, ((255 & b2) << i2) | (l & (~(255 << i2))));
    }

    public static void r(Object obj, long j, byte b2) {
        long j2 = -4 & j;
        int i2 = (((int) j) & 3) << 3;
        Np1 np1 = e;
        np1.o(obj, j2, ((255 & b2) << i2) | (l(obj, j2) & (~(255 << i2))));
    }

    public static void s(Object obj, long j, double d2) {
        e.m(obj, j, d2);
    }

    public static void t(Object obj, long j, long j2) {
        e.p(obj, j, j2);
    }
}
