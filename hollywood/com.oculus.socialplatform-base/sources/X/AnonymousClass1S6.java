package X;

import android.content.res.AssetManager;
import androidx.annotation.NonNull;
import java.io.IOException;

/* renamed from: X.1S6  reason: invalid class name */
public abstract class AnonymousClass1S6<T> implements AbstractC07051bX<T> {
    public T A00;
    public final AssetManager A01;
    public final String A02;

    @Override // X.AbstractC07051bX
    public final void cancel() {
    }

    @Override // X.AbstractC07051bX
    public final void A26() {
        T t = this.A00;
        if (t != null) {
            try {
                if (!(this instanceof AnonymousClass1S5)) {
                    t.close();
                } else {
                    t.close();
                }
            } catch (IOException unused) {
            }
        }
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final AnonymousClass1fM A3i() {
        return AnonymousClass1fM.LOCAL;
    }

    @Override // X.AbstractC07051bX
    public final void A6H(@NonNull AnonymousClass1cY r4, @NonNull AnonymousClass1Ry<? super T> r5) {
        try {
            AssetManager assetManager = this.A01;
            String str = this.A02;
            T t = !(this instanceof AnonymousClass1S5) ? (T) assetManager.openFd(str).getParcelFileDescriptor() : (T) assetManager.open(str);
            this.A00 = t;
            r5.A6x(t);
        } catch (IOException e) {
            r5.A7F(e);
        }
    }

    public AnonymousClass1S6(AssetManager assetManager, String str) {
        this.A01 = assetManager;
        this.A02 = str;
    }
}
