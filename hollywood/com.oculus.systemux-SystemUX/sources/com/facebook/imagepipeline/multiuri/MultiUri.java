package com.facebook.imagepipeline.multiuri;

import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

public class MultiUri {
    @Nullable
    private ImageRequest mHighResImageRequest;
    @Nullable
    private ImageRequest mLowResImageRequest;
    @Nullable
    private ImageRequest[] mMultiImageRequests;

    private MultiUri(Builder builder) {
        this.mLowResImageRequest = builder.mLowResImageRequest;
        this.mHighResImageRequest = builder.mHighResImageRequest;
        this.mMultiImageRequests = builder.mMultiImageRequests;
    }

    @Nullable
    public ImageRequest getLowResImageRequest() {
        return this.mLowResImageRequest;
    }

    @Nullable
    public ImageRequest getHighResImageRequest() {
        return this.mHighResImageRequest;
    }

    @Nullable
    public ImageRequest[] getMultiImageRequests() {
        return this.mMultiImageRequests;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        @Nullable
        private ImageRequest mHighResImageRequest;
        @Nullable
        private ImageRequest mLowResImageRequest;
        @Nullable
        private ImageRequest[] mMultiImageRequests;

        private Builder() {
        }

        public MultiUri build() {
            return new MultiUri(this);
        }

        public Builder setLowResImageRequest(@Nullable ImageRequest imageRequest) {
            this.mLowResImageRequest = imageRequest;
            return this;
        }

        public Builder setHighResImageRequest(@Nullable ImageRequest imageRequest) {
            this.mHighResImageRequest = imageRequest;
            return this;
        }

        public Builder setImageRequests(@Nullable ImageRequest... imageRequestArr) {
            this.mMultiImageRequests = imageRequestArr;
            return this;
        }
    }
}
