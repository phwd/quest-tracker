package defpackage;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashSet;
import java.util.Set;
import org.chromium.chrome.browser.browserservices.verification.OriginVerifier;

/* renamed from: iv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3116iv {

    /* renamed from: a  reason: collision with root package name */
    public final int f10172a;
    public C5216vC b;
    public final C4194pC c;
    public final IE0 d;
    public final KE0 e;
    public final Set f = new HashSet();
    public OriginVerifier g;
    public boolean h;
    public boolean i;
    public String j;

    public C3116iv(Context context, int i2, C5216vC vCVar, C4194pC pCVar, IE0 ie0, KE0 ke0) {
        this.f10172a = i2;
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(i2);
        String str = (packagesForUid.length != 1 || TextUtils.isEmpty(packagesForUid[0])) ? null : packagesForUid[0];
        this.j = str;
        this.b = vCVar;
        this.c = pCVar;
        this.d = ie0;
        this.e = ke0;
        if (ie0 != null) {
            ke0.d = str;
        }
    }
}
