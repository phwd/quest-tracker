package defpackage;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: bn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1897bn implements AbstractC1424Xh0 {

    /* renamed from: a  reason: collision with root package name */
    public static final List f9561a = Arrays.asList("custom_controller_scoped", "tab_and_origin_scoped", "origin_scoped", "page_scoped");
    public static final List b = Arrays.asList("video_out", "audio_out", "video_in", "audio_in", "multizone_group");
    public final String c;
    public final String d;
    public final String e;
    public final String f;

    public C1897bn(String str, String str2, String str3, String str4, String[] strArr) {
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4 == null ? "tab_and_origin_scoped" : str4;
    }

    public static String d(String[] strArr, String str) {
        String f2 = AbstractC2531fV.f(str, "=");
        for (String str2 : strArr) {
            if (str2.startsWith(f2)) {
                return str2.substring(f2.length());
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static defpackage.C1897bn e(java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 234
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1897bn.e(java.lang.String):bn");
    }

    @Override // defpackage.AbstractC1424Xh0
    public String a() {
        return this.d;
    }

    @Override // defpackage.AbstractC1424Xh0
    public String b() {
        return this.c;
    }

    @Override // defpackage.AbstractC1424Xh0
    public C0629Kg0 c() {
        try {
            String a2 = AbstractC1717an.a(this.d);
            if (a2 != null) {
                ArrayList<String> arrayList = new ArrayList<>();
                if (!arrayList.contains(a2)) {
                    arrayList.add(a2);
                }
                if (arrayList == null) {
                    return C0629Kg0.f8380a;
                }
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("controlCategories", arrayList);
                return new C0629Kg0(bundle, arrayList);
            }
            throw new IllegalArgumentException("category must not be null");
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
