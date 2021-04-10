package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: rI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4553rI extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final String f11196a;

    public C4553rI(String str) {
        this.f11196a = str;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        String str = this.f11196a;
        Iterator it = ((ArrayList) obj).iterator();
        while (it.hasNext()) {
            LF lf = (LF) it.next();
            if (str.contains(lf.b)) {
                AbstractC3364kK0.g("MobileDownload.Location.Download.DirectoryType", lf.e, 3);
                return;
            }
        }
    }
}
