package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@Deprecated
/* renamed from: X.0B0  reason: invalid class name */
public final class AnonymousClass0B0 {
    public static AnonymousClass0B0 A05;
    public static final Object A06 = new Object();
    public final Context A00;
    public final ArrayList<AnonymousClass0Ay> A01 = new ArrayList<>();
    public final HashMap<BroadcastReceiver, ArrayList<AnonymousClass0Az>> A02 = new HashMap<>();
    public final Handler A03;
    public final HashMap<String, ArrayList<AnonymousClass0Az>> A04 = new HashMap<>();

    @NonNull
    public static AnonymousClass0B0 A00(@NonNull Context context) {
        AnonymousClass0B0 r1;
        synchronized (A06) {
            r1 = A05;
            if (r1 == null) {
                r1 = new AnonymousClass0B0(context.getApplicationContext());
                A05 = r1;
            }
        }
        return r1;
    }

    public final void A01(@NonNull BroadcastReceiver broadcastReceiver) {
        HashMap<BroadcastReceiver, ArrayList<AnonymousClass0Az>> hashMap = this.A02;
        synchronized (hashMap) {
            ArrayList<AnonymousClass0Az> remove = hashMap.remove(broadcastReceiver);
            if (remove != null) {
                for (int size = remove.size() - 1; size >= 0; size--) {
                    AnonymousClass0Az r7 = remove.get(size);
                    r7.A01 = true;
                    for (int i = 0; i < r7.A02.countActions(); i++) {
                        String action = r7.A02.getAction(i);
                        HashMap<String, ArrayList<AnonymousClass0Az>> hashMap2 = this.A04;
                        ArrayList<AnonymousClass0Az> arrayList = hashMap2.get(action);
                        if (arrayList != null) {
                            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                                AnonymousClass0Az r1 = arrayList.get(size2);
                                if (r1.A03 == broadcastReceiver) {
                                    r1.A01 = true;
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

    public final void A02(@NonNull BroadcastReceiver broadcastReceiver, @NonNull IntentFilter intentFilter) {
        HashMap<BroadcastReceiver, ArrayList<AnonymousClass0Az>> hashMap = this.A02;
        synchronized (hashMap) {
            AnonymousClass0Az r5 = new AnonymousClass0Az(intentFilter, broadcastReceiver);
            ArrayList<AnonymousClass0Az> arrayList = hashMap.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                hashMap.put(broadcastReceiver, arrayList);
            }
            arrayList.add(r5);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                HashMap<String, ArrayList<AnonymousClass0Az>> hashMap2 = this.A04;
                ArrayList<AnonymousClass0Az> arrayList2 = hashMap2.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    hashMap2.put(action, arrayList2);
                }
                arrayList2.add(r5);
            }
        }
    }

    public final void A03(@NonNull Intent intent) {
        synchronized (this.A02) {
            String action = intent.getAction();
            String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.A00.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            intent.getFlags();
            ArrayList<AnonymousClass0Az> arrayList = this.A04.get(intent.getAction());
            if (arrayList != null) {
                ArrayList arrayList2 = null;
                for (int i = 0; i < arrayList.size(); i++) {
                    AnonymousClass0Az r5 = arrayList.get(i);
                    if (!r5.A00 && r5.A02.match(action, resolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager") >= 0) {
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        arrayList2.add(r5);
                        r5.A00 = true;
                    }
                }
                if (arrayList2 != null) {
                    for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                        ((AnonymousClass0Az) arrayList2.get(i2)).A00 = false;
                    }
                    this.A01.add(new AnonymousClass0Ay(intent, arrayList2));
                    Handler handler = this.A03;
                    if (!handler.hasMessages(1)) {
                        handler.sendEmptyMessage(1);
                    }
                }
            }
        }
    }

    public AnonymousClass0B0(Context context) {
        this.A00 = context;
        this.A03 = new HandlerC00590Ax(this, context.getMainLooper());
    }
}
