package defpackage;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import org.chromium.base.ContextUtils;

/* renamed from: iR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3037iR0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10138a;
    public final AppWidgetManager b;

    public C3037iR0(Context context) {
        Context applicationContext = ContextUtils.getApplicationContext();
        this.f10138a = applicationContext;
        this.b = AppWidgetManager.getInstance(applicationContext);
    }

    public int[] a() {
        AppWidgetManager appWidgetManager = this.b;
        if (appWidgetManager == null) {
            return new int[0];
        }
        return appWidgetManager.getAppWidgetIds(new ComponentName(this.f10138a, AbstractC3378kR0.class.getName()));
    }
}
