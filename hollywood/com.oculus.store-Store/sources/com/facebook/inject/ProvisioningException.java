package com.facebook.inject;

import com.facebook.inject.ProvisioningDebugStack;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class ProvisioningException extends RuntimeException {
    public ProvisioningException() {
        super(getMessage(null));
    }

    public ProvisioningException(String detailMessage) {
        super(getMessage(detailMessage));
    }

    public ProvisioningException(String detailMessage, Throwable throwable) {
        super(getMessage(detailMessage), throwable);
    }

    public ProvisioningException(Throwable throwable) {
        super(getMessage(null), throwable);
    }

    private static String getMessage(@Nullable String msg) {
        StringBuilder sb = new StringBuilder();
        if (msg != null) {
            sb.append(msg);
        } else {
            sb.append("Failure to provision.");
        }
        sb.append("\n");
        List<ProvisioningDebugStack.StackEntry> stack = ProvisioningDebugStack.getStackEntries();
        Collections.reverse(stack);
        for (ProvisioningDebugStack.StackEntry entry : stack) {
            if (entry.stackType == ProvisioningDebugStack.StackType.INSTANCE_GET) {
                sb.append(" while trying to get instance of ");
            } else if (entry.stackType == ProvisioningDebugStack.StackType.INJECT_COMPONENT) {
                sb.append(" while trying to inject component of ");
            } else {
                sb.append(" while trying to get provider of ");
            }
            sb.append(entry.key).append("\n");
        }
        sb.append("If this is an instrumentation/screenshot test then you likely need to pass the ").append("relevant DI module to the test rule, see https://fburl.com/wiki/24nviijj for ").append("more details.\n");
        return sb.toString();
    }
}
