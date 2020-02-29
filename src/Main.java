import java.io.*;
import java.util.Scanner;

public class Main {
    static String inFile;
    static String outFile;
    static BufferedReader extFile;
    static BufferedWriter newFile;
    static Scanner stdin = new Scanner(System.in);

    public static void main(String[] args) {
        openExtFile();
        createNewFile();
        String temp;

        do {
            try {
                temp = extFile.readLine();
                if(temp != null) {
                    newFile.write(temp);
                    newFile.newLine();
                }

            } catch (IOException e) {
                System.out.println("File could not be read");
                temp = null;
            }
        } while (temp != null);

        try {
            newFile.flush();
            extFile.close();
            newFile.close();
        }catch (IOException e) {
            System.out.println("couldn't flush and close files");
        }

        System.out.println("Copied " + inFile + " " + "to " + outFile );
    }

    private static void openExtFile() {
        String temp;
        System.out.print("Path to existing file: ");
        temp= stdin.nextLine();

        try{
            extFile = new BufferedReader(new FileReader(temp));
            inFile = temp;
        }
        catch (IOException e) {
            String tempin;
            do {
                System.out.print("File does not exist, do you want to continue? (y | n)");
                tempin = stdin.nextLine();
            } while (!(tempin.equals("y") || tempin.equals("n")));

            if( tempin.equals("y")) {
                openExtFile();
            }
            else {
                System.exit(1);
            }
        }
    }

    private static void createNewFile() {
        String temp;
        String tempYN;
        System.out.print("Path to new File: ");
        temp = stdin.nextLine();
        File nF = new File(temp);

        if(nF.exists()) {
            do {
                System.out.print("Selected File exists, do you wan to override it? (y | n) ");
                tempYN = stdin.nextLine();
            } while (!(tempYN.equals("y") || tempYN.equals("n")));

            if(temp.equals("n")) {
                createNewFile();
                return;
            }
        } else {
            try {
                nF.createNewFile();
            }catch (IOException e) {
                System.out.println("could not create file exiting...");
                System.exit(-1);
            }
        }
        try {
            newFile = new BufferedWriter(new FileWriter(temp));
            outFile = temp;
        } catch (IOException e) {
            System.out.println("could not open file for writing...");
            System.exit(-1);
        }
    }

}