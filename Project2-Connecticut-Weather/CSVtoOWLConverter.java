//package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVtoOWLConverter {

    private Scanner in;
    private BufferedWriter out;
    private int rowCount;

    //opening the files for reading and writing and skipping first line that just has title names
    CSVtoOWLConverter(String csvFile, String owlFile) throws IOException {
        in = new Scanner(new File(csvFile));
        out = new BufferedWriter(new FileWriter(owlFile));
        in.nextLine();
    }

    //write to owl file
    public void writeToOWL(String stuff) throws IOException {
        out.write(stuff);
    }

    //gets row from CSV file and converts it into a usable formatted string array
    public String[] getRow(){
        if (in.hasNextLine() == false)
            return null;

        //removes extra comma and any quotation marks
        String[] rowSplit = in.nextLine().split(",", 2);
        String[] rowStrArray = rowSplit[0].concat(",").concat(rowSplit[1].replaceFirst(",","")).replaceAll("\"", "").split(",");

        //intializes string list to empty string, then fills the string with availible paramaters
        List<String> rowStrList = new ArrayList(19);
        for(int i = 0; i < 19; i++){
            rowStrList.add("");
        }
        for(int i = 0; i < rowStrArray.length; i++){
            rowStrList.set(i,rowStrArray[i]);
        }
        String[] row = new String[19];
        for(int i = 0; i < 19; i++){
            row[i] = rowStrList.get(i);
        }
        return row;
    }

    //checks if CSV file has been read completely or not
    public boolean isComplete(){
        return !in.hasNextLine();
    }

    //takes a CSV row and converts it into OWL format
    public void csvRowToOWLChunk(String[] csvRow) throws IOException {
        out.write("<!-- http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Station_Instance_" + rowCount + "-->\n");
        out.write("<owl:NamedIndividual rdf:about=\"http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Station_Instance_" + rowCount + "\">\n\n");
        out.write("    <measures rdf:resource=\"http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Instance_" + rowCount + "\"/>\n");
        out.write("    <stationID rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">" + csvRow[0] + "</stationID>\n");
        out.write("    <name rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">" +csvRow[1] + "</name>\n");
        out.write("    <latitude rdf:datatype=\"http://www.w3.org/2001/XMLSchema#float\">" + csvRow[2] + "</latitude>\n");
        out.write("    <longitude rdf:datatype=\"http://www.w3.org/2001/XMLSchema#float\">" + csvRow[3] + "</longitude>\n");
        out.write("    <elevation rdf:datatype=\"http://www.w3.org/2001/XMLSchema#float\">" + csvRow[4] + "</elevation>\n");
        out.write("    <xsd:date rdf:datatype=\"http://www.w3.org/2001/XMLSchema#date\">" +convertDateFormat(csvRow[5]) + "</xsd:date>\n");
        out.write("</owl:NamedIndividual>\n\n");

        out.write("<!-- http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Instance_" + rowCount +" -->\n\n");
        out.write("<owl:NamedIndividual rdf:about=\"http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Instance_" + rowCount + "\">\n");
        out.write("    <AWND rdf:datatype=\"http://www.w3.org/2001/XMLSchema#float\">" + csvRow[6] + "</AWND>\n");
        out.write("    <prcp rdf:datatype=\"http://www.w3.org/2001/XMLSchema#float\">" + csvRow[9] + "</prcp>\n");
        out.write("    <snow rdf:datatype=\"http://www.w3.org/2001/XMLSchema#float\">" + csvRow[10] + "</snow>\n");
        out.write("    <TMAX rdf:datatype=\"http://www.w3.org/2001/XMLSchema#int\">" + csvRow[13] + "</TMAX>\n");
        out.write("    <TMIN rdf:datatype=\"http://www.w3.org/2001/XMLSchema#int\">" + csvRow[14] + "</TMIN>\n");
        out.write("    <WDF2 rdf:datatype=\"http://www.w3.org/2001/XMLSchema#int\">" + csvRow[15] + "</WDF2>\n");
        out.write("    <WDF5 rdf:datatype=\"http://www.w3.org/2001/XMLSchema#int\">" + csvRow[16] + "</WDF5>\n");
        out.write("    <WSF2 rdf:datatype=\"http://www.w3.org/2001/XMLSchema#float\">" + csvRow[17] + "</WSF2>\n");
        out.write("    <WSF5 rdf:datatype=\"http://www.w3.org/2001/XMLSchema#float\">" + csvRow[18] + "</WSF5>\n");
        out.write("</owl:NamedIndividual>\n\n\n\n");

        rowCount++;
    }

    //converts dates from 1/2/2012 to 2012-01-02
    private String convertDateFormat(String oldDate) {
        //convert to string builder
        StringBuilder date = new StringBuilder();
        date.append(oldDate);

        //add the prepending 0s for months and days that are only 1 digit
        if(date.charAt(1) == '/')
            date.insert(0,"0");
        if(date.charAt(date.indexOf("/")+2) == '/')
            date.insert(date.indexOf("/")+1,"0");

        //puts year first instead of last
        date.insert(0,date.substring(date.length()-4,date.length()).concat("/"));
        date.delete(date.length()-5,date.length());

        //swap out forward slashes for dashes and return
        return date.toString().replaceAll("/","-");
        //return date.toString();
        //Date.replaceAll("/","-");


    }

    //closes CSV and OWL files
    public void closeFiles() throws IOException {
        in.close();
        out.close();
    }
}
