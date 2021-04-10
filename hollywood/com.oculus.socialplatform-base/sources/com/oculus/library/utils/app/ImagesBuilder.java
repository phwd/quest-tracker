package com.oculus.library.utils.app;

import com.oculus.library.model.Image;
import java.util.HashMap;
import java.util.Map;

public class ImagesBuilder {
    public static final int IMAGE_HERO_HEIGHT = 308;
    public static final int IMAGE_HERO_WIDTH = 1029;
    public static final int IMAGE_LANDSCAPE_HEIGHT = 120;
    public static final int IMAGE_LANDSCAPE_WIDTH = 360;
    public static final int IMAGE_MAIN_HEIGHT = 405;
    public static final int IMAGE_MAIN_WIDTH = 720;
    public static final int IMAGE_SQUARE_HEIGHT = 720;
    public static final int IMAGE_SQUARE_WIDTH = 720;
    public static final int IMAGE_TINY_HEIGHT = 203;
    public static final int IMAGE_TINY_WIDTH = 360;

    public static Map<Image.ImageName, Image> createImages(String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            Image.ImageName imageName = Image.ImageName.SOURCE_SQUARE;
            hashMap.put(imageName, new Image(imageName, str, 720, 720));
        }
        if (str2 != null) {
            Image.ImageName imageName2 = Image.ImageName.SOURCE_MAIN;
            hashMap.put(imageName2, new Image(imageName2, str2, 720, IMAGE_MAIN_HEIGHT));
        }
        if (str3 != null) {
            Image.ImageName imageName3 = Image.ImageName.SOURCE_TINY;
            hashMap.put(imageName3, new Image(imageName3, str3, 360, 203));
        }
        if (str4 != null) {
            Image.ImageName imageName4 = Image.ImageName.LANDSCAPE_SMALL;
            hashMap.put(imageName4, new Image(imageName4, str4, 360, 120));
        }
        if (str5 != null) {
            Image.ImageName imageName5 = Image.ImageName.HERO;
            hashMap.put(imageName5, new Image(imageName5, str5, IMAGE_HERO_WIDTH, 308));
        }
        return hashMap;
    }
}
