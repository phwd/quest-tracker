package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import java.util.Calendar;
import java.util.TimeZone;

/* renamed from: Gy1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Gy1 extends Go1 {
    public Gy1(Context context, double d, double d2) {
        super(context, d, d2);
        this.F.setContentDescription(getResources().getString(R.string.f45370_resource_name_obfuscated_RES_2131951854));
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        instance.setFirstDayOfWeek(2);
        instance.setMinimalDaysInFirstWeek(4);
        instance.setTimeInMillis(System.currentTimeMillis());
        h(m(instance), instance.get(3), null);
    }

    public static Calendar k(double d) {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        instance.clear();
        instance.setFirstDayOfWeek(2);
        instance.setMinimalDaysInFirstWeek(4);
        instance.setTimeInMillis((long) d);
        return instance;
    }

    public static Calendar l(int i, int i2) {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        instance.clear();
        instance.setFirstDayOfWeek(2);
        instance.setMinimalDaysInFirstWeek(4);
        instance.set(7, 2);
        instance.set(1, i);
        instance.set(3, i2);
        return instance;
    }

    public static int m(Calendar calendar) {
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(3);
        if (i2 != 0 || i3 <= 51) {
            return (i2 == 11 && i3 == 1) ? i + 1 : i;
        }
        return i - 1;
    }

    public static int n(Calendar calendar) {
        return calendar.get(3);
    }

    @Override // defpackage.Go1
    public Calendar a(double d) {
        return k(d);
    }

    @Override // defpackage.Go1
    public int b(int i) {
        if (i == m(this.f8111J)) {
            return n(this.f8111J);
        }
        return l(i, 20).getActualMaximum(3);
    }

    @Override // defpackage.Go1
    public int c() {
        return m(this.f8111J);
    }

    @Override // defpackage.Go1
    public int d(int i) {
        if (i == m(this.I)) {
            return n(this.I);
        }
        return 1;
    }

    @Override // defpackage.Go1
    public int e() {
        return m(this.I);
    }

    @Override // defpackage.Go1
    public int f() {
        return n(this.K);
    }

    @Override // defpackage.Go1
    public int g() {
        return m(this.K);
    }

    @Override // defpackage.Go1
    public void i(int i, int i2) {
        Calendar l = l(i, i2);
        if (l.before(this.I)) {
            this.K = this.I;
        } else if (l.after(this.f8111J)) {
            this.K = this.f8111J;
        } else {
            this.K = l;
        }
    }
}
