package com.company;

class InvalidPeselException extends Exception {
    public InvalidPeselException(String errorMessage) {
        super(errorMessage);
    }
}

public class Pesel {
    private String pesel;
    private String birthDate;
    private String gender;

    boolean validatePesel(String pesel) {  //returns true if argument contains valid pesel number
        if (pesel.length() != 11) return false;
        int controlNumber = Character.getNumericValue(pesel.charAt(10));
        int controlSum = 9 * Character.getNumericValue(pesel.charAt(0)) +
                7 * Character.getNumericValue(pesel.charAt(1)) +
                3 * Character.getNumericValue(pesel.charAt(2)) +
                1 * Character.getNumericValue(pesel.charAt(3)) +
                9 * Character.getNumericValue(pesel.charAt(4)) +
                7 * Character.getNumericValue(pesel.charAt(5)) +
                3 * Character.getNumericValue(pesel.charAt(6)) +
                1 * Character.getNumericValue(pesel.charAt(7)) +
                9 * Character.getNumericValue(pesel.charAt(8)) +
                7 * Character.getNumericValue(pesel.charAt(9));
        return controlSum % 10 == controlNumber;
    }

    Pesel(String pesel) throws InvalidPeselException {
        if (validatePesel(pesel)) {
            this.pesel = pesel;
            this.birthDate = findBirthDate(pesel);
            this.gender = findGender(pesel);
        } else {
            throw new InvalidPeselException("Invalid PESEL number!");
        }
    }

    private String findBirthDate(String pesel) {
        Integer year, month, day;
        int monthDigits = Character.getNumericValue(pesel.charAt(2)) * 10 + Character.getNumericValue(pesel.charAt(3));
        month = monthDigits % 20;
        int century = monthDigits / 20;

        switch (century) {
            case 0:
                year = 1900 + Character.getNumericValue(pesel.charAt(0)) * 10 + Character.getNumericValue(pesel.charAt(1));
                break;
            case 1:
                year = 2000 + Character.getNumericValue(pesel.charAt(0)) * 10 + Character.getNumericValue(pesel.charAt(1));
                break;
            case 2:
                year = 2100 + Character.getNumericValue(pesel.charAt(0)) * 10 + Character.getNumericValue(pesel.charAt(1));
                break;
            case 3:
                year = 2200 + Character.getNumericValue(pesel.charAt(0)) * 10 + Character.getNumericValue(pesel.charAt(1));
                break;
            case 4:
                year = 1800 + Character.getNumericValue(pesel.charAt(0)) * 10 + Character.getNumericValue(pesel.charAt(1));
                break;
            default:
                year = 0;
                break;
        }

        day = (Character.getNumericValue(pesel.charAt(4)) * 10) + Character.getNumericValue(pesel.charAt(5));

        String dayFinal , monthFinal; // add 0 if value lower than 10

        if(day < 10) {
            dayFinal = "0" + day.toString();
        } else {
            dayFinal = day.toString();
        }

        if(month < 10) {
            monthFinal = "0" +  month.toString();
        } else {
            monthFinal = month.toString();
        }

        return (year + "-" + monthFinal + "-" + dayFinal);
    }

    private String findGender(String pesel) {
        int genderNumber = Character.getNumericValue(pesel.charAt(9));
        if (genderNumber % 2 == 1) return "Male";
        return "Female";
    }

    String getPesel() {
        return pesel;
    }

    String getBirthDate() {
        return birthDate;
    }

    String getGender() {
        return gender;
    }

    void printData() {
        System.out.println("Data collected from pesel number: " + getPesel());
        System.out.println("Birth date: " + getBirthDate());
        System.out.println("Gender: " + getGender());
    }
}
