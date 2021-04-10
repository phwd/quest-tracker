package defpackage;

import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.Arrays;
import java.util.Locale;
import org.chromium.base.BundleUtils;
import org.chromium.base.ContextUtils;

/* renamed from: B60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B60 {

    /* renamed from: a  reason: collision with root package name */
    public final String f7716a;
    public final String b;
    public final String c;
    public final boolean d;
    public boolean e;

    public B60(String str, String str2, String str3, boolean z) {
        this.f7716a = str;
        this.b = str2;
        this.c = str3;
        this.d = z;
        boolean z2 = true;
        if (TextUtils.equals(str, null)) {
            this.e = true;
            return;
        }
        Boolean bool = BundleUtils.f10583a;
        this.e = Arrays.binarySearch(RG0.f8823a, str, AbstractC0195De.f7900a) < 0 ? false : z2;
    }

    public static B60 a() {
        return new B60(null, ContextUtils.getApplicationContext().getResources().getString(R.string.f50750_resource_name_obfuscated_RES_2131952392), BV.f7741a.b.getDisplayName(Locale.getDefault()), true);
    }
}
