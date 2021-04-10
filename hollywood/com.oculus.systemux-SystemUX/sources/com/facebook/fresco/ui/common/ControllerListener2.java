package com.facebook.fresco.ui.common;

import java.util.Map;
import javax.annotation.Nullable;

@Deprecated
public interface ControllerListener2<INFO> {
    void onFailure(String str, Throwable th, @Nullable Extras extras);

    void onFinalImageSet(String str, @Nullable INFO info, Extras extras);

    void onIntermediateImageFailed(String str);

    void onIntermediateImageSet(String str, @Nullable INFO info);

    void onRelease(String str, @Nullable Extras extras);

    void onSubmit(String str, Object obj, @Nullable Extras extras);

    public static class Extras {
        @Nullable
        public Map<String, Object> pipe;
        @Nullable
        public Map<String, Object> view;

        public static Extras of(@Nullable Map<String, Object> map, @Nullable Map<String, Object> map2) {
            Extras extras = new Extras();
            extras.pipe = map;
            extras.view = map2;
            return extras;
        }

        public String toString() {
            return "pipe: " + this.pipe + ", view: " + this.view;
        }
    }
}
