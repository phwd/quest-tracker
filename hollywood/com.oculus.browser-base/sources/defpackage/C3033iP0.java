package defpackage;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Handler;
import android.os.Process;
import android.provider.MediaStore;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;

/* renamed from: iP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3033iP0 extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    public final C3203jP0 f10135a;
    public final /* synthetic */ C3203jP0 b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3033iP0(C3203jP0 jp0, Handler handler, C3203jP0 jp02) {
        super(null);
        this.b = jp0;
        this.f10135a = jp02;
    }

    public void onChange(boolean z) {
        onChange(z, null);
    }

    /* JADX INFO: finally extract failed */
    public void onChange(boolean z, Uri uri) {
        String str;
        String str2;
        String str3 = "Detected change to the media database " + uri;
        String uri2 = uri.toString();
        boolean z2 = false;
        if (!uri.toString().startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString())) {
            AbstractC1220Ua0.f("ScreenshotMonitor", "uri: %s is not valid. Returning without processing screenshot", uri);
            return;
        }
        Objects.requireNonNull(this.b);
        Cursor cursor = null;
        String[] strArr = {"datetaken", "_data", "height", "width", "_id"};
        Context applicationContext = ContextUtils.getApplicationContext();
        Object obj = K2.f8337a;
        if (applicationContext.checkPermission("android.permission.READ_EXTERNAL_STORAGE", Process.myPid(), Process.myUid()) != 0) {
            AbstractC3535lK0.a("Tab.Screenshot.WithoutStoragePermission");
        } else {
            try {
                cursor = ContextUtils.getApplicationContext().getContentResolver().query(uri, strArr, null, null, null);
            } catch (SecurityException e) {
                AbstractC1220Ua0.a("ScreenshotMonitor", "Cannot query media store.", e);
            }
            if (cursor != null) {
                try {
                    String str4 = "";
                    if (cursor.moveToNext()) {
                        str4 = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                        str2 = cursor.getString(cursor.getColumnIndexOrThrow("height"));
                        str = cursor.getString(cursor.getColumnIndexOrThrow("width"));
                    } else {
                        str2 = str4;
                        str = str2;
                    }
                    cursor.close();
                    if (str4.indexOf("Screenshot") != -1) {
                        Point point = YF.b(ContextUtils.getApplicationContext()).d;
                        int i = point.x;
                        int i2 = point.y;
                        int parseInt = Integer.parseInt(str2);
                        int parseInt2 = Integer.parseInt(str);
                        if (i == parseInt || i2 == parseInt2 || i == parseInt2 || i2 == parseInt) {
                            z2 = true;
                        }
                    }
                } catch (Throwable th) {
                    cursor.close();
                    throw th;
                }
            }
        }
        if (z2) {
            PostTask.b(Zo1.f9374a, new RunnableC2862hP0(this, uri2), 0);
        }
    }
}
