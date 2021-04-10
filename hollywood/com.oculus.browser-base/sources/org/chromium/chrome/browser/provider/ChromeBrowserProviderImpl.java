package org.chromium.chrome.browser.provider;

import J.N;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.database.SQLiteCursor;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeBrowserProviderImpl extends VY0 {
    public static final String[] b = {"_id", "search", "date"};
    public static final String[] c = {"_id", "url", "visits", "date", "bookmark", "title", "favicon", "created"};
    public static final String[] d = {"_id", "title", "url", "date", "bookmark"};
    public final Object e = new Object();
    public final Object f = new Object();
    public UriMatcher g;
    public long h = -1;
    public long i;

    static {
        g("com.google.android.apps.chrome.browser-contract", "bookmarks");
        g("com.google.android.apps.chrome.browser-contract", "searches");
        g("com.google.android.apps.chrome.browser-contract", "history");
        g("com.google.android.apps.chrome.browser-contract", "combined");
    }

    public static Uri c(Context context, String str) {
        return g(context.getPackageName() + ".browser", str);
    }

    public static String d(long j, String str) {
        return h(j, f(str, true));
    }

    public static String e(String str) {
        return f(str, true);
    }

    public static String f(String str, boolean z) {
        StringBuilder i2 = AbstractC2531fV.i("bookmark");
        i2.append(z ? " = 1 " : " = 0");
        if (!TextUtils.isEmpty(str)) {
            i2.append(" AND (");
            i2.append(str);
            i2.append(")");
        }
        return i2.toString();
    }

    public static Uri g(String str, String str2) {
        return Uri.parse("content://" + str + "/" + str2);
    }

    public static String h(long j, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("_id");
        sb.append(" = ");
        sb.append(j);
        if (!TextUtils.isEmpty(str)) {
            sb.append(" AND (");
            sb.append(str);
            sb.append(")");
        }
        return sb.toString();
    }

    public static long l(Uri uri) {
        try {
            return ContentUris.parseId(uri);
        } catch (NumberFormatException | UnsupportedOperationException unused) {
            return -1;
        }
    }

    public final boolean i() {
        boolean z;
        if (!ThreadUtils.i()) {
            z = false;
        } else if ("REL".equals(Build.VERSION.CODENAME)) {
            Log.w("ChromeBrowserProvider", "ChromeBrowserProvider methods cannot be called from the UI thread.");
            z = true;
        } else {
            throw new IllegalStateException("Shouldn't run in the UI thread");
        }
        if (z) {
            return false;
        }
        j();
        if (this.i != 0) {
            return true;
        }
        synchronized (this.f) {
            PostTask.e(Zo1.f9374a, new RunnableC1382Wq(this));
        }
        return true;
    }

    public final void j() {
        synchronized (this.e) {
            if (this.g == null) {
                this.g = new UriMatcher(-1);
                String str = b().getPackageName() + ".ChromeBrowserProvider";
                this.g.addURI(str, "bookmarks", 0);
                this.g.addURI(str, "bookmarks/#", 1);
                String str2 = b().getPackageName() + ".browser";
                this.g.addURI(str2, "bookmarks", 2);
                this.g.addURI(str2, "bookmarks/#", 3);
                this.g.addURI(str2, "searches", 4);
                this.g.addURI(str2, "searches/#", 5);
                this.g.addURI(str2, "history", 6);
                this.g.addURI(str2, "history/#", 7);
                this.g.addURI(str2, "combined", 2);
                this.g.addURI(str2, "combined/#", 3);
                this.g.addURI("com.google.android.apps.chrome.browser-contract", "history", 6);
                this.g.addURI("com.google.android.apps.chrome.browser-contract", "history/#", 7);
                this.g.addURI("com.google.android.apps.chrome.browser-contract", "combined", 2);
                this.g.addURI("com.google.android.apps.chrome.browser-contract", "combined/#", 3);
                this.g.addURI("com.google.android.apps.chrome.browser-contract", "searches", 4);
                this.g.addURI("com.google.android.apps.chrome.browser-contract", "searches/#", 5);
                this.g.addURI("com.google.android.apps.chrome.browser-contract", "bookmarks", 8);
                this.g.addURI("com.google.android.apps.chrome.browser-contract", "bookmarks/#", 9);
                this.g.addURI("com.android.browser", "history", 6);
                this.g.addURI("com.android.browser", "history/#", 7);
                this.g.addURI("com.android.browser", "combined", 2);
                this.g.addURI("com.android.browser", "combined/#", 3);
                this.g.addURI("com.android.browser", "searches", 4);
                this.g.addURI("com.android.browser", "searches/#", 5);
                this.g.addURI("com.android.browser", "bookmarks", 8);
                this.g.addURI("com.android.browser", "bookmarks/#", 9);
                this.g.addURI("browser", "bookmarks", 2);
                this.g.addURI("browser", "bookmarks/#", 3);
                this.g.addURI("browser", "searches", 4);
                this.g.addURI("browser", "searches/#", 5);
                this.g.addURI(str2, "bookmarks/search_suggest_query", 10);
                this.g.addURI(str2, "search_suggest_query", 11);
            }
        }
    }

    public final Cursor k(String str, String[] strArr, String str2, boolean z) {
        boolean z2;
        ArrayList arrayList = new ArrayList();
        String h2 = AbstractC2531fV.h(new StringBuilder(), strArr[0], "%");
        if (strArr[0].startsWith("http") || strArr[0].startsWith("file")) {
            arrayList.add(h2);
            z2 = false;
        } else {
            arrayList.add("http://" + h2);
            arrayList.add("https://" + h2);
            arrayList.add("http://www." + h2);
            arrayList.add("https://www." + h2);
            arrayList.add("file://" + h2);
            z2 = true;
        }
        StringBuilder sb = new StringBuilder("(");
        int size = arrayList.size();
        StringBuilder sb2 = new StringBuilder(str);
        for (int i2 = 0; i2 < size - 1; i2++) {
            sb2.append(" OR ");
            sb2.append(str);
        }
        sb.append(sb2.toString());
        if (z2) {
            arrayList.add(h2);
            sb.append(" OR title LIKE ?");
        }
        sb.append(")");
        if (z) {
            sb.append(" AND bookmark=?");
            arrayList.add("1");
        }
        return new C1738ar(p(d, sb.toString(), (String[]) arrayList.toArray(strArr), str2));
    }

    public final boolean m(String str) {
        boolean z = true;
        if (YM.f9268a.a(b(), 3, "")) {
            r("SignaturePassed", str);
            return true;
        }
        Context b2 = b();
        if (b2.checkCallingOrSelfPermission(b().getApplicationContext().getPackageName() + ".permission." + "READ_WRITE_BOOKMARK_FOLDERS") != 0) {
            z = false;
        }
        if (z) {
            r("CallerHasPermission", str);
        }
        return z;
    }

    public final boolean n() {
        return m("WRITE_HISTORY_BOOKMARKS");
    }

    public final void o(Uri uri) {
        UserHandle callingUserHandle = Binder.getCallingUserHandle();
        if (callingUserHandle == null || callingUserHandle.equals(Process.myUserHandle())) {
            b().getContentResolver().notifyChange(uri, null);
        } else {
            PostTask.b(Zo1.f9374a, new RunnableC1443Xq(this, uri), 0);
        }
    }

    public final void onBookmarkChanged() {
        o(c(b(), "bookmarks"));
    }

    public final void onHistoryChanged() {
        o(c(b(), "history"));
    }

    public final void onSearchTermChanged() {
        o(c(b(), "searches"));
    }

    public final Cursor p(String[] strArr, String str, String[] strArr2, String str2) {
        if (strArr == null || strArr.length == 0) {
            strArr = c;
        }
        return (SQLiteCursor) N.MKZRZKXB(this.i, this, strArr, str, strArr2, str2);
    }

    public final Cursor q(String[] strArr, String str, String[] strArr2, String str2) {
        if (strArr == null || strArr.length == 0) {
            strArr = b;
        }
        return (SQLiteCursor) N.MPmRpyWu(this.i, this, strArr, str, strArr2, str2);
    }

    public final void r(String str, String str2) {
        String[] packagesForUid = b().getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid.length != 0) {
            int s = S20.s(packagesForUid[0]);
            AbstractC3364kK0.g("Android.ChromeBrowserProvider." + str + "." + str2, s, 16);
        }
    }

    public final int s(String str, String[] strArr) {
        return N.MOmmRjdm(this.i, this, str, strArr);
    }

    public final int t(ContentValues contentValues, String str, String[] strArr) {
        C1504Yq a2 = C1504Yq.a(contentValues);
        return N.MUDCe3et(this.i, this, a2.c, a2.b, a2.f9299a, a2.d, a2.e, a2.f, a2.g, a2.h, str, strArr);
    }

    public final void u(long j) {
        if (this.h == -1) {
            this.h = NU0.f8549a.h("last_bookmark_folder_id", -1);
        }
        if (this.h != j) {
            this.h = j;
            NU0.f8549a.o("last_bookmark_folder_id", j);
        }
    }

    public final int v(ContentValues contentValues, String str, String[] strArr) {
        C1565Zq a2 = C1565Zq.a(contentValues);
        return N.MgbxHn9u(this.i, this, a2.f9375a, a2.b, str, strArr);
    }
}
