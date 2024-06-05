package org.example;

public class CountWordsRefactorized {
    private final String str;

    public CountWordsRefactorized(String str){
        if (str.isEmpty() || stringDoesntEndsWithLetter(str)) {
            throw new IllegalArgumentException("Length is 0");
        }
        this.str = str + " ";
    }

    public int count() {
        int words = 0;
        // Comenzamos desde 1 nuestro analisis
        for (int i = 1; i < str.length(); i++) { // 1
            if (lastCharacterSatisfyTheCondition(str.charAt(i), str.charAt(i-1))) { // 2
                words++;
            }
        }
        return words;
    }

    private boolean stringDoesntEndsWithLetter(String str){
        return !isLetter(str.charAt(str.length()-1));
    }

    private boolean lastCharacterSatisfyTheCondition(Character currentChar, Character lastChar) {
        return !isLetter(currentChar) && (lastChar == 's' || lastChar == 'r');
    }

    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }
}
