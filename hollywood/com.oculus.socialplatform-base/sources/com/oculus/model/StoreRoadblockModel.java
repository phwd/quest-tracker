package com.oculus.model;

import X.AnonymousClass0K2;
import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.oculus.horizon.api.common.Section;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.Iterator;
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
    public final LazyString mButtonText;
    public final LazyStringWithUri mDescription;
    @Nullable
    public final LazyImage mImage;
    @Nullable
    public final LazyStringWithUri mSecondDescription;
    public final LazyString mTitle;

    public static class Builder {
        public LazyString mButtonText;
        public String mCategory;
        public LazyStringWithUri mDescription;
        public LazyImage mImage;
        public String mKey;
        public LazyStringWithUri mSecondDescription;
        public LazyString mTitle;

        public StoreRoadblockModel build() {
            return new StoreRoadblockModel(this.mKey, this.mTitle, this.mDescription, this.mSecondDescription, this.mImage, this.mButtonText, this.mCategory);
        }

        public Builder setButtonText(int i) {
            this.mButtonText = new LazyString(i);
            return this;
        }

        public Builder setCategory(String str) {
            this.mCategory = str;
            return this;
        }

        public Builder setKey(String str) {
            this.mKey = str;
            return this;
        }

        public Builder setDescription(int i) {
            this.mDescription = new LazyStringWithUri(i, (Uri) null);
            return this;
        }

        public Builder setDescription(String str, Uri uri) {
            this.mDescription = new LazyStringWithUri(str, uri);
            return this;
        }

        public Builder setImage(int i) {
            this.mImage = new LazyImage(i);
            return this;
        }

        public Builder setImage(Section.Assets.AssetEdge.AssetNode assetNode) {
            Preconditions.checkArgument(Section.Assets.AssetEdge.AssetNode.AssetMediaType.IMAGE.toString().equals(assetNode.asset_media_type));
            boolean z = false;
            if (assetNode.asset_media.uri != null) {
                z = true;
            }
            Preconditions.checkArgument(z);
            Section.Assets.AssetEdge.AssetNode.AssetMedia assetMedia = assetNode.asset_media;
            String str = assetMedia.uri;
            float f = (float) assetMedia.height;
            float f2 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            if (f != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                f2 = ((float) assetMedia.width) / f;
            }
            this.mImage = new LazyImage(str, f2);
            return this;
        }

        public Builder setSecondDescription(int i) {
            this.mSecondDescription = new LazyStringWithUri(i, (Uri) null);
            return this;
        }

        public Builder setSecondDescription(String str, Uri uri) {
            this.mSecondDescription = new LazyStringWithUri(str, uri);
            return this;
        }

        public Builder setTitle(int i) {
            this.mTitle = new LazyString(i);
            return this;
        }

        public Builder setTitle(String str) {
            this.mTitle = new LazyString(str);
            return this;
        }
    }

    @Nullable
    public static StoreRoadblockModel from(Section.Assets assets, Section.Assets.AssetEdge.AssetNode.AssetMediaName assetMediaName, Section.Assets.AssetEdge.AssetNode.AssetMediaName assetMediaName2, Section.Assets.AssetEdge.AssetNode.AssetMediaName assetMediaName3, Section.Assets.AssetEdge.AssetNode.AssetMediaName assetMediaName4, String str) {
        ArrayList<Section.Assets.AssetEdge> arrayList;
        Uri uri;
        Section.Assets.AssetEdge.AssetNode assetNode;
        Section.Assets.AssetEdge.AssetNode.AssetMedia assetMedia;
        LazyStringWithUri lazyStringWithUri = null;
        LazyImage lazyImage = null;
        String str2 = null;
        Uri uri2 = null;
        if (!(assets == null || (arrayList = assets.edges) == null)) {
            Iterator<Section.Assets.AssetEdge> it = arrayList.iterator();
            Section.Assets.AssetEdge.AssetNode assetNode2 = null;
            Section.Assets.AssetEdge.AssetNode assetNode3 = null;
            Section.Assets.AssetEdge.AssetNode assetNode4 = null;
            Section.Assets.AssetEdge.AssetNode assetNode5 = null;
            while (it.hasNext()) {
                Section.Assets.AssetEdge next = it.next();
                if (!(next == null || (assetNode = next.node) == null)) {
                    if (assetNode2 == null && assetMediaName.name().toLowerCase().equals(assetNode.name) && Section.Assets.AssetEdge.AssetNode.AssetMediaType.DISPLAY_TEXT.toString().equals(assetNode.asset_media_type) && !Strings.isNullOrEmpty(assetNode.display_text)) {
                        assetNode2 = assetNode;
                    } else if (assetNode3 == null && assetMediaName2.name().toLowerCase().equals(assetNode.name) && Section.Assets.AssetEdge.AssetNode.AssetMediaType.DISPLAY_TEXT.toString().equals(assetNode.asset_media_type) && !Strings.isNullOrEmpty(assetNode.display_text)) {
                        assetNode3 = assetNode;
                    } else if (assetNode4 == null && assetMediaName3.name().toLowerCase().equals(assetNode.name) && Section.Assets.AssetEdge.AssetNode.AssetMediaType.DISPLAY_TEXT.toString().equals(assetNode.asset_media_type) && !Strings.isNullOrEmpty(assetNode.display_text)) {
                        assetNode4 = assetNode;
                    } else if (assetNode5 == null && assetMediaName4.name().toLowerCase().equals(assetNode.name) && Section.Assets.AssetEdge.AssetNode.AssetMediaType.IMAGE.toString().equals(assetNode.asset_media_type) && (assetMedia = assetNode.asset_media) != null && !Strings.isNullOrEmpty(assetMedia.uri)) {
                        assetNode5 = assetNode;
                    }
                }
            }
            if (!(assetNode2 == null || assetNode3 == null)) {
                String A00 = AnonymousClass0K2.A00(assetNode2.display_text, assetNode3.display_text);
                LazyString lazyString = new LazyString(assetNode2.display_text);
                String str3 = assetNode3.display_text;
                String str4 = assetNode3.display_text_uri;
                if (!Strings.isNullOrEmpty(str4)) {
                    uri = Uri.parse(str4);
                } else {
                    uri = null;
                }
                LazyStringWithUri lazyStringWithUri2 = new LazyStringWithUri(str3, uri);
                if (assetNode4 != null) {
                    String str5 = assetNode4.display_text;
                    String str6 = assetNode4.display_text_uri;
                    if (!Strings.isNullOrEmpty(str6)) {
                        uri2 = Uri.parse(str6);
                    }
                    lazyStringWithUri = new LazyStringWithUri(str5, uri2);
                }
                if (assetNode5 != null) {
                    Preconditions.checkArgument(Section.Assets.AssetEdge.AssetNode.AssetMediaType.IMAGE.toString().equals(assetNode5.asset_media_type));
                    boolean z = false;
                    if (assetNode5.asset_media.uri != null) {
                        z = true;
                    }
                    Preconditions.checkArgument(z);
                    Section.Assets.AssetEdge.AssetNode.AssetMedia assetMedia2 = assetNode5.asset_media;
                    String str7 = assetMedia2.uri;
                    float f = (float) assetMedia2.height;
                    float f2 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                    if (f != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                        f2 = ((float) assetMedia2.width) / f;
                    }
                    lazyImage = new LazyImage(str7, f2);
                }
                if (!Strings.isNullOrEmpty(str) && "GEAR_VR_CONTROLLER".equals(str)) {
                    str2 = str;
                }
                return new StoreRoadblockModel(A00, lazyString, lazyStringWithUri2, lazyStringWithUri, lazyImage, null, str2);
            }
        }
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public static boolean isValidAssetCategory(String str) {
        return "GEAR_VR_CONTROLLER".equals(str);
    }

    @Nullable
    public String getButtonText(Context context) {
        LazyString lazyString = this.mButtonText;
        if (lazyString != null) {
            return lazyString.get(context);
        }
        return null;
    }

    public String getDescriptionText(Context context) {
        return this.mDescription.mText.get(context);
    }

    @Nullable
    public Uri getDescriptionUri() {
        return this.mDescription.mUri;
    }

    public float getImageAspectRatio(Context context) {
        LazyImage lazyImage = this.mImage;
        if (lazyImage != null) {
            return lazyImage.getAspectRatio(context);
        }
        return AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    }

    @Nullable
    public String getImageUri() {
        LazyImage lazyImage = this.mImage;
        if (lazyImage != null) {
            return lazyImage.getUri();
        }
        return null;
    }

    @Nullable
    public String getSecondDescriptionText(Context context) {
        LazyStringWithUri lazyStringWithUri = this.mSecondDescription;
        if (lazyStringWithUri == null) {
            return null;
        }
        return lazyStringWithUri.mText.get(context);
    }

    @Nullable
    public Uri getSecondDescriptionUri() {
        LazyStringWithUri lazyStringWithUri = this.mSecondDescription;
        if (lazyStringWithUri == null) {
            return null;
        }
        return lazyStringWithUri.mUri;
    }

    public String getTitle(Context context) {
        return this.mTitle.get(context);
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

    public StoreRoadblockModel(Parcel parcel) {
        this.key = parcel.readString();
        this.mTitle = (LazyString) parcel.readParcelable(LazyString.class.getClassLoader());
        this.mDescription = (LazyStringWithUri) parcel.readParcelable(LazyStringWithUri.class.getClassLoader());
        this.mSecondDescription = (LazyStringWithUri) parcel.readParcelable(LazyStringWithUri.class.getClassLoader());
        this.mImage = (LazyImage) parcel.readParcelable(LazyImage.class.getClassLoader());
        this.mButtonText = (LazyString) parcel.readParcelable(LazyString.class.getClassLoader());
        this.category = parcel.readString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0008, code lost:
        if (r4 == null) goto L_0x000a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StoreRoadblockModel(java.lang.String r2, com.oculus.model.LazyString r3, com.oculus.model.LazyStringWithUri r4, com.oculus.model.LazyStringWithUri r5, com.oculus.model.LazyImage r6, com.oculus.model.LazyString r7, java.lang.String r8) {
        /*
            r1 = this;
            r1.<init>()
            if (r2 == 0) goto L_0x000a
            if (r3 == 0) goto L_0x000a
            r0 = 1
            if (r4 != 0) goto L_0x000b
        L_0x000a:
            r0 = 0
        L_0x000b:
            com.google.common.base.Preconditions.checkArgument(r0)
            r1.key = r2
            r1.mTitle = r3
            r1.mDescription = r4
            r1.mSecondDescription = r5
            r1.mImage = r6
            r1.mButtonText = r7
            r1.category = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.model.StoreRoadblockModel.<init>(java.lang.String, com.oculus.model.LazyString, com.oculus.model.LazyStringWithUri, com.oculus.model.LazyStringWithUri, com.oculus.model.LazyImage, com.oculus.model.LazyString, java.lang.String):void");
    }

    public static boolean isMalibuCategory(String str) {
        return "GEAR_VR_CONTROLLER".equals(str);
    }

    public boolean isMalibuCategory() {
        return "GEAR_VR_CONTROLLER".equals(this.category);
    }
}
