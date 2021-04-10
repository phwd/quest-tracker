package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.EncodedImageOrigin;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import javax.annotation.Nullable;

public interface ProducerContext {

    public @interface ExtraKeys {
        public static final String ENCODED_HEIGHT = "encoded_height";
        public static final String ENCODED_SIZE = "encoded_size";
        public static final String ENCODED_WIDTH = "encoded_width";
        public static final String MULTIPLEX_BITMAP_COUNT = "multiplex_bmp_cnt";
        public static final String MULTIPLEX_ENCODED_COUNT = "multiplex_enc_cnt";
        public static final String NORMALIZED_URI = "uri_norm";
        public static final String ORIGIN = "origin";
        public static final String ORIGIN_SUBCATEGORY = "origin_sub";
        public static final String SOURCE_URI = "uri_source";
    }

    void addCallbacks(ProducerContextCallbacks producerContextCallbacks);

    Object getCallerContext();

    EncodedImageOrigin getEncodedImageOrigin();

    @Nullable
    <E> E getExtra(String str);

    @Nullable
    <E> E getExtra(String str, @Nullable E e);

    Map<String, Object> getExtras();

    String getId();

    ImagePipelineConfig getImagePipelineConfig();

    ImageRequest getImageRequest();

    ImageRequest.RequestLevel getLowestPermittedRequestLevel();

    Priority getPriority();

    ProducerListener2 getProducerListener();

    @Nullable
    String getUiComponentId();

    boolean isIntermediateResultExpected();

    boolean isPrefetch();

    void putExtras(@Nullable Map<String, ?> map);

    void putOriginExtra(@Nullable String str);

    void putOriginExtra(@Nullable String str, @Nullable String str2);

    void setEncodedImageOrigin(EncodedImageOrigin encodedImageOrigin);

    <E> void setExtra(String str, @Nullable E e);
}
