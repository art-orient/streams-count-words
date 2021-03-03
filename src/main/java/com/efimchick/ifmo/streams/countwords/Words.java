package com.efimchick.ifmo.streams.countwords;

import java.util.*;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {
        Map<String, Integer> result = new TreeMap<>();
        result = lines.stream()
                .map(s -> s.toLowerCase(Locale.ROOT))
                .flatMap(s -> Arrays.asList(s.split("[\\s\\d\\.\\p{Punct}„“«»…]+")).stream())
                .filter(s -> s.length() > 3)
                .collect(Collectors.toMap(w -> w, w -> 1, Integer::sum, TreeMap::new));
        result = sortByValues(result);

        String output = result.entrySet().stream().filter(e -> e.getValue() > 9)
                .map((s) -> s.getKey() + " - " + s.getValue())
                .collect(Collectors.joining("\n"));
        return output;
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =  new Comparator<K>() {
            public int compare(K k1, K k2) {
                int compare = map.get(k2).compareTo(map.get(k1));
                return compare == 0 ? 1: compare;
            }
        };
        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
}
