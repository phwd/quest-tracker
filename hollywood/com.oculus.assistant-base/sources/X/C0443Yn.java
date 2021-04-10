package X;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.util.SparseArray;

/* renamed from: X.Yn  reason: case insensitive filesystem */
public final class C0443Yn {
    public static final SparseArray A00 = new SparseArray();

    public static void A00(int i, MediaPlayer.OnCompletionListener onCompletionListener) {
        SparseArray sparseArray = A00;
        MediaPlayer mediaPlayer = (MediaPlayer) sparseArray.get(i);
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(BX.A00(), i);
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(1).setContentType(4).setUsage(16).build());
            sparseArray.put(i, mediaPlayer);
        }
        C0441Yl.A00().A02();
        mediaPlayer.setOnCompletionListener(new C0442Ym(onCompletionListener));
        mediaPlayer.start();
    }
}
