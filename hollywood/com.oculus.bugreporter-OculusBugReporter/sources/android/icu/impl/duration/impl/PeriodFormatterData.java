package android.icu.impl.duration.impl;

import android.icu.impl.duration.TimeUnit;
import android.icu.impl.duration.impl.DataRecord;
import android.icu.impl.duration.impl.Utils;
import java.io.PrintStream;
import java.util.Arrays;

public class PeriodFormatterData {
    private static final int FORM_DUAL = 2;
    private static final int FORM_HALF_SPELLED = 6;
    private static final int FORM_PAUCAL = 3;
    private static final int FORM_PLURAL = 0;
    private static final int FORM_SINGULAR = 1;
    private static final int FORM_SINGULAR_NO_OMIT = 5;
    private static final int FORM_SINGULAR_SPELLED = 4;
    public static boolean trace = false;
    final DataRecord dr;
    String localeName;

    public PeriodFormatterData(String localeName2, DataRecord dr2) {
        this.dr = dr2;
        this.localeName = localeName2;
        if (localeName2 == null) {
            throw new NullPointerException("localename is null");
        } else if (dr2 == null) {
            throw new NullPointerException("data record is null");
        }
    }

    public int pluralization() {
        return this.dr.pl;
    }

    public boolean allowZero() {
        return this.dr.allowZero;
    }

    public boolean weeksAloneOnly() {
        return this.dr.weeksAloneOnly;
    }

    public int useMilliseconds() {
        return this.dr.useMilliseconds;
    }

    public boolean appendPrefix(int tl, int td, StringBuffer sb) {
        DataRecord.ScopeData sd;
        String prefix;
        if (this.dr.scopeData == null || (sd = this.dr.scopeData[(tl * 3) + td]) == null || (prefix = sd.prefix) == null) {
            return false;
        }
        sb.append(prefix);
        return sd.requiresDigitPrefix;
    }

    public void appendSuffix(int tl, int td, StringBuffer sb) {
        DataRecord.ScopeData sd;
        String suffix;
        if (this.dr.scopeData != null && (sd = this.dr.scopeData[(tl * 3) + td]) != null && (suffix = sd.suffix) != null) {
            if (trace) {
                PrintStream printStream = System.out;
                printStream.println("appendSuffix '" + suffix + "'");
            }
            sb.append(suffix);
        }
    }

    public boolean appendUnit(TimeUnit unit, int count, int cv, int uv, boolean useCountSep, boolean useDigitPrefix, boolean multiple, boolean last, boolean wasSkipped, StringBuffer sb) {
        boolean willRequireSkipMarker;
        int count2;
        int cv2;
        String name;
        String name2;
        int form;
        byte b;
        String[] names;
        int px = unit.ordinal();
        if (this.dr.requiresSkipMarker == null || !this.dr.requiresSkipMarker[px] || this.dr.skippedUnitMarker == null) {
            willRequireSkipMarker = false;
        } else {
            if (!wasSkipped && last) {
                sb.append(this.dr.skippedUnitMarker);
            }
            willRequireSkipMarker = true;
        }
        if (uv != 0) {
            boolean useMedium = uv == 1;
            DataRecord dataRecord = this.dr;
            String[] names2 = useMedium ? dataRecord.mediumNames : dataRecord.shortNames;
            if (names2 == null || names2[px] == null) {
                DataRecord dataRecord2 = this.dr;
                names = useMedium ? dataRecord2.shortNames : dataRecord2.mediumNames;
            } else {
                names = names2;
            }
            if (!(names == null || names[px] == null)) {
                appendCount(unit, false, false, count, cv, useCountSep, names[px], last, sb);
                return false;
            }
        }
        if (cv != 2 || this.dr.halfSupport == null || (b = this.dr.halfSupport[px]) == 0 || (b != 1 && (b != 2 || count > 1000))) {
            count2 = count;
            cv2 = cv;
        } else {
            count2 = (count / 500) * 500;
            cv2 = 3;
        }
        int form2 = computeForm(unit, count2, cv2, multiple && last);
        if (form2 == 4) {
            if (this.dr.singularNames == null) {
                form2 = 1;
                name = this.dr.pluralNames[px][1];
            } else {
                name = this.dr.singularNames[px];
            }
        } else if (form2 == 5) {
            name = this.dr.pluralNames[px][1];
        } else if (form2 == 6) {
            name = this.dr.halfNames[px];
        } else {
            try {
                name = this.dr.pluralNames[px][form2];
            } catch (NullPointerException e) {
                System.out.println("Null Pointer in PeriodFormatterData[" + this.localeName + "].au px: " + px + " form: " + form2 + " pn: " + Arrays.toString(this.dr.pluralNames));
                throw e;
            }
        }
        if (name == null) {
            name2 = this.dr.pluralNames[px][0];
            form = 0;
        } else {
            name2 = name;
            form = form2;
        }
        int suffixIndex = appendCount(unit, form == 4 || form == 6 || (this.dr.omitSingularCount && form == 1) || (this.dr.omitDualCount && form == 2), useDigitPrefix, count2, cv2, useCountSep, name2, last, sb);
        if (last && suffixIndex >= 0) {
            String suffix = null;
            if (this.dr.rqdSuffixes != null && suffixIndex < this.dr.rqdSuffixes.length) {
                suffix = this.dr.rqdSuffixes[suffixIndex];
            }
            if (suffix == null && this.dr.optSuffixes != null && suffixIndex < this.dr.optSuffixes.length) {
                suffix = this.dr.optSuffixes[suffixIndex];
            }
            if (suffix != null) {
                sb.append(suffix);
            }
        }
        return willRequireSkipMarker;
    }

    public int appendCount(TimeUnit unit, boolean omitCount, boolean useDigitPrefix, int count, int cv, boolean useSep, String name, boolean last, StringBuffer sb) {
        String measure;
        String name2 = name;
        int cv2 = cv;
        if (cv2 == 2 && this.dr.halves == null) {
            cv2 = 0;
        }
        if (!omitCount && useDigitPrefix && this.dr.digitPrefix != null) {
            sb.append(this.dr.digitPrefix);
        }
        int index = unit.ordinal();
        if (cv2 != 0) {
            if (cv2 == 1) {
                int val = count / 1000;
                if (unit == TimeUnit.MINUTE && !((this.dr.fiveMinutes == null && this.dr.fifteenMinutes == null) || val == 0 || val % 5 != 0)) {
                    if (this.dr.fifteenMinutes != null && (val == 15 || val == 45)) {
                        int val2 = val == 15 ? 1 : 3;
                        if (!omitCount) {
                            appendInteger(val2, 1, 10, sb);
                        }
                        name2 = this.dr.fifteenMinutes;
                        index = 8;
                    } else if (this.dr.fiveMinutes != null) {
                        int val3 = val / 5;
                        if (!omitCount) {
                            appendInteger(val3, 1, 10, sb);
                        }
                        name2 = this.dr.fiveMinutes;
                        index = 9;
                    }
                }
                if (!omitCount) {
                    appendInteger(val, 1, 10, sb);
                }
            } else if (cv2 != 2) {
                int decimals = 1;
                if (cv2 == 4) {
                    decimals = 2;
                } else if (cv2 == 5) {
                    decimals = 3;
                }
                if (!omitCount) {
                    appendCountValue(count, 1, decimals, sb);
                }
            } else {
                int v = count / 500;
                byte b = 0;
                if (v != 1 && !omitCount) {
                    appendCountValue(count, 1, 0, sb);
                }
                if ((v & 1) == 1) {
                    if (v != 1 || this.dr.halfNames == null || this.dr.halfNames[index] == null) {
                        int solox = v == 1 ? 0 : 1;
                        if (this.dr.genders != null && this.dr.halves.length > 2 && this.dr.genders[index] == 1) {
                            solox += 2;
                        }
                        if (this.dr.halfPlacements != null) {
                            b = this.dr.halfPlacements[solox & 1];
                        }
                        String half = this.dr.halves[solox];
                        String measure2 = this.dr.measures == null ? null : this.dr.measures[index];
                        if (b == 0) {
                            sb.append(half);
                        } else if (b != 1) {
                            if (b == 2) {
                                if (measure2 != null) {
                                    sb.append(measure2);
                                }
                                if (useSep && !omitCount) {
                                    sb.append(this.dr.countSep);
                                }
                                sb.append(name2);
                                sb.append(half);
                                if (last) {
                                    return index;
                                }
                                return -1;
                            }
                        } else if (measure2 != null) {
                            sb.append(measure2);
                            sb.append(half);
                            if (useSep && !omitCount) {
                                sb.append(this.dr.countSep);
                            }
                            sb.append(name2);
                            return -1;
                        } else {
                            sb.append(name2);
                            sb.append(half);
                            if (last) {
                                return index;
                            }
                            return -1;
                        }
                    } else {
                        sb.append(name2);
                        if (last) {
                            return index;
                        }
                        return -1;
                    }
                }
            }
        } else if (!omitCount) {
            appendInteger(count / 1000, 1, 10, sb);
        }
        if (!omitCount && useSep) {
            sb.append(this.dr.countSep);
        }
        if (!omitCount && this.dr.measures != null && index < this.dr.measures.length && (measure = this.dr.measures[index]) != null) {
            sb.append(measure);
        }
        sb.append(name2);
        if (last) {
            return index;
        }
        return -1;
    }

    public void appendCountValue(int count, int integralDigits, int decimalDigits, StringBuffer sb) {
        int ival = count / 1000;
        if (decimalDigits == 0) {
            appendInteger(ival, integralDigits, 10, sb);
            return;
        }
        if (this.dr.requiresDigitSeparator && sb.length() > 0) {
            sb.append(' ');
        }
        appendDigits((long) ival, integralDigits, 10, sb);
        int dval = count % 1000;
        if (decimalDigits == 1) {
            dval /= 100;
        } else if (decimalDigits == 2) {
            dval /= 10;
        }
        sb.append(this.dr.decimalSep);
        appendDigits((long) dval, decimalDigits, decimalDigits, sb);
        if (this.dr.requiresDigitSeparator) {
            sb.append(' ');
        }
    }

    public void appendInteger(int num, int mindigits, int maxdigits, StringBuffer sb) {
        String name;
        if (this.dr.numberNames == null || num >= this.dr.numberNames.length || (name = this.dr.numberNames[num]) == null) {
            if (this.dr.requiresDigitSeparator && sb.length() > 0) {
                sb.append(' ');
            }
            byte b = this.dr.numberSystem;
            if (b == 0) {
                appendDigits((long) num, mindigits, maxdigits, sb);
            } else if (b == 1) {
                sb.append(Utils.chineseNumber((long) num, Utils.ChineseDigits.TRADITIONAL));
            } else if (b == 2) {
                sb.append(Utils.chineseNumber((long) num, Utils.ChineseDigits.SIMPLIFIED));
            } else if (b == 3) {
                sb.append(Utils.chineseNumber((long) num, Utils.ChineseDigits.KOREAN));
            }
            if (this.dr.requiresDigitSeparator) {
                sb.append(' ');
                return;
            }
            return;
        }
        sb.append(name);
    }

    public void appendDigits(long num, int mindigits, int maxdigits, StringBuffer sb) {
        char[] buf = new char[maxdigits];
        int ix = maxdigits;
        while (ix > 0 && num > 0) {
            ix--;
            buf[ix] = (char) ((int) (((long) this.dr.zero) + (num % 10)));
            num /= 10;
        }
        int e = maxdigits - mindigits;
        while (ix > e) {
            ix--;
            buf[ix] = this.dr.zero;
        }
        sb.append(buf, ix, maxdigits - ix);
    }

    public void appendSkippedUnit(StringBuffer sb) {
        if (this.dr.skippedUnitMarker != null) {
            sb.append(this.dr.skippedUnitMarker);
        }
    }

    public boolean appendUnitSeparator(TimeUnit unit, boolean longSep, boolean afterFirst, boolean beforeLast, StringBuffer sb) {
        if ((longSep && this.dr.unitSep != null) || this.dr.shortUnitSep != null) {
            if (!longSep || this.dr.unitSep == null) {
                sb.append(this.dr.shortUnitSep);
            } else {
                int ix = (afterFirst ? 2 : 0) + (beforeLast ? 1 : 0);
                sb.append(this.dr.unitSep[ix]);
                if (this.dr.unitSepRequiresDP == null || !this.dr.unitSepRequiresDP[ix]) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private int computeForm(TimeUnit unit, int count, int cv, boolean lastOfMultiple) {
        if (trace) {
            PrintStream printStream = System.err;
            printStream.println("pfd.cf unit: " + ((Object) unit) + " count: " + count + " cv: " + cv + " dr.pl: " + ((int) this.dr.pl));
            Thread.dumpStack();
        }
        if (this.dr.pl == 0) {
            return 0;
        }
        int val = count / 1000;
        if (!(cv == 0 || cv == 1)) {
            if (cv != 2) {
                byte b = this.dr.decimalHandling;
                if (b != 0) {
                    if (b == 1) {
                        return 5;
                    }
                    if (b != 2) {
                        if (b == 3 && this.dr.pl == 3) {
                            return 3;
                        }
                    } else if (count < 1000) {
                        return 5;
                    }
                }
                return 0;
            }
            byte b2 = this.dr.fractionHandling;
            if (b2 == 0) {
                return 0;
            }
            if (b2 == 1 || b2 == 2) {
                int v = count / 500;
                if (v == 1) {
                    if (this.dr.halfNames == null || this.dr.halfNames[unit.ordinal()] == null) {
                        return 5;
                    }
                    return 6;
                } else if ((v & 1) == 1) {
                    if (this.dr.pl == 5 && v > 21) {
                        return 5;
                    }
                    if (v == 3 && this.dr.pl == 1 && this.dr.fractionHandling != 2) {
                        return 0;
                    }
                }
            } else if (b2 == 3) {
                int v2 = count / 500;
                if (v2 == 1 || v2 == 3) {
                    return 3;
                }
            } else {
                throw new IllegalStateException();
            }
        }
        if (trace && count == 0) {
            PrintStream printStream2 = System.err;
            printStream2.println("EZeroHandling = " + ((int) this.dr.zeroHandling));
        }
        if (count == 0 && this.dr.zeroHandling == 1) {
            return 4;
        }
        byte b3 = this.dr.pl;
        if (b3 == 0) {
            return 0;
        }
        if (b3 != 1) {
            if (b3 != 2) {
                if (b3 == 3) {
                    int v3 = val % 100;
                    if (v3 > 20) {
                        v3 %= 10;
                    }
                    if (v3 == 1) {
                        return 1;
                    }
                    if (v3 <= 1 || v3 >= 5) {
                        return 0;
                    }
                    return 3;
                } else if (b3 != 4) {
                    if (b3 != 5) {
                        PrintStream printStream3 = System.err;
                        printStream3.println("dr.pl is " + ((int) this.dr.pl));
                        throw new IllegalStateException();
                    } else if (val == 2) {
                        return 2;
                    } else {
                        if (val == 1) {
                            return 1;
                        }
                        if (val > 10) {
                            return 5;
                        }
                        return 0;
                    }
                } else if (val == 2) {
                    return 2;
                } else {
                    if (val == 1) {
                        if (lastOfMultiple) {
                            return 4;
                        }
                        return 1;
                    } else if (unit != TimeUnit.YEAR || val <= 11) {
                        return 0;
                    } else {
                        return 5;
                    }
                }
            } else if (val == 2) {
                return 2;
            } else {
                if (val == 1) {
                    return 1;
                }
                return 0;
            }
        } else if (val == 1) {
            return 4;
        } else {
            return 0;
        }
    }
}
