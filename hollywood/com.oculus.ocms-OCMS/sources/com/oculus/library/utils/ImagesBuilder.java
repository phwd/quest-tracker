package com.oculus.library.utils;

import com.facebook.common.fragmentconstants.FragmentConstants;
import com.oculus.library.model.Image;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class ImagesBuilder {
    private static final int IMAGE_HERO_HEIGHT = 308;
    private static final int IMAGE_HERO_WIDTH = 1029;
    private static final int IMAGE_LANDSCAPE_HEIGHT = 120;
    private static final int IMAGE_LANDSCAPE_WIDTH = 360;
    private static final int IMAGE_MAIN_HEIGHT = 405;
    private static final int IMAGE_MAIN_WIDTH = 720;
    private static final int IMAGE_SQUARE_HEIGHT = 720;
    private static final int IMAGE_SQUARE_WIDTH = 720;
    private static final int IMAGE_TINY_HEIGHT = 203;
    private static final int IMAGE_TINY_WIDTH = 360;

    public static Map<Image.ImageName, Image> createImages(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            hashMap.put(Image.ImageName.SOURCE_SQUARE, new Image(Image.ImageName.SOURCE_SQUARE, str, FragmentConstants.ContentFragmentType.GROUP_MEMBERS_LIST_FRAGMENT, FragmentConstants.ContentFragmentType.GROUP_MEMBERS_LIST_FRAGMENT));
        }
        if (str2 != null) {
            hashMap.put(Image.ImageName.SOURCE_MAIN, new Image(Image.ImageName.SOURCE_MAIN, str2, FragmentConstants.ContentFragmentType.GROUP_MEMBERS_LIST_FRAGMENT, 405));
        }
        if (str3 != null) {
            hashMap.put(Image.ImageName.SOURCE_TINY, new Image(Image.ImageName.SOURCE_TINY, str3, FragmentConstants.ContentFragmentType.COMPARISON_CARDS_FRAGMENT, 203));
        }
        if (str4 != null) {
            hashMap.put(Image.ImageName.LANDSCAPE_SMALL, new Image(Image.ImageName.LANDSCAPE_SMALL, str4, FragmentConstants.ContentFragmentType.COMPARISON_CARDS_FRAGMENT, 120));
        }
        if (str5 != null) {
            hashMap.put(Image.ImageName.HERO, new Image(Image.ImageName.HERO, str5, IMAGE_HERO_WIDTH, 308));
        }
        return hashMap;
    }
}
