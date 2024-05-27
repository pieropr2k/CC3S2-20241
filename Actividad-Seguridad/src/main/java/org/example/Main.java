package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AttackTreeNode rootNode =  new AttackTreeNode("arbol", 90, 0.65);
        rootNode.addChild(new AttackTreeNode("padre", 45, 0.35));
        rootNode.addChild(new AttackTreeNode("hijo", 50, 0.35));
        AttackTree tree =  new AttackTree(rootNode);
        double lol = tree.calculateTotalRisk();
        System.out.println("Hello world!" + lol);

        List<AttackTreeNode> gaa = tree.findCriticalPath();
        //System.out.println("Hello world!" + gaa);
        for (AttackTreeNode child : gaa) {
            System.out.println("Hello world!" + child.getDescription());
            System.out.println(child.getDescription() + " Comps" + child.getRisk() + " " + child.getChildren());
        }

    }
}