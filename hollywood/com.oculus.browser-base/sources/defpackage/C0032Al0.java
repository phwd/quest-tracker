package defpackage;

import android.widget.BaseAdapter;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.datepicker.Month;
import java.util.Calendar;

/* renamed from: Al0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0032Al0 extends BaseAdapter {
    public static final int F = AbstractC2255ds1.e().getMaximum(4);
    public final Month G;
    public final DateSelector H;
    public C0702Ll I;

    /* renamed from: J  reason: collision with root package name */
    public final CalendarConstraints f7692J;

    public C0032Al0(Month month, DateSelector dateSelector, CalendarConstraints calendarConstraints) {
        this.G = month;
        this.H = dateSelector;
        this.f7692J = calendarConstraints;
    }

    public int a() {
        return this.G.i();
    }

    /* renamed from: b */
    public Long getItem(int i) {
        if (i < this.G.i() || i > c()) {
            return null;
        }
        Month month = this.G;
        Calendar b = AbstractC2255ds1.b(month.F);
        b.set(5, (i - month.i()) + 1);
        return Long.valueOf(b.getTimeInMillis());
    }

    public int c() {
        return (this.G.i() + this.G.K) - 1;
    }

    public int getCount() {
        return a() + this.G.K;
    }

    public long getItemId(int i) {
        return (long) (i / this.G.f9692J);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
        // Method dump skipped, instructions count: 287
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0032Al0.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public boolean hasStableIds() {
        return true;
    }
}
