package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import org.chromium.base.Callback;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.util.ChromeFileProvider;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Ur0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1263Ur0 extends AbstractC2032cb {
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;
    public final /* synthetic */ File k;
    public final /* synthetic */ WindowAndroid l;
    public final /* synthetic */ String m;
    public final /* synthetic */ Callback n;

    public C1263Ur0(String str, String str2, File file, WindowAndroid windowAndroid, String str3, Callback callback) {
        this.i = str;
        this.j = str2;
        this.k = file;
        this.l = windowAndroid;
        this.m = str3;
        this.n = callback;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        if (ContentUriUtils.e(this.i)) {
            return Uri.parse(this.i);
        }
        if (this.i.isEmpty()) {
            return Uri.parse(this.j);
        }
        try {
            File file = this.k;
            Object obj = ChromeFileProvider.f10796J;
            return WP.b(ContextUtils.getApplicationContext(), ChromeFileProvider.e(), file);
        } catch (IllegalArgumentException e) {
            AbstractC1220Ua0.a("OfflinePageUtils", "Couldn't generate URI for sharing page: " + e, new Object[0]);
            return Uri.parse(this.j);
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Uri uri = (Uri) obj;
        WindowAndroid windowAndroid = this.l;
        String str = this.m;
        String str2 = this.j;
        Uri uri2 = "content".equals(uri.getScheme()) ? uri : null;
        Callback callback = this.n;
        if (!TextUtils.isEmpty(str2)) {
            str2 = HG.a(str2);
        }
        callback.onResult(new C2189dU0(windowAndroid, str, null, str2, null, null, uri2, null, null, null));
    }
}
