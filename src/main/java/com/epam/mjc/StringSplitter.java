package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> delimitersList = new ArrayList<>(delimiters);
        List<String> result = new ArrayList<>();

        StringTokenizer stringTokenizer = new StringTokenizer(source, delimitersList.get(0));
        while (stringTokenizer.hasMoreTokens()) {
            result.add(stringTokenizer.nextToken());
        }

        for (int i = 1; i < delimiters.size(); i++) {
            List<String> currResult = new ArrayList<>();
            for (String str : result) {
                StringTokenizer currStringTokenizer = new StringTokenizer(str, delimitersList.get(i));
                while (currStringTokenizer.hasMoreTokens()) {
                    currResult.add(currStringTokenizer.nextToken());
                }
            }
            result = currResult;
        }
        return result;
    }
}
