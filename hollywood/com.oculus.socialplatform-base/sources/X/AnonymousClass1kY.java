package X;

import android.graphics.drawable.Animatable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1kY  reason: invalid class name */
public class AnonymousClass1kY<INFO> implements AnonymousClass1l9<INFO> {
    public final List<AnonymousClass1l9<? super INFO>> A00 = new ArrayList(2);

    private synchronized void A00(String str, Throwable th) {
        Log.e("FdingControllerListener", str, th);
    }

    public final synchronized void A01(AnonymousClass1l9<? super INFO> r2) {
        this.A00.add(r2);
    }

    @Override // X.AnonymousClass1l9
    public final synchronized void onFailure(String str, Throwable th) {
        List<AnonymousClass1l9<? super INFO>> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                AnonymousClass1l9<? super INFO> r0 = list.get(i);
                if (r0 != null) {
                    r0.onFailure(str, th);
                }
            } catch (Exception e) {
                A00("InternalListener exception in onFailure", e);
            }
        }
    }

    @Override // X.AnonymousClass1l9
    public final synchronized void onFinalImageSet(String str, @Nullable INFO info, @Nullable Animatable animatable) {
        List<AnonymousClass1l9<? super INFO>> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                AnonymousClass1l9<? super INFO> r0 = list.get(i);
                if (r0 != null) {
                    r0.onFinalImageSet(str, info, animatable);
                }
            } catch (Exception e) {
                A00("InternalListener exception in onFinalImageSet", e);
            }
        }
    }

    @Override // X.AnonymousClass1l9
    public final synchronized void onRelease(String str) {
        List<AnonymousClass1l9<? super INFO>> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                AnonymousClass1l9<? super INFO> r0 = list.get(i);
                if (r0 != null) {
                    r0.onRelease(str);
                }
            } catch (Exception e) {
                A00("InternalListener exception in onRelease", e);
            }
        }
    }

    @Override // X.AnonymousClass1l9
    public final synchronized void onSubmit(String str, Object obj) {
        List<AnonymousClass1l9<? super INFO>> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                AnonymousClass1l9<? super INFO> r0 = list.get(i);
                if (r0 != null) {
                    r0.onSubmit(str, obj);
                }
            } catch (Exception e) {
                A00("InternalListener exception in onSubmit", e);
            }
        }
    }

    @Override // X.AnonymousClass1l9
    public final void onIntermediateImageFailed(String str, Throwable th) {
        List<AnonymousClass1l9<? super INFO>> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                AnonymousClass1l9<? super INFO> r0 = list.get(i);
                if (r0 != null) {
                    r0.onIntermediateImageFailed(str, th);
                }
            } catch (Exception e) {
                A00("InternalListener exception in onIntermediateImageFailed", e);
            }
        }
    }

    @Override // X.AnonymousClass1l9
    public final void onIntermediateImageSet(String str, @Nullable INFO info) {
        List<AnonymousClass1l9<? super INFO>> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                AnonymousClass1l9<? super INFO> r0 = list.get(i);
                if (r0 != null) {
                    r0.onIntermediateImageSet(str, info);
                }
            } catch (Exception e) {
                A00("InternalListener exception in onIntermediateImageSet", e);
            }
        }
    }
}
