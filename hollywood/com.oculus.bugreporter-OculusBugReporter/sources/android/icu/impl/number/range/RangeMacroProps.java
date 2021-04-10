package android.icu.impl.number.range;

import android.icu.number.NumberRangeFormatter;
import android.icu.number.UnlocalizedNumberFormatter;
import android.icu.util.ULocale;
import java.util.Objects;

public class RangeMacroProps {
    public NumberRangeFormatter.RangeCollapse collapse;
    public UnlocalizedNumberFormatter formatter1;
    public UnlocalizedNumberFormatter formatter2;
    public NumberRangeFormatter.RangeIdentityFallback identityFallback;
    public ULocale loc;
    public int sameFormatters = -1;

    public int hashCode() {
        return Objects.hash(this.formatter1, this.formatter2, this.collapse, this.identityFallback, this.loc);
    }

    public boolean equals(Object _other) {
        if (_other == null) {
            return false;
        }
        if (this == _other) {
            return true;
        }
        if (!(_other instanceof RangeMacroProps)) {
            return false;
        }
        RangeMacroProps other = (RangeMacroProps) _other;
        if (!Objects.equals(this.formatter1, other.formatter1) || !Objects.equals(this.formatter2, other.formatter2) || !Objects.equals(this.collapse, other.collapse) || !Objects.equals(this.identityFallback, other.identityFallback) || !Objects.equals(this.loc, other.loc)) {
            return false;
        }
        return true;
    }
}
