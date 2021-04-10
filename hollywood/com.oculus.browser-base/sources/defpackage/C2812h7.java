package defpackage;

import android.app.Application;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;

/* renamed from: h7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2812h7 {
    public static Bitmap a(ContentResolver contentResolver, Uri uri) {
        return ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri));
    }

    public static String b() {
        return Application.getProcessName();
    }
}
