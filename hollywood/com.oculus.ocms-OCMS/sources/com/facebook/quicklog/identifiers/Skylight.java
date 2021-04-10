package com.facebook.quicklog.identifiers;

public class Skylight {
    public static final int APP_MOUSE_CLICK = 34209814;
    public static final int ASSETS_TREE_UPDATE = 34209820;
    public static final int ASSET_IMPORT = 34213779;
    public static final int ASSET_SUMMARY_UPDATE = 34209802;
    public static final int AUTO_SAVE_IOS = 34209799;
    public static final int BLOCK_EXTRACTION = 34215603;
    public static final int BLOCK_INSTANCE_SYNC = 34209801;
    public static final int COMPRESSION_WORKER = 34209832;
    public static final int CRASH_DUMP_CREATE = 34217413;
    public static final int DOCKING = 34221485;
    public static final int EDITOR_TO_ENGINE_SYNC = 34209826;
    public static final int EFFECT_EXPORT_SETUP = 34209818;
    public static final int ENGINE_TO_STUDIO_PROP_SYNC = 34209823;
    public static final int INSPECTOR_UPDATE = 34209819;
    public static final int IN_APP_TESTING = 34209796;
    public static final int LAYERS_TREE_UPDATE = 34209822;
    public static final int LOGIN = 34209829;
    public static final int MENU_ITEM_CLICK = 34209816;
    public static final int MIRRORING_TIME = 34209825;
    public static final short MODULE_ID = 522;
    public static final int OPEN_DOCUMENT = 34218566;
    public static final int PATCH_EDITOR_CONNECT_PORTS = 34220995;
    public static final int PATCH_EDITOR_CONN_INSERTED_PATCHES = 34213491;
    public static final int PATCH_EDITOR_LAYOUT = 34212364;
    public static final int PATCH_EDITOR_OPENING = 34209828;
    public static final int PATCH_EDITOR_PORT_DRAGGING = 34211052;
    public static final int PATCH_GRAPH_SYNC = 34209795;
    public static final int PATCH_LIBRARY_LOAD = 34209800;
    public static final int PROCESS_EXECUTION = 34216840;
    public static final int PROJECT_CREATE = 34209808;
    public static final int PROJECT_CREATE_TOTAL = 34209811;
    public static final int PROJECT_OPEN = 34209803;
    public static final int PROJECT_OPEN_TOTAL = 34209810;
    public static final int PROJECT_PACKAGE_OPEN = 34209805;
    public static final int PROJECT_PACKAGE_SAVE = 34209806;
    public static final int PROJECT_SAVE = 34209804;
    public static final int PROJECT_SAVE_AS = 34209807;
    public static final int PROJECT_SYNC_UNSAVED_CHANGES = 34218240;
    public static final int PROJECT_WINDOW_CREATE = 34209812;
    public static final int PROJECT_WINDOW_MANAGER_OPEN_DOCUMENT = 34214955;
    public static final int QML_WINDOW_CONTROLLER_CLOSING = 34224848;
    public static final int RENDER = 34209794;
    public static final int RENDER_SETUP = 34219578;
    public static final int SAMPLE_PROJECT_OPEN_TOTAL = 34209817;
    public static final int SCENE_TREE_UPDATE = 34209821;
    public static final int SCROLL_PERF = 34225246;
    public static final int STARTUP = 34209793;
    public static final int SYSTEM_RESOURCES = 34209824;
    public static final int TEMPLATE_IMAGE_DOWNLOAD = 34209831;
    public static final int TEMPLATE_PROJECT_DOWNLOAD = 34209830;
    public static final int TEXTURE_COMPRESSION = 34218314;
    public static final int UIDRIVER_GENERATE_HIERARCHY = 34218513;
    public static final int WELCOME_WINDOW_CREATE = 34209815;
    public static final int WINDOW_RENDER = 34216358;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "SKYLIGHT_STARTUP";
        }
        if (i == 2) {
            return "SKYLIGHT_RENDER";
        }
        if (i == 3) {
            return "SKYLIGHT_PATCH_GRAPH_SYNC";
        }
        if (i == 4) {
            return "SKYLIGHT_IN_APP_TESTING";
        }
        switch (i) {
            case 7:
                return "SKYLIGHT_AUTO_SAVE_IOS";
            case 8:
                return "SKYLIGHT_PATCH_LIBRARY_LOAD";
            case 9:
                return "SKYLIGHT_BLOCK_INSTANCE_SYNC";
            case 10:
                return "SKYLIGHT_ASSET_SUMMARY_UPDATE";
            case 11:
                return "SKYLIGHT_PROJECT_OPEN";
            case 12:
                return "SKYLIGHT_PROJECT_SAVE";
            case 13:
                return "SKYLIGHT_PROJECT_PACKAGE_OPEN";
            case 14:
                return "SKYLIGHT_PROJECT_PACKAGE_SAVE";
            case 15:
                return "SKYLIGHT_PROJECT_SAVE_AS";
            case 16:
                return "SKYLIGHT_PROJECT_CREATE";
            default:
                switch (i) {
                    case 18:
                        return "SKYLIGHT_PROJECT_OPEN_TOTAL";
                    case 19:
                        return "SKYLIGHT_PROJECT_CREATE_TOTAL";
                    case 20:
                        return "SKYLIGHT_PROJECT_WINDOW_CREATE";
                    default:
                        switch (i) {
                            case 22:
                                return "SKYLIGHT_APP_MOUSE_CLICK";
                            case 23:
                                return "SKYLIGHT_WELCOME_WINDOW_CREATE";
                            case 24:
                                return "SKYLIGHT_MENU_ITEM_CLICK";
                            case 25:
                                return "SKYLIGHT_SAMPLE_PROJECT_OPEN_TOTAL";
                            case 26:
                                return "SKYLIGHT_EFFECT_EXPORT_SETUP";
                            case 27:
                                return "SKYLIGHT_INSPECTOR_UPDATE";
                            case 28:
                                return "SKYLIGHT_ASSETS_TREE_UPDATE";
                            case 29:
                                return "SKYLIGHT_SCENE_TREE_UPDATE";
                            case 30:
                                return "SKYLIGHT_LAYERS_TREE_UPDATE";
                            case 31:
                                return "SKYLIGHT_ENGINE_TO_STUDIO_PROP_SYNC";
                            case 32:
                                return "SKYLIGHT_SYSTEM_RESOURCES";
                            case 33:
                                return "SKYLIGHT_MIRRORING_TIME";
                            case 34:
                                return "SKYLIGHT_EDITOR_TO_ENGINE_SYNC";
                            default:
                                switch (i) {
                                    case 36:
                                        return "SKYLIGHT_PATCH_EDITOR_OPENING";
                                    case 37:
                                        return "SKYLIGHT_LOGIN";
                                    case 38:
                                        return "SKYLIGHT_TEMPLATE_PROJECT_DOWNLOAD";
                                    case 39:
                                        return "SKYLIGHT_TEMPLATE_IMAGE_DOWNLOAD";
                                    case 40:
                                        return "SKYLIGHT_COMPRESSION_WORKER";
                                    default:
                                        switch (i) {
                                            case 1260:
                                                return "SKYLIGHT_PATCH_EDITOR_PORT_DRAGGING";
                                            case 2572:
                                                return "SKYLIGHT_PATCH_EDITOR_LAYOUT";
                                            case 3699:
                                                return "SKYLIGHT_PATCH_EDITOR_CONN_INSERTED_PATCHES";
                                            case 3987:
                                                return "SKYLIGHT_ASSET_IMPORT";
                                            case 5163:
                                                return "SKYLIGHT_PROJECT_WINDOW_MANAGER_OPEN_DOCUMENT";
                                            case 5811:
                                                return "SKYLIGHT_BLOCK_EXTRACTION";
                                            case 6566:
                                                return "SKYLIGHT_WINDOW_RENDER";
                                            case 7048:
                                                return "SKYLIGHT_PROCESS_EXECUTION";
                                            case 7621:
                                                return "SKYLIGHT_CRASH_DUMP_CREATE";
                                            case 8448:
                                                return "SKYLIGHT_PROJECT_SYNC_UNSAVED_CHANGES";
                                            case 8522:
                                                return "SKYLIGHT_TEXTURE_COMPRESSION";
                                            case 8721:
                                                return "SKYLIGHT_UIDRIVER_GENERATE_HIERARCHY";
                                            case 8774:
                                                return "SKYLIGHT_OPEN_DOCUMENT";
                                            case 9786:
                                                return "SKYLIGHT_RENDER_SETUP";
                                            case 11203:
                                                return "SKYLIGHT_PATCH_EDITOR_CONNECT_PORTS";
                                            case 11693:
                                                return "SKYLIGHT_DOCKING";
                                            case 15056:
                                                return "SKYLIGHT_QML_WINDOW_CONTROLLER_CLOSING";
                                            case 15454:
                                                return "SKYLIGHT_SCROLL_PERF";
                                            default:
                                                return "UNDEFINED_QPL_EVENT";
                                        }
                                }
                        }
                }
        }
    }
}
