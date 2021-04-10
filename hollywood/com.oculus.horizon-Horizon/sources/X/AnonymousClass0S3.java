package X;

import android.annotation.SuppressLint;
import java.util.LinkedHashSet;
import java.util.List;

/* renamed from: X.0S3  reason: invalid class name */
public final class AnonymousClass0S3 {
    public static final String[] A00 = {"facebook-hardware.com", "facebook.com", "facebookvirtualassistant.com", "facebookstudy.com", "fbcdn.net", "fbsbx.com", "freebasics.com", "internet.org", "instagram.com", "novi.com", "oculus.com", "viewpointsfromfacebook.com", "whatsapp.com"};

    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public static C08540wk A00(long j) {
        if (j < System.currentTimeMillis() - 31536000000L) {
            return C08540wk.A02;
        }
        C08560wm r11 = new C08560wm();
        String[] strArr = AnonymousClass0S0.A00;
        for (String str : strArr) {
            String[] strArr2 = A00;
            for (String str2 : strArr2) {
                String[] strArr3 = {AnonymousClass006.A05("sha256/", str)};
                if (str2 != null) {
                    String str3 = strArr3[0];
                    List<C08550wl> list = r11.A00;
                    list.add(new C08550wl(str2, str3));
                    String A05 = AnonymousClass006.A05("*.", str2);
                    String[] strArr4 = {AnonymousClass006.A05("sha256/", str)};
                    if (A05 != null) {
                        list.add(new C08550wl(A05, strArr4[0]));
                    }
                }
                throw new NullPointerException("pattern == null");
            }
        }
        return new C08540wk(new LinkedHashSet(r11.A00), null);
    }
}
