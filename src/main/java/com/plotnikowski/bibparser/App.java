package com.plotnikowski.bibparser;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);

        System.out.println(BibParser.parse("test.txt"));

    }

}
