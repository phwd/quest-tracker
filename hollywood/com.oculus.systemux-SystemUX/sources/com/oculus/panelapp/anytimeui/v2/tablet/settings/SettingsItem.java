package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.databinding.Bindable;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;
import java.util.function.Supplier;

public class SettingsItem extends BaseSettingsItem {
    private static final int NO_RESOURCE_ID = 0;
    private final BaseSettingsActionType mActionType;
    private final Context mContext;
    private final int mIconID;
    private final Uri mImageUri;
    private final boolean mIndented;
    private final boolean mIsImageCircular;
    private final String mName;
    @DrawableRes
    private final int mPlaceholderImage;
    private final String mSubtitle;
    private final Supplier<String> mSubtitleFetcher;
    private final String mSubtitleUri;
    private final String mTitle;
    private Supplier<Boolean> mVisibilityFetcher;

    public interface InitializeHandler {
        void run(SettingsItem settingsItem);
    }

    private SettingsItem(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, String str, Gatekeeper[] gatekeeperArr, boolean z, BaseSettingsActionType baseSettingsActionType, String str2, String str3, Supplier<String> supplier, Supplier<Boolean> supplier2, int i, String str4, Uri uri, boolean z2, @DrawableRes int i2, InitializeHandler initializeHandler, boolean z3) {
        super(anytimeUIAndroidPanelAppV2, gatekeeperArr, z, supplier2);
        this.mContext = context;
        this.mName = str;
        this.mTitle = str2;
        this.mSubtitle = str3;
        this.mSubtitleFetcher = supplier;
        this.mVisibilityFetcher = supplier2;
        this.mIconID = i;
        this.mActionType = baseSettingsActionType;
        this.mSubtitleUri = str4;
        this.mImageUri = uri;
        this.mIsImageCircular = z2;
        this.mPlaceholderImage = i2;
        this.mIndented = z3;
        if (initializeHandler != null) {
            initializeHandler.run(this);
        }
    }

    public String getTitle() {
        if (TextUtils.isEmpty(this.mTitle)) {
            return "";
        }
        return this.mTitle;
    }

    @Bindable
    public String getSubtitle() {
        Supplier<String> supplier = this.mSubtitleFetcher;
        if (supplier != null) {
            return supplier.get();
        }
        if (TextUtils.isEmpty(this.mSubtitle)) {
            return "";
        }
        return this.mSubtitle;
    }

    public boolean isTitleVisible() {
        return !TextUtils.isEmpty(this.mTitle);
    }

    public boolean isSubtitleVisible() {
        return !TextUtils.isEmpty(this.mSubtitle) || this.mSubtitleFetcher != null;
    }

    public Drawable getIcon() {
        if (this.mIconID == 0) {
            return null;
        }
        return this.mContext.getResources().getDrawable(this.mIconID, null);
    }

    public Uri getImageUri() {
        return this.mImageUri;
    }

    public boolean getIsImageCircular() {
        return this.mIsImageCircular;
    }

    @DrawableRes
    public int getPlaceholderImage() {
        return this.mPlaceholderImage;
    }

    public boolean hasPlaceholderImage() {
        return this.mPlaceholderImage != 0;
    }

    public boolean getVisibility() {
        Supplier<Boolean> supplier = this.mVisibilityFetcher;
        if (supplier != null) {
            return supplier.get().booleanValue();
        }
        return true;
    }

    public BaseSettingsActionType getActionType() {
        return this.mActionType;
    }

    public String getName() {
        return this.mName;
    }

    public int getIconVisibility() {
        if (this.mIndented) {
            return 4;
        }
        return getIcon() != null ? 0 : 8;
    }

    public void buildView(SettingsListItem settingsListItem, OCEventHandler oCEventHandler, SettingsLogger settingsLogger) {
        BaseSettingsActionType baseSettingsActionType = this.mActionType;
        if (baseSettingsActionType != null) {
            baseSettingsActionType.buildView(settingsListItem, oCEventHandler, settingsLogger);
        }
    }

    @Bindable
    public String getSubtitleUri() {
        return this.mSubtitleUri;
    }

    public static class Builder extends BaseSettingsItem.Builder {
        private BaseSettingsActionType mActionType;
        private Context mContext;
        private Gatekeeper[] mGatekeepers = new Gatekeeper[0];
        private int mIconID;
        private Uri mImageUri;
        private boolean mIndented;
        private InitializeHandler mInitializeHandler;
        private boolean mIsImageCircular;
        private String mName;
        private AnytimeUIAndroidPanelAppV2 mPanelApp;
        @DrawableRes
        private int mPlaceholderImage;
        private boolean mShowInEnterprise = false;
        private String mSubtitle;
        private Supplier<String> mSubtitleFetcher;
        private String mSubtitleUri;
        private String mTitle;
        private Supplier<Boolean> mVisibilityFetcher;

        public Builder(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
            this.mContext = context;
            this.mPanelApp = anytimeUIAndroidPanelAppV2;
        }

        public Builder withSettingName(String str) {
            this.mName = str;
            return this;
        }

        public Builder withGatekeepers(Gatekeeper... gatekeeperArr) {
            this.mGatekeepers = gatekeeperArr;
            return this;
        }

        public Builder withOnInitialize(InitializeHandler initializeHandler) {
            this.mInitializeHandler = initializeHandler;
            return this;
        }

        public Builder withTitle(@StringRes int i) {
            this.mTitle = this.mContext.getResources().getString(i);
            return this;
        }

        public Builder withTitle(@NonNull String str) {
            this.mTitle = str;
            return this;
        }

        public Builder withSubtitle(@StringRes int i) {
            this.mSubtitle = this.mContext.getResources().getString(i);
            return this;
        }

        public Builder withSubtitle(@NonNull String str) {
            this.mSubtitle = str;
            return this;
        }

        public Builder withSubtitleUri(@NonNull String str) {
            this.mSubtitleUri = str;
            return this;
        }

        public Builder withIcon(int i) {
            this.mIconID = i;
            return this;
        }

        public Builder withImageUri(Uri uri) {
            this.mImageUri = uri;
            return this;
        }

        public Builder withIsImageCircular(boolean z) {
            this.mIsImageCircular = z;
            return this;
        }

        public Builder withPlaceholderImage(@DrawableRes int i) {
            this.mPlaceholderImage = i;
            return this;
        }

        public Builder withShowInEnterprise(boolean z) {
            this.mShowInEnterprise = z;
            return this;
        }

        public Builder withDynamicSubtitle(Supplier<String> supplier) {
            this.mSubtitleFetcher = supplier;
            return this;
        }

        public Builder withVisibilityCondition(Supplier<Boolean> supplier) {
            this.mVisibilityFetcher = supplier;
            return this;
        }

        public Builder withIndented(boolean z) {
            this.mIndented = z;
            return this;
        }

        public Builder withSettingsActionType(BaseSettingsActionType.Builder builder) {
            this.mActionType = builder.build();
            return this;
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem.Builder
        public BaseSettingsItem build() {
            return new SettingsItem(this.mContext, this.mPanelApp, this.mName, this.mGatekeepers, this.mShowInEnterprise, this.mActionType, this.mTitle, this.mSubtitle, this.mSubtitleFetcher, this.mVisibilityFetcher, this.mIconID, this.mSubtitleUri, this.mImageUri, this.mIsImageCircular, this.mPlaceholderImage, this.mInitializeHandler, this.mIndented);
        }
    }
}
