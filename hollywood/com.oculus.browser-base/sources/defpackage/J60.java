package defpackage;

import J.N;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: J60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class J60 implements R80 {
    public final L60 F;
    public final B60 G;
    public final int H;

    public J60(L60 l60, B60 b60, int i) {
        this.F = l60;
        this.G = b60;
        this.H = i;
    }

    @Override // defpackage.R80
    public void I(UH0 uh0) {
        L60 l60 = this.F;
        B60 b60 = this.G;
        int i = this.H;
        Objects.requireNonNull(l60);
        int f = uh0.f(Y80.f9255a);
        boolean z = false;
        if (f == R.string.f53910_resource_name_obfuscated_RES_2131952708) {
            if (uh0.f(Y80.d) == 0) {
                z = true;
            }
            N.Mt0H9F3d(b60.f7716a, !z);
            T60.d(z ? 7 : 6);
        } else if (f == R.string.f60190_resource_name_obfuscated_RES_2131953336) {
            T60 a2 = T60.a();
            String str = b60.f7716a;
            Objects.requireNonNull(a2);
            N.Me60Lv4_(str, false);
            a2.c();
            T60.d(3);
        } else if (f == R.string.f54660_resource_name_obfuscated_RES_2131952783) {
            T60.a().b(b60.f7716a, -1, true);
        } else if (f == R.string.f54640_resource_name_obfuscated_RES_2131952781) {
            T60.a().b(b60.f7716a, 1, true);
        } else if (f == R.string.f54650_resource_name_obfuscated_RES_2131952782) {
            T60.a().b(b60.f7716a, -i, true);
        }
        if (f != R.string.f60190_resource_name_obfuscated_RES_2131953336) {
            l60.F.b();
        }
    }
}
