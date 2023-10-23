package com.x.application;

public record CoOrdinate(String s) {
    public CoOrdinate {
        if (s == null || s.isEmpty() || !isValidString(s)) {
            throw new IllegalArgumentException("Bad CoOrdinate");
        }
    }

    public static boolean isValidString(String s){
        String[] parts = s.trim().split("\\.");
        return parts.length == 2 && parts[1].length() <= 6;
    }

    @Override
    public String toString(){
        return s;
    }
}
