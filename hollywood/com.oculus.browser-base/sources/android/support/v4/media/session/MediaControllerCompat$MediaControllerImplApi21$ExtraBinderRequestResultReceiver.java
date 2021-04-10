package android.support.v4.media.session;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import androidx.versionedparcelable.C1728ParcelImpl;
import java.lang.ref.WeakReference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver extends ResultReceiver {
    public WeakReference F;

    public MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver(C0741Md0 md0) {
        super(null);
        this.F = new WeakReference(md0);
    }

    public void onReceiveResult(int i, Bundle bundle) {
        C0741Md0 md0 = (C0741Md0) this.F.get();
        if (md0 != null && bundle != null) {
            synchronized (md0.b) {
                MediaSessionCompat$Token mediaSessionCompat$Token = md0.e;
                TY c = SY.c(bundle.getBinder("android.support.v4.media.session.EXTRA_BINDER"));
                synchronized (mediaSessionCompat$Token.F) {
                    mediaSessionCompat$Token.H = c;
                }
                MediaSessionCompat$Token mediaSessionCompat$Token2 = md0.e;
                Ns1 ns1 = null;
                try {
                    Bundle bundle2 = (Bundle) bundle.getParcelable("android.support.v4.media.session.SESSION_TOKEN2");
                    if (bundle2 != null) {
                        bundle2.setClassLoader(AbstractC0176Cw0.class.getClassLoader());
                        Parcelable parcelable = bundle2.getParcelable(AbstractC1585a.f9392a);
                        if (parcelable instanceof C1728ParcelImpl) {
                            ns1 = ((C1728ParcelImpl) parcelable).F;
                        } else {
                            throw new IllegalArgumentException("Invalid parcel");
                        }
                    }
                } catch (RuntimeException unused) {
                }
                synchronized (mediaSessionCompat$Token2.F) {
                    mediaSessionCompat$Token2.I = ns1;
                }
                md0.b();
            }
        }
    }
}
