package X;

import com.facebook.assistant.clientplatform.keyboard.modeltypeahead.SupplementalDictionarySuggestionProvider;
import java.util.Comparator;

/* renamed from: X.94  reason: invalid class name */
public final class AnonymousClass94 implements Comparator {
    public final /* synthetic */ SupplementalDictionarySuggestionProvider A00;

    public AnonymousClass94(SupplementalDictionarySuggestionProvider supplementalDictionarySuggestionProvider) {
        this.A00 = supplementalDictionarySuggestionProvider;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((Number) obj2).intValue() - ((Number) obj).intValue();
    }
}
