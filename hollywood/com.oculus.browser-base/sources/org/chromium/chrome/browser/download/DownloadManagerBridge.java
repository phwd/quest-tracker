package org.chromium.chrome.browser.download;

import J.N;
import android.app.DownloadManager;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadManagerBridge {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f10661a = new Object();

    public static long a(String str, String str2, String str3, String str4, long j, String str5, String str6, String str7) {
        long b = b(str7);
        if (b != -1) {
            return b;
        }
        long a2 = OI.a(str, str2, str3, str4, j, str5, str6);
        synchronized (f10661a) {
            SharedPreferences.Editor edit = c().edit();
            edit.putLong(str7, a2);
            edit.apply();
        }
        return a2;
    }

    public static void addCompletedDownload(String str, String str2, String str3, String str4, long j, String str5, String str6, String str7, long j2) {
        YH yh = new YH(str4, str, str2, MimeUtils.remapGenericMimeType(str3, str5, str), j, str5, str6, str7, j2);
        try {
            Executor executor = AbstractC2032cb.f9616a;
            yh.f();
            ((ExecutorC1463Ya) executor).execute(yh.e);
        } catch (RejectedExecutionException unused) {
            AbstractC1220Ua0.a("DownloadDelegate", "Thread limit reached, reschedule notification update later.", new Object[0]);
            N.Mct0JWyi(j2, -1);
        }
    }

    public static long b(String str) {
        P21 f0 = P21.f0();
        try {
            long j = c().getLong(str, -1);
            f0.close();
            return j;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static SharedPreferences c() {
        return ContextUtils.getApplicationContext().getSharedPreferences("download_id_mappings", 0);
    }

    public static C1820bI d(long j) {
        C1820bI bIVar = new C1820bI(j);
        DownloadManager downloadManager = (DownloadManager) ContextUtils.getApplicationContext().getSystemService("download");
        try {
            Cursor query = downloadManager.query(new DownloadManager.Query().setFilterById(j));
            if (query == null) {
                bIVar.f9529a = 3;
                return bIVar;
            }
            bIVar.f9529a = 0;
            if (query.moveToNext()) {
                int i = query.getInt(query.getColumnIndex("status"));
                bIVar.f9529a = i != 8 ? i != 16 ? 0 : 2 : 1;
                bIVar.b = query.getString(query.getColumnIndex("title"));
                bIVar.g = query.getInt(query.getColumnIndex("reason"));
                query.getLong(query.getColumnIndex("last_modified_timestamp"));
                bIVar.e = query.getLong(query.getColumnIndex("bytes_so_far"));
                bIVar.f = query.getLong(query.getColumnIndex("total_size"));
                String string = query.getString(query.getColumnIndex("local_uri"));
                if (!TextUtils.isEmpty(string)) {
                    bIVar.h = Uri.parse(string).getPath();
                }
            } else {
                bIVar.f9529a = 3;
            }
            query.close();
            try {
                bIVar.d = downloadManager.getUriForDownloadedFile(j);
            } catch (SecurityException unused) {
                AbstractC1220Ua0.a("DownloadDelegate", "unable to get content URI from DownloadManager", new Object[0]);
            }
            bIVar.c = downloadManager.getMimeTypeForDownloadedFile(j);
            return bIVar;
        } catch (Exception e) {
            bIVar.f9529a = 3;
            AbstractC1220Ua0.a("DownloadDelegate", "unable to query android DownloadManager", e);
        }
    }

    public static void e(long j, Callback callback) {
        C1991cI cIVar = new C1991cI(j, callback);
        Executor executor = AbstractC2032cb.f9616a;
        cIVar.f();
        ((ExecutorC1463Ya) executor).execute(cIVar.e);
    }

    public static void removeCompletedDownload(String str, boolean z) {
        PostTask.b(C3070if1.b, new XH(str, z), 0);
    }
}
