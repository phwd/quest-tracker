package android.icu.impl.duration;

import java.util.Date;
import java.util.TimeZone;

class BasicDurationFormatter implements DurationFormatter {
    private PeriodBuilder builder;
    private DateFormatter fallback;
    private long fallbackLimit;
    private PeriodFormatter formatter;
    private String localeName;
    private TimeZone timeZone;

    public BasicDurationFormatter(PeriodFormatter formatter2, PeriodBuilder builder2, DateFormatter fallback2, long fallbackLimit2) {
        this.formatter = formatter2;
        this.builder = builder2;
        this.fallback = fallback2;
        this.fallbackLimit = fallbackLimit2 >= 0 ? fallbackLimit2 : 0;
    }

    protected BasicDurationFormatter(PeriodFormatter formatter2, PeriodBuilder builder2, DateFormatter fallback2, long fallbackLimit2, String localeName2, TimeZone timeZone2) {
        this.formatter = formatter2;
        this.builder = builder2;
        this.fallback = fallback2;
        this.fallbackLimit = fallbackLimit2;
        this.localeName = localeName2;
        this.timeZone = timeZone2;
    }

    @Override // android.icu.impl.duration.DurationFormatter
    public String formatDurationFromNowTo(Date targetDate) {
        long now = System.currentTimeMillis();
        return formatDurationFrom(targetDate.getTime() - now, now);
    }

    @Override // android.icu.impl.duration.DurationFormatter
    public String formatDurationFromNow(long duration) {
        return formatDurationFrom(duration, System.currentTimeMillis());
    }

    @Override // android.icu.impl.duration.DurationFormatter
    public String formatDurationFrom(long duration, long referenceDate) {
        String s = doFallback(duration, referenceDate);
        if (s == null) {
            return doFormat(doBuild(duration, referenceDate));
        }
        return s;
    }

    @Override // android.icu.impl.duration.DurationFormatter
    public DurationFormatter withLocale(String locName) {
        DateFormatter newFallback;
        if (locName.equals(this.localeName)) {
            return this;
        }
        PeriodFormatter newFormatter = this.formatter.withLocale(locName);
        PeriodBuilder newBuilder = this.builder.withLocale(locName);
        DateFormatter dateFormatter = this.fallback;
        if (dateFormatter == null) {
            newFallback = null;
        } else {
            newFallback = dateFormatter.withLocale(locName);
        }
        return new BasicDurationFormatter(newFormatter, newBuilder, newFallback, this.fallbackLimit, locName, this.timeZone);
    }

    @Override // android.icu.impl.duration.DurationFormatter
    public DurationFormatter withTimeZone(TimeZone tz) {
        DateFormatter newFallback;
        if (tz.equals(this.timeZone)) {
            return this;
        }
        PeriodBuilder newBuilder = this.builder.withTimeZone(tz);
        DateFormatter dateFormatter = this.fallback;
        if (dateFormatter == null) {
            newFallback = null;
        } else {
            newFallback = dateFormatter.withTimeZone(tz);
        }
        return new BasicDurationFormatter(this.formatter, newBuilder, newFallback, this.fallbackLimit, this.localeName, tz);
    }

    /* access modifiers changed from: protected */
    public String doFallback(long duration, long referenceDate) {
        if (this.fallback == null || this.fallbackLimit <= 0 || Math.abs(duration) < this.fallbackLimit) {
            return null;
        }
        return this.fallback.format(referenceDate + duration);
    }

    /* access modifiers changed from: protected */
    public Period doBuild(long duration, long referenceDate) {
        return this.builder.createWithReferenceDate(duration, referenceDate);
    }

    /* access modifiers changed from: protected */
    public String doFormat(Period period) {
        if (period.isSet()) {
            return this.formatter.format(period);
        }
        throw new IllegalArgumentException("period is not set");
    }
}
