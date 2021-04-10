package com.oculus.horizon.api.common;

import java.util.List;
import javax.annotation.Nullable;

public class Images {
    @Nullable
    public List<Image> gallery;
    @Nullable
    public Image main;

    public class Image {
        @Nullable
        public ImageDetails medium;
        @Nullable
        public ImageDetails small;
        @Nullable
        public ImageDetails tiny;

        public Image() {
        }

        public class ImageDetails {
            public String imageUrl;

            public ImageDetails() {
            }
        }

        @Nullable
        public String getImageUrl() {
            ImageDetails imageDetails = this.medium;
            if (imageDetails == null) {
                return null;
            }
            return imageDetails.imageUrl;
        }
    }

    @Nullable
    public String getImageUrl() {
        Image image = this.main;
        if (image == null) {
            return null;
        }
        return image.getImageUrl();
    }

    @Nullable
    public String getThumbnailUrl() {
        Image.ImageDetails imageDetails;
        Image image = this.main;
        if (image == null || (imageDetails = image.small) == null) {
            return null;
        }
        return imageDetails.imageUrl;
    }

    @Nullable
    public String getTinyImageUrl() {
        Image.ImageDetails imageDetails;
        Image image = this.main;
        if (image == null || (imageDetails = image.tiny) == null) {
            return null;
        }
        return imageDetails.imageUrl;
    }
}
