package defpackage;

import J.N;
import android.os.Handler;
import android.text.TextUtils;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: ht  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2940ht extends AbstractC2032cb {
    public C3110it i;
    public final AbstractC0124Ca1 j;

    public C2940ht(C3110it itVar, AbstractC0124Ca1 ca1) {
        this.i = itVar;
        this.j = ca1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a9  */
    @Override // defpackage.AbstractC2032cb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object c() {
        /*
        // Method dump skipped, instructions count: 204
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2940ht.c():java.lang.Object");
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            C3110it itVar = this.i;
            ContextUtils.getApplicationContext();
            AbstractC0124Ca1 ca1 = this.j;
            Objects.requireNonNull(itVar);
            itVar.G = new Handler();
            itVar.F = ca1;
            C2463f41 a2 = C2463f41.a();
            if (!TextUtils.isEmpty(itVar.a())) {
                N.M09VlOh_("HorizontalTabSwitcherAndroid");
                Objects.requireNonNull(a2);
            }
        }
    }
}
