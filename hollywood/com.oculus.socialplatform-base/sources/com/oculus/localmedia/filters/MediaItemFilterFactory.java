package com.oculus.localmedia.filters;

import java.util.StringTokenizer;

public class MediaItemFilterFactory {
    public static MediaItemFilter create(String str) {
        if (str == null) {
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str.toLowerCase(), "!=<>");
        if (stringTokenizer.countTokens() != 2) {
            return null;
        }
        String nextToken = stringTokenizer.nextToken();
        String nextToken2 = stringTokenizer.nextToken();
        String substring = str.substring(nextToken.length());
        MediaItemFilterOperand fromString = MediaItemFilterOperand.fromString(substring.substring(0, substring.length() - nextToken2.length()));
        if (nextToken.equalsIgnoreCase("projection")) {
            return new ProjectionTypeFilter(fromString, nextToken2);
        }
        if (nextToken.equalsIgnoreCase("format")) {
            return new FormatFilter(fromString, nextToken2);
        }
        if (nextToken.equalsIgnoreCase("size")) {
            return new FileSizeFilter(fromString, nextToken2);
        }
        if (nextToken.equalsIgnoreCase("width")) {
            return new MediaWidthFilter(fromString, nextToken2);
        }
        if (nextToken.equalsIgnoreCase("height")) {
            return new MediaHeightFilter(fromString, nextToken2);
        }
        if (nextToken.equalsIgnoreCase("duration")) {
            return new DurationFilter(fromString, nextToken2);
        }
        if (nextToken.equalsIgnoreCase("date")) {
            return new DateFilter(fromString, nextToken2);
        }
        if (nextToken.equalsIgnoreCase("alpha")) {
            return new AlphaFilter(fromString, nextToken2);
        }
        if (nextToken.equalsIgnoreCase("stereo")) {
            return new StereoTypeFilter(fromString, nextToken2);
        }
        if (nextToken.equalsIgnoreCase("visibility")) {
            return new VisibilityFilter(fromString, nextToken2);
        }
        if (nextToken.equalsIgnoreCase("fov")) {
            return new FieldOfViewFilter(fromString, nextToken2);
        }
        return null;
    }
}
