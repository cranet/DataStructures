import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Main class calls CodingTree to execute Huffman algorithm
 * Displays read and compression times, and compression percent
 *
 * @author Todd Crane (cranet@uw.edu)
 * @author Caleb Smith (caleb447@uw.edu)
 * @version 2/24/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //Fields
        long sTime;
        long eTime;
        long tTime;

        //Create files
        File inputText = new File("TheCountOfMonteCristo.txt"); //File to be compressed

        File codeFile = new File("codes.txt"); //File to store Huffman codes
        codeFile.createNewFile();

        File compressedFile = new File("compressed.txt"); //Final compressed file
        compressedFile.createNewFile();

        //Read contents of inputText file into a string
        StringBuilder sb = new StringBuilder();
        int ch;

        sTime = System.currentTimeMillis();
        try {
            //Fields
            FileReader fr = new FileReader(inputText);
            BufferedReader br = new BufferedReader(fr);

            while((ch = br.read()) != -1) {
                char character = (char) ch;
                sb.append(character);
            }
            br.close();
        } finally{}

        eTime = System.currentTimeMillis();
        tTime = eTime - sTime;
        System.out.println("Read time: " + tTime + " ms");


        //Pass string to CodingTree (generate map of codes)
        sTime = System.currentTimeMillis();

        //Test string
        //String testa = "a";

        CodingTree a = new CodingTree(sb.toString());

        eTime = System.currentTimeMillis();
        tTime = eTime - sTime;
        System.out.println("Compress time: " + tTime + " ms");

        //Output codes to a text file

        //Write Huffman codes
        sTime = System.currentTimeMillis();

        FileWriter writer = new FileWriter(codeFile);
        writer.write(a.codes.toString());

        writer.flush();
        writer.close();

        //Write compressed file
        FileOutputStream writer2 = new FileOutputStream(compressedFile);
        byte[] toPrint = new byte[a.bits.size()];
        for(int i = 0; i < a.bits.size(); i++){
            toPrint[i] = a.bits.get(i);
        }
        writer2.write(toPrint);

        writer2.flush();
        writer2.close();

        eTime = System.currentTimeMillis();
        tTime = eTime - sTime;
        System.out.println("File write time: " + tTime + " ms");

        //Display the size of the compressed text, compression, and run time statistics
        System.out.println("Original file size: " + inputText.length() + " bytes");
        System.out.println("Compressed file size: " + compressedFile.length() + " bytes");
        long compress = (long)(compressedFile.length() * 1.0 / inputText.length() * 100);
        System.out.println("Compression: " + compress + "%");
        //System.out.println(codeFile.length());

        //Testing
        //check();

    }

    /**
     * Test method to compare the compression of two files
     * @throws IOException
     */
    private static void check() throws IOException {

        File myCodes = new File("codes.txt"); //Final compressed file
        myCodes.createNewFile();

        File givenCodes = new File("codes_given.txt");
        givenCodes.createNewFile();

        //Read contents of inputText file into a string
        //My codes
        try {
            //Fields
            FileReader fr = new FileReader(myCodes);
            BufferedReader br = new BufferedReader(fr);
            String line;

            int myCount = 0;
            while((line = br.readLine()) != null) {
                for(int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) == '=') {
                        myCount++;
                    }
                }
            }
            System.out.println("my count: " + myCount);
            br.close();
        } finally{}

        //Given codes
        try {
            //Fields
            FileReader fr = new FileReader(givenCodes);
            BufferedReader br = new BufferedReader(fr);
            String line;

            int myCount = 0;
            while((line = br.readLine()) != null) {
                for(int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) == '=') {
                        myCount++;
                    }
                }
            }
            System.out.println("given count: " + myCount);
            br.close();
        } finally{}

    }
}
