package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class Timeline {
    public static final int ADDITIONAL_CONTENT_RESTORATION_TIME = 1704003;
    public static final int COLLECTIONS_COLLECTION_HAS_PRELIM_DATA = 1703989;
    public static final int COLLECTIONS_COLLECTION_LOAD_COLLECTION = 1703984;
    public static final int COLLECTIONS_COLLECTION_LOAD_FIRST_FETCHED_ITEMS = 1703970;
    public static final int COLLECTIONS_COLLECTION_LOAD_PRELIM_DATA = 1703981;
    public static final int COLLECTIONS_COLLECTION_NO_PRELIM_DATA = 1703990;
    public static final int COLLECTIONS_SECTION_HAS_PRELIM_DATA = 1703987;
    public static final int COLLECTIONS_SECTION_LOAD_ALL_COLLECTIONS = 1703983;
    public static final int COLLECTIONS_SECTION_LOAD_PRELIM_DATA = 1703980;
    public static final int COLLECTIONS_SECTION_NO_PRELIM_DATA = 1703988;
    public static final int COLLECTIONS_SUMMARY_HAS_PRELIM_DATA = 1703985;
    public static final int COLLECTIONS_SUMMARY_LOAD_ALL_SECTIONS = 1703982;
    public static final int COLLECTIONS_SUMMARY_LOAD_FIRST_SECTIONS = 1703966;
    public static final int COLLECTIONS_SUMMARY_LOAD_PRELIM_DATA = 1703979;
    public static final int COLLECTIONS_SUMMARY_NO_PRELIM_DATA = 1703986;
    public static final int FETCH_STORIES = 1703997;
    public static final int LIFE_EVENT_TYPE_TTI = 1703973;
    public static final short MODULE_ID = 26;
    public static final int PROTILES_NETWORK_FETCH = 1703977;
    public static final int PROTILES_WAIT_TIME = 1703976;
    public static final int PROTILES_WAIT_TO_RENDER_TIME = 1704000;
    public static final int TIMELINE_CONTEXT_ITEMS_NETWORK_FETCH = 1703978;
    public static final int TIMELINE_FIRST_UNITS_NETWORK_FETCH = 1703952;
    public static final int TIMELINE_HEADER_TTI = 1703937;
    public static final int TIMELINE_INITIAL_FETCH_UNITS = 1703967;
    public static final int TIMELINE_INITIAL_UNITS_WAIT_TIME = 1703938;
    public static final int TIMELINE_LOAD_COVER_PHOTO = 1703960;
    public static final int TIMELINE_LOAD_COVER_PHOTO_LOW_RES = 1703959;
    public static final int TIMELINE_LOAD_FIRST_SECTION = 1703954;
    public static final int TIMELINE_LOAD_FIRST_SECTION_FROM_CACHE = 1703956;
    public static final int TIMELINE_LOAD_FIRST_SECTION_FROM_SERVER = 1703955;
    public static final int TIMELINE_LOAD_PROFILE_PIC = 1703961;
    public static final int TIMELINE_LOAD_PROFILE_PIC_PREVIEW = 1703958;
    public static final int TIMELINE_RENDER_CORE_HEADER = 1703941;
    public static final int TIMELINE_RENDER_ENTIRE_HEADER = 1703945;
    public static final int TIMELINE_RENDER_ENTIRE_HEADER_FROM_DISK_CACHE = 1703947;
    public static final int TIMELINE_RENDER_ENTIRE_HEADER_FROM_SERVER = 1703946;
    public static final int TIMELINE_RENDER_LOWRES_HEADER = 1703962;
    public static final int TIMELINE_RENDER_LOWRES_HEADER_COVERPHOTO_OPTIONAL = 1703963;
    public static final int TIMELINE_RENDER_LOWRES_HEADER_FROM_DISK_CACHE = 1703950;
    public static final int TIMELINE_RENDER_LOWRES_HEADER_FROM_SERVER = 1703949;
    public static final int TIMELINE_SCROLL_FETCH_UNITS = 1703968;
    public static final int TIMELINE_SPINNER_VISIBLE_FETCHING_STORIES = 1703999;
    public static final int TIMELINE_VISIBLE_SCROLL_FETCH_UNITS = 1703957;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "TIMELINE_TIMELINE_HEADER_TTI";
        }
        if (i == 2) {
            return "TIMELINE_TIMELINE_INITIAL_UNITS_WAIT_TIME";
        }
        if (i == 5) {
            return "TIMELINE_TIMELINE_RENDER_CORE_HEADER";
        }
        if (i == 16) {
            return "TIMELINE_TIMELINE_FIRST_UNITS_NETWORK_FETCH";
        }
        if (i == 34) {
            return "TIMELINE_COLLECTIONS_COLLECTION_LOAD_FIRST_FETCHED_ITEMS";
        }
        if (i == 37) {
            return "TIMELINE_LIFE_EVENT_TYPE_TTI";
        }
        if (i == 61) {
            return "TIMELINE_FETCH_STORIES";
        }
        if (i == 67) {
            return "Additional Content Restoration Time";
        }
        if (i == 13) {
            return "TIMELINE_TIMELINE_RENDER_LOWRES_HEADER_FROM_SERVER";
        }
        if (i == 14) {
            return "TIMELINE_TIMELINE_RENDER_LOWRES_HEADER_FROM_DISK_CACHE";
        }
        if (i == 63) {
            return "TIMELINE_TIMELINE_SPINNER_VISIBLE_FETCHING_STORIES";
        }
        if (i == 64) {
            return "TIMELINE_PROTILES_WAIT_TO_RENDER_TIME";
        }
        switch (i) {
            case 9:
                return "TIMELINE_TIMELINE_RENDER_ENTIRE_HEADER";
            case 10:
                return "TIMELINE_TIMELINE_RENDER_ENTIRE_HEADER_FROM_SERVER";
            case 11:
                return "TIMELINE_TIMELINE_RENDER_ENTIRE_HEADER_FROM_DISK_CACHE";
            default:
                switch (i) {
                    case 18:
                        return "TIMELINE_TIMELINE_LOAD_FIRST_SECTION";
                    case 19:
                        return "TIMELINE_TIMELINE_LOAD_FIRST_SECTION_FROM_SERVER";
                    case 20:
                        return "TIMELINE_TIMELINE_LOAD_FIRST_SECTION_FROM_CACHE";
                    case 21:
                        return "TIMELINE_TIMELINE_VISIBLE_SCROLL_FETCH_UNITS";
                    case 22:
                        return "TIMELINE_TIMELINE_LOAD_PROFILE_PIC_PREVIEW";
                    case 23:
                        return "TIMELINE_TIMELINE_LOAD_COVER_PHOTO_LOW_RES";
                    case 24:
                        return "TIMELINE_TIMELINE_LOAD_COVER_PHOTO";
                    case 25:
                        return "TIMELINE_TIMELINE_LOAD_PROFILE_PIC";
                    case 26:
                        return "TIMELINE_TIMELINE_RENDER_LOWRES_HEADER";
                    case 27:
                        return "TIMELINE_TIMELINE_RENDER_LOWRES_HEADER_COVERPHOTO_OPTIONAL";
                    default:
                        switch (i) {
                            case 30:
                                return "TIMELINE_COLLECTIONS_SUMMARY_LOAD_FIRST_SECTIONS";
                            case 31:
                                return "TIMELINE_TIMELINE_INITIAL_FETCH_UNITS";
                            case 32:
                                return "TIMELINE_TIMELINE_SCROLL_FETCH_UNITS";
                            default:
                                switch (i) {
                                    case 40:
                                        return "TIMELINE_PROTILES_WAIT_TIME";
                                    case 41:
                                        return "TIMELINE_PROTILES_NETWORK_FETCH";
                                    case 42:
                                        return "TIMELINE_TIMELINE_CONTEXT_ITEMS_NETWORK_FETCH";
                                    case 43:
                                        return "TIMELINE_COLLECTIONS_SUMMARY_LOAD_PRELIM_DATA";
                                    case 44:
                                        return "TIMELINE_COLLECTIONS_SECTION_LOAD_PRELIM_DATA";
                                    case 45:
                                        return "TIMELINE_COLLECTIONS_COLLECTION_LOAD_PRELIM_DATA";
                                    case 46:
                                        return "TIMELINE_COLLECTIONS_SUMMARY_LOAD_ALL_SECTIONS";
                                    case 47:
                                        return "TIMELINE_COLLECTIONS_SECTION_LOAD_ALL_COLLECTIONS";
                                    case 48:
                                        return "TIMELINE_COLLECTIONS_COLLECTION_LOAD_COLLECTION";
                                    case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                                        return "TIMELINE_COLLECTIONS_SUMMARY_HAS_PRELIM_DATA";
                                    case 50:
                                        return "TIMELINE_COLLECTIONS_SUMMARY_NO_PRELIM_DATA";
                                    case 51:
                                        return "TIMELINE_COLLECTIONS_SECTION_HAS_PRELIM_DATA";
                                    case 52:
                                        return "TIMELINE_COLLECTIONS_SECTION_NO_PRELIM_DATA";
                                    case 53:
                                        return "TIMELINE_COLLECTIONS_COLLECTION_HAS_PRELIM_DATA";
                                    case 54:
                                        return "TIMELINE_COLLECTIONS_COLLECTION_NO_PRELIM_DATA";
                                    default:
                                        return "UNDEFINED_QPL_EVENT";
                                }
                        }
                }
        }
    }
}
