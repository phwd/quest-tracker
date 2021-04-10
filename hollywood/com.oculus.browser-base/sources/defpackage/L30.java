package defpackage;

import java.io.IOException;

/* renamed from: L30  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L30 extends IOException {
    public static final /* synthetic */ int F = 0;

    public L30(String str) {
        super(str);
    }

    public static L30 a() {
        return new L30("Protocol message contained an invalid tag (zero).");
    }

    public static L30 b() {
        return new L30("Protocol message had invalid UTF-8.");
    }

    public static L30 c() {
        return new L30("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static L30 d() {
        return new L30("Failed to parse the message.");
    }

    public static L30 e() {
        return new L30("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }
}
