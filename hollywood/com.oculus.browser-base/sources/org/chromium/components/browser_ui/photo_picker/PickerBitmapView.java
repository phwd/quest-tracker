package org.chromium.components.browser_ui.photo_picker;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PickerBitmapView extends VR0 {
    public Context O;
    public EC0 P;
    public C3209jS0 Q;
    public C5557xC0 R;
    public ImageView S;
    public float T = -1.0f;
    public ViewGroup U;
    public TextView V;
    public ImageView W;
    public ImageView a0;
    public ImageView b0;
    public ImageView c0;
    public ImageView d0;
    public View e0;
    public ImageView f0;
    public TextView g0;
    public boolean h0;
    public int i0 = 0;
    public boolean j0;

    public PickerBitmapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.O = context;
    }

    @Override // defpackage.AbstractC3039iS0, defpackage.VR0
    public void b(List list) {
        C5557xC0 xc0 = this.R;
        if (xc0 != null) {
            p(list.contains(xc0) != super.isChecked());
            setChecked(this.I.c(this.f9084J));
        }
    }

    @Override // defpackage.VR0
    public void f() {
        if (this.R != null) {
            if (m()) {
                this.P.d(3, null, 3);
            } else if (l()) {
                this.P.d(2, null, 2);
            } else {
                onLongClick(this);
            }
        }
    }

    @Override // defpackage.VR0
    public boolean i(Object obj) {
        C5557xC0 xc0 = (C5557xC0) obj;
        if (m() || l() || this.P.c0) {
            return false;
        }
        return this.I.f(xc0);
    }

    public void k(C5557xC0 xc0, List list, String str, boolean z, float f) {
        int i;
        Fs1 fs1 = null;
        this.R = null;
        this.S.setImageBitmap(null);
        this.a0.setVisibility(8);
        this.V.setText("");
        this.U.setVisibility(8);
        this.d0.setVisibility(8);
        this.c0.setVisibility(8);
        this.b0.setVisibility(8);
        this.e0.setVisibility(8);
        this.f0.setVisibility(8);
        this.g0.setVisibility(8);
        this.j0 = false;
        setEnabled(true);
        this.R = xc0;
        this.f9084J = xc0;
        setChecked(this.I.c.contains(xc0));
        if (l() || m()) {
            Resources resources = this.O.getResources();
            if (l()) {
                fs1 = Fs1.a(resources, R.drawable.f32470_resource_name_obfuscated_RES_2131231287, this.O.getTheme());
                i = R.string.f58950_resource_name_obfuscated_RES_2131953212;
            } else if (m()) {
                fs1 = Fs1.a(resources, R.drawable.f29790_resource_name_obfuscated_RES_2131231019, this.O.getTheme());
                i = R.string.f58940_resource_name_obfuscated_RES_2131953211;
            } else {
                i = 0;
            }
            this.f0.setImageDrawable(fs1);
            ImageView imageView = this.f0;
            Context context = this.O;
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            imageView.setImageTintList(context.getColorStateList(R.color.f11380_resource_name_obfuscated_RES_2131099828));
            this.f0.setImageTintMode(PorterDuff.Mode.SRC_IN);
            this.g0.setText(i);
            this.e0.setVisibility(0);
            this.f0.setVisibility(0);
            this.g0.setVisibility(0);
            this.h0 = true;
        } else {
            o(list, str, f);
            this.h0 = !z;
        }
        p(false);
    }

    public final boolean l() {
        return this.R.H == 1;
    }

    public final boolean m() {
        return this.R.H == 2;
    }

    public final boolean n() {
        int i = this.R.H;
        return i == 0 || i == 3;
    }

    public boolean o(List list, String str, float f) {
        if (str == null || list.size() == 1) {
            this.S.setImageBitmap(list == null ? null : (Bitmap) list.get(0));
        } else {
            AnimationDrawable animationDrawable = new AnimationDrawable();
            for (int i = 0; i < list.size(); i++) {
                animationDrawable.addFrame(new BitmapDrawable(this.O.getResources(), (Bitmap) list.get(i)), 250);
            }
            animationDrawable.setOneShot(false);
            this.S.setImageDrawable(animationDrawable);
            animationDrawable.start();
        }
        this.V.setText(str);
        if (list != null && list.size() > 0) {
            this.T = f;
        }
        boolean z = !this.h0;
        this.h0 = true;
        p(false);
        return z;
    }

    @Override // defpackage.VR0
    public final void onClick(View view) {
        if (view == this.W || view == this.a0) {
            EC0 ec0 = this.P;
            Uri uri = this.R.F;
            DialogC4536rC0 rc0 = ec0.F;
            if (rc0 != null) {
                PickerVideoPlayer pickerVideoPlayer = ec0.l0;
                pickerVideoPlayer.G = rc0.getWindow().getDecorView();
                SpannableString spannableString = new SpannableString(uri.toString());
                spannableString.setSpan(new TextAppearanceSpan(pickerVideoPlayer.H, R.style.f72010_resource_name_obfuscated_RES_2132017774), 0, uri.getScheme().length(), 33);
                pickerVideoPlayer.f10816J.setText(spannableString, TextView.BufferType.SPANNABLE);
                pickerVideoPlayer.setVisibility(0);
                pickerVideoPlayer.K.setVisibility(0);
                pickerVideoPlayer.K.setVideoURI(uri);
                pickerVideoPlayer.K.setOnPreparedListener(new LC0(pickerVideoPlayer));
                pickerVideoPlayer.K.setOnCompletionListener(new QC0(pickerVideoPlayer));
                return;
            }
            return;
        }
        super.onClick(view);
    }

    @Override // defpackage.VR0
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.j0 = false;
    }

    @Override // defpackage.VR0
    public void onFinishInflate() {
        super.onFinishInflate();
        this.S = (ImageView) findViewById(R.id.bitmap_view);
        this.b0 = (ImageView) findViewById(R.id.scrim);
        this.c0 = (ImageView) findViewById(R.id.selected);
        this.d0 = (ImageView) findViewById(R.id.unselected);
        this.e0 = findViewById(R.id.special_tile);
        this.f0 = (ImageView) findViewById(R.id.special_tile_icon);
        this.g0 = (TextView) findViewById(R.id.special_tile_label);
        this.U = (ViewGroup) findViewById(R.id.video_controls_small);
        this.V = (TextView) findViewById(R.id.video_duration);
        ImageView imageView = (ImageView) findViewById(R.id.small_play_button);
        this.W = imageView;
        imageView.setOnClickListener(this);
        ImageView imageView2 = (ImageView) findViewById(R.id.large_play_button);
        this.a0 = imageView2;
        imageView2.setOnClickListener(this);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (n()) {
            accessibilityNodeInfo.setCheckable(true);
            accessibilityNodeInfo.setChecked(isChecked());
            StringBuilder sb = new StringBuilder();
            String path = this.R.F.getPath();
            int lastIndexOf = path.lastIndexOf("/");
            if (lastIndexOf != -1) {
                path = path.substring(lastIndexOf + 1, path.length());
            }
            sb.append(path);
            sb.append(" ");
            C5557xC0 xc0 = this.R;
            Objects.requireNonNull(xc0);
            sb.append(DateFormat.getDateTimeInstance().format(new Date(xc0.G)));
            accessibilityNodeInfo.setText(sb.toString());
        }
    }

    @Override // org.chromium.ui.widget.OptimizedFrameLayout
    public void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        EC0 ec0 = this.P;
        if (ec0 != null) {
            if (ec0.b0) {
                if (n()) {
                    i3 = (int) (this.T * ((float) this.P.f0));
                } else {
                    i3 = this.P.g0;
                }
                setMeasuredDimension(this.P.f0, i3);
                return;
            }
            int i4 = ec0.f0;
            setMeasuredDimension(i4, i4);
        }
    }

    public final void p(boolean z) {
        int i;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        boolean z2 = !n();
        C3209jS0 js0 = this.Q;
        boolean z3 = js0 != null && js0.d();
        Resources resources = this.O.getResources();
        if (!z2) {
            i = R.color.f14600_resource_name_obfuscated_RES_2131100150;
        } else {
            i = R.color.f14580_resource_name_obfuscated_RES_2131100148;
            boolean z4 = !z3;
            this.g0.setEnabled(z4);
            this.f0.setEnabled(z4);
            setEnabled(z4);
        }
        int color = resources.getColor(i);
        this.i0 = color;
        if (this.P.c0 && !z2) {
            color = 0;
        }
        setBackgroundColor(color);
        boolean c = this.Q.c(this.R);
        int i2 = 8;
        this.c0.setVisibility((z2 || !c) ? 8 : 0);
        boolean z5 = !z2 && !c && this.h0 && (z3 || this.P.b0) && this.P.K;
        this.d0.setVisibility(z5 ? 0 : 8);
        this.b0.setVisibility(z5 ? 0 : 8);
        boolean z6 = this.h0 && this.R.H == 3;
        this.U.setVisibility((!z6 || this.P.b0) ? 8 : 0);
        ImageView imageView = this.a0;
        if (z6 && this.P.b0) {
            i2 = 0;
        }
        imageView.setVisibility(i2);
        if (!z2 && isAttachedToWindow()) {
            boolean c2 = this.Q.c(this.R);
            EC0 ec0 = this.P;
            boolean z7 = ec0.b0;
            if (z7) {
                c2 = false;
            }
            if (c2 != this.j0) {
                this.j0 = c2;
                float f6 = 0.8f;
                float f7 = 1.0f;
                if (z7) {
                    float f8 = (float) ec0.f0;
                    f6 = 1.0f - ((0.07999998f * f8) / (f8 * this.T));
                    f = 0.92f;
                } else {
                    f = 0.8f;
                }
                float f9 = 0.0f;
                if (c2) {
                    float dimensionPixelSize = (float) getResources().getDimensionPixelSize(R.dimen.f24110_resource_name_obfuscated_RES_2131166030);
                    f4 = f;
                    f2 = f6;
                    f3 = 1.0f;
                    f9 = -dimensionPixelSize;
                    f5 = dimensionPixelSize;
                } else {
                    f5 = 0.0f;
                    f3 = f6;
                    f4 = 1.0f;
                    f2 = 1.0f;
                    f7 = f;
                }
                ScaleAnimation scaleAnimation = new ScaleAnimation(f7, f4, f3, f2, 1, 0.5f, 1, 0.5f);
                long j = 100;
                scaleAnimation.setDuration(z ? 100 : 0);
                scaleAnimation.setFillAfter(true);
                this.S.startAnimation(scaleAnimation);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.U, View.TRANSLATION_X, f9);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.U, View.TRANSLATION_Y, f5);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(ofFloat, ofFloat2);
                if (!z) {
                    j = 0;
                }
                animatorSet.setDuration(j);
                animatorSet.start();
            }
        }
    }

    @Override // defpackage.VR0
    public void setChecked(boolean z) {
        if (n()) {
            super.setChecked(z);
            p(false);
        }
    }
}
