package defpackage;

import J.N;
import android.net.Uri;
import android.service.notification.StatusBarNotification;
import android.webkit.URLUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.notifications.NotificationPlatformBridge;

/* renamed from: o41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4001o41 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4172p41 f10531a;
    public final boolean b;
    public final List c;
    public final List d;
    public final C5232vH0 e;

    public C4001o41(C4172p41 p41, boolean z, List list, List list2, C5232vH0 vh0) {
        this.f10531a = p41;
        this.b = z;
        this.c = list;
        this.d = list2;
        this.e = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4172p41 p41 = this.f10531a;
        boolean z = this.b;
        List list = this.c;
        List list2 = this.d;
        C5232vH0 vh0 = this.e;
        Objects.requireNonNull(p41);
        if (((Boolean) obj).booleanValue()) {
            if (z) {
                list.addAll(list2);
            } else {
                list.removeAll(list2);
            }
            C2078cq0 cq0 = p41.b;
            Objects.requireNonNull(cq0);
            if (!list2.isEmpty() && C2078cq0.b()) {
                int i = 0;
                if (z) {
                    ArrayList arrayList = new ArrayList();
                    StatusBarNotification[] activeNotifications = cq0.c.getActiveNotifications();
                    int length = activeNotifications.length;
                    while (i < length) {
                        StatusBarNotification statusBarNotification = activeNotifications[i];
                        if (statusBarNotification.getId() == -1) {
                            String tag = statusBarNotification.getTag();
                            String d2 = NotificationPlatformBridge.d(tag);
                            if ((URLUtil.isHttpUrl(d2) || URLUtil.isHttpsUrl(d2)) && list2.contains(Uri.parse(d2).getHost())) {
                                arrayList.add(new C3444kq0(statusBarNotification.getNotification(), new C0832Np0(7, tag, -1)));
                            }
                        }
                        i++;
                    }
                    cq0.c(arrayList);
                } else if (!list2.isEmpty()) {
                    String[] strArr = new String[(list2.size() * 2)];
                    while (i < list2.size()) {
                        int i2 = i * 2;
                        StringBuilder i3 = AbstractC2531fV.i("http://");
                        i3.append((String) list2.get(i));
                        strArr[i2 + 0] = i3.toString();
                        StringBuilder i4 = AbstractC2531fV.i("https://");
                        i4.append((String) list2.get(i));
                        strArr[i2 + 1] = i4.toString();
                        i++;
                    }
                    N.MkLq9R0C(cq0.f9723a, strArr);
                }
            }
            vh0.b(null);
            return;
        }
        vh0.e(null);
    }
}
