package android.support.v4.media;

import android.media.VolumeProvider;

/* access modifiers changed from: package-private */
public class VolumeProviderCompatApi21 {

    public interface Delegate {
        void onAdjustVolume(int i);

        void onSetVolumeTo(int i);
    }

    VolumeProviderCompatApi21() {
    }

    public static Object createVolumeProvider(int i, int i2, int i3, final Delegate delegate) {
        return new VolumeProvider(i, i2, i3) {
            /* class android.support.v4.media.VolumeProviderCompatApi21.AnonymousClass1 */

            public void onSetVolumeTo(int i) {
                delegate.onSetVolumeTo(i);
            }

            public void onAdjustVolume(int i) {
                delegate.onAdjustVolume(i);
            }
        };
    }

    public static void setCurrentVolume(Object obj, int i) {
        ((VolumeProvider) obj).setCurrentVolume(i);
    }
}
