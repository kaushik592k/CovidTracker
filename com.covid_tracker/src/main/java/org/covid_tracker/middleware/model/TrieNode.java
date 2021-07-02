package org.covid_tracker.middleware.model;

import java.util.List;

public class TrieNode {

    public boolean isEnd;
    public TrieNode[] child;
    public TrieNode() {
        this.child = new TrieNode[26];
        for (int i = 0; i<26; i++) this.child[i] = null;
        this.isEnd = false;
    }
}
