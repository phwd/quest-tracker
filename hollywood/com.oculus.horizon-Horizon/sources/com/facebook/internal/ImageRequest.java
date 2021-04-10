package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import com.oculus.horizon.notifications.core.NotificationsProperties;
import java.util.Locale;

public class ImageRequest {
    public static final String AUTHORITY = "graph.facebook.com";
    public static final String HEIGHT_PARAM = "height";
    public static final String MIGRATION_PARAM = "migration_overrides";
    public static final String MIGRATION_VALUE = "{october_2012:true}";
    public static final String PATH = "%s/picture";
    public static final String SCHEME = "https";
    public static final int UNSPECIFIED_DIMENSION = 0;
    public static final String WIDTH_PARAM = "width";
    public boolean allowCachedRedirects;
    public Callback callback;
    public Object callerTag;
    public Context context;
    public Uri imageUri;

    public static class Builder {
        public boolean allowCachedRedirects;
        public Callback callback;
        public Object callerTag;
        public Context context;
        public Uri imageUrl;

        public ImageRequest build() {
            return new ImageRequest(this);
        }

        public Builder(Context context2, Uri uri) {
            Validate.notNull(uri, NotificationsProperties.IMAGE_URI_KEY);
            this.context = context2;
            this.imageUrl = uri;
        }

        public Builder setAllowCachedRedirects(boolean z) {
            this.allowCachedRedirects = z;
            return this;
        }

        public Builder setCallback(Callback callback2) {
            this.callback = callback2;
            return this;
        }

        public Builder setCallerTag(Object obj) {
            this.callerTag = obj;
            return this;
        }
    }

    public interface Callback {
        void onCompleted(ImageResponse imageResponse);
    }

    public static Uri getProfilePictureUri(String str, int i, int i2) {
        Validate.notNullOrEmpty(str, "userId");
        int max = Math.max(i, 0);
        int max2 = Math.max(i2, 0);
        if (max == 0 && max2 == 0) {
            throw new IllegalArgumentException("Either width or height must be greater than 0");
        }
        Uri.Builder path = new Uri.Builder().scheme("https").authority("graph.facebook.com").path(String.format(Locale.US, PATH, str));
        if (max2 != 0) {
            path.appendQueryParameter(HEIGHT_PARAM, String.valueOf(max2));
        }
        if (max != 0) {
            path.appendQueryParameter(WIDTH_PARAM, String.valueOf(max));
        }
        path.appendQueryParameter(MIGRATION_PARAM, MIGRATION_VALUE);
        return path.build();
    }

    public Callback getCallback() {
        return this.callback;
    }

    public Object getCallerTag() {
        return this.callerTag;
    }

    public Context getContext() {
        return this.context;
    }

    public Uri getImageUri() {
        return this.imageUri;
    }

    public boolean isCachedRedirectAllowed() {
        return this.allowCachedRedirects;
    }

    public ImageRequest(Builder builder) {
        this.context = builder.context;
        this.imageUri = builder.imageUrl;
        this.callback = builder.callback;
        this.allowCachedRedirects = builder.allowCachedRedirects;
        Object obj = builder.callerTag;
        this.callerTag = obj == null ? new Object() : obj;
    }
}
