package org.chromium.components.offline_items_collection.bridges;

import java.util.ArrayList;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.OfflineItemSchedule;
import org.chromium.components.offline_items_collection.UpdateDelta;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class OfflineItemBridge {
    public static ArrayList createArrayList() {
        return new ArrayList();
    }

    public static OfflineItem createOfflineItemAndMaybeAddToList(ArrayList arrayList, String str, String str2, String str3, String str4, int i, boolean z, boolean z2, boolean z3, boolean z4, long j, boolean z5, long j2, long j3, long j4, boolean z6, String str5, String str6, String str7, String str8, boolean z7, String str9, int i2, int i3, int i4, boolean z8, boolean z9, long j5, long j6, long j7, int i5, long j8, boolean z10, boolean z11, boolean z12, double d, OfflineItemSchedule offlineItemSchedule) {
        Long l;
        OfflineItem offlineItem = new OfflineItem();
        C0788My my = offlineItem.F;
        my.f8514a = str;
        my.b = str2;
        offlineItem.G = str3;
        offlineItem.H = str4;
        offlineItem.I = i;
        offlineItem.f10857J = z;
        offlineItem.K = z2;
        offlineItem.L = z3;
        offlineItem.M = z4;
        offlineItem.Q = j;
        offlineItem.R = z5;
        offlineItem.S = j2;
        offlineItem.T = j3;
        offlineItem.U = j4;
        offlineItem.V = z6;
        offlineItem.W = str5;
        offlineItem.X = str6;
        offlineItem.Y = str7;
        offlineItem.Z = str8;
        offlineItem.a0 = z7;
        offlineItem.b0 = str9;
        offlineItem.c0 = i2;
        offlineItem.j0 = i3;
        offlineItem.k0 = i4;
        offlineItem.d0 = z8;
        offlineItem.e0 = z9;
        offlineItem.f0 = j5;
        if (j7 == -1) {
            l = null;
        } else {
            l = Long.valueOf(j7);
        }
        offlineItem.g0 = new C0288Er0(j6, l, i5);
        offlineItem.h0 = j8;
        offlineItem.i0 = z10;
        offlineItem.N = z11;
        offlineItem.O = z12;
        offlineItem.P = d;
        offlineItem.l0 = offlineItemSchedule;
        if (arrayList != null) {
            arrayList.add(offlineItem);
        }
        return offlineItem;
    }

    public static OfflineItemSchedule createOfflineItemSchedule(boolean z, long j) {
        return new OfflineItemSchedule(z, j);
    }

    public static UpdateDelta createUpdateDelta(boolean z, boolean z2) {
        UpdateDelta updateDelta = new UpdateDelta();
        updateDelta.f10860a = z;
        updateDelta.b = z2;
        return updateDelta;
    }
}
