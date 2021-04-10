package X;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.ParcelFileDescriptor;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.util.List;

@RequiresApi(21)
/* renamed from: X.1eB  reason: invalid class name and case insensitive filesystem */
public final class C08191eB implements AbstractC07611cj {
    public final AnonymousClass1e9 A00;
    public final AnonymousClass1hX A01;
    public final List<AbstractC08251eH> A02;

    @Override // X.AbstractC07611cj
    public final void AAV() {
    }

    @Override // X.AbstractC07611cj
    @Nullable
    public final Bitmap A2X(BitmapFactory.Options options) throws IOException {
        return BitmapFactory.decodeFileDescriptor(this.A00.A9Q().getFileDescriptor(), null, options);
    }

    @Override // X.AbstractC07611cj
    public final int A48() throws IOException {
        List<AbstractC08251eH> list = this.A02;
        C08221eE r4 = new C08221eE(this.A00, this.A01);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int A4Z = r4.A4Z(list.get(i));
            if (A4Z != -1) {
                return A4Z;
            }
        }
        return -1;
    }

    @Override // X.AbstractC07611cj
    public final ImageHeaderParser$ImageType A49() throws IOException {
        return C08201eC.A01(this.A02, new C08211eD(this.A00, this.A01));
    }

    public C08191eB(ParcelFileDescriptor parcelFileDescriptor, List<AbstractC08251eH> list, ArrayPool arrayPool) {
        AnonymousClass1S2.A00(arrayPool);
        this.A01 = arrayPool;
        AnonymousClass1S2.A00(list);
        this.A02 = list;
        this.A00 = new AnonymousClass1e9(parcelFileDescriptor);
    }
}
