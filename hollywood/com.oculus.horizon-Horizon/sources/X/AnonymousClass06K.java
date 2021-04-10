package X;

import android.text.SpannableStringBuilder;

/* renamed from: X.06K  reason: invalid class name */
public final class AnonymousClass06K {
    public static final AnonymousClass06K A03;
    public static final AnonymousClass06K A04;
    public static final AnonymousClass06S A05 = AnonymousClass06U.A01;
    public static final String A06 = Character.toString(8206);
    public static final String A07 = Character.toString(8207);
    public final int A00 = 2;
    public final boolean A01;
    public final AnonymousClass06S A02;

    public AnonymousClass06K(boolean z, AnonymousClass06S r3) {
        this.A01 = z;
        this.A02 = r3;
    }

    public static int A00(CharSequence charSequence) {
        byte directionality;
        AnonymousClass06J r4 = new AnonymousClass06J(charSequence);
        r4.A01 = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = r4.A01;
            if (i4 < r4.A02) {
                if (i == 0) {
                    CharSequence charSequence2 = r4.A03;
                    char charAt = charSequence2.charAt(i4);
                    r4.A00 = charAt;
                    if (Character.isHighSurrogate(charAt)) {
                        int codePointAt = Character.codePointAt(charSequence2, r4.A01);
                        r4.A01 += Character.charCount(codePointAt);
                        directionality = Character.getDirectionality(codePointAt);
                    } else {
                        r4.A01++;
                        char c = r4.A00;
                        if (c < 1792) {
                            directionality = AnonymousClass06J.A04[c];
                        } else {
                            directionality = Character.getDirectionality(c);
                        }
                    }
                    if (directionality != 0) {
                        if (directionality == 1 || directionality == 2) {
                            if (i3 == 0) {
                                return 1;
                            }
                        } else if (directionality != 9) {
                            switch (directionality) {
                                case 14:
                                case 15:
                                    i3++;
                                    i2 = -1;
                                    break;
                                case 16:
                                case 17:
                                    i3++;
                                    i2 = 1;
                                    break;
                                case 18:
                                    i3--;
                                    i2 = 0;
                                    break;
                            }
                        }
                    } else if (i3 == 0) {
                        return -1;
                    }
                    i = i3;
                }
            } else if (i == 0) {
                return 0;
            }
        }
        if (i2 != 0) {
            return i2;
        }
        while (r4.A01 > 0) {
            switch (AnonymousClass06J.A00(r4)) {
                case 14:
                case 15:
                    if (i == i3) {
                        return -1;
                    }
                    break;
                case 16:
                case 17:
                    if (i == i3) {
                        return 1;
                    }
                    break;
                case 18:
                    i3++;
                    continue;
            }
            i3--;
        }
        return 0;
    }

    static {
        AnonymousClass06S r2 = A05;
        A03 = new AnonymousClass06K(false, r2);
        A04 = new AnonymousClass06K(true, r2);
    }

    public static int A01(CharSequence charSequence) {
        AnonymousClass06J r4 = new AnonymousClass06J(charSequence);
        r4.A01 = r4.A02;
        int i = 0;
        int i2 = 0;
        while (r4.A01 > 0) {
            byte A002 = AnonymousClass06J.A00(r4);
            if (A002 != 0) {
                if (A002 == 1 || A002 == 2) {
                    if (i2 == 0) {
                        return 1;
                    }
                } else if (A002 != 9) {
                    switch (A002) {
                        case 14:
                        case 15:
                            if (i == i2) {
                                return -1;
                            }
                            i2--;
                            break;
                        case 16:
                        case 17:
                            if (i == i2) {
                                return 1;
                            }
                            i2--;
                            break;
                        case 18:
                            i2++;
                            break;
                    }
                } else {
                    continue;
                }
            } else if (i2 == 0) {
                return -1;
            }
            if (i == 0) {
                i = i2;
            }
        }
        return 0;
    }

    public final CharSequence A02(CharSequence charSequence) {
        AnonymousClass06S r2;
        String str;
        AnonymousClass06S r22;
        String str2;
        AnonymousClass06S r23 = this.A02;
        if (charSequence == null) {
            return null;
        }
        boolean A56 = r23.A56(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if ((this.A00 & 2) != 0) {
            if (A56) {
                r22 = AnonymousClass06U.A05;
            } else {
                r22 = AnonymousClass06U.A04;
            }
            boolean A562 = r22.A56(charSequence, 0, charSequence.length());
            boolean z = this.A01;
            if (!z && (A562 || A00(charSequence) == 1)) {
                str2 = A06;
            } else if (!z || (A562 && A00(charSequence) != -1)) {
                str2 = "";
            } else {
                str2 = A07;
            }
            spannableStringBuilder.append((CharSequence) str2);
        }
        boolean z2 = this.A01;
        if (A56 != z2) {
            char c = 8234;
            if (A56) {
                c = 8235;
            }
            spannableStringBuilder.append(c);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (A56) {
            r2 = AnonymousClass06U.A05;
        } else {
            r2 = AnonymousClass06U.A04;
        }
        boolean A563 = r2.A56(charSequence, 0, charSequence.length());
        if (!z2) {
            if (A563 || A01(charSequence) == 1) {
                str = A06;
            }
            str = "";
        } else {
            if (!A563 || A01(charSequence) == -1) {
                str = A07;
            }
            str = "";
        }
        spannableStringBuilder.append((CharSequence) str);
        return spannableStringBuilder;
    }
}
