package com.oculus.horizon.service.result;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.C08780ya;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.durableiap.DurableIAPStorage;
import com.oculus.durableiap.Purchase;
import com.oculus.horizon.service.OVRService;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import java.util.ArrayList;
import java.util.List;

@Dependencies({"_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_durableiap_DurableIAPStorage_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_config_duc_DUCHelper_ULSEP_BINDING_ID"})
public class DurableIAPResultBuilder extends ResultBuilder {
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final DurableIAPStorage mDurableIAPStorage;
    public final C08780ya mGson = new C08780ya();
    @Inject
    @Eager
    public final OVRLibrary mLibrary;

    @VisibleForTesting
    public static class ItemForJSON {
        public final String sku;

        public ItemForJSON(String str) {
            this.sku = str;
        }
    }

    @VisibleForTesting
    public static class PurchaseForJSON {
        public final long expiration_time;
        public final long grant_time;
        public final String id;
        public final ItemForJSON item;

        public PurchaseForJSON(Purchase purchase) {
            long parseLong;
            String str = purchase.expirationTime;
            if (str == null) {
                parseLong = 0;
            } else {
                parseLong = Long.parseLong(str);
            }
            this.expiration_time = parseLong;
            this.grant_time = Long.parseLong(purchase.grantTime);
            this.id = purchase.purchaseID;
            this.item = new ItemForJSON(purchase.sku);
        }
    }

    @VisibleForTesting
    public static class PurchasesForJSON {
        public final List<PurchaseForJSON> data;

        public PurchasesForJSON(List<Purchase> list) {
            this.data = new ArrayList(list.size());
            for (Purchase purchase : list) {
                this.data.add(new PurchaseForJSON(purchase));
            }
        }
    }

    @Inject
    public DurableIAPResultBuilder(AbstractC06640p5 r3, @Assisted OVRService oVRService) {
        super(oVRService);
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mLibrary = OVRLibraryModule.A00(r3);
        this.mDurableIAPStorage = (DurableIAPStorage) AnonymousClass117.A00(442, r3);
    }
}
