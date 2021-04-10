package com.oculus.horizon.api.common;

import java.util.ArrayList;
import javax.annotation.Nullable;

public class Section {
    @Nullable
    public Assets assets;
    public String content_type;
    public long end_time;
    public String id;
    public String layout;
    public String section_name;
    public boolean show_timer;
    public ArrayList<SortOption> sort_options;
    @Nullable
    public String style_theme;

    public static class Assets {
        public ArrayList<AssetEdge> edges;

        public static class AssetEdge {
            public AssetNode node;

            public static class AssetNode {
                public AssetMedia asset_media;
                public String asset_media_type;
                public String display_text;
                public String display_text_uri;
                public String name;

                public static class AssetMedia {
                    public int height;
                    public String uri;
                    public int width;
                }

                public enum AssetMediaName {
                    HORIZON_SECTION_HEADER,
                    HORIZON_SECTION_HEADER_PLACEHOLDER,
                    HORIZON_STORE_HEADER,
                    HORIZON_STORE_FOOTER,
                    HORIZON_ROADBLOCK_TITLE,
                    HORIZON_ROADBLOCK_DESCRIPTION,
                    HORIZON_ROADBLOCK_SUBDESCRIPTION,
                    HORIZON_ROADBLOCK_IMAGE
                }

                public enum AssetMediaType {
                    IMAGE,
                    VIDEO,
                    CUBEMAP,
                    DISPLAY_TEXT
                }
            }
        }
    }

    public static class SortOption {
        public String __typename;
        public String display_name;
        public boolean is_default;
        public boolean is_default_descending;
        public String option_name;
        public String reversed_sort_order;
        public String sort_order;
    }
}
