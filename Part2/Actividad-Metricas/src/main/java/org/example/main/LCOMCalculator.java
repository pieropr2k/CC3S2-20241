package org.example.main;
import java.util.*;

public class LCOMCalculator {
    private static class ClassInfo {
        List<String> methods = new ArrayList<>();
        Map<String, Set<String>> methodAttributes = new HashMap<>();
        Set<String> attributes = new HashSet<>();
        public void addMethod(String methodName, Set<String> attrs) {
            methods.add(methodName);
            methodAttributes.put(methodName, attrs);
            attributes.addAll(attrs);
        }
        public void deleteMethod(String methodName) {
            if (methodAttributes.containsKey(methodName)) {
                methods.remove(methodName);
                Set<String> attrsToRemove = methodAttributes.remove(methodName);
                for (String attr : attrsToRemove) {
                    //boolean isAttributeUsed = false;
                    for (Set<String> attrs : methodAttributes.values()) {
                        if (attrs.contains(attr)) {
                            attributes.remove(attr);
                            break;
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        ClassInfo classInfo = new ClassInfo();
        // Simulación de entrada de métodos y sus accesos a atributos
        classInfo.addMethod("method1", new HashSet<>(Arrays.asList("attr1", "attr2")));
        classInfo.addMethod("method2", new HashSet<>(Arrays.asList("attr2")));
        classInfo.addMethod("method3", new HashSet<>(Arrays.asList("attr3")));
        // Metodos extra
        classInfo.addMethod("method4", new HashSet<>(Arrays.asList("attr3", "attr4")));
        classInfo.addMethod("method5", new HashSet<>(Arrays.asList("attr5")));
        classInfo.addMethod("method6", new HashSet<>(Arrays.asList("attr3", "attr6")));
        classInfo.addMethod("method7", new HashSet<>(Arrays.asList("attr1", "attr7")));
        classInfo.addMethod("method8", new HashSet<>(Arrays.asList("attr8")));

        // Total = 28 = 7*8/2

        // Pares de metodos que no comparten atributos (P) = 23 = Total - Q
        // Pares de metodos que comparten atributos (Q) = 5
        // (method1, method2), (method1, method7), (method3, method4), (method3, method6), (method3, method6)

        int p = 0, q = 0;
        List<String> methods = classInfo.methods;
        for (int i = 0; i < methods.size(); i++) {
            for (int j = i + 1; j < methods.size(); j++) {
                String method1 = methods.get(i);
                String method2 = methods.get(j);
                Set<String> attrs1 = classInfo.methodAttributes.get(method1);
                Set<String> attrs2 = classInfo.methodAttributes.get(method2);
                // Calculamos si comparten atributos
                Set<String> intersection = new HashSet<>(attrs1);
                intersection.retainAll(attrs2);
                if (intersection.isEmpty()) {
                    p++; // No comparten atributos
                } else {
                    System.out.println("Par: (" + method1.toString() + ", " +  method2.toString() + ")");
                    System.out.println("Intercepcion = " + intersection.toString());
                    q++; // Comparten al menos un atributo
                }
            }
        }
        int lcom = p - q;
        System.out.println("LCOM = " + lcom);
    }
    /*
    Casos: intercepcion de 2+
    
     */
}