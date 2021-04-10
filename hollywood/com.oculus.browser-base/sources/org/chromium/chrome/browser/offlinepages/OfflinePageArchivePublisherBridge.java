package org.chromium.chrome.browser.offlinepages;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OfflinePageArchivePublisherBridge {
    public static DownloadManager a() {
        return (DownloadManager) ContextUtils.getApplicationContext().getSystemService("download");
    }

    public static long addCompletedDownload(String str, String str2, String str3, long j, String str4, String str5) {
        try {
            DownloadManager a2 = a();
            if (a2 == null) {
                return 0;
            }
            return a2.addCompletedDownload(str, str2, false, "multipart/related", str3, j, false, Uri.parse(str4), Uri.parse(str5));
        } catch (Exception e) {
            AbstractC1220Ua0.d("OPArchivePublisher", "ADM threw while trying to add a download. " + e, new Object[0]);
            return 0;
        }
    }

    public static boolean b(ContentResolver contentResolver, Uri uri, ContentValues contentValues, String str) {
        try {
            if (contentResolver.update(uri, contentValues, null, null) == 1) {
                return true;
            }
            AbstractC1220Ua0.d("OPArchivePublisher", str, new Object[0]);
            return false;
        } catch (RuntimeException e) {
            AbstractC1220Ua0.a("OPArchivePublisher", str, e);
        }
    }

    public static boolean isAndroidDownloadManagerInstalled() {
        return a() != null;
    }

    public static String publishArchiveToDownloadsCollection(OfflinePageItem offlinePageItem) {
        Field field;
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        ContentValues contentValues = new ContentValues();
        contentValues.put("date_added", Long.valueOf(currentTimeMillis));
        contentValues.put("date_modified", Long.valueOf(currentTimeMillis));
        contentValues.put("is_pending", (Integer) 1);
        contentValues.put("download_uri", offlinePageItem.f10719a);
        try {
            Uri uri = (Uri) Class.forName("android.provider.MediaStore$Downloads").getDeclaredField("EXTERNAL_CONTENT_URI").get(null);
            try {
                field = MediaStore.MediaColumns.class.getDeclaredField("RELATIVE_PATH");
            } catch (NoSuchFieldException unused) {
                field = MediaStore.MediaColumns.class.getDeclaredField("PRIMARY_DIRECTORY");
            }
            contentValues.put((String) field.get(null), Environment.DIRECTORY_DOWNLOADS);
            contentValues.put("date_expires", Long.valueOf((System.currentTimeMillis() + 259200000) / 1000));
            ContentResolver contentResolver = ContextUtils.getApplicationContext().getContentResolver();
            Uri insert = contentResolver.insert(uri, contentValues);
            if (insert == null || !ContentUriUtils.e(insert.toString())) {
                AbstractC1220Ua0.d("OPArchivePublisher", "Failed to create intermediate URI.", new Object[0]);
                return "";
            }
            try {
                Method method = Class.forName("android.os.FileUtils").getMethod("copy", InputStream.class, OutputStream.class);
                OutputStream openOutputStream = contentResolver.openOutputStream(insert);
                FileInputStream fileInputStream = new FileInputStream(offlinePageItem.e);
                method.invoke(null, fileInputStream, openOutputStream);
                fileInputStream.close();
                openOutputStream.close();
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("is_pending", (Integer) 0);
                contentValues2.putNull("date_expires");
                contentValues2.put("_display_name", offlinePageItem.d);
                contentValues2.put("mime_type", "multipart/related");
                if (!b(contentResolver, insert, contentValues2, "Failed to finish publishing archive.")) {
                    return "";
                }
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put("mime_type", "multipart/related");
                if (!b(contentResolver, insert, contentValues3, "Failed to update mime type.")) {
                    return "";
                }
                return insert.toString();
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to copy archive to pending URI (externalDownloadUri: ");
                sb.append(uri);
                sb.append(", intermediateUri: ");
                sb.append(insert);
                sb.append(", page.getFilePath(): ");
                AbstractC1220Ua0.d("OPArchivePublisher", AbstractC2531fV.h(sb, offlinePageItem.e, ")"), e);
                return "";
            }
        } catch (Exception e2) {
            AbstractC1220Ua0.d("OPArchivePublisher", "Unable to set pending download fields.", e2);
            return "";
        }
    }

    public static int remove(long[] jArr) {
        DownloadManager a2 = a();
        if (a2 == null) {
            return 0;
        }
        try {
            return a2.remove(jArr);
        } catch (Exception e) {
            AbstractC1220Ua0.d("OPArchivePublisher", "ADM threw while trying to remove a download. " + e, new Object[0]);
            return 0;
        }
    }
}
