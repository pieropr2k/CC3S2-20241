package org.example;
import java.util.Scanner;

public class Game {
    private int maximumOfTrys;
    private int numberOfTrys;
    private String wordToBet;
    private boolean youWin = false;
    private HintGenerator hintGenerator;
    WordSelector wordSelector;

    public Game(int maximumOfTrys, WordSelector wordSelector, HintGenerator hintGenerator) {
        this.maximumOfTrys = maximumOfTrys;
        this.wordToBet = wordSelector.getWordToBet();
                //wordToBet;
        this.hintGenerator = hintGenerator;
        this.wordSelector = wordSelector;
    }

    /*
    public void playWithCPU(){
        for (int i = 0; i < maximumOfTrys; i++) {
            Scanner sc = new Scanner(System.in);
            String tryString = sc.toString();
            this.tryEvent(tryString);
            this.hintGenerator.getHint(i%(hintGenerator.hints.size()));
        }
    }
    */

    public void tryEvent(String tryString) {
        numberOfTrys++;
        if (wordSelector.tryIndicatorIfYouWin(tryString)) {
            this.youWin = true;
            printWinning();
        } else {
            System.out.println("Te quedan "+ (maximumOfTrys-numberOfTrys) + " intentos");
        }
    }

    public boolean getYouWin () {
        return this.youWin;
    }

    public int getNumberOfTrys () {
        return this.numberOfTrys;
    }

    public void printWinning(){
        if (youWin) {
            System.out.println("You win bro, congrats");
        } else {
            System.out.println("You lose. Correct word is: " + this.wordToBet);
        }
    }
}
