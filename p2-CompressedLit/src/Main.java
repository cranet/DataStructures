import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by todc on 2/18/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        //Read contents of a text file into a string

        StringBuilder sb = new StringBuilder();
        String line;

        try {
            //Fields
            FileReader fr = new FileReader("test.txt");
            BufferedReader bs = new BufferedReader(fr);

            while((line = bs.readLine()) != null) {
                sb.append(line);
                sb.append((char) 10); //Account for lines
                //System.out.println(line);
            }
            bs.close();
        } finally{}
        //System.out.println(sb.toString());

        //Pass string to CodingTree (generate map of codes)
        CodingTree a = new CodingTree(sb.toString());


        //Output codes to a text file

        //System.out.println(a.codes);
        //System.out.println(a.bits);

        File f = new File("codes.txt");

        //Create file
        f.createNewFile();

        //Create file writer
        FileWriter writer = new FileWriter(f);

        //Writes content to file
        writer.write(a.codes.toString());
        writer.flush();
        writer.close();




        //Output the compressed message to a binary file


        //Display the size of the compressed text, compression, and run time statistics


    }
}
