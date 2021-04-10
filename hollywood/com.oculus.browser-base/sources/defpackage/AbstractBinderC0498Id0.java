package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat$QueueItem;
import android.support.v4.media.session.ParcelableVolumeInfo;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.oculus.os.Version;
import java.lang.ref.WeakReference;

/* renamed from: Id0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC0498Id0 extends Binder implements QY {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f8236a = 0;
    public final WeakReference b;

    public AbstractBinderC0498Id0(AbstractC0559Jd0 jd0) {
        attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
        this.b = new WeakReference(jd0);
    }

    public static QY c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
        if (queryLocalInterface == null || !(queryLocalInterface instanceof QY)) {
            return new PY(iBinder);
        }
        return (QY) queryLocalInterface;
    }

    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1598968902) {
            Bundle bundle = null;
            ParcelableVolumeInfo parcelableVolumeInfo = null;
            Bundle bundle2 = null;
            CharSequence charSequence = null;
            MediaMetadataCompat mediaMetadataCompat = null;
            PlaybackStateCompat playbackStateCompat = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    String readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    AbstractC0559Jd0 jd0 = (AbstractC0559Jd0) this.b.get();
                    if (jd0 == null) {
                        return true;
                    }
                    jd0.d(1, readString, bundle);
                    return true;
                case 2:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    l();
                    return true;
                case 3:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (parcel.readInt() != 0) {
                        playbackStateCompat = (PlaybackStateCompat) PlaybackStateCompat.CREATOR.createFromParcel(parcel);
                    }
                    u0(playbackStateCompat);
                    return true;
                case 4:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (parcel.readInt() != 0) {
                        mediaMetadataCompat = (MediaMetadataCompat) MediaMetadataCompat.CREATOR.createFromParcel(parcel);
                    }
                    n(mediaMetadataCompat);
                    return true;
                case 5:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    T(parcel.createTypedArrayList(MediaSessionCompat$QueueItem.CREATOR));
                    return true;
                case 6:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (parcel.readInt() != 0) {
                        charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                    }
                    e(charSequence);
                    return true;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (parcel.readInt() != 0) {
                        bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    M(bundle2);
                    return true;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (parcel.readInt() != 0) {
                        parcelableVolumeInfo = (ParcelableVolumeInfo) ParcelableVolumeInfo.CREATOR.createFromParcel(parcel);
                    }
                    w0(parcelableVolumeInfo);
                    return true;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    int readInt = parcel.readInt();
                    AbstractC0559Jd0 jd02 = (AbstractC0559Jd0) this.b.get();
                    if (jd02 == null) {
                        return true;
                    }
                    jd02.d(9, Integer.valueOf(readInt), null);
                    return true;
                case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    parcel.readInt();
                    return true;
                case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    boolean z = parcel.readInt() != 0;
                    AbstractC0559Jd0 jd03 = (AbstractC0559Jd0) this.b.get();
                    if (jd03 == null) {
                        return true;
                    }
                    jd03.d(11, Boolean.valueOf(z), null);
                    return true;
                case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    int readInt2 = parcel.readInt();
                    AbstractC0559Jd0 jd04 = (AbstractC0559Jd0) this.b.get();
                    if (jd04 == null) {
                        return true;
                    }
                    jd04.d(12, Integer.valueOf(readInt2), null);
                    return true;
                case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    AbstractC0559Jd0 jd05 = (AbstractC0559Jd0) this.b.get();
                    if (jd05 == null) {
                        return true;
                    }
                    jd05.d(13, null, null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        } else {
            parcel2.writeString("android.support.v4.media.session.IMediaControllerCallback");
            return true;
        }
    }

    @Override // defpackage.QY
    public void u0(PlaybackStateCompat playbackStateCompat) {
        AbstractC0559Jd0 jd0 = (AbstractC0559Jd0) this.b.get();
        if (jd0 != null) {
            jd0.d(2, playbackStateCompat, null);
        }
    }
}
