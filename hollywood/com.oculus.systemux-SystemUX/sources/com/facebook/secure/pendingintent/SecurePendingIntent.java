package com.facebook.secure.pendingintent;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.facebook.secure.logger.Reporter;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

public class SecurePendingIntent {

    public enum EnforceMode {
        LOG_ONLY,
        THROW_EXCEPTION
    }

    @SuppressLint({"HexColorValueUsage"})
    public static class Builder {
        private static final long ALLOW_IMPLICIT = 1;
        private static final long ALLOW_MUTABLE = 8;
        private static final long ALLOW_ONLY_SAME_APP_IMPLICIT = 2;
        private static final long ALLOW_UNSAFE_IMPLICIT_INTENT = 4;
        public static final EnforceMode DEFAULT_ENFORCE_MODE = EnforceMode.LOG_ONLY;
        private static final String INVALID_CLASS = "com.facebook.invalid_class.f4c3b00c";
        public static final String TAG = "SecurePendingIntent";
        @Nullable
        private String mAction = null;
        private final Set<String> mCategories = new HashSet();
        @Nullable
        private ClipData mClipData = null;
        @Nullable
        private ComponentName mComponentName = null;
        private long mConfig = 0;
        @Nullable
        private Uri mData = null;
        private EnforceMode mEnforceMode = DEFAULT_ENFORCE_MODE;
        @Nullable
        private Bundle mExtras = null;
        private int mFlags = 0;
        private boolean mIsDummyIntent = false;
        @Nullable
        private String mPackageName = null;
        @Nullable
        private Reporter mReporter;
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

        public Builder setPackageName(@Nullable String str) {
            this.mPackageName = str;
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

        public Builder putExtras(Bundle bundle) {
            return putExtrasWithClassLoader(bundle, null);
        }

        public Builder putExtrasWithClassLoader(Bundle bundle, @Nullable ClassLoader classLoader) {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            if (classLoader != null) {
                this.mExtras.setClassLoader(classLoader);
            }
            this.mExtras.putAll(bundle);
            return this;
        }

        public Builder putExtra(String str, boolean z) {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            this.mExtras.putBoolean(str, z);
            return this;
        }

        public Builder putExtra(String str, int i) {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            this.mExtras.putInt(str, i);
            return this;
        }

        public Builder putExtra(String str, String str2) {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            this.mExtras.putString(str, str2);
            return this;
        }

        public Builder setFlags(int i) {
            this.mFlags = i;
            return this;
        }

        public Builder allowSafeImplicit() {
            this.mConfig |= 1;
            return this;
        }

        public Builder allowOnlySameAppImplicitIntent() {
            this.mConfig |= 1;
            this.mConfig |= 2;
            return this;
        }

        public Builder allowUnsafeImplicitIntent__DO_NOT_USE() {
            this.mConfig |= 1;
            this.mConfig |= 4;
            return this;
        }

        public Builder allowMutable() {
            this.mConfig |= 8;
            return this;
        }

        public Builder setDummyIntent() {
            this.mIsDummyIntent = true;
            return this;
        }

        public Builder setEnforceMode(EnforceMode enforceMode) {
            this.mEnforceMode = enforceMode;
            return this;
        }

        public Builder setReporter(Reporter reporter) {
            this.mReporter = reporter;
            return this;
        }

        public Builder fromIntent(Intent intent) {
            return fromIntentWithExtras(intent, null);
        }

        public Builder fromIntentWithExtras(Intent intent, @Nullable ClassLoader classLoader) {
            fromIntentBase(intent);
            if (intent.getExtras() != null) {
                if (classLoader != null) {
                    intent.setExtrasClassLoader(classLoader);
                }
                putExtrasWithClassLoader(intent.getExtras(), classLoader);
            }
            return this;
        }

        private Builder fromIntentBase(Intent intent) {
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
            this.mFlags = intent.getFlags();
            return this;
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public Intent createIntent(Context context) {
            Intent intent = new Intent();
            intent.setComponent(this.mComponentName);
            intent.setFlags(this.mFlags);
            if (this.mIsDummyIntent) {
                intent.setComponent(new ComponentName(context, INVALID_CLASS));
                intent.setPackage(context.getPackageName());
                return intent;
            }
            intent.setAction(this.mAction);
            intent.setDataAndType(this.mData, this.mType);
            intent.setSourceBounds(this.mSourceBounds);
            if (SecurePendingIntent.isOnOrAboveApi15()) {
                Api15Utils.setSelector(intent, this.mSelector);
            }
            if (SecurePendingIntent.isOnOrAboveApi16()) {
                Api16Utils.setClipData(intent, this.mClipData);
            }
            for (String str : this.mCategories) {
                intent.addCategory(str);
            }
            if (this.mExtras != null) {
                intent.setExtrasClassLoader(context.getClassLoader());
                intent.putExtras(this.mExtras);
            }
            if (!hasFlag(1)) {
                enforceExplicitIntent(intent);
                intent.setPackage(intent.getComponent().getPackageName());
            } else if (hasFlag(4)) {
                return intent;
            } else {
                if (this.mPackageName == null) {
                    this.mPackageName = context.getPackageName();
                }
                intent.setPackage(this.mPackageName);
                if (hasFlag(2)) {
                    if (this.mPackageName.equals(context.getPackageName())) {
                        return intent;
                    }
                    customizeThrow("SecurePendingIntent is configured to allow only implicit intent going to the same app, but detected intent for a different app.");
                }
                enforceSafeImplicitIntent(intent);
            }
            return intent;
        }

        @SuppressLint({"BadMethodUse-android.app.PendingIntent.getActivity"})
        public PendingIntent buildForActivity(Context context, int i, int i2) {
            return PendingIntent.getActivity(context, i, createIntent(context), enforceImmutable(i2));
        }

        @SuppressLint({"BadMethodUse-android.app.PendingIntent.getService"})
        public PendingIntent buildForService(Context context, int i, int i2) {
            return PendingIntent.getService(context, i, createIntent(context), enforceImmutable(i2));
        }

        @SuppressLint({"BadMethodUse-android.app.PendingIntent.getBroadcast"})
        public PendingIntent buildForBroadcast(Context context, int i, int i2) {
            return PendingIntent.getBroadcast(context, i, createIntent(context), enforceImmutable(i2));
        }

        private static void enforceExplicitIntent(Intent intent) {
            if (intent.getComponent() == null) {
                throw new SecurityException("Must generate PendingIntent based on an explicit intent.");
            }
        }

        private void customizeThrow(String str) {
            if (this.mEnforceMode != EnforceMode.THROW_EXCEPTION) {
                Reporter reporter = this.mReporter;
                if (reporter != null) {
                    reporter.report(str);
                    return;
                }
                throw new IllegalArgumentException("Please set reporter for SecurePendingIntent library");
            }
            throw new SecurityException(str);
        }

        private void enforceSafeImplicitIntent(Intent intent) {
            if (intent.getAction() != null && !intent.getAction().startsWith("android")) {
                return;
            }
            if (intent.getCategories() == null || intent.getCategories().isEmpty()) {
                customizeThrow("SecurePendingIntent is configured to allow implicit intent with either customized action or categories");
            }
        }

        private boolean hasFlag(long j) {
            return (j & this.mConfig) != 0;
        }

        private int enforceImmutable(int i) {
            if (Build.VERSION.SDK_INT >= 21) {
                return !hasFlag(8) ? i | 67108864 : i & -67108865;
            }
            return i;
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

        public static void setSelector(Intent intent, @Nullable Intent intent2) {
            intent.setSelector(intent2);
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

        public static void setClipData(Intent intent, @Nullable ClipData clipData) {
            intent.setClipData(clipData);
        }

        public static ClipData getClipData(Intent intent) {
            return intent.getClipData();
        }
    }
}
