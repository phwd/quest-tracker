package X;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.oculus.deviceconfigclient.ConfigStorageCache;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0rl  reason: invalid class name and case insensitive filesystem */
public class C07270rl implements AbstractC00650Bz {
    public final /* synthetic */ AnonymousClass0Ae A00;

    public C07270rl(AnonymousClass0Ae r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC00650Bz
    @NonNull
    public final Bundle A8R() {
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
