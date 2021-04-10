package X;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.IOException;

@RequiresApi(21)
/* renamed from: X.1ce  reason: invalid class name and case insensitive filesystem */
public final class C07561ce implements AnonymousClass1dN<ParcelFileDescriptor, Bitmap> {
    public final AnonymousClass1gC A00;

    /* Return type fixed from 'X.1fR' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AnonymousClass1dN
    @Nullable
    public final AnonymousClass1fR<Bitmap> A2V(@NonNull ParcelFileDescriptor parcelFileDescriptor, int i, int i2, @NonNull AnonymousClass1cO r12) throws IOException {
        AnonymousClass1gC r2 = this.A00;
        return AnonymousClass1gC.A01(r2, new C08191eB(parcelFileDescriptor, r2.A04, r2.A02), i, i2, r12, AnonymousClass1gC.A0A);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull ParcelFileDescriptor parcelFileDescriptor, @NonNull AnonymousClass1cO r3) throws IOException {
        return true;
    }

    public C07561ce(AnonymousClass1gC r1) {
        this.A00 = r1;
    }
}
