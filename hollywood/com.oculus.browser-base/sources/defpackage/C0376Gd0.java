package defpackage;

import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat$QueueItem;
import android.support.v4.media.session.PlaybackStateCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* renamed from: Gd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0376Gd0 extends MediaController.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f8097a;

    public C0376Gd0(AbstractC0559Jd0 jd0) {
        this.f8097a = new WeakReference(jd0);
    }

    public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
        if (((AbstractC0559Jd0) this.f8097a.get()) != null) {
            playbackInfo.getPlaybackType();
            playbackInfo.getAudioAttributes();
            if (Build.VERSION.SDK_INT >= 26) {
            }
            playbackInfo.getVolumeControl();
            playbackInfo.getMaxVolume();
            playbackInfo.getCurrentVolume();
        }
    }

    public void onExtrasChanged(Bundle bundle) {
        C0571Jh0.a(bundle);
        AbstractC0559Jd0 jd0 = (AbstractC0559Jd0) this.f8097a.get();
    }

    public void onMetadataChanged(MediaMetadata mediaMetadata) {
        AbstractC0559Jd0 jd0 = (AbstractC0559Jd0) this.f8097a.get();
        if (jd0 != null) {
            jd0.a(MediaMetadataCompat.b(mediaMetadata));
        }
    }

    public void onPlaybackStateChanged(PlaybackState playbackState) {
        AbstractC0559Jd0 jd0 = (AbstractC0559Jd0) this.f8097a.get();
        if (jd0 != null && jd0.c == null) {
            jd0.b(PlaybackStateCompat.b(playbackState));
        }
    }

    @Override // android.media.session.MediaController.Callback
    public void onQueueChanged(List list) {
        MediaSessionCompat$QueueItem mediaSessionCompat$QueueItem;
        if (!(((AbstractC0559Jd0) this.f8097a.get()) == null || list == null)) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (obj != null) {
                    MediaSession.QueueItem queueItem = (MediaSession.QueueItem) obj;
                    mediaSessionCompat$QueueItem = new MediaSessionCompat$QueueItem(queueItem, MediaDescriptionCompat.b(queueItem.getDescription()), queueItem.getQueueId());
                } else {
                    mediaSessionCompat$QueueItem = null;
                }
                arrayList.add(mediaSessionCompat$QueueItem);
            }
        }
    }

    public void onQueueTitleChanged(CharSequence charSequence) {
        AbstractC0559Jd0 jd0 = (AbstractC0559Jd0) this.f8097a.get();
    }

    public void onSessionDestroyed() {
        AbstractC0559Jd0 jd0 = (AbstractC0559Jd0) this.f8097a.get();
        if (jd0 != null) {
            jd0.c();
        }
    }

    public void onSessionEvent(String str, Bundle bundle) {
        C0571Jh0.a(bundle);
        AbstractC0559Jd0 jd0 = (AbstractC0559Jd0) this.f8097a.get();
    }
}
