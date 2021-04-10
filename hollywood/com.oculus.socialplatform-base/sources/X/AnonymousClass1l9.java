package X;

import android.graphics.drawable.Animatable;
import javax.annotation.Nullable;

/* renamed from: X.1l9  reason: invalid class name */
public interface AnonymousClass1l9<INFO> {
    void onFailure(String str, Throwable th);

    void onFinalImageSet(String str, @Nullable INFO info, @Nullable Animatable animatable);

    void onIntermediateImageFailed(String str, Throwable th);

    void onIntermediateImageSet(String str, @Nullable INFO info);

    void onRelease(String str);

    void onSubmit(String str, Object obj);
}
