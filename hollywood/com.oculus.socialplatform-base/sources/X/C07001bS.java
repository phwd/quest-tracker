package X;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.model.DataUrlLoader;
import java.io.InputStream;

/* renamed from: X.1bS  reason: invalid class name and case insensitive filesystem */
public final class C07001bS<Model> implements AbstractC07401c9<Model, InputStream> {
    public final DataUrlLoader.DataDecoder<InputStream> A00 = new C07021bU(this);

    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<Model, InputStream> A1o(@NonNull C07381c7 r3) {
        return new C06991bR(this.A00);
    }
}
