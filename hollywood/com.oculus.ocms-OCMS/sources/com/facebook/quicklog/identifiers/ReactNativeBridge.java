package com.facebook.quicklog.identifiers;

public class ReactNativeBridge {
    public static final int CONVERT_CONSTANTS = 7798823;
    public static final int CORE = 7798828;
    public static final int CREATE_I18N_ASSETS_MODULE = 7798813;
    public static final int CREATE_I18N_MODULE_CONSTANTS = 7798812;
    public static final int CREATE_MOBILE_CONFIG_MODULE = 7798807;
    public static final int CREATE_MOBILE_CONFIG_MODULE_GET_METADATA = 7798809;
    public static final int CREATE_MODULE = 7798800;
    public static final int CREATE_UI_MANAGER_MODULE = 7798794;
    public static final int CREATE_UI_MANAGER_MODULE_CONSTANTS = 7798796;
    public static final int CREATE_VIEW_MANAGERS = 7798795;
    public static final int DESTROY_CATALYST_INSTANCE = 7798838;
    public static final int FABRIC_RENDER_TTI = 7798844;
    public static final int FABRIC_RENDER_TTRC = 7798843;
    public static final int FBREACT_BUILD_REACT_INSTANCE_MANAGER = 7798798;
    public static final int FBREACT_GET_REACT_INSTANCE_HOLDER_SPEC = 7798799;
    public static final int GET_CONSTANTS = 7798814;
    public static final int I18N_MODULE_CONSTANTS_CONVERT = 7798811;
    public static final int INITIALIZE_MODULE = 7798820;
    public static final int LOAD_RN_FABRIC_SO_FILE = 7798842;
    public static final int LOAD_RN_SO_FILE = 7798841;
    public static final short MODULE_ID = 119;
    public static final int NATIVE_MODULE_INITIALIZE = 7798792;
    public static final int NATIVE_MODULE_SETUP = 7798840;
    public static final int ON_HOST_PAUSE = 7798822;
    public static final int ON_HOST_RESUME = 7798821;
    public static final int PRE_RUN_APPLICATION = 7798831;
    public static final int PRE_SETUP_REACT_CONTEXT = 7798826;
    public static final int PROCESS_CORE_REACT_PACKAGE = 7798801;
    public static final int PROCESS_FB4A_INFRA_PACKAGE = 7798802;
    public static final int PROCESS_FB4A_PRODUCT_PACKAGE = 7798803;
    public static final int PROCESS_PACKAGES = 7798787;
    public static final int REGISTER_JS_SEGMENT = 7798839;
    public static final int RUN_JS_BUNDLE = 7798791;
    public static final int UNPACK_JS_BUNDLE = 7798836;
    public static final int UNPACK_JS_BUNDLE_EAGERLY = 7798837;

    public static String getMarkerName(int i) {
        if (i == 3) {
            return "REACT_NATIVE_BRIDGE_PROCESS_PACKAGES";
        }
        if (i == 23) {
            return "REACT_NATIVE_BRIDGE_CREATE_MOBILE_CONFIG_MODULE";
        }
        if (i == 25) {
            return "REACT_NATIVE_BRIDGE_CREATE_MOBILE_CONFIG_MODULE_GET_METADATA";
        }
        if (i == 42) {
            return "REACT_NATIVE_BRIDGE_PRE_SETUP_REACT_CONTEXT";
        }
        if (i == 44) {
            return "REACT_NATIVE_BRIDGE_CORE";
        }
        if (i == 47) {
            return "REACT_NATIVE_BRIDGE_PRE_RUN_APPLICATION";
        }
        if (i == 7) {
            return "REACT_NATIVE_BRIDGE_RUN_JS_BUNDLE";
        }
        if (i == 8) {
            return "REACT_NATIVE_BRIDGE_NATIVE_MODULE_INITIALIZE";
        }
        switch (i) {
            case 10:
                return "REACT_NATIVE_BRIDGE_CREATE_UI_MANAGER_MODULE";
            case 11:
                return "REACT_NATIVE_BRIDGE_CREATE_VIEW_MANAGERS";
            case 12:
                return "REACT_NATIVE_BRIDGE_CREATE_UI_MANAGER_MODULE_CONSTANTS";
            default:
                switch (i) {
                    case 14:
                        return "REACT_NATIVE_BRIDGE_FBREACT_BUILD_REACT_INSTANCE_MANAGER";
                    case 15:
                        return "REACT_NATIVE_BRIDGE_FBREACT_GET_REACT_INSTANCE_HOLDER_SPEC";
                    case 16:
                        return "REACT_NATIVE_BRIDGE_CREATE_MODULE";
                    case 17:
                        return "REACT_NATIVE_BRIDGE_PROCESS_CORE_REACT_PACKAGE";
                    case 18:
                        return "REACT_NATIVE_BRIDGE_PROCESS_FB4A_INFRA_PACKAGE";
                    case 19:
                        return "REACT_NATIVE_BRIDGE_PROCESS_FB4A_PRODUCT_PACKAGE";
                    default:
                        switch (i) {
                            case 27:
                                return "REACT_NATIVE_BRIDGE_I18N_MODULE_CONSTANTS_CONVERT";
                            case 28:
                                return "REACT_NATIVE_BRIDGE_CREATE_I18N_MODULE_CONSTANTS";
                            case 29:
                                return "REACT_NATIVE_BRIDGE_CREATE_I18N_ASSETS_MODULE";
                            case 30:
                                return "REACT_NATIVE_BRIDGE_GET_CONSTANTS";
                            default:
                                switch (i) {
                                    case 36:
                                        return "REACT_NATIVE_BRIDGE_INITIALIZE_MODULE";
                                    case 37:
                                        return "REACT_NATIVE_BRIDGE_ON_HOST_RESUME";
                                    case 38:
                                        return "REACT_NATIVE_BRIDGE_ON_HOST_PAUSE";
                                    case 39:
                                        return "REACT_NATIVE_BRIDGE_CONVERT_CONSTANTS";
                                    default:
                                        switch (i) {
                                            case 52:
                                                return "REACT_NATIVE_BRIDGE_UNPACK_JS_BUNDLE";
                                            case 53:
                                                return "REACT_NATIVE_BRIDGE_UNPACK_JS_BUNDLE_EAGERLY";
                                            case 54:
                                                return "REACT_NATIVE_BRIDGE_DESTROY_CATALYST_INSTANCE";
                                            case 55:
                                                return "REACT_NATIVE_BRIDGE_REGISTER_JS_SEGMENT";
                                            case 56:
                                                return "REACT_NATIVE_BRIDGE_NATIVE_MODULE_SETUP";
                                            case 57:
                                                return "REACT_NATIVE_BRIDGE_LOAD_RN_SO_FILE";
                                            case 58:
                                                return "REACT_NATIVE_BRIDGE_LOAD_RN_FABRIC_SO_FILE";
                                            case 59:
                                                return "REACT_NATIVE_BRIDGE_FABRIC_RENDER_TTRC";
                                            case 60:
                                                return "REACT_NATIVE_BRIDGE_FABRIC_RENDER_TTI";
                                            default:
                                                return "UNDEFINED_QPL_EVENT";
                                        }
                                }
                        }
                }
        }
    }
}
