package com.oculus.model;

import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.common.string.StringUtil;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.oculus.horizon.api.common.Section;
import javax.annotation.Nullable;

public class StoreRoadblockModel implements Parcelable {
    public static final Parcelable.Creator<StoreRoadblockModel> CREATOR = new Parcelable.Creator<StoreRoadblockModel>() {
        /* class com.oculus.model.StoreRoadblockModel.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public StoreRoadblockModel createFromParcel(Parcel parcel) {
            return new StoreRoadblockModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public StoreRoadblockModel[] newArray(int i) {
            return new StoreRoadblockModel[i];
        }
    };
    @Nullable
    public final String category;
    public final String key;
    @Nullable
    private final LazyString mButtonText;
    private final LazyStringWithUri mDescription;
    @Nullable
    private final LazyImage mImage;
    @Nullable
    private final LazyStringWithUri mSecondDescription;
    private final LazyString mTitle;

    public int describeContents() {
        return 0;
    }

    public String getTitle(Context context) {
        return this.mTitle.get(context);
    }

    public String getDescriptionText(Context context) {
        return this.mDescription.getText(context);
    }

    @Nullable
    public Uri getDescriptionUri() {
        return this.mDescription.getUri();
    }

    @Nullable
    public String getSecondDescriptionText(Context context) {
        LazyStringWithUri lazyStringWithUri = this.mSecondDescription;
        if (lazyStringWithUri == null) {
            return null;
        }
        return lazyStringWithUri.getText(context);
    }

    @Nullable
    public Uri getSecondDescriptionUri() {
        LazyStringWithUri lazyStringWithUri = this.mSecondDescription;
        if (lazyStringWithUri == null) {
            return null;
        }
        return lazyStringWithUri.getUri();
    }

    @Nullable
    public String getImageUri() {
        LazyImage lazyImage = this.mImage;
        if (lazyImage != null) {
            return lazyImage.getUri();
        }
        return null;
    }

    public float getImageAspectRatio(Context context) {
        LazyImage lazyImage = this.mImage;
        if (lazyImage != null) {
            return lazyImage.getAspectRatio(context);
        }
        return 0.0f;
    }

    @Nullable
    public String getButtonText(Context context) {
        LazyString lazyString = this.mButtonText;
        if (lazyString != null) {
            return lazyString.get(context);
        }
        return null;
    }

    public StoreRoadblockModel(String str, LazyString lazyString, LazyStringWithUri lazyStringWithUri, LazyStringWithUri lazyStringWithUri2, LazyImage lazyImage, LazyString lazyString2, String str2) {
        Preconditions.checkArgument((str == null || lazyString == null || lazyStringWithUri == null) ? false : true);
        this.key = str;
        this.mTitle = lazyString;
        this.mDescription = lazyStringWithUri;
        this.mSecondDescription = lazyStringWithUri2;
        this.mImage = lazyImage;
        this.mButtonText = lazyString2;
        this.category = str2;
    }

    public boolean isMalibuCategory() {
        return isMalibuCategory(this.category);
    }

    private static boolean isValidAssetCategory(String str) {
        return isMalibuCategory(str);
    }

    public static boolean isMalibuCategory(String str) {
        return "GEAR_VR_CONTROLLER".equals(str);
    }

    public static class Builder {
        private LazyString mButtonText;
        private String mCategory;
        private LazyStringWithUri mDescription;
        private LazyImage mImage;
        private String mKey;
        private LazyStringWithUri mSecondDescription;
        private LazyString mTitle;

        public Builder setKey(String str) {
            this.mKey = str;
            return this;
        }

        public Builder setTitle(String str) {
            this.mTitle = new LazyString(str);
            return this;
        }

        public Builder setTitle(int i) {
            this.mTitle = new LazyString(i);
            return this;
        }

        public Builder setDescription(String str, Uri uri) {
            this.mDescription = new LazyStringWithUri(str, uri);
            return this;
        }

        public Builder setDescription(int i) {
            this.mDescription = new LazyStringWithUri(i, (Uri) null);
            return this;
        }

        public Builder setSecondDescription(String str, Uri uri) {
            this.mSecondDescription = new LazyStringWithUri(str, uri);
            return this;
        }

        public Builder setSecondDescription(int i) {
            this.mSecondDescription = new LazyStringWithUri(i, (Uri) null);
            return this;
        }

        public Builder setCategory(String str) {
            this.mCategory = str;
            return this;
        }

        public Builder setImage(Section.Assets.AssetEdge.AssetNode assetNode) {
            Preconditions.checkArgument(Section.Assets.AssetEdge.AssetNode.AssetMediaType.IMAGE.toString().equals(assetNode.asset_media_type));
            Preconditions.checkArgument(assetNode.asset_media.uri != null);
            String str = assetNode.asset_media.uri;
            float f = 0.0f;
            if (((float) assetNode.asset_media.height) != 0.0f) {
                f = ((float) assetNode.asset_media.width) / ((float) assetNode.asset_media.height);
            }
            this.mImage = new LazyImage(str, f);
            return this;
        }

        public Builder setImage(int i) {
            this.mImage = new LazyImage(i);
            return this;
        }

        public Builder setButtonText(int i) {
            this.mButtonText = new LazyString(i);
            return this;
        }

        public StoreRoadblockModel build() {
            return new StoreRoadblockModel(this.mKey, this.mTitle, this.mDescription, this.mSecondDescription, this.mImage, this.mButtonText, this.mCategory);
        }
    }

    public StoreRoadblockModel(Parcel parcel) {
        this.key = parcel.readString();
        this.mTitle = (LazyString) parcel.readParcelable(LazyString.class.getClassLoader());
        this.mDescription = (LazyStringWithUri) parcel.readParcelable(LazyStringWithUri.class.getClassLoader());
        this.mSecondDescription = (LazyStringWithUri) parcel.readParcelable(LazyStringWithUri.class.getClassLoader());
        this.mImage = (LazyImage) parcel.readParcelable(LazyImage.class.getClassLoader());
        this.mButtonText = (LazyString) parcel.readParcelable(LazyString.class.getClassLoader());
        this.category = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
        parcel.writeParcelable(this.mTitle, i);
        parcel.writeParcelable(this.mDescription, i);
        parcel.writeParcelable(this.mSecondDescription, i);
        parcel.writeParcelable(this.mImage, i);
        parcel.writeParcelable(this.mButtonText, i);
        parcel.writeString(this.category);
    }

    @Nullable
    public static StoreRoadblockModel from(Section.Assets assets, Section.Assets.AssetEdge.AssetNode.AssetMediaName assetMediaName, Section.Assets.AssetEdge.AssetNode.AssetMediaName assetMediaName2, Section.Assets.AssetEdge.AssetNode.AssetMediaName assetMediaName3, Section.Assets.AssetEdge.AssetNode.AssetMediaName assetMediaName4, String str) {
        Uri uri = null;
        if (!(assets == null || assets.edges == null)) {
            Section.Assets.AssetEdge.AssetNode assetNode = null;
            Section.Assets.AssetEdge.AssetNode assetNode2 = null;
            Section.Assets.AssetEdge.AssetNode assetNode3 = null;
            Section.Assets.AssetEdge.AssetNode assetNode4 = null;
            for (Section.Assets.AssetEdge assetEdge : assets.edges) {
                if (!(assetEdge == null || assetEdge.node == null)) {
                    if (assetNode == null && assetMediaName.name().toLowerCase().equals(assetEdge.node.name) && Section.Assets.AssetEdge.AssetNode.AssetMediaType.DISPLAY_TEXT.toString().equals(assetEdge.node.asset_media_type) && !Strings.isNullOrEmpty(assetEdge.node.display_text)) {
                        assetNode = assetEdge.node;
                    } else if (assetNode2 == null && assetMediaName2.name().toLowerCase().equals(assetEdge.node.name) && Section.Assets.AssetEdge.AssetNode.AssetMediaType.DISPLAY_TEXT.toString().equals(assetEdge.node.asset_media_type) && !Strings.isNullOrEmpty(assetEdge.node.display_text)) {
                        assetNode2 = assetEdge.node;
                    } else if (assetNode3 == null && assetMediaName3.name().toLowerCase().equals(assetEdge.node.name) && Section.Assets.AssetEdge.AssetNode.AssetMediaType.DISPLAY_TEXT.toString().equals(assetEdge.node.asset_media_type) && !Strings.isNullOrEmpty(assetEdge.node.display_text)) {
                        assetNode3 = assetEdge.node;
                    } else if (assetNode4 == null && assetMediaName4.name().toLowerCase().equals(assetEdge.node.name) && Section.Assets.AssetEdge.AssetNode.AssetMediaType.IMAGE.toString().equals(assetEdge.node.asset_media_type) && assetEdge.node.asset_media != null && !Strings.isNullOrEmpty(assetEdge.node.asset_media.uri)) {
                        assetNode4 = assetEdge.node;
                    }
                }
            }
            if (!(assetNode == null || assetNode2 == null)) {
                Builder description = new Builder().setKey(StringUtil.join(assetNode.display_text, assetNode2.display_text)).setTitle(assetNode.display_text).setDescription(assetNode2.display_text, !Strings.isNullOrEmpty(assetNode2.display_text_uri) ? Uri.parse(assetNode2.display_text_uri) : null);
                if (assetNode3 != null) {
                    String str2 = assetNode3.display_text;
                    if (!Strings.isNullOrEmpty(assetNode3.display_text_uri)) {
                        uri = Uri.parse(assetNode3.display_text_uri);
                    }
                    description.setSecondDescription(str2, uri);
                }
                if (assetNode4 != null) {
                    description.setImage(assetNode4);
                }
                if (!Strings.isNullOrEmpty(str) && isValidAssetCategory(str)) {
                    description.setCategory(str);
                }
                return description.build();
            }
        }
        return null;
    }
}
