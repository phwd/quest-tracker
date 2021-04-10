package org.chromium.base;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class ContentUriUtils {

    /* renamed from: a  reason: collision with root package name */
    public static XP f10584a;
    public static final Object b = new Object();

    public static AssetFileDescriptor a(String str) {
        ContentResolver contentResolver = ContextUtils.getApplicationContext().getContentResolver();
        Uri parse = Uri.parse(str);
        try {
            if (f(parse)) {
                String[] streamTypes = contentResolver.getStreamTypes(parse, "*/*");
                if (streamTypes != null && streamTypes.length > 0) {
                    AssetFileDescriptor openTypedAssetFileDescriptor = contentResolver.openTypedAssetFileDescriptor(parse, streamTypes[0], null);
                    if (openTypedAssetFileDescriptor == null || openTypedAssetFileDescriptor.getStartOffset() == 0) {
                        return openTypedAssetFileDescriptor;
                    }
                    try {
                        openTypedAssetFileDescriptor.close();
                    } catch (IOException unused) {
                    }
                    throw new SecurityException("Cannot open files with non-zero offset type.");
                }
            } else {
                ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(parse, "r");
                if (openFileDescriptor != null) {
                    return new AssetFileDescriptor(openFileDescriptor, 0, -1);
                }
            }
        } catch (Exception e) {
            AbstractC1220Ua0.f("ContentUriUtils", "Cannot open content uri: %s", str, e);
        }
        return null;
    }

    public static Uri b(File file) {
        synchronized (b) {
            XP xp = f10584a;
            if (xp == null) {
                return null;
            }
            return xp.a(file);
        }
    }

    public static String c(Uri uri, Context context, String str) {
        String[] streamTypes;
        String extensionFromMimeType;
        if (uri == null) {
            return "";
        }
        ContentResolver contentResolver = context.getContentResolver();
        try {
            Cursor query = contentResolver.query(uri, null, null, null, null);
            if (query != null) {
                try {
                    if (query.getCount() >= 1) {
                        query.moveToFirst();
                        int columnIndex = query.getColumnIndex(str);
                        if (columnIndex == -1) {
                            query.close();
                            return "";
                        }
                        String string = query.getString(columnIndex);
                        if (d(query) && (streamTypes = contentResolver.getStreamTypes(uri, "*/*")) != null && streamTypes.length > 0 && (extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(streamTypes[0])) != null) {
                            string = string + "." + extensionFromMimeType;
                        }
                        query.close();
                        return string;
                    }
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (NullPointerException unused) {
        }
        return "";
        throw th;
    }

    public static boolean contentUriExists(String str) {
        AssetFileDescriptor a2 = a(str);
        boolean z = a2 != null;
        if (a2 != null) {
            try {
                a2.close();
            } catch (IOException unused) {
            }
        }
        return z;
    }

    public static boolean d(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("flags");
        return columnIndex > -1 && (cursor.getLong(columnIndex) & 512) != 0;
    }

    public static boolean delete(String str) {
        return ContextUtils.getApplicationContext().getContentResolver().delete(Uri.parse(str), null, null) > 0;
    }

    public static boolean e(String str) {
        Uri parse;
        if (str == null || (parse = Uri.parse(str)) == null || !"content".equals(parse.getScheme())) {
            return false;
        }
        return true;
    }

    public static boolean f(Uri uri) {
        if (uri == null || !DocumentsContract.isDocumentUri(ContextUtils.getApplicationContext(), uri)) {
            return false;
        }
        try {
            Cursor query = ContextUtils.getApplicationContext().getContentResolver().query(uri, null, null, null, null);
            if (query != null) {
                try {
                    if (query.getCount() >= 1) {
                        query.moveToFirst();
                        boolean d = d(query);
                        query.close();
                        return d;
                    }
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (NullPointerException unused) {
        }
        return false;
        throw th;
    }

    public static String getContentUriFromFilePath(String str) {
        try {
            Uri b2 = b(new File(str));
            if (b2 != null) {
                return b2.toString();
            }
            return null;
        } catch (IllegalArgumentException e) {
            AbstractC1220Ua0.a("ContentUriUtils", "Cannot retrieve content uri from file: %s", str, e);
            return null;
        }
    }

    public static String getMimeType(String str) {
        ContentResolver contentResolver = ContextUtils.getApplicationContext().getContentResolver();
        Uri parse = Uri.parse(str);
        if (!f(parse)) {
            return contentResolver.getType(parse);
        }
        String[] streamTypes = contentResolver.getStreamTypes(parse, "*/*");
        if (streamTypes == null || streamTypes.length <= 0) {
            return null;
        }
        return streamTypes[0];
    }

    public static String maybeGetDisplayName(String str) {
        try {
            String c = c(Uri.parse(str), ContextUtils.getApplicationContext(), "_display_name");
            if (TextUtils.isEmpty(c)) {
                return null;
            }
            return c;
        } catch (Exception e) {
            AbstractC1220Ua0.f("ContentUriUtils", "Cannot open content uri: %s", str, e);
            return null;
        }
    }

    public static int openContentUriForRead(String str) {
        AssetFileDescriptor a2 = a(str);
        if (a2 != null) {
            return a2.getParcelFileDescriptor().detachFd();
        }
        return -1;
    }
}
