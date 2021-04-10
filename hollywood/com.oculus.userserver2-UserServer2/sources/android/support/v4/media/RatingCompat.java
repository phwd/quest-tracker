package android.support.v4.media;

import X.AnonymousClass0d;
import X.AnonymousClass2O;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressLint({"BanParcelableUsage"})
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = new AnonymousClass0d();
    public final float A00;
    public final int A01;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public @interface StarStyle {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public @interface Style {
    }

    public final String toString() {
        String valueOf;
        StringBuilder sb = new StringBuilder("Rating:style=");
        sb.append(this.A01);
        sb.append(" rating=");
        float f = this.A00;
        if (f < 0.0f) {
            valueOf = "unrated";
        } else {
            valueOf = String.valueOf(f);
        }
        sb.append(valueOf);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.A01);
        parcel.writeFloat(this.A00);
    }

    public RatingCompat(int i, float f) {
        this.A01 = i;
        this.A00 = f;
    }

    public final int describeContents() {
        return this.A01;
    }
}
