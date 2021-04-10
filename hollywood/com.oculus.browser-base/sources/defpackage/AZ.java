package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: AZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AZ extends Binder implements CY {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f7676a = 0;
    public boolean b;

    public AZ() {
        attachInterface(this, "org.chromium.components.browser_ui.photo_picker.IDecoderService");
    }

    public IBinder asBinder() {
        return this;
    }

    public final void c(FY fy, Bundle bundle) {
        try {
            fy.p(bundle);
        } catch (RemoteException e) {
            AbstractC1220Ua0.a("ImageDecoder", "Remote error while replying: " + e, new Object[0]);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    @Override // defpackage.CY
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void g(android.os.Bundle r21, defpackage.FY r22) {
        /*
        // Method dump skipped, instructions count: 303
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AZ.g(android.os.Bundle, FY):void");
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.components.browser_ui.photo_picker.IDecoderService");
            FY fy = null;
            Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("org.chromium.components.browser_ui.photo_picker.IDecoderServiceCallback");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof FY)) {
                    fy = new DY(readStrongBinder);
                } else {
                    fy = (FY) queryLocalInterface;
                }
            }
            g(bundle, fy);
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("org.chromium.components.browser_ui.photo_picker.IDecoderService");
            return true;
        }
    }
}
