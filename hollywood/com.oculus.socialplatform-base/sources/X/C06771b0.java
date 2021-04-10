package X;

import androidx.annotation.NonNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* renamed from: X.1b0  reason: invalid class name and case insensitive filesystem */
public final class C06771b0<Data> implements AbstractC07051bX<Data> {
    public Data A00;
    public final AbstractC06781b1<Data> A01;
    public final File A02;

    @Override // X.AbstractC07051bX
    public final void cancel() {
    }

    @Override // X.AbstractC07051bX
    public final void A26() {
        Data data = this.A00;
        if (data != null) {
            try {
                this.A01.A28(data);
            } catch (IOException unused) {
            }
        }
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<Data> A3h() {
        return this.A01.A3h();
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final AnonymousClass1fM A3i() {
        return AnonymousClass1fM.LOCAL;
    }

    @Override // X.AbstractC07051bX
    public final void A6H(@NonNull AnonymousClass1cY r3, @NonNull AnonymousClass1Ry<? super Data> r4) {
        try {
            Data A8J = this.A01.A8J(this.A02);
            this.A00 = A8J;
            r4.A6x(A8J);
        } catch (FileNotFoundException e) {
            r4.A7F(e);
        }
    }

    public C06771b0(File file, AbstractC06781b1<Data> r2) {
        this.A02 = file;
        this.A01 = r2;
    }
}
