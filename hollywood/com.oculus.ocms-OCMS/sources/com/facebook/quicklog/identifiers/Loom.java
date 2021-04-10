package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class Loom {
    public static final int ACTIVE_BLACKBOX_TRIGGER_ID = 8126525;
    public static final int ACTIVE_PROVIDERS = 8126514;
    public static final int APPLICATION_STATE = 8126530;
    public static final int APP_VERSION_CODE = 8126518;
    public static final int APP_VERSION_NAME = 8126519;
    public static final int AVAILABLE_COUNTERS = 8126501;
    public static final int BLACKBOX_TRACE_REQUESTED = 8126528;
    public static final int BLACKBOX_TRACE_REQUEST_PROCESSING_FAILED = 8126543;
    public static final int BLACKBOX_TRACE_REQUEST_PROCESSING_FINISHED = 8126545;
    public static final int BLACKBOX_TRACE_REQUEST_PROCESSING_STARTED = 8126544;
    public static final int BLACKBOX_TRACE_STARTED = 8126523;
    public static final int CONFIG_ID = 8126470;
    public static final int CONNECTION_CLASS = 8126484;
    public static final int CPU_SAMPLING_INTERVAL_MS = 8126495;
    public static final int DEVICE_BRAND = 8126479;
    public static final int DEVICE_MAX_CPU_FREQUENCY = 8126503;
    public static final int DEVICE_TOTAL_MEMORY = 8126502;
    public static final int DEVICE_TYPE = 8126478;
    public static final int DEXOPT = 8126475;
    public static final int FRAME_DURATION = 8126515;
    public static final int FREE_DISK_PERCENT = 8126497;
    public static final int JEST_MOCK_CONFIG_APPLIED = 8129319;
    public static final int KERNEL_PERF_EVENTS = 8126490;
    public static final int KERNEL_VERSION = 8126527;
    public static final int LARGE_FRAME_DROP_UNCAPPED = 8126535;
    public static final int MANUAL_TRACE = 8126531;
    public static final int MANUFACTURER = 8126480;
    public static final int MMAP_TRACE_PROCESSING = 8126547;
    public static final short MODULE_ID = 124;
    public static final int NETWORK_SUBTYPE = 8126486;
    public static final int NETWORK_TYPE = 8126485;
    public static final int NOOP = 8126536;
    public static final int NUM_CONNECTED_PROCESSES = 8126500;
    public static final int OS_SDK = 8126537;
    public static final int OS_VER = 8126483;
    public static final int PERF_TEST_INFO = 8126489;
    public static final int PERSISTENT_BLACKBOX = 8126539;
    public static final int PROCESS_UPTIME = 8126505;
    public static final int PROF_ERR_SIG_CRASHES = 8126491;
    public static final int PROF_ERR_SLOT_MISSES = 8126492;
    public static final int PROF_ERR_STACK_OVERFLOWS = 8126493;
    public static final int REACT_BUNDLE_VERSION = 8126517;
    public static final int SKIPPED_FRAMES = 8126498;
    public static final int SYSTEM_AND_MONOTONIC_TIME_DIFF_MICROS = 8137900;
    public static final int TIME_SINCE_TTI = 8126538;
    public static final int TOTAL_FRAME_TIME_SPENT_UNCAPPED = 8126534;
    public static final int TOTAL_SKIPPED_FRAMES_UNCAPPED = 8126533;
    public static final int TRACE_ABORTED = 8126466;
    public static final int TRACE_CREATED = 8126465;
    public static final int TRACE_FILEMAN_ADDED_UPLOAD = 8126474;
    public static final int TRACE_FILEMAN_ERRORS = 8126471;
    public static final int TRACE_FILEMAN_TRIMMED_AGE = 8126473;
    public static final int TRACE_FILEMAN_TRIMMED_COUNT = 8126472;
    public static final int TRACE_FILEMAN_TRIMMED_LOGOUT = 8126477;
    public static final int TRACE_SESSION_ID = 8126546;
    public static final int TRACE_STARTED = 8126512;
    public static final int TRACE_STOPPED = 8126516;
    public static final int TRACE_UPLOAD = 8126469;
    public static final int UI_THREAD_RESPONSIVENESS = 8126506;
    public static final int VERIFIER = 8126494;
    public static final int VSYNC_TIME_NS = 8126532;
    public static final int YEAR_CLASS = 8126481;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "LOOM_TRACE_CREATED";
        }
        if (i == 2) {
            return "LOOM_TRACE_ABORTED";
        }
        if (i == 5) {
            return "LOOM_TRACE_UPLOAD";
        }
        if (i == 6) {
            return "loom_config";
        }
        if (i == 7) {
            return "LOOM_TRACE_FILEMAN_ERRORS";
        }
        if (i == 33) {
            return "LOOM_FREE_DISK_PERCENT";
        }
        if (i == 34) {
            return "LOOM_SKIPPED_FRAMES";
        }
        if (i == 41) {
            return "LOOM_PROCESS_UPTIME";
        }
        if (i == 42) {
            return "LOOM_UI_THREAD_RESPONSIVENESS";
        }
        if (i == 63) {
            return "LOOM_KERNEL_VERSION";
        }
        if (i == 64) {
            return "LOOM_BLACKBOX_TRACE_REQUESTED";
        }
        switch (i) {
            case 7:
                return "LOOM_TRACE_FILEMAN_ERRORS";
            case 8:
                return "LOOM_TRACE_FILEMAN_TRIMMED_COUNT";
            case 9:
                return "LOOM_TRACE_FILEMAN_TRIMMED_AGE";
            case 10:
                return "LOOM_TRACE_FILEMAN_ADDED_UPLOAD";
            case 11:
                return "LOOM_DEXOPT";
            case 48:
                return "LOOM_TRACE_STARTED";
            case 59:
                return "LOOM_BLACKBOX_TRACE_STARTED";
            case UL.id._UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_BINDING_ID /*{ENCODED_INT: 61}*/:
                return "LOOM_ACTIVE_BLACKBOX_TRIGGER_ID";
            case 66:
                return "LOOM_APPLICATION_STATE";
            case 67:
                return "LOOM_MANUAL_TRACE";
            case 68:
                return "LOOM_VSYNC_TIME_NS";
            case 69:
                return "LOOM_TOTAL_SKIPPED_FRAMES_UNCAPPED";
            case 70:
                return "LOOM_TOTAL_FRAME_TIME_SPENT_UNCAPPED";
            case 71:
                return "LOOM_LARGE_FRAME_DROP_UNCAPPED";
            case 72:
                return "LOOM_NOOP";
            case 73:
                return "LOOM_OS_SDK";
            case 74:
                return "LOOM_TIME_SINCE_TTI";
            case 75:
                return "LOOM_PERSISTENT_BLACKBOX";
            case 79:
                return "LOOM_BLACKBOX_TRACE_REQUEST_PROCESSING_FAILED";
            case 80:
                return "LOOM_BLACKBOX_TRACE_REQUEST_PROCESSING_STARTED";
            case 81:
                return "LOOM_BLACKBOX_TRACE_REQUEST_PROCESSING_FINISHED";
            case 82:
                return "LOOM_TRACE_SESSION_ID";
            case 83:
                return "LOOM_MMAP_TRACE_PROCESSING";
            case 2855:
                return "LOOM_JEST_MOCK_CONFIG_APPLIED";
            case 11436:
                return "LOOM_SYSTEM_AND_MONOTONIC_TIME_DIFF_MICROS";
            default:
                switch (i) {
                    case 13:
                        return "LOOM_TRACE_FILEMAN_TRIMMED_LOGOUT";
                    case 14:
                        return "LOOM_DEVICE_TYPE";
                    case 15:
                        return "LOOM_DEVICE_BRAND";
                    case 16:
                        return "LOOM_MANUFACTURER";
                    case 17:
                        return "LOOM_YEAR_CLASS";
                    default:
                        switch (i) {
                            case 19:
                                return "LOOM_OS_VER";
                            case 20:
                                return "LOOM_CONNECTION_CLASS";
                            case 21:
                                return "LOOM_NETWORK_TYPE";
                            case 22:
                                return "LOOM_NETWORK_SUBTYPE";
                            default:
                                switch (i) {
                                    case 25:
                                        return "LOOM_PERF_TEST_INFO";
                                    case 26:
                                        return "LOOM_KERNEL_PERF_EVENTS";
                                    case 27:
                                        return "LOOM_PROF_ERR_SIG_CRASHES";
                                    case 28:
                                        return "LOOM_PROF_ERR_SLOT_MISSES";
                                    case 29:
                                        return "LOOM_PROF_ERR_STACK_OVERFLOWS";
                                    case 30:
                                        return "LOOM_VERIFIER";
                                    case 31:
                                        return "CPU Sampling Interval (ms)";
                                    default:
                                        switch (i) {
                                            case 36:
                                                return "LOOM_NUM_CONNECTED_PROCESSES";
                                            case 37:
                                                return "LOOM_AVAILABLE_COUNTERS";
                                            case 38:
                                                return "LOOM_DEVICE_TOTAL_MEMORY";
                                            case 39:
                                                return "LOOM_DEVICE_MAX_CPU_FREQUENCY";
                                            default:
                                                switch (i) {
                                                    case 50:
                                                        return "LOOM_ACTIVE_PROVIDERS";
                                                    case 51:
                                                        return "LOOM_FRAME_DURATION";
                                                    case 52:
                                                        return "LOOM_TRACE_STOPPED";
                                                    case 53:
                                                        return "LOOM_REACT_BUNDLE_VERSION";
                                                    case 54:
                                                        return "App version code";
                                                    case 55:
                                                        return "App version";
                                                    default:
                                                        return "UNDEFINED_QPL_EVENT";
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
