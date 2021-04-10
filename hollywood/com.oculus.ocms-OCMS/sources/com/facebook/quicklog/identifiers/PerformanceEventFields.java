package com.facebook.quicklog.identifiers;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PerformanceEventFields {
    public static final String ACTION_ID = "action_id";
    public static final String ALLOC_STALL = "allocstall";
    public static final String ANONYMOUS_RSS_AT_START = "anonymous_rss_at_start";
    public static final String ANONYMOUS_RSS_USED = "anonymous_rss_used";
    public static final String APP_INIT_MS = "app_init_ms";
    public static final String APP_STARTED_IN_BACKGROUND = "app_started_in_bg";
    public static final String AVAILABLE_DISK_SPACE_KB = "avail_disk_spc_kb";
    public static final String AVAILABLE_MEMORY = "avail_mem";
    public static final String AVAILABLE_MEMORY_DELTA = "avail_mem_delta";
    public static final String CLASS_LOADS_FAILED = "class_loads_failed";
    public static final String CLASS_LOAD_ATTEMPTS = "class_load_attempts";
    public static final String CONNECTION_QUALITY = "connqual";
    public static final String DATA_EVENT_LEVEL = "da_level";
    public static final String DATA_EVENT_TYPE = "da_type";
    public static final String DEX_QUERIES = "dex_queries";
    public static final String DIRTY_PSS_AT_START = "dirty_pss_at_start";
    public static final String DIRTY_PSS_USED = "dirty_pss_used";
    public static final String DURATION_MS = "duration_ms";
    public static final String DURATION_NS = "duration_ns";
    public static final String ERROR = "error";
    public static final String ERROR_TIMER_RESTARTED = "markerStart called multiple times without end or cancel";
    public static final String FIRST_FOREGROUND_MS = "first_foreground_ms";
    public static final String GC_MS = "gc_ms";
    public static final String INSTANCE_ID = "instance_id";
    public static final String INTERMEDIATE_POINTS = "points";
    public static final String IO_CANCELLED_WRITE_BYTES = "io_cancelledwb";
    public static final String IO_READ_BYTES = "io_readbytes";
    public static final String IO_READ_CHARS = "io_readchars";
    public static final String IO_READ_SYS_CALLS = "io_readsyscalls";
    public static final String IO_WRITE_BYTES = "io_writebytes";
    public static final String IO_WRITE_CHARS = "io_writechars";
    public static final String IO_WRITE_SYS_CALLS = "io_writesyscalls";
    public static final String JAVA_HEAP_AT_START = "java_heap_at_start";
    public static final String JAVA_HEAP_USED = "java_heap_used";
    public static final String JAVA_TO_JS_CALLS = "java_to_js_calls";
    public static final String JS_SCOPE_ENTERS = "js_scope_enters";
    public static final String JS_TO_JAVA_CALLS = "js_to_java_calls";
    public static final String LAST_FOREGROUND_MS = "last_foreground_ms";
    public static final String LITHO_COMPONENT_APPLIED_STATE_UPDATE_COUNTER = "litho_component_applied_state_update_counter";
    public static final String LITHO_COMPONENT_CALCULATE_LAYOUT_COUNTER = "litho_calculate_layout_counter";
    public static final String LITHO_COMPONENT_CALCULATE_LAYOUT_ON_UI_COUNTER = "litho_calculate_layout_on_ui_counter";
    public static final String LITHO_COMPONENT_MOUNT_COUNTER = "litho_mount_counter";
    public static final String LITHO_COMPONENT_TRIGGERED_ASYNC_STATE_UPDATE_COUNTER = "litho_component_triggered_async_state_update_counter";
    public static final String LITHO_COMPONENT_TRIGGERED_SYNC_STATE_UPDATE_COUNTER = "litho_component_triggered_sync_state_update_counter";
    public static final String LITHO_SECTION_APPLIED_STATE_UPDATE_COUNTER = "litho_section_applied_state_update_counter";
    public static final String LITHO_SECTION_CALCULATE_NEW_CHANGESET_COUNTER = "litho_section_calculate_new_changeset_counter";
    public static final String LITHO_SECTION_CALCULATE_NEW_CHANGESET_ON_UI_COUNTER = "litho_section_calculate_new_changeset_on_ui_counter";
    public static final String LITHO_SECTION_TRIGGERED_ASYNC_STATE_UPDATE_COUNTER = "litho_section_triggered_async_state_update_counter";
    public static final String LITHO_SECTION_TRIGGERED_SYNC_STATE_UPDATE_COUNTER = "litho_section_triggered_sync_state_update_counter";
    public static final String LOCATOR_ASSISTS = "locator_assists";
    public static final String LOOM_ID = "loom_id";
    public static final String LOW_MEMORY = "low_mem";
    public static final String LOW_POWER_STATE = "low_power_state";
    public static final String MARKER_ID = "marker_id";
    public static final String MARKER_TYPE = "marker_type";
    public static final String MARKER_TYPE_FROM_ACTION = "marker";
    public static final String METADATA = "metadata";
    public static final String METHOD = "method";
    public static final String MONOTONIC_TIME_SINCE_BOOT = "time_since_boot_ms";
    public static final String NATIVE_HEAP_AT_START = "native_heap_at_start";
    public static final String NATIVE_HEAP_USED = "native_heap_used";
    public static final String NETWORK_BYTES = "network_bytes";
    public static final String NETWORK_SUBTYPE = "network_subtype";
    public static final String NETWORK_TYPE = "network_type";
    public static final String PAGES_IN = "pages_in";
    public static final String PAGES_OUT = "pages_out";
    public static final String PAGE_STEALS = "pages_steals";
    public static final String PAGE_STEALS_SINCE_COLD_START = "page_steals_since_cold_start";
    public static final String PAGE_STEALS_SINCE_FOREGROUND = "page_steals_since_foreground";
    public static final String PERF_CPU_CLOCK = "perf_cpu_clock";
    public static final String PERF_TASK_CLOCK = "perf_task_clock";
    public static final String POINT_DATA = "data";
    public static final String POINT_NAME = "name";
    public static final String POINT_TIMESTAMP = "timeSinceStart";
    public static final String PRIVATE_DIRTY_AT_START = "private_dirty_at_start";
    public static final String PRIVATE_DIRTY_USED = "private_dirty_used";
    public static final String PROC_DELAYACCT_BLKIO_TICKS = "proc_delayacct_blkio_ticks";
    public static final String PSS_AT_START = "pss_at_start";
    public static final String PSS_USED = "pss_used";
    public static final String PS_CPU_MS = "ps_cpu_ms";
    public static final String PS_FLT = "ps_flt";
    public static final String PS_MIN_FLT = "ps_min_flt";
    public static final String RSS_AT_START = "rss_at_start";
    public static final String RSS_USED = "rss_used";
    public static final String SAMPLE_RATE = "sample_rate";
    public static final String SCENARIO = "scenario";
    public static final String STARTUP_STATUS = "startup_status";
    public static final String START_PRI = "start_pri";
    public static final String STOP_PRI = "stop_pri";
    public static final String TAG_NAME = "tag_name";
    public static final String TAG_VALUE = "tag_value";
    public static final String TERMINATION_STATUS = "status";
    public static final String TH_CPU_MS = "th_cpu_ms";
    public static final String TH_FLT = "th_flt";
    public static final String TIME_SINCE_APP_INIT_MS = "time_since_app_init_ms";
    public static final String TOTAL_MEMORY = "total_mem";
    public static final String TRACKED_FOR_LOSS = "tracked_for_loss";
    public static final String TTL_MS = "ttl_ms";
    public static final String UNIQUE_MARKER_ID = "unique_marker_id_debug_only";
    public static final String USER_INSTRUCTION_COUNT = "user_instruction_count";
    public static final String USER_KERNEL_INSTRUCTION_COUNT = "user_kernel_instruction_count";
    public static final String VALUE = "value";
    public static final String WRONG_DFA_GUESSES = "wrong_dfa_guesses";

    public static class EventType {
        public static final String PERF = "perf";
    }

    public static class MarkerType {
        public static final String CLIENT_CANCEL = "client_cancel";
        public static final String CLIENT_FAIL = "client_fail";
        public static final String CLIENT_TTI = "client_tti";
    }

    public static class Method {
        public static final String ALWAYS_ON = "always_on";
        public static final String MISSING_CONFIG = "missing_config";
        public static final String RANDOM_SAMPLING = "random_sampling";
    }

    public static class TerminationStatus {
        public static final String FAILED = "failed";
    }

    public static String getMarkerTypeString(short s) {
        return s == 3 ? MarkerType.CLIENT_FAIL : s == 4 ? MarkerType.CLIENT_CANCEL : MarkerType.CLIENT_TTI;
    }

    public static String getMethodString(boolean z, boolean z2) {
        return z ? Method.MISSING_CONFIG : z2 ? Method.ALWAYS_ON : Method.RANDOM_SAMPLING;
    }
}
