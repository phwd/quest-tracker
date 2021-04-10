package oculus.internal.license.parsing;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.oculus.license.Category;
import com.oculus.license.FilterClass;
import com.oculus.license.FilterConfig;
import com.oculus.license.License;
import com.oculus.license.PackageFilter;
import com.oculus.license.Rule;
import com.oculus.license.Signer;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import oculus.internal.gson.CertificateAdapter;
import oculus.internal.gson.DateTimeWithTimeZoneOffsetAdapter;
import oculus.internal.license.store.LicenseStoreSchema;
import org.json.JSONObject;

public final class CodableLicense {
    @JsonAdapter(LicenseCategoryAdapter.class)
    public final Category category;
    public final String comment;
    @JsonAdapter(DateTimeWithTimeZoneOffsetAdapter.class)
    public final long expires;
    public final long id;
    @JsonAdapter(DateTimeWithTimeZoneOffsetAdapter.class)
    public final long issued;
    public final String issuer;
    @SerializedName("packages")
    public final List<CodablePackageFilter> packageFilters;
    public final String requester;
    public final List<CodableRule> rules;

    public CodableLicense(long id2, String comment2, long issued2, long expires2, String requester2, String issuer2, Category category2, List<CodablePackageFilter> packagesFilters, List<CodableRule> rules2) {
        this.id = id2;
        this.comment = comment2;
        this.issued = issued2;
        this.expires = expires2;
        this.requester = requester2;
        this.issuer = issuer2;
        this.category = category2;
        this.packageFilters = Collections.unmodifiableList(packagesFilters);
        this.rules = Collections.unmodifiableList(rules2);
    }

    public static final class CodableRule {
        public final List<String> capabilities;
        public final List<CodableFilterConfig> conditions;
        public final Rule.Disposition disposition;

        public CodableRule(Rule.Disposition disposition2, List<String> capabilities2, List<CodableFilterConfig> conditions2) {
            this.disposition = disposition2;
            this.capabilities = Collections.unmodifiableList(capabilities2);
            this.conditions = Collections.unmodifiableList(conditions2);
        }

        public static final class CodableFilterConfig {
            @SerializedName("config")
            public final JSONObject configuration;
            @SerializedName("class")
            @JsonAdapter(FilterClassAdapter.class)
            public final FilterClass filterClass;

            public CodableFilterConfig(FilterClass filterClass2, JSONObject configuration2) {
                this.filterClass = filterClass2;
                this.configuration = configuration2;
            }

            public FilterConfig toFilterConfig() {
                return new FilterConfig(this.filterClass, this.configuration);
            }
        }

        public Rule toRule() {
            List<FilterConfig> convertedConditions;
            List<CodableFilterConfig> list = this.conditions;
            if (list == null) {
                convertedConditions = Collections.emptyList();
            } else {
                convertedConditions = (List) list.stream().map($$Lambda$cVgejGGna1CYv36P6dwKRhT1n10.INSTANCE).collect(Collectors.toList());
            }
            List<String> caps = this.capabilities;
            if (caps == null) {
                caps = Collections.emptyList();
            }
            return new Rule(this.disposition, caps, convertedConditions);
        }
    }

    public static final class CodablePackageFilter {
        @SerializedName(LicenseStoreSchema.PackageFilterTableSchema.COLUMN_NAME_IDENTIFIER_PATTERN)
        public final String identifierPattern;
        public final List<CodableSigner> signers;

        public CodablePackageFilter(String identifierPattern2, List<CodableSigner> signers2) {
            this.identifierPattern = identifierPattern2;
            this.signers = Collections.unmodifiableList(signers2);
        }

        public static final class CodableSigner {
            @JsonAdapter(CertificateAdapter.class)
            public final Certificate certificate;
            public final List<CodableDigest> digests;

            public CodableSigner(Certificate certificate2, List<CodableDigest> digests2) {
                this.certificate = certificate2;
                this.digests = Collections.unmodifiableList(digests2);
            }

            public static final class CodableDigest {
                public final String algorithm;
                public final byte[] digest;

                public CodableDigest(String algorithm2, byte[] digest2) {
                    this.algorithm = algorithm2;
                    this.digest = digest2;
                }

                public Signer.Digest toDigest() {
                    return new Signer.Digest(this.algorithm, this.digest);
                }
            }

            public Signer toSigner() {
                List<Signer.Digest> convertedDigests;
                List<CodableDigest> list = this.digests;
                if (list == null) {
                    convertedDigests = Collections.emptyList();
                } else {
                    convertedDigests = (List) list.stream().map($$Lambda$WwOOfY0_2zxuf3oofKbEgpQOgo.INSTANCE).collect(Collectors.toList());
                }
                return new Signer(this.certificate, convertedDigests);
            }
        }

        public PackageFilter toPackageFilter() {
            List<Signer> convertedSigners;
            List<CodableSigner> list = this.signers;
            if (list == null) {
                convertedSigners = Collections.emptyList();
            } else {
                convertedSigners = (List) list.stream().map($$Lambda$lVnOn0Dtn0tV5phHXi4kq1kyL0.INSTANCE).collect(Collectors.toList());
            }
            return new PackageFilter(this.identifierPattern, convertedSigners);
        }
    }

    public License toLicense() {
        List list;
        List list2;
        List<CodablePackageFilter> list3 = this.packageFilters;
        if (list3 == null) {
            list = Collections.emptyList();
        } else {
            list = (List) list3.stream().map($$Lambda$_fmM_PUI85Mw3m3N2a1UsTv_Oqc.INSTANCE).collect(Collectors.toList());
        }
        List<CodableRule> list4 = this.rules;
        if (list4 == null) {
            list2 = Collections.emptyList();
        } else {
            list2 = (List) list4.stream().map($$Lambda$t8usC4M9oZLGJO3I1iP3gexqLCM.INSTANCE).collect(Collectors.toList());
        }
        return new License(this.id, this.comment, this.issued, this.expires, this.requester, this.issuer, this.category, list, list2);
    }

    public static CodableLicense fromLicense(License license) {
        return new CodableLicense(license.id, license.comment, license.issued, license.expires, license.requester, license.issuer, license.category, (List) license.packageFilters.stream().map($$Lambda$CodableLicense$m3Pdv_8U3lUMkzcE6FZKsUvRAFQ.INSTANCE).collect(Collectors.toList()), (List) license.rules.stream().map($$Lambda$CodableLicense$swSwBEOQjJOUmdSfwvsQPRrFDpY.INSTANCE).collect(Collectors.toList()));
    }

    /* access modifiers changed from: private */
    public static CodablePackageFilter toCodablePackageFilter(PackageFilter packageFilter) {
        return new CodablePackageFilter(packageFilter.identifierPattern, (List) packageFilter.signers.stream().map($$Lambda$CodableLicense$G1eTzqLUUreFpdWgVzbCemxSIm8.INSTANCE).collect(Collectors.toList()));
    }

    /* access modifiers changed from: private */
    public static CodablePackageFilter.CodableSigner toCodableSigner(Signer signer) {
        return new CodablePackageFilter.CodableSigner(signer.certificate, (List) signer.digests.stream().map($$Lambda$CodableLicense$A7dWSEwShJrnk2wueXnJFxEpzsc.INSTANCE).collect(Collectors.toList()));
    }

    /* access modifiers changed from: private */
    public static CodablePackageFilter.CodableSigner.CodableDigest toCodableDigest(Signer.Digest digest) {
        return new CodablePackageFilter.CodableSigner.CodableDigest(digest.algorithm, digest.digest);
    }

    /* access modifiers changed from: private */
    public static CodableRule toCodableRule(Rule rule) {
        return new CodableRule(rule.disposition, rule.capabilities, (List) rule.conditions.stream().map($$Lambda$CodableLicense$cDfOjnQmJbVx3ULB8vY6vxN6TtY.INSTANCE).collect(Collectors.toList()));
    }

    /* access modifiers changed from: private */
    public static CodableRule.CodableFilterConfig toCodableFilterConfig(FilterConfig config) {
        return new CodableRule.CodableFilterConfig(config.filterClass, config.configuration);
    }
}
