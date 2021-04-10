package defpackage;

import java.nio.charset.Charset;

/* renamed from: ec0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2377ec0 implements VO0 {

    /* renamed from: a  reason: collision with root package name */
    public static final AbstractC1064Rj0 f9864a = new C2036cc0();
    public final AbstractC1064Rj0 b;

    public C2377ec0() {
        AbstractC1064Rj0 rj0;
        AbstractC1064Rj0[] rj0Arr = new AbstractC1064Rj0[2];
        rj0Arr[0] = C1668aV.f9432a;
        try {
            rj0 = (AbstractC1064Rj0) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            rj0 = f9864a;
        }
        rj0Arr[1] = rj0;
        C2207dc0 dc0 = new C2207dc0(rj0Arr);
        Charset charset = F30.f7992a;
        this.b = dc0;
    }

    public static boolean a(AbstractC1003Qj0 qj0) {
        return ((((UJ0) qj0).d & 1) == 1 ? (char) 1 : 2) == 1;
    }
}
