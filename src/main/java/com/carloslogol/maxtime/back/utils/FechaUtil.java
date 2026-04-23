package com.carloslogol.maxtime.back.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class FechaUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate hoy(){
        return LocalDate.now();
    }

    public static String hoyFormateado(){
        return LocalDate.now().format(FORMATTER);
    }

    public static DayOfWeek diaDeLaSemana() {
        return LocalDate.now().getDayOfWeek();
    }

    public static String nombreDia() {
        return LocalDate.now().getDayOfWeek()
                .getDisplayName(TextStyle.FULL, new Locale("es", "CO"));
    }

    public static boolean esLunes()     { return diaDeLaSemana() == DayOfWeek.MONDAY; }
    public static boolean esMartes()    { return diaDeLaSemana() == DayOfWeek.TUESDAY; }
    public static boolean esMiercoles() { return diaDeLaSemana() == DayOfWeek.WEDNESDAY; }
    public static boolean esJueves()    { return diaDeLaSemana() == DayOfWeek.THURSDAY; }
    public static boolean esViernes() {
        return diaDeLaSemana() == DayOfWeek.FRIDAY;
    }
    public static boolean esSabado()    { return diaDeLaSemana() == DayOfWeek.SATURDAY; }
    public static boolean esDomingo()   { return diaDeLaSemana() == DayOfWeek.SUNDAY; }

}
