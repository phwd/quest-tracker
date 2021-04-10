package org.chromium.chrome.features.start_surface;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FeedLoadingLayout extends LinearLayout {
    public final Context F;
    public final Resources G;
    public long H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f10808J;
    public Yo1 K;

    public FeedLoadingLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F = context;
        Resources resources = context.getResources();
        this.G = resources;
        this.I = resources.getConfiguration().screenWidthDp;
    }

    public final int a(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }

    public final GradientDrawable[] b(int i, int i2, int i3) {
        GradientDrawable[] gradientDrawableArr = new GradientDrawable[i];
        int a2 = a(12);
        for (int i4 = 0; i4 < i; i4++) {
            gradientDrawableArr[i4] = new GradientDrawable();
            gradientDrawableArr[i4].setShape(0);
            gradientDrawableArr[i4].setSize(i2, i3);
            gradientDrawableArr[i4].setCornerRadius((float) a2);
            gradientDrawableArr[i4].setColor(this.G.getColor(R.color.f12310_resource_name_obfuscated_RES_2131099921));
        }
        return gradientDrawableArr;
    }

    public final void c(LinearLayout linearLayout, boolean z, ViewGroup.LayoutParams layoutParams) {
        LayerDrawable layerDrawable;
        LinearLayout.LayoutParams layoutParams2;
        LayerDrawable layerDrawable2;
        int i;
        int i2;
        LinearLayout linearLayout2 = new LinearLayout(this.F);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setOrientation(!z ? 1 : 0);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        ImageView imageView = new ImageView(this.F);
        if (z) {
            int a2 = a(92);
            int a3 = a(15);
            layerDrawable = new LayerDrawable(b(1, a2, a2));
            if (this.f10808J) {
                i2 = a(48);
            } else {
                i2 = a(72);
            }
            layerDrawable.setLayerInset(0, 0, a3, 0, i2);
        } else {
            layerDrawable = new LayerDrawable(b(1, a(this.I), a(207)));
        }
        imageView.setImageDrawable(layerDrawable);
        imageView.setLayoutParams(layoutParams3);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        int a4 = a(15);
        int i3 = a4 / 2;
        int a5 = a(20);
        int a6 = a(this.I);
        int a7 = a(80);
        if (z) {
            layoutParams2 = new LinearLayout.LayoutParams(0, -1, 1.0f);
        } else {
            layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        }
        if (z) {
            GradientDrawable[] b = b(4, a6, a5);
            int a8 = a(15) + a(92);
            if (this.f10808J) {
                i = a(48);
            } else {
                i = a(72);
            }
            int i4 = a8 + i;
            layerDrawable2 = new LayerDrawable(b);
            int i5 = i4 - a4;
            int i6 = i5 - a5;
            layerDrawable2.setLayerInset(0, 0, a4, a4, i6);
            layerDrawable2.setLayerInset(1, 0, ((a7 - a5) / 2) + a4, a4, i5 - ((a5 + a7) / 2));
            layerDrawable2.setLayerInset(2, 0, (a4 + a7) - a5, a4, i5 - a7);
            layerDrawable2.setLayerInset(3, 0, i6, a4 * 7, a4);
        } else {
            int i7 = (a4 * 2) + a7;
            LayerDrawable layerDrawable3 = new LayerDrawable(b(3, a6, a5));
            int i8 = (i7 - a4) - a5;
            layerDrawable3.setLayerInset(0, i3, a4, a4, i8);
            int i9 = (i7 - a5) / 2;
            layerDrawable3.setLayerInset(1, i3, i9, a4, i9);
            layerDrawable3.setLayerInset(2, i3, i8, a4, a4);
            layerDrawable2 = layerDrawable3;
        }
        ImageView imageView2 = new ImageView(this.F);
        imageView2.setImageDrawable(layerDrawable2);
        imageView2.setLayoutParams(layoutParams2);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        linearLayout2.addView(z ? imageView2 : imageView);
        ImageView imageView3 = imageView;
        if (!z) {
            imageView3 = imageView2;
        }
        linearLayout2.addView(imageView3);
        linearLayout.addView(linearLayout2);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.K.b();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.K = new Yo1(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.feed_placeholder_header);
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        layoutParams.height = getResources().getDimensionPixelSize(R.dimen.f25160_resource_name_obfuscated_RES_2131166135);
        linearLayout.setLayoutParams(layoutParams);
        C2777gv1.b(this, this.K, this.G.getDimensionPixelSize(R.dimen.f17680_resource_name_obfuscated_RES_2131165387), this.G.getDimensionPixelSize(R.dimen.f23150_resource_name_obfuscated_RES_2131165934));
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.placeholders_layout);
        linearLayout2.removeAllViews();
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        int dimensionPixelSize = this.G.getDimensionPixelSize(R.dimen.f17690_resource_name_obfuscated_RES_2131165388);
        layoutParams2.setMargins(dimensionPixelSize, 0, dimensionPixelSize, a(12));
        this.f10808J = (getResources().getConfiguration().orientation == 2) || NU0.f8549a.d("Chrome.Feed.PlaceholderIsDense", false);
        c(linearLayout2, true, layoutParams2);
        c(linearLayout2, false, layoutParams2);
        c(linearLayout2, false, layoutParams2);
        this.H = SystemClock.elapsedRealtime();
    }
}
