package X;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/* renamed from: X.Gg  reason: case insensitive filesystem */
public final class C0175Gg {
    public static final String[] A00 = {"facebook-hardware.com", "facebook.com", "facebookvirtualassistant.com", "facebookstudy.com", "fbcdn.net", "fbsbx.com", "freebasics.com", "internet.org", "instagram.com", "novi.com", "oculus.com", "viewpointsfromfacebook.com", "whatsapp.com"};

    public static C0530bT A00(long j) {
        if (j < System.currentTimeMillis() - 31536000000L) {
            return C0530bT.A02;
        }
        ArrayList arrayList = new ArrayList();
        String[] strArr = C0173Gd.A00;
        for (String str : strArr) {
            String[] strArr2 = A00;
            for (String str2 : strArr2) {
                String[] strArr3 = {AnonymousClass08.A04("sha256/", str)};
                if (str2 != null) {
                    arrayList.add(new C0529bS(str2, strArr3[0]));
                    String A04 = AnonymousClass08.A04("*.", str2);
                    String[] strArr4 = {AnonymousClass08.A04("sha256/", str)};
                    if (A04 != null) {
                        arrayList.add(new C0529bS(A04, strArr4[0]));
                    } else {
                        throw new NullPointerException("pattern == null");
                    }
                } else {
                    throw new NullPointerException("pattern == null");
                }
            }
        }
        return new C0530bT(new LinkedHashSet(arrayList), null);
    }
}
