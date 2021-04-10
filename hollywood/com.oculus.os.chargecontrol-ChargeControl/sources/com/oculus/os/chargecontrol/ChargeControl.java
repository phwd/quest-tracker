package com.oculus.os.chargecontrol;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.oculus.os.ui.VrActivity;
import java.util.Timer;
import java.util.TimerTask;

public class ChargeControl extends VrActivity {
    private boolean chargeLimitEnabled_;
    private View container_;
    private TextView currentChargeView_;
    private Handler handler_;
    private TextView limitEnabledView_;
    private Button limitToggleButton_;
    private Button quitButton_;
    private final Timer timer_ = new Timer("ui-update");
    private TimerTask updateTick_;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ChargeControl.super.onCreate(bundle);
        setContentView((int) R.layout.activity);
        this.currentChargeView_ = (TextView) findViewById((int) R.id.current_charge);
        this.limitEnabledView_ = (TextView) findViewById((int) R.id.limit_enabled);
        this.limitToggleButton_ = (Button) findViewById((int) R.id.limit_toggle_button);
        this.quitButton_ = (Button) findViewById((int) R.id.quit_button);
        this.container_ = findViewById((int) R.id.view_container);
        this.handler_ = new Handler(new Handler.Callback() {
            /* class com.oculus.os.chargecontrol.$$Lambda$ChargeControl$2UJLtXXj3mCwSTkjnnkr4r9JGo */

            public final boolean handleMessage(Message message) {
                return ChargeControl.this.lambda$onCreate$0$ChargeControl(message);
            }
        });
        this.quitButton_.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.os.chargecontrol.$$Lambda$ChargeControl$I9TN1ojxXLJd87J9iUfXnT4lZz8 */

            public final void onClick(View view) {
                ChargeControl.this.lambda$onCreate$1$ChargeControl(view);
            }
        });
        this.limitToggleButton_.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.os.chargecontrol.$$Lambda$ChargeControl$WsyutN943FobeLoipCYHrzgZYS0 */

            public final void onClick(View view) {
                ChargeControl.this.lambda$onCreate$2$ChargeControl(view);
            }
        });
        updateAllViews();
    }

    public /* synthetic */ boolean lambda$onCreate$0$ChargeControl(Message message) {
        updateAllViews();
        return true;
    }

    public /* synthetic */ void lambda$onCreate$1$ChargeControl(View view) {
        finish();
    }

    public /* synthetic */ void lambda$onCreate$2$ChargeControl(View view) {
        this.chargeLimitEnabled_ = !this.chargeLimitEnabled_;
        reconfigureChargeLimit();
        updateAllViews();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        ChargeControl.super.onResume();
        this.updateTick_ = new PeriodicUpdateTask();
        this.timer_.purge();
        this.timer_.scheduleAtFixedRate(this.updateTick_, 0, 60000);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        ChargeControl.super.onPause();
        this.updateTick_.cancel();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.oculus.os.chargecontrol.ChargeControl */
    /* JADX WARN: Multi-variable type inference failed */
    private void reconfigureChargeLimit() {
        Intent intent = new Intent((Context) this, (Class<?>) ChargeMonitorService.class);
        intent.setAction(this.chargeLimitEnabled_ ? "com.oculus.os.chargecontrol.action.start" : "com.oculus.os.chargecontrol.action.stop");
        startService(intent);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: com.oculus.os.chargecontrol.ChargeControl */
    /* JADX WARN: Multi-variable type inference failed */
    private void updateCurrentChargeView() {
        String str;
        int currentCharge = ChargeMonitorService.getCurrentCharge(this);
        if (currentCharge < 52 || currentCharge > 58) {
            this.container_.setBackgroundColor(Color.argb(255, 250, 250, 250));
        } else {
            this.container_.setBackgroundColor(-16711936);
        }
        this.currentChargeView_.setText(getResources().getString(R.string.current_charge, Integer.valueOf(currentCharge)));
        if (!this.chargeLimitEnabled_) {
            str = getResources().getString(R.string.disabled);
        } else if (!ChargeMonitorService.isChargingEnabled()) {
            str = getResources().getString(R.string.discharging);
        } else if (ChargeMonitorService.isCharging(this)) {
            str = getResources().getString(R.string.charging);
        } else {
            str = getResources().getString(R.string.unable_to_charge);
        }
        this.limitEnabledView_.setText(getResources().getString(R.string.charge_limit_info, str));
    }

    private void updateAllViews() {
        this.limitToggleButton_.setText(getResources().getString(this.chargeLimitEnabled_ ? R.string.disable_limit : R.string.enable_limit));
        updateCurrentChargeView();
    }

    private class PeriodicUpdateTask extends TimerTask {
        private PeriodicUpdateTask() {
        }

        public void run() {
            ChargeControl.this.handler_.obtainMessage(1).sendToTarget();
        }
    }
}
