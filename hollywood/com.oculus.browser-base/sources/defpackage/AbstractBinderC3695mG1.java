package defpackage;

import java.lang.ref.WeakReference;

/* renamed from: mG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC3695mG1 extends AbstractBinderC5737yF1 {
    public static final WeakReference b = new WeakReference(null);
    public WeakReference c = b;

    public AbstractBinderC3695mG1(byte[] bArr) {
        super(bArr);
    }

    @Override // defpackage.AbstractBinderC5737yF1
    public final byte[] d() {
        byte[] bArr;
        synchronized (this) {
            bArr = (byte[]) this.c.get();
            if (bArr == null) {
                bArr = e0();
                this.c = new WeakReference(bArr);
            }
        }
        return bArr;
    }

    public abstract byte[] e0();
}
