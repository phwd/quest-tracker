package X;

import android.graphics.drawable.Animatable;
import javax.annotation.Nullable;

/* renamed from: X.1lG  reason: invalid class name */
public class AnonymousClass1lG<INFO> implements AnonymousClass1l9<INFO> {
    public static final AnonymousClass1l9<Object> NO_OP_LISTENER = new AnonymousClass1lG();

    @Override // X.AnonymousClass1l9
    public void onFailure(String str, Throwable th) {
    }

    @Override // X.AnonymousClass1l9
    public void onFinalImageSet(String str, @Nullable INFO info, @Nullable Animatable animatable) {
    }

    @Override // X.AnonymousClass1l9
    public void onIntermediateImageFailed(String str, Throwable th) {
    }

    @Override // X.AnonymousClass1l9
    public void onIntermediateImageSet(String str, @Nullable INFO info) {
    }

    @Override // X.AnonymousClass1l9
    public void onRelease(String str) {
    }

    @Override // X.AnonymousClass1l9
    public void onSubmit(String str, Object obj) {
    }

    public static <INFO> AnonymousClass1l9<INFO> getNoOpListener() {
        return (AnonymousClass1l9<INFO>) NO_OP_LISTENER;
    }
}
