package android.icu.impl.duration;

import android.icu.impl.duration.BasicPeriodBuilderFactory;
import java.util.TimeZone;

/* access modifiers changed from: package-private */
/* compiled from: BasicPeriodBuilderFactory */
public abstract class PeriodBuilderImpl implements PeriodBuilder {
    protected BasicPeriodBuilderFactory.Settings settings;

    /* access modifiers changed from: protected */
    public abstract Period handleCreate(long j, long j2, boolean z);

    /* access modifiers changed from: protected */
    public abstract PeriodBuilder withSettings(BasicPeriodBuilderFactory.Settings settings2);

    @Override // android.icu.impl.duration.PeriodBuilder
    public Period create(long duration) {
        return createWithReferenceDate(duration, System.currentTimeMillis());
    }

    public long approximateDurationOf(TimeUnit unit) {
        return BasicPeriodBuilderFactory.approximateDurationOf(unit);
    }

    @Override // android.icu.impl.duration.PeriodBuilder
    public Period createWithReferenceDate(long duration, long referenceDate) {
        boolean inPast = duration < 0;
        if (inPast) {
            duration = -duration;
        }
        Period ts = this.settings.createLimited(duration, inPast);
        if (ts != null) {
            return ts;
        }
        Period ts2 = handleCreate(duration, referenceDate, inPast);
        if (ts2 == null) {
            return Period.lessThan(1.0f, this.settings.effectiveMinUnit()).inPast(inPast);
        }
        return ts2;
    }

    @Override // android.icu.impl.duration.PeriodBuilder
    public PeriodBuilder withTimeZone(TimeZone timeZone) {
        return this;
    }

    @Override // android.icu.impl.duration.PeriodBuilder
    public PeriodBuilder withLocale(String localeName) {
        BasicPeriodBuilderFactory.Settings newSettings = this.settings.setLocale(localeName);
        if (newSettings != this.settings) {
            return withSettings(newSettings);
        }
        return this;
    }

    protected PeriodBuilderImpl(BasicPeriodBuilderFactory.Settings settings2) {
        this.settings = settings2;
    }
}
