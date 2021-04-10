package X;

import android.app.Application;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.os.Bundle;
import com.oculus.assistant.R;
import com.oculus.assistant.assistantutils.SystemUXUtil;

/* renamed from: X.Wb  reason: case insensitive filesystem */
public abstract class AbstractC0400Wb {
    public static final WZ A02 = new WZ();
    public AssetFileDescriptor A00;
    public Integer A01 = AnonymousClass09.A01;

    public void A05() {
    }

    public void A06() {
    }

    public final C00859p A01() {
        String str;
        if (this instanceof C00959z) {
            return null;
        }
        if (this instanceof C1282wc) {
            C0333Rn rn = new C0333Rn();
            rn.A0G("NUX_WELCOME_STEP_DIALOG");
            C1308x2 x2Var = new C1308x2(R.string.assistant_nux_welcome_att_sys_description);
            ((XA) x2Var).A00 = 40;
            x2Var.A01 = null;
            x2Var.A00 = 19;
            ((XA) x2Var).A01 = 19;
            Integer num = AnonymousClass09.A01;
            C0514bB.A02(num, "style");
            ((C00859p) rn).A00 = num;
            rn.A0D(x2Var);
            return rn;
        } else if (this instanceof C1281wb) {
            return null;
        } else {
            if (this instanceof AbstractC1279wZ) {
                AbstractC1279wZ wZVar = (AbstractC1279wZ) this;
                C1304wy wyVar = new C1304wy(wZVar.A01.getString(R.string.assistant_nux_skip_button));
                if (!(wZVar instanceof A1)) {
                    str = "NUX_TAKE_A_PHOTO_STEP_DIALOG";
                } else {
                    str = "NUX_WHAT_TIME_IS_IT_STEP_DIALOG";
                }
                wyVar.A03 = AnonymousClass08.A04("VOICE_COMMAND_SKIP-", str);
                ((XA) wyVar).A01 = 16;
                wyVar.A00 = "bordered";
                ((XA) wyVar).A00 = Integer.valueOf(X9.A00(R.dimen.assistant_nux_skip_button_height));
                return AbstractC1279wZ.A00(wZVar, wyVar);
            } else if ((this instanceof C1268wO) || (this instanceof C1265wL) || !(this instanceof C1264wK)) {
                return null;
            } else {
                C1306x0 x0Var = new C1306x0();
                x0Var.A02 = -1;
                C1308x2 x2Var2 = new C1308x2(R.string.assistant_nux_double_press_feedback_att_sys_description);
                ((XA) x2Var2).A00 = -1;
                x2Var2.A01 = null;
                x2Var2.A00 = 19;
                ((XA) x2Var2).A01 = 3;
                C1304wy wyVar2 = new C1304wy(((C1264wK) this).A00.getString(R.string.assistant_nux_skip_button));
                wyVar2.A03 = "DOUBLE_PRESS_FEEDBACK_SKIP";
                ((XA) wyVar2).A01 = 16;
                wyVar2.A00 = "bordered";
                ((XA) wyVar2).A00 = Integer.valueOf(X9.A00(R.dimen.assistant_nux_skip_button_height));
                x0Var.A02(x2Var2);
                x0Var.A00.add(new XC(x0Var, wyVar2));
                AnonymousClass1m r2 = new AnonymousClass1m();
                r2.A0G("NUX_DOUBLE_PRESS_FEEDBACK_STEP_DIALOG");
                Integer num2 = AnonymousClass09.A01;
                C0514bB.A02(num2, "style");
                ((C00859p) r2).A00 = num2;
                r2.A0D(x0Var);
                return r2;
            }
        }
    }

    public final C1300wu A02() {
        String str;
        if (this instanceof C00959z) {
            A0 a0 = new A0();
            a0.A0G("NUX_WAKE_WORD_INTRO_STEP_DIALOG");
            a0.A0C(R.string.assistant_nux_wake_word_title);
            a0.A09(R.string.assistant_nux_wake_word_description);
            a0.A01.putString("information-box", C1300wu.A02(R.string.assistant_nux_wake_word_info_box));
            a0.A0A(R.string.assistant_nux_wake_word_primary);
            a0.A0B(R.string.dialog_not_now);
            return a0;
        } else if ((this instanceof C1282wc) || (this instanceof C1281wb) || (this instanceof A1)) {
            return null;
        } else {
            if (this instanceof A7) {
                A9 a9 = new A9();
                a9.A0G("NUX_TAKE_A_PHOTO_STEP_DIALOG");
                a9.A0C(R.string.assistant_nux_take_screenshot_description);
                a9.A0A(R.string.assistant_nux_skip_button);
                return a9;
            } else if (this instanceof C1268wO) {
                AM am = new AM((C1268wO) this);
                am.A0G("NUX_PRIVACY_STEP_DIALOG");
                Bundle bundle = am.A01;
                bundle.putInt("width", 512);
                bundle.putInt("height", 440);
                am.A0E("dialog");
                am.A0C(R.string.nux_voice_commands_privacy_title);
                bundle.putBoolean("auto-close-primary", true);
                C1306x0 x0Var = new C1306x0();
                x0Var.A02 = -1;
                Integer[] numArr = x0Var.A05.A00;
                numArr[0] = null;
                numArr[2] = 28;
                C1308x2 x2Var = new C1308x2(R.string.nux_store_voice_command);
                C1309x3 x3Var = new C1309x3(true);
                x3Var.A03 = "NUX_PRIVACY_STEP_TOGGLE_ID";
                x3Var.A02 = 48;
                ((XA) x3Var).A00 = 24;
                x0Var.A02(x2Var);
                x0Var.A00.add(new XC(x0Var, x3Var));
                C1308x2 x2Var2 = new C1308x2(R.string.nux_voice_commands_privacy_description_paragraph_1);
                Integer[] numArr2 = ((XA) x2Var2).A04.A00;
                numArr2[0] = null;
                numArr2[4] = 24;
                XA[] xaArr = {x2Var2, new C1308x2(R.string.nux_voice_commands_privacy_description_paragraph_2), x0Var};
                int i = 0;
                do {
                    am.A02.add(xaArr[i]);
                    i++;
                } while (i < 3);
                if (SystemUXUtil.A00() < 6) {
                    C1304wy wyVar = new C1304wy(BX.A00().getString(R.string.dialog_continue_setup));
                    wyVar.A03 = "primary";
                    ((XA) wyVar).A00 = 42;
                    wyVar.A00 = "primary";
                    ((XA) wyVar).A02 = -1;
                    Integer[] numArr3 = wyVar.A04.A00;
                    numArr3[0] = null;
                    numArr3[2] = 24;
                    am.A0D(wyVar);
                    bundle.putInt("height", 364);
                    return am;
                }
                am.A0A(R.string.dialog_continue_setup);
                return am;
            } else if (!(this instanceof C1265wL)) {
                return null;
            } else {
                AS as = new AS();
                as.A0G("NUX_DOUBLE_TAP_STEP_DIALOG");
                Bundle bundle2 = as.A01;
                bundle2.putInt("height", 508);
                as.A0C(R.string.assistant_nux_double_tap_title);
                as.A09(R.string.assistant_nux_double_tap_description);
                bundle2.putString("tertiary", C1300wu.A02(R.string.assistant_nux_double_tap_skip));
                bundle2.putBoolean("auto-close-secondary", false);
                bundle2.putBoolean("back", false);
                if (C0398Vv.A07) {
                    str = "assistant_nux_double_tap_video_quest2.mp4";
                } else {
                    str = "assistant_nux_double_tap_video.mp4";
                }
                Application A002 = BX.A00();
                C0514bB.A01(A002, "ApplicationHolder.get()");
                bundle2.putString("video", AnonymousClass08.A06("apk://", A002.getPackageName(), "/assets/", str));
                as.A0E("system-dialog");
                return as;
            }
        }
    }

    public final String A03() {
        if (this instanceof C00959z) {
            return "wake_word_step";
        }
        if (!(this instanceof AbstractC1279wZ)) {
            return "dialog_only_step";
        }
        return "voice_command_step";
    }

    public void A04() {
        if (this instanceof C1264wK) {
            C0112Aq.A00().A01(new C1290wk());
        }
    }

    public final boolean A07() {
        if ((this instanceof C00959z) || (this instanceof C1282wc)) {
            return false;
        }
        if ((this instanceof C1281wb) || (this instanceof AbstractC1279wZ)) {
            return true;
        }
        if (this instanceof C1268wO) {
            return false;
        }
        if ((this instanceof C1265wL) || !(this instanceof C1264wK)) {
            return true;
        }
        return false;
    }

    public AssetFileDescriptor A08(Resources resources, Integer num) {
        int i;
        AssetFileDescriptor openRawResourceFd;
        int i2;
        int intValue;
        C0514bB.A02(resources, "resources");
        C0514bB.A02(num, "audioType");
        int i3 = C0399Wa.A00[num.intValue()];
        AssetFileDescriptor assetFileDescriptor = null;
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 == 3) {
                    intValue = R.raw.tts_silence_first_error;
                }
            } else if (!(this instanceof C00959z) && !(this instanceof C1282wc) && !(this instanceof C1281wb)) {
                if (this instanceof A1) {
                    i2 = R.raw.tts_double_tap_what_time_is_it_error;
                } else if (this instanceof A7) {
                    i2 = R.raw.tts_double_tap_take_a_photo_error;
                } else if (!(this instanceof C1268wO) && (this instanceof C1265wL)) {
                    i2 = R.raw.tts_double_tap_timeout;
                }
                Integer valueOf = Integer.valueOf(i2);
                if (valueOf != null) {
                    intValue = valueOf.intValue();
                }
            }
            assetFileDescriptor = resources.openRawResourceFd(intValue);
        } else {
            if (this instanceof C00959z) {
                i = R.raw.tts_wake_work_intro_instruction;
            } else if (this instanceof C1282wc) {
                i = ((C1282wc) this).A00;
            } else if (!(this instanceof C1281wb)) {
                if (this instanceof A1) {
                    i = R.raw.tts_what_time_is_it_instruction;
                } else if (this instanceof A7) {
                    i = R.raw.tts_take_a_photo_instruction;
                } else if (this instanceof C1268wO) {
                    i = ((C1268wO) this).A01;
                } else if (this instanceof C1265wL) {
                    i = R.raw.tts_double_tap_instruction;
                } else if (this instanceof C1264wK) {
                    i = R.raw.tts_double_press_feedback_instruction;
                }
            }
            Integer valueOf2 = Integer.valueOf(i);
            if (!(valueOf2 == null || (openRawResourceFd = resources.openRawResourceFd(valueOf2.intValue())) == null)) {
                assetFileDescriptor = openRawResourceFd;
            }
        }
        this.A00 = assetFileDescriptor;
        return assetFileDescriptor;
    }
}
