package com.google.android.gms.fido.fido2.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BrowserPublicKeyCredentialRequestOptions extends BrowserRequestOptions {
    public static final Parcelable.Creator CREATOR = new C2673gH1();
    public final PublicKeyCredentialRequestOptions F;
    public final Uri G;

    public BrowserPublicKeyCredentialRequestOptions(PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions, Uri uri) {
        Objects.requireNonNull(publicKeyCredentialRequestOptions, "null reference");
        this.F = publicKeyCredentialRequestOptions;
        x(uri);
        this.G = uri;
    }

    public static Uri x(Uri uri) {
        Objects.requireNonNull(uri, "null reference");
        boolean z = true;
        SE0.b(uri.getScheme() != null, "origin scheme must be non-empty");
        if (uri.getAuthority() == null) {
            z = false;
        }
        SE0.b(z, "origin authority must be non-empty");
        return uri;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BrowserPublicKeyCredentialRequestOptions)) {
            return false;
        }
        BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions = (BrowserPublicKeyCredentialRequestOptions) obj;
        if (!AbstractC0895Oq0.a(this.F, browserPublicKeyCredentialRequestOptions.F) || !AbstractC0895Oq0.a(this.G, browserPublicKeyCredentialRequestOptions.G)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.f(parcel, 2, this.F, i, false);
        AbstractC5758yO0.f(parcel, 3, this.G, i, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
