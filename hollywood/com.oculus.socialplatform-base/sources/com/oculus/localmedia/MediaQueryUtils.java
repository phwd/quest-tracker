package com.oculus.localmedia;

import X.AnonymousClass006;
import java.util.StringTokenizer;

public class MediaQueryUtils {
    public static String normalizePath(String str) {
        if (str == null) {
            return str;
        }
        if (str.toLowerCase().startsWith("file://")) {
            str = str.substring(7, str.length());
        }
        if (str.startsWith("*") || str.startsWith("/")) {
            return str;
        }
        return AnonymousClass006.A07("/", str);
    }

    public static MediaSort parseSortType(String str) {
        if (!str.equalsIgnoreCase("name")) {
            if (str.equalsIgnoreCase("date")) {
                return MediaSort.DATE;
            }
            if (str.equalsIgnoreCase("size")) {
                return MediaSort.SIZE;
            }
            if (str.equalsIgnoreCase("duration")) {
                return MediaSort.DURATION;
            }
        }
        return MediaSort.NAME;
    }

    public static String serializeMediaTypes(int i) {
        StringBuilder sb = new StringBuilder();
        MediaType[] values = MediaType.values();
        boolean z = true;
        for (MediaType mediaType : values) {
            if (mediaType != MediaType.UNKNOWN && mediaType.match(i)) {
                if (!z) {
                    sb.append(",");
                }
                sb.append(mediaType.toString());
                z = false;
            }
        }
        return sb.toString();
    }

    public static MediaType parseMediaType(String str) {
        MediaType[] values = MediaType.values();
        for (MediaType mediaType : values) {
            if (mediaType.toString().equalsIgnoreCase(str)) {
                return mediaType;
            }
        }
        return MediaType.UNKNOWN;
    }

    public static int parseMediaTypes(String str) throws Exception {
        if (!str.isEmpty()) {
            int i = 0;
            StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
            while (stringTokenizer.hasMoreTokens()) {
                i |= parseMediaType(stringTokenizer.nextToken()).getValue();
            }
            if (i == 0) {
                throw new Exception(AnonymousClass006.A07("Invalid media type was provided:", str));
            } else if (i <= 0) {
                return MediaType.all();
            } else {
                return i;
            }
        } else {
            throw new Exception("Media type has is mandatory and need to be specified.");
        }
    }
}
