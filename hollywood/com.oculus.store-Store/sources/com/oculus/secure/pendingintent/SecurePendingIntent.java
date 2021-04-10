package com.oculus.secure.pendingintent;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import java.util.HashSet;
import java.util.Set;

public class SecurePendingIntent {

    public static class Builder {
        private String mAction = null;
        private final Set<String> mCategories = new HashSet();
        private ClipData mClipData = null;
        private ComponentName mComponentName = null;
        private Uri mData = null;
        private Intent mSelector = null;
        private Rect mSourceBounds = null;
        private String mType = null;

        public Builder() {
        }

        public Builder(ComponentName componentName) {
            this.mComponentName = componentName;
        }

        public Builder setAction(String action) {
            this.mAction = action;
            return this;
        }

        public Builder setData(Uri data) {
            this.mData = data;
            return this;
        }

        public Builder setType(String type) {
            this.mType = type;
            return this;
        }

        public Builder setComponentName(ComponentName componentName) {
            this.mComponentName = componentName;
            return this;
        }

        public Builder setSourceBounds(Rect sourceBounds) {
            this.mSourceBounds = sourceBounds;
            return this;
        }

        public Builder setSelector(Intent selector) {
            this.mSelector = selector;
            return this;
        }

        public Builder setClipData(ClipData clipData) {
            this.mClipData = clipData;
            return this;
        }

        public Builder addCategory(String category) {
            this.mCategories.add(category);
            return this;
        }

        public Builder fromIntent(Intent intent) {
            this.mComponentName = intent.getComponent();
            this.mAction = intent.getAction();
            this.mData = intent.getData();
            this.mType = intent.getType();
            this.mSourceBounds = intent.getSourceBounds();
            this.mSelector = intent.getSelector();
            if (SecurePendingIntent.isOnOrAboveApi16()) {
                this.mClipData = Api16Utils.getClipData(intent);
            }
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
            if (SecurePendingIntent.isOnOrAboveApi15()) {
                Api15Utils.setSelector(intent, this.mSelector);
            }
            if (SecurePendingIntent.isOnOrAboveApi16()) {
                Api16Utils.setClipData(intent, this.mClipData);
            }
            for (String category : this.mCategories) {
                intent.addCategory(category);
            }
            return intent;
        }

        public PendingIntent buildForActivity(Context context, int requestCode, int flags) {
            return PendingIntent.getActivity(context, requestCode, createIntent(), flags);
        }

        public PendingIntent buildCallerInfoForActivity(Context context, int requestCode, int flags) {
            Intent intent = createIntent();
            if (context.getPackageManager().queryIntentActivities(intent, 131072).isEmpty()) {
                return PendingIntent.getActivity(context, requestCode, intent, flags);
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

    static boolean isOnOrAboveApi15() {
        return Build.VERSION.SDK_INT >= 15;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(15)
    public static class Api15Utils {
        Api15Utils() {
        }

        public static void setSelector(Intent intent, Intent selector) {
            intent.setSelector(selector);
        }

        public static Intent getSelector(Intent intent) {
            return intent.getSelector();
        }
    }

    static boolean isOnOrAboveApi16() {
        return Build.VERSION.SDK_INT >= 16;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(16)
    public static class Api16Utils {
        Api16Utils() {
        }

        public static void setClipData(Intent intent, ClipData clipData) {
            intent.setClipData(clipData);
        }

        public static ClipData getClipData(Intent intent) {
            return intent.getClipData();
        }
    }
}
