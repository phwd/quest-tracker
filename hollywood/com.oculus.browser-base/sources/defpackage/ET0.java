package defpackage;

import J.N;
import android.text.TextUtils;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: ET0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ET0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f7962a;
    public final /* synthetic */ WindowAndroid b;
    public final /* synthetic */ WebContents c;
    public final /* synthetic */ String d;
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;
    public final /* synthetic */ GT0 h;

    public ET0(GT0 gt0, String str, WindowAndroid windowAndroid, WebContents webContents, String str2, int i, boolean z, boolean z2) {
        this.h = gt0;
        this.f7962a = str;
        this.b = windowAndroid;
        this.c = webContents;
        this.d = str2;
        this.e = i;
        this.f = z;
        this.g = z2;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        int i;
        String str = (String) obj;
        String str2 = this.f7962a;
        if (!"https".equals(N.M$mITdbo(str2))) {
            i = 0;
        } else if (TextUtils.isEmpty(str)) {
            i = 2;
        } else {
            String M$mITdbo = N.M$mITdbo(str);
            if (!"https".equals(M$mITdbo)) {
                i = !"http".equals(M$mITdbo) ? 3 : 6;
            } else {
                i = TextUtils.equals(str2, str) ? 5 : 4;
            }
        }
        AbstractC3364kK0.g("Mobile.CanonicalURLResult", i, 7);
        this.h.e(this.b, this.c, this.d, this.f7962a, str, this.e, this.f, this.g);
    }
}
