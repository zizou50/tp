/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fares
 */
import java.io.*;
class Voiture implements Serializable {
private int carburant;
private String model;
private String type;
private static int capacite = 300;
Voiture(String _type, String _model) {
type = _type;
model = _model;
carburant = 0;
}
public void setCarburant(int c) {
int maxi = capacite - carburant;
if (c < maxi) {
carburant += c;
System.out.println("Le remplissage a été effectué sans problème.");
} else {
carburant = capacite;
System.out.println((c - maxi) + " litre(s) de carburant ont débordé.");
}
}
public int getCarburant() {
return carburant;
}
public int getCapacite() {
return capacite;
}

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

}