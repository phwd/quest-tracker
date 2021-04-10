package org.chromium.media;

import J.N;
import android.media.AudioTrack;
import java.nio.ByteBuffer;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AudioTrackOutputStream {

    /* renamed from: a  reason: collision with root package name */
    public long f10970a;
    public C4082ob b;
    public AudioTrack c;
    public int d;
    public C4253pb e;
    public int f;
    public long g;
    public long h;
    public ByteBuffer i;
    public ByteBuffer j;
    public int k;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class AudioBufferInfo {

        /* renamed from: a  reason: collision with root package name */
        public final int f10971a;
        public final int b;

        public AudioBufferInfo(int i, int i2) {
            this.f10971a = i;
            this.b = i2;
        }
    }

    public AudioTrackOutputStream(C4082ob obVar) {
        this.b = null;
        this.b = new C4082ob(this);
    }

    public static AudioTrackOutputStream create() {
        return new AudioTrackOutputStream(null);
    }

    public void close() {
        AudioTrack audioTrack = this.c;
        if (audioTrack != null) {
            audioTrack.release();
            this.c = null;
        }
    }

    public AudioBufferInfo createAudioBufferInfo(int i2, int i3) {
        return new AudioBufferInfo(i2, i3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean open(int r11, int r12, int r13) {
        /*
            r10 = this;
            java.lang.String r0 = "AudioTrackOutput"
            r1 = 4
            r2 = 1
            if (r11 == r2) goto L_0x001f
            r3 = 2
            if (r11 == r3) goto L_0x001d
            if (r11 == r1) goto L_0x001a
            r1 = 6
            if (r11 == r1) goto L_0x0017
            r1 = 8
            if (r11 == r1) goto L_0x0014
            r6 = r2
            goto L_0x0020
        L_0x0014:
            r1 = 6396(0x18fc, float:8.963E-42)
            goto L_0x001f
        L_0x0017:
            r1 = 252(0xfc, float:3.53E-43)
            goto L_0x001f
        L_0x001a:
            r1 = 204(0xcc, float:2.86E-43)
            goto L_0x001f
        L_0x001d:
            r1 = 12
        L_0x001f:
            r6 = r1
        L_0x0020:
            ob r11 = r10.b
            java.util.Objects.requireNonNull(r11)
            int r11 = android.media.AudioTrack.getMinBufferSize(r12, r6, r13)
            int r11 = r11 * 3
            r10.d = r11
            r11 = 0
            ob r1 = r10.b     // Catch:{ IllegalArgumentException -> 0x0059 }
            r4 = 3
            int r8 = r10.d     // Catch:{ IllegalArgumentException -> 0x0059 }
            r9 = 1
            java.util.Objects.requireNonNull(r1)     // Catch:{ IllegalArgumentException -> 0x0059 }
            android.media.AudioTrack r1 = new android.media.AudioTrack     // Catch:{ IllegalArgumentException -> 0x0059 }
            r3 = r1
            r5 = r12
            r7 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9)     // Catch:{ IllegalArgumentException -> 0x0059 }
            r10.c = r1     // Catch:{ IllegalArgumentException -> 0x0059 }
            int r12 = r1.getState()
            if (r12 != 0) goto L_0x0052
            java.lang.Object[] r12 = new java.lang.Object[r11]
            java.lang.String r13 = "Cannot create AudioTrack"
            defpackage.AbstractC1220Ua0.a(r0, r13, r12)
            r12 = 0
            r10.c = r12
            return r11
        L_0x0052:
            r10.f = r11
            r11 = 0
            r10.g = r11
            return r2
        L_0x0059:
            r12 = move-exception
            java.lang.Object[] r13 = new java.lang.Object[r2]
            r13[r11] = r12
            java.lang.String r12 = "Exception creating AudioTrack for playback: "
            defpackage.AbstractC1220Ua0.a(r0, r12, r13)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.AudioTrackOutputStream.open(int, int, int):boolean");
    }

    public void setVolume(double d2) {
        float maxVolume = (float) (d2 * ((double) AudioTrack.getMaxVolume()));
        this.c.setStereoVolume(maxVolume, maxVolume);
    }

    public void start(long j2) {
        if (this.e == null) {
            this.f10970a = j2;
            this.h = 0;
            int i2 = this.d;
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i2 + 15);
            AudioTrackOutputStream audioTrackOutputStream = this.b.f10559a;
            int MMQ1O_vA = 15 & (16 - ((int) (N.MMQ1O_vA(audioTrackOutputStream.f10970a, audioTrackOutputStream, allocateDirect) & ((long) 15))));
            allocateDirect.position(MMQ1O_vA);
            allocateDirect.limit(MMQ1O_vA + i2);
            this.i = allocateDirect.slice();
            this.c.play();
            C4253pb pbVar = new C4253pb(this);
            this.e = pbVar;
            pbVar.start();
        }
    }

    public void stop() {
        C4253pb pbVar = this.e;
        if (pbVar != null) {
            pbVar.F = true;
            try {
                this.e.interrupt();
                this.e.join();
            } catch (SecurityException e2) {
                AbstractC1220Ua0.a("AudioTrackOutput", "Exception while waiting for AudioTrack worker thread finished: ", e2);
            } catch (InterruptedException e3) {
                AbstractC1220Ua0.a("AudioTrackOutput", "Exception while waiting for AudioTrack worker thread finished: ", e3);
            }
            this.e = null;
        }
        this.c.pause();
        this.c.flush();
        this.f = 0;
        this.g = 0;
        this.f10970a = 0;
    }
}
