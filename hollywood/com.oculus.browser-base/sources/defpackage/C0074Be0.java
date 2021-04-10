package defpackage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import java.util.Set;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;

/* renamed from: Be0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0074Be0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f7746a;
    public final MediaMetadata b;
    public final boolean c;
    public final String d;
    public final int e;
    public final boolean f;
    public final int g;
    public final Bitmap h;
    public final int i;
    public final Bitmap j;
    public final int k;
    public final Intent l;
    public final AbstractC0135Ce0 m;
    public final Set n;
    public final MediaPosition o;

    public C0074Be0(MediaMetadata mediaMetadata, boolean z, String str, int i2, boolean z2, int i3, Bitmap bitmap, int i4, Bitmap bitmap2, int i5, int i6, Intent intent, AbstractC0135Ce0 ce0, Set set, MediaPosition mediaPosition, AbstractC5964ze0 ze0) {
        this.b = mediaMetadata;
        this.c = z;
        this.d = str;
        this.e = i2;
        this.f = z2;
        this.g = i3;
        this.h = bitmap;
        this.i = i4;
        this.j = bitmap2;
        this.f7746a = i5;
        this.k = i6;
        this.l = intent;
        this.m = ce0;
        this.n = set;
        this.o = mediaPosition;
    }

    public boolean a() {
        return (this.f7746a & 1) != 0;
    }

    public boolean equals(Object obj) {
        Bitmap bitmap;
        Bitmap bitmap2;
        MediaMetadata mediaMetadata;
        MediaMetadata mediaMetadata2;
        Intent intent;
        Intent intent2;
        AbstractC0135Ce0 ce0;
        AbstractC0135Ce0 ce02;
        Set set;
        Set set2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0074Be0)) {
            return false;
        }
        C0074Be0 be0 = (C0074Be0) obj;
        return this.c == be0.c && this.f == be0.f && this.e == be0.e && this.g == be0.g && ((bitmap = this.h) == (bitmap2 = be0.h) || (bitmap != null && bitmap.sameAs(bitmap2))) && this.i == be0.i && this.j == be0.j && this.f7746a == be0.f7746a && this.k == be0.k && (((mediaMetadata = this.b) == (mediaMetadata2 = be0.b) || (mediaMetadata != null && mediaMetadata.equals(mediaMetadata2))) && TextUtils.equals(this.d, be0.d) && (((intent = this.l) == (intent2 = be0.l) || (intent != null && intent.equals(intent2))) && (((ce0 = this.m) == (ce02 = be0.m) || (ce0 != null && ce0.equals(ce02))) && (((set = this.n) == (set2 = be0.n) || (set != null && set.equals(set2))) && this.o == be0.o))));
    }

    public int hashCode() {
        int i2 = (((this.c ? 1 : 0) * 31) + (this.f ? 1 : 0)) * 31;
        MediaMetadata mediaMetadata = this.b;
        int i3 = 0;
        int hashCode = (i2 + (mediaMetadata == null ? 0 : mediaMetadata.hashCode())) * 31;
        String str = this.d;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Intent intent = this.l;
        int hashCode3 = (((((hashCode2 + (intent == null ? 0 : intent.hashCode())) * 31) + this.e) * 31) + this.g) * 31;
        Bitmap bitmap = this.h;
        int hashCode4 = (((hashCode3 + (bitmap == null ? 0 : bitmap.hashCode())) * 31) + this.i) * 31;
        Bitmap bitmap2 = this.j;
        if (bitmap2 != null) {
            i3 = bitmap2.hashCode();
        }
        return this.o.hashCode() + ((this.n.hashCode() + ((this.m.hashCode() + ((((((hashCode4 + i3) * 31) + this.f7746a) * 31) + this.k) * 31)) * 31)) * 31);
    }
}
