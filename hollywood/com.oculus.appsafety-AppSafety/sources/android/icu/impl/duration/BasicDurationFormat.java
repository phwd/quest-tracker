package android.icu.impl.duration;

import android.icu.text.DurationFormat;
import android.icu.util.ULocale;
import java.text.FieldPosition;
import java.util.Date;
import javax.xml.datatype.Duration;

public class BasicDurationFormat extends DurationFormat {
    private static final long serialVersionUID = -3146984141909457700L;
    transient DurationFormatter formatter;
    transient PeriodFormatter pformatter;
    transient PeriodFormatterService pfs;

    public static BasicDurationFormat getInstance(ULocale locale) {
        return new BasicDurationFormat(locale);
    }

    @Override // java.text.Format, android.icu.text.DurationFormat
    public StringBuffer format(Object object, StringBuffer toAppend, FieldPosition pos) {
        if (object instanceof Long) {
            toAppend.append(formatDurationFromNow(((Long) object).longValue()));
            return toAppend;
        } else if (object instanceof Date) {
            toAppend.append(formatDurationFromNowTo((Date) object));
            return toAppend;
        } else if (object instanceof Duration) {
            toAppend.append(formatDuration(object));
            return toAppend;
        } else {
            throw new IllegalArgumentException("Cannot format given Object as a Duration");
        }
    }

    public BasicDurationFormat() {
        this.pfs = null;
        this.pfs = BasicPeriodFormatterService.getInstance();
        this.formatter = this.pfs.newDurationFormatterFactory().getFormatter();
        this.pformatter = this.pfs.newPeriodFormatterFactory().setDisplayPastFuture(false).getFormatter();
    }

    public BasicDurationFormat(ULocale locale) {
        super(locale);
        this.pfs = null;
        this.pfs = BasicPeriodFormatterService.getInstance();
        this.formatter = this.pfs.newDurationFormatterFactory().setLocale(locale.getName()).getFormatter();
        this.pformatter = this.pfs.newPeriodFormatterFactory().setDisplayPastFuture(false).setLocale(locale.getName()).getFormatter();
    }

    @Override // android.icu.text.DurationFormat
    public String formatDurationFrom(long duration, long referenceDate) {
        return this.formatter.formatDurationFrom(duration, referenceDate);
    }

    @Override // android.icu.text.DurationFormat
    public String formatDurationFromNow(long duration) {
        return this.formatter.formatDurationFromNow(duration);
    }

    @Override // android.icu.text.DurationFormat
    public String formatDurationFromNowTo(Date targetDate) {
        return this.formatter.formatDurationFromNowTo(targetDate);
    }

    /* JADX INFO: Multiple debug info for r9v6 double: [D('millis' double), D('n' java.lang.Number)] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00c6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String formatDuration(java.lang.Object r24) {
        /*
        // Method dump skipped, instructions count: 246
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.duration.BasicDurationFormat.formatDuration(java.lang.Object):java.lang.String");
    }
}
