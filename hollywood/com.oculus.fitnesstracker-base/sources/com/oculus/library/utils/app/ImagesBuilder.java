package com.oculus.library.utils.app;

import com.oculus.library.model.Image;
import java.util.HashMap;
import java.util.Map;

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

    public static Map<Image.ImageName, Image> createImages(String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            hashMap.put(Image.ImageName.SOURCE_SQUARE, new Image(Image.ImageName.SOURCE_SQUARE, str, 720, 720));
        }
        if (str2 != null) {
            hashMap.put(Image.ImageName.SOURCE_MAIN, new Image(Image.ImageName.SOURCE_MAIN, str2, 720, IMAGE_MAIN_HEIGHT));
        }
        if (str3 != null) {
            hashMap.put(Image.ImageName.SOURCE_TINY, new Image(Image.ImageName.SOURCE_TINY, str3, 360, IMAGE_TINY_HEIGHT));
        }
        if (str4 != null) {
            hashMap.put(Image.ImageName.LANDSCAPE_SMALL, new Image(Image.ImageName.LANDSCAPE_SMALL, str4, 360, IMAGE_LANDSCAPE_HEIGHT));
        }
        if (str5 != null) {
            hashMap.put(Image.ImageName.HERO, new Image(Image.ImageName.HERO, str5, IMAGE_HERO_WIDTH, IMAGE_HERO_HEIGHT));
        }
        return hashMap;
    }
}
