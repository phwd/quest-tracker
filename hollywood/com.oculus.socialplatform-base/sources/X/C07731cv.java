package X;

import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(21)
/* renamed from: X.1cv  reason: invalid class name and case insensitive filesystem */
public final class C07731cv implements AbstractC07621ck<ParcelFileDescriptor> {
    /* Return type fixed from 'X.1e8' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07621ck
    @NonNull
    public final AbstractC08171e8<ParcelFileDescriptor> A1n(@NonNull ParcelFileDescriptor parcelFileDescriptor) {
        return new AnonymousClass1e9(parcelFileDescriptor);
    }

    @Override // X.AbstractC07621ck
    @NonNull
    public final Class<ParcelFileDescriptor> A3h() {
        return ParcelFileDescriptor.class;
    }
}
