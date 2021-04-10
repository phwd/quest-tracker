package X;

import com.facebook.common.util.TriState;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface Ec {
    @JsonProperty("required_connection")
    TriState getRequiredNewConnection();
}
