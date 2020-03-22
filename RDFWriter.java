package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class RDFWriter {

    private BufferedWriter bw;
    //private HashSet<Center> centerSet;
    private int row;

    public RDFWriter(String path) throws IOException {
        bw = new BufferedWriter(new FileWriter(path));
        //centerSet = new HashSet<>();
    }

//    public void writeIndividual(List<String> line) throws IOException {
//        String centerName = line.get(2);
//        PersonOfContact poc = new PersonOfContact(line.get(15), line.get(16), line.get(17));
//        CenterLocation cl = new CenterLocation(line.get(10), line.get(11), line.get(14),
//                line.get(12), line.get(13), centerName);
//        Center center = new Center(cl, poc, centerName, line.get(3));
//        Facility facility = new Facility(center, line.get(1), line.get(4));
//
//        if (line.get(5) != "")
//            facility.setYearOccupied(line.get(5));
//
//        if (line.get(6) != "")
//            facility.setStatus(line.get(6));
//
//        if (line.get(7) != "")
//            facility.setURL((line.get(7)));
//
//        if (!centerSet.contains(center)) {
//            centerSet.add(center);
//            bw.write(poc.toRDF());
//            bw.write(cl.toRDF());
//            bw.write(center.toRDF());
//        }
//
//        bw.write(facility.toRDF());
//
//    }

    public void write(String text) throws IOException {
        bw.write(text);
    }

    public void csvRowToOWLChunk(List<String> line) throws IOException {

        bw.write("        <!-- http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Instance_" + row +" -->\n\n");
        bw.write("<owl:NamedIndividual rdf:about=\"http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Instance_" + row + "\">\n");
        bw.write("    <precipitation rdf:datatype=\"http://www.w3.org/2001/XMLSchema#float\">" + line.get(6) + "</precipitation>\n");
        bw.write("    <snow rdf:datatype=\"http://www.w3.org/2001/XMLSchema#float\">" + line.get(7) + "</snow>\n");
        bw.write("</owl:NamedIndividual>\n\n\n");
        bw.write("<!-- http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Station_Instance_" + row + "-->\n\n");
        bw.write("<owl:NamedIndividual rdf:about=\"http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Station_Instance_" + row + "\">\n\n");
        bw.write("    <measures rdf:resource=\"http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Instance_" + row + "\"/>\n");
        bw.write("    <date rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\"" + line.get(2) + "</date>\n");
        bw.write("    <name rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">" + line.get(1) + "</name>\n\n");
        bw.write("    <stationID rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">" + line.get(0) + "</stationID>\n");
        bw.write("</owl:NamedIndividual>\n");
        /*
            <!-- http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Instance_1 -->

    <owl:NamedIndividual rdf:about="http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Instance_1">
        <precipitation rdf:datatype="http://www.w3.org/2001/XMLSchema#float">0.11</precipitation>
        <snow rdf:datatype="http://www.w3.org/2001/XMLSchema#float">0.0</snow>
    </owl:NamedIndividual>



    <!-- http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Station_Instance_1 -->

    <owl:NamedIndividual rdf:about="http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Station_Instance_1">
        <measures rdf:resource="http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#weather_Instance_1"/>
        <date rdf:datatype="http://www.w3.org/2001/XMLSchema#string">2012-01-01</date>
        <name rdf:datatype="http://www.w3.org/2001/XMLSchema#string">HARTFORD BRADLEY INTERNATIONAL AIRPORT, CT US</name>
        <stationID rdf:datatype="http://www.w3.org/2001/XMLSchema#string">USW00014740</stationID>
    </owl:NamedIndividual>

         */
    row++;
    }

    public void close() throws IOException {
        bw.close();
    }
}