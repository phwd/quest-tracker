package com.oculus.security.basecomponent;

import X.AnonymousClass0B0;
import X.AnonymousClass0B1;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class OculusLocalSecureBroadcastReceiver extends OculusSecureBroadcastReceiverBase {
    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public final void registerReceiver(Context context) {
        AnonymousClass0B1 A00 = AnonymousClass0B1.A00(context);
        IntentFilter intentFilter = getIntentFilter();
        HashMap<BroadcastReceiver, ArrayList<AnonymousClass0B0>> hashMap = A00.A02;
        synchronized (hashMap) {
            AnonymousClass0B0 r5 = new AnonymousClass0B0(intentFilter, this);
            ArrayList<AnonymousClass0B0> arrayList = hashMap.get(this);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                hashMap.put(this, arrayList);
            }
            arrayList.add(r5);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                HashMap<String, ArrayList<AnonymousClass0B0>> hashMap2 = A00.A01;
                ArrayList<AnonymousClass0B0> arrayList2 = hashMap2.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    hashMap2.put(action, arrayList2);
                }
                arrayList2.add(r5);
            }
        }
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public void unregisterReceiver(Context context) {
        AnonymousClass0B1 A00 = AnonymousClass0B1.A00(context);
        HashMap<BroadcastReceiver, ArrayList<AnonymousClass0B0>> hashMap = A00.A02;
        synchronized (hashMap) {
            ArrayList<AnonymousClass0B0> remove = hashMap.remove(this);
            if (remove != null) {
                for (int size = remove.size() - 1; size >= 0; size--) {
                    AnonymousClass0B0 r7 = remove.get(size);
                    r7.A00 = true;
                    for (int i = 0; i < r7.A02.countActions(); i++) {
                        String action = r7.A02.getAction(i);
                        HashMap<String, ArrayList<AnonymousClass0B0>> hashMap2 = A00.A01;
                        ArrayList<AnonymousClass0B0> arrayList = hashMap2.get(action);
                        if (arrayList != null) {
                            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                                AnonymousClass0B0 r1 = arrayList.get(size2);
                                if (r1.A01 == this) {
                                    r1.A00 = true;
                                    arrayList.remove(size2);
                                }
                            }
                            if (arrayList.size() <= 0) {
                                hashMap2.remove(action);
                            }
                        }
                    }
                }
            }
        }
    }

    public OculusLocalSecureBroadcastReceiver(String... strArr) {
        super(strArr);
    }
}
