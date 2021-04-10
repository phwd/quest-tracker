package com.oculus.socialplatform.partyservice;

import X.AnonymousClass0RE;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import X.C02670jZ;
import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.oculus.partystatemanager.IPartyServiceDelegate;
import com.oculus.socialplatform.R;
import javax.annotation.Nullable;

public class PartyService extends Service implements IPartyServiceDelegate {
    public static final String TAG = "PartyService";
    public AnonymousClass0RE _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AnonymousClass0lg r2, PartyService partyService) {
        partyService._UL_mInjectionContext = new AnonymousClass0RE(1, r2);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            ((PartyServiceManager) AnonymousClass0VF.A03(0, 17, this._UL_mInjectionContext)).handleStartCommand(this, intent);
        }
        return 1;
    }

    public void onDestroy() {
    }

    @Override // com.oculus.partystatemanager.IPartyServiceDelegate
    public void onUserInParty(long j) {
        ((PartyServiceManager) AnonymousClass0VF.A03(0, 17, this._UL_mInjectionContext)).initNativeCode(j);
        C02670jZ r1 = new C02670jZ(new ComponentName(this, PartyService.class));
        r1.A09 = "FOREGROUND_NOTIFICATION_ACTION";
        startForeground(ForegroundServiceNotificationsIds.PARTY_SERVICE_NOTIFICATION_ID, new Notification.Builder(this).setSmallIcon(R.drawable.app_icon).setContentTitle(getString(R.string.livestreaming_notification_title)).setContentText(getString(R.string.livestreaming_notification_subtitle)).setContentIntent(r1.A03(this, 0)).build());
    }

    @Override // com.oculus.partystatemanager.IPartyServiceDelegate
    public void onUserNotInParty() {
        ((PartyServiceManager) AnonymousClass0VF.A03(0, 17, this._UL_mInjectionContext)).onTearDown();
        stopForeground(true);
        stopSelf();
    }

    public static final void _UL_injectMe(Context context, PartyService partyService) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), partyService);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
        PartyServiceManager partyServiceManager = (PartyServiceManager) AnonymousClass0VF.A03(0, 17, this._UL_mInjectionContext);
        partyServiceManager.setServiceDelegate(this);
        partyServiceManager.onPartyServiceCreated();
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }
}
