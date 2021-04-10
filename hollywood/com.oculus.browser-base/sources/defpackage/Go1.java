package defpackage;

import android.content.Context;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import com.oculus.browser.R;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: Go1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Go1 extends FrameLayout {
    public final NumberPicker F;
    public final NumberPicker G;
    public Fo1 H;
    public Calendar I;

    /* renamed from: J  reason: collision with root package name */
    public Calendar f8111J;
    public Calendar K = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    public Go1(Context context, double d, double d2) {
        super(context, null, 16843612);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.f42180_resource_name_obfuscated_RES_2131624527, (ViewGroup) this, true);
        Eo1 eo1 = new Eo1(this);
        int i = 0;
        if (d >= d2) {
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.I = instance;
            instance.set(0, 0, 1);
            Calendar instance2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.f8111J = instance2;
            instance2.set(9999, 0, 1);
        } else {
            this.I = a(d);
            this.f8111J = a(d2);
        }
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.position_in_year);
        this.F = numberPicker;
        numberPicker.setOnLongPressUpdateInterval(200);
        numberPicker.setOnValueChangedListener(eo1);
        NumberPicker numberPicker2 = (NumberPicker) findViewById(R.id.year);
        this.G = numberPicker2;
        numberPicker2.setOnLongPressUpdateInterval(100);
        numberPicker2.setOnValueChangedListener(eo1);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.pickers);
        linearLayout.removeView(numberPicker);
        linearLayout.removeView(numberPicker2);
        String bestDateTimePattern = DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMMdd");
        boolean z = false;
        boolean z2 = false;
        while (i < bestDateTimePattern.length()) {
            char charAt = bestDateTimePattern.charAt(i);
            if (charAt == '\'') {
                i = bestDateTimePattern.indexOf(39, i + 1);
                if (i == -1) {
                    throw new IllegalArgumentException(AbstractC2531fV.f("Bad quoting in ", bestDateTimePattern));
                }
            } else if ((charAt == 'M' || charAt == 'L') && !z) {
                linearLayout.addView(this.F);
                z = true;
            } else if (charAt == 'y' && !z2) {
                linearLayout.addView(this.G);
                z2 = true;
            }
            i++;
        }
        if (!z) {
            linearLayout.addView(this.F);
        }
        if (!z2) {
            linearLayout.addView(this.G);
        }
    }

    public abstract Calendar a(double d);

    public abstract int b(int i);

    public abstract int c();

    public abstract int d(int i);

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    public abstract int e();

    public abstract int f();

    public int g() {
        return this.K.get(1);
    }

    public void h(int i, int i2, Fo1 fo1) {
        i(i, i2);
        j();
        this.H = null;
    }

    public abstract void i(int i, int i2);

    public void j() {
        this.F.setDisplayedValues(null);
        this.F.setMinValue(d(g()));
        this.F.setMaxValue(b(g()));
        this.F.setWrapSelectorWheel(!this.K.equals(this.I) && !this.K.equals(this.f8111J));
        this.G.setMinValue(e());
        this.G.setMaxValue(c());
        this.G.setWrapSelectorWheel(false);
        this.G.setValue(g());
        this.F.setValue(f());
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(getContext(), this.K.getTimeInMillis(), 20));
    }
}
