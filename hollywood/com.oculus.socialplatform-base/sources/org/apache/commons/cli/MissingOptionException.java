package org.apache.commons.cli;

import java.util.Iterator;
import java.util.List;

public class MissingOptionException extends ParseException {
    public static final long serialVersionUID = 8161889051578563249L;
    public List missingOptions;

    public static String createMessage(List<?> list) {
        String str;
        StringBuilder sb = new StringBuilder("Missing required option");
        if (list.size() == 1) {
            str = "";
        } else {
            str = "s";
        }
        sb.append(str);
        sb.append(": ");
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public List getMissingOptions() {
        return this.missingOptions;
    }

    public MissingOptionException(String str) {
        super(str);
    }

    public MissingOptionException(List list) {
        super(createMessage(list));
        this.missingOptions = list;
    }
}
