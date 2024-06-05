package org.example;

public class WordSelector {
    private String wordToBet;

    public WordSelector(String wordToBet) {
        this.wordToBet = wordToBet;
    }

    public boolean tryIndicatorIfYouWin(String tryString) {
        System.out.println("Palabra ingresada: "+ tryString);
        if (this.wordToBet.equals(tryString)) {
            return true;
        }
        retroalimentacion(tryString);
        return false;
    }

    public void retroalimentacion(String tryString){
        int correctChars = 0;
        final char[] wordToBetCharArray = this.wordToBet.toCharArray();
        final char[] tryStringCharArray = tryString.toCharArray();
        for (int i = 0; i < wordToBetCharArray.length; i++) {
            for (int j = 0; j < tryStringCharArray.length; j++) {
                if (wordToBetCharArray[i] == tryStringCharArray[j]) {
                    correctChars++;
                    break;
                }
            }
        }
        System.out.println("La palabra tiene " + correctChars + " letras correctas en la posición correcta");
    }

    public String getWordToBet() {
        return this.wordToBet;
    }
}