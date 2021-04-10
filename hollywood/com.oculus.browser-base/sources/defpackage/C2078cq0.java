package defpackage;

import J.N;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.notifications.NotificationPlatformBridge;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: cq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2078cq0 {

    /* renamed from: a  reason: collision with root package name */
    public final Profile f9723a;
    public final Context b;
    public final NotificationManager c;

    public C2078cq0(Profile profile) {
        this.f9723a = profile;
        Context applicationContext = ContextUtils.getApplicationContext();
        this.b = applicationContext;
        this.c = (NotificationManager) applicationContext.getSystemService("notification");
    }

    public static boolean b() {
        return Jr1.b() && N.M09VlOh_("NotificationSuspender");
    }

    public final Bitmap a(Icon icon) {
        if (icon == null || icon.getType() != 1) {
            return null;
        }
        return ((BitmapDrawable) icon.loadDrawable(this.b)).getBitmap();
    }

    public final void c(List list) {
        if (!list.isEmpty()) {
            String[] strArr = new String[list.size()];
            String[] strArr2 = new String[list.size()];
            Bitmap[] bitmapArr = new Bitmap[(list.size() * 3)];
            for (int i = 0; i < list.size(); i++) {
                Notification notification = ((C3444kq0) list.get(i)).f10306a;
                String str = ((C3444kq0) list.get(i)).b.b;
                strArr[i] = str;
                strArr2[i] = NotificationPlatformBridge.d(str);
                int i2 = i * 3;
                bitmapArr[i2 + 0] = a(notification.getLargeIcon());
                bitmapArr[i2 + 1] = a(notification.getSmallIcon());
                bitmapArr[i2 + 2] = (Bitmap) notification.extras.get("android.picture");
                this.c.cancel(str, -1);
            }
            N.Mj9WitTn(this.f9723a, strArr, strArr2, bitmapArr);
        }
    }
}
