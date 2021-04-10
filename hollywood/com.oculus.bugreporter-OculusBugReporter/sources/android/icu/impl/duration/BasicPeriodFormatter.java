package android.icu.impl.duration;

import android.icu.impl.duration.BasicPeriodFormatterFactory;
import android.icu.impl.duration.impl.PeriodFormatterData;

class BasicPeriodFormatter implements PeriodFormatter {
    private BasicPeriodFormatterFactory.Customizations customs;
    private PeriodFormatterData data;
    private BasicPeriodFormatterFactory factory;
    private String localeName;

    BasicPeriodFormatter(BasicPeriodFormatterFactory factory2, String localeName2, PeriodFormatterData data2, BasicPeriodFormatterFactory.Customizations customs2) {
        this.factory = factory2;
        this.localeName = localeName2;
        this.data = data2;
        this.customs = customs2;
    }

    @Override // android.icu.impl.duration.PeriodFormatter
    public String format(Period period) {
        if (period.isSet()) {
            return format(period.timeLimit, period.inFuture, period.counts);
        }
        throw new IllegalArgumentException("period is not set");
    }

    @Override // android.icu.impl.duration.PeriodFormatter
    public PeriodFormatter withLocale(String locName) {
        if (this.localeName.equals(locName)) {
            return this;
        }
        return new BasicPeriodFormatter(this.factory, locName, this.factory.getData(locName), this.customs);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r18v3, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r18v5, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r18v6, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r11v2 int: [D('wasSkipped' boolean), D('j' int)] */
    private String format(int tl, boolean inFuture, int[] counts) {
        int i;
        int tl2;
        int td;
        boolean wasSkipped;
        boolean skipped;
        int j;
        int cv;
        char c;
        boolean z;
        int[] iArr = counts;
        int mask = 0;
        int i2 = 0;
        while (true) {
            i = 1;
            if (i2 >= iArr.length) {
                break;
            }
            if (iArr[i2] > 0) {
                mask |= 1 << i2;
            }
            i2++;
        }
        if (!this.data.allowZero()) {
            int i3 = 0;
            int m = 1;
            while (i3 < iArr.length) {
                if ((mask & m) != 0 && iArr[i3] == 1) {
                    mask &= ~m;
                }
                i3++;
                m <<= 1;
            }
            if (mask == 0) {
                return null;
            }
        }
        boolean forceD3Seconds = false;
        if (!(this.data.useMilliseconds() == 0 || ((1 << TimeUnit.MILLISECOND.ordinal) & mask) == 0)) {
            int sx = TimeUnit.SECOND.ordinal;
            int mx = TimeUnit.MILLISECOND.ordinal;
            int sf = 1 << sx;
            int mf = 1 << mx;
            int useMilliseconds = this.data.useMilliseconds();
            if (useMilliseconds == 1) {
                if ((mask & sf) == 0) {
                    mask |= sf;
                    iArr[sx] = 1;
                }
                iArr[sx] = iArr[sx] + ((iArr[mx] - 1) / 1000);
                mask &= ~mf;
                forceD3Seconds = true;
            } else if (useMilliseconds == 2 && (mask & sf) != 0) {
                iArr[sx] = iArr[sx] + ((iArr[mx] - 1) / 1000);
                mask &= ~mf;
                forceD3Seconds = true;
            }
        }
        int first = 0;
        int last = iArr.length - 1;
        while (first < iArr.length && ((1 << first) & mask) == 0) {
            first++;
        }
        while (last > first && ((1 << last) & mask) == 0) {
            last--;
        }
        boolean isZero = true;
        int i4 = first;
        while (true) {
            if (i4 <= last) {
                if (((1 << i4) & mask) != 0 && iArr[i4] > 1) {
                    isZero = false;
                    break;
                }
                i4++;
            } else {
                break;
            }
        }
        StringBuffer sb = new StringBuffer();
        if (!this.customs.displayLimit || isZero) {
            tl2 = 0;
        } else {
            tl2 = tl;
        }
        if (!this.customs.displayDirection || isZero) {
            td = 0;
        } else {
            td = inFuture ? 2 : 1;
        }
        boolean useDigitPrefix = this.data.appendPrefix(tl2, td, sb);
        boolean multiple = first != last;
        boolean wasSkipped2 = true;
        boolean countSep = this.customs.separatorVariant != 0;
        int j2 = first;
        boolean useDigitPrefix2 = useDigitPrefix;
        boolean useDigitPrefix3 = false;
        int i5 = first;
        while (i5 <= last) {
            if (useDigitPrefix3) {
                this.data.appendSkippedUnit(sb);
                wasSkipped = true;
                skipped = false;
            } else {
                skipped = useDigitPrefix3;
                wasSkipped = wasSkipped2;
            }
            while (true) {
                j = j2 + 1;
                if (j >= last || ((i << j) & mask) != 0) {
                    TimeUnit unit = TimeUnit.units[i5];
                    int count = iArr[i5] - 1;
                    int cv2 = this.customs.countVariant;
                } else {
                    skipped = true;
                    j2 = j;
                }
            }
            TimeUnit unit2 = TimeUnit.units[i5];
            int count2 = iArr[i5] - 1;
            int cv22 = this.customs.countVariant;
            if (i5 != last) {
                cv = 0;
            } else if (forceD3Seconds) {
                cv = 5;
            } else {
                cv = cv22;
            }
            useDigitPrefix3 = skipped | this.data.appendUnit(unit2, count2, cv, this.customs.unitVariant, countSep, useDigitPrefix2, multiple, i5 == last ? i : false, wasSkipped, sb);
            wasSkipped2 = false;
            if (this.customs.separatorVariant == 0 || j > last) {
                c = 2;
                z = false;
            } else {
                boolean afterFirst = i5 == first;
                c = 2;
                z = this.data.appendUnitSeparator(unit2, this.customs.separatorVariant == 2, afterFirst, j == last, sb);
            }
            useDigitPrefix2 = z;
            j2 = j;
            td = td;
            tl2 = tl2;
            forceD3Seconds = forceD3Seconds;
            isZero = isZero;
            i = 1;
            iArr = counts;
            i5 = j;
            mask = mask;
        }
        this.data.appendSuffix(tl2, td, sb);
        return sb.toString();
    }
}
