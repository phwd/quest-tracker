package android.icu.impl.duration.impl;

import java.util.Locale;

public class Utils {
    public static final Locale localeFromString(String s) {
        String language = s;
        String region = "";
        String variant = "";
        int x = language.indexOf("_");
        if (x != -1) {
            region = language.substring(x + 1);
            language = language.substring(0, x);
        }
        int x2 = region.indexOf("_");
        if (x2 != -1) {
            variant = region.substring(x2 + 1);
            region = region.substring(0, x2);
        }
        return new Locale(language, region, variant);
    }

    public static String chineseNumber(long n, ChineseDigits zh) {
        long n2 = n;
        if (n2 < 0) {
            n2 = -n2;
        }
        if (n2 > 10) {
            char[] buf = new char[40];
            char[] digits = String.valueOf(n2).toCharArray();
            boolean inZero = true;
            boolean forcedZero = false;
            int x = buf.length;
            int i = digits.length;
            int u = -1;
            int l = -1;
            while (true) {
                i--;
                boolean z = true;
                if (i < 0) {
                    break;
                }
                if (u == -1) {
                    if (l != -1) {
                        x--;
                        buf[x] = zh.levels[l];
                        inZero = true;
                        forcedZero = false;
                    }
                    u++;
                } else {
                    x--;
                    int u2 = u + 1;
                    buf[x] = zh.units[u];
                    if (u2 == 3) {
                        u = -1;
                        l++;
                    } else {
                        u = u2;
                    }
                }
                int d = digits[i] - '0';
                if (d == 0) {
                    if (x < buf.length - 1 && u != 0) {
                        buf[x] = '*';
                    }
                    if (inZero || forcedZero) {
                        x--;
                        buf[x] = '*';
                    } else {
                        x--;
                        buf[x] = zh.digits[0];
                        inZero = true;
                        if (u != 1) {
                            z = false;
                        }
                        forcedZero = z;
                    }
                } else {
                    inZero = false;
                    x--;
                    buf[x] = zh.digits[d];
                }
            }
            if (n2 > 1000000) {
                boolean last = true;
                int i2 = buf.length - 3;
                while (buf[i2] != '0') {
                    i2 -= 8;
                    last = !last;
                    if (i2 <= x) {
                        break;
                    }
                }
                int i3 = buf.length - 7;
                do {
                    if (buf[i3] == zh.digits[0] && !last) {
                        buf[i3] = '*';
                    }
                    i3 -= 8;
                    last = !last;
                } while (i3 > x);
                if (n2 >= 100000000) {
                    int i4 = buf.length - 8;
                    do {
                        boolean empty = true;
                        int j = i4 - 1;
                        int e = Math.max(x - 1, i4 - 8);
                        while (true) {
                            if (j <= e) {
                                break;
                            } else if (buf[j] != '*') {
                                empty = false;
                                break;
                            } else {
                                j--;
                            }
                        }
                        if (empty) {
                            if (buf[i4 + 1] == '*' || buf[i4 + 1] == zh.digits[0]) {
                                buf[i4] = '*';
                            } else {
                                buf[i4] = zh.digits[0];
                            }
                        }
                        i4 -= 8;
                    } while (i4 > x);
                }
            }
            for (int i5 = x; i5 < buf.length; i5++) {
                if (buf[i5] == zh.digits[2] && ((i5 >= buf.length - 1 || buf[i5 + 1] != zh.units[0]) && (i5 <= x || !(buf[i5 - 1] == zh.units[0] || buf[i5 - 1] == zh.digits[0] || buf[i5 - 1] == '*')))) {
                    buf[i5] = zh.liang;
                }
            }
            if (buf[x] == zh.digits[1] && (zh.ko || buf[x + 1] == zh.units[0])) {
                x++;
            }
            int w = x;
            for (int r = x; r < buf.length; r++) {
                if (buf[r] != '*') {
                    buf[w] = buf[r];
                    w++;
                }
            }
            return new String(buf, x, w - x);
        } else if (n2 == 2) {
            return String.valueOf(zh.liang);
        } else {
            return String.valueOf(zh.digits[(int) n2]);
        }
    }

    public static class ChineseDigits {
        public static final ChineseDigits DEBUG = new ChineseDigits("0123456789s", "sbq", "WYZ", 'L', false);
        public static final ChineseDigits KOREAN = new ChineseDigits("영일이삼사오육칠팔구십", "십백천", "만억?", 51060, true);
        public static final ChineseDigits SIMPLIFIED = new ChineseDigits("零一二三四五六七八九十", "十百千", "万亿兆", 20004, false);
        public static final ChineseDigits TRADITIONAL = new ChineseDigits("零一二三四五六七八九十", "十百千", "萬億兆", 20841, false);
        final char[] digits;
        final boolean ko;
        final char[] levels;
        final char liang;
        final char[] units;

        ChineseDigits(String digits2, String units2, String levels2, char liang2, boolean ko2) {
            this.digits = digits2.toCharArray();
            this.units = units2.toCharArray();
            this.levels = levels2.toCharArray();
            this.liang = liang2;
            this.ko = ko2;
        }
    }
}
