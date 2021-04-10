package com.facebook.assistant.clientplatform.keyboard.modeltypeahead;

import X.AnonymousClass96;
import X.BY;
import X.C00698x;
import X.C0139Dd;
import X.ScheduledExecutorServiceC0337Rr;
import X.V0;
import X.h9;
import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public final class VocabLoader {
    public h9 A00 = new h9();
    public List A01 = new ArrayList();
    public Map A02 = new HashMap();
    public boolean A03 = false;
    public boolean A04 = false;
    public ListenableFuture A05;
    public final Context A06;
    public final C00698x A07;
    public final ExecutorService A08;

    public static BufferedReader A00(String str, Context context) {
        if (str != null) {
            try {
                return new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(str)));
            } catch (IOException e) {
                C0139Dd.A0L("com.facebook.assistant.clientplatform.keyboard.modeltypeahead.VocabLoader", "could not read corpus into memory", e);
                return null;
            }
        } else {
            throw new IllegalArgumentException("File name is null");
        }
    }

    public final ListenableFuture A01() {
        V0 by;
        if (this.A03 || this.A04) {
            return this.A05;
        }
        this.A04 = true;
        ExecutorService executorService = this.A08;
        if (executorService instanceof V0) {
            by = (V0) executorService;
        } else if (executorService instanceof ScheduledExecutorService) {
            by = new ScheduledExecutorServiceC0337Rr((ScheduledExecutorService) executorService);
        } else {
            by = new BY(executorService);
        }
        ListenableFuture A5B = by.A5B(new AnonymousClass96(this));
        this.A05 = A5B;
        return A5B;
    }

    public VocabLoader(C00698x r2, Context context, ExecutorService executorService) {
        this.A07 = r2;
        this.A06 = context;
        this.A08 = executorService;
    }
}
