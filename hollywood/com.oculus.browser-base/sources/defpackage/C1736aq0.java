package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import org.chromium.chrome.browser.notifications.NotificationPlatformBridge;

/* renamed from: aq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1736aq0 implements WT {

    /* renamed from: a  reason: collision with root package name */
    public final C3444kq0 f9493a;

    public C1736aq0(C3444kq0 kq0) {
        this.f9493a = kq0;
    }

    @Override // defpackage.WT
    public Object apply(Object obj) {
        String str;
        C3444kq0 kq0 = this.f9493a;
        List list = (List) obj;
        String d = NotificationPlatformBridge.d(kq0.b.b);
        if (TextUtils.isEmpty(d) || (str = Uri.parse(d).getHost()) == null) {
            str = "";
        }
        if (!list.contains(str)) {
            return Boolean.FALSE;
        }
        Jr1.a().d.c(Collections.singletonList(kq0));
        return Boolean.TRUE;
    }
}
