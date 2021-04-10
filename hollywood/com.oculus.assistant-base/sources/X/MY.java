package X;

import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Process;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public final class MY implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.voiceplatform.tts.AudioTrackPlayer$AudioWorker";
    public int A00;
    public int A01;
    public AudioTrack A02;
    public boolean A03;
    public byte[] A04;
    public final /* synthetic */ C0239Mb A05;

    public MY(C0239Mb mb) {
        this.A05 = mb;
    }

    private void A00() {
        AudioTrack audioTrack = this.A02;
        if (audioTrack != null) {
            audioTrack.release();
            this.A02 = null;
        }
        while (true) {
            C0239Mb mb = this.A05;
            MX mx = (MX) mb.A06.poll();
            if (mx == null) {
                C0139Dd.A09("AudioTrackPlayer", "Sending done");
                mb.A03.post(new MW(mb));
                return;
            } else if (mx.A01) {
                MR.A00(mx.A02);
            }
        }
    }

    public final void run() {
        C0139Dd.A09("AudioTrackPlayer", "Thread start");
        Process.setThreadPriority(-16);
        try {
            C0239Mb mb = this.A05;
            AudioFormat audioFormat = mb.A02;
            int minBufferSize = AudioTrack.getMinBufferSize(audioFormat.getSampleRate(), audioFormat.getChannelMask(), audioFormat.getEncoding());
            this.A00 = minBufferSize;
            AudioTrack audioTrack = new AudioTrack(mb.A01, audioFormat, minBufferSize, 1, 0);
            this.A02 = audioTrack;
            if (audioTrack.getState() == 1) {
                this.A02.play();
                this.A01 = 0;
                int i = this.A00;
                this.A04 = new byte[i];
                C0139Dd.A0F("AudioTrackPlayer", "Listening for audio... %d", Integer.valueOf(i));
                while (true) {
                    if (mb.A05.isInterrupted()) {
                        break;
                    }
                    MX mx = (MX) mb.A06.poll(30, TimeUnit.SECONDS);
                    if (mx == null) {
                        throw new IllegalStateException("No new audio data in awhile, timing out");
                    } else if (mx.A01) {
                        byte[] bArr = mx.A02;
                        int i2 = mx.A00;
                        int i3 = 0;
                        if (this.A02 != null) {
                            int i4 = i2 + 0;
                            while (i3 < i4) {
                                int i5 = this.A00;
                                int i6 = this.A01;
                                int min = Math.min(i4 - i3, i5 - i6);
                                System.arraycopy(bArr, i3, this.A04, i6, min);
                                int i7 = this.A01 + min;
                                this.A01 = i7;
                                i3 += min;
                                if (i7 == this.A00) {
                                    if (!this.A03) {
                                        this.A03 = true;
                                        C0139Dd.A09("AudioTrackPlayer", "Sending start");
                                        mb.A03.post(new MV(mb));
                                    }
                                    int write = this.A02.write(ByteBuffer.wrap(this.A04, 0, this.A01), this.A01, 0);
                                    if (write >= 0) {
                                        this.A01 = 0;
                                    } else {
                                        throw new IllegalStateException(AnonymousClass08.A00("Bad write result - ", write));
                                    }
                                } else if (i3 < i4) {
                                    throw new IllegalArgumentException("This should never happen");
                                }
                            }
                            mb.A07.addAndGet(-mx.A00);
                            MR.A00(mx.A02);
                        } else {
                            throw new IllegalStateException("Attempted write to audio track after cleanup");
                        }
                    } else if (mx.A03) {
                        if (this.A02 != null) {
                            int i8 = this.A01;
                            if (i8 > 0) {
                                Arrays.fill(this.A04, i8, this.A00, (byte) 0);
                                int write2 = this.A02.write(ByteBuffer.wrap(this.A04, 0, this.A00), this.A00, 0);
                                if (write2 < 0) {
                                    throw new IllegalStateException(AnonymousClass08.A00("Bad write result - ", write2));
                                }
                            }
                            this.A02.stop();
                            C0139Dd.A09("AudioTrackPlayer", "Done playing audio");
                        } else {
                            throw new IllegalStateException("Attempted flush to audio track after cleanup");
                        }
                    }
                }
                A00();
                C0139Dd.A09("AudioTrackPlayer", "Thread done");
                return;
            }
            throw new IllegalStateException("AudioTrack in a bad state");
        } catch (IllegalStateException e) {
            C0139Dd.A0S("AudioTrackPlayer", e, "Error playing audio");
            C0239Mb mb2 = this.A05;
            mb2.A08 = true;
            mb2.A05.interrupt();
            C0139Dd.A0S("AudioTrackPlayer", e, "Error while playing TTS");
            mb2.A03.post(new MU(mb2, e));
        } catch (InterruptedException unused) {
        } catch (Throwable th) {
            A00();
            throw th;
        }
    }
}
