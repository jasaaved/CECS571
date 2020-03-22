package com.company;

    import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class CSVParser {

        private Scanner inFile;

        public CSVParser(String path) throws FileNotFoundException {
            inFile = new Scanner(new File(path));
            // We skip the first line since it simply serves as reminders of the format of the file
            inFile.nextLine();
            //inFile.nextLine();
        }

        public boolean hasNextLine() {
            return inFile.hasNextLine();
        }

        public List<String> nextLine() {
            if (!inFile.hasNextLine())
                return null;

            List<String> line = new ArrayList<String>();

            char[] charLine = inFile.nextLine().toCharArray();

            String s = "";
            boolean skipCommas = false;

            for (int i = 0; i < charLine.length; i++) {
                if (charLine[i] == '"') {
                    skipCommas = !skipCommas;
                }
                else if ((charLine[i] == ',' && !skipCommas) || (i == charLine.length-1)) {
                    line.add(s);
                    s = "";
                }
                else
                    s += charLine[i];
            }

            return line;
        }

        public void close() {
            inFile.close();
        }
    //}
}
