package X;

import android.content.Context;
import java.io.File;
import java.io.IOException;

/* renamed from: X.2M  reason: invalid class name */
public final class AnonymousClass2M extends VS {
    public KZ A00;
    public final File A01;
    public final File A02;
    public final String A03;

    @Override // X.C0965ph, X.KK
    public final String toString() {
        String str;
        try {
            str = String.valueOf(((C0965ph) this).A00.getCanonicalPath());
        } catch (IOException unused) {
            str = ((C0965ph) this).A00.getName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append("[root = ");
        sb.append(str);
        sb.append(" flags = ");
        sb.append(((C0965ph) this).A01);
        sb.append(" zipSource = ");
        sb.append(this.A02.getPath());
        sb.append(" compressedPath = ");
        sb.append(this.A03);
        sb.append(']');
        return sb.toString();
    }

    public AnonymousClass2M(Context context, KZ kz) {
        super(context, kz.getOutputDirectoryName());
        File file = new File(super.A02.getApplicationInfo().sourceDir);
        this.A01 = file;
        this.A02 = file;
        this.A00 = kz;
        this.A03 = kz.getAssetPath();
    }
}
