package oculus.internal.license;

import com.oculus.license.Category;
import com.oculus.license.License;
import com.oculus.license.PackageFilter;
import com.oculus.license.Rule;
import java.util.List;

public class LicenseBuilder {
    Category category;
    String comment;
    long expires;
    long id;
    long issued;
    String issuer;
    List<PackageFilter> packageFilters;
    String requester;
    List<Rule> rules;

    public LicenseBuilder setId(long id2) {
        this.id = id2;
        return this;
    }

    public LicenseBuilder setComment(String comment2) {
        this.comment = comment2;
        return this;
    }

    public LicenseBuilder setIssuer(String issuer2) {
        this.issuer = issuer2;
        return this;
    }

    public LicenseBuilder setIssued(long issued2) {
        this.issued = issued2;
        return this;
    }

    public LicenseBuilder setExpires(long expires2) {
        this.expires = expires2;
        return this;
    }

    public LicenseBuilder setRequester(String requester2) {
        this.requester = requester2;
        return this;
    }

    public LicenseBuilder setCategory(Category category2) {
        this.category = category2;
        return this;
    }

    public LicenseBuilder setPackageFilters(List<PackageFilter> packageFilters2) {
        this.packageFilters = packageFilters2;
        return this;
    }

    public LicenseBuilder setRules(List<Rule> rules2) {
        this.rules = rules2;
        return this;
    }

    public License build() {
        return new License(this.id, this.comment, this.issued, this.expires, this.requester, this.issuer, this.category, this.packageFilters, this.rules);
    }

    public License buildAndValidate() {
        License license = build();
        Validator.validate(license);
        return license;
    }
}
