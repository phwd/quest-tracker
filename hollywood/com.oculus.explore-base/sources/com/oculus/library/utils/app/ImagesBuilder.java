package com.oculus.library.utils.app;

import com.oculus.library.model.Image;
import java.util.HashMap;
import java.util.Map;

public class ImagesBuilder {
    public static Map<Image.ImageName, Image> createImages(String squareImageUrl, String mainImageUrl, String tinyImageUrl, String landscapeSmallImageUri, String heroImageUri) {
        HashMap<Image.ImageName, Image> images = new HashMap<>();
        if (squareImageUrl != null) {
            images.put(Image.ImageName.SOURCE_SQUARE, new Image(Image.ImageName.SOURCE_SQUARE, squareImageUrl, 720, 720));
        }
        if (mainImageUrl != null) {
            images.put(Image.ImageName.SOURCE_MAIN, new Image(Image.ImageName.SOURCE_MAIN, mainImageUrl, 720, 405));
        }
        if (tinyImageUrl != null) {
            images.put(Image.ImageName.SOURCE_TINY, new Image(Image.ImageName.SOURCE_TINY, tinyImageUrl, 360, 203));
        }
        if (landscapeSmallImageUri != null) {
            images.put(Image.ImageName.LANDSCAPE_SMALL, new Image(Image.ImageName.LANDSCAPE_SMALL, landscapeSmallImageUri, 360, 120));
        }
        if (heroImageUri != null) {
            images.put(Image.ImageName.HERO, new Image(Image.ImageName.HERO, heroImageUri, 1029, 308));
        }
        return images;
    }
}
