package defpackage;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* renamed from: F30  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class F30 {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f7992a = Charset.forName("UTF-8");
    public static final byte[] b;

    static {
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        b = bArr;
        ByteBuffer.wrap(bArr);
        int length = bArr.length;
        int i = length + 0;
        if (length < 0) {
            throw L30.c();
        } else if (length + 0 <= Integer.MAX_VALUE) {
            int i2 = i + 0 + 0;
        } else {
            try {
                throw L30.e();
            } catch (L30 e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public static int a(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int b(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static Object c(Object obj, Object obj2) {
        AbstractC2360eV eVVar = (AbstractC2360eV) ((AbstractC1125Sj0) obj);
        C1848bV bVVar = (C1848bV) eVVar.e(EnumC2190dV.NEW_BUILDER, null, null);
        bVVar.c();
        AbstractC2360eV eVVar2 = bVVar.G;
        C2163dI0 di0 = C2163dI0.f9768a;
        di0.b(eVVar2).b(eVVar2, eVVar);
        AbstractC1125Sj0 sj0 = (AbstractC1125Sj0) obj2;
        if (bVVar.F.getClass().isInstance(sj0)) {
            bVVar.c();
            AbstractC2360eV eVVar3 = bVVar.G;
            di0.b(eVVar3).b(eVVar3, (AbstractC2360eV) ((AbstractC2790h) sj0));
            return bVVar.b();
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
