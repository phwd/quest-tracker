package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class NativeTemplates {
    public static final int ACTION = 12845109;
    public static final int ACTION_DELAYED = 12845110;
    public static final int ACTION_START = 12845111;
    public static final int ASYNC_ACTION = 12845113;
    public static final int CALCULATE_LAYOUT = 12845086;
    public static final int CALCULATE_LAYOUT_STATE = 12845098;
    public static final int COLLECT_RESULTS = 12845089;
    public static final int CREATE_LAYOUT = 12845087;
    public static final int CSS_LAYOUT = 12845088;
    public static final int DRAW = 12845099;
    public static final int FB_SUBSCRIPTION = 12859715;
    public static final int INIT_RANGE = 12845104;
    public static final int LAYOUT_STATE_FUTURE_GET_WAIT = 12845105;
    public static final int LITHO_RENDER = 12845076;
    public static final short MODULE_ID = 196;
    public static final int MOUNT = 12845091;
    public static final int MUTATE = 12845107;
    public static final int NT_BG_PARSE_WAIT = 12845103;
    public static final int NT_CONVERT_TEMPLATE = 12845085;
    public static final int NT_LITHO_CONVERTER = 12845100;
    public static final int NT_QPL_EXAMPLE_EVENT = 12845070;
    public static final int NT_SCREEN_TTI = 12845066;
    public static final int NT_VC_TTI = 12845071;
    public static final int PARSE = 12845059;
    public static final int PARSE_SINGLE_BUNDLE = 12845081;
    public static final int PRE_ALLOCATE_MOUNT_CONTENT = 12845092;
    public static final int RECENT_ACTIVITY_CALLSITE = 12845112;
    public static final int RENDER_PERF_FOR_MEASURE_RENDER_PERF_STYLE = 12845083;
    public static final int ROOMS_END_TO_END = 12850710;
    public static final int ROOMS_INIT_RANGE = 12857172;
    public static final int ROOMS_LAYOUT = 12852289;
    public static final int ROOMS_LAYOUT_STATE_FUTURE_GET_WAIT = 12853312;
    public static final int ROOMS_MOUNT = 12856427;
    public static final int ROOMS_PARSE = 12856613;
    public static final int ROOMS_POST_NETWORK = 12854073;
    public static final int SEARCH_BG_PARSE = 12850148;
    public static final int SEARCH_BG_PARSE_WAIT = 12856509;
    public static final int SEARCH_COMPONENT_CREATION = 12845084;
    public static final int SECTIONS_CREATE_NEW_TREE = 12845093;
    public static final int SECTIONS_DATA_DIFF_CALCULATE_DIFF = 12845094;
    public static final int SECTIONS_GENERATE_CHANGESET = 12845095;
    public static final int SECTIONS_ON_CREATE_CHILDREN = 12845096;
    public static final int SECTIONS_SET_ROOT = 12845097;
    public static final int SOFT_ERROR_ANDROID = 12845108;
    public static final int WOODHENGE_SUPPORTER_HUB_HSCROLL = 12845106;

    public static String getMarkerName(int i) {
        if (i == 14) {
            return "NATIVE_TEMPLATES_NT_QPL_EXAMPLE_EVENT";
        }
        if (i == 15) {
            return "NATIVE_TEMPLATES_NT_VC_TTI";
        }
        switch (i) {
            case 3:
                return "NATIVE_TEMPLATES_PARSE";
            case 10:
                return "NATIVE_TEMPLATES_NT_SCREEN_TTI";
            case 20:
                return "NATIVE_TEMPLATES_LITHO_RENDER";
            case 25:
                return "NATIVE_TEMPLATES_PARSE_SINGLE_BUNDLE";
            case 5092:
                return "NATIVE_TEMPLATES_SEARCH_BG_PARSE";
            case 5654:
                return "NATIVE_TEMPLATES_ROOMS_END_TO_END";
            case 7233:
                return "NATIVE_TEMPLATES_ROOMS_LAYOUT";
            case 8256:
                return "NATIVE_TEMPLATES_ROOMS_LAYOUT_STATE_FUTURE_GET_WAIT";
            case 9017:
                return "NATIVE_TEMPLATES_ROOMS_POST_NETWORK";
            case 11371:
                return "NATIVE_TEMPLATES_ROOMS_MOUNT";
            case 11453:
                return "NATIVE_TEMPLATES_SEARCH_BG_PARSE_WAIT";
            case 11557:
                return "NATIVE_TEMPLATES_ROOMS_PARSE";
            case 12116:
                return "NATIVE_TEMPLATES_ROOMS_INIT_RANGE";
            case 14659:
                return "NATIVE_TEMPLATES_FB_SUBSCRIPTION";
            default:
                switch (i) {
                    case 27:
                        return "NATIVE_TEMPLATES_RENDER_PERF_FOR_MEASURE_RENDER_PERF_STYLE";
                    case 28:
                        return "NATIVE_TEMPLATES_SEARCH_COMPONENT_CREATION";
                    case 29:
                        return "NATIVE_TEMPLATES_NT_CONVERT_TEMPLATE";
                    case 30:
                        return "NATIVE_TEMPLATES_CALCULATE_LAYOUT";
                    case 31:
                        return "NATIVE_TEMPLATES_CREATE_LAYOUT";
                    case 32:
                        return "NATIVE_TEMPLATES_CSS_LAYOUT";
                    case 33:
                        return "NATIVE_TEMPLATES_COLLECT_RESULTS";
                    default:
                        switch (i) {
                            case 35:
                                return "NATIVE_TEMPLATES_MOUNT";
                            case 36:
                                return "NATIVE_TEMPLATES_PRE_ALLOCATE_MOUNT_CONTENT";
                            case 37:
                                return "NATIVE_TEMPLATES_SECTIONS_CREATE_NEW_TREE";
                            case 38:
                                return "NATIVE_TEMPLATES_SECTIONS_DATA_DIFF_CALCULATE_DIFF";
                            case 39:
                                return "NATIVE_TEMPLATES_SECTIONS_GENERATE_CHANGESET";
                            case 40:
                                return "NATIVE_TEMPLATES_SECTIONS_ON_CREATE_CHILDREN";
                            case 41:
                                return "NATIVE_TEMPLATES_SECTIONS_SET_ROOT";
                            case 42:
                                return "NATIVE_TEMPLATES_CALCULATE_LAYOUT_STATE";
                            case 43:
                                return "NATIVE_TEMPLATES_DRAW";
                            case 44:
                                return "NATIVE_TEMPLATES_NT_LITHO_CONVERTER";
                            default:
                                switch (i) {
                                    case 47:
                                        return "NATIVE_TEMPLATES_NT_BG_PARSE_WAIT";
                                    case 48:
                                        return "NATIVE_TEMPLATES_INIT_RANGE";
                                    case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                                        return "NATIVE_TEMPLATES_LAYOUT_STATE_FUTURE_GET_WAIT";
                                    case 50:
                                        return "NATIVE_TEMPLATES_WOODHENGE_SUPPORTER_HUB_HSCROLL";
                                    case 51:
                                        return "NATIVE_TEMPLATES_MUTATE";
                                    case 52:
                                        return "NATIVE_TEMPLATES_SOFT_ERROR_ANDROID";
                                    case 53:
                                        return "NATIVE_TEMPLATES_ACTION";
                                    case 54:
                                        return "NATIVE_TEMPLATES_ACTION_DELAYED";
                                    case 55:
                                        return "NATIVE_TEMPLATES_ACTION_START";
                                    case 56:
                                        return "NATIVE_TEMPLATES_RECENT_ACTIVITY_CALLSITE";
                                    case 57:
                                        return "NATIVE_TEMPLATES_ASYNC_ACTION";
                                    default:
                                        return "UNDEFINED_QPL_EVENT";
                                }
                        }
                }
        }
    }
}
