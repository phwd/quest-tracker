package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.signin.base.CoreAccountInfo;

/* renamed from: fV  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2531fV {
    public static float a(float f, float f2, float f3, float f4) {
        return ((f - f2) * f3) + f4;
    }

    public static int b(int i, int i2, int i3, int i4) {
        return ((i * i2) / i3) + i4;
    }

    public static String c(String str, Uri uri) {
        return str + uri;
    }

    public static String d(String str, AbstractComponentCallbacksC3550lS lSVar, String str2) {
        return str + lSVar + str2;
    }

    public static String e(String str, File file) {
        return str + file;
    }

    public static String f(String str, String str2) {
        return str + str2;
    }

    public static String g(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String h(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static StringBuilder i(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder j(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder k(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static Intent l(Context context, Class cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        return intent;
    }

    public static CoreAccountInfo m(C5949zZ zZVar, int i) {
        return (CoreAccountInfo) N.MwJ3GEOr(zZVar.c(Profile.b()).f10894a, i);
    }

    public static C4709sD n(int i, int i2, int i3, C4709sD sDVar, boolean z) {
        return sDVar.s((i * i2) + i3, z);
    }

    public static UH0 o(HashMap hashMap, OH0 oh0, LH0 lh0, Map map, FH0 fh0) {
        hashMap.put(oh0, lh0);
        return new UH0(map, fh0);
    }

    public static void p(String str, AbstractComponentCallbacksC3550lS lSVar) {
        String str2 = str + lSVar;
    }

    public static void q(Z5 z5, String str, Handler handler) {
        handler.post(new Q5(z5, str));
    }

    public static View r(ViewGroup viewGroup, int i, ViewGroup viewGroup2, boolean z) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup2, z);
    }

    public static String s(int i, String str, int i2) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(i2);
        return sb.toString();
    }

    public static String t(int i, String str, int i2, String str2, int i3) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(i2);
        sb.append(str2);
        sb.append(i3);
        return sb.toString();
    }

    public static String u(int i, String str, long j) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(j);
        return sb.toString();
    }

    public static String v(RecyclerView recyclerView, StringBuilder sb) {
        sb.append(recyclerView.B());
        return sb.toString();
    }

    public static String w(String str, int i) {
        return str + i;
    }

    public static String x(String str, Intent intent) {
        return str + intent;
    }
}
