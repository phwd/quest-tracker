package com.oculus.common.socialtablet.util;

import X.AbstractC08371eV;
import X.AnonymousClass1g0;
import X.AnonymousClass1g1;
import X.AnonymousClass1gU;
import X.C08611ev;
import X.ComponentCallbacks2C07631cl;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Pair;
import android.widget.ImageView;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.oculus.os.MemoryUtils;
import com.oculus.os.Version;
import com.oculus.socialplatform.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ImageHandler {
    public final Context mContext;
    public final Set<AnonymousClass1gU<Bitmap>> mGlideCustomTargets = new HashSet();
    public final AnonymousClass1g1<Bitmap> mGlideFetcher;
    public final AnonymousClass1g0 mGlideManager;
    public final Set<ImageView> mGlideTargets = new HashSet();

    public void destroy() {
        for (ImageView imageView : this.mGlideTargets) {
            this.mGlideManager.clear(imageView);
        }
        this.mGlideTargets.clear();
        for (AnonymousClass1gU<Bitmap> r1 : this.mGlideCustomTargets) {
            this.mGlideManager.clear(r1);
        }
        this.mGlideCustomTargets.clear();
        trimHwuiCachesSafe();
    }

    @VisibleForTesting
    public boolean isTrimHwuiCachesSupported() {
        if (Version.CURRENT_SDK_VERSION >= 73) {
            return true;
        }
        return false;
    }

    public void loadCircleCroppedToTarget(String str, AnonymousClass1gU<Bitmap> r4) {
        this.mGlideCustomTargets.add(r4);
        AnonymousClass1g1<Bitmap> r0 = this.mGlideFetcher;
        r0.load(str);
        ((AnonymousClass1g1) r0.placeholder((int) R.drawable.oc_icon_profile_circle_filled_48_d2d2d2).error(R.drawable.oc_icon_profile_circle_filled_48_d2d2d2).circleCrop()).into(r4);
    }

    public void loadCircleCroppedToView(String str, ImageView imageView) {
        this.mGlideTargets.add(imageView);
        AnonymousClass1g1<Bitmap> r1 = this.mGlideFetcher;
        r1.load(str);
        ((AnonymousClass1g1) r1.placeholder((int) R.drawable.oc_icon_profile_circle_filled_48_d2d2d2).circleCrop()).into(imageView);
    }

    public void loadFacePile(Pair<String, String> pair, ImageView imageView, ImageView imageView2) {
        this.mGlideTargets.add(imageView);
        AnonymousClass1g1<Bitmap> r1 = this.mGlideFetcher;
        r1.load((String) pair.first);
        ((AnonymousClass1g1) r1.placeholder((int) R.drawable.oc_icon_profile_circle_filled_24_d2d2d2).circleCrop()).into(imageView);
        this.mGlideTargets.add(imageView2);
        AnonymousClass1g1<Bitmap> r12 = this.mGlideFetcher;
        r12.load((String) pair.second);
        ((AnonymousClass1g1) r12.placeholder((int) R.drawable.oc_icon_profile_circle_filled_24_d2d2d2).circleCrop()).into(imageView2);
    }

    public void loadRoundedCornersCroppedToView(String str, int i, int i2, Optional<ImageCornerRadius> optional, ImageView imageView) {
        ArrayList<AbstractC08371eV> arrayList = new ArrayList<>();
        arrayList.add(new CenterCrop());
        loadRoundedCornersToView(str, i, i2, optional, imageView, arrayList);
    }

    public void loadToView(String str, int i, int i2, ImageView imageView) {
        this.mGlideTargets.add(imageView);
        AnonymousClass1g1<Bitmap> r0 = this.mGlideFetcher;
        r0.load(str);
        ((AnonymousClass1g1) r0.override(i, i2).fitCenter().skipMemoryCache(true)).into(imageView);
    }

    public void unloadTarget(AnonymousClass1gU r2) {
        this.mGlideManager.clear(r2);
        this.mGlideCustomTargets.remove(r2);
        trimHwuiCachesSafe();
    }

    public void unloadView(ImageView imageView) {
        this.mGlideManager.clear(imageView);
        this.mGlideTargets.remove(imageView);
        trimHwuiCachesSafe();
    }

    public ImageHandler(Context context) {
        this.mContext = context;
        AnonymousClass1g0 A06 = ComponentCallbacks2C07631cl.A02(context).A06(context);
        this.mGlideManager = A06;
        AnonymousClass1g1<Bitmap> asBitmap = A06.asBitmap();
        asBitmap.autoClone();
        this.mGlideFetcher = asBitmap;
    }

    public void trimHwuiCachesSafe() {
        if (isTrimHwuiCachesSupported()) {
            MemoryUtils.trimHwuiCaches(MemoryUtils.HwuiTrimMode.UI_HIDDEN);
        }
    }

    private void loadRoundedCornersToView(String str, int i, int i2, Optional<ImageCornerRadius> optional, ImageView imageView, ArrayList<AbstractC08371eV> arrayList) {
        if (optional.isPresent()) {
            ImageCornerRadius imageCornerRadius = optional.get();
            arrayList.add(new GranularRoundedCorners((float) imageCornerRadius.mTopLeft, (float) imageCornerRadius.mTopRight, (float) imageCornerRadius.mBottomRight, (float) imageCornerRadius.mBottomLeft));
        }
        this.mGlideTargets.add(imageView);
        AnonymousClass1g1<Bitmap> r0 = this.mGlideFetcher;
        r0.load(str);
        ((AnonymousClass1g1) r0.override(i, i2).skipMemoryCache(true).transform(new C08611ev(arrayList))).into(imageView);
    }

    public void loadRoundedCornersToView(String str, int i, int i2, Optional<ImageCornerRadius> optional, ImageView imageView) {
        ArrayList<AbstractC08371eV> arrayList = new ArrayList<>();
        arrayList.add(new FitCenter());
        loadRoundedCornersToView(str, i, i2, optional, imageView, arrayList);
    }
}
