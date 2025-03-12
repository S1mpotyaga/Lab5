package org.example.collectionClasses.interfaces;

import org.example.collectionClasses.Coordinates;

import java.util.Scanner;

public interface CoordinatesReadable {

    static Coordinates readCoordinates(){
        Coordinates result = new Coordinates();
        result.setX(readX());
        result.setY(readY());
        setIncorrectFields(result);
        return result;
    }

    public static void setIncorrectFields(Coordinates coordinates){
        if (coordinates.getX() < -152){
            coordinates.setX(readX());
        }
        if (coordinates.getY() == null){
            coordinates.setY(readY());
        }
    }

    private static Float readX(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter coordinate x: ");
        Float x = in.nextFloat();
        while (x <= -152){
            System.out.println("Incorrect coordinate x. Enter coordinate again: ");
            x = in.nextFloat();
        }
        in.close();
        return x;
    }

    private static Double readY(){
        Scanner in = new Scanner(System.in);
        Double y = in.nextDouble();
        in.close();
        return y;
    }
}