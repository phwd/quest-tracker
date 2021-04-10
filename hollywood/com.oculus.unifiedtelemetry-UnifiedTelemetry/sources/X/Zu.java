package X;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Zu implements AbstractC0067By {
    public final /* synthetic */ Ae A00;

    public Zu(Ae ae) {
        this.A00 = ae;
    }

    @Override // X.AbstractC0067By
    @NonNull
    public final Bundle A4p() {
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
