package X;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import com.oculus.assistant.testui.AssistantTypeaheadTestActivity;

public final class Z8 implements TextWatcher {
    public final /* synthetic */ AssistantTypeaheadTestActivity A00;

    public final void afterTextChanged(Editable editable) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public Z8(AssistantTypeaheadTestActivity assistantTypeaheadTestActivity) {
        this.A00 = assistantTypeaheadTestActivity;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putString("inputText", charSequence.toString());
        this.A00.A01.A00("GetTypeaheadSuggestionMessage", bundle);
    }
}
