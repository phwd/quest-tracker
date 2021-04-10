package defpackage;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: GP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GP extends AbstractC2032cb {
    public final WindowAndroid i;
    public FP j;
    public C0030Ak0 k;
    public boolean l;
    public boolean m;
    public ContentResolver n;

    public GP(WindowAndroid windowAndroid, FP fp, C0030Ak0 ak0, List list, ContentResolver contentResolver) {
        this.i = windowAndroid;
        this.j = fp;
        this.k = ak0;
        this.n = contentResolver;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str.startsWith("image/")) {
                this.l = true;
            } else if (str.startsWith("video/")) {
                this.m = true;
            }
            if (this.l && this.m) {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Object obj = ThreadUtils.f10596a;
        if (!h()) {
            ArrayList arrayList = new ArrayList();
            int i2 = Build.VERSION.SDK_INT;
            String str = i2 >= 29 ? "relative_path" : "_data";
            String[] strArr = {"_id", "date_added", "media_type", "mime_type", str};
            String str2 = str + " LIKE ? OR " + str + " LIKE ? OR " + str + " LIKE ? OR " + str + " LIKE ?";
            boolean z = this.l;
            String str3 = z ? "media_type=1" : "";
            if (this.m) {
                if (z) {
                    str3 = AbstractC2531fV.f(str3, " OR ");
                }
                str3 = AbstractC2531fV.f(str3, "media_type=3");
            }
            if (!str3.isEmpty()) {
                str2 = str2 + " AND (" + str3 + ")";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.DIRECTORY_DCIM);
            String h = AbstractC2531fV.h(sb, File.separator, "Camera");
            String str4 = Environment.DIRECTORY_PICTURES;
            String str5 = Environment.DIRECTORY_DOWNLOADS;
            String h2 = AbstractC2531fV.h(new StringBuilder(), Environment.DIRECTORY_DCIM, "/Restored");
            if (i2 < 29) {
                h = Environment.getExternalStoragePublicDirectory(h).toString();
                str4 = Environment.getExternalStoragePublicDirectory(str4).toString();
                str5 = Environment.getExternalStoragePublicDirectory(str5).toString();
                h2 = Environment.getExternalStoragePublicDirectory(h2).toString();
            }
            String[] strArr2 = {AbstractC2531fV.f(h, "%"), AbstractC2531fV.f(str4, "%"), AbstractC2531fV.f(str5, "%"), AbstractC2531fV.f(h2, "%")};
            Uri contentUri = MediaStore.Files.getContentUri("external");
            Cursor query = this.n.query(contentUri, strArr, str2, strArr2, "date_added DESC");
            if (query == null) {
                AbstractC1220Ua0.a("PhotoPicker", "Content Resolver query() returned null", new Object[0]);
            } else {
                StringBuilder i3 = AbstractC2531fV.i("Found ");
                i3.append(query.getCount());
                i3.append(" media files, when requesting columns: ");
                i3.append(Arrays.toString(strArr));
                i3.append(", with WHERE ");
                i3.append(str2);
                i3.append(", params: ");
                i3.append(Arrays.toString(strArr2));
                AbstractC1220Ua0.d("PhotoPicker", i3.toString(), new Object[0]);
                while (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndex("mime_type"));
                    if (this.k.a(null, string)) {
                        arrayList.add(new C5557xC0(ContentUris.withAppendedId(contentUri, (long) query.getInt(query.getColumnIndex("_id"))), query.getLong(query.getColumnIndex("date_added")), string.startsWith("video/") ? 3 : 0));
                    }
                }
                query.close();
                arrayList.add(0, new C5557xC0(null, 0, 2));
                if (!(this.i.r0(new Intent("android.media.action.IMAGE_CAPTURE")) && (this.i.hasPermission("android.permission.CAMERA") || this.i.canRequestPermission("android.permission.CAMERA")))) {
                    return arrayList;
                }
                arrayList.add(0, new C5557xC0(null, 0, 1));
                return arrayList;
            }
        }
        return null;
    }

    @Override // defpackage.AbstractC2032cb
    public void i() {
        ((EC0) this.j).e(null);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        List list = (List) obj;
        if (!h()) {
            ((EC0) this.j).e(list);
        }
    }
}
