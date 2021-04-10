package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* renamed from: AY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AY extends Binder implements IInterface {
    public AY() {
        attachInterface(this, "android.support.customtabs.ICustomTabsService");
    }

    public IBinder asBinder() {
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10, types: [android.graphics.Bitmap] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0104  */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // android.os.Binder
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTransact(int r8, android.os.Parcel r9, android.os.Parcel r10, int r11) {
        /*
        // Method dump skipped, instructions count: 1006
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AY.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
