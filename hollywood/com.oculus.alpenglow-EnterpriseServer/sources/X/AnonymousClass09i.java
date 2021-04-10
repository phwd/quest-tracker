package X;

import android.text.SpannableStringBuilder;
import com.squareup.okhttp.internal.framed.Hpack;

/* renamed from: X.09i  reason: invalid class name */
public final class AnonymousClass09i {
    public static final AnonymousClass09i A03;
    public static final AnonymousClass09i A04;
    public static final AnonymousClass09q A05 = AnonymousClass09s.A01;
    public static final String A06 = Character.toString(8206);
    public static final String A07 = Character.toString(8207);
    public final int A00 = 2;
    public final boolean A01;
    public final AnonymousClass09q A02;

    public AnonymousClass09i(boolean z, AnonymousClass09q r3) {
        this.A01 = z;
        this.A02 = r3;
    }

    public static int A00(CharSequence charSequence) {
        byte directionality;
        AnonymousClass09h r4 = new AnonymousClass09h(charSequence);
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
                            directionality = AnonymousClass09h.A04[c];
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
                                case Hpack.PREFIX_4_BITS /*{ENCODED_INT: 15}*/:
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
            switch (AnonymousClass09h.A00(r4)) {
                case 14:
                case Hpack.PREFIX_4_BITS /*{ENCODED_INT: 15}*/:
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
        AnonymousClass09q r2 = A05;
        A03 = new AnonymousClass09i(false, r2);
        A04 = new AnonymousClass09i(true, r2);
    }

    public static int A01(CharSequence charSequence) {
        AnonymousClass09h r4 = new AnonymousClass09h(charSequence);
        r4.A01 = r4.A02;
        int i = 0;
        int i2 = 0;
        while (r4.A01 > 0) {
            byte A002 = AnonymousClass09h.A00(r4);
            if (A002 != 0) {
                if (A002 == 1 || A002 == 2) {
                    if (i2 == 0) {
                        return 1;
                    }
                } else if (A002 != 9) {
                    switch (A002) {
                        case 14:
                        case Hpack.PREFIX_4_BITS /*{ENCODED_INT: 15}*/:
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
        AnonymousClass09q r2;
        String str;
        AnonymousClass09q r22;
        String str2;
        AnonymousClass09q r23 = this.A02;
        if (charSequence == null) {
            return null;
        }
        boolean A5Y = r23.A5Y(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if ((this.A00 & 2) != 0) {
            if (A5Y) {
                r22 = AnonymousClass09s.A05;
            } else {
                r22 = AnonymousClass09s.A04;
            }
            boolean A5Y2 = r22.A5Y(charSequence, 0, charSequence.length());
            boolean z = this.A01;
            if (!z && (A5Y2 || A00(charSequence) == 1)) {
                str2 = A06;
            } else if (!z || (A5Y2 && A00(charSequence) != -1)) {
                str2 = "";
            } else {
                str2 = A07;
            }
            spannableStringBuilder.append((CharSequence) str2);
        }
        boolean z2 = this.A01;
        if (A5Y != z2) {
            char c = 8234;
            if (A5Y) {
                c = 8235;
            }
            spannableStringBuilder.append(c);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (A5Y) {
            r2 = AnonymousClass09s.A05;
        } else {
            r2 = AnonymousClass09s.A04;
        }
        boolean A5Y3 = r2.A5Y(charSequence, 0, charSequence.length());
        if (!z2) {
            if (A5Y3 || A01(charSequence) == 1) {
                str = A06;
            }
            str = "";
        } else {
            if (!A5Y3 || A01(charSequence) == -1) {
                str = A07;
            }
            str = "";
        }
        spannableStringBuilder.append((CharSequence) str);
        return spannableStringBuilder;
    }
}
