package com.facebook.mobileconfigservice.serviceconstants;

import android.net.Uri;

public class MobileConfigServiceConstants {
    public static final String ACCESS_MOBILE_CONFIG_SERVICE_PERMISSION = "com.facebook.mobileconfigservice.fbpermission.ACCESS_MOBILE_CONFIG_SERVICE";
    public static final String AUTHORITY = "com.facebook.mobileconfigservice.contentprovider";
    public static final Uri CHANGE_LISTENER_PATH = Uri.withAppendedPath(CONTENT_URI, PATH_CHANGE_LISTENER);
    public static final Uri CHANGE_LISTENER_REMOVE_PATH = Uri.withAppendedPath(CONTENT_URI, PATH_CHANGE_LISTENER_REMOVE);
    public static final String COLUMN_GET_VALUE = "val";
    public static final int COLUMN_GET_VALUE_IDX = 0;
    public static final String COLUMN_GET_VALUE_SOURCE = "value_source";
    public static final int COLUMN_GET_VALUE_SOURCE_IDX = 1;
    public static final Uri CONTENT_URI = Uri.parse("content://com.facebook.mobileconfigservice.contentprovider");
    public static final Uri CONTENT_URI_GET_PATH = Uri.withAppendedPath(CONTENT_URI, PATH_GET);
    public static final Uri DEBUG_ONLY_GET_ALL_CONFIGS_URI = Uri.withAppendedPath(CONTENT_URI, DEBUG_ONLY_PATH_GET_ALL_CONFIGS);
    public static final Uri DEBUG_ONLY_OVERRIDE_CONFIGS_URI = Uri.withAppendedPath(CONTENT_URI, DEBUG_ONLY_PATH_OVERRIDE_CONFIGS);
    public static final String DEBUG_ONLY_PATH_GET_ALL_CONFIGS = "debug_only_get_all_configs";
    public static final String DEBUG_ONLY_PATH_OVERRIDE_CONFIGS = "debug_only_set_overridden_configs";
    public static final Uri GET_MULTIPLE_CONFIGS_URI = Uri.withAppendedPath(CONTENT_URI, PATH_GET_MULTIPLE_CONFIGS);
    public static final Uri LOG_EXPOSURE_URI = Uri.withAppendedPath(CONTENT_URI, PATH_LOG_EXPOSURE);
    public static final String PATH_CHANGE_LISTENER = "change";
    public static final String PATH_CHANGE_LISTENER_REMOVE = "change_remove";
    public static final String PATH_GET = "get";
    public static final String PATH_GET_MULTIPLE_CONFIGS = "get_multiple";
    public static final String PATH_LOG_EXPOSURE = "log";
    public static final String PATH_UPDATE_CONFIGS = "update";
    public static final String REQUEST_VALUE_SOURCE = "request_value_source";
    public static final Uri SUBSCRIBE_CALLBACK_EXCEPTION = Uri.withAppendedPath(CONTENT_URI, SUBSCRIBE_ERROR_EXCEPTION);
    public static final Uri SUBSCRIBE_CALLBACK_FAILED_TO_SAVE_URI = Uri.withAppendedPath(CONTENT_URI, SUBSCRIBE_ERROR_FAILED_TO_SAVE);
    public static final Uri SUBSCRIBE_CALLBACK_PARAMS_MAP_PARSE_FAILED_URI = Uri.withAppendedPath(CONTENT_URI, SUBSCRIBE_ERROR_PARAMS_MAP_PARSE_FAILED);
    public static final String SUBSCRIBE_CALLBACK_PATH = "subscribe";
    public static final Uri SUBSCRIBE_CALLBACK_SUCCESS_URI = Uri.withAppendedPath(CONTENT_URI, SUBSCRIBE_CALLBACK_PATH);
    public static final String SUBSCRIBE_ERROR_EXCEPTION = "exception";
    public static final String SUBSCRIBE_ERROR_FAILED_TO_SAVE = "failed_to_save";
    public static final String SUBSCRIBE_ERROR_PARAMS_MAP_PARSE_FAILED = "params_map_parse_fail";
    public static final String SUBSCRIBE_SUCCESS = "success";
    public static final Uri UPDATE_CONFIGS_URI = Uri.withAppendedPath(CONTENT_URI, "update");
}
