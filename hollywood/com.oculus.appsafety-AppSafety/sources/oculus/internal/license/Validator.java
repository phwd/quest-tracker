package oculus.internal.license;

import com.oculus.license.FilterConfig;
import com.oculus.license.License;
import com.oculus.license.PackageFilter;
import com.oculus.license.Rule;
import com.oculus.license.Signer;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern WILDCARD = Pattern.compile("^\\*$");

    private Validator() {
    }

    public static void validate(License license) throws IllegalArgumentException {
        if (license == null || license.id == 0 || license.issuer == null || license.issued == 0 || license.requester == null || license.packageFilters == null || license.packageFilters.size() == 0 || license.category == null || license.rules == null || license.rules.size() == 0) {
            throw new IllegalArgumentException("Missing required fields");
        }
        for (PackageFilter packageFilter : license.packageFilters) {
            validate(packageFilter);
        }
        for (Rule rule : license.rules) {
            validate(rule);
        }
    }

    private static void validate(PackageFilter packageFilter) {
        boolean signersOptional = false;
        if (packageFilter.identifierPattern != null) {
            signersOptional = WILDCARD.matcher(packageFilter.identifierPattern).find();
        }
        if (!signersOptional && (packageFilter.signers == null || packageFilter.signers.size() == 0)) {
            throw new IllegalArgumentException("No signers were specified");
        } else if (packageFilter.signers != null) {
            for (Signer signer : packageFilter.signers) {
                validate(signer);
            }
        }
    }

    private static void validate(Signer signer) {
        if (signer.certificate == null) {
            throw new IllegalArgumentException("No certificate specified for signer");
        } else if (signer.digests != null) {
            for (Signer.Digest digest : signer.digests) {
                validate(digest);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0046 A[Catch:{ NoSuchAlgorithmException -> 0x006a }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0047 A[Catch:{ NoSuchAlgorithmException -> 0x006a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void validate(com.oculus.license.Signer.Digest r8) {
        /*
        // Method dump skipped, instructions count: 125
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.license.Validator.validate(com.oculus.license.Signer$Digest):void");
    }

    private static void validate(Rule rule) {
        if (rule.disposition == null || rule.capabilities == null || rule.capabilities.size() == 0 || rule.conditions == null || rule.conditions.size() == 0) {
            throw new IllegalArgumentException("Rule missing required fields");
        }
        for (FilterConfig condition : rule.conditions) {
            validate(condition);
        }
    }

    private static void validate(FilterConfig condition) {
        if (condition.filterClass == null || condition.configuration == null) {
            throw new IllegalArgumentException("Condition filter missing required fields");
        }
    }
}
