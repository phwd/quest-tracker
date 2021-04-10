package X;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.OptionGroup;

/* renamed from: X.cX  reason: case insensitive filesystem */
public final class C0309cX implements Serializable {
    public static final long serialVersionUID = 1;
    public final Map<String, C0310cY> longOpts = new LinkedHashMap();
    public final Map<String, OptionGroup> optionGroups = new HashMap();
    public final List<Object> requiredOpts = new ArrayList();
    public final Map<String, C0310cY> shortOpts = new LinkedHashMap();

    public final void A00(C0310cY cYVar) {
        String str = cYVar.opt;
        if (str == null) {
            str = cYVar.longOpt;
        }
        String str2 = cYVar.longOpt;
        if (str2 != null) {
            this.longOpts.put(str2, cYVar);
        }
        if (cYVar.required) {
            if (this.requiredOpts.contains(str)) {
                List<Object> list = this.requiredOpts;
                list.remove(list.indexOf(str));
            }
            this.requiredOpts.add(str);
        }
        this.shortOpts.put(str, cYVar);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[ Options: [ short ");
        sb.append(this.shortOpts.toString());
        sb.append(" ] [ long ");
        sb.append(this.longOpts);
        sb.append(" ]");
        return sb.toString();
    }
}
