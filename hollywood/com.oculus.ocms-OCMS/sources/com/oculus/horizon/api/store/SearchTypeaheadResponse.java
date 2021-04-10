package com.oculus.horizon.api.store;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class SearchTypeaheadResponse {
    @Nullable
    public Viewer viewer;

    public static class Result {
        @Nullable
        public String query_completion;
    }

    public static class Search {
        @Nullable
        public TypeaheadCompletions typeahead_completions;
    }

    public static class TypeaheadCompletions {
        @Nullable
        public List<Result> completions;
        @Nullable
        public String prefix;
    }

    public static class Viewer {
        @Nullable
        public Search search;
    }

    public List<String> getCompletions() {
        ArrayList arrayList = new ArrayList();
        Viewer viewer2 = this.viewer;
        if (!(viewer2 == null || viewer2.search == null || this.viewer.search.typeahead_completions == null || this.viewer.search.typeahead_completions.completions == null)) {
            for (Result result : this.viewer.search.typeahead_completions.completions) {
                arrayList.add(result.query_completion);
            }
        }
        return arrayList;
    }

    @Nullable
    public String getPrefix() {
        Viewer viewer2 = this.viewer;
        if (viewer2 == null || viewer2.search == null || this.viewer.search.typeahead_completions == null) {
            return null;
        }
        return this.viewer.search.typeahead_completions.prefix;
    }
}
