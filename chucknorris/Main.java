package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Cipher c = new Cipher();
        while(true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String operation = scan.nextLine();
            if (operation.equalsIgnoreCase("encode")) {
                System.out.println("Input string:");
                String message = scan.nextLine();
                invokeEncoder(message, c);
            } else if (operation.equalsIgnoreCase("decode")) {
                System.out.println("Input encoded string:");
                String cipher = scan.nextLine();
                invokeDecoder(cipher, c);
            } else if (operation.equalsIgnoreCase("exit")){
                System.out.println("Bye!");
                break;
            } else {
                System.out.printf("There is no '%s' operation\n", operation);
            }
        }
    }

    private static void invokeDecoder(String cipher, Cipher c) {
        try {
            c.decryptCipher(cipher);
            System.out.printf("Decoded string:\n%s\n", c.getMessage());
        } catch (Exception e) {
            System.out.println("Encoded string is not valid");
        }
    }

    private static void invokeEncoder(String message, Cipher c) {
        c.encryptMessage(message);
        System.out.printf("Encoded string:\n%s\n", c.getCipher());

    }


}
