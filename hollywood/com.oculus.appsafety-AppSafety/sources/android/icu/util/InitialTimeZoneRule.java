package android.icu.util;

import java.util.Date;

public class InitialTimeZoneRule extends TimeZoneRule {
    private static final long serialVersionUID = 1876594993064051206L;

    public InitialTimeZoneRule(String name, int rawOffset, int dstSavings) {
        super(name, rawOffset, dstSavings);
    }

    @Override // android.icu.util.TimeZoneRule
    public boolean isEquivalentTo(TimeZoneRule other) {
        if (other instanceof InitialTimeZoneRule) {
            return super.isEquivalentTo(other);
        }
        return false;
    }

    @Override // android.icu.util.TimeZoneRule
    public Date getFinalStart(int prevRawOffset, int prevDSTSavings) {
        return null;
    }

    @Override // android.icu.util.TimeZoneRule
    public Date getFirstStart(int prevRawOffset, int prevDSTSavings) {
        return null;
    }

    @Override // android.icu.util.TimeZoneRule
    public Date getNextStart(long base, int prevRawOffset, int prevDSTSavings, boolean inclusive) {
        return null;
    }

    @Override // android.icu.util.TimeZoneRule
    public Date getPreviousStart(long base, int prevRawOffset, int prevDSTSavings, boolean inclusive) {
        return null;
    }

    @Override // android.icu.util.TimeZoneRule
    public boolean isTransitionRule() {
        return false;
    }
}
