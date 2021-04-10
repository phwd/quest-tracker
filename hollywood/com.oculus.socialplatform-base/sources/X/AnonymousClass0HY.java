package X;

import android.content.Context;
import java.io.File;
import java.io.IOException;

/* renamed from: X.0HY  reason: invalid class name */
public final class AnonymousClass0HY extends AnonymousClass0T3 {
    public EnumC03110lH A00;
    public final File A01;
    public final String A02;
    public final File A03;

    @Override // X.AnonymousClass0T3
    public final AnonymousClass0lB A08() throws IOException {
        return new C02420ik(this, this);
    }

    @Override // X.AnonymousClass0T3
    public final byte[] A09() throws IOException {
        return AnonymousClass0l4.A01(this.A03, super.A03);
    }

    @Override // X.AnonymousClass0jM, X.AnonymousClass0l1
    public final String toString() {
        String str;
        try {
            str = String.valueOf(((AnonymousClass0jM) this).A00.getCanonicalPath());
        } catch (IOException unused) {
            str = ((AnonymousClass0jM) this).A00.getName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append("[root = ");
        sb.append(str);
        sb.append(" flags = ");
        sb.append(((AnonymousClass0jM) this).A01);
        sb.append(" zipSource = ");
        sb.append(this.A01.getPath());
        sb.append(" compressedPath = ");
        sb.append(this.A02);
        sb.append(']');
        return sb.toString();
    }

    public AnonymousClass0HY(Context context, EnumC03110lH r4) {
        super(context, r4.getOutputDirectoryName());
        File file = new File(super.A03.getApplicationInfo().sourceDir);
        this.A03 = file;
        this.A01 = file;
        this.A00 = r4;
        this.A02 = r4.getAssetPath();
    }
}
