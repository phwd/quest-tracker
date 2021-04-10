package X;

import android.app.Application;
import android.database.Cursor;
import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import com.oculus.assistant.assistantutils.SystemUXUtil;
import com.oculus.os.SettingsManager;

/* renamed from: X.Xm  reason: case insensitive filesystem */
public final class C0434Xm {
    public final AnonymousClass8J A00;
    public final AnonymousClass8U A01 = C1321xF.A00;
    public final w1 A02;
    public final YB A03;
    public final SettingsManager A04;

    public final /* synthetic */ void A00() {
        C00799i.A00.logFulfillment("Enabling Fitness Overlay");
        YP.A00().A03();
        SettingsManager settingsManager = this.A04;
        if (settingsManager.getBoolean("fitness_tracking_enabled", false)) {
            settingsManager.setBoolean("fitness_tracking_overlay_enabled", true);
        }
    }

    public final /* synthetic */ void A01() {
        C00799i.A00.logFulfillment("Disabling Fitness Overlay");
        YP.A00().A03();
        SettingsManager settingsManager = this.A04;
        if (settingsManager.getBoolean("fitness_tracking_enabled", false)) {
            settingsManager.setBoolean("fitness_tracking_overlay_enabled", false);
        }
    }

    public final /* synthetic */ void A02() {
        C00799i.A00.logFulfillment("Disabling In-App Voice Command");
        YP.A00().A03();
        this.A04.setBoolean("in_app_voice_commands_enabled_v2", false);
    }

    public final /* synthetic */ void A03() {
        C0460Zq zq;
        AssistantLogger assistantLogger = C00799i.A00;
        assistantLogger.logFulfillment("Enabling Hands");
        YP.A00().A03();
        SettingsManager settingsManager = this.A04;
        settingsManager.setBoolean("hand_tracking_opt_in", true);
        settingsManager.setBoolean("hand_tracking_enabled", true);
        Application A002 = BX.A00();
        Cursor query = A002.getContentResolver().query(Zv.A00, null, null, null, null);
        if (query == null) {
            zq = new C0460Zq(false);
        } else {
            query.moveToFirst();
            int columnIndex = query.getColumnIndex("default_browser");
            if (columnIndex >= 0) {
                query.getString(columnIndex);
            }
            int columnIndex2 = query.getColumnIndex("nux_result");
            if (columnIndex2 >= 0) {
                query.getString(columnIndex2);
            }
            int columnIndex3 = query.getColumnIndex("nux_seen_count");
            if (columnIndex3 >= 0) {
                query.getInt(columnIndex3);
            }
            int columnIndex4 = query.getColumnIndex("nux_state");
            if (columnIndex4 >= 0) {
                query.getString(columnIndex4);
            }
            int columnIndex5 = query.getColumnIndex("id");
            if (columnIndex5 >= 0) {
                query.getString(columnIndex5);
            }
            C0461Zr.A00(query, query.getColumnIndex("has_seen_confirm_quit"));
            C0461Zr.A00(query, query.getColumnIndex("has_seen_confirm_quit_notif"));
            C0461Zr.A00(query, query.getColumnIndex("has_seen_focus"));
            C0461Zr.A00(query, query.getColumnIndex("has_seen_internet_browser_prompt"));
            C0461Zr.A00(query, query.getColumnIndex("has_seen_tutorial_prompt"));
            C0461Zr.A00(query, query.getColumnIndex("has_seen_party_calls_nux"));
            C0461Zr.A00(query, query.getColumnIndex("has_seen_saved_prompt"));
            C0461Zr.A00(query, query.getColumnIndex("has_seen_long_hsw"));
            C0461Zr.A00(query, query.getColumnIndex("has_opted_out_hsw"));
            C0461Zr.A00(query, query.getColumnIndex("has_opted_out_of_malibu_recenter"));
            int columnIndex6 = query.getColumnIndex("high_priority_apps_download_status");
            if (columnIndex6 >= 0) {
                AnonymousClass09.A00(3);
                query.getInt(columnIndex6);
            }
            int columnIndex7 = query.getColumnIndex("high_priority_apps_download_waittime");
            if (columnIndex7 >= 0) {
                query.getLong(columnIndex7);
            }
            C0461Zr.A00(query, query.getColumnIndex("has_docked"));
            boolean A003 = C0461Zr.A00(query, query.getColumnIndex("has_seen_hand_tracking_nux"));
            C0461Zr.A00(query, query.getColumnIndex("has_finished_full_vr_nux"));
            C0461Zr.A00(query, query.getColumnIndex("has_finished_nux"));
            C0461Zr.A00(query, query.getColumnIndex("has_seen_nux"));
            C0461Zr.A00(query, query.getColumnIndex("has_finished_ipd_adjust"));
            C0461Zr.A00(query, query.getColumnIndex("has_finished_monterey_nux"));
            int columnIndex8 = query.getColumnIndex("rollout_token");
            if (columnIndex8 >= 0) {
                query.getString(columnIndex8);
            }
            int columnIndex9 = query.getColumnIndex("high_priority_apps_pkgs");
            if (columnIndex9 >= 0) {
                query.getString(columnIndex9);
            }
            query.close();
            zq = new C0460Zq(A003);
        }
        if (!zq.A00) {
            assistantLogger.logFulfillment("About to launch hand tracking NUX");
            BX.A00().sendBroadcast(SystemUXUtil.A03(ZG.HAND_TRACKING_NUX, false, new String[0]));
        }
    }

    public final /* synthetic */ void A04() {
        C00799i.A00.logFulfillment("Disabling Hands");
        YP.A00().A03();
        this.A04.setBoolean("hand_tracking_opt_in", false);
    }

    public C0434Xm(AnonymousClass8J r2, YB yb, w1 w1Var, SettingsManager settingsManager) {
        this.A00 = r2;
        this.A03 = yb;
        this.A02 = w1Var;
        this.A04 = settingsManager;
    }
}
