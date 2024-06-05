package org.example;

public class CountWords {
    int words = 0;
    char last = ' ';
    public int count(String str) {
        for (int i = 0; i < str.length(); i++) { // 1
            if (!isLetter(str.charAt(i)) && (last == 's' || last == 'r')) { // 2
                words++;
            }
            last = str.charAt(i); // 3
        }
        if (last == 'r' || last == 's') {
            words++;
        }
        return words;
    }

    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }
}