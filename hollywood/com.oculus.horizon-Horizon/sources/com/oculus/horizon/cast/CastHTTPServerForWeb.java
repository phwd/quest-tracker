package com.oculus.horizon.cast;

import X.AbstractC06600ny;
import android.content.Context;
import android.os.Handler;
import com.oculus.debug.DebugMode;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nullable;

public class CastHTTPServerForWeb extends CastHTTPServerBase {
    public static final VideoSpec CAST_WWW_DEFAULT_VIDEO_SPEC = new VideoSpec(1024, 1024, 10);
    public static final int OFFER_TIMEOUT = 3;
    public static final String TAG = "CastHTTPServerForWeb";
    public static final int WWW_ANSWER_TIMEOUT = 60;
    public String mAnswerJSONString;
    @Nullable
    public CountDownLatch mInitialOfferFence;
    @Nullable
    public CountDownLatch mOfferAnswerFence;
    public String mOfferJSONString;

    public CastHTTPServerForWeb(Context context, CastAnalytics castAnalytics, DebugMode debugMode, AbstractC06600ny r4, Handler handler) {
        super(context, castAnalytics, debugMode, r4, handler);
    }
}
