package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* renamed from: hZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2881hZ implements AbstractC3222jZ {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f10078a;

    public C2881hZ(IBinder iBinder) {
        this.f10078a = iBinder;
    }

    @Override // defpackage.AbstractC3222jZ
    public void P(AbstractC5947zY zYVar, String str, Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.customtabs.IPostMessageService");
            obtain.writeStrongBinder(zYVar != null ? ((C5607xY) zYVar).f11613a : null);
            obtain.writeString(str);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            if (!this.f10078a.transact(3, obtain, obtain2, 0)) {
                int i = AbstractBinderC3052iZ.f10143a;
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public IBinder asBinder() {
        return this.f10078a;
    }

    @Override // defpackage.AbstractC3222jZ
    public void r0(AbstractC5947zY zYVar, Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.customtabs.IPostMessageService");
            obtain.writeStrongBinder(zYVar != null ? ((C5607xY) zYVar).f11613a : null);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            if (!this.f10078a.transact(2, obtain, obtain2, 0)) {
                int i = AbstractBinderC3052iZ.f10143a;
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
