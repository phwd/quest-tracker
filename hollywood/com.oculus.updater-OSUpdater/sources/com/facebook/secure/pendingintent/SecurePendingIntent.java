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
import com.facebook.secure.urifilter.UriFilter;
import com.facebook.ultralight.UL;
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
        public static final EnforceMode DEFAULT_ENFORCE_MODE = EnforceMode.LOG_ONLY;
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
        @Nullable
        private UriFilter mUriFilter = null;

        public Builder setAction(@Nullable String str) {
            this.mAction = str;
            return this;
        }

        public Builder setComponentName(@Nullable ComponentName componentName) {
            this.mComponentName = componentName;
            return this;
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public Intent createIntent(Context context) {
            Intent intent = new Intent();
            intent.setComponent(this.mComponentName);
            intent.setFlags(this.mFlags);
            if (this.mIsDummyIntent) {
                intent.setComponent(new ComponentName(context, "com.facebook.invalid_class.f4c3b00c"));
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
            UriFilter uriFilter = this.mUriFilter;
            if (uriFilter != null && !uriFilter.isValid(this.mData)) {
                customizeThrow(String.format("SecurePendingIntent UriFilter fails. Data: %s", this.mData));
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
    @TargetApi(UL.id._UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID)
    public static class Api15Utils {
        Api15Utils() {
        }

        public static void setSelector(Intent intent, @Nullable Intent intent2) {
            intent.setSelector(intent2);
        }
    }

    static boolean isOnOrAboveApi16() {
        return Build.VERSION.SDK_INT >= 16;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(UL.id._UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID)
    public static class Api16Utils {
        Api16Utils() {
        }

        public static void setClipData(Intent intent, @Nullable ClipData clipData) {
            intent.setClipData(clipData);
        }
    }
}
