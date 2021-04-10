package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: Bl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0093Bl0 extends Go1 {
    public final String[] L;

    public C0093Bl0(Context context, double d, double d2) {
        super(context, d, d2);
        this.F.setContentDescription(getResources().getString(R.string.f45360_resource_name_obfuscated_RES_2131951853));
        String[] shortMonths = DateFormatSymbols.getInstance(Locale.getDefault()).getShortMonths();
        this.L = shortMonths;
        if (Character.isDigit(shortMonths[0].charAt(0))) {
            int i = 0;
            while (true) {
                String[] strArr = this.L;
                if (i >= strArr.length) {
                    break;
                }
                int i2 = i + 1;
                strArr[i] = String.format("%d", Integer.valueOf(i2));
                i = i2;
            }
        }
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        h(instance.get(1), instance.get(2), null);
    }

    public static Calendar k(double d) {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        instance.clear();
        instance.set((int) Math.min((d / 12.0d) + 1970.0d, 2.147483647E9d), (int) (d % 12.0d), 1);
        return instance;
    }

    @Override // defpackage.Go1
    public Calendar a(double d) {
        return k(d);
    }

    @Override // defpackage.Go1
    public int b(int i) {
        if (i == this.f8111J.get(1)) {
            return this.f8111J.get(2);
        }
        return 11;
    }

    @Override // defpackage.Go1
    public int c() {
        return this.f8111J.get(1);
    }

    @Override // defpackage.Go1
    public int d(int i) {
        if (i == this.I.get(1)) {
            return this.I.get(2);
        }
        return 0;
    }

    @Override // defpackage.Go1
    public int e() {
        return this.I.get(1);
    }

    @Override // defpackage.Go1
    public int f() {
        return this.K.get(2);
    }

    @Override // defpackage.Go1
    public void i(int i, int i2) {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        instance.set(i, i2, 1);
        if (instance.before(this.I)) {
            this.K = this.I;
        } else if (instance.after(this.f8111J)) {
            this.K = this.f8111J;
        } else {
            this.K = instance;
        }
    }

    @Override // defpackage.Go1
    public void j() {
        super.j();
        this.F.setDisplayedValues((String[]) Arrays.copyOfRange(this.L, this.F.getMinValue(), this.F.getMaxValue() + 1));
    }
}
