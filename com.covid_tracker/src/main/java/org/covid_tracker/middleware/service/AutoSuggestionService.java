package org.covid_tracker.middleware.service;

import org.covid_tracker.middleware.model.TrieNode;

import java.util.List;
import java.util.Set;

public interface AutoSuggestionService {

    public TrieNode build(List<String> countries);

    public Set<String> getTopK(String prefix);

}
