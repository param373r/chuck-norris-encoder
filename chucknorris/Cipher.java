package chucknorris;

public class Cipher {
    private String message;
    private String cipher;

    public String getMessage() {
        return message;
    }
    public String getCipher() {
        return cipher;
    }
    public Cipher() {
        this.message = "";
        this.cipher = "";
    }
    private static String printSequence(int counter, boolean flag) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < counter; i++) {
            if (flag) {
                builder.append("1");
            } else {
                builder.append("0");
            }
        }
        return builder.toString();
    }
    private void convertFromBinaryString(String binaryString) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i+=7) {
            message.append((char) Integer.parseInt(binaryString.substring(i, i + 7), 2));
        }
        this.message = message.toString();
    }
    private String convertToBinaryString(String message) {
        StringBuilder bString = new StringBuilder();
        for(char i : message.toCharArray()) {
            bString.append(String.format("%7s", Integer.toBinaryString(i)).replace(" ", "0"));
        }
        return bString.toString();
    }

    public void decryptCipher(String cipher) throws Exception {
        if (!isCipherValid(cipher)) {
            throw new Exception();
        }
        String[] cipherArray = cipher.split(" ");
        StringBuilder bString = new StringBuilder();

        for (int i = 0; i < cipherArray.length; i+=2) {
            if (cipherArray[i].equals("0")) {
                bString.append(printSequence(cipherArray[i + 1].length(), true));
            } else {
                bString.append(printSequence(cipherArray[i + 1].length(), false));
            }
        }
        String binaryString = bString.toString();
        if(binaryString.length() % 7 != 0) {
            throw new Exception();
        }
        convertFromBinaryString(binaryString);
    }

    private boolean isCipherValid(String cipher) {
        String[] cipherArray = cipher.split(" ");
        for (int i = 0; i < cipher.length(); i++) {
            if (cipher.charAt(i) != '0' && cipher.charAt(i) != ' ') {
                return false;
            }
            if (i % 2 == 0 && i < cipherArray.length && (!cipherArray[i].equals("0") && !cipherArray[i].equals("00"))) {
                return false;
            }
        }

        return cipherArray.length % 2 != 1;
    }

    public void encryptMessage(String message) {
        String binaryString = convertToBinaryString(message);
        boolean flag;
        flag = binaryString.charAt(0) == '1';
        for (int i = 0; i < binaryString.length();) {
            int counter = 0;

            for (int j = i; j < binaryString.length(); j++) {
                if (flag) {
                    if (binaryString.charAt(j) != '1') {
                        flag = false;
                        break;
                    } else {
                        counter++;
                    }
                } else {
                    if (binaryString.charAt(j) != '0') {
                        flag = true;
                        break;
                    } else {
                        counter++;
                    }
                }
            }
            i += counter;
            if (i == binaryString.length()) {
                flag = !flag;
            }
            if (flag) {
                cipher += (String.format("00 %s ", printSequence(counter, false)));
            } else {
                cipher += (String.format("0 %s ", printSequence(counter, false)));
            }
            if (i == binaryString.length()) {
                break;
            }
        }
    }
}
