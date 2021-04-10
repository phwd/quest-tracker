package com.facebook.mobileconfigservice.serviceconstants;

import android.net.Uri;

public class MobileConfigServiceConstants {
    public static final Uri CHANGE_LISTENER_PATH = Uri.withAppendedPath(CONTENT_URI, "change");
    public static final Uri CHANGE_LISTENER_REMOVE_PATH = Uri.withAppendedPath(CONTENT_URI, "change_remove");
    public static final Uri CONTENT_URI = Uri.parse("content://com.facebook.mobileconfigservice.contentprovider");
    public static final Uri CONTENT_URI_GET_PATH = Uri.withAppendedPath(CONTENT_URI, "get");
    public static final Uri DEBUG_ONLY_GET_ALL_CONFIGS_URI = Uri.withAppendedPath(CONTENT_URI, "debug_only_get_all_configs");
    public static final Uri DEBUG_ONLY_OVERRIDE_CONFIGS_URI = Uri.withAppendedPath(CONTENT_URI, "debug_only_set_overridden_configs");
    public static final Uri DEBUG_ONLY_SERVICE_COMMAND_URI = Uri.withAppendedPath(CONTENT_URI, "debug_only_service_command");
    public static final Uri GET_MULTIPLE_CONFIGS_URI = Uri.withAppendedPath(CONTENT_URI, "get_multiple");
    public static final Uri LOG_EXPOSURE_URI = Uri.withAppendedPath(CONTENT_URI, "log");
    public static final Uri SUBSCRIBE_CALLBACK_EXCEPTION = Uri.withAppendedPath(CONTENT_URI, "exception");
    public static final Uri SUBSCRIBE_CALLBACK_FAILED_TO_SAVE_URI = Uri.withAppendedPath(CONTENT_URI, "failed_to_save");
    public static final Uri SUBSCRIBE_CALLBACK_PARAMS_MAP_PARSE_FAILED_URI = Uri.withAppendedPath(CONTENT_URI, "params_map_parse_fail");
    public static final Uri SUBSCRIBE_CALLBACK_SUCCESS_URI = Uri.withAppendedPath(CONTENT_URI, "subscribe");
    public static final Uri UPDATE_CONFIGS_URI = Uri.withAppendedPath(CONTENT_URI, "update");
}
