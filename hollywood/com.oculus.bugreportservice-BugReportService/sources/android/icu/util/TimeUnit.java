package android.icu.util;

import android.icu.util.MeasureUnit;
import java.io.InvalidObjectException;

public class TimeUnit extends MeasureUnit {
    private static final long serialVersionUID = -2839973855554750484L;
    private final int index = 0;

    TimeUnit(String str, String str2) {
        super(str, str2);
    }

    private Object writeReplace() {
        return new MeasureUnit.MeasureUnitProxy(this.type, this.subType);
    }

    private Object readResolve() {
        switch (this.index) {
            case 0:
                return MeasureUnit.YEAR;
            case 1:
                return MeasureUnit.MONTH;
            case 2:
                return MeasureUnit.WEEK;
            case 3:
                return MeasureUnit.DAY;
            case 4:
                return MeasureUnit.HOUR;
            case 5:
                return MeasureUnit.MINUTE;
            case 6:
                return MeasureUnit.SECOND;
            default:
                throw new InvalidObjectException("Bad index: " + this.index);
        }
    }
}
