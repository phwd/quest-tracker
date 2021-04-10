package X;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.net.URL;
import java.security.MessageDigest;

/* renamed from: X.1Rx  reason: invalid class name */
public final class AnonymousClass1Rx implements AbstractC06491aL {
    @Nullable
    public String A00;
    @Nullable
    public URL A01;
    public int A02;
    public final AnonymousClass1Kl A03;
    @Nullable
    public final String A04;
    @Nullable
    public final URL A05;
    @Nullable
    public volatile byte[] A06;

    private final String A00() {
        String str = this.A04;
        if (str != null) {
            return str;
        }
        URL url = this.A05;
        AnonymousClass1S2.A00(url);
        return url.toString();
    }

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        if (this.A06 == null) {
            this.A06 = A00().getBytes(AbstractC06491aL.A00);
        }
        messageDigest.update(this.A06);
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass1Rx)) {
            return false;
        }
        AnonymousClass1Rx r4 = (AnonymousClass1Rx) obj;
        if (!A00().equals(r4.A00()) || !this.A03.equals(r4.A03)) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        int i = this.A02;
        if (i != 0) {
            return i;
        }
        int hashCode = A00().hashCode();
        this.A02 = hashCode;
        int hashCode2 = (hashCode * 31) + this.A03.hashCode();
        this.A02 = hashCode2;
        return hashCode2;
    }

    public final String toString() {
        return A00();
    }

    public AnonymousClass1Rx(String str) {
        AnonymousClass1Kl r1 = AnonymousClass1Kl.A00;
        this.A05 = null;
        if (!TextUtils.isEmpty(str)) {
            this.A04 = str;
            AnonymousClass1S2.A00(r1);
            this.A03 = r1;
            return;
        }
        throw new IllegalArgumentException("Must not be null or empty");
    }

    public AnonymousClass1Rx(URL url) {
        AnonymousClass1Kl r1 = AnonymousClass1Kl.A00;
        AnonymousClass1S2.A00(url);
        this.A05 = url;
        this.A04 = null;
        AnonymousClass1S2.A00(r1);
        this.A03 = r1;
    }
}
