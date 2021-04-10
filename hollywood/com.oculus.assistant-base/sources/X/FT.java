package X;

import android.media.AudioAttributes;
import android.os.Handler;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;

public final class FT {
    public static final AudioAttributes A06 = new AudioAttributes.Builder().setContentType(2).setUsage(1).build();
    public final AudioAttributes A00;
    public final Handler A01;
    public final ImmutableMap A02 = RegularImmutableMap.A03;
    public final int A03;
    public final Integer A04;
    public final String A05;

    public FT(Handler handler, String str, Integer num, int i, AudioAttributes audioAttributes) {
        this.A01 = handler;
        this.A05 = str;
        this.A04 = num;
        this.A03 = i;
        this.A00 = audioAttributes == null ? A06 : audioAttributes;
    }
}
