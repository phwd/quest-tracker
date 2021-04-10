package X;

import com.facebook.infer.annotation.Nullsafe;
import java.lang.reflect.InvocationTargetException;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1lV  reason: invalid class name */
public final class AnonymousClass1lV implements AnonymousClass1lX {
    public final int A00 = 2048;
    public final boolean A01 = true;

    @Override // X.AnonymousClass1lX
    public final AnonymousClass1s3 createImageTranscoder(AnonymousClass1tL r10, boolean z) {
        int i = this.A00;
        boolean z2 = this.A01;
        try {
            Class<?> cls = Class.forName("com.facebook.imagepipeline.nativecode.NativeJpegTranscoderFactory");
            Class<?> cls2 = Boolean.TYPE;
            AnonymousClass1s3 createImageTranscoder = ((AnonymousClass1lX) cls.getConstructor(Integer.TYPE, cls2, cls2).newInstance(Integer.valueOf(i), false, Boolean.valueOf(z2))).createImageTranscoder(r10, z);
            if (createImageTranscoder == null) {
                return new AnonymousClass1lW(i).createImageTranscoder(r10, z);
            }
            return createImageTranscoder;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            throw new RuntimeException("Dependency ':native-imagetranscoder' is needed to use the default native image transcoder.", e);
        }
    }
}
