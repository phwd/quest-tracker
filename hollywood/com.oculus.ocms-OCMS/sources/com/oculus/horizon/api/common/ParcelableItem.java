package com.oculus.horizon.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.horizon.api.common.Item;
import com.oculus.http.common.Image;
import java.util.ArrayList;
import java.util.List;

public class ParcelableItem extends Item implements Parcelable {
    public static final Parcelable.Creator<ParcelableItem> CREATOR = new Parcelable.Creator<ParcelableItem>() {
        /* class com.oculus.horizon.api.common.ParcelableItem.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ParcelableItem createFromParcel(Parcel parcel) {
            return new ParcelableItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableItem[] newArray(int i) {
            return new ParcelableItem[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public ParcelableItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, Item.ApplicationGrouping applicationGrouping, Image image, Image image2, Image image3, Image image4, Item.AndroidBinary androidBinary, float f, List<Item.QualityRatingHistogram> list, List<Item.QualityRating> list2, boolean z, String str8, String str9, boolean z2, boolean z3, String str10, int i, int i2) {
        this.__typename = str;
        this.id = str2;
        this.display_name = str3;
        this.display_long_description = str4;
        this.comfort_rating = str5;
        this.category = str6;
        this.category_name = str7;
        this.grouping = applicationGrouping;
        this.cover_square_image = image;
        this.cover_landscape_image = image2;
        this.thumbnail = image3;
        this.small_landscape_image = image4;
        this.latest_supported_binary = androidBinary;
        this.quality_rating_aggregate = f;
        this.quality_rating_histogram_aggregate = list;
        this.quality_ratings_by_viewer = list2;
        this.is_viewer_entitled = z;
        this.app_viewer_id = str8;
        this.developer_filtered_livestreaming_status = str9;
        this.livestreaming_audio_hooking_enabled = z2;
        this.chromecast_audio_enabled = z3;
        this.microphone_usage = str10;
        this.min_recommended_version_code = i;
        this.min_required_version_code = i2;
    }

    public ParcelableItem(Parcel parcel) {
        this.__typename = parcel.readString();
        this.id = parcel.readString();
        this.display_name = parcel.readString();
        this.display_long_description = parcel.readString();
        this.comfort_rating = parcel.readString();
        this.category = parcel.readString();
        this.category_name = parcel.readString();
        this.grouping = (Item.ApplicationGrouping) parcel.readParcelable(Item.ApplicationGrouping.class.getClassLoader());
        this.cover_square_image = (Image) parcel.readParcelable(Image.class.getClassLoader());
        this.cover_landscape_image = (Image) parcel.readParcelable(Image.class.getClassLoader());
        this.thumbnail = (Image) parcel.readParcelable(Image.class.getClassLoader());
        this.small_landscape_image = (Image) parcel.readParcelable(Image.class.getClassLoader());
        this.latest_supported_binary = (Item.AndroidBinary) parcel.readParcelable(Item.AndroidBinary.class.getClassLoader());
        this.quality_rating_aggregate = parcel.readFloat();
        this.quality_rating_histogram_aggregate = new ArrayList();
        parcel.readList(this.quality_rating_histogram_aggregate, Item.QualityRatingHistogram.class.getClassLoader());
        this.quality_ratings_by_viewer = new ArrayList();
        parcel.readList(this.quality_ratings_by_viewer, Item.QualityRating.class.getClassLoader());
        boolean z = false;
        this.is_viewer_entitled = parcel.readByte() != 0;
        this.app_viewer_id = parcel.readString();
        this.developer_filtered_livestreaming_status = parcel.readString();
        this.livestreaming_audio_hooking_enabled = parcel.readByte() == 1;
        this.chromecast_audio_enabled = parcel.readByte() == 1 ? true : z;
        this.microphone_usage = parcel.readString();
        this.min_recommended_version_code = parcel.readInt();
        this.min_required_version_code = parcel.readInt();
    }

    public static ParcelableItem from(Item item) {
        return new ParcelableItem(item.__typename, item.id, item.display_name, item.display_long_description, item.comfort_rating, item.category, item.category_name, item.grouping, item.cover_square_image, item.cover_landscape_image, item.thumbnail, item.small_landscape_image, item.latest_supported_binary, item.quality_rating_aggregate, item.quality_rating_histogram_aggregate, item.quality_ratings_by_viewer, item.is_viewer_entitled, item.app_viewer_id, item.developer_filtered_livestreaming_status, item.livestreaming_audio_hooking_enabled, item.chromecast_audio_enabled, item.microphone_usage, item.min_recommended_version_code, item.min_required_version_code);
    }

    public static ParcelableItem from(InAppItem inAppItem) {
        return new ParcelableItem(inAppItem.__typename, inAppItem.id, inAppItem.display_name, inAppItem.display_long_description, null, null, null, null, null, inAppItem.cover_landscape_image, null, null, null, 0.0f, null, null, false, null, null, false, false, null, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.__typename);
        parcel.writeString(this.id);
        parcel.writeString(this.display_name);
        parcel.writeString(this.display_long_description);
        parcel.writeString(this.comfort_rating);
        parcel.writeString(this.category);
        parcel.writeString(this.category_name);
        parcel.writeParcelable(this.grouping, i);
        parcel.writeParcelable(this.cover_square_image, i);
        parcel.writeParcelable(this.cover_landscape_image, i);
        parcel.writeParcelable(this.thumbnail, i);
        parcel.writeParcelable(this.small_landscape_image, i);
        parcel.writeParcelable(this.latest_supported_binary, i);
        parcel.writeFloat(this.quality_rating_aggregate);
        parcel.writeList(this.quality_rating_histogram_aggregate);
        parcel.writeList(this.quality_ratings_by_viewer);
        parcel.writeByte(this.is_viewer_entitled ? (byte) 1 : 0);
        parcel.writeString(this.app_viewer_id);
        parcel.writeString(this.developer_filtered_livestreaming_status);
        parcel.writeByte(this.livestreaming_audio_hooking_enabled ? (byte) 1 : 0);
        parcel.writeByte(this.chromecast_audio_enabled ? (byte) 1 : 0);
        parcel.writeString(this.microphone_usage);
        parcel.writeInt(this.min_recommended_version_code);
        parcel.writeInt(this.min_required_version_code);
    }
}
