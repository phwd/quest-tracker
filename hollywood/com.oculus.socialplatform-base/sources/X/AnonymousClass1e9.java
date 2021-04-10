package X;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.io.IOException;

/* renamed from: X.1e9  reason: invalid class name */
public final class AnonymousClass1e9 implements AbstractC08171e8<ParcelFileDescriptor> {
    public final C08181eA A00;

    @Override // X.AbstractC08171e8
    public final void A26() {
    }

    @NonNull
    @RequiresApi(21)
    /* renamed from: A00 */
    public final ParcelFileDescriptor A9Q() throws IOException {
        try {
            ParcelFileDescriptor parcelFileDescriptor = this.A00.A00;
            Os.lseek(parcelFileDescriptor.getFileDescriptor(), 0, OsConstants.SEEK_SET);
            return parcelFileDescriptor;
        } catch (ErrnoException e) {
            throw new IOException(e);
        }
    }

    @RequiresApi(21)
    public AnonymousClass1e9(ParcelFileDescriptor parcelFileDescriptor) {
        this.A00 = new C08181eA(parcelFileDescriptor);
    }
}
