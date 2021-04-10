package defpackage;

import J.N;
import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: nC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3852nC implements Runnable {
    public final String F;
    public final List G;
    public final String[] H;

    public RunnableC3852nC(String str, List list, String[] strArr) {
        this.F = str;
        this.G = list;
        this.H = strArr;
    }

    public void run() {
        String str = this.F;
        List<Bundle> list = this.G;
        String[] strArr = this.H;
        Set set = CustomTabsConnection.f10648a;
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            arrayList.add(str);
        }
        if (list != null) {
            for (Bundle bundle : list) {
                Uri uri = (Uri) U20.j(bundle, "android.support.customtabs.otherurls.URL");
                if (CustomTabsConnection.i(uri)) {
                    arrayList.add(uri.toString());
                }
            }
        }
        Profile b = Profile.b();
        Object obj = ThreadUtils.f10596a;
        N.MYX5Nv8s(b, strArr, (String[]) arrayList.toArray(new String[0]));
    }
}
