package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: u81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5036u81 {

    /* renamed from: a  reason: collision with root package name */
    public static final List f11390a = new ArrayList(2);

    public static void a(Tab tab) {
        Object obj = ThreadUtils.f10596a;
        TabImpl tabImpl = (TabImpl) tab;
        if (tabImpl.b0 != 1) {
            tabImpl.b0 = 1;
            WebContents webContents = tabImpl.L;
            if (webContents != null) {
                webContents.G(1);
            }
        }
        Iterator it = f11390a.iterator();
        while (it.hasNext()) {
            TabImpl tabImpl2 = (TabImpl) it.next();
            if (tabImpl2.isHidden()) {
                if (tabImpl2.b0 != 0) {
                    tabImpl2.b0 = 0;
                    WebContents webContents2 = tabImpl2.L;
                    if (webContents2 != null) {
                        webContents2.G(0);
                    }
                }
                it.remove();
            }
        }
        List list = f11390a;
        if (!list.contains(tab)) {
            list.add(tab);
        }
    }
}
