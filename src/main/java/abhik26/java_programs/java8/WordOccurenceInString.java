package abhik26.java_programs.java8;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordOccurenceInString {
    public static void main(String[] args) {
        String string = "welcome to the world of happiness";

        string.chars().mapToObj(e -> (char) e).filter(e -> e != ' ')
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.println(e.getKey() + "\t: " + e.getValue()));

        // Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
        // for (int i = 0; i < string.length(); i++) {
        // String word = string.substring(i, i + 1);
        // Integer wordCount = wordCountMap.get(word);
        // if (wordCount == null) {
        // wordCountMap.put(word, 1);
        // } else {
        // wordCountMap.put(word, ++wordCount);
        // }
        // }

        // wordCountMap.entrySet().stream().forEach(e -> {
        // System.out.println(e.getKey() + ": " + e.getValue());
        // });

    }
}
