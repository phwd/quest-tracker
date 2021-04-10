package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.ParcelableVolumeInfo;
import android.support.v4.media.session.PlaybackStateCompat;

/* renamed from: PY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PY implements QY {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f8697a;

    public PY(IBinder iBinder) {
        this.f8697a = iBinder;
    }

    public IBinder asBinder() {
        return this.f8697a;
    }

    @Override // defpackage.QY
    public void l() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            if (!this.f8697a.transact(2, obtain, null, 1)) {
                int i = AbstractBinderC0498Id0.f8236a;
            }
        } finally {
            obtain.recycle();
        }
    }

    @Override // defpackage.QY
    public void n(MediaMetadataCompat mediaMetadataCompat) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            if (mediaMetadataCompat != null) {
                obtain.writeInt(1);
                obtain.writeBundle(mediaMetadataCompat.f9451J);
            } else {
                obtain.writeInt(0);
            }
            if (!this.f8697a.transact(4, obtain, null, 1)) {
                int i = AbstractBinderC0498Id0.f8236a;
            }
        } finally {
            obtain.recycle();
        }
    }

    @Override // defpackage.QY
    public void u0(PlaybackStateCompat playbackStateCompat) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            if (playbackStateCompat != null) {
                obtain.writeInt(1);
                playbackStateCompat.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            if (!this.f8697a.transact(3, obtain, null, 1)) {
                int i = AbstractBinderC0498Id0.f8236a;
            }
        } finally {
            obtain.recycle();
        }
    }

    @Override // defpackage.QY
    public void w0(ParcelableVolumeInfo parcelableVolumeInfo) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            if (parcelableVolumeInfo != null) {
                obtain.writeInt(1);
                parcelableVolumeInfo.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            if (!this.f8697a.transact(8, obtain, null, 1)) {
                int i = AbstractBinderC0498Id0.f8236a;
            }
        } finally {
            obtain.recycle();
        }
    }
}
