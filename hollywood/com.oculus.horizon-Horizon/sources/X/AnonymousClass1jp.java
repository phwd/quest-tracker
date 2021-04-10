package X;

import android.media.AudioRecord;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.List;
import org.webrtc.voiceengine.WebRtcAudioUtils;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1jp  reason: invalid class name */
public final class AnonymousClass1jp {
    public static String A00;
    public static List<int[]> A01;
    public static final int[] A02 = {8000, 11025, WebRtcAudioUtils.DEFAULT_SAMPLE_RATE_HZ, 22050, 32000, 37800, 44056, 44100, 47250, 48000, 50000, 50400, 88200, 96000, 176400, 192000, 352800, 2822400, 5644800};
    public static final short[] A03 = {16, 12};
    public static final short[] A04 = {3, 2, 4};

    public static String A00(List<int[]> list) {
        String str = A00;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int[] iArr : list) {
            sb.append("(");
            sb.append(iArr[0]);
            sb.append(",");
            sb.append(iArr[1]);
            sb.append(",");
            sb.append(iArr[2]);
            sb.append(");");
        }
        String obj = sb.toString();
        A00 = obj;
        return obj;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r13v0, resolved type: short[] */
    /* JADX DEBUG: Multi-variable search result rejected for r10v0, resolved type: short */
    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: short[] */
    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: short */
    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: int[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static List<int[]> A01() {
        if (A01 == null) {
            A01 = new ArrayList();
            short[] sArr = A03;
            for (short s : sArr) {
                short[] sArr2 = A04;
                for (short s2 : sArr2) {
                    int[] iArr = A02;
                    for (int i : iArr) {
                        if (AudioRecord.getMinBufferSize(i, s, s2) >= 0) {
                            A01.add(new int[]{i, s, s2});
                        }
                    }
                }
            }
        }
        return A01;
    }
}
