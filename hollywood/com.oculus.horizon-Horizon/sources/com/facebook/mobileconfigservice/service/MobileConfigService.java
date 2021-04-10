package com.facebook.mobileconfigservice.service;

import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rp;
import X.AnonymousClass1ZV;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfigservice.service.IMobileConfig;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigService extends Service {
    public AnonymousClass0QC A00;
    public final IMobileConfig A01 = new IMobileConfig.Stub() {
        /* class com.facebook.mobileconfigservice.service.MobileConfigService.AnonymousClass1 */

        @Override // com.facebook.mobileconfigservice.service.IMobileConfig
        public final void A9X(String str) {
            MobileConfigService mobileConfigService = MobileConfigService.this;
            ((AnonymousClass0Rp) AnonymousClass0J2.A03(1, 306, mobileConfigService.A00)).A2U(mobileConfigService.getApplicationContext());
            AnonymousClass0QC r1 = mobileConfigService.A00;
            ((AnonymousClass1ZV) AnonymousClass0J2.A03(0, 135, r1)).A01(str, ((AnonymousClass0Rp) AnonymousClass0J2.A03(1, 306, r1)).A3D(), null, 0);
        }

        @Override // com.facebook.mobileconfigservice.service.IMobileConfig
        public final void A9Z(String str, String str2, int i) {
            MobileConfigService mobileConfigService = MobileConfigService.this;
            ((AnonymousClass0Rp) AnonymousClass0J2.A03(1, 306, mobileConfigService.A00)).A2U(mobileConfigService.getApplicationContext());
            AnonymousClass0QC r2 = mobileConfigService.A00;
            ((AnonymousClass1ZV) AnonymousClass0J2.A03(0, 135, r2)).A01(str, ((AnonymousClass0Rp) AnonymousClass0J2.A03(1, 306, r2)).A3D(), str2, i);
        }
    };

    public final IBinder onBind(Intent intent) {
        return (IBinder) this.A01;
    }

    public final void onCreate() {
        super.onCreate();
        this.A00 = new AnonymousClass0QC(2, AnonymousClass0J2.get(this));
    }
}
