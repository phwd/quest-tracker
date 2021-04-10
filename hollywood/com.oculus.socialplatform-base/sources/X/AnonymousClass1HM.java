package X;

import com.facebook.infer.annotation.Nullsafe;
import java.lang.reflect.InvocationTargetException;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1HM  reason: invalid class name */
public final class AnonymousClass1HM implements AbstractC01080Pu {
    public final int A00 = 2048;
    public final boolean A01 = true;

    @Override // X.AbstractC01080Pu
    public final AbstractC01070Pt createImageTranscoder(AnonymousClass0Oj r8, boolean z) {
        int i = this.A00;
        boolean z2 = this.A01;
        try {
            Class<?> cls = Class.forName("com.facebook.imagepipeline.nativecode.NativeJpegTranscoderFactory");
            Class cls2 = Integer.TYPE;
            Class cls3 = Boolean.TYPE;
            AbstractC01070Pt createImageTranscoder = ((AbstractC01080Pu) cls.getConstructor(cls2, cls3, cls3).newInstance(Integer.valueOf(i), false, Boolean.valueOf(z2))).createImageTranscoder(r8, z);
            if (createImageTranscoder == null) {
                return new AnonymousClass1HN(i).createImageTranscoder(r8, z);
            }
            return createImageTranscoder;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            throw new RuntimeException("Dependency ':native-imagetranscoder' is needed to use the default native image transcoder.", e);
        }
    }
}
