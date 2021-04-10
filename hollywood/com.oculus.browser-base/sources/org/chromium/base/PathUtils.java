package org.chromium.base;

import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.system.Os;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class PathUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicBoolean f10591a = new AtomicBoolean();
    public static FutureTask b;
    public static String c;
    public static String d;

    public static void a(String str, int i) {
        try {
            Os.chmod(str, i);
        } catch (Exception unused) {
            AbstractC1220Ua0.a("PathUtils", AbstractC2531fV.g("Failed to set permissions for path \"", str, "\""), new Object[0]);
        }
    }

    public static String b(int i) {
        if (!b.isDone()) {
            P21 g0 = P21.g0();
            try {
                b.run();
                g0.close();
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        try {
            return ((String[]) b.get())[i];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw th;
    }

    public static void c(String str) {
        if (!f10591a.getAndSet(true)) {
            c = str;
            d = null;
            b = new FutureTask(new CallableC0607Jy0());
            ((ExecutorC1463Ya) AbstractC2032cb.f9616a).execute(b);
        }
    }

    public static String[] d(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            File file = (File) it.next();
            if (file != null && !TextUtils.isEmpty(file.getAbsolutePath())) {
                arrayList.add(file.getAbsolutePath());
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] getAllPrivateDownloadsDirectories() {
        List arrayList = new ArrayList();
        P21 g0 = P21.g0();
        try {
            File[] externalFilesDirs = ContextUtils.getApplicationContext().getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS);
            if (externalFilesDirs != null) {
                arrayList = Arrays.asList(externalFilesDirs);
            }
            g0.close();
            return d(arrayList);
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static String getCacheDirectory() {
        return b(2);
    }

    public static String getDataDirectory() {
        return b(0);
    }

    public static String getDownloadsDirectory() {
        P21 f0 = P21.f0();
        try {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            f0.close();
            return path;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static String[] getExternalDownloadVolumesNames() {
        ArrayList arrayList = new ArrayList();
        for (String str : MediaStore.getExternalVolumeNames(ContextUtils.getApplicationContext())) {
            if (!TextUtils.isEmpty(str) && !str.contains("external_primary")) {
                arrayList.add(new File(((StorageManager) ContextUtils.getApplicationContext().getSystemService(StorageManager.class)).getStorageVolume(MediaStore.Files.getContentUri(str)).getDirectory().getAbsolutePath(), Environment.DIRECTORY_DOWNLOADS));
            }
        }
        return d(arrayList);
    }

    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String getNativeLibraryDirectory() {
        ApplicationInfo applicationInfo = ContextUtils.getApplicationContext().getApplicationInfo();
        int i = applicationInfo.flags;
        if ((i & 128) != 0 || (i & 1) == 0) {
            return applicationInfo.nativeLibraryDir;
        }
        return "/system/lib/";
    }

    public static String getThumbnailCacheDirectory() {
        return b(1);
    }
}
