package com.android.volley.toolbox;

import android.widget.ImageView;
import com.android.volley.Response$ErrorListener;

public class ImageLoader {

    public class ImageContainer {
        public void cancelRequest() {
            throw null;
        }

        public String getRequestUrl() {
            throw null;
        }
    }

    public interface ImageListener extends Response$ErrorListener {
    }

    public ImageContainer get(String str, ImageListener imageListener, int i, int i2, ImageView.ScaleType scaleType) {
        throw null;
    }
}
