package defpackage;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;

/* renamed from: RY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RY implements TY {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f8838a;

    public RY(IBinder iBinder) {
        this.f8838a = iBinder;
    }

    @Override // defpackage.TY
    public PendingIntent B() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.f8838a.transact(8, obtain, obtain2, 0)) {
                int i = SY.f8899a;
            }
            obtain2.readException();
            return obtain2.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.TY
    public void I(QY qy) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            obtain.writeStrongBinder(qy != null ? qy.asBinder() : null);
            if (!this.f8838a.transact(4, obtain, obtain2, 0)) {
                int i = SY.f8899a;
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.TY
    public void O() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.f8838a.transact(13, obtain, obtain2, 0)) {
                int i = SY.f8899a;
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.TY
    public void W(QY qy) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            obtain.writeStrongBinder((AbstractBinderC0498Id0) qy);
            if (!this.f8838a.transact(3, obtain, obtain2, 0)) {
                int i = SY.f8899a;
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.TY
    public void Y() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.f8838a.transact(18, obtain, obtain2, 0)) {
                int i = SY.f8899a;
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.TY
    public MediaMetadataCompat Z() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.f8838a.transact(27, obtain, obtain2, 0)) {
                int i = SY.f8899a;
            }
            obtain2.readException();
            return obtain2.readInt() != 0 ? (MediaMetadataCompat) MediaMetadataCompat.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public IBinder asBinder() {
        return this.f8838a;
    }

    @Override // defpackage.TY
    public PlaybackStateCompat b() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.f8838a.transact(28, obtain, obtain2, 0)) {
                int i = SY.f8899a;
            }
            obtain2.readException();
            return obtain2.readInt() != 0 ? (PlaybackStateCompat) PlaybackStateCompat.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // defpackage.TY
    public void stop() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (!this.f8838a.transact(19, obtain, obtain2, 0)) {
                int i = SY.f8899a;
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
