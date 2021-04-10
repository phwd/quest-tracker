package com.oculus.horizon.api.common;

import com.oculus.horizon.api.common.Section;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class BannerTarget {
    public static final String APP_STORE_PREFIX = "AppStore";
    public static final String SECTION_SUFFIX = "Section";
    public static final String STORE_WIDE_OFFER_TYPE_NAME = "StoreWideOffer";
    public String __typename;
    @Nullable
    public Section.Assets assets;
    public String content_type;
    public String display_long_description;
    public String display_post_description;
    public String display_post_title;
    public String display_short_description;
    public String display_terms;
    public String display_title;
    public long end_time;
    public String id;
    public String section_name;
    public boolean show_timer;
    public ArrayList<Section.SortOption> sort_options;
    @Nullable
    public String style_theme;

    public boolean isValidAppStoreCuratedSection() {
        return this.__typename.startsWith(APP_STORE_PREFIX) && this.__typename.endsWith(SECTION_SUFFIX);
    }

    public boolean isValidStoreWideOffer() {
        return STORE_WIDE_OFFER_TYPE_NAME.equals(this.__typename);
    }
}
