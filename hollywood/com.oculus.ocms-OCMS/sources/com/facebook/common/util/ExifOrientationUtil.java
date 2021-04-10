package com.facebook.common.util;

import com.facebook.common.fragmentconstants.FragmentConstants;
import com.google.common.collect.ImmutableBiMap;

public class ExifOrientationUtil {
    private static final ImmutableBiMap<ExifOrientation, Integer> EXIF_ORIENTATION_TO_ROTATION_MAP = ImmutableBiMap.builder().put((Object) ExifOrientation.NORMAL, (Object) 0).put((Object) ExifOrientation.ROTATE_90, (Object) 90).put((Object) ExifOrientation.ROTATE_180, (Object) 180).put((Object) ExifOrientation.ROTATE_270, (Object) Integer.valueOf((int) FragmentConstants.ContentFragmentType.GROUPS_STORY_DIVEIN_FRAGMENT)).build();
    private static final ImmutableBiMap<Integer, ExifOrientation> ROTATION_TO_EXIF_ORIENTATION_MAP = EXIF_ORIENTATION_TO_ROTATION_MAP.inverse();

    public static int getDegrees(ExifOrientation exifOrientation) {
        return getDegrees(exifOrientation, 0);
    }

    public static int getDegrees(ExifOrientation exifOrientation, int i) {
        Integer num = EXIF_ORIENTATION_TO_ROTATION_MAP.get(exifOrientation);
        return num == null ? i : num.intValue();
    }

    public static ExifOrientation getExifOrientation(int i) {
        return getExifOrientation(i, ExifOrientation.NORMAL);
    }

    public static ExifOrientation getExifOrientation(int i, ExifOrientation exifOrientation) {
        ExifOrientation exifOrientation2 = ROTATION_TO_EXIF_ORIENTATION_MAP.get(Integer.valueOf(i));
        return exifOrientation2 == null ? exifOrientation : exifOrientation2;
    }

    public static ExifOrientation getExifOrientationFromConfiguration(int i) {
        if (i == 1) {
            return ExifOrientation.NORMAL;
        }
        if (i == 2) {
            return ExifOrientation.FLIP_HORIZONTAL;
        }
        return ExifOrientation.UNDEFINED;
    }
}
