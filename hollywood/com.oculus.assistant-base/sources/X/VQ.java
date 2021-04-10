package X;

import android.media.AudioAttributes;
import android.os.Handler;
import java.util.regex.Pattern;

public final class VQ {
    public static final Pattern A07 = Pattern.compile("audio/raw; sampleRate=(\\d+); encoding=INT16; channels=1");
    public C0861ky A00;
    public C0239Mb A01;
    public boolean A02 = false;
    public boolean A03 = false;
    public final AudioAttributes A04;
    public final Handler A05;
    public final AbstractC0238Ma A06 = new C1006qZ(this);

    public VQ(Handler handler, AudioAttributes audioAttributes) {
        this.A05 = handler;
        this.A04 = audioAttributes;
    }
}
