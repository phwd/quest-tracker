package com.facebook.ultralight.names;

public class UltralightNames {
    public static String decodeFromJavaName(String str) {
        String[] split = str.replace("_ULCB_", "]").replace("_ULOB_", "[").replace("_ULGT_", ">").replace("_ULLT_", "<").replace("_ULCOMMA_", ",").split("_ULUNDERSCORE_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            sb.append(split[i].replace("_ULDOL_", "$").replace("_", "."));
            if (i < split.length - 1) {
                sb.append('_');
            }
        }
        return sb.toString();
    }
}
