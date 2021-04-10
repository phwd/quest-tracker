package org.chromium.services.media_session;

import android.text.TextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MediaMetadata {

    /* renamed from: a  reason: collision with root package name */
    public String f11009a;
    public String b;
    public String c;

    public MediaMetadata(String str, String str2, String str3) {
        this.f11009a = str;
        this.b = str2;
        this.c = str3;
    }

    public static MediaMetadata create(String str, String str2, String str3) {
        return new MediaMetadata(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaMetadata)) {
            return false;
        }
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        return TextUtils.equals(this.f11009a, mediaMetadata.f11009a) && TextUtils.equals(this.b, mediaMetadata.b) && TextUtils.equals(this.c, mediaMetadata.c);
    }

    public int hashCode() {
        int hashCode = this.b.hashCode();
        return this.c.hashCode() + ((hashCode + (this.f11009a.hashCode() * 31)) * 31);
    }
}
