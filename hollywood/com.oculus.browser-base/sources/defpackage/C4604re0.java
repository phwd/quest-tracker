package defpackage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;

/* renamed from: re0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4604re0 {

    /* renamed from: a  reason: collision with root package name */
    public final Bundle f11210a = new Bundle();

    public MediaMetadataCompat a() {
        return new MediaMetadataCompat(this.f11210a);
    }

    public C4604re0 b(String str, Bitmap bitmap) {
        C4931ta taVar = MediaMetadataCompat.F;
        if (!(taVar.e(str) >= 0) || ((Integer) taVar.getOrDefault(str, null)).intValue() == 2) {
            this.f11210a.putParcelable(str, bitmap);
            return this;
        }
        throw new IllegalArgumentException(AbstractC2531fV.g("The ", str, " key cannot be used to put a Bitmap"));
    }

    public C4604re0 c(String str, long j) {
        C4931ta taVar = MediaMetadataCompat.F;
        if (!(taVar.e(str) >= 0) || ((Integer) taVar.getOrDefault(str, null)).intValue() == 0) {
            this.f11210a.putLong(str, j);
            return this;
        }
        throw new IllegalArgumentException(AbstractC2531fV.g("The ", str, " key cannot be used to put a long"));
    }

    public C4604re0 d(String str, String str2) {
        C4931ta taVar = MediaMetadataCompat.F;
        if (!(taVar.e(str) >= 0) || ((Integer) taVar.getOrDefault(str, null)).intValue() == 1) {
            this.f11210a.putCharSequence(str, str2);
            return this;
        }
        throw new IllegalArgumentException(AbstractC2531fV.g("The ", str, " key cannot be used to put a String"));
    }
}
