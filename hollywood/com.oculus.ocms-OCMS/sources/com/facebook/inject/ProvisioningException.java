package com.facebook.inject;

import com.facebook.inject.ProvisioningDebugStack;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class ProvisioningException extends RuntimeException {
    public ProvisioningException() {
        super(getMessage(null));
    }

    public ProvisioningException(String str) {
        super(getMessage(str));
    }

    public ProvisioningException(String str, Throwable th) {
        super(getMessage(str), th);
    }

    public ProvisioningException(Throwable th) {
        super(getMessage(null), th);
    }

    private static String getMessage(@Nullable String str) {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
        } else {
            sb.append("Failure to provision.");
        }
        sb.append("\n");
        List<ProvisioningDebugStack.StackEntry> stackEntries = ProvisioningDebugStack.getStackEntries();
        Collections.reverse(stackEntries);
        for (ProvisioningDebugStack.StackEntry stackEntry : stackEntries) {
            if (stackEntry.stackType == ProvisioningDebugStack.StackType.INSTANCE_GET) {
                sb.append(" while trying to get instance of ");
            } else if (stackEntry.stackType == ProvisioningDebugStack.StackType.INJECT_COMPONENT) {
                sb.append(" while trying to inject component of ");
            } else {
                sb.append(" while trying to get provider of ");
            }
            sb.append(stackEntry.key);
            sb.append("\n");
        }
        sb.append("If this is an instrumentation/screenshot test then you likely need to pass the ");
        sb.append("relevant DI module to the test rule, see https://fburl.com/wiki/24nviijj for ");
        sb.append("more details.\n");
        return sb.toString();
    }
}
