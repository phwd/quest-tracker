package com.facebook.assistant.clientplatform.keyboard.modeltypeahead;

import X.AbstractC0103Ah;
import X.AbstractC0105Aj;
import X.C00658s;
import X.C00698x;
import X.C0112Aq;
import X.C0616d1;
import X.C0770gw;
import X.C0773gz;
import X.C0776h5;
import X.C0777h6;
import X.C0778h7;
import X.C0779hA;
import X.RunnableC00678u;
import X.h1;
import X.h3;
import X.h4;
import X.h8;
import android.content.Context;
import com.facebook.models.interfaces.ModelLoaderBase;
import java.util.concurrent.ExecutorService;

public final class ByteLMWordTypeaheadProvider implements AbstractC0103Ah {
    public SupplementalDictionarySuggestionProvider A00;
    public C0112Aq A01;
    public ExecutorService A02;
    public final C00658s A03;
    public final LiteLMSuggestionPredictor A04;
    public final C00698x A05;
    public final VocabLoader A06;
    public final C0779hA A07;
    public final AbstractC0105Aj A08 = new C0778h7(this);
    public AbstractC0105Aj mTypeaheadLoggingUtilsSubscriber = new h8(this);

    public ByteLMWordTypeaheadProvider(Context context, ModelLoaderBase modelLoaderBase, ExecutorService executorService, C00698x r13, C0616d1 d1Var) {
        VocabLoader vocabLoader = new VocabLoader(r13, context, executorService);
        this.A02 = executorService;
        this.A05 = r13;
        this.A06 = vocabLoader;
        this.A03 = new C00658s();
        this.A01 = C0112Aq.A00();
        C00698x r3 = this.A05;
        String str = r3.A00;
        if (str != null) {
            this.A00 = new SupplementalDictionarySuggestionProvider(str, context);
        } else {
            this.A00 = null;
        }
        if ((r3.A03 & 2) > 0) {
            this.A07 = new C0779hA(executorService);
        } else {
            this.A07 = null;
        }
        this.A04 = new LiteLMSuggestionPredictor(r13, modelLoaderBase, executorService, context, d1Var);
        this.A06.A01().addListener(new RunnableC00678u(this), executorService);
    }

    @Override // X.AbstractC0103Ah
    public final void A3e() {
        C00658s r4 = this.A03;
        C0112Aq aq = this.A01;
        aq.A02(C0773gz.class, r4.mStartTypeaheadSession);
        aq.A02(h1.class, r4.mStopTypeaheadSession);
        aq.A02(h4.class, r4.mTextForTypeAhead);
        aq.A02(C0776h5.class, r4.mTypeaheadSuggestion);
        aq.A02(C0770gw.class, r4.mModelInference);
        aq.A02(h3.class, r4.mSuggestionClicked);
        this.A01.A02(h4.class, this.A08);
        this.A01.A02(C0777h6.class, this.mTypeaheadLoggingUtilsSubscriber);
    }

    @Override // X.AbstractC0103Ah
    public final void A3g() {
        C00658s r4 = this.A03;
        C0112Aq aq = this.A01;
        aq.A04(C0773gz.class, r4.mStartTypeaheadSession);
        aq.A04(h1.class, r4.mStopTypeaheadSession);
        aq.A04(h4.class, r4.mTextForTypeAhead);
        aq.A04(C0776h5.class, r4.mTypeaheadSuggestion);
        aq.A04(C0770gw.class, r4.mModelInference);
        aq.A04(h3.class, r4.mSuggestionClicked);
        this.A01.A04(h4.class, this.A08);
        this.A01.A04(C0777h6.class, this.mTypeaheadLoggingUtilsSubscriber);
    }
}
