package org.example;

import java.util.ArrayList;
import java.util.List;

public class AttackTree {
    private AttackTreeNode root;
    public AttackTree(AttackTreeNode root) {
        this.root = root;
    }
    public double calculateTotalRisk() {
        return calculateRiskRecursively(root);
    }
    private double calculateRiskRecursively(AttackTreeNode node) {
        double totalRisk = node.getRisk();
        //System.out.println(node.getChildren());
        for (AttackTreeNode child : node.getChildren()) {
            totalRisk += calculateRiskRecursively(child);

        }
        return totalRisk;
    }
    // Método para encontrar la ruta crítica
    public List<AttackTreeNode> findCriticalPath() {
        List<AttackTreeNode> path = new ArrayList<>();
        findCriticalPathRecursively(root, path);
        return path;
    }
    private double findCriticalPathRecursively(AttackTreeNode node, List<AttackTreeNode> path) {
        path.add(node);
        if (node.getChildren().isEmpty()) {
            return node.getRisk();
        }
        double maxRisk = 0;
        AttackTreeNode criticalChild = null;
        for (AttackTreeNode child : node.getChildren()) {
            double childRisk = findCriticalPathRecursively(child, new ArrayList<>());
            if (childRisk > maxRisk) {
                maxRisk = childRisk;
                criticalChild = child;
            }
        }
        if (criticalChild != null) {
            path.add(criticalChild);
        }
        return maxRisk + node.getRisk();
    }
}