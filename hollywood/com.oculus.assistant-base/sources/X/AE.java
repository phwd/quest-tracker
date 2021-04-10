package X;

import android.net.Uri;
import com.facebook.acra.AppComponentStats;

public final class AE {
    public static final Uri A00 = A00().buildUpon().appendPath(Integer.toString(1)).appendPath("tts_personas").appendPath("selection").build();
    public static final String[] A01 = {"persona_id", "locale", AppComponentStats.ATTRIBUTE_NAME, "desc", "sample_url", "selected"};

    public static final Uri A00() {
        return new Uri.Builder().scheme("content").authority("com.facebook.assistant.common.config.tts").build();
    }
}
