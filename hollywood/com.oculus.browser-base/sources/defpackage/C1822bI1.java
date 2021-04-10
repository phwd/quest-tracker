package defpackage;

import java.security.KeyPair;
import java.util.Arrays;

/* renamed from: bI1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1822bI1 {

    /* renamed from: a  reason: collision with root package name */
    public final KeyPair f9531a;
    public final long b;

    public C1822bI1(KeyPair keyPair, long j) {
        this.f9531a = keyPair;
        this.b = j;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1822bI1)) {
            return false;
        }
        C1822bI1 bi1 = (C1822bI1) obj;
        if (this.b != bi1.b || !this.f9531a.getPublic().equals(bi1.f9531a.getPublic()) || !this.f9531a.getPrivate().equals(bi1.f9531a.getPrivate())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f9531a.getPublic(), this.f9531a.getPrivate(), Long.valueOf(this.b)});
    }
}
