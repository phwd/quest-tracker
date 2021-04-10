package android.icu.impl;

import android.icu.util.ICUException;
import android.icu.util.UResourceBundle;
import android.icu.util.UResourceBundleIterator;
import java.util.Arrays;

public class EraRules {
    private static final int DAY_MASK = 255;
    private static final int MAX_ENCODED_START_YEAR = 32767;
    public static final int MIN_ENCODED_START = encodeDate(MIN_ENCODED_START_YEAR, 1, 1);
    private static final int MIN_ENCODED_START_YEAR = -32768;
    private static final int MONTH_MASK = 65280;
    private static final int YEAR_MASK = -65536;
    private int currentEra;
    private int numEras;
    private int[] startDates;

    private EraRules(int[] startDates2, int numEras2) {
        this.startDates = startDates2;
        this.numEras = numEras2;
        initCurrentEra();
    }

    public static EraRules getInstance(CalType calType, boolean includeTentativeEra) {
        UResourceBundleIterator itr;
        UResourceBundle erasRes;
        UResourceBundle calendarTypeRes;
        UResourceBundle supplementalDataRes = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
        UResourceBundle calendarDataRes = supplementalDataRes.get("calendarData");
        UResourceBundle calendarTypeRes2 = calendarDataRes.get(calType.getId());
        UResourceBundle erasRes2 = calendarTypeRes2.get("eras");
        int numEras2 = erasRes2.getSize();
        int[] startDates2 = new int[numEras2];
        UResourceBundleIterator itr2 = erasRes2.getIterator();
        int firstTentativeIdx = Integer.MAX_VALUE;
        while (itr2.hasNext()) {
            UResourceBundle eraRuleRes = itr2.next();
            String eraIdxStr = eraRuleRes.getKey();
            try {
                int eraIdx = Integer.parseInt(eraIdxStr);
                if (eraIdx < 0 || eraIdx >= numEras2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Era rule key:");
                    sb.append(eraIdxStr);
                    sb.append(" in era rule data for ");
                    sb.append(calType.getId());
                    sb.append(" must be in range [0, ");
                    sb.append(numEras2 - 1);
                    sb.append("]");
                    throw new ICUException(sb.toString());
                } else if (!isSet(startDates2[eraIdx])) {
                    boolean hasName = true;
                    boolean hasEnd = false;
                    UResourceBundleIterator ruleItr = eraRuleRes.getIterator();
                    while (ruleItr.hasNext()) {
                        UResourceBundle res = ruleItr.next();
                        String key = res.getKey();
                        if (key.equals("start")) {
                            int[] fields = res.getIntVector();
                            calendarTypeRes = calendarTypeRes2;
                            erasRes = erasRes2;
                            if (fields.length == 3) {
                                itr = itr2;
                                if (isValidRuleStartDate(fields[0], fields[1], fields[2])) {
                                    startDates2[eraIdx] = encodeDate(fields[0], fields[1], fields[2]);
                                }
                            }
                            throw new ICUException("Invalid era rule date data:" + Arrays.toString(fields) + " in era rule data for " + calType.getId());
                        }
                        calendarTypeRes = calendarTypeRes2;
                        erasRes = erasRes2;
                        itr = itr2;
                        if (key.equals("named")) {
                            if (res.getString().equals("false")) {
                                hasName = false;
                            }
                        } else if (key.equals("end")) {
                            hasEnd = true;
                        }
                        supplementalDataRes = supplementalDataRes;
                        calendarDataRes = calendarDataRes;
                        calendarTypeRes2 = calendarTypeRes;
                        erasRes2 = erasRes;
                        itr2 = itr;
                    }
                    if (!isSet(startDates2[eraIdx])) {
                        if (!hasEnd) {
                            throw new ICUException("Missing era start/end rule date for key:" + eraIdxStr + " in era rule data for " + calType.getId());
                        } else if (eraIdx == 0) {
                            startDates2[eraIdx] = MIN_ENCODED_START;
                        } else {
                            throw new ICUException("Era data for " + eraIdxStr + " in era rule data for " + calType.getId() + " has only end rule.");
                        }
                    }
                    if (hasName) {
                        if (eraIdx >= firstTentativeIdx) {
                            throw new ICUException("Non-tentative era(" + eraIdx + ") must be placed before the first tentative era");
                        }
                    } else if (eraIdx < firstTentativeIdx) {
                        firstTentativeIdx = eraIdx;
                    }
                    supplementalDataRes = supplementalDataRes;
                    calendarDataRes = calendarDataRes;
                    calendarTypeRes2 = calendarTypeRes2;
                    erasRes2 = erasRes2;
                    itr2 = itr2;
                } else {
                    throw new ICUException("Dupulicated era rule for rule key:" + eraIdxStr + " in era rule data for " + calType.getId());
                }
            } catch (NumberFormatException e) {
                throw new ICUException("Invald era rule key:" + eraIdxStr + " in era rule data for " + calType.getId());
            }
        }
        if (firstTentativeIdx >= Integer.MAX_VALUE || includeTentativeEra) {
            return new EraRules(startDates2, numEras2);
        }
        return new EraRules(startDates2, firstTentativeIdx);
    }

    public int getNumberOfEras() {
        return this.numEras;
    }

    public int[] getStartDate(int eraIdx, int[] fillIn) {
        if (eraIdx >= 0 && eraIdx < this.numEras) {
            return decodeDate(this.startDates[eraIdx], fillIn);
        }
        throw new IllegalArgumentException("eraIdx is out of range");
    }

    public int getStartYear(int eraIdx) {
        if (eraIdx >= 0 && eraIdx < this.numEras) {
            return decodeDate(this.startDates[eraIdx], null)[0];
        }
        throw new IllegalArgumentException("eraIdx is out of range");
    }

    public int getEraIndex(int year, int month, int day) {
        int low;
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            throw new IllegalArgumentException("Illegal date - year:" + year + "month:" + month + "day:" + day);
        }
        int high = this.numEras;
        if (compareEncodedDateWithYMD(this.startDates[getCurrentEraIndex()], year, month, day) <= 0) {
            low = getCurrentEraIndex();
        } else {
            low = 0;
        }
        while (low < high - 1) {
            int i = (low + high) / 2;
            if (compareEncodedDateWithYMD(this.startDates[i], year, month, day) <= 0) {
                low = i;
            } else {
                high = i;
            }
        }
        return low;
    }

    public int getCurrentEraIndex() {
        return this.currentEra;
    }

    private void initCurrentEra() {
        int[] fields = Grego.timeToFields(System.currentTimeMillis(), null);
        int currentEncodedDate = encodeDate(fields[0], fields[1] + 1, fields[2]);
        int eraIdx = this.numEras - 1;
        while (eraIdx > 0 && currentEncodedDate < this.startDates[eraIdx]) {
            eraIdx--;
        }
        this.currentEra = eraIdx;
    }

    private static boolean isSet(int startDate) {
        return startDate != 0;
    }

    private static boolean isValidRuleStartDate(int year, int month, int day) {
        return year >= MIN_ENCODED_START_YEAR && year <= MAX_ENCODED_START_YEAR && month >= 1 && month <= 12 && day >= 1 && day <= 31;
    }

    private static int encodeDate(int year, int month, int day) {
        return (year << 16) | (month << 8) | day;
    }

    private static int[] decodeDate(int encodedDate, int[] fillIn) {
        int day;
        int month;
        int year;
        if (encodedDate == MIN_ENCODED_START) {
            year = Integer.MIN_VALUE;
            month = 1;
            day = 1;
        } else {
            year = (-65536 & encodedDate) >> 16;
            month = (65280 & encodedDate) >> 8;
            day = encodedDate & 255;
        }
        if (fillIn == null || fillIn.length < 3) {
            return new int[]{year, month, day};
        }
        fillIn[0] = year;
        fillIn[1] = month;
        fillIn[2] = day;
        return fillIn;
    }

    private static int compareEncodedDateWithYMD(int encoded, int year, int month, int day) {
        int tmp;
        if (year < MIN_ENCODED_START_YEAR) {
            if (encoded == MIN_ENCODED_START) {
                return (year > Integer.MIN_VALUE || month > 1 || day > 1) ? -1 : 0;
            }
            return 1;
        } else if (year <= MAX_ENCODED_START_YEAR && encoded >= (tmp = encodeDate(year, month, day))) {
            return encoded == tmp ? 0 : 1;
        } else {
            return -1;
        }
    }
}
