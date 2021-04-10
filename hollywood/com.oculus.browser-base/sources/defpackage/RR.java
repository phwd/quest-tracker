package defpackage;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: RR  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class RR {

    /* renamed from: a  reason: collision with root package name */
    public static final C4595rb0 f8830a = new C4595rb0(16);
    public static final C5766yS0 b = new C5766yS0("fonts", 10, 10000);
    public static final Object c = new Object();
    public static final BW0 d = new BW0();
    public static final Comparator e = new NR();

    /* JADX WARN: Type inference failed for: r7v2, types: [PR[], android.database.Cursor] */
    public static OR a(Context context, CancellationSignal cancellationSignal, DR dr) {
        Cursor cursor;
        Uri uri;
        boolean z;
        PackageManager packageManager = context.getPackageManager();
        Resources resources = context.getResources();
        String str = dr.f7892a;
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(str, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException(AbstractC2531fV.f("No package found for authority: ", str));
        } else if (resolveContentProvider.packageName.equals(dr.b)) {
            Signature[] signatureArr = packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures;
            ArrayList arrayList = new ArrayList();
            for (Signature signature : signatureArr) {
                arrayList.add(signature.toByteArray());
            }
            Collections.sort(arrayList, e);
            List list = dr.d;
            if (list == null) {
                list = IR.b(resources, dr.e);
            }
            int i = 0;
            while (true) {
                cursor = 0;
                if (i >= list.size()) {
                    resolveContentProvider = cursor;
                    break;
                }
                ArrayList arrayList2 = new ArrayList((Collection) list.get(i));
                Collections.sort(arrayList2, e);
                if (arrayList.size() == arrayList2.size()) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            z = true;
                            break;
                        } else if (!Arrays.equals((byte[]) arrayList.get(i2), (byte[]) arrayList2.get(i2))) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                z = false;
                if (z) {
                    break;
                }
                i++;
            }
            if (resolveContentProvider == null) {
                return new OR(1, cursor);
            }
            String str2 = resolveContentProvider.authority;
            ArrayList arrayList3 = new ArrayList();
            Uri build = new Uri.Builder().scheme("content").authority(str2).build();
            Uri build2 = new Uri.Builder().scheme("content").authority(str2).appendPath("file").build();
            try {
                cursor = context.getContentResolver().query(build, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{dr.c}, null, cancellationSignal);
                if (cursor != null && cursor.getCount() > 0) {
                    int columnIndex = cursor.getColumnIndex("result_code");
                    arrayList3 = new ArrayList();
                    int columnIndex2 = cursor.getColumnIndex("_id");
                    int columnIndex3 = cursor.getColumnIndex("file_id");
                    int columnIndex4 = cursor.getColumnIndex("font_ttc_index");
                    int columnIndex5 = cursor.getColumnIndex("font_weight");
                    int columnIndex6 = cursor.getColumnIndex("font_italic");
                    while (cursor.moveToNext()) {
                        int i3 = columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
                        int i4 = columnIndex4 != -1 ? cursor.getInt(columnIndex4) : 0;
                        if (columnIndex3 == -1) {
                            uri = ContentUris.withAppendedId(build, cursor.getLong(columnIndex2));
                        } else {
                            uri = ContentUris.withAppendedId(build2, cursor.getLong(columnIndex3));
                        }
                        arrayList3.add(new PR(uri, i4, columnIndex5 != -1 ? cursor.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursor.getInt(columnIndex6) == 1, i3));
                    }
                }
                return new OR(0, (PR[]) arrayList3.toArray(new PR[0]));
            } finally {
                if (cursor != 0) {
                    cursor.close();
                }
            }
        } else {
            StringBuilder k = AbstractC2531fV.k("Found content provider ", str, ", but package was not ");
            k.append(dr.b);
            throw new PackageManager.NameNotFoundException(k.toString());
        }
    }

    public static QR b(Context context, DR dr, int i) {
        try {
            OR a2 = a(context, null, dr);
            int i2 = a2.f8623a;
            int i3 = -3;
            if (i2 == 0) {
                Typeface b2 = Lo1.f8441a.b(context, null, a2.b, i);
                if (b2 != null) {
                    i3 = 0;
                }
                return new QR(b2, i3);
            }
            if (i2 == 1) {
                i3 = -2;
            }
            return new QR(null, i3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new QR(null, -1);
        }
    }
}
