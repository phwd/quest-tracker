package android.icu.util;

import java.util.Date;

/* access modifiers changed from: package-private */
/* compiled from: RangeDateRule */
public class Range {
    public DateRule rule;
    public Date start;

    public Range(Date start2, DateRule rule2) {
        this.start = start2;
        this.rule = rule2;
    }
}
