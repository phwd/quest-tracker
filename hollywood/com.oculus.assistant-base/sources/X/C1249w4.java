package X;

import android.database.Cursor;
import android.database.SQLException;
import android.preference.PreferenceManager;
import com.facebook.assistant.clientplatform.keyboard.learning.util.LearningDataUtils;
import com.facebook.assistant.clientplatform.keyboard.learning.util.LearningStore;
import com.facebook.papaya.client.Papaya$LogDelegate;
import com.google.common.base.Function;
import com.oculus.assistant.learning.ExecutionJobService;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* renamed from: X.w4  reason: case insensitive filesystem */
public final class C1249w4 implements Function {
    public final /* synthetic */ ExecutionJobService A00;

    public C1249w4(ExecutionJobService executionJobService) {
        this.A00 = executionJobService;
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        ArrayList arrayList;
        String[] split;
        C0904oW oWVar = (C0904oW) obj;
        if (oWVar == null) {
            ExecutionJobService executionJobService = this.A00;
            C0139Dd.A09("com.oculus.assistant.learning.ExecutionJobService", "Error adding Falco Logger: Papaya instance null");
            WF wf = executionJobService.A00;
            wf.A07 = "Error adding Falco Logger: Papaya instance null";
            executionJobService.A00 = wf;
            return null;
        }
        ExecutionJobService executionJobService2 = this.A00;
        LearningStore A002 = LearningStore.A00("oculus", executionJobService2);
        synchronized (A002) {
            arrayList = new ArrayList();
            Cursor rawQuery = A002.A00.getWritableDatabase().rawQuery(A002.A00.A00, null);
            try {
                if (rawQuery.moveToFirst()) {
                    int columnIndexOrThrow = rawQuery.getColumnIndexOrThrow("input_text");
                    do {
                        String string = rawQuery.getString(columnIndexOrThrow);
                        if (string != null) {
                            if (string.isEmpty()) {
                                split = new String[0];
                            } else {
                                split = string.split(LearningDataUtils.A00);
                            }
                            arrayList.add(new int[]{split.length, Collections.frequency(Arrays.asList(split), "__UNKNOWN__")});
                        }
                    } while (rawQuery.moveToNext());
                }
                if (!rawQuery.isClosed()) {
                    rawQuery.close();
                }
                try {
                } catch (SQLException | IllegalArgumentException e) {
                    ExecutionJobService executionJobService3 = this.A00;
                    WF wf2 = executionJobService3.A00;
                    wf2.A09 = e.getClass().getName();
                    wf2.A07 = e.getMessage();
                    StringWriter stringWriter = new StringWriter();
                    e.printStackTrace(new PrintWriter(stringWriter));
                    wf2.A08 = stringWriter.toString();
                    executionJobService3.A00 = wf2;
                    C0139Dd.A0S("com.oculus.assistant.learning.ExecutionJobService", e, "Error reading token counts from dataset");
                    return null;
                }
            } catch (Throwable th) {
                if (rawQuery != null && !rawQuery.isClosed()) {
                    rawQuery.close();
                }
                throw th;
            }
        }
        WF wf3 = executionJobService2.A00;
        wf3.A00 = Long.valueOf((long) arrayList.size());
        executionJobService2.A00 = wf3;
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        ArrayList arrayList3 = new ArrayList(arrayList.size());
        ArrayList arrayList4 = new ArrayList(arrayList.size());
        int i = 0;
        while (true) {
            if (i >= arrayList.size()) {
                break;
            }
            int[] iArr = (int[]) arrayList.get(i);
            if (iArr.length != 2) {
                C0139Dd.A09("com.oculus.assistant.learning.ExecutionJobService", "Error in length of tokenCounts");
                break;
            }
            arrayList2.add(Long.valueOf((long) i));
            arrayList3.add(Long.valueOf((long) iArr[0]));
            arrayList4.add(Long.valueOf((long) iArr[1]));
            i++;
        }
        WF wf4 = executionJobService2.A00;
        wf4.A0A = arrayList2;
        wf4.A0C = arrayList3;
        wf4.A0B = arrayList4;
        executionJobService2.A00 = wf4;
        long j = PreferenceManager.getDefaultSharedPreferences(executionJobService2.getApplicationContext()).getLong("assistant_keyboard_fl_papaya_last_submit", -1);
        if (j != -1) {
            WF wf5 = executionJobService2.A00;
            wf5.A05 = Long.valueOf(j);
            executionJobService2.A00 = wf5;
        }
        C1253w9 w9Var = new C1253w9(executionJobService2.A00);
        Papaya$LogDelegate papaya$LogDelegate = oWVar.A00;
        synchronized (papaya$LogDelegate) {
            papaya$LogDelegate.A00.put(w9Var.getId(), w9Var);
        }
        return oWVar;
    }
}
