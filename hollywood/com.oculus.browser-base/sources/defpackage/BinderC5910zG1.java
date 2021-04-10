package defpackage;

import java.util.Arrays;

/* renamed from: zG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BinderC5910zG1 extends AbstractBinderC5737yF1 {
    public final byte[] b;

    public BinderC5910zG1(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.b = bArr;
    }

    @Override // defpackage.AbstractBinderC5737yF1
    public final byte[] d() {
        return this.b;
    }
}
