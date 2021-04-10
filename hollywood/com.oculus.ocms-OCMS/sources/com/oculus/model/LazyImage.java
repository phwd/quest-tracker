package com.oculus.model;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.common.util.UriUtil;

public class LazyImage implements Parcelable {
    public static final Parcelable.Creator<LazyImage> CREATOR = new Parcelable.Creator<LazyImage>() {
        /* class com.oculus.model.LazyImage.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public LazyImage createFromParcel(Parcel parcel) {
            return new LazyImage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public LazyImage[] newArray(int i) {
            return new LazyImage[i];
        }
    };
    private final float mAspectRatio;
    private final int mResourceId;
    private final String mUri;

    public int describeContents() {
        return 0;
    }

    public LazyImage(String str, float f) {
        this.mUri = str;
        this.mResourceId = 0;
        this.mAspectRatio = f;
    }

    public LazyImage(int i) {
        this.mUri = null;
        this.mResourceId = i;
        this.mAspectRatio = 0.0f;
    }

    public String getUri() {
        String str = this.mUri;
        return str != null ? str : UriUtil.getUriForResourceId(this.mResourceId).toString();
    }

    public float getAspectRatio(Context context) {
        float f = this.mAspectRatio;
        if (f != 0.0f) {
            return f;
        }
        if (this.mResourceId == 0) {
            return 0.0f;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), this.mResourceId, options);
        return ((float) options.outWidth) / ((float) options.outHeight);
    }

    public LazyImage(Parcel parcel) {
        this.mUri = parcel.readString();
        this.mResourceId = parcel.readInt();
        this.mAspectRatio = parcel.readFloat();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUri);
        parcel.writeInt(this.mResourceId);
        parcel.writeFloat(this.mAspectRatio);
    }
}
