package X;

import android.text.SpannableStringBuilder;
import com.squareup.okhttp.internal.framed.Hpack;

/* renamed from: X.6K  reason: invalid class name */
public final class AnonymousClass6K {
    public static final AnonymousClass6K A03;
    public static final AnonymousClass6K A04;
    public static final AnonymousClass6S A05 = AnonymousClass6U.A01;
    public static final String A06 = Character.toString(8206);
    public static final String A07 = Character.toString(8207);
    public final int A00 = 2;
    public final boolean A01;
    public final AnonymousClass6S A02;

    public AnonymousClass6K(boolean z, AnonymousClass6S r3) {
        this.A01 = z;
        this.A02 = r3;
    }

    public static int A00(CharSequence charSequence) {
        byte directionality;
        AnonymousClass6J r4 = new AnonymousClass6J(charSequence);
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
                            directionality = AnonymousClass6J.A04[c];
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
            switch (AnonymousClass6J.A00(r4)) {
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
        AnonymousClass6S r2 = A05;
        A03 = new AnonymousClass6K(false, r2);
        A04 = new AnonymousClass6K(true, r2);
    }

    public static int A01(CharSequence charSequence) {
        AnonymousClass6J r4 = new AnonymousClass6J(charSequence);
        r4.A01 = r4.A02;
        int i = 0;
        int i2 = 0;
        while (r4.A01 > 0) {
            byte A002 = AnonymousClass6J.A00(r4);
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
        AnonymousClass6S r2;
        String str;
        AnonymousClass6S r22;
        String str2;
        AnonymousClass6S r23 = this.A02;
        if (charSequence == null) {
            return null;
        }
        boolean A3H = r23.A3H(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if ((this.A00 & 2) != 0) {
            if (A3H) {
                r22 = AnonymousClass6U.A05;
            } else {
                r22 = AnonymousClass6U.A04;
            }
            boolean A3H2 = r22.A3H(charSequence, 0, charSequence.length());
            boolean z = this.A01;
            if (!z && (A3H2 || A00(charSequence) == 1)) {
                str2 = A06;
            } else if (!z || (A3H2 && A00(charSequence) != -1)) {
                str2 = "";
            } else {
                str2 = A07;
            }
            spannableStringBuilder.append((CharSequence) str2);
        }
        boolean z2 = this.A01;
        if (A3H != z2) {
            char c = 8234;
            if (A3H) {
                c = 8235;
            }
            spannableStringBuilder.append(c);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (A3H) {
            r2 = AnonymousClass6U.A05;
        } else {
            r2 = AnonymousClass6U.A04;
        }
        boolean A3H3 = r2.A3H(charSequence, 0, charSequence.length());
        if (!z2) {
            if (A3H3 || A01(charSequence) == 1) {
                str = A06;
            }
            str = "";
        } else {
            if (!A3H3 || A01(charSequence) == -1) {
                str = A07;
            }
            str = "";
        }
        spannableStringBuilder.append((CharSequence) str);
        return spannableStringBuilder;
    }
}
