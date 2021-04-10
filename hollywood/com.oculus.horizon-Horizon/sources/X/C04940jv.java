package X;

import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.push.service.FbnsService;
import java.util.ArrayList;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0jv  reason: invalid class name and case insensitive filesystem */
public final class C04940jv implements AbstractC02430aW {
    @Override // X.AbstractC02430aW
    public final Bundle A2G(FbnsService fbnsService, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();
        fbnsService.A0Z(arrayList, arrayList2);
        bundle2.putStringArrayList("valid_compatible_apps", arrayList);
        bundle2.putStringArrayList("enabled_compatible_apps", arrayList2);
        ArrayList<String> arrayList3 = new ArrayList<>();
        fbnsService.A0Y(arrayList3);
        bundle2.putStringArrayList("registered_apps", arrayList3);
        return bundle2;
    }

    @Override // X.AbstractC02430aW
    public final void A2H(FbnsService fbnsService, Bundle bundle) {
        AnonymousClass0NO.A08("AppsStatisticsFetcher", "not implemented for AppsStatisticsFetcher");
        throw new IllegalArgumentException("not implemented for AppsStatisticsFetcher");
    }
}
