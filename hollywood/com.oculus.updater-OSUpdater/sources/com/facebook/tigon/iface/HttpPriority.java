package com.facebook.tigon.iface;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class HttpPriority {
    public boolean incremental;
    public byte urgency;

    public HttpPriority() {
        this.urgency = 3;
        this.incremental = true;
        this.urgency = 3;
        this.incremental = true;
    }

    public int hashCode() {
        return new Byte(this.urgency).hashCode() + new Boolean(this.incremental).hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof HttpPriority)) {
            return false;
        }
        HttpPriority httpPriority = (HttpPriority) obj;
        if (this.urgency == httpPriority.urgency && this.incremental == httpPriority.incremental) {
            return true;
        }
        return false;
    }
}
