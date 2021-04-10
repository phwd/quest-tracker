package defpackage;

import J.N;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: S20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class S20 {

    /* renamed from: a  reason: collision with root package name */
    public static ComponentName f8870a;
    public static final Object b = new Object();
    public static Pair c;
    public static int d;
    public static String e;
    public static boolean f;
    public static C2833hE g;
    public final R20 h;
    public final Activity i;

    public S20(Activity activity, R20 r20) {
        this.h = r20;
        this.i = activity;
    }

    public static boolean A(Intent intent) {
        PendingIntent pendingIntent;
        if (intent == null || (pendingIntent = (PendingIntent) U20.k(intent, "trusted_application_code_extra")) == null) {
            return false;
        }
        return e().equals(pendingIntent);
    }

    public static void a(Intent intent) {
        if (C2003cN.q(intent, true)) {
            intent.setPackage(ContextUtils.getApplicationContext().getPackageName());
            intent.putExtra("trusted_application_code_extra", e());
        }
    }

    public static Intent b(Context context, boolean z) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("chrome-native://newtab/"));
        intent.setClass(context, Lr.class);
        intent.putExtra("create_new_tab", true);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", z);
        a(intent);
        return intent;
    }

    public static int c(Intent intent) {
        if (A(intent)) {
            return 5;
        }
        String n = U20.n(intent, "com.android.browser.application_id");
        if (n != null) {
            return s(n);
        }
        String m = m(intent);
        String g2 = g(intent);
        if (m != null && m.startsWith("http://t.co/")) {
            return 4;
        }
        if (!"android-app://m.facebook.com".equals(g2)) {
            if (m != null && m.startsWith("http://news.google.com/news/url?")) {
                return 8;
            }
            if (m != null && (m.startsWith("https://www.youtube.com/redirect?") || m.startsWith("http://www.youtube.com/redirect?"))) {
                return 15;
            }
            Bundle e2 = U20.e(intent, "com.android.browser.headers");
            if (e2 == null || !"http://m.facebook.com".equals(e2.get("Referer"))) {
                return 0;
            }
        }
        return 2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ba A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00bb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String d(android.content.Intent r5) {
        /*
        // Method dump skipped, instructions count: 200
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.S20.d(android.content.Intent):java.lang.String");
    }

    public static PendingIntent e() {
        Intent intent = new Intent();
        Context applicationContext = ContextUtils.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        synchronized (b) {
            if (f8870a == null) {
                f8870a = new ComponentName(packageName, "FakeClass");
            }
        }
        intent.setComponent(f8870a);
        return PendingIntent.getActivity(applicationContext, 0, intent, 0);
    }

    public static String f(Intent intent) {
        C4649rt0 a2;
        Bundle e2 = U20.e(intent, "com.android.browser.headers");
        if (e2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean A = A(intent);
        CustomTabsConnection f2 = CustomTabsConnection.f();
        Objects.requireNonNull(f2);
        CustomTabsSessionToken b2 = CustomTabsSessionToken.b(intent);
        boolean e3 = (b2 == null || (a2 = C4649rt0.a(intent.getData())) == null) ? false : f2.f.e(b2, a2);
        for (String str : e2.keySet()) {
            String string = e2.getString(str);
            if (!N.MorcXgQd(str, string)) {
                AbstractC1220Ua0.f("IntentHandler", AbstractC2531fV.g("Ignoring forbidden header ", str, " in EXTRA_HEADERS."), new Object[0]);
            }
            Locale locale = Locale.US;
            if (!"x-chrome-intent-type".equals(str.toLowerCase(locale))) {
                if (!A) {
                    if (str.toLowerCase(locale).startsWith("x-chrome-")) {
                        AbstractC1220Ua0.f("IntentHandler", AbstractC2531fV.g("Ignoring x-chrome header ", str, " in EXTRA_HEADERS."), new Object[0]);
                    } else if (!e3 && !N.MUs5WTJu(str, string)) {
                        AbstractC1220Ua0.f("IntentHandler", AbstractC2531fV.g("Ignoring non-CORS-safelisted header ", str, " in EXTRA_HEADERS."), new Object[0]);
                    }
                }
                if (sb.length() != 0) {
                    sb.append("\n");
                }
                sb.append(str);
                sb.append(": ");
                sb.append(string);
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    public static String g(Intent intent) {
        C2512fL0 d2;
        Uri uri = (Uri) U20.k(intent, "android.intent.extra.REFERRER");
        if (uri != null) {
            int h2 = U20.h(intent, "org.chromium.chrome.browser.referrer_id", 0);
            Pair pair = c;
            String str = (pair == null || ((Integer) pair.first).intValue() != h2) ? null : (String) c.second;
            if (!TextUtils.isEmpty(str)) {
                uri = Uri.parse(str);
            }
        } else {
            String n = U20.n(intent, "android.intent.extra.REFERRER_NAME");
            uri = n != null ? Uri.parse(n) : null;
        }
        CustomTabsSessionToken b2 = CustomTabsSessionToken.b(intent);
        if (!(uri != null || b2 == null || (d2 = CustomTabsConnection.f().f.d(b2)) == null)) {
            uri = Uri.parse(d2.f9916a);
        }
        if (uri == null) {
            return null;
        }
        if (r(uri)) {
            return uri.toString();
        }
        if (t(intent)) {
            return uri.toString();
        }
        Objects.requireNonNull(AbstractApplicationC3785mq.g().d());
        return null;
    }

    public static String h(Intent intent) {
        String g2 = g(intent);
        if (g2 != null) {
            return g2;
        }
        Bundle e2 = U20.e(intent, "com.android.browser.headers");
        if (e2 == null) {
            return null;
        }
        for (String str : e2.keySet()) {
            String string = e2.getString(str);
            if (string != null && "referer".equals(str.toLowerCase(Locale.US))) {
                Uri normalizeScheme = Uri.parse(string).normalizeScheme();
                if (r(normalizeScheme)) {
                    return normalizeScheme.toString();
                }
            }
        }
        return null;
    }

    public static String i(String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf(":")) < 0) {
            return null;
        }
        boolean z = false;
        String trim = str.substring(0, indexOf).toLowerCase(Locale.US).trim();
        char[] charArray = trim.toCharArray();
        int length = charArray.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char c2 = charArray[i2];
            if (!Character.isLetterOrDigit(c2) && c2 != '-' && c2 != '+' && c2 != '.') {
                z = true;
                break;
            }
            i2++;
        }
        return z ? trim.replaceAll("[^a-z0-9.+-]", "") : trim;
    }

    public static Integer j(Intent intent) {
        Serializable serializable = null;
        try {
            serializable = intent.getSerializableExtra("org.chromium.chrome.browser.tab_launch_type");
        } catch (ClassCastException e2) {
            AbstractC1220Ua0.a("IntentUtils", AbstractC2531fV.f("Invalide class for Serializable: ", "org.chromium.chrome.browser.tab_launch_type"), e2);
        } catch (Throwable unused) {
            AbstractC1220Ua0.a("IntentUtils", AbstractC2531fV.x("getSerializableExtra failed on intent ", intent), new Object[0]);
        }
        return (Integer) serializable;
    }

    public static int k(Intent intent, int i2) {
        if (intent == null) {
            return i2;
        }
        int h2 = U20.h(intent, "com.google.chrome.transition_type", 0);
        if (h2 == 1) {
            return h2;
        }
        return (h2 == 0 || !t(intent)) ? i2 : h2;
    }

    public static String l(String str) {
        if (!str.toLowerCase(Locale.US).startsWith("googlechrome://navigate?url=")) {
            return null;
        }
        String substring = str.substring(28);
        if (!TextUtils.isEmpty(substring) && i(substring) == null) {
            substring = AbstractC2531fV.f("http://", substring);
        }
        if (AbstractC5154ur1.d(substring)) {
            return substring;
        }
        return null;
    }

    public static String m(Intent intent) {
        String d2 = d(intent);
        return p(d2) ? l(d2) : d2;
    }

    public static boolean n(String str, String str2, Intent intent) {
        if (str != null && (intent.hasCategory("android.intent.category.BROWSABLE") || intent.hasCategory("android.intent.category.DEFAULT") || intent.getCategories() == null)) {
            Locale locale = Locale.US;
            String lowerCase = str.toLowerCase(locale);
            if ("chrome".equals(lowerCase) || "chrome-native".equals(lowerCase) || "about".equals(lowerCase)) {
                String lowerCase2 = str2.toLowerCase(locale);
                if ("about:blank".equals(lowerCase2) || "about://blank".equals(lowerCase2) || "chrome://dino".equals(lowerCase2)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003a A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean o(android.content.Intent r5) {
        /*
            java.lang.String r5 = d(r5)
            boolean r0 = p(r5)
            r1 = 0
            if (r0 == 0) goto L_0x0012
            java.lang.String r5 = l(r5)
            if (r5 != 0) goto L_0x0012
            return r1
        L_0x0012:
            r0 = 1
            if (r5 == 0) goto L_0x003b
            java.lang.String r5 = i(r5)
            if (r5 == 0) goto L_0x0037
            java.util.Locale r2 = java.util.Locale.US
            java.lang.String r3 = r5.toLowerCase(r2)
            java.lang.String r4 = "javascript"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0035
            java.lang.String r5 = r5.toLowerCase(r2)
            java.lang.String r2 = "jar"
            boolean r5 = r5.equals(r2)
            if (r5 == 0) goto L_0x0037
        L_0x0035:
            r5 = r0
            goto L_0x0038
        L_0x0037:
            r5 = r1
        L_0x0038:
            if (r5 == 0) goto L_0x003b
            return r1
        L_0x003b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.S20.o(android.content.Intent):boolean");
    }

    public static boolean p(String str) {
        String scheme;
        if (str == null || (scheme = Uri.parse(str).getScheme()) == null || !scheme.equals("googlechrome")) {
            return false;
        }
        return true;
    }

    public static boolean q(String str) {
        return str.equals("multipart/related") || str.equals("message/rfc822");
    }

    public static boolean r(Uri uri) {
        if (uri == null) {
            return false;
        }
        Uri normalizeScheme = uri.normalizeScheme();
        if (!TextUtils.equals(normalizeScheme.getScheme(), "android-app") || TextUtils.isEmpty(normalizeScheme.getHost())) {
            return false;
        }
        return true;
    }

    public static int s(String str) {
        if (str.equals("com.google.android.apps.plus")) {
            return 3;
        }
        if (str.equals("com.google.android.gm")) {
            return 1;
        }
        if (str.equals("com.google.android.talk")) {
            return 6;
        }
        if (str.equals("com.google.android.apps.messaging")) {
            return 7;
        }
        if (str.equals("jp.naver.line.android")) {
            return 9;
        }
        if (str.equals("com.whatsapp")) {
            return 10;
        }
        if (str.equals("com.google.android.googlequicksearchbox")) {
            return 11;
        }
        if (str.equals(ContextUtils.getApplicationContext().getPackageName())) {
            return 5;
        }
        if (str.startsWith("org.chromium.webapk")) {
            return 12;
        }
        if (str.equals("com.yahoo.mobile.client.android.mail")) {
            return 13;
        }
        return str.equals("com.viber.voip") ? 14 : 0;
    }

    @Deprecated
    public static boolean t(Intent intent) {
        PendingIntent pendingIntent;
        if (intent == null || (pendingIntent = (PendingIntent) U20.k(intent, "trusted_application_code_extra")) == null) {
            return false;
        }
        if (e().equals(pendingIntent)) {
            return true;
        }
        YM.f9268a.b(pendingIntent.getCreatorPackage());
        return false;
    }

    public static void w(Map map, Intent intent) {
        if (map == null || map.isEmpty()) {
            intent.removeExtra("com.android.browser.headers");
            return;
        }
        Bundle bundle = new Bundle();
        for (Map.Entry entry : map.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        intent.putExtra("com.android.browser.headers", bundle);
    }

    public static void y(Intent intent, String str) {
        Context applicationContext = ContextUtils.getApplicationContext();
        Intent intent2 = new Intent(intent);
        if (str != null) {
            intent2.setComponent(new ComponentName(applicationContext.getPackageName(), str));
        }
        a(intent2);
        applicationContext.startActivity(intent2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0156, code lost:
        if (r1.equals("mht") == false) goto L_0x015a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:108:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x018f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean u(android.content.Intent r15) {
        /*
        // Method dump skipped, instructions count: 427
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.S20.u(android.content.Intent):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void v(java.lang.String r14, java.lang.String r15, java.lang.String r16, int r17, int r18, boolean r19, boolean r20, org.chromium.url.Origin r21, android.content.Intent r22) {
        /*
        // Method dump skipped, instructions count: 121
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.S20.v(java.lang.String, java.lang.String, java.lang.String, int, int, boolean, boolean, org.chromium.url.Origin, android.content.Intent):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a3 A[Catch:{ all -> 0x00bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00a5 A[Catch:{ all -> 0x00bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ba A[Catch:{ all -> 0x00bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00be A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean x(android.content.Intent r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.S20.x(android.content.Intent, boolean):boolean");
    }

    public final void z(Intent intent) {
        if (g == null && intent != null) {
            g = new C2833hE(this.i);
        }
        C2833hE hEVar = g;
        if (hEVar != null) {
            hEVar.a(intent);
        }
    }
}
