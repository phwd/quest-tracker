package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.chromium.base.ContextUtils;

/* renamed from: NL0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NL0 implements AbstractC1424Xh0 {

    /* renamed from: a  reason: collision with root package name */
    public static String f8543a;
    public final String b;
    public final String c;

    public NL0(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    public static NL0 d(String str) {
        if (!str.startsWith("remote-playback://")) {
            return null;
        }
        try {
            return new NL0(str, new String(Base64.decode(str.substring(18), 8), "UTF-8"));
        } catch (UnsupportedEncodingException | IllegalArgumentException e) {
            AbstractC1220Ua0.a("MediaRemoting", "Couldn't parse the source id.", e);
            return null;
        }
    }

    @Override // defpackage.AbstractC1424Xh0
    public String a() {
        if (f8543a == null) {
            String str = null;
            try {
                Context applicationContext = ContextUtils.getApplicationContext();
                str = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128).metaData.getString("org.chromium.content.browser.REMOTE_PLAYBACK_APP_ID");
            } catch (Exception unused) {
            }
            if (str == null || str.isEmpty()) {
                str = "CC1AD845";
            }
            f8543a = str;
        }
        return f8543a;
    }

    @Override // defpackage.AbstractC1424Xh0
    public String b() {
        return this.b;
    }

    @Override // defpackage.AbstractC1424Xh0
    public C0629Kg0 c() {
        String a2 = AbstractC1717an.a(a());
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
    }
}
