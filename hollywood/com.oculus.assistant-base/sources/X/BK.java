package X;

import java.util.HashSet;

public final class BK extends HashSet<String> {
    public BK() {
        add("https://prod.facebookvirtualassistant.com");
        add("https://staging.facebookvirtualassistant.com");
        add("https://internal.facebookvirtualassistant.com");
    }
}
