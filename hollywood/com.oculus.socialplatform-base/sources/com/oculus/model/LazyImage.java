package com.oculus.model;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.vrshell.panels.AndroidPanelLayer;

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
    public final float mAspectRatio;
    public final int mResourceId;
    public final String mUri;

    public int describeContents() {
        return 0;
    }

    public float getAspectRatio(Context context) {
        float f = this.mAspectRatio;
        if (f != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            return f;
        }
        if (this.mResourceId == 0) {
            return AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), this.mResourceId, options);
        return ((float) options.outWidth) / ((float) options.outHeight);
    }

    public String getUri() {
        String str = this.mUri;
        if (str != null) {
            return str;
        }
        return new Uri.Builder().scheme("res").path(String.valueOf(this.mResourceId)).build().toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUri);
        parcel.writeInt(this.mResourceId);
        parcel.writeFloat(this.mAspectRatio);
    }

    public LazyImage(int i) {
        this.mUri = null;
        this.mResourceId = i;
        this.mAspectRatio = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    }

    public LazyImage(Parcel parcel) {
        this.mUri = parcel.readString();
        this.mResourceId = parcel.readInt();
        this.mAspectRatio = parcel.readFloat();
    }

    public LazyImage(String str, float f) {
        this.mUri = str;
        this.mResourceId = 0;
        this.mAspectRatio = f;
    }
}
