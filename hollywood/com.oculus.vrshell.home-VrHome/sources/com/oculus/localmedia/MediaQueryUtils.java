package com.oculus.localmedia;

import java.util.StringTokenizer;

public class MediaQueryUtils {
    public static int parseMediaTypes(String mediaTypes) throws Exception {
        if (mediaTypes.isEmpty()) {
            throw new Exception("Media type has is mandatory and need to be specified.");
        }
        int types = 0;
        StringTokenizer tokenizer = new StringTokenizer(mediaTypes, ",");
        while (tokenizer.hasMoreTokens()) {
            types |= parseMediaType(tokenizer.nextToken()).getValue();
        }
        if (types != 0) {
            return types > 0 ? types : MediaType.all();
        }
        throw new Exception("Invalid media type was provided:" + mediaTypes);
    }

    public static MediaType parseMediaType(String mediaType) {
        MediaType[] values = MediaType.values();
        for (MediaType t : values) {
            if (t.toString().equalsIgnoreCase(mediaType)) {
                return t;
            }
        }
        return MediaType.UNKNOWN;
    }

    public static String serializeMediaTypes(int bitmask) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        MediaType[] values = MediaType.values();
        for (MediaType type : values) {
            if (type != MediaType.UNKNOWN && type.match(bitmask)) {
                if (!first) {
                    sb.append(",");
                }
                first = false;
                sb.append(type.toString());
            }
        }
        return sb.toString();
    }

    public static MediaSort parseSortType(String sortType) {
        if (sortType.equalsIgnoreCase("name")) {
            return MediaSort.NAME;
        }
        if (sortType.equalsIgnoreCase("date")) {
            return MediaSort.DATE;
        }
        if (sortType.equalsIgnoreCase("size")) {
            return MediaSort.SIZE;
        }
        if (sortType.equalsIgnoreCase("duration")) {
            return MediaSort.DURATION;
        }
        return MediaSort.NAME;
    }

    public static String normalizePath(String path) {
        if (path == null) {
            return path;
        }
        if (path.toLowerCase().startsWith("file://")) {
            path = path.substring(7, path.length());
        }
        if (path.startsWith("*") || path.startsWith("/")) {
            return path;
        }
        return "/" + path;
    }
}
