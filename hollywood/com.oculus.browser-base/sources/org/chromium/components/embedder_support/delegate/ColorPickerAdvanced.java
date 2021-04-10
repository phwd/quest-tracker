package org.chromium.components.embedder_support.delegate;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ColorPickerAdvanced extends LinearLayout implements SeekBar.OnSeekBarChangeListener {
    public C0721Lv F;
    public C0721Lv G;
    public C0721Lv H;
    public AbstractC0656Ks0 I;

    /* renamed from: J  reason: collision with root package name */
    public int f10842J;
    public final float[] K = new float[3];

    public ColorPickerAdvanced(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
        this.F = a(R.string.f49320_resource_name_obfuscated_RES_2131952249, 360, this);
        this.G = a(R.string.f49330_resource_name_obfuscated_RES_2131952250, 100, this);
        this.H = a(R.string.f49340_resource_name_obfuscated_RES_2131952251, 100, this);
        b();
    }

    public C0721Lv a(int i, int i2, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.f37340_resource_name_obfuscated_RES_2131624043, (ViewGroup) null);
        addView(inflate);
        return new C0721Lv(inflate, i, i2, onSeekBarChangeListener);
    }

    public final void b() {
        int max = Math.max(Math.min(Math.round(this.K[1] * 100.0f), 100), 0);
        int max2 = Math.max(Math.min(Math.round(this.K[2] * 100.0f), 100), 0);
        this.F.c(this.K[0]);
        this.G.c((float) max);
        this.H.c((float) max2);
        c();
        d();
        e();
    }

    public final void c() {
        float[] fArr = new float[3];
        float[] fArr2 = this.K;
        fArr[1] = fArr2[1];
        fArr[2] = fArr2[2];
        int[] iArr = new int[7];
        for (int i = 0; i < 7; i++) {
            fArr[0] = ((float) i) * 60.0f;
            iArr[i] = Color.HSVToColor(fArr);
        }
        this.F.b(iArr);
    }

    public final void d() {
        float[] fArr = this.K;
        float[] fArr2 = {fArr[0], 0.0f, fArr[2]};
        fArr2[1] = 1.0f;
        this.G.b(new int[]{Color.HSVToColor(fArr2), Color.HSVToColor(fArr2)});
    }

    public final void e() {
        float[] fArr = this.K;
        float[] fArr2 = {fArr[0], fArr[1], 0.0f};
        fArr2[2] = 1.0f;
        this.H.b(new int[]{Color.HSVToColor(fArr2), Color.HSVToColor(fArr2)});
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            this.K[0] = this.F.a();
            this.K[1] = this.G.a() / 100.0f;
            this.K[2] = this.H.a() / 100.0f;
            this.f10842J = Color.HSVToColor(this.K);
            c();
            d();
            e();
            AbstractC0656Ks0 ks0 = this.I;
            if (ks0 != null) {
                ks0.a(this.f10842J);
            }
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
