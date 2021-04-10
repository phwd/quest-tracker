package X;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.resource.SimpleResource;
import java.io.File;

/* renamed from: X.1ca  reason: invalid class name and case insensitive filesystem */
public final class C07521ca extends SimpleResource<File> implements AnonymousClass1fR {
    public final T A00;

    @Override // X.AnonymousClass1fR
    public final void A8u() {
    }

    @Override // X.AnonymousClass1fR
    public final int getSize() {
        return 1;
    }

    @Override // X.AnonymousClass1fR
    @NonNull
    public final Class<T> A4o() {
        return (Class<T>) this.A00.getClass();
    }

    @Override // X.AnonymousClass1fR
    @NonNull
    public final T get() {
        return this.A00;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.io.File */
    /* JADX WARN: Multi-variable type inference failed */
    public C07521ca(File file) {
        AnonymousClass1S2.A00(file);
        this.A00 = file;
    }
}
