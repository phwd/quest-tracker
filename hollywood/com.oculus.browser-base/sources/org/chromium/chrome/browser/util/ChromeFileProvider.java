package org.chromium.chrome.browser.util;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.util.Arrays;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeFileProvider extends WP {

    /* renamed from: J  reason: collision with root package name */
    public static Object f10796J = new Object();
    public static boolean K;
    public static Uri L;
    public static Uri M;

    public static boolean d(Uri uri) {
        Uri uri2;
        return (uri == null || (uri2 = L) == null || !uri2.equals(uri)) ? false : true;
    }

    public static String e() {
        return ContextUtils.getApplicationContext().getPackageName() + ".FileProvider";
    }

    public static Uri f(Uri uri) {
        if (uri == null || !uri.getPath().contains("BlockedFile_")) {
            return uri;
        }
        synchronized (f10796J) {
            while (!K && d(uri)) {
                try {
                    f10796J.wait();
                } catch (InterruptedException unused) {
                }
            }
            if (!d(uri)) {
                return null;
            }
            return M;
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        if (uri != null && uri.getPath().contains("BlockedFile_")) {
            synchronized (f10796J) {
                if (!d(uri)) {
                    return 0;
                }
                M = null;
                K = false;
                L = null;
            }
        }
        return this.I.a(uri).delete() ? 1 : 0;
    }

    @Override // defpackage.WP
    public String getType(Uri uri) {
        Uri f = f(uri);
        if (f != null) {
            return super.getType(f);
        }
        return null;
    }

    @Override // defpackage.WP, android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) {
        Uri f = f(uri);
        if (f != null) {
            return super.openFile(f, str);
        }
        return null;
    }

    @Override // defpackage.WP
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String[] strArr3;
        Uri f = f(uri);
        if (f == null) {
            return null;
        }
        Cursor query = super.query(f, strArr, str, strArr2, str2);
        MatrixCursor matrixCursor = (MatrixCursor) query;
        String[] columnNames = matrixCursor.getColumnNames();
        int length = columnNames.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                strArr3 = (String[]) Arrays.copyOf(columnNames, columnNames.length + 1);
                strArr3[columnNames.length] = "_data";
                break;
            } else if ("_data".equals(columnNames[i])) {
                strArr3 = columnNames;
                break;
            } else {
                i++;
            }
        }
        if (columnNames == strArr3) {
            return query;
        }
        MatrixCursor matrixCursor2 = new MatrixCursor(strArr3, matrixCursor.getCount());
        query.moveToPosition(-1);
        while (query.moveToNext()) {
            MatrixCursor.RowBuilder newRow = matrixCursor2.newRow();
            for (int i2 = 0; i2 < columnNames.length; i2++) {
                int type = matrixCursor.getType(i2);
                if (type == 1) {
                    newRow.add(Integer.valueOf(matrixCursor.getInt(i2)));
                } else if (type == 2) {
                    newRow.add(Float.valueOf(matrixCursor.getFloat(i2)));
                } else if (type == 3) {
                    newRow.add(matrixCursor.getString(i2));
                } else if (type != 4) {
                    newRow.add(null);
                } else {
                    newRow.add(matrixCursor.getBlob(i2));
                }
            }
        }
        query.close();
        return matrixCursor2;
    }
}
