package X;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0ca  reason: invalid class name and case insensitive filesystem */
public class C03520ca implements AnonymousClass0GI {
    public final /* synthetic */ C01110Dn A00;

    public C03520ca(C01110Dn r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0GI
    @NonNull
    public final Bundle A7X() {
        Map<String, Object> map = this.A00.A00;
        Set<String> keySet = map.keySet();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(keySet.size());
        ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>(arrayList.size());
        for (String str : keySet) {
            arrayList.add(str);
            arrayList2.add(map.get(str));
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("keys", arrayList);
        bundle.putParcelableArrayList("values", arrayList2);
        return bundle;
    }
}
