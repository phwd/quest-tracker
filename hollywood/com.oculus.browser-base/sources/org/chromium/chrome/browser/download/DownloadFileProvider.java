package org.chromium.chrome.browser.download;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadFileProvider extends WP {

    /* renamed from: J  reason: collision with root package name */
    public static final String[] f10657J = {"_display_name", "_size"};

    public static Uri d(String str, String str2) {
        Uri.Builder scheme = new Uri.Builder().scheme("content");
        return scheme.authority(ContextUtils.getApplicationContext().getPackageName() + ".DownloadFileProvider").path(str).appendQueryParameter("file", str2).build();
    }

    public static String e(Uri uri, C4209pH pHVar) {
        if (uri == null) {
            return null;
        }
        String path = uri.getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        if (path.charAt(0) == File.separatorChar && path.length() > 1) {
            path = path.substring(1);
        }
        String queryParameter = uri.getQueryParameter("file");
        StringBuilder i = AbstractC2531fV.i("..");
        String str = File.separator;
        i.append(str);
        if (queryParameter.contains(i.toString())) {
            return null;
        }
        if (path.equals("download")) {
            File a2 = pHVar.a();
            return a2 + str + queryParameter;
        }
        ArrayList arrayList = (ArrayList) pHVar.b();
        if (arrayList.isEmpty() || !path.equals("download_external")) {
            return null;
        }
        return ((File) arrayList.get(0)).getAbsolutePath() + str + queryParameter;
    }

    @Override // defpackage.WP
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        } else if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external deletes");
    }

    @Override // defpackage.WP
    public String getType(Uri uri) {
        if (uri == null) {
            return null;
        }
        Uri.parse(uri.getQueryParameter("file"));
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()));
    }

    @Override // defpackage.WP
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // defpackage.WP, android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) {
        int i;
        String e = e(uri, new C4209pH());
        if (e != null) {
            if ("r".equals(str)) {
                i = 268435456;
            } else if ("w".equals(str) || "wt".equals(str)) {
                i = 738197504;
            } else if ("wa".equals(str)) {
                i = 704643072;
            } else if ("rw".equals(str)) {
                i = 939524096;
            } else if ("rwt".equals(str)) {
                i = 1006632960;
            } else {
                throw new IllegalArgumentException(AbstractC2531fV.f("Invalid mode: ", str));
            }
            return ParcelFileDescriptor.open(new File(e), i);
        }
        throw new FileNotFoundException();
    }

    @Override // defpackage.WP
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i;
        if (strArr == null) {
            strArr = f10657J;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        String e = e(uri, new C4209pH());
        if (TextUtils.isEmpty(e)) {
            return new MatrixCursor(strArr3, 1);
        }
        File file = new File(e);
        if (!file.exists() || !file.isFile()) {
            return new MatrixCursor(strArr3, 1);
        }
        int i2 = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i2] = "_display_name";
                i = i2 + 1;
                objArr[i2] = file.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i2] = "_size";
                i = i2 + 1;
                objArr[i2] = Long.valueOf(file.length());
            }
            i2 = i;
        }
        String[] strArr4 = new String[i2];
        System.arraycopy(strArr3, 0, strArr4, 0, i2);
        Object[] objArr2 = new Object[i2];
        System.arraycopy(objArr, 0, objArr2, 0, i2);
        MatrixCursor matrixCursor = new MatrixCursor(strArr4, 1);
        matrixCursor.addRow(objArr2);
        return matrixCursor;
    }

    @Override // defpackage.WP
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }
}
