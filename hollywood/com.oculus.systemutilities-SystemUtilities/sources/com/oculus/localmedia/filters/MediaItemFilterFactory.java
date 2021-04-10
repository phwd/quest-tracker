package com.oculus.localmedia.filters;

import java.util.StringTokenizer;

public class MediaItemFilterFactory {
    public static MediaItemFilter create(String filter) {
        if (filter == null) {
            return null;
        }
        StringTokenizer tokenizer = new StringTokenizer(filter.toLowerCase(), "!=<>");
        if (tokenizer.countTokens() != 2) {
            return null;
        }
        String name = tokenizer.nextToken();
        String value = tokenizer.nextToken();
        String operator = filter.substring(name.length());
        MediaItemFilterOperand operand = MediaItemFilterOperand.fromString(operator.substring(0, operator.length() - value.length()));
        if (name.equalsIgnoreCase("projection")) {
            return new ProjectionTypeFilter(operand, value);
        }
        if (name.equalsIgnoreCase("format")) {
            return new FormatFilter(operand, value);
        }
        if (name.equalsIgnoreCase("size")) {
            return new FileSizeFilter(operand, value);
        }
        if (name.equalsIgnoreCase("width")) {
            return new MediaWidthFilter(operand, value);
        }
        if (name.equalsIgnoreCase("height")) {
            return new MediaHeightFilter(operand, value);
        }
        if (name.equalsIgnoreCase("duration")) {
            return new DurationFilter(operand, value);
        }
        if (name.equalsIgnoreCase("date")) {
            return new DateFilter(operand, value);
        }
        if (name.equalsIgnoreCase("alpha")) {
            return new AlphaFilter(operand, value);
        }
        if (name.equalsIgnoreCase("stereo")) {
            return new StereoTypeFilter(operand, value);
        }
        if (name.equalsIgnoreCase("visibility")) {
            return new VisibilityFilter(operand, value);
        }
        if (name.equalsIgnoreCase("fov")) {
            return new FieldOfViewFilter(operand, value);
        }
        return null;
    }
}
