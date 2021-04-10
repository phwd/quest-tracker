package com.facebook.quicklog.identifiers;

public class Memento {
    public static final int CORE_DESERIALIZE = 834095320;
    public static final int CORE_FETCH = 834082642;
    public static final int CORE_GET = 834080772;
    public static final int CORE_GET_ASYNC = 834091263;
    public static final int CORE_PUT = 834106884;
    public static final int CORE_REMOVE = 834088904;
    public static final int CORE_SERIALIZE = 834079716;
    public static final int MEM_ENTRY_REMOVED = 834095277;
    public static final int MEM_GET = 834084309;
    public static final int MEM_PUT = 834109179;
    public static final int MEM_REMOVE = 834087320;
    public static final int MEM_REMOVE_ALL = 834082393;
    public static final int MEM_SIZE = 834106317;
    public static final int MEM_SNAPSHOT = 834093735;
    public static final short MODULE_ID = 12727;
    public static final int PRELOAD_RECENT_FROM_STORAGE = 834085897;

    public static String getMarkerName(int i) {
        switch (i) {
            case 3044:
                return "MEMENTO_CORE_SERIALIZE";
            case 4100:
                return "MEMENTO_CORE_GET";
            case 5721:
                return "MEMENTO_MEM_REMOVE_ALL";
            case 5970:
                return "MEMENTO_CORE_FETCH";
            case 7637:
                return "MEMENTO_MEM_GET";
            case 9225:
                return "MEMENTO_PRELOAD_RECENT_FROM_STORAGE";
            case 10648:
                return "MEMENTO_MEM_REMOVE";
            case 12232:
                return "MEMENTO_CORE_REMOVE";
            case 14591:
                return "MEMENTO_CORE_GET_ASYNC";
            case 17063:
                return "MEMENTO_MEM_SNAPSHOT";
            case 18605:
                return "MEMENTO_MEM_ENTRY_REMOVED";
            case 18648:
                return "MEMENTO_CORE_DESERIALIZE";
            case 29645:
                return "MEMENTO_MEM_SIZE";
            case 30212:
                return "MEMENTO_CORE_PUT";
            case 32507:
                return "MEMENTO_MEM_PUT";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
