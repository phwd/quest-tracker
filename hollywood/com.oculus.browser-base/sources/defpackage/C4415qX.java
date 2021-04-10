package defpackage;

import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.Arrays;
import org.chromium.base.ContextUtils;

/* renamed from: qX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4415qX {

    /* renamed from: a  reason: collision with root package name */
    public final String f11146a;

    public C4415qX(String str, String str2, String str3, long j, long[] jArr, boolean z) {
        this.f11146a = str;
        if (z) {
            ContextUtils.getApplicationContext().getString(R.string.f46790_resource_name_obfuscated_RES_2131951996);
        } else {
            TextUtils.isEmpty(str3);
        }
        Arrays.copyOf(jArr, jArr.length);
    }
}
