package X;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* renamed from: X.1cg  reason: invalid class name and case insensitive filesystem */
public final class C07581cg implements AbstractC07611cj {
    public final C07591ch A00;
    public final AnonymousClass1hX A01;
    public final List<AbstractC08251eH> A02;

    @Override // X.AbstractC07611cj
    @Nullable
    public final Bitmap A2X(BitmapFactory.Options options) throws IOException {
        C06741ax r1 = this.A00.A00;
        r1.reset();
        return BitmapFactory.decodeStream(r1, null, options);
    }

    @Override // X.AbstractC07611cj
    public final int A48() throws IOException {
        List<AbstractC08251eH> list = this.A02;
        C06741ax r1 = this.A00.A00;
        r1.reset();
        return C08201eC.A00(list, r1, this.A01);
    }

    @Override // X.AbstractC07611cj
    public final ImageHeaderParser$ImageType A49() throws IOException {
        List<AbstractC08251eH> list = this.A02;
        C06741ax r2 = this.A00.A00;
        r2.reset();
        AnonymousClass1hX r1 = this.A01;
        if (!r2.markSupported()) {
            r2 = new C06741ax(r2, r1);
        }
        r2.mark(5242880);
        return C08201eC.A01(list, new C08231eF(r2));
    }

    @Override // X.AbstractC07611cj
    public final void AAV() {
        C06741ax r1 = this.A00.A00;
        synchronized (r1) {
            r1.A00 = r1.A05.length;
        }
    }

    public C07581cg(InputStream inputStream, List<AbstractC08251eH> list, ArrayPool arrayPool) {
        AnonymousClass1S2.A00(arrayPool);
        this.A01 = arrayPool;
        AnonymousClass1S2.A00(list);
        this.A02 = list;
        this.A00 = new C07591ch(inputStream, arrayPool);
    }
}
