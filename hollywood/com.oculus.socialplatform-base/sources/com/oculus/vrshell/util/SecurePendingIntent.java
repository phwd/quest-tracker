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
        public String mAction = null;
        public final Set<String> mCategories = new HashSet();
        @Nullable
        public ClipData mClipData = null;
        @Nullable
        public ComponentName mComponentName = null;
        @Nullable
        public Uri mData = null;
        @Nullable
        public Intent mSelector = null;
        @Nullable
        public Rect mSourceBounds = null;
        @Nullable
        public String mType = null;

        public Builder addCategory(String str) {
            this.mCategories.add(str);
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

        public static void enforceExplicitIntent(Intent intent) {
            if (intent.getComponent() == null) {
                throw new SecurityException("Must generate PendingIntent based on an explicit intent.");
            }
        }

        public PendingIntent buildCallerInfoForActivity(Context context, int i, int i2) {
            Intent createIntent = createIntent();
            if (context.getPackageManager().queryIntentActivities(createIntent, 131072).isEmpty()) {
                return PendingIntent.getActivity(context, i, createIntent, i2);
            }
            throw new SecurityException("Cannot use existing ComponentName to generate an invalid PendingIntent");
        }

        public PendingIntent buildForActivity(Context context, int i, int i2) {
            return PendingIntent.getActivity(context, i, createIntent(), i2);
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

        public Builder setAction(@Nullable String str) {
            this.mAction = str;
            return this;
        }

        public Builder setClipData(@Nullable ClipData clipData) {
            this.mClipData = clipData;
            return this;
        }

        public Builder setComponentName(@Nullable ComponentName componentName) {
            this.mComponentName = componentName;
            return this;
        }

        public Builder setData(@Nullable Uri uri) {
            this.mData = uri;
            return this;
        }

        public Builder setSelector(@Nullable Intent intent) {
            this.mSelector = intent;
            return this;
        }

        public Builder setSourceBounds(@Nullable Rect rect) {
            this.mSourceBounds = rect;
            return this;
        }

        public Builder setType(@Nullable String str) {
            this.mType = str;
            return this;
        }

        public Builder() {
        }

        public Builder(@Nullable ComponentName componentName) {
            this.mComponentName = componentName;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
