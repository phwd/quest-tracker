package X;

import androidx.annotation.Nullable;
import java.io.IOException;

/* renamed from: X.1Os  reason: invalid class name */
public final class AnonymousClass1Os implements Appendable {
    public boolean A00 = true;
    public final Appendable A01;

    public AnonymousClass1Os(Appendable appendable) {
        this.A01 = appendable;
    }

    @Override // java.lang.Appendable
    public final Appendable append(char c) throws IOException {
        boolean z = false;
        if (this.A00) {
            this.A00 = false;
            this.A01.append("  ");
        }
        if (c == '\n') {
            z = true;
        }
        this.A00 = z;
        this.A01.append(c);
        return this;
    }

    @Override // java.lang.Appendable
    public final Appendable append(@Nullable CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            charSequence = "";
        }
        append(charSequence, 0, charSequence.length());
        return this;
    }

    @Override // java.lang.Appendable
    public final Appendable append(@Nullable CharSequence charSequence, int i, int i2) throws IOException {
        if (charSequence == null) {
            charSequence = "";
        }
        boolean z = false;
        if (this.A00) {
            this.A00 = false;
            this.A01.append("  ");
        }
        if (charSequence.length() > 0 && charSequence.charAt(i2 - 1) == '\n') {
            z = true;
        }
        this.A00 = z;
        this.A01.append(charSequence, i, i2);
        return this;
    }
}
