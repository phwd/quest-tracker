package X;

import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.messenger.assistant.thrift.AssistantAction;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class FN {
    public final Map A00 = new HashMap();

    public final Object A00(FO fo) {
        Map map = this.A00;
        if (!map.containsKey(fo)) {
            return null;
        }
        AssistantAction assistantAction = (AssistantAction) map.get(fo);
        int A03 = assistantAction.A03();
        int i = fo.get();
        if (A03 != i) {
            throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("we claim to have a type but can't find it %d", Integer.valueOf(i)));
        }
        int i2 = assistantAction.A01;
        if (i2 != -1) {
            return assistantAction.A00(i2);
        }
        throw new IllegalStateException(AnonymousClass08.A04(assistantAction.A02, " is not a union!"));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (FO fo : this.A00.keySet()) {
            sb.append(fo.toString());
            sb.append("\n");
            sb.append(A00(fo));
            sb.append("\n");
        }
        return sb.toString();
    }

    public FN(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AssistantAction assistantAction = (AssistantAction) it.next();
            FO fromAssistantAction = FO.fromAssistantAction(assistantAction);
            if (fromAssistantAction != FO.UNKNOWN_ACTION_TYPE) {
                this.A00.put(fromAssistantAction, assistantAction);
            }
        }
    }
}
