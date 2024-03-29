package com.jareer;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Calendar {

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN


    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.printf(CYAN_BRIGHT + "Enter which calendar year you want to create =>  ");

            int year = scanner.nextInt();
            yearlyCalendar(year);
        }

    }

    private static void yearlyCalendar(int year) {
        for (int i = 1; i <= 12; i++) {
            monthlyCalendar(year, i);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void monthlyCalendar(int y, int m) {

        LocalDate localDate = LocalDate.of(y, m, 1);
        Month month = localDate.getMonth();
        int year = localDate.getYear();
        System.out.println(YELLOW_BRIGHT + month + ", " + year + RESET);

        for (DayOfWeek value : DayOfWeek.values()) {
            System.out.printf("%s%s%s\t", CYAN_BRIGHT, value.name().substring(0, 2), RESET);
        }

        System.out.println();
        int firstDay = localDate.getDayOfWeek().getValue() - 1;
        for (int i = 0; i < firstDay; i++) {
            System.out.print(" \t");
        }
        int days = month.length(localDate.isLeapYear());
        for (int i = 1; i <= days; i++) {
            System.out.printf(getMessage(localDate, i), i);
            if ((i + firstDay) % 7 == 0) System.out.println();
        }
        System.out.println();
    }

    private static String getMessage(LocalDate date, int i) {
        LocalDate currentDate = LocalDate.now();
        return (currentDate.getYear() == date.getYear() &&
                currentDate.getMonth().equals(date.getMonth()) &&
                currentDate.getDayOfMonth() == i ?
                RED_BRIGHT : CYAN_BRIGHT) + "%02d" + RESET + "\t";
    }
}

