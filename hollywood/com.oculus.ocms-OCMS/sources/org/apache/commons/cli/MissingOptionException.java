package org.apache.commons.cli;

import java.util.Iterator;
import java.util.List;

public class MissingOptionException extends ParseException {
    private static final long serialVersionUID = 8161889051578563249L;
    private List missingOptions;

    public MissingOptionException(String str) {
        super(str);
    }

    public MissingOptionException(List list) {
        this(createMessage(list));
        this.missingOptions = list;
    }

    public List getMissingOptions() {
        return this.missingOptions;
    }

    private static String createMessage(List<?> list) {
        StringBuilder sb = new StringBuilder("Missing required option");
        sb.append(list.size() == 1 ? "" : "s");
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
}
