package org.example;

import java.util.List;

public class HintGenerator {
    List<String> hints;

    public HintGenerator(List<String> hints){
        this.hints = hints;
    }
    public String getHint(int index) {
        return hints.get(index);
    }
}
