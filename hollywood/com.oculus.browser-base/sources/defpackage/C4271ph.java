package defpackage;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import java.util.Locale;

/* renamed from: ph  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4271ph {

    /* renamed from: a  reason: collision with root package name */
    public static final AbstractC2903hg1 f11081a;
    public static final String b = Character.toString(8206);
    public static final String c = Character.toString(8207);
    public static final C4271ph d;
    public static final C4271ph e;
    public final boolean f;
    public final int g;
    public final AbstractC2903hg1 h;

    static {
        AbstractC2903hg1 hg1 = AbstractC3244jg1.c;
        f11081a = hg1;
        d = new C4271ph(false, 2, hg1);
        e = new C4271ph(true, 2, hg1);
    }

    public C4271ph(boolean z, int i, AbstractC2903hg1 hg1) {
        this.f = z;
        this.g = i;
        this.h = hg1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        return 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(java.lang.CharSequence r9) {
        /*
        // Method dump skipped, instructions count: 174
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4271ph.a(java.lang.CharSequence):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int b(java.lang.CharSequence r6) {
        /*
            oh r0 = new oh
            r1 = 0
            r0.<init>(r6, r1)
            int r6 = r0.c
            r0.d = r6
            r6 = r1
        L_0x000b:
            r2 = r6
        L_0x000c:
            int r3 = r0.d
            r4 = 1
            if (r3 <= 0) goto L_0x0041
            byte r3 = r0.a()
            if (r3 == 0) goto L_0x0039
            if (r3 == r4) goto L_0x0032
            r5 = 2
            if (r3 == r5) goto L_0x0032
            r5 = 9
            if (r3 == r5) goto L_0x000c
            switch(r3) {
                case 14: goto L_0x002c;
                case 15: goto L_0x002c;
                case 16: goto L_0x0029;
                case 17: goto L_0x0029;
                case 18: goto L_0x0026;
                default: goto L_0x0023;
            }
        L_0x0023:
            if (r6 != 0) goto L_0x000c
            goto L_0x003f
        L_0x0026:
            int r2 = r2 + 1
            goto L_0x000c
        L_0x0029:
            if (r6 != r2) goto L_0x002f
            goto L_0x0034
        L_0x002c:
            if (r6 != r2) goto L_0x002f
            goto L_0x003b
        L_0x002f:
            int r2 = r2 + -1
            goto L_0x000c
        L_0x0032:
            if (r2 != 0) goto L_0x0036
        L_0x0034:
            r1 = r4
            goto L_0x0041
        L_0x0036:
            if (r6 != 0) goto L_0x000c
            goto L_0x003f
        L_0x0039:
            if (r2 != 0) goto L_0x003d
        L_0x003b:
            r1 = -1
            goto L_0x0041
        L_0x003d:
            if (r6 != 0) goto L_0x000c
        L_0x003f:
            r6 = r2
            goto L_0x000b
        L_0x0041:
            return r1
            switch-data {14->0x002c, 15->0x002c, 16->0x0029, 17->0x0029, 18->0x0026, }
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4271ph.b(java.lang.CharSequence):int");
    }

    public static C4271ph c() {
        Locale locale = Locale.getDefault();
        Locale locale2 = AbstractC0630Kg1.f8381a;
        boolean z = true;
        if (TextUtils.getLayoutDirectionFromLocale(locale) != 1) {
            z = false;
        }
        AbstractC2903hg1 hg1 = f11081a;
        if (hg1 == f11081a) {
            return z ? e : d;
        }
        return new C4271ph(z, 2, hg1);
    }

    public String d(String str) {
        String str2;
        AbstractC2903hg1 hg1 = this.h;
        if (str == null) {
            return null;
        }
        boolean b2 = hg1.b(str, 0, str.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String str3 = "";
        if ((this.g & 2) != 0) {
            boolean b3 = (b2 ? AbstractC3244jg1.b : AbstractC3244jg1.f10228a).b(str, 0, str.length());
            if (this.f || (!b3 && a(str) != 1)) {
                str2 = (!this.f || (b3 && a(str) != -1)) ? str3 : c;
            } else {
                str2 = b;
            }
            spannableStringBuilder.append((CharSequence) str2);
        }
        if (b2 != this.f) {
            spannableStringBuilder.append(b2 ? (char) 8235 : 8234);
            spannableStringBuilder.append((CharSequence) str);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append((CharSequence) str);
        }
        boolean b4 = (b2 ? AbstractC3244jg1.b : AbstractC3244jg1.f10228a).b(str, 0, str.length());
        if (!this.f && (b4 || b(str) == 1)) {
            str3 = b;
        } else if (this.f && (!b4 || b(str) == -1)) {
            str3 = c;
        }
        spannableStringBuilder.append((CharSequence) str3);
        return spannableStringBuilder.toString();
    }
}
