package org.chromium.services.media_session;

import android.graphics.Rect;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MediaImage {

    /* renamed from: a  reason: collision with root package name */
    public String f11008a;
    public String b;
    public List c = new ArrayList();

    public MediaImage(String str, String str2, List list) {
        this.f11008a = str;
        this.b = str2;
        this.c = list;
    }

    public static MediaImage create(String str, String str2, Rect[] rectArr) {
        return new MediaImage(str, str2, Arrays.asList(rectArr));
    }

    public static Rect createRect(int i, int i2) {
        return new Rect(0, 0, i, i2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaImage)) {
            return false;
        }
        MediaImage mediaImage = (MediaImage) obj;
        return TextUtils.equals(this.f11008a, mediaImage.f11008a) && TextUtils.equals(this.b, mediaImage.b) && this.c.equals(mediaImage.c);
    }

    public int hashCode() {
        int hashCode = this.b.hashCode();
        return this.c.hashCode() + ((hashCode + (this.f11008a.hashCode() * 31)) * 31);
    }
}
