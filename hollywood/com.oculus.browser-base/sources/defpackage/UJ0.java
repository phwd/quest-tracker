package defpackage;

/* renamed from: UJ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class UJ0 implements AbstractC1003Qj0 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC1125Sj0 f9022a;
    public final String b;
    public final Object[] c;
    public final int d;

    public UJ0(AbstractC1125Sj0 sj0, String str, Object[] objArr) {
        this.f9022a = sj0;
        this.b = str;
        this.c = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.d = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 >= 55296) {
                i |= (charAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            } else {
                this.d = i | (charAt2 << i2);
                return;
            }
        }
    }
}
