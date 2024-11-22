package org.example.events;

import java.util.Scanner;

public class Calculator {

    static String number = null;
    static int from = 0;
    static int to = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;

        System.out.println("Gib das Uhrsprungsystem an (2-16)");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            switch (i) {
                case 1 -> {
                    try {
                        from = Integer.parseInt(line);
                        if (from < 2 || from > 16) throw new IllegalArgumentException();
                    } catch (Exception e) {
                        System.out.println("'" + line + "' Ist kein valides Nummer System (2-16)");
                        System.exit(1);
                    }
                    System.out.println("Gib deine Nummer an (0-F)");
                }
                case 2 -> {
                    number = line.toUpperCase();
                    System.out.println("Gib das Zielsystem an (2-16)");
                }
                case 3 -> {
                    try {
                        to = Integer.parseInt(line);
                        if (to < 2 || to > 16) throw new IllegalArgumentException();
                    } catch (Exception e) {
                        System.out.println("'" + line + "' Ist kein valides Nummer System (2-16)");
                        System.exit(1);
                    }
                    for (int j = 0; j != 10; j++) {
                        System.out.println();
                    }
                    printResult(number, from, to);
                    System.out.println();
                    System.out.println();
                    System.out.println("Gib das Uhrsprungsystem an (2-16)");
                    i = 0;
                }
            }
            i++;
        }
    }

    public static void printResult(String num, int from, int to) {
        if (num == null || from == 0 || to == 0) {
            System.out.println("Kein valider input {nummer:'" + num + "';von:'" + from + "';zu:'" + to + "'}");
            System.exit(1);
        }

        try {
            String decimalValue = convertToDecimal(num, from);
            String result = convertFromDecimal(decimalValue, to);
            System.out.println("Deine Nummer " + num + toSubscriptChar(from) + " ist gleich " + result + toSubscriptChar(to));
        } catch (Exception e) {
            System.out.println("Error during conversion: " + e.getMessage());
        }
    }

    private static String convertToDecimal(String number, int fromBase) {
        int decimalValue = 0;

        for (int i = 0; i < number.length(); i++) {
            char digit = number.charAt(i);
            int digitValue;

            if (Character.isDigit(digit)) {
                digitValue = digit - '0';
            } else if (digit >= 'A' && digit <= 'F') {
                digitValue = 10 + (digit - 'A');
            } else {
                throw new IllegalArgumentException("Invalid character '" + digit + "' for base " + fromBase);
            }

            if (digitValue >= fromBase) {
                throw new IllegalArgumentException("Invalid digit '" + digit + "' for base " + fromBase);
            }

            decimalValue = decimalValue * fromBase + digitValue;
        }

        return Integer.toString(decimalValue);
    }

    private static String convertFromDecimal(String decimalValue, int toBase) {
        int value = Integer.parseInt(decimalValue);
        StringBuilder result = new StringBuilder();

        while (value > 0) {
            int remainder = value % toBase;
            if (remainder < 10) {
                result.insert(0, (char) ('0' + remainder));
            } else {
                result.insert(0, (char) ('A' + (remainder - 10)));
            }
            value /= toBase;
        }

        return !result.isEmpty() ? result.toString() : "0";
    }

    public static String toSubscriptChar(int digit) {
        return switch (digit) {
            case 0 -> "₀";
            case 1 -> "₁";
            case 2 -> "₂";
            case 3 -> "₃";
            case 4 -> "₄";
            case 5 -> "₅";
            case 6 -> "₆";
            case 7 -> "₇";
            case 8 -> "₈";
            case 9 -> "₉";
            case 10 -> "₁₀";
            case 11 -> "₁₁";
            case 12 -> "₁₂";
            case 13 -> "₁₃";
            case 14 -> "₁₄";
            case 15 -> "₁₅";
            case 16 -> "₁₆";
            default -> throw new IllegalArgumentException("Invalid digit: " + digit);
        };
    }
}
