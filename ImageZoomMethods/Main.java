import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {


        //Un POP-UP message pentru a face mai usoara interactiunea cu programul

        JOptionPane.showMessageDialog(null,"Bine ati venit!\n\n\n" +
                "Aveti la dispozitie 2 operatii pentru prelucrarea imaginii:\n" +
                "PixelReplication in care trebuie sa introduceti de cate ori sa fie marita Ex: x2,x3\n" +
                "ZeroOrderHolde care are implicit zoomul x2\n\n\n" +
                "Incepeti prin a introduce calea catre fisier\n" +
                "Si apoi urmariti pasii");

        //Codul de mai jos realizeaza citirea de la tastatura. In consola o sa fie introdusa calea catre poza pe care dorim sa o modificam.
        // Am ales sa folosesc InputStreamReader si BufferedImage deoarece sunt mai rapide decat utilitarul Scanner;
        try(
                InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                ){

                String path = bufferedReader.readLine();

                JOptionPane.showMessageDialog(null,"Calea aleasa este: " + path + "\n\n" +
                        "Acum alegeti operatia dorita!\n" +
                        "Pentru PixelReplication este nevoide de inca un parametru de tip int\n\n" +
                        "Exemplu:\"PixelReplication 2\"\n sau \"ZeroOrderedHold\"");

                //Aici or sa fie separate cele 2 metode implementate. Se citeste de la tastatura metoda dorita si se intra pe switch
                //In cazul in care utilizatorul alege metoda Pixel replication acesta trebuie sa dea resizeFactorul iar in cazul acesta,
                //operatia o sa fie compusa dintr-un string care trebuie splitat


                //De asemenea programul contorizeaza timpul pe care fiecare metoda il obtine in urma realizarii uneia dintre operatii;
                String[] operation = bufferedReader.readLine().split(" ");

                switch (operation[0]) {

                    case "PixelReplication" :
                                               int start = (int) System.currentTimeMillis();
                                               new PixelReplicationPicture(path,Integer.parseInt(operation[1])).performAction();
                                               int end = (int) System.currentTimeMillis();
                                               System.out.println("Pixel Replication execution time::: " + (end - start));
                                               break;

                    case "ZeroOrderedHold"  :
                                               int start2 = (int)System.currentTimeMillis();
                                               new ZeroOrderedHold(path).performAction();
                                               int end2 = (int)System.currentTimeMillis();
                                               System.out.println("ZeroOrderedHold execution time::: " + (end2 - start2) + " milisecunde");
                                               break;
                }

        }catch(IOException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}
