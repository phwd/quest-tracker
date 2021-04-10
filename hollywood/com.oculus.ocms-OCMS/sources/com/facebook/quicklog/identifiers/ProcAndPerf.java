package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class ProcAndPerf {
    public static final int ALLOC_FREE_BYTES = 9240624;
    public static final int ALLOC_MAX_BYTES = 9240622;
    public static final int ALLOC_MMAP_BYTES = 9240621;
    public static final int ALLOC_TOTAL_BYTES = 9240623;
    public static final int ATTEMPTED_CLASS_LOADS = 9240583;
    public static final int BUFFER_MEM = 9240618;
    public static final int CONNECTED_PROCESSES = 9240638;
    public static final int CONTEXT_SWITCHES_INVOLUNTARY = 9240629;
    public static final int CONTEXT_SWITCHES_VOLUNTARY = 9240628;
    public static final int CPU_CORE_FREQUENCY = 9240642;
    public static final int DEX_FILE_QUERIES = 9240584;
    public static final int DISK_LATENCY_NS = 9240676;
    public static final int FREE_MEM = 9240616;
    public static final int GLOBAL_ALLOC_COUNT = 9240593;
    public static final int GLOBAL_ALLOC_SIZE = 9240594;
    public static final int GLOBAL_BLOCKING_GC_COUNT = 9240664;
    public static final int GLOBAL_BLOCKING_GC_TIME = 9240666;
    public static final int GLOBAL_GC_INVOCATION_COUNT = 9240595;
    public static final int GLOBAL_GC_TIME = 9240665;
    public static final int IOWAIT_COUNT = 9240630;
    public static final int IOWAIT_TIME = 9240631;
    public static final int JAVA_ALLOC_BYTES = 9240636;
    public static final int JAVA_FREE_BYTES = 9240634;
    public static final int JAVA_MAX_BYTES = 9240635;
    public static final int JAVA_TOTAL_BYTES = 9240637;
    public static final int LOADAVG_15M = 9240614;
    public static final int LOADAVG_1M = 9240612;
    public static final int LOADAVG_5M = 9240613;
    public static final int LOW_POWER_STATE = 9240611;
    public static final int MAX_CPU_CORE_FREQUENCY = 9240643;
    public static final int MEMINFO_ACTIVE = 9240674;
    public static final int MEMINFO_CACHED = 9240673;
    public static final int MEMINFO_DIRTY = 9240671;
    public static final int MEMINFO_FREE = 9240670;
    public static final int MEMINFO_INACTIVE = 9240675;
    public static final int MEMINFO_WRITEBACK = 9240672;
    public static final short MODULE_ID = 141;
    public static final int NUM_PROCS = 9240619;
    public static final int PROC_CONTEXT_SWITCHES_INVOLUNTARY = 9240658;
    public static final int PROC_CONTEXT_SWITCHES_VOLUNTARY = 9240659;
    public static final int PROC_CPU_TIME = 9240579;
    public static final int PROC_IOWAIT_COUNT = 9240656;
    public static final int PROC_IOWAIT_TIME = 9240657;
    public static final int PROC_KERNEL_CPU_TIME = 9240645;
    public static final int PROC_READ_BYTES = 9240661;
    public static final int PROC_STATM_RESIDENT = 9240669;
    public static final int PROC_STATM_SHARED = 9240668;
    public static final int PROC_SW_FAULTS_MAJOR = 9240580;
    public static final int PROC_SW_FAULTS_MINOR = 9240604;
    public static final int PROC_WRITE_BYTES = 9240663;
    public static final int PSS = 9240596;
    public static final int SHARED_MEM = 9240617;
    public static final int THREAD_CPU_NUM = 9240640;
    public static final int THREAD_CPU_TIME = 9240581;
    public static final int THREAD_KERNEL_CPU_TIME = 9240644;
    public static final int THREAD_PRIORITY = 9240667;
    public static final int THREAD_READ_BYTES = 9240660;
    public static final int THREAD_SW_FAULTS_MAJOR = 9240582;
    public static final int THREAD_SW_FAULTS_MINOR = 9240646;
    public static final int THREAD_WAIT_IN_RUNQUEUE_TIME = 9240626;
    public static final int THREAD_WRITE_BYTES = 9240662;
    public static final int TOTAL_MEM = 9240615;
    public static final int VMSTAT_ALLOCSTALL = 9240651;
    public static final int VMSTAT_KSWAPD_STEAL = 9240653;
    public static final int VMSTAT_NR_DIRTY = 9240647;
    public static final int VMSTAT_NR_FREE_PAGES = 9240655;
    public static final int VMSTAT_NR_WRITEBACK = 9240648;
    public static final int VMSTAT_PAGEOUTRUN = 9240652;
    public static final int VMSTAT_PGMAJFAULT = 9240654;
    public static final int VMSTAT_PGPGIN = 9240649;
    public static final int VMSTAT_PGPGOUT = 9240650;

    public static String getMarkerName(int i) {
        if (i == 28) {
            return "PROC_AND_PERF_PROC_SW_FAULTS_MINOR";
        }
        if (i == 50) {
            return "PROC_AND_PERF_THREAD_WAIT_IN_RUNQUEUE_TIME";
        }
        if (i == 64) {
            return "PROC_AND_PERF_THREAD_CPU_NUM";
        }
        switch (i) {
            case 3:
                return "PROC_AND_PERF_PROC_CPU_TIME";
            case 4:
                return "PROC_AND_PERF_PROC_SW_FAULTS_MAJOR";
            case 5:
                return "PROC_AND_PERF_THREAD_CPU_TIME";
            case 6:
                return "PROC_AND_PERF_THREAD_SW_FAULTS_MAJOR";
            case 7:
                return "PROC_AND_PERF_ATTEMPTED_CLASS_LOADS";
            case 8:
                return "PROC_AND_PERF_DEX_FILE_QUERIES";
            default:
                switch (i) {
                    case 17:
                        return "PROC_AND_PERF_GLOBAL_ALLOC_COUNT";
                    case 18:
                        return "PROC_AND_PERF_GLOBAL_ALLOC_SIZE";
                    case 19:
                        return "PROC_AND_PERF_GLOBAL_GC_INVOCATION_COUNT";
                    case 20:
                        return "PROC_AND_PERF_PSS";
                    default:
                        switch (i) {
                            case 35:
                                return "PROC_AND_PERF_LOW_POWER_STATE";
                            case 36:
                                return "PROC_AND_PERF_LOADAVG_1M";
                            case 37:
                                return "PROC_AND_PERF_LOADAVG_5M";
                            case 38:
                                return "PROC_AND_PERF_LOADAVG_15M";
                            case 39:
                                return "PROC_AND_PERF_TOTAL_MEM";
                            case 40:
                                return "PROC_AND_PERF_FREE_MEM";
                            case 41:
                                return "PROC_AND_PERF_SHARED_MEM";
                            case 42:
                                return "PROC_AND_PERF_BUFFER_MEM";
                            case 43:
                                return "PROC_AND_PERF_NUM_PROCS";
                            default:
                                switch (i) {
                                    case 45:
                                        return "PROC_AND_PERF_ALLOC_MMAP_BYTES";
                                    case 46:
                                        return "PROC_AND_PERF_ALLOC_MAX_BYTES";
                                    case 47:
                                        return "PROC_AND_PERF_ALLOC_TOTAL_BYTES";
                                    case 48:
                                        return "PROC_AND_PERF_ALLOC_FREE_BYTES";
                                    default:
                                        switch (i) {
                                            case 52:
                                                return "PROC_AND_PERF_CONTEXT_SWITCHES_VOLUNTARY";
                                            case 53:
                                                return "PROC_AND_PERF_CONTEXT_SWITCHES_INVOLUNTARY";
                                            case 54:
                                                return "PROC_AND_PERF_IOWAIT_COUNT";
                                            case 55:
                                                return "PROC_AND_PERF_IOWAIT_TIME";
                                            default:
                                                switch (i) {
                                                    case 58:
                                                        return "PROC_AND_PERF_JAVA_FREE_BYTES";
                                                    case 59:
                                                        return "PROC_AND_PERF_JAVA_MAX_BYTES";
                                                    case 60:
                                                        return "PROC_AND_PERF_JAVA_ALLOC_BYTES";
                                                    case UL.id._UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_BINDING_ID /*{ENCODED_INT: 61}*/:
                                                        return "PROC_AND_PERF_JAVA_TOTAL_BYTES";
                                                    case 62:
                                                        return "PROC_AND_PERF_CONNECTED_PROCESSES";
                                                    default:
                                                        switch (i) {
                                                            case 66:
                                                                return "PROC_AND_PERF_CPU_CORE_FREQUENCY";
                                                            case 67:
                                                                return "PROC_AND_PERF_MAX_CPU_CORE_FREQUENCY";
                                                            case 68:
                                                                return "PROC_AND_PERF_THREAD_KERNEL_CPU_TIME";
                                                            case 69:
                                                                return "PROC_AND_PERF_PROC_KERNEL_CPU_TIME";
                                                            case 70:
                                                                return "PROC_AND_PERF_THREAD_SW_FAULTS_MINOR";
                                                            case 71:
                                                                return "PROC_AND_PERF_VMSTAT_NR_DIRTY";
                                                            case 72:
                                                                return "PROC_AND_PERF_VMSTAT_NR_WRITEBACK";
                                                            case 73:
                                                                return "PROC_AND_PERF_VMSTAT_PGPGIN";
                                                            case 74:
                                                                return "PROC_AND_PERF_VMSTAT_PGPGOUT";
                                                            case 75:
                                                                return "PROC_AND_PERF_VMSTAT_ALLOCSTALL";
                                                            case 76:
                                                                return "PROC_AND_PERF_VMSTAT_PAGEOUTRUN";
                                                            case 77:
                                                                return "PROC_AND_PERF_VMSTAT_KSWAPD_STEAL";
                                                            case 78:
                                                                return "PROC_AND_PERF_VMSTAT_PGMAJFAULT";
                                                            case 79:
                                                                return "PROC_AND_PERF_VMSTAT_NR_FREE_PAGES";
                                                            case 80:
                                                                return "PROC_AND_PERF_PROC_IOWAIT_COUNT";
                                                            case 81:
                                                                return "PROC_AND_PERF_PROC_IOWAIT_TIME";
                                                            case 82:
                                                                return "PROC_AND_PERF_PROC_CONTEXT_SWITCHES_INVOLUNTARY";
                                                            case 83:
                                                                return "PROC_AND_PERF_PROC_CONTEXT_SWITCHES_VOLUNTARY";
                                                            case 84:
                                                                return "PROC_AND_PERF_THREAD_READ_BYTES";
                                                            case 85:
                                                                return "PROC_AND_PERF_PROC_READ_BYTES";
                                                            case 86:
                                                                return "PROC_AND_PERF_THREAD_WRITE_BYTES";
                                                            case 87:
                                                                return "PROC_AND_PERF_PROC_WRITE_BYTES";
                                                            case 88:
                                                                return "PROC_AND_PERF_GLOBAL_BLOCKING_GC_COUNT";
                                                            case 89:
                                                                return "PROC_AND_PERF_GLOBAL_GC_TIME";
                                                            case 90:
                                                                return "PROC_AND_PERF_GLOBAL_BLOCKING_GC_TIME";
                                                            case 91:
                                                                return "PROC_AND_PERF_THREAD_PRIORITY";
                                                            case 92:
                                                                return "PROC_AND_PERF_PROC_STATM_SHARED";
                                                            case 93:
                                                                return "PROC_AND_PERF_PROC_STATM_RESIDENT";
                                                            case 94:
                                                                return "PROC_AND_PERF_MEMINFO_FREE";
                                                            case 95:
                                                                return "PROC_AND_PERF_MEMINFO_DIRTY";
                                                            case 96:
                                                                return "PROC_AND_PERF_MEMINFO_WRITEBACK";
                                                            case 97:
                                                                return "PROC_AND_PERF_MEMINFO_CACHED";
                                                            case 98:
                                                                return "PROC_AND_PERF_MEMINFO_ACTIVE";
                                                            case 99:
                                                                return "PROC_AND_PERF_MEMINFO_INACTIVE";
                                                            case 100:
                                                                return "PROC_AND_PERF_DISK_LATENCY_NS";
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
}
