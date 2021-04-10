package X;

import android.os.Build;
import com.facebook.assistant.clientplatform.clientstate.AssistantErrorType;
import com.facebook.quicklog.QuickPerformanceLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class hG extends AbstractC00889s {
    public static final List A00 = new ArrayList();

    public final void A06() {
        long currentMonotonicTimestamp = this.A00.currentMonotonicTimestamp();
        QuickPerformanceLogger quickPerformanceLogger = this.A00;
        int i = this.A02;
        if (quickPerformanceLogger.isMarkerOn(i)) {
            A08(AnonymousClass09.A0C);
        }
        this.A00.markerStart(i, 0, currentMonotonicTimestamp, TimeUnit.MILLISECONDS);
        C1396yX yXVar = AbstractC00889s.A03;
        if (yXVar != null) {
            A03(EnumC00899t.ASSISTANT_APP_VERSION, yXVar.A01());
        }
        String str = Build.SERIAL;
        if (str != null) {
            A03(EnumC00899t.BUILD_SERIAL, str);
        }
        A02();
        for (AbstractC00869q r0 : A00) {
            r0.A4F();
        }
    }

    public final void A07(AssistantErrorType assistantErrorType) {
        this.A00.markerAnnotate(this.A02, EnumC00899t.ERROR_TYPE.getExtraName(), assistantErrorType.name());
        A09(87, this.A00.currentMonotonicTimestamp());
    }

    public final void A08(Integer num) {
        String str;
        QuickPerformanceLogger quickPerformanceLogger = this.A00;
        int i = this.A02;
        String extraName = EnumC00899t.CANCEL_REASON.getExtraName();
        switch (num.intValue()) {
            case 1:
                str = "ASSISTANT_INTERRUPTED";
                break;
            case 2:
                str = "PREVIOUS_INTERACTION_NOT_CLOSED";
                break;
            default:
                str = "KEYWORD_REJECT";
                break;
        }
        quickPerformanceLogger.markerAnnotate(i, extraName, str);
        A09(4, this.A00.currentMonotonicTimestamp());
    }

    public final void A09(short s, long j) {
        for (AbstractC00869q r0 : A00) {
            r0.A3u();
        }
        this.A00.markerEnd(this.A02, s, j, TimeUnit.MILLISECONDS);
    }

    public hG() {
        super(50790401);
    }
}
