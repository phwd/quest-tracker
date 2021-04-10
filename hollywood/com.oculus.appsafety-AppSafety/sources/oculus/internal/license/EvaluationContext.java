package oculus.internal.license;

import com.oculus.os.PackageMetadata;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class EvaluationContext {
    public final Long asOfEpochSeconds;
    public final PackageMetadata packageMetadata;
    public final Set<String> securityPrincipals;
    public final Set<String> systemAttributes;

    public EvaluationContext(Set<String> securityPrincipals2, Set<String> systemAttributes2, PackageMetadata packageMetadata2, Long asOfEpochSeconds2) {
        this.securityPrincipals = Collections.unmodifiableSet(securityPrincipals2);
        this.systemAttributes = Collections.unmodifiableSet(systemAttributes2);
        this.packageMetadata = packageMetadata2;
        this.asOfEpochSeconds = asOfEpochSeconds2;
    }

    public Set<String> getSecurityPrincipals() {
        return this.securityPrincipals;
    }

    public Set<String> getSystemAttributes() {
        return this.systemAttributes;
    }

    public static class Builder {
        private Long asOfEpochSeconds = null;
        private PackageMetadata packageMetadata = null;
        private Set<String> securityPrincipals = new HashSet();
        private Set<String> systemAttributes = new HashSet();

        public Builder addSecurityPrincipal(String securityPrincipal) {
            this.securityPrincipals.add(securityPrincipal);
            return this;
        }

        public Builder addSecurityPrincipals(Collection<String> securityPrincipals2) {
            this.securityPrincipals.addAll(securityPrincipals2);
            return this;
        }

        public Builder addSystemAttributes(Collection<String> systemAttributes2) {
            this.systemAttributes.addAll(systemAttributes2);
            return this;
        }

        public Builder setPackageMetadata(PackageMetadata packageMetadata2) {
            this.packageMetadata = packageMetadata2;
            return this;
        }

        public Builder setAsOfEpochSeconds(Long asOfEpochSeconds2) {
            this.asOfEpochSeconds = asOfEpochSeconds2;
            return this;
        }

        public EvaluationContext build() {
            return new EvaluationContext(this.securityPrincipals, this.systemAttributes, this.packageMetadata, this.asOfEpochSeconds);
        }
    }
}
