package X;

import android.text.TextUtils;
import com.facebook.auth.viewercontext.ViewerContext;
import com.oculus.assistant.entry.AssistantEntryManager;

public final class w1 {
    public final /* synthetic */ AssistantEntryManager A00;

    public w1(AssistantEntryManager assistantEntryManager) {
        this.A00 = assistantEntryManager;
    }

    public final ViewerContext A00() {
        ZC A002 = this.A00.A03.A00();
        if (A002 == null) {
            return null;
        }
        String str = A002.A01;
        if (TextUtils.isEmpty(str) || str.equals("0")) {
            return null;
        }
        BV bv = new BV();
        bv.A00 = A002.A00;
        bv.A01 = str;
        return new ViewerContext(bv);
    }
}
