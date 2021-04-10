package X;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.model.DataUrlLoader;

/* renamed from: X.1bR  reason: invalid class name and case insensitive filesystem */
public final class C06991bR<Model, Data> implements AbstractC07011bT<Model, Data> {
    public final DataUrlLoader.DataDecoder<Data> A00;

    @Override // X.AbstractC07011bT
    public final C07091bb<Data> A1r(@NonNull Model model, int i, int i2, @NonNull AnonymousClass1cO r8) {
        return new C07091bb<>(new AnonymousClass1S3(model), new C06761az(model.toString(), this.A00));
    }

    public C06991bR(DataUrlLoader.DataDecoder<Data> dataDecoder) {
        this.A00 = dataDecoder;
    }

    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull Model model) {
        return model.toString().startsWith("data:image");
    }
}
