package defpackage;

import android.app.PendingIntent;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.locale.LocaleManager;

/* renamed from: kR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3378kR0 extends AppWidgetProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f10278a = new Object();
    public static final Object b = new Object();
    public static String c;
    public static C3207jR0 d;
    public static C3037iR0 e;

    public static C3037iR0 a() {
        synchronized (f10278a) {
            if (e == null) {
                e = new C3037iR0(null);
            }
        }
        return e;
    }

    public static void b(int[] iArr) {
        String str;
        C3037iR0 a2 = a();
        int[] a3 = a2.a();
        if (a3.length != 0) {
            Objects.requireNonNull(a2);
            PU0 pu0 = NU0.f8549a;
            boolean d2 = pu0.d("org.chromium.chrome.browser.searchwidget.IS_VOICE_SEARCH_AVAILABLE", true);
            String i = pu0.i("org.chromium.chrome.browser.searchwidget.SEARCH_ENGINE_SHORTNAME", null);
            for (int i2 : a3) {
                Context context = a2.f10138a;
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), (int) R.layout.f41300_resource_name_obfuscated_RES_2131624439);
                Intent intent = new Intent("org.chromium.chrome.browser.searchwidget.START_TEXT_QUERY", Uri.parse(String.valueOf(i2)));
                intent.setClass(context, AbstractC3378kR0.class);
                S20.a(intent);
                remoteViews.setOnClickPendingIntent(R.id.text_container, PendingIntent.getBroadcast(context, 0, intent, 134217728));
                if (d2) {
                    Intent intent2 = new Intent("org.chromium.chrome.browser.searchwidget.START_VOICE_QUERY", Uri.parse(String.valueOf(i2)));
                    intent2.setClass(context, AbstractC3378kR0.class);
                    S20.a(intent2);
                    remoteViews.setOnClickPendingIntent(R.id.microphone_icon, PendingIntent.getBroadcast(context, 0, intent2, 134217728));
                    remoteViews.setViewVisibility(R.id.microphone_icon, 0);
                } else {
                    remoteViews.setViewVisibility(R.id.microphone_icon, 8);
                }
                if (TextUtils.isEmpty(i) || !c()) {
                    str = context.getString(R.string.f61110_resource_name_obfuscated_RES_2131953428);
                } else {
                    str = context.getString(R.string.f61130_resource_name_obfuscated_RES_2131953430, i);
                }
                remoteViews.setCharSequence(R.id.title, "setHint", str);
                a2.b.updateAppWidget(i2, remoteViews);
            }
        }
    }

    public static boolean c() {
        return (SQ.b(false, false) ^ true) && (LocaleManager.getInstance().b() ^ true);
    }
}
