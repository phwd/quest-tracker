package com.oculus.secure.pendingintent;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import java.util.HashSet;
import java.util.Set;

public class SecurePendingIntent {

    public static class Builder {
        public String mAction = null;
        public final Set<String> mCategories = new HashSet();
        public ClipData mClipData = null;
        public ComponentName mComponentName = null;
        public Uri mData = null;
        public Intent mSelector = null;
        public Rect mSourceBounds = null;
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

        public Builder setAction(String str) {
            this.mAction = str;
            return this;
        }

        public Builder setClipData(ClipData clipData) {
            this.mClipData = clipData;
            return this;
        }

        public Builder setComponentName(ComponentName componentName) {
            this.mComponentName = componentName;
            return this;
        }

        public Builder setData(Uri uri) {
            this.mData = uri;
            return this;
        }

        public Builder setSelector(Intent intent) {
            this.mSelector = intent;
            return this;
        }

        public Builder setSourceBounds(Rect rect) {
            this.mSourceBounds = rect;
            return this;
        }

        public Builder setType(String str) {
            this.mType = str;
            return this;
        }

        public Builder() {
        }

        public Builder(ComponentName componentName) {
            this.mComponentName = componentName;
        }
    }

    @TargetApi(15)
    public static class Api15Utils {
        public static Intent getSelector(Intent intent) {
            return intent.getSelector();
        }

        public static void setSelector(Intent intent, Intent intent2) {
            intent.setSelector(intent2);
        }
    }

    @TargetApi(16)
    public static class Api16Utils {
        public static ClipData getClipData(Intent intent) {
            return intent.getClipData();
        }

        public static void setClipData(Intent intent, ClipData clipData) {
            intent.setClipData(clipData);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static boolean isOnOrAboveApi15() {
        return true;
    }

    public static boolean isOnOrAboveApi16() {
        return true;
    }
}
