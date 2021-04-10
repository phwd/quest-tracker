package X;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.oculus.assistant.R;
import com.oculus.os.SettingsManager;

public final class Y7 {
    public final AnonymousClass7Z A00 = new NO(this);
    public final AnonymousClass7Z A01 = new QO(this);
    public final AnonymousClass7Z A02 = new MS(this);
    public final HandlerC0422Wz A03;

    public Y7(HandlerC0422Wz wz) {
        SharedPreferences sharedPreferences = BX.A00().getSharedPreferences("AssistantServiceConfig", 0);
        if (!sharedPreferences.getBoolean("initialized_storage_setting", false)) {
            new SettingsManager(BX.A00()).setBoolean("voice_interaction_storage_enabled", true);
            sharedPreferences.edit().putBoolean("initialized_storage_setting", true).apply();
        }
        AnonymousClass7Z r1 = this.A00;
        r1.A0G("nux-intro");
        r1.A0C(R.string.nux_voice_commands_intro_title);
        r1.A09(R.string.nux_voice_commands_intro_description);
        r1.A0A(R.string.dialog_start_setup);
        r1.A0B(R.string.dialog_set_up_later);
        r1.A01.putBoolean("auto-close-primary", false);
        r1.A0F("nux_intro_hero.jpg");
        AnonymousClass7Z r12 = this.A02;
        r12.A0G("nux-setup_complete");
        r12.A09(R.string.nux_setup_complete_description);
        r12.A0F("nux_setup_complete.jpg");
        AnonymousClass7Z r13 = this.A01;
        r13.A0G("nux-privacy");
        r13.A0C(R.string.nux_voice_commands_privacy_title);
        r13.A09(R.string.nux_voice_commands_privacy_description);
        Bundle bundle = r13.A01;
        bundle.putBoolean("auto-close-primary", false);
        r13.A0A(R.string.dialog_continue_setup);
        r13.A0B(R.string.dialog_set_up_later);
        bundle.putString("tertiary", C1300wu.A02(R.string.nux_interaction_storage));
        this.A03 = wz;
        HandlerC0422Wz.A03(this.A00);
        HandlerC0422Wz.A03(this.A01);
    }
}
