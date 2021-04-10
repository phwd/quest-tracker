package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0yx  reason: invalid class name and case insensitive filesystem */
public final class C09260yx extends IOException {
    public EnumC09190yp mDNSResolveStatus;

    public C09260yx(EnumC09190yp r3) {
        super("Status: " + r3);
        this.mDNSResolveStatus = r3;
    }
}
