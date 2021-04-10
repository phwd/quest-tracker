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

    public static Map<Image.ImageName, Image> createImages(String squareImageUrl, String mainImageUrl, String tinyImageUrl, String landscapeSmallImageUri, String heroImageUri) {
        HashMap<Image.ImageName, Image> images = new HashMap<>();
        if (squareImageUrl != null) {
            images.put(Image.ImageName.SOURCE_SQUARE, new Image(Image.ImageName.SOURCE_SQUARE, squareImageUrl, 720, 720));
        }
        if (mainImageUrl != null) {
            images.put(Image.ImageName.SOURCE_MAIN, new Image(Image.ImageName.SOURCE_MAIN, mainImageUrl, 720, IMAGE_MAIN_HEIGHT));
        }
        if (tinyImageUrl != null) {
            images.put(Image.ImageName.SOURCE_TINY, new Image(Image.ImageName.SOURCE_TINY, tinyImageUrl, 360, IMAGE_TINY_HEIGHT));
        }
        if (landscapeSmallImageUri != null) {
            images.put(Image.ImageName.LANDSCAPE_SMALL, new Image(Image.ImageName.LANDSCAPE_SMALL, landscapeSmallImageUri, 360, IMAGE_LANDSCAPE_HEIGHT));
        }
        if (heroImageUri != null) {
            images.put(Image.ImageName.HERO, new Image(Image.ImageName.HERO, heroImageUri, IMAGE_HERO_WIDTH, 308));
        }
        return images;
    }
}
