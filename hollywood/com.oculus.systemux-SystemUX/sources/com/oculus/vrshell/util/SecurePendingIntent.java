package com.oculus.vrshell.util;

import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import androidx.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public class SecurePendingIntent {

    public static class Builder {
        @Nullable
        private String mAction = null;
        private final Set<String> mCategories = new HashSet();
        @Nullable
        private ClipData mClipData = null;
        @Nullable
        private ComponentName mComponentName = null;
        @Nullable
        private Uri mData = null;
        @Nullable
        private Intent mSelector = null;
        @Nullable
        private Rect mSourceBounds = null;
        @Nullable
        private String mType = null;

        public Builder() {
        }

        public Builder(@Nullable ComponentName componentName) {
            this.mComponentName = componentName;
        }

        public Builder setAction(@Nullable String str) {
            this.mAction = str;
            return this;
        }

        public Builder setData(@Nullable Uri uri) {
            this.mData = uri;
            return this;
        }

        public Builder setType(@Nullable String str) {
            this.mType = str;
            return this;
        }

        public Builder setComponentName(@Nullable ComponentName componentName) {
            this.mComponentName = componentName;
            return this;
        }

        public Builder setSourceBounds(@Nullable Rect rect) {
            this.mSourceBounds = rect;
            return this;
        }

        public Builder setSelector(@Nullable Intent intent) {
            this.mSelector = intent;
            return this;
        }

        public Builder setClipData(@Nullable ClipData clipData) {
            this.mClipData = clipData;
            return this;
        }

        public Builder addCategory(String str) {
            this.mCategories.add(str);
            return this;
        }

        public Builder fromIntent(Intent intent) {
            this.mComponentName = intent.getComponent();
            this.mAction = intent.getAction();
            this.mData = intent.getData();
            this.mType = intent.getType();
            this.mSourceBounds = intent.getSourceBounds();
            this.mSelector = intent.getSelector();
            this.mClipData = intent.getClipData();
            Set<String> categories = intent.getCategories();
            if (categories != null) {
                this.mCategories.addAll(categories);
            }
            return this;
        }

        public Intent createIntent() {
            Intent intent = new Intent();
            intent.setComponent(this.mComponentName);
            enforceExplicitIntent(intent);
            intent.setPackage(intent.getComponent().getPackageName());
            intent.setAction(this.mAction);
            intent.setDataAndType(this.mData, this.mType);
            intent.setSourceBounds(this.mSourceBounds);
            intent.setSelector(this.mSelector);
            intent.setClipData(this.mClipData);
            for (String str : this.mCategories) {
                intent.addCategory(str);
            }
            return intent;
        }

        public PendingIntent buildForActivity(Context context, int i, int i2) {
            return PendingIntent.getActivity(context, i, createIntent(), i2);
        }

        public PendingIntent buildCallerInfoForActivity(Context context, int i, int i2) {
            Intent createIntent = createIntent();
            if (context.getPackageManager().queryIntentActivities(createIntent, 131072).isEmpty()) {
                return PendingIntent.getActivity(context, i, createIntent, i2);
            }
            throw new SecurityException("Cannot use existing ComponentName to generate an invalid PendingIntent");
        }

        private static void enforceExplicitIntent(Intent intent) {
            if (intent.getComponent() == null) {
                throw new SecurityException("Must generate PendingIntent based on an explicit intent.");
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
