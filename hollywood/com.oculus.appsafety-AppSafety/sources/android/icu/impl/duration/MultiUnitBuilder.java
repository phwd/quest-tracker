package android.icu.impl.duration;

import android.icu.impl.duration.BasicPeriodBuilderFactory;

/* compiled from: BasicPeriodBuilderFactory */
class MultiUnitBuilder extends PeriodBuilderImpl {
    private int nPeriods;

    MultiUnitBuilder(int nPeriods2, BasicPeriodBuilderFactory.Settings settings) {
        super(settings);
        this.nPeriods = nPeriods2;
    }

    public static MultiUnitBuilder get(int nPeriods2, BasicPeriodBuilderFactory.Settings settings) {
        if (nPeriods2 <= 0 || settings == null) {
            return null;
        }
        return new MultiUnitBuilder(nPeriods2, settings);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.duration.PeriodBuilderImpl
    public PeriodBuilder withSettings(BasicPeriodBuilderFactory.Settings settingsToUse) {
        return get(this.nPeriods, settingsToUse);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.duration.PeriodBuilderImpl
    public Period handleCreate(long duration, long referenceDate, boolean inPast) {
        Period period = null;
        int n = 0;
        short uset = this.settings.effectiveSet();
        int i = 0;
        long duration2 = duration;
        while (true) {
            if (i >= TimeUnit.units.length) {
                break;
            }
            if (((1 << i) & uset) != 0) {
                TimeUnit unit = TimeUnit.units[i];
                if (n == this.nPeriods) {
                    break;
                }
                long unitDuration = approximateDurationOf(unit);
                if (duration2 >= unitDuration || n > 0) {
                    n++;
                    double count = ((double) duration2) / ((double) unitDuration);
                    if (n < this.nPeriods) {
                        count = Math.floor(count);
                        duration2 -= (long) (((double) unitDuration) * count);
                    }
                    if (period == null) {
                        period = Period.at((float) count, unit).inPast(inPast);
                    } else {
                        period = period.and((float) count, unit);
                    }
                }
            }
            i++;
        }
        return period;
    }
}
