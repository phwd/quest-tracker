package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: dU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2189dU0 {

    /* renamed from: a  reason: collision with root package name */
    public final WindowAndroid f9785a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final ArrayList f;
    public final Uri g;
    public final Uri h;
    public AbstractC2018cU0 i;

    public C2189dU0(WindowAndroid windowAndroid, String str, String str2, String str3, String str4, ArrayList arrayList, Uri uri, Uri uri2, AbstractC2018cU0 cu0, AbstractC1847bU0 bu0) {
        this.f9785a = windowAndroid;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = arrayList;
        this.g = uri;
        this.h = uri2;
        this.i = cu0;
    }

    public String a() {
        if (TextUtils.isEmpty(this.d)) {
            return this.c;
        }
        if (TextUtils.isEmpty(this.c)) {
            return this.d;
        }
        return this.c + " " + this.d;
    }
}
