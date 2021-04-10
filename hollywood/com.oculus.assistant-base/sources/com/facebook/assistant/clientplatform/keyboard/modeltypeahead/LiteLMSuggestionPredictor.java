package com.facebook.assistant.clientplatform.keyboard.modeltypeahead;

import X.C00698x;
import X.C0139Dd;
import X.C0616d1;
import X.RunnableC00688v;
import android.content.Context;
import com.facebook.models.interfaces.ModelLoaderBase;
import java.util.concurrent.ExecutorService;

public final class LiteLMSuggestionPredictor {
    public ModelState A00;
    public C0616d1 A01;
    public boolean A02 = false;
    public boolean A03 = false;
    public boolean A04 = false;
    public Context A05;
    public final C00698x A06;
    public final ModelLoaderBase A07;
    public final C0616d1 A08;
    public final ExecutorService A09;

    public static synchronized void A00(LiteLMSuggestionPredictor liteLMSuggestionPredictor) {
        synchronized (liteLMSuggestionPredictor) {
            liteLMSuggestionPredictor.A01 = liteLMSuggestionPredictor.A08;
            liteLMSuggestionPredictor.A02 = true;
            liteLMSuggestionPredictor.A03 = false;
            liteLMSuggestionPredictor.A04 = true;
            C0139Dd.A09("com.facebook.assistant.clientplatform.keyboard.modeltypeahead.LiteLMSuggestionPredictor", " Successfully loaded backup model");
        }
    }

    public final void A01() {
        if (!this.A02 && !this.A03) {
            this.A03 = true;
            this.A09.execute(new RunnableC00688v(this));
        }
    }

    public LiteLMSuggestionPredictor(C00698x r2, ModelLoaderBase modelLoaderBase, ExecutorService executorService, Context context, C0616d1 d1Var) {
        this.A06 = r2;
        this.A07 = modelLoaderBase;
        this.A09 = executorService;
        this.A05 = context;
        this.A08 = d1Var;
        this.A00 = new ModelState();
    }
}
