package com.company;
/*
W zadaniu zawarta walidacja peselu po sumie i numerze kontrolnym, oraz zapewnione działanie dla wszystkich możliwych
    pokoleń (lata 1800 - 2299).
 */
public class Main {
    public static void main(String[] args) {
        String pesel = "98060209693";
        try {
            Pesel myPesel = new Pesel(pesel);
            myPesel.printData();
        } catch (InvalidPeselException e) {
            e.printStackTrace();
        }
    }
}
