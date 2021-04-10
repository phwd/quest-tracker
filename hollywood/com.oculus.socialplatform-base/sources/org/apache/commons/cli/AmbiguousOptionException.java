package org.apache.commons.cli;

import java.util.Collection;
import java.util.Iterator;

public class AmbiguousOptionException extends UnrecognizedOptionException {
    public static final long serialVersionUID = 5829816121277947229L;
    public final Collection<String> matchingOptions;

    public static String createMessage(String str, Collection<String> collection) {
        StringBuilder sb = new StringBuilder("Ambiguous option: '");
        sb.append(str);
        sb.append("'  (could be: ");
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            sb.append("'");
            sb.append(it.next());
            sb.append("'");
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public Collection<String> getMatchingOptions() {
        return this.matchingOptions;
    }

    public AmbiguousOptionException(String str, Collection<String> collection) {
        super(createMessage(str, collection), str);
        this.matchingOptions = collection;
    }
}
