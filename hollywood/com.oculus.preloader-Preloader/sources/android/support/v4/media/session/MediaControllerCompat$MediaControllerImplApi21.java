package android.support.v4.media.session;

import android.os.Bundle;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.SessionToken2;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaControllerCompat$Callback;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

class MediaControllerCompat$MediaControllerImplApi21 implements MediaControllerCompat$MediaControllerImpl {
    private HashMap<MediaControllerCompat$Callback, ExtraCallback> mCallbackMap;
    private final List<MediaControllerCompat$Callback> mPendingCallbacks;
    private final MediaSessionCompat$Token mSessionToken;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void processPendingCallbacks() {
        if (this.mSessionToken.getExtraBinder() != null) {
            synchronized (this.mPendingCallbacks) {
                for (MediaControllerCompat$Callback mediaControllerCompat$Callback : this.mPendingCallbacks) {
                    ExtraCallback extraCallback = new ExtraCallback(mediaControllerCompat$Callback);
                    this.mCallbackMap.put(mediaControllerCompat$Callback, extraCallback);
                    mediaControllerCompat$Callback.mIControllerCallback = extraCallback;
                    try {
                        this.mSessionToken.getExtraBinder().registerCallbackListener(extraCallback);
                        mediaControllerCompat$Callback.onSessionReady();
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                    }
                }
                this.mPendingCallbacks.clear();
            }
        }
    }

    private static class ExtraBinderRequestResultReceiver extends ResultReceiver {
        private WeakReference<MediaControllerCompat$MediaControllerImplApi21> mMediaControllerImpl;

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            MediaControllerCompat$MediaControllerImplApi21 mediaControllerCompat$MediaControllerImplApi21 = this.mMediaControllerImpl.get();
            if (mediaControllerCompat$MediaControllerImplApi21 != null && bundle != null) {
                mediaControllerCompat$MediaControllerImplApi21.mSessionToken.setExtraBinder(IMediaSession.Stub.asInterface(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                mediaControllerCompat$MediaControllerImplApi21.mSessionToken.setSessionToken2(SessionToken2.fromBundle(bundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2")));
                mediaControllerCompat$MediaControllerImplApi21.processPendingCallbacks();
            }
        }
    }

    /* access modifiers changed from: private */
    public static class ExtraCallback extends MediaControllerCompat$Callback.StubCompat {
        ExtraCallback(MediaControllerCompat$Callback mediaControllerCompat$Callback) {
            super(mediaControllerCompat$Callback);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat$Callback.StubCompat, android.support.v4.media.session.IMediaControllerCallback
        public void onSessionDestroyed() throws RemoteException {
            throw new AssertionError();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat$Callback.StubCompat, android.support.v4.media.session.IMediaControllerCallback
        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
            throw new AssertionError();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat$Callback.StubCompat, android.support.v4.media.session.IMediaControllerCallback
        public void onQueueChanged(List<MediaSessionCompat$QueueItem> list) throws RemoteException {
            throw new AssertionError();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat$Callback.StubCompat, android.support.v4.media.session.IMediaControllerCallback
        public void onQueueTitleChanged(CharSequence charSequence) throws RemoteException {
            throw new AssertionError();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat$Callback.StubCompat, android.support.v4.media.session.IMediaControllerCallback
        public void onExtrasChanged(Bundle bundle) throws RemoteException {
            throw new AssertionError();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat$Callback.StubCompat, android.support.v4.media.session.IMediaControllerCallback
        public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
            throw new AssertionError();
        }
    }
}
