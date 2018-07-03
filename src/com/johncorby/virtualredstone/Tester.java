package com.johncorby.virtualredstone;

import com.johncorby.virtualredstone.util.Common;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Run regular java tests and such with this
 */
public class Tester extends Application {
    public static void print(Object... msgs) {
        System.out.println(String.join(" ", Common.toStr(msgs)));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
