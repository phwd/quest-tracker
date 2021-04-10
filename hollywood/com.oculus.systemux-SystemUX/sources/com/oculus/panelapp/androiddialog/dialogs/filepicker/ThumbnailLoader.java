package com.oculus.panelapp.androiddialog.dialogs.filepicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import java.util.HashSet;
import java.util.Set;

public class ThumbnailLoader {
    private final Context mContext;
    private final RequestBuilder<Bitmap> mGlideFetcher;
    private final RequestManager mGlideManager;
    private final Set<ImageView> mGlideTargets = new HashSet();

    public ThumbnailLoader(Context context) {
        this.mContext = context;
        this.mGlideManager = Glide.with(this.mContext);
        this.mGlideFetcher = this.mGlideManager.asBitmap();
    }

    public void destroy() {
        for (ImageView imageView : this.mGlideTargets) {
            this.mGlideManager.clear(imageView);
        }
    }

    public void unload(ImageView imageView) {
        this.mGlideManager.clear(imageView);
        this.mGlideTargets.remove(imageView);
    }

    public void load(String str, ImageView imageView, float f) {
        this.mGlideTargets.add(imageView);
        this.mGlideFetcher.load(str).thumbnail(f).into(imageView);
    }

    public void load(String str, ImageView imageView) {
        this.mGlideTargets.add(imageView);
        this.mGlideFetcher.load(str).into(imageView);
    }

    public void load(int i, ImageView imageView) {
        this.mGlideTargets.add(imageView);
        this.mGlideFetcher.load(Integer.valueOf(i)).into(imageView);
    }
}
