package com.facebook.ui.emoji.model;

import X.AnonymousClass0WB;
import android.os.Parcelable;
import android.text.TextUtils;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Emoji implements Parcelable {
    public static final AnonymousClass0WB<char[]> A00 = new AnonymousClass0WB<>(2);
    public static final AnonymousClass0WB<int[]> A01 = new AnonymousClass0WB<>(2);

    public final String toString() {
        String str = ((BasicEmoji) this).A00;
        StringBuilder sb = new StringBuilder(11);
        int i = 0;
        boolean z = true;
        while (i < str.length()) {
            if (z) {
                z = false;
            } else {
                sb.append('_');
            }
            int codePointAt = Character.codePointAt(str, i);
            sb.append(Integer.toHexString(codePointAt));
            i += Character.charCount(codePointAt);
        }
        return sb.toString();
    }

    public static String A00(CharSequence charSequence, int i, int i2) {
        AnonymousClass0WB<char[]> r3 = A00;
        char[] A19 = r3.A19();
        if (A19 == null) {
            A19 = new char[19];
        }
        TextUtils.getChars(charSequence, i, i2, A19, 0);
        String str = new String(A19, 0, i2 - i);
        r3.A8z(A19);
        return str;
    }
}
