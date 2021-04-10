package defpackage;

import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: jD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3173jD1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f10196a;
    public final C5221vD1 b;
    public C5221vD1 c;

    public C3173jD1(String str, AbstractC3686mD1 md1) {
        C5221vD1 vd1 = new C5221vD1(null);
        this.b = vd1;
        this.c = vd1;
        this.f10196a = str;
    }

    public final C3173jD1 a(String str, @NullableDecl Object obj) {
        C5221vD1 vd1 = new C5221vD1(null);
        this.c.c = vd1;
        this.c = vd1;
        vd1.b = obj;
        vd1.f11468a = str;
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.f10196a);
        sb.append('{');
        C5221vD1 vd1 = this.b.c;
        String str = "";
        while (vd1 != null) {
            Object obj = vd1.b;
            sb.append(str);
            String str2 = vd1.f11468a;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append((CharSequence) deepToString, 1, deepToString.length() - 1);
            }
            vd1 = vd1.c;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }
}
