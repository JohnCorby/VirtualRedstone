package com.johncorby.virtualredstone;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.*;

/**
 * Run regular java tests and such with this
 */
public class Tester extends Application {
    public static void print(Object msg) {
        if (msg instanceof Object[]) msg = Arrays.toString((Object[]) msg);
        System.out.println(msg);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Map<String, Integer> m = new HashMap<>();
        m.put("Hi", 1);
        m.put("Die", 2);
        print(m);

        Set<String> s = new HashSet<>();
        s.add("Hi");
        s.add("Die");
        print(s);

        List<String> l = new ArrayList<>();
        l.add("Hi");
        l.add("Die");
        print(l);

        String[] a = {"Hi", "Die"};
        print(a);
    }
}
