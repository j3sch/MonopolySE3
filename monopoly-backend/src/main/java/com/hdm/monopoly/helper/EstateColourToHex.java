package com.hdm.monopoly.helper;

public class EstateColourToHex {
    public String getHexOfColour(String i){
        switch(i){
            case "Brown": return "#955436";
            case "LightBlue": return "#AAE0FA";
            case "Pink": return "#D93A96";
            case "Orange": return "#F7941D";
            case "Red": return "#ED1B24";
            case "Yellow": return "#FEF200";
            case "Green": return "#1FB25A";
            case "DarkBlue": return "#0072BB";
            default: return null;
        }
    }}
