package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LaunchOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C3344kD1();
    public boolean F;
    public String G;

    public LaunchOptions(boolean z, String str) {
        this.F = z;
        this.G = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LaunchOptions)) {
            return false;
        }
        LaunchOptions launchOptions = (LaunchOptions) obj;
        return this.F == launchOptions.F && GF1.a(this.G, launchOptions.G);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.F), this.G});
    }

    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", Boolean.valueOf(this.F), this.G);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        boolean z = this.F;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(z ? 1 : 0);
        AbstractC5758yO0.g(parcel, 3, this.G, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public LaunchOptions() {
        Locale locale = Locale.getDefault();
        Pattern pattern = GF1.f8078a;
        StringBuilder sb = new StringBuilder(20);
        sb.append(locale.getLanguage());
        String country = locale.getCountry();
        if (!TextUtils.isEmpty(country)) {
            sb.append('-');
            sb.append(country);
        }
        String variant = locale.getVariant();
        if (!TextUtils.isEmpty(variant)) {
            sb.append('-');
            sb.append(variant);
        }
        String sb2 = sb.toString();
        this.F = false;
        this.G = sb2;
    }
}
