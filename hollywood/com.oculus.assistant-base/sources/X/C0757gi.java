package X;

import android.content.Context;
import android.preference.PreferenceManager;
import com.facebook.assistant.clientplatform.keyboard.learning.LearningListener;

/* renamed from: X.gi  reason: case insensitive filesystem */
public final class C0757gi implements AbstractC0383Uv {
    public final /* synthetic */ LearningListener A00;

    public C0757gi(LearningListener learningListener) {
        this.A00 = learningListener;
    }

    @Override // X.AbstractC0383Uv
    public final void A49(Throwable th) {
        C0139Dd.A0S("com.facebook.assistant.clientplatform.keyboard.learning.LearningListener", th, "Papaya scheduling job failed");
    }

    @Override // X.AbstractC0383Uv
    public final void A4N(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            C0139Dd.A09("com.facebook.assistant.clientplatform.keyboard.learning.LearningListener", "Scheduling job success");
            Context context = this.A00.A01;
            if (PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getLong("assistant_keyboard_fl_papaya_last_submit", -1) == -1) {
                C00638p.A01(context, "assistant_keyboard_fl_papaya_last_submit", System.currentTimeMillis());
                return;
            }
            return;
        }
        C0139Dd.A0A("com.facebook.assistant.clientplatform.keyboard.learning.LearningListener", "Failed to submit Papaya executor");
    }
}
