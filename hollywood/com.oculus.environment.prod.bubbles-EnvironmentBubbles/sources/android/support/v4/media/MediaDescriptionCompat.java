package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.media.MediaDescriptionCompatApi21;
import android.support.v4.media.MediaDescriptionCompatApi23;
import android.text.TextUtils;

public final class MediaDescriptionCompat implements Parcelable {
    public static final long BT_FOLDER_TYPE_ALBUMS = 2;
    public static final long BT_FOLDER_TYPE_ARTISTS = 3;
    public static final long BT_FOLDER_TYPE_GENRES = 4;
    public static final long BT_FOLDER_TYPE_MIXED = 0;
    public static final long BT_FOLDER_TYPE_PLAYLISTS = 5;
    public static final long BT_FOLDER_TYPE_TITLES = 1;
    public static final long BT_FOLDER_TYPE_YEARS = 6;
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new Parcelable.Creator<MediaDescriptionCompat>() {
        /* class android.support.v4.media.MediaDescriptionCompat.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            if (Build.VERSION.SDK_INT < 21) {
                return new MediaDescriptionCompat(parcel);
            }
            return MediaDescriptionCompat.fromMediaDescription(MediaDescriptionCompatApi21.fromParcel(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public MediaDescriptionCompat[] newArray(int i) {
            return new MediaDescriptionCompat[i];
        }
    };
    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static final String DESCRIPTION_KEY_MEDIA_URI = "android.support.v4.media.description.MEDIA_URI";
    @RestrictTo({RestrictTo.Scope.GROUP_ID})
    public static final String DESCRIPTION_KEY_NULL_BUNDLE_FLAG = "android.support.v4.media.description.NULL_BUNDLE_FLAG";
    public static final String EXTRA_BT_FOLDER_TYPE = "android.media.extra.BT_FOLDER_TYPE";
    private final CharSequence mDescription;
    private Object mDescriptionObj;
    private final Bundle mExtras;
    private final Bitmap mIcon;
    private final Uri mIconUri;
    private final String mMediaId;
    private final Uri mMediaUri;
    private final CharSequence mSubtitle;
    private final CharSequence mTitle;

    public int describeContents() {
        return 0;
    }

    MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.mMediaId = str;
        this.mTitle = charSequence;
        this.mSubtitle = charSequence2;
        this.mDescription = charSequence3;
        this.mIcon = bitmap;
        this.mIconUri = uri;
        this.mExtras = bundle;
        this.mMediaUri = uri2;
    }

    MediaDescriptionCompat(Parcel parcel) {
        this.mMediaId = parcel.readString();
        this.mTitle = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mSubtitle = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mDescription = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mIcon = (Bitmap) parcel.readParcelable(null);
        this.mIconUri = (Uri) parcel.readParcelable(null);
        this.mExtras = parcel.readBundle();
        this.mMediaUri = (Uri) parcel.readParcelable(null);
    }

    @Nullable
    public String getMediaId() {
        return this.mMediaId;
    }

    @Nullable
    public CharSequence getTitle() {
        return this.mTitle;
    }

    @Nullable
    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    @Nullable
    public CharSequence getDescription() {
        return this.mDescription;
    }

    @Nullable
    public Bitmap getIconBitmap() {
        return this.mIcon;
    }

    @Nullable
    public Uri getIconUri() {
        return this.mIconUri;
    }

    @Nullable
    public Bundle getExtras() {
        return this.mExtras;
    }

    @Nullable
    public Uri getMediaUri() {
        return this.mMediaUri;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT < 21) {
            parcel.writeString(this.mMediaId);
            TextUtils.writeToParcel(this.mTitle, parcel, i);
            TextUtils.writeToParcel(this.mSubtitle, parcel, i);
            TextUtils.writeToParcel(this.mDescription, parcel, i);
            parcel.writeParcelable(this.mIcon, i);
            parcel.writeParcelable(this.mIconUri, i);
            parcel.writeBundle(this.mExtras);
            parcel.writeParcelable(this.mMediaUri, i);
            return;
        }
        MediaDescriptionCompatApi21.writeToParcel(getMediaDescription(), parcel, i);
    }

    public String toString() {
        return ((Object) this.mTitle) + ", " + ((Object) this.mSubtitle) + ", " + ((Object) this.mDescription);
    }

    public Object getMediaDescription() {
        if (this.mDescriptionObj != null || Build.VERSION.SDK_INT < 21) {
            return this.mDescriptionObj;
        }
        Object newInstance = MediaDescriptionCompatApi21.Builder.newInstance();
        MediaDescriptionCompatApi21.Builder.setMediaId(newInstance, this.mMediaId);
        MediaDescriptionCompatApi21.Builder.setTitle(newInstance, this.mTitle);
        MediaDescriptionCompatApi21.Builder.setSubtitle(newInstance, this.mSubtitle);
        MediaDescriptionCompatApi21.Builder.setDescription(newInstance, this.mDescription);
        MediaDescriptionCompatApi21.Builder.setIconBitmap(newInstance, this.mIcon);
        MediaDescriptionCompatApi21.Builder.setIconUri(newInstance, this.mIconUri);
        Bundle bundle = this.mExtras;
        if (Build.VERSION.SDK_INT < 23 && this.mMediaUri != null) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putBoolean(DESCRIPTION_KEY_NULL_BUNDLE_FLAG, true);
            }
            bundle.putParcelable(DESCRIPTION_KEY_MEDIA_URI, this.mMediaUri);
        }
        MediaDescriptionCompatApi21.Builder.setExtras(newInstance, bundle);
        if (Build.VERSION.SDK_INT >= 23) {
            MediaDescriptionCompatApi23.Builder.setMediaUri(newInstance, this.mMediaUri);
        }
        this.mDescriptionObj = MediaDescriptionCompatApi21.Builder.build(newInstance);
        return this.mDescriptionObj;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0070  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.v4.media.MediaDescriptionCompat fromMediaDescription(java.lang.Object r6) {
        /*
        // Method dump skipped, instructions count: 133
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaDescriptionCompat.fromMediaDescription(java.lang.Object):android.support.v4.media.MediaDescriptionCompat");
    }

    public static final class Builder {
        private CharSequence mDescription;
        private Bundle mExtras;
        private Bitmap mIcon;
        private Uri mIconUri;
        private String mMediaId;
        private Uri mMediaUri;
        private CharSequence mSubtitle;
        private CharSequence mTitle;

        public Builder setMediaId(@Nullable String str) {
            this.mMediaId = str;
            return this;
        }

        public Builder setTitle(@Nullable CharSequence charSequence) {
            this.mTitle = charSequence;
            return this;
        }

        public Builder setSubtitle(@Nullable CharSequence charSequence) {
            this.mSubtitle = charSequence;
            return this;
        }

        public Builder setDescription(@Nullable CharSequence charSequence) {
            this.mDescription = charSequence;
            return this;
        }

        public Builder setIconBitmap(@Nullable Bitmap bitmap) {
            this.mIcon = bitmap;
            return this;
        }

        public Builder setIconUri(@Nullable Uri uri) {
            this.mIconUri = uri;
            return this;
        }

        public Builder setExtras(@Nullable Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public Builder setMediaUri(@Nullable Uri uri) {
            this.mMediaUri = uri;
            return this;
        }

        public MediaDescriptionCompat build() {
            return new MediaDescriptionCompat(this.mMediaId, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.mIconUri, this.mExtras, this.mMediaUri);
        }
    }
}
