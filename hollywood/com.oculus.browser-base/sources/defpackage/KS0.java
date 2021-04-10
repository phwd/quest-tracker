package defpackage;

import android.content.Intent;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.ServiceTabLauncher;
import org.chromium.content_public.common.ResourceRequestBody;

/* renamed from: KS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class KS0 implements Hw1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f8366a;
    public final String b;
    public final int c;
    public final boolean d;
    public final String e;
    public final int f;
    public final String g;
    public final ResourceRequestBody h;
    public final List i;

    public KS0(String str, String str2, int i2, boolean z, String str3, int i3, String str4, ResourceRequestBody resourceRequestBody, List list) {
        this.f8366a = str;
        this.b = str2;
        this.c = i2;
        this.d = z;
        this.e = str3;
        this.f = i3;
        this.g = str4;
        this.h = resourceRequestBody;
        this.i = list;
    }

    @Override // defpackage.Hw1
    public void a(boolean z, String str) {
        String str2 = this.f8366a;
        String str3 = this.b;
        int i2 = this.c;
        boolean z2 = this.d;
        String str4 = this.e;
        int i3 = this.f;
        String str5 = this.g;
        ResourceRequestBody resourceRequestBody = this.h;
        List list = this.i;
        if (z) {
            Intent a2 = Mw1.a(str2, str3, true);
            a2.putExtra("org.chromium.chrome.browser.webapp_source", 5);
            ContextUtils.getApplicationContext().startActivity(a2);
            return;
        }
        ServiceTabLauncher.a(i2, z2, str3, str4, i3, str5, resourceRequestBody, list);
    }
}
