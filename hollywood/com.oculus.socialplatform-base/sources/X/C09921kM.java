package X;

import android.net.Uri;
import javax.annotation.Nullable;

/* renamed from: X.1kM  reason: invalid class name and case insensitive filesystem */
public final class C09921kM {
    public final int A00;
    public Uri A01 = null;
    @Nullable
    public AnonymousClass0PH A02 = null;
    public AnonymousClass0PI A03 = AnonymousClass0PI.A03;
    public AnonymousClass0PK A04 = AnonymousClass0PK.HIGH;
    @Nullable
    public AnonymousClass0PO A05 = null;
    @Nullable
    public final AnonymousClass1mI A06;
    public AnonymousClass1lL A07 = AnonymousClass1lL.DEFAULT;
    public AnonymousClass1l4 A08 = AnonymousClass1l4.FULL_FETCH;
    @Nullable
    public Boolean A09 = null;
    public boolean A0A = false;
    public boolean A0B = false;

    public final AnonymousClass1kA A00() {
        String scheme;
        Uri uri = this.A01;
        if (uri != null) {
            if ("res".equals(uri.getScheme())) {
                if (!this.A01.isAbsolute()) {
                    throw new AnonymousClass1YD("Resource URI path must be absolute.");
                } else if (!this.A01.getPath().isEmpty()) {
                    try {
                        Integer.parseInt(this.A01.getPath().substring(1));
                    } catch (NumberFormatException unused) {
                        throw new AnonymousClass1YD("Resource URI path must be a resource id.");
                    }
                } else {
                    throw new AnonymousClass1YD("Resource URI must not be empty");
                }
            }
            Uri uri2 = this.A01;
            if (uri2 == null) {
                scheme = null;
            } else {
                scheme = uri2.getScheme();
            }
            if (!"asset".equals(scheme) || this.A01.isAbsolute()) {
                return new AnonymousClass1kA(this);
            }
            throw new AnonymousClass1YD("Asset URI path must be absolute.");
        }
        throw new AnonymousClass1YD("Source must be set!");
    }
}
