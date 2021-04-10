package org.chromium.components.download;

import J.N;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadCollectionBridge {

    /* renamed from: a  reason: collision with root package name */
    public static final List f10838a = new ArrayList(Arrays.asList("tar.gz", "tar.z", "tar.bz2", "tar.bz", "user.js"));
    public static C2671gH b = new C2671gH();

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class DisplayNameInfo {

        /* renamed from: a  reason: collision with root package name */
        public final String f10839a;
        public final String b;

        public DisplayNameInfo(String str, String str2) {
            this.f10839a = str;
            this.b = str2;
        }

        public final String getDisplayName() {
            return this.b;
        }

        public final String getDownloadUri() {
            return this.f10839a;
        }
    }

    public static Uri a(String str, String str2, String str3, String str4) {
        Uri uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
        C1703ai0 ai0 = new C1703ai0(uri, str, b.c(str2, str3, str));
        Uri b2 = b.b(str3);
        Uri parse = TextUtils.isEmpty(str4) ? null : Uri.parse(str4);
        if (b2 == null) {
            ai0.b.remove("download_uri");
        } else {
            ai0.b.put("download_uri", b2.toString());
        }
        if (parse == null) {
            ai0.b.remove("referer_uri");
        } else {
            ai0.b.put("referer_uri", parse.toString());
        }
        ai0.b.put("date_expires", Long.valueOf(b()));
        try {
            return ContextUtils.getApplicationContext().getContentResolver().insert(uri, ai0.b);
        } catch (Exception unused) {
            return null;
        }
    }

    public static long b() {
        return ((((long) N.M7rZ5uJE()) * 86400000) + System.currentTimeMillis()) / 1000;
    }

    public static C1883bi0 c(String str) {
        return new C1883bi0(ContextUtils.getApplicationContext(), Uri.parse(str));
    }

    public static boolean copyFileToIntermediateUri(String str, String str2) {
        try {
            C1883bi0 c = c(str2);
            OutputStream openOutputStream = c.F.getContentResolver().openOutputStream(c.G);
            FileInputStream fileInputStream = new FileInputStream(str);
            FileUtils.copy(fileInputStream, openOutputStream);
            fileInputStream.close();
            openOutputStream.close();
            return true;
        } catch (Exception e) {
            AbstractC1220Ua0.a("DownloadCollection", "Unable to copy content to pending Uri.", e);
            return false;
        }
    }

    public static String createIntermediateUriForPublish(String str, String str2, String str3, String str4) {
        String substring;
        Uri a2 = a(str, str2, str3, str4);
        if (a2 != null) {
            return a2.toString();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HHmmss.SSS", Locale.getDefault());
        Iterator it = f10838a.iterator();
        while (true) {
            if (it.hasNext()) {
                String str5 = (String) it.next();
                if (str.endsWith(str5)) {
                    String substring2 = str.substring(0, str.length() - str5.length());
                    if (substring2.endsWith(".")) {
                        substring = substring2.substring(0, substring2.length() - 1);
                        break;
                    }
                }
            } else {
                int lastIndexOf = str.lastIndexOf(46);
                if (lastIndexOf == -1) {
                    substring = str;
                } else {
                    substring = str.substring(0, lastIndexOf);
                }
            }
        }
        String substring3 = str.substring(substring.length());
        Uri a3 = a(substring + " - " + simpleDateFormat.format(new Date()) + substring3, str2, str3, str4);
        if (a3 == null) {
            return null;
        }
        return a3.toString();
    }

    public static void deleteIntermediateUri(String str) {
        C1883bi0 c = c(str);
        c.F.getContentResolver().delete(c.G, null, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0050, code lost:
        if (r12 != null) goto L_0x0026;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0024, code lost:
        if (r12 != null) goto L_0x0026;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean fileNameExists(java.lang.String r12) {
        /*
            java.lang.String r0 = "_id"
            r1 = 0
            r2 = 1
            r3 = 0
            android.net.Uri r4 = android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            android.content.Context r5 = org.chromium.base.ContextUtils.getApplicationContext()     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            android.content.ContentResolver r6 = r5.getContentResolver()     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            android.net.Uri r7 = android.provider.MediaStore.setIncludePending(r4)     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            java.lang.String[] r8 = new java.lang.String[]{r0}     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            java.lang.String r9 = "_display_name LIKE ?1"
            java.lang.String[] r10 = new java.lang.String[r2]     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            r10[r3] = r12     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            r11 = 0
            android.database.Cursor r12 = r6.query(r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x0042, all -> 0x0040 }
            if (r12 != 0) goto L_0x002a
            if (r12 == 0) goto L_0x0053
        L_0x0026:
            r12.close()
            goto L_0x0053
        L_0x002a:
            boolean r5 = r12.moveToNext()     // Catch:{ Exception -> 0x003e }
            if (r5 == 0) goto L_0x0026
            int r0 = r12.getColumnIndex(r0)     // Catch:{ Exception -> 0x003e }
            int r0 = r12.getInt(r0)     // Catch:{ Exception -> 0x003e }
            long r5 = (long) r0     // Catch:{ Exception -> 0x003e }
            android.net.Uri r1 = android.content.ContentUris.withAppendedId(r4, r5)     // Catch:{ Exception -> 0x003e }
            goto L_0x0026
        L_0x003e:
            r0 = move-exception
            goto L_0x0045
        L_0x0040:
            r12 = move-exception
            goto L_0x005b
        L_0x0042:
            r12 = move-exception
            r0 = r12
            r12 = r1
        L_0x0045:
            java.lang.String r4 = "DownloadCollection"
            java.lang.String r5 = "Unable to check file name existence."
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x0058 }
            r6[r3] = r0     // Catch:{ all -> 0x0058 }
            defpackage.AbstractC1220Ua0.a(r4, r5, r6)     // Catch:{ all -> 0x0058 }
            if (r12 == 0) goto L_0x0053
            goto L_0x0026
        L_0x0053:
            if (r1 == 0) goto L_0x0056
            goto L_0x0057
        L_0x0056:
            r2 = r3
        L_0x0057:
            return r2
        L_0x0058:
            r0 = move-exception
            r1 = r12
            r12 = r0
        L_0x005b:
            if (r1 == 0) goto L_0x0060
            r1.close()
        L_0x0060:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.download.DownloadCollectionBridge.fileNameExists(java.lang.String):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        if (r8 != null) goto L_0x0050;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDisplayName(java.lang.String r8) {
        /*
            java.lang.String r0 = "_display_name"
            android.content.Context r1 = org.chromium.base.ContextUtils.getApplicationContext()
            android.content.ContentResolver r2 = r1.getContentResolver()
            r1 = 0
            android.net.Uri r3 = android.net.Uri.parse(r8)     // Catch:{ Exception -> 0x003f, all -> 0x003d }
            java.lang.String[] r4 = new java.lang.String[]{r0}     // Catch:{ Exception -> 0x003f, all -> 0x003d }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x003f, all -> 0x003d }
            if (r8 == 0) goto L_0x0037
            int r2 = r8.getCount()     // Catch:{ Exception -> 0x0035 }
            if (r2 != 0) goto L_0x0023
            goto L_0x0037
        L_0x0023:
            boolean r2 = r8.moveToNext()     // Catch:{ Exception -> 0x0035 }
            if (r2 == 0) goto L_0x0050
            int r0 = r8.getColumnIndex(r0)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r0 = r8.getString(r0)     // Catch:{ Exception -> 0x0035 }
            r8.close()
            return r0
        L_0x0035:
            r0 = move-exception
            goto L_0x0041
        L_0x0037:
            if (r8 == 0) goto L_0x003c
            r8.close()
        L_0x003c:
            return r1
        L_0x003d:
            r0 = move-exception
            goto L_0x0056
        L_0x003f:
            r0 = move-exception
            r8 = r1
        L_0x0041:
            java.lang.String r2 = "DownloadCollection"
            java.lang.String r3 = "Unable to get display name for download."
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0054 }
            r5 = 0
            r4[r5] = r0     // Catch:{ all -> 0x0054 }
            defpackage.AbstractC1220Ua0.a(r2, r3, r4)     // Catch:{ all -> 0x0054 }
            if (r8 == 0) goto L_0x0053
        L_0x0050:
            r8.close()
        L_0x0053:
            return r1
        L_0x0054:
            r0 = move-exception
            r1 = r8
        L_0x0056:
            if (r1 == 0) goto L_0x005b
            r1.close()
        L_0x005b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.download.DownloadCollectionBridge.getDisplayName(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.chromium.components.download.DownloadCollectionBridge.DisplayNameInfo[] getDisplayNamesForDownloads() {
        /*
        // Method dump skipped, instructions count: 135
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.download.DownloadCollectionBridge.getDisplayNamesForDownloads():org.chromium.components.download.DownloadCollectionBridge$DisplayNameInfo[]");
    }

    public static int openIntermediateUri(String str) {
        try {
            ParcelFileDescriptor openFileDescriptor = ContextUtils.getApplicationContext().getContentResolver().openFileDescriptor(Uri.parse(str), "rw");
            ContentValues contentValues = new ContentValues();
            contentValues.put("date_expires", Long.valueOf(b()));
            ContextUtils.getApplicationContext().getContentResolver().update(Uri.parse(str), contentValues, null, null);
            return openFileDescriptor.detachFd();
        } catch (Exception e) {
            AbstractC1220Ua0.a("DownloadCollection", "Cannot open intermediate Uri.", e);
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0085 A[SYNTHETIC, Splitter:B:30:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String publishDownload(java.lang.String r12) {
        /*
        // Method dump skipped, instructions count: 168
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.download.DownloadCollectionBridge.publishDownload(java.lang.String):java.lang.String");
    }

    public static boolean renameDownloadUri(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        Uri parse = Uri.parse(str);
        contentValues.put("_display_name", str2);
        return ContextUtils.getApplicationContext().getContentResolver().update(parse, contentValues, null, null) == 1;
    }

    public static boolean shouldPublishDownload(String str) {
        if (Build.VERSION.SDK_INT < 29 || str == null) {
            return false;
        }
        return !b.a(str);
    }
}
