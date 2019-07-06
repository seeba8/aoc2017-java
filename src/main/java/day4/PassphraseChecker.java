package day4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PassphraseChecker {
    public boolean isValid(String phrase) {
        String[] tokens = phrase.split("\\s");
        Set<String> uniqueTokens = new HashSet<>();
        for(String token : tokens) {
            if(!uniqueTokens.add(token)) return false; // not unique
        }
        return true;
    }

    public boolean checkNoAnagram(String phrase) {
        String[] tokens = phrase.split("\\s");
        Set<HashMap<String,Integer>> uniqueTokens = new HashSet<>();
        for(String token : tokens) {
            HashMap<String, Integer> word = new HashMap<>();
            for(String c : token.split("")) {
                word.merge(c, 1, Integer::sum);
            }
            if(!uniqueTokens.add(word)) return false; // not unique
        }
        return true;
    }
}
