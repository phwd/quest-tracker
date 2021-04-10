package X;

import android.content.Context;
import java.io.File;
import java.io.IOException;

/* renamed from: X.08d  reason: invalid class name */
public final class AnonymousClass08d extends AnonymousClass0HU {
    public AnonymousClass0cb A00;
    public final File A01;
    public final String A02;
    public final File A03;

    @Override // X.AnonymousClass0HU
    public final AbstractC03240cV A08() throws IOException {
        return new C04230hA(this, this);
    }

    @Override // X.AnonymousClass0HU
    public final byte[] A09() throws IOException {
        return C03190cO.A01(this.A03, super.A03);
    }

    @Override // X.C04530hv, X.AbstractC03170cL
    public final String toString() {
        String str;
        try {
            str = String.valueOf(((C04530hv) this).A00.getCanonicalPath());
        } catch (IOException unused) {
            str = ((C04530hv) this).A00.getName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append("[root = ");
        sb.append(str);
        sb.append(" flags = ");
        sb.append(((C04530hv) this).A01);
        sb.append(" zipSource = ");
        sb.append(this.A01.getPath());
        sb.append(" compressedPath = ");
        sb.append(this.A02);
        sb.append(']');
        return sb.toString();
    }

    public AnonymousClass08d(Context context, AnonymousClass0cb r4) {
        super(context, r4.getOutputDirectoryName());
        File file = new File(super.A03.getApplicationInfo().sourceDir);
        this.A03 = file;
        this.A01 = file;
        this.A00 = r4;
        this.A02 = r4.getAssetPath();
    }
}
