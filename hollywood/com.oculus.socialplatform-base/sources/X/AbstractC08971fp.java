package X;

import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.TransitionFactory;

/* renamed from: X.1fp  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC08971fp<CHILD extends TransitionOptions<CHILD, TranscodeType>, TranscodeType> implements Cloneable {
    public TransitionFactory<? super TranscodeType> A00 = ((TransitionFactory<? super TranscodeType>) AnonymousClass1fW.A00);

    /* renamed from: A00 */
    public final CHILD clone() {
        try {
            return (AbstractC08971fp) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
