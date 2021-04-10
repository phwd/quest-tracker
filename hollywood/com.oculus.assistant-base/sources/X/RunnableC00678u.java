package X;

import com.facebook.assistant.clientplatform.keyboard.modeltypeahead.ByteLMWordTypeaheadProvider;
import com.facebook.assistant.clientplatform.keyboard.modeltypeahead.SupplementalDictionarySuggestionProvider;
import com.facebook.assistant.clientplatform.keyboard.modeltypeahead.VocabLoader;
import java.util.List;

/* renamed from: X.8u  reason: invalid class name and case insensitive filesystem */
public final class RunnableC00678u implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.keyboard.modeltypeahead.ByteLMWordTypeaheadProvider$3";
    public final /* synthetic */ ByteLMWordTypeaheadProvider A00;

    public RunnableC00678u(ByteLMWordTypeaheadProvider byteLMWordTypeaheadProvider) {
        this.A00 = byteLMWordTypeaheadProvider;
    }

    public final void run() {
        ByteLMWordTypeaheadProvider byteLMWordTypeaheadProvider = this.A00;
        byteLMWordTypeaheadProvider.A04.A01();
        C0779hA hAVar = byteLMWordTypeaheadProvider.A07;
        if (hAVar != null) {
            VocabLoader vocabLoader = byteLMWordTypeaheadProvider.A06;
            if (!vocabLoader.A03) {
                vocabLoader.A01();
            }
            List list = vocabLoader.A01;
            if (!hAVar.A04 && !hAVar.A03) {
                hAVar.A04 = true;
                hAVar.A02 = list;
                hAVar.A05.execute(new AnonymousClass99(hAVar, list));
            }
        }
        SupplementalDictionarySuggestionProvider supplementalDictionarySuggestionProvider = byteLMWordTypeaheadProvider.A00;
        if (supplementalDictionarySuggestionProvider != null && !supplementalDictionarySuggestionProvider.A02 && !supplementalDictionarySuggestionProvider.A03) {
            supplementalDictionarySuggestionProvider.A03 = true;
            new Thread(new AnonymousClass93(supplementalDictionarySuggestionProvider)).start();
        }
    }
}
