package defpackage;

import J.N;
import java.nio.ByteBuffer;
import org.chromium.media.AudioTrackOutputStream;

/* renamed from: pb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4253pb extends Thread {
    public volatile boolean F;
    public final /* synthetic */ AudioTrackOutputStream G;

    public C4253pb(AudioTrackOutputStream audioTrackOutputStream) {
        this.G = audioTrackOutputStream;
    }

    public void run() {
        while (!this.F) {
            AudioTrackOutputStream audioTrackOutputStream = this.G;
            int i = audioTrackOutputStream.k;
            int i2 = 0;
            if (i != 0) {
                int write = audioTrackOutputStream.c.write(audioTrackOutputStream.j, i, 0);
                if (write < 0) {
                    AbstractC1220Ua0.a("AudioTrackOutput", AbstractC2531fV.w("AudioTrack.write() failed. Error:", write), new Object[0]);
                    AudioTrackOutputStream audioTrackOutputStream2 = audioTrackOutputStream.b.f10559a;
                    N.Mr6$Ko2f(audioTrackOutputStream2.f10970a, audioTrackOutputStream2);
                    i2 = write;
                } else {
                    i2 = audioTrackOutputStream.k - write;
                    audioTrackOutputStream.k = i2;
                }
            }
            if (i2 >= 0) {
                if (i2 <= 0) {
                    AudioTrackOutputStream audioTrackOutputStream3 = this.G;
                    int playbackHeadPosition = audioTrackOutputStream3.c.getPlaybackHeadPosition();
                    long j = audioTrackOutputStream3.g + ((long) (playbackHeadPosition - audioTrackOutputStream3.f));
                    audioTrackOutputStream3.g = j;
                    audioTrackOutputStream3.f = playbackHeadPosition;
                    long j2 = audioTrackOutputStream3.h - j;
                    long j3 = j2 < 0 ? 0 : j2;
                    C4082ob obVar = audioTrackOutputStream3.b;
                    ByteBuffer duplicate = audioTrackOutputStream3.i.duplicate();
                    AudioTrackOutputStream audioTrackOutputStream4 = obVar.f10559a;
                    AudioTrackOutputStream.AudioBufferInfo audioBufferInfo = (AudioTrackOutputStream.AudioBufferInfo) N.MEPH2V3G(audioTrackOutputStream4.f10970a, audioTrackOutputStream4, duplicate, j3);
                    if (audioBufferInfo != null && audioBufferInfo.b > 0) {
                        audioTrackOutputStream3.h += (long) audioBufferInfo.f10971a;
                        audioTrackOutputStream3.j = audioTrackOutputStream3.i.asReadOnlyBuffer();
                        audioTrackOutputStream3.k = audioBufferInfo.b;
                    }
                }
            } else {
                return;
            }
        }
    }
}
