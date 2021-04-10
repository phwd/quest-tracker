package com.facebook.voltron.common;

public interface AppModuleConstants {
    public static final String DOWNLOAD_ACTIVITY_CANCEL_REASON_BACK = "app_module_download_cancel_reason_back";
    public static final String DOWNLOAD_ACTIVITY_CANCEL_REASON_ERROR = "app_module_download_cancel_reason_error";
    public static final String EXTRA_APP_MODULE_MANAGER_PROVIDER = "app_module_manager_provider";
    public static final String EXTRA_COMPONENT_HELPER_NAME = "component_helper_name";
    public static final String EXTRA_DOWNLOAD_ACTIVITY_CANCEL_REASON = "app_module_download_cancel_reason";
    public static final String EXTRA_DOWNLOAD_ACTIVITY_ERROR = "app_module_download_error";
    public static final String EXTRA_DOWNLOAD_ACTIVITY_PROMPT_DELAY_MS = "prompt_delay";
    public static final String EXTRA_DOWNLOAD_REDIRECT = "app_module_download_redirect";
    public static final String EXTRA_EXECUTOR_SERVICE_FACTORY = "executor_service_factory";
    public static final String EXTRA_MODULE_DOWNLOAD_PREFERENCES_PROVIDER = "module_download_preferences_provider";
    public static final String EXTRA_MODULE_NAMES = "app_module_names";
    public static final String EXTRA_REDIRECT_COMPONENT_NAME = "redirect_component_name";
    public static final String EXTRA_REDIRECT_FRAGMENT_ID = "redirect_fragment_id";
    public static final String EXTRA_REDIRECT_INTENT = "redirect_intent";
    public static final int GOOGLE_PLAY_UNPACK_ERROR = -200;
    public static final String PREF_KEY_NEED_FALLBACK = "key::NeedFallback";
    public static final String PREF_KEY_PREV_DOWNLOAD_INIT = "key::PrevDownloadInit";
    public static final String PREF_NAME_INITIAL_INSTALL_REQUEST_TIMESTAMP = "AppModules::InitialInstallRequestTs-1";
    public static final String PREF_NAME_INITIAL_LOAD_TIMESTAMP = "AppModules::InitialLoadTimestamp";
    public static final String PREF_NAME_INITIAL_PREFETCH_TIME = "AppModules::InitialPrefetchTime";
    public static final String PREF_NAME_INSTALL_LATENCY = "AppModules::InstallLatency-1";
    public static final String PREF_NAME_LAST_LOAD_TIMESTAMP = "AppModules::LastLoadTimestamp";
    public static final String PREF_NAME_MODULE_PREV_DOWNLOAD = "AppModules::PrevDownload";
    public static final String PREF_NAME_MODULE_UNINSTALL = "AppModules::Uninstall";
    public static final String PREF_NAME_MODULE_UNINSTALL_INITIAL_REQUEST_TIME = "AppModules::UninstallInitialRequestTime";
    public static final String PREF_NAME_MODULE_UNINSTALL_LAST_FINISH_TIME = "AppModules::UninstallLastFinishTime";
    public static final String PREF_NAME_NEED_FALLBACK_DOWNLOAD = "AppModules::NeedToFallbackDownload";
}
