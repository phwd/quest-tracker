package com.oculus.horizon.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.oculus.horizon.api.common.user.User;
import com.oculus.http.common.Image;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.library.model.HeadTracking;
import com.oculus.library.model.InputDevice;
import com.oculus.util.collection.CollectionUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import org.json.JSONArray;

@SingleEntryMapResponse
public class Item {
    public static final String STORE_APPLICATION_TYPE_NAME = "Application";
    public static final String STORE_BANNER_TYPE_NAME = "AppStoreBanner";
    public static final String STORE_BUNDLE_TYPE_NAME = "AppItemBundle";
    public static final String STORE_CINEMA_VIDEO_ITEM = "CinemaVideo";
    public static final String STORE_IN_APP_ITEM_TYPE_NAME = "IAPItem";
    public static final String STORE_IN_APP_RENTAL_ITEM_TYPE_NAME = "InAppRentalItem";
    public static final String UNKNOWN_TYPE_NAME = "UNKNOWN_TYPE_NAME";
    public String __typename;
    public AgeRating age_rating;
    public String app_viewer_id;
    public BannerTarget banner_target;
    public BundleItems bundle_items;
    public String category;
    public String category_name;
    public boolean chromecast_audio_enabled;
    @Nullable
    public CinemaVideo cinema_video;
    public String comfort_rating;
    public String comfort_rating_name;
    public Image cover_landscape_image;
    public Image cover_square_image;
    public AppStoreOffer current_offer;
    public Developer developer;
    public String developer_filtered_livestreaming_status;
    public String developer_name;
    public String developer_privacy_policy_url;
    public String developer_terms_of_service_url;
    public String display_long_description;
    public String display_name;
    public String display_short_description;
    @Nullable
    public Entitlement entitlement;
    @Nullable
    public String environment_path;
    @Nullable
    public BuyBoxSection extra_buy_box_section;
    public List<String> genre_names;
    public ApplicationGrouping grouping;
    public IARCCert iarc_cert;
    public String id;
    public String internet_connection;
    public String internet_connection_name;
    @Nullable
    public IsDemoOf is_demo_of;
    public boolean is_test;
    public boolean is_viewer_entitled;
    @Nullable
    public AndroidBinary latest_supported_binary;
    public boolean livestreaming_audio_hooking_enabled;
    public Metadata metadata_for_user;
    public String microphone_usage;
    public int min_recommended_version_code;
    public int min_required_version_code;
    public Organization organization;
    @Nullable
    public Item parent_application;
    public String platform;
    public String publisher_name;
    @Nullable
    public List<Item> purchasable_items;
    public float quality_rating_aggregate;
    public List<QualityRatingHistogram> quality_rating_histogram_aggregate;
    public List<QualityRating> quality_ratings_by_viewer;
    public int release_date;
    public InputDevice required_input_device;
    public List<Image> screenshots;
    public List<String> share_capabilities;
    public Image small_landscape_image;
    public List<StoreWideOffer> store_wide_offers;
    @Nullable
    public String style_theme;
    public String support_email;
    public ChangeLogs supported_binaries;
    public List<Language> supported_in_app_languages;
    public List<InputDevice> supported_input_devices_list;
    public List<String> supported_player_modes;
    public int term_in_sec;
    public Image thumbnail;
    public List<String> user_interaction_mode_names;
    public List<String> user_interaction_modes;
    public QualityRatings user_reviews;
    public QualityRatings user_reviews2;
    public Video video_trailer;
    public String website_url;

    public static class AgeRating {
        public String category_name;
        public List<Descriptor> descriptors;
    }

    public static class AgeRatingImage {
        public String uri;
    }

    public static class AppStoreOffer {
        public long end_time;
        public String id;
        public boolean is_offer_locked_by_payment_method;
        public Price price;
        public PriceWithoutProration price_without_proration;
        public String promo_benefit;
        public boolean show_timer;
        public Price strikethrough_price;
        public Viewer viewer;

        public static class Price {
            public String formatted;
            public int offset_amount;
        }

        public static class PriceWithoutProration {
            public String amount_with_offset;
        }

        public static class Viewer {
            public User user;

            public static class User {
                public PaymentAccount payment_account;
            }
        }
    }

    public static class BundleItems {
        public List<Item> nodes;
    }

    public static class BuyBoxSection {
        public String content;
        @Nullable
        public String link_text;
        @Nullable
        public String link_uri;
        public int position;
    }

    public static class ChangeLog {
        public String change_log;
        public int release_date;
        public String version;
    }

    public static class ChangeLogs {
        public List<ChangeLog> nodes;
    }

    public static class Descriptor {
        public String display_name;
    }

    public static class Developer {
        public String id;
        public String name;
    }

    public static class IARCCert {
        public String external_id;
        public IARCRating iarc_rating;
        public String product_name;
    }

    public static class IARCRating {
        public AgeRatingImage age_rating_image_large;
        public AgeRatingImage age_rating_image_small;
        public String age_rating_text;
        public ArrayList<String> descriptors;
        public String external_id;
        public ArrayList<String> interactive_elements;
        public String rating_definition_uri;
    }

    public static class IsDemoOf {
        public String id;
    }

    public static class Language {
        public String name;
        public String tag;
    }

    public static class Metadata {
        public Image poster_image;
    }

    public static class Organization {
        public String id;
        public String name;
    }

    public static class QualityRatings {
        public int count;
        public List<QualityRating> nodes;
        public PageInfo page_info;
    }

    public static class CinemaVideo {
        public String format;
        public List<Item> purchasable_items;
        public String rating;

        private enum Dimension {
            TWO_D,
            THREE_D
        }

        public boolean is3D() {
            String str = this.format;
            return str != null && str.startsWith(Dimension.THREE_D.toString());
        }

        public static boolean is3D(String str) {
            return str != null && str.startsWith(Dimension.THREE_D.toString());
        }
    }

    public static class ApplicationGrouping implements Parcelable {
        public static final Parcelable.Creator<ApplicationGrouping> CREATOR = new Parcelable.Creator<ApplicationGrouping>() {
            /* class com.oculus.horizon.api.common.Item.ApplicationGrouping.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public ApplicationGrouping createFromParcel(Parcel parcel) {
                return new ApplicationGrouping(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public ApplicationGrouping[] newArray(int i) {
                return new ApplicationGrouping[i];
            }
        };
        public boolean cloud_file_is_enabled;
        public IAPItems iap_items_2;
        public String id;

        public int describeContents() {
            return 0;
        }

        public static class IAPItems implements Parcelable {
            public static final Parcelable.Creator<IAPItems> CREATOR = new Parcelable.Creator<IAPItems>() {
                /* class com.oculus.horizon.api.common.Item.ApplicationGrouping.IAPItems.AnonymousClass1 */

                @Override // android.os.Parcelable.Creator
                public IAPItems createFromParcel(Parcel parcel) {
                    return new IAPItems(parcel);
                }

                @Override // android.os.Parcelable.Creator
                public IAPItems[] newArray(int i) {
                    return new IAPItems[i];
                }
            };
            public List<Node> nodes;

            public int describeContents() {
                return 0;
            }

            public static class Node implements Parcelable {
                public static final Parcelable.Creator<Node> CREATOR = new Parcelable.Creator<Node>() {
                    /* class com.oculus.horizon.api.common.Item.ApplicationGrouping.IAPItems.Node.AnonymousClass1 */

                    @Override // android.os.Parcelable.Creator
                    public Node createFromParcel(Parcel parcel) {
                        return new Node(parcel);
                    }

                    @Override // android.os.Parcelable.Creator
                    public Node[] newArray(int i) {
                        return new Node[i];
                    }
                };
                public String id;

                public int describeContents() {
                    return 0;
                }

                protected Node(Parcel parcel) {
                    this.id = parcel.readString();
                }

                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeString(this.id);
                }
            }

            protected IAPItems(Parcel parcel) {
                this.nodes = parcel.createTypedArrayList(Node.CREATOR);
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeTypedList(this.nodes);
            }
        }

        protected ApplicationGrouping(Parcel parcel) {
            this.id = parcel.readString();
            this.cloud_file_is_enabled = parcel.readInt() != 1 ? false : true;
            this.iap_items_2 = (IAPItems) parcel.readParcelable(IAPItems.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.id);
            parcel.writeInt(this.cloud_file_is_enabled ? 1 : 0);
            parcel.writeParcelable(this.iap_items_2, i);
        }
    }

    public static class QualityRatingHistogram implements Parcelable {
        public static final Parcelable.Creator<QualityRatingHistogram> CREATOR = new Parcelable.Creator<QualityRatingHistogram>() {
            /* class com.oculus.horizon.api.common.Item.QualityRatingHistogram.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public QualityRatingHistogram createFromParcel(Parcel parcel) {
                return new QualityRatingHistogram(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public QualityRatingHistogram[] newArray(int i) {
                return new QualityRatingHistogram[i];
            }
        };
        public int count;
        public int star_rating;

        public int describeContents() {
            return 0;
        }

        public QualityRatingHistogram(Parcel parcel) {
            this.count = parcel.readInt();
            this.star_rating = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.count);
            parcel.writeInt(this.star_rating);
        }
    }

    public static class QualityRating implements Parcelable {
        public static final Parcelable.Creator<QualityRating> CREATOR = new Parcelable.Creator<QualityRating>() {
            /* class com.oculus.horizon.api.common.Item.QualityRating.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public QualityRating createFromParcel(Parcel parcel) {
                return new QualityRating(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public QualityRating[] newArray(int i) {
                return new QualityRating[i];
            }
        };
        @Nullable
        public User author;
        public int author_timespent_pct;
        public int date;
        public DeveloperResponse developer_response;
        public String id;
        public String review_description;
        public int review_helpful_count;
        public String review_rank_by_viewer;
        public String review_title;
        public int score;

        public int describeContents() {
            return 0;
        }

        public enum Rank {
            SPAM("spam"),
            HELPFUL("helpful"),
            NOT_RANKED(null);
            
            public final String rankString;

            private Rank(String str) {
                this.rankString = str;
            }
        }

        private QualityRating(int i) {
            this.score = i;
        }

        protected QualityRating(Parcel parcel) {
            this.id = parcel.readString();
            this.author = (User) parcel.readParcelable(User.class.getClassLoader());
            this.date = parcel.readInt();
            this.score = parcel.readInt();
            this.review_title = parcel.readString();
            this.review_description = parcel.readString();
            this.developer_response = (DeveloperResponse) parcel.readParcelable(DeveloperResponse.class.getClassLoader());
            this.review_rank_by_viewer = parcel.readString();
            this.review_helpful_count = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.id);
            parcel.writeParcelable(this.author, i);
            parcel.writeInt(this.date);
            parcel.writeInt(this.score);
            parcel.writeString(this.review_title);
            parcel.writeString(this.review_description);
            parcel.writeParcelable(this.developer_response, i);
            parcel.writeString(this.review_rank_by_viewer);
            parcel.writeInt(this.review_helpful_count);
        }

        public boolean hasText() {
            return !TextUtils.isEmpty(this.review_title) || !TextUtils.isEmpty(this.review_description);
        }

        public Rank getRank() {
            String str = this.review_rank_by_viewer;
            if (str == null) {
                return Rank.NOT_RANKED;
            }
            try {
                return (Rank) Enum.valueOf(Rank.class, str.trim().toUpperCase(Locale.US));
            } catch (IllegalArgumentException unused) {
                return Rank.NOT_RANKED;
            }
        }

        @Nullable
        public static QualityRating from(int i) {
            if (i <= 0) {
                return null;
            }
            return new QualityRating(i);
        }
    }

    public static class DeveloperResponse implements Parcelable {
        public static final Parcelable.Creator<DeveloperResponse> CREATOR = new Parcelable.Creator<DeveloperResponse>() {
            /* class com.oculus.horizon.api.common.Item.DeveloperResponse.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public DeveloperResponse createFromParcel(Parcel parcel) {
                return new DeveloperResponse(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public DeveloperResponse[] newArray(int i) {
                return new DeveloperResponse[i];
            }
        };
        public String body;
        public int date;

        public int describeContents() {
            return 0;
        }

        protected DeveloperResponse(Parcel parcel) {
            this.date = parcel.readInt();
            this.body = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.date);
            parcel.writeString(this.body);
        }
    }

    public static class AndroidBinary implements Parcelable {
        public static final Parcelable.Creator<AndroidBinary> CREATOR = new Parcelable.Creator<AndroidBinary>() {
            /* class com.oculus.horizon.api.common.Item.AndroidBinary.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public AndroidBinary createFromParcel(Parcel parcel) {
                return new AndroidBinary(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public AndroidBinary[] newArray(int i) {
                return new AndroidBinary[i];
            }
        };
        public boolean can_use_external_storage;
        @Nullable
        public List<String> external_signatures;
        public HeadTracking head_tracking;
        public String id;
        @Nullable
        public ObbBinary obb_binary;
        public String package_name;
        public List<String> permissions;
        public long size;
        public int target_android_sdk_version;
        public String uri;
        public QualityRating user_quality_rating;
        public String version;
        public int version_code;

        public int describeContents() {
            return 0;
        }

        protected AndroidBinary(Parcel parcel) {
            this.id = parcel.readString();
            this.package_name = parcel.readString();
            this.version = parcel.readString();
            this.uri = parcel.readString();
            this.version_code = parcel.readInt();
            this.size = parcel.readLong();
            this.permissions = parcel.createStringArrayList();
            this.user_quality_rating = (QualityRating) parcel.readParcelable(QualityRating.class.getClassLoader());
            this.can_use_external_storage = parcel.readByte() != 0;
            this.obb_binary = (ObbBinary) parcel.readParcelable(ObbBinary.class.getClassLoader());
            this.external_signatures = parcel.createStringArrayList();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.id);
            parcel.writeString(this.package_name);
            parcel.writeString(this.version);
            parcel.writeString(this.uri);
            parcel.writeInt(this.version_code);
            parcel.writeLong(this.size);
            parcel.writeStringList(this.permissions);
            parcel.writeParcelable(this.user_quality_rating, i);
            parcel.writeInt(!this.can_use_external_storage ? 1 : 0);
            parcel.writeParcelable(this.obb_binary, i);
            parcel.writeStringList(this.external_signatures);
        }
    }

    public static class ObbBinary implements Parcelable {
        public static final Parcelable.Creator<ObbBinary> CREATOR = new Parcelable.Creator<ObbBinary>() {
            /* class com.oculus.horizon.api.common.Item.ObbBinary.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public ObbBinary createFromParcel(Parcel parcel) {
                return new ObbBinary(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public ObbBinary[] newArray(int i) {
                return new ObbBinary[i];
            }
        };
        public String id;
        public long size;
        public String uri;

        public int describeContents() {
            return 0;
        }

        protected ObbBinary(Parcel parcel) {
            this.id = parcel.readString();
            this.uri = parcel.readString();
            this.size = parcel.readLong();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.id);
            parcel.writeString(this.uri);
            parcel.writeLong(this.size);
        }
    }

    public static class StoreWideOffer implements Parcelable {
        public static final Parcelable.Creator<StoreWideOffer> CREATOR = new Parcelable.Creator<StoreWideOffer>() {
            /* class com.oculus.horizon.api.common.Item.StoreWideOffer.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public StoreWideOffer createFromParcel(Parcel parcel) {
                return new StoreWideOffer(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public StoreWideOffer[] newArray(int i) {
                return new StoreWideOffer[i];
            }
        };
        public String display_long_description;
        public String display_post_description;
        public String display_post_title;
        public String display_short_description;
        public String display_terms;
        public String display_title;
        public String id;

        public int describeContents() {
            return 0;
        }

        protected StoreWideOffer(Parcel parcel) {
            this.id = parcel.readString();
            this.display_title = parcel.readString();
            this.display_terms = parcel.readString();
            this.display_short_description = parcel.readString();
            this.display_long_description = parcel.readString();
            this.display_post_title = parcel.readString();
            this.display_post_description = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.id);
            parcel.writeString(this.display_title);
            parcel.writeString(this.display_terms);
            parcel.writeString(this.display_short_description);
            parcel.writeString(this.display_long_description);
            parcel.writeString(this.display_post_title);
            parcel.writeString(this.display_post_description);
        }
    }

    public String getPackageName() {
        Preconditions.checkArgument(isAppSupportedOnCurrentDevice(), "Item %s:%sis not supported on this device.", this.display_name, this.id);
        return this.latest_supported_binary.package_name;
    }

    @Nullable
    public String getExternalSignaturesAsJson() {
        Preconditions.checkArgument(isAppSupportedOnCurrentDevice(), "Item %s:%s is not supported on this device.", this.display_name, this.id);
        if (this.latest_supported_binary.external_signatures == null || this.latest_supported_binary.external_signatures.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (String str : this.latest_supported_binary.external_signatures) {
            jSONArray.put(str);
        }
        return jSONArray.toString();
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean isValidBasedOnType() {
        char c;
        String str = this.__typename;
        switch (str.hashCode()) {
            case -1994642069:
                if (str.equals(STORE_IN_APP_ITEM_TYPE_NAME)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1072845520:
                if (str.equals(STORE_APPLICATION_TYPE_NAME)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1021429229:
                if (str.equals(STORE_IN_APP_RENTAL_ITEM_TYPE_NAME)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -501166506:
                if (str.equals(STORE_BUNDLE_TYPE_NAME)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -390430036:
                if (str.equals(STORE_BANNER_TYPE_NAME)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 196282346:
                if (str.equals(STORE_CINEMA_VIDEO_ITEM)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return isValidApplication();
        }
        if (c == 1) {
            return isValidBanner();
        }
        if (c == 2) {
            return isValidAppBundle();
        }
        if (c == 3) {
            return isValidInAppItem();
        }
        if (c == 4) {
            return isValidInAppRentalItem();
        }
        if (c != 5) {
            return false;
        }
        return isValidCinemaVideo();
    }

    public boolean isValidApplication() {
        return STORE_APPLICATION_TYPE_NAME.equals(this.__typename) && isAppSupportedOnCurrentDevice();
    }

    @Nullable
    public String getEntitlementHash() {
        Entitlement entitlement2 = this.entitlement;
        if (entitlement2 == null) {
            return null;
        }
        return entitlement2.signed_token;
    }

    public long getLastUsed() {
        Entitlement entitlement2 = this.entitlement;
        if (entitlement2 == null) {
            return 0;
        }
        return entitlement2.last_used;
    }

    public boolean isAppSupportedOnCurrentDevice() {
        AndroidBinary androidBinary = this.latest_supported_binary;
        return (androidBinary == null || androidBinary.package_name == null) ? false : true;
    }

    public boolean isValidBanner() {
        return STORE_BANNER_TYPE_NAME.equals(this.__typename);
    }

    public boolean isValidAppBundle() {
        return STORE_BUNDLE_TYPE_NAME.equals(this.__typename);
    }

    public boolean isValidInAppItem() {
        return STORE_IN_APP_ITEM_TYPE_NAME.equals(this.__typename);
    }

    public boolean isValidInAppRentalItem() {
        return STORE_IN_APP_RENTAL_ITEM_TYPE_NAME.equals(this.__typename);
    }

    public boolean isValidCinemaVideo() {
        return STORE_CINEMA_VIDEO_ITEM.equals(this.__typename);
    }

    public Item getItemForUniversalSearch() {
        Metadata metadata;
        String str = this.__typename;
        if (((str.hashCode() == 196282346 && str.equals(STORE_CINEMA_VIDEO_ITEM)) ? (char) 0 : 65535) != 0) {
            return this;
        }
        Item item = CollectionUtil.isNotNullOrEmpty(this.purchasable_items) ? this.purchasable_items.get(0) : null;
        if (!(item == null || item.cover_landscape_image != null || (metadata = this.metadata_for_user) == null)) {
            item.cover_landscape_image = metadata.poster_image;
        }
        return item;
    }

    @Nullable
    public Item getOtherInAppItem() {
        CinemaVideo cinemaVideo = this.cinema_video;
        if (cinemaVideo != null && !CollectionUtil.isNullOrEmpty(cinemaVideo.purchasable_items) && this.cinema_video.purchasable_items.size() == 2) {
            Item item = this.cinema_video.purchasable_items.get(0);
            Item item2 = this.cinema_video.purchasable_items.get(1);
            if (!(item == null || item2 == null)) {
                if (item.id.equals(this.id)) {
                    return item2;
                }
                if (item2.id.equals(this.id)) {
                    return item;
                }
            }
        }
        return null;
    }

    public boolean isValidUnknownType() {
        return !STORE_APPLICATION_TYPE_NAME.equals(this.__typename) && !STORE_BANNER_TYPE_NAME.equals(this.__typename) && !STORE_BUNDLE_TYPE_NAME.equals(this.__typename) && !STORE_IN_APP_ITEM_TYPE_NAME.equals(this.__typename) && !STORE_IN_APP_RENTAL_ITEM_TYPE_NAME.equals(this.__typename) && this.__typename != null && this.display_name != null && this.cover_landscape_image != null && this.current_offer != null;
    }

    public List<ChangeLog> getChangeLogs() {
        ChangeLogs changeLogs = this.supported_binaries;
        if (changeLogs != null) {
            return changeLogs.nodes;
        }
        return ImmutableList.builder().build();
    }

    public String getSquareImageUri() {
        Image image = this.cover_square_image;
        if (image == null) {
            image = this.cover_landscape_image;
        }
        return image.uri;
    }

    public ImmutableList<QualityRating> getUserReviews() {
        QualityRatings qualityRatings = this.user_reviews2;
        if (qualityRatings == null || qualityRatings.nodes == null) {
            return new ImmutableList.Builder().build();
        }
        List<QualityRating> list = this.user_reviews2.nodes;
        AndroidBinary androidBinary = this.latest_supported_binary;
        if (androidBinary != null) {
            QualityRating qualityRating = androidBinary.user_quality_rating;
            if (list.isEmpty() && qualityRating != null && qualityRating.hasText()) {
                return ImmutableList.of(qualityRating);
            }
        }
        return new ImmutableList.Builder().addAll((Iterable) list).build();
    }

    public long getLatestApkAndObbSize() {
        return getLatestApkSize() + getLatestOBBSize();
    }

    @Nullable
    public String getLatestAPKUri() {
        AndroidBinary androidBinary = this.latest_supported_binary;
        if (androidBinary != null) {
            return androidBinary.uri;
        }
        return null;
    }

    @Nullable
    public String getLatestOBBUri() {
        AndroidBinary androidBinary = this.latest_supported_binary;
        if (androidBinary == null || androidBinary.obb_binary == null) {
            return null;
        }
        return this.latest_supported_binary.obb_binary.uri;
    }

    public boolean latestBinaryUseExternalStorage() {
        AndroidBinary androidBinary = this.latest_supported_binary;
        if (androidBinary != null) {
            return androidBinary.can_use_external_storage;
        }
        return false;
    }

    public long getLatestApkSize() {
        AndroidBinary androidBinary = this.latest_supported_binary;
        if (androidBinary == null) {
            return 0;
        }
        return androidBinary.size;
    }

    public long getLatestOBBSize() {
        AndroidBinary androidBinary = this.latest_supported_binary;
        if (androidBinary == null || androidBinary.obb_binary == null) {
            return 0;
        }
        return this.latest_supported_binary.obb_binary.size;
    }

    @Nullable
    public String getBinaryVersion() {
        AndroidBinary androidBinary = this.latest_supported_binary;
        if (androidBinary != null) {
            return androidBinary.version;
        }
        return null;
    }

    @Nullable
    public Integer getBinaryVersionCode() {
        AndroidBinary androidBinary = this.latest_supported_binary;
        if (androidBinary != null) {
            return Integer.valueOf(androidBinary.version_code);
        }
        return null;
    }

    @Nullable
    public Integer getTargetAndroidSDKVersion() {
        AndroidBinary androidBinary = this.latest_supported_binary;
        if (androidBinary != null) {
            return Integer.valueOf(androidBinary.target_android_sdk_version);
        }
        return null;
    }

    public List<String> getPermissions() {
        AndroidBinary androidBinary = this.latest_supported_binary;
        if (androidBinary != null) {
            return androidBinary.permissions;
        }
        return new ArrayList();
    }
}
