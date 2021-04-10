package defpackage;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: Lv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0721Lv {

    /* renamed from: a  reason: collision with root package name */
    public final View f8446a;
    public final SeekBar b;
    public int[] c;
    public GradientDrawable d = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, null);
    public final TextView e;

    public C0721Lv(View view, int i, int i2, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.f8446a = view.findViewById(R.id.gradient);
        TextView textView = (TextView) view.findViewById(R.id.text);
        this.e = textView;
        textView.setText(i);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seek_bar);
        this.b = seekBar;
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBar.setMax(i2);
        seekBar.setThumbOffset(AbstractC3153j7.c(view.getContext().getResources(), R.drawable.f28880_resource_name_obfuscated_RES_2131230928).getIntrinsicWidth() / 2);
    }

    public float a() {
        return (float) this.b.getProgress();
    }

    public void b(int[] iArr) {
        int[] iArr2 = (int[]) iArr.clone();
        this.c = iArr2;
        this.d.setColors(iArr2);
        this.f8446a.setBackground(this.d);
    }

    public void c(float f) {
        this.b.setProgress((int) f);
    }
}
