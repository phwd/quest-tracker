package android.support.v4.media;

import android.media.Rating;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new TJ0();
    public final int F;
    public final float G;
    public Object H;

    public RatingCompat(int i, float f) {
        this.F = i;
        this.G = f;
    }

    public static RatingCompat b(Object obj) {
        RatingCompat ratingCompat;
        float f;
        RatingCompat ratingCompat2 = null;
        if (obj != null) {
            Rating rating = (Rating) obj;
            int ratingStyle = rating.getRatingStyle();
            if (!rating.isRated()) {
                switch (ratingStyle) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        ratingCompat2 = new RatingCompat(ratingStyle, -1.0f);
                        break;
                }
            } else {
                float f2 = 1.0f;
                switch (ratingStyle) {
                    case 1:
                        if (!rating.hasHeart()) {
                            f2 = 0.0f;
                        }
                        ratingCompat = new RatingCompat(1, f2);
                        ratingCompat2 = ratingCompat;
                        break;
                    case 2:
                        if (!rating.isThumbUp()) {
                            f2 = 0.0f;
                        }
                        ratingCompat = new RatingCompat(2, f2);
                        ratingCompat2 = ratingCompat;
                        break;
                    case 3:
                    case 4:
                    case 5:
                        float starRating = rating.getStarRating();
                        if (ratingStyle == 3) {
                            f = 3.0f;
                        } else if (ratingStyle == 4) {
                            f = 4.0f;
                        } else if (ratingStyle != 5) {
                            Log.e("Rating", "Invalid rating style (" + ratingStyle + ") for a star rating");
                            break;
                        } else {
                            f = 5.0f;
                        }
                        if (starRating >= 0.0f && starRating <= f) {
                            ratingCompat2 = new RatingCompat(ratingStyle, starRating);
                            break;
                        } else {
                            Log.e("Rating", "Trying to set out of range star-based rating");
                            break;
                        }
                        break;
                    case 6:
                        float percentRating = rating.getPercentRating();
                        if (percentRating >= 0.0f && percentRating <= 100.0f) {
                            ratingCompat2 = new RatingCompat(6, percentRating);
                            break;
                        } else {
                            Log.e("Rating", "Invalid percentage-based rating value");
                            break;
                        }
                    default:
                        return null;
                }
            }
            ratingCompat2.H = obj;
        }
        return ratingCompat2;
    }

    public int describeContents() {
        return this.F;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("Rating:style=");
        i.append(this.F);
        i.append(" rating=");
        float f = this.G;
        i.append(f < 0.0f ? "unrated" : String.valueOf(f));
        return i.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.F);
        parcel.writeFloat(this.G);
    }
}
