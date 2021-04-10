package defpackage;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper;
import android.support.v4.media.session.ParcelableVolumeInfo;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.oculus.os.Version;
import java.util.List;

/* renamed from: SY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class SY extends Binder implements TY {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f8899a = 0;

    public SY() {
        attachInterface(this, "android.support.v4.media.session.IMediaSession");
    }

    public static TY c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
        if (queryLocalInterface == null || !(queryLocalInterface instanceof TY)) {
            return new RY(iBinder);
        }
        return (TY) queryLocalInterface;
    }

    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1598968902) {
            boolean z = false;
            MediaSessionCompat$ResultReceiverWrapper mediaSessionCompat$ResultReceiverWrapper = null;
            Bundle bundle = null;
            MediaDescriptionCompat mediaDescriptionCompat = null;
            MediaDescriptionCompat mediaDescriptionCompat2 = null;
            MediaDescriptionCompat mediaDescriptionCompat3 = null;
            Bundle bundle2 = null;
            Bundle bundle3 = null;
            Bundle bundle4 = null;
            Bundle bundle5 = null;
            RatingCompat ratingCompat = null;
            Bundle bundle6 = null;
            Bundle bundle7 = null;
            Bundle bundle8 = null;
            KeyEvent keyEvent = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String readString = parcel.readString();
                    Bundle bundle9 = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
                    if (parcel.readInt() != 0) {
                        mediaSessionCompat$ResultReceiverWrapper = (MediaSessionCompat$ResultReceiverWrapper) MediaSessionCompat$ResultReceiverWrapper.CREATOR.createFromParcel(parcel);
                    }
                    Q(readString, bundle9, mediaSessionCompat$ResultReceiverWrapper);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (parcel.readInt() != 0) {
                        keyEvent = (KeyEvent) KeyEvent.CREATOR.createFromParcel(parcel);
                    }
                    boolean x0 = x0(keyEvent);
                    parcel2.writeNoException();
                    parcel2.writeInt(x0 ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    W(AbstractBinderC0498Id0.c(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    I(AbstractBinderC0498Id0.c(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean z2 = z();
                    parcel2.writeNoException();
                    parcel2.writeInt(z2 ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String s0 = s0();
                    parcel2.writeNoException();
                    parcel2.writeString(s0);
                    return true;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String o = o();
                    parcel2.writeNoException();
                    parcel2.writeString(o);
                    return true;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    PendingIntent B = B();
                    parcel2.writeNoException();
                    if (B != null) {
                        parcel2.writeInt(1);
                        B.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    long d0 = d0();
                    parcel2.writeNoException();
                    parcel2.writeLong(d0);
                    return true;
                case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    ParcelableVolumeInfo k0 = k0();
                    parcel2.writeNoException();
                    if (k0 != null) {
                        parcel2.writeInt(1);
                        k0.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    i(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    t(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    O();
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String readString2 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle8 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    c0(readString2, bundle8);
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String readString3 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle7 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    i0(readString3, bundle7);
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Uri uri = parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null;
                    if (parcel.readInt() != 0) {
                        bundle6 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    m0(uri, bundle6);
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_17 /*{ENCODED_INT: 17}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    g0(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_18 /*{ENCODED_INT: 18}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Y();
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_19 /*{ENCODED_INT: 19}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    stop();
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_20 /*{ENCODED_INT: 20}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    next();
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_21 /*{ENCODED_INT: 21}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    previous();
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_22 /*{ENCODED_INT: 22}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    l0();
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_23 /*{ENCODED_INT: 23}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    V();
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_24 /*{ENCODED_INT: 24}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    n0(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_25 /*{ENCODED_INT: 25}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (parcel.readInt() != 0) {
                        ratingCompat = (RatingCompat) RatingCompat.CREATOR.createFromParcel(parcel);
                    }
                    s(ratingCompat);
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_26 /*{ENCODED_INT: 26}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String readString4 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle5 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    h(readString4, bundle5);
                    parcel2.writeNoException();
                    return true;
                case Version.VERSION_27 /*{ENCODED_INT: 27}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    MediaMetadataCompat Z = Z();
                    parcel2.writeNoException();
                    if (Z != null) {
                        parcel2.writeInt(1);
                        parcel2.writeBundle(Z.f9451J);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case Version.VERSION_28 /*{ENCODED_INT: 28}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    PlaybackStateCompat b = b();
                    parcel2.writeNoException();
                    if (b != null) {
                        parcel2.writeInt(1);
                        b.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case Version.VERSION_29 /*{ENCODED_INT: 29}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    List R = R();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(R);
                    return true;
                case Version.VERSION_30 /*{ENCODED_INT: 30}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    CharSequence X = X();
                    parcel2.writeNoException();
                    if (X != null) {
                        parcel2.writeInt(1);
                        TextUtils.writeToParcel(X, parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case Version.VERSION_31 /*{ENCODED_INT: 31}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Bundle extras = getExtras();
                    parcel2.writeNoException();
                    if (extras != null) {
                        parcel2.writeInt(1);
                        extras.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case Version.VERSION_32 /*{ENCODED_INT: 32}*/:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int E = E();
                    parcel2.writeNoException();
                    parcel2.writeInt(E);
                    return true;
                case 33:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    K();
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String readString5 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle4 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    a0(readString5, bundle4);
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String readString6 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle3 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    F(readString6, bundle3);
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Uri uri2 = parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null;
                    if (parcel.readInt() != 0) {
                        bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    u(uri2, bundle2);
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int f0 = f0();
                    parcel2.writeNoException();
                    parcel2.writeInt(f0);
                    return true;
                case 38:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean q = q();
                    parcel2.writeNoException();
                    parcel2.writeInt(q ? 1 : 0);
                    return true;
                case 39:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    U(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    h0(z);
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (parcel.readInt() != 0) {
                        mediaDescriptionCompat3 = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
                    }
                    A(mediaDescriptionCompat3);
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (parcel.readInt() != 0) {
                        mediaDescriptionCompat2 = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
                    }
                    m(mediaDescriptionCompat2, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (parcel.readInt() != 0) {
                        mediaDescriptionCompat = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
                    }
                    x(mediaDescriptionCompat);
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    D(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean G = G();
                    parcel2.writeNoException();
                    parcel2.writeInt(G ? 1 : 0);
                    return true;
                case 46:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    r(z);
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int C = C();
                    parcel2.writeNoException();
                    parcel2.writeInt(C);
                    return true;
                case 48:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    o0(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    v0(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Bundle b0 = b0();
                    parcel2.writeNoException();
                    if (b0 != null) {
                        parcel2.writeInt(1);
                        b0.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 51:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    RatingCompat ratingCompat2 = parcel.readInt() != 0 ? (RatingCompat) RatingCompat.CREATOR.createFromParcel(parcel) : null;
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    k(ratingCompat2, bundle);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        } else {
            parcel2.writeString("android.support.v4.media.session.IMediaSession");
            return true;
        }
    }
}
