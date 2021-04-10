package X;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.oculus.deviceconfigclient.ConfigStorageCache;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0ut  reason: invalid class name */
public class AnonymousClass0ut implements AnonymousClass0C3 {
    public final /* synthetic */ AnonymousClass0Af A00;

    public AnonymousClass0ut(AnonymousClass0Af r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0C3
    @NonNull
    public final Bundle A9U() {
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
        bundle.putParcelableArrayList(ConfigStorageCache.VALUES_JSON_KEY, arrayList2);
        return bundle;
    }
}
