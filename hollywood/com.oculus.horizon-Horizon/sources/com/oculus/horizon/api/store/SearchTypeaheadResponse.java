package com.oculus.horizon.api.store;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class SearchTypeaheadResponse {
    @Nullable
    public final Viewer viewer;

    public static class Result {
        @Nullable
        public final String query_completion;
    }

    public static class Search {
        @Nullable
        public final TypeaheadCompletions typeahead_completions;
    }

    public static class TypeaheadCompletions {
        @Nullable
        public final List<Result> completions;
        @Nullable
        public final String prefix;
    }

    public static class Viewer {
        @Nullable
        public final Search search;
    }

    public List<String> getCompletions() {
        Search search;
        TypeaheadCompletions typeaheadCompletions;
        List<Result> list;
        ArrayList arrayList = new ArrayList();
        Viewer viewer2 = this.viewer;
        if (!(viewer2 == null || (search = viewer2.search) == null || (typeaheadCompletions = search.typeahead_completions) == null || (list = typeaheadCompletions.completions) == null)) {
            for (Result result : list) {
                arrayList.add(result.query_completion);
            }
        }
        return arrayList;
    }

    @Nullable
    public String getPrefix() {
        Search search;
        TypeaheadCompletions typeaheadCompletions;
        Viewer viewer2 = this.viewer;
        if (viewer2 == null || (search = viewer2.search) == null || (typeaheadCompletions = search.typeahead_completions) == null) {
            return null;
        }
        return typeaheadCompletions.prefix;
    }
}
