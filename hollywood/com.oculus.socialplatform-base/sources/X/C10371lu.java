package X;

import android.graphics.drawable.Animatable;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1lu  reason: invalid class name and case insensitive filesystem */
public final class C10371lu extends AnonymousClass1lG {
    public long A00 = -1;
    @Nullable
    public AbstractC10501n0 A01;

    public C10371lu(@Nullable AbstractC10501n0 r3) {
        this.A01 = r3;
    }

    @Override // X.AnonymousClass1l9, X.AnonymousClass1lG
    public final void onFinalImageSet(String str, @Nullable Object obj, @Nullable Animatable animatable) {
        long currentTimeMillis = System.currentTimeMillis();
        AbstractC10501n0 r2 = this.A01;
        if (r2 != null) {
            r2.A77(currentTimeMillis - this.A00);
        }
    }

    @Override // X.AnonymousClass1l9, X.AnonymousClass1lG
    public final void onSubmit(String str, Object obj) {
        this.A00 = System.currentTimeMillis();
    }
}
