package defpackage;

import java.text.BreakIterator;
import java.util.regex.Pattern;

/* renamed from: kS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3380kS0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f10279a = Pattern.compile("[\\p{javaSpaceChar}\\s]+");
    public String b;
    public int c;
    public String d;
    public int e;
    public int f;

    public int a(int i, int i2, BreakIterator breakIterator) {
        int i3 = 0;
        while (i > i2) {
            int preceding = breakIterator.preceding(i);
            if (!d(preceding, i)) {
                i3++;
            }
            i = preceding;
        }
        return i3;
    }

    public int b(int i, int i2, BreakIterator breakIterator) {
        int i3 = 0;
        while (i < i2) {
            int following = breakIterator.following(i);
            if (!d(i, following)) {
                i3++;
            }
            i = following;
        }
        return i3;
    }

    public boolean c(int i, int i2, int[] iArr) {
        iArr[1] = 0;
        iArr[0] = 0;
        int i3 = this.c;
        int i4 = i - i3;
        int i5 = i2 - i3;
        if (i4 >= i5 || i4 < 0 || i4 >= this.b.length() || i5 <= 0 || i5 > this.b.length()) {
            return false;
        }
        int i6 = this.f - this.c;
        BreakIterator wordInstance = BreakIterator.getWordInstance();
        wordInstance.setText(this.b);
        if (i4 <= i6) {
            iArr[0] = -b(i4, i6, wordInstance);
        } else {
            iArr[0] = a(i4, i6, wordInstance);
            if (!wordInstance.isBoundary(i4) && !d(wordInstance.preceding(i4), wordInstance.following(i4))) {
                iArr[0] = iArr[0] - 1;
            }
        }
        if (i5 <= i6) {
            iArr[1] = -b(i5, i6, wordInstance);
        } else {
            iArr[1] = a(i5, i6, wordInstance);
        }
        return true;
    }

    public boolean d(int i, int i2) {
        return f10279a.matcher(this.b.substring(i, i2)).matches();
    }

    public boolean e(String str, int i) {
        boolean z;
        if (this.b == null) {
            this.d = str;
            this.e = i;
            this.b = str;
            this.c = i;
            return true;
        }
        int length = str.length() + i;
        int length2 = this.d.length() + this.e;
        int i2 = this.e;
        if (i2 > i ? length > i2 : i < length2) {
            int max = Math.max(i2, i);
            z = this.d.regionMatches(max - this.e, str, max - i, Math.min(length2, length) - max);
        } else {
            z = false;
        }
        if (this.e == length || length2 == i) {
            z = true;
        }
        if (!z) {
            this.b = null;
            this.d = null;
            return false;
        }
        this.d = str;
        this.e = i;
        int length3 = str.length() + i;
        int length4 = this.b.length() + this.c;
        if (i < this.c) {
            this.b = str.substring(0, this.c - i) + this.b;
            this.c = i;
        }
        if (length3 > length4) {
            String str2 = this.b + str.substring(length4 - i, str.length());
            int i3 = this.c;
            this.b = str2;
            this.c = i3;
        }
        return true;
    }
}
