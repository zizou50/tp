package Client;

import java.rmi.Naming;
import java.util.Scanner;

import Server.IArithOp;

public class CLient {
    public static void main (String args[]){
        Scanner scanner = new Scanner (System.in);
        try{
            IArithOp mediator = (IArithOp) Naming.lookup("rmi://localhost:1099/AithmeticOperations");
            while (true) {
                System.out.println("Donner l'opperateur");
                char operateur =scanner.nextLine().charAt(0);
                if(operateur !='+' && operateur  !='*' && operateur !='/' && operateur !='-'){
                    System.out.println("program terminated");
                    return;
                }
                System.out.println("Donner l'opperande 1");
                double operande1 =Double.parseDouble(scanner.nextLine());
                System.out.println("Donner l'opperande 2");
                double operande2 =Double.parseDouble(scanner.nextLine());
                switch(operateur){
                    case '+' ->System.out.println("Resultat   :  "+ mediator.addition(operande1, operande2));
                    case '*' ->System.out.println("Resultat   :  "+ mediator.multiplication(operande1, operande2));
                    case '/' ->System.out.println("Resultat   :  "+ mediator.division(operande1, operande2));
                    case '-' ->System.out.println("Resultat   :  "+ mediator.soustraction(operande1, operande2));
                    
                }
            }
            
        }
        catch (Exception e) {
            System.out.println ("Erreur d'accés à l'objet distant.");
            System.out.println (e.toString());
        }
        finally{
            scanner.close();
        }
    }
    
}
