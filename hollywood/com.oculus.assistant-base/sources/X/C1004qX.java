package X;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: X.qX  reason: case insensitive filesystem */
public final class C1004qX extends MT {
    @JsonProperty("mimeType")
    public String mMimeType;

    public final boolean equals(Object obj) {
        if (obj instanceof C1004qX) {
            return ((C1004qX) obj).mMimeType.equals(this.mMimeType);
        }
        return false;
    }
}
