package android.icu.impl;

import android.icu.text.UTF16;
import java.text.CharacterIterator;

public final class CharacterIteration {
    public static final int DONE32 = Integer.MAX_VALUE;

    private CharacterIteration() {
    }

    public static int next32(CharacterIterator ci) {
        int c;
        int c2 = ci.current();
        if (c2 >= 55296 && c2 <= 56319 && ((c = ci.next()) < 56320 || c > 57343)) {
            ci.previous();
        }
        int c3 = ci.next();
        if (c3 >= 55296) {
            c3 = nextTrail32(ci, c3);
        }
        if (c3 >= 65536 && c3 != Integer.MAX_VALUE) {
            ci.previous();
        }
        return c3;
    }

    public static int nextTrail32(CharacterIterator ci, int lead) {
        if (lead == 65535 && ci.getIndex() >= ci.getEndIndex()) {
            return Integer.MAX_VALUE;
        }
        if (lead > 56319) {
            return lead;
        }
        char cTrail = ci.next();
        if (UTF16.isTrailSurrogate(cTrail)) {
            return ((lead - 55296) << 10) + (cTrail - UTF16.TRAIL_SURROGATE_MIN_VALUE) + 65536;
        }
        ci.previous();
        return lead;
    }

    public static int previous32(CharacterIterator ci) {
        if (ci.getIndex() <= ci.getBeginIndex()) {
            return Integer.MAX_VALUE;
        }
        char trail = ci.previous();
        if (!UTF16.isTrailSurrogate(trail) || ci.getIndex() <= ci.getBeginIndex()) {
            return trail;
        }
        char lead = ci.previous();
        if (UTF16.isLeadSurrogate(lead)) {
            return ((lead - 55296) << 10) + (trail - UTF16.TRAIL_SURROGATE_MIN_VALUE) + 65536;
        }
        ci.next();
        return trail;
    }

    public static int current32(CharacterIterator ci) {
        char lead = ci.current();
        if (lead < 55296) {
            return lead;
        }
        if (UTF16.isLeadSurrogate(lead)) {
            int trail = ci.next();
            ci.previous();
            if (UTF16.isTrailSurrogate((char) trail)) {
                return ((lead - 55296) << 10) + (trail - UTF16.TRAIL_SURROGATE_MIN_VALUE) + 65536;
            }
            return lead;
        } else if (lead != 65535 || ci.getIndex() < ci.getEndIndex()) {
            return lead;
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
