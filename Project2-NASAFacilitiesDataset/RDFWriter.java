import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class RDFWriter {

    private BufferedWriter bw;
    private HashSet<Center> centerSet;

    public RDFWriter(String path) throws IOException {
        bw = new BufferedWriter(new FileWriter(path));
        centerSet = new HashSet<>();
    }

    public void writeIndividual(List<String> line) throws IOException {
        String centerName = line.get(2);
        PersonOfContact poc = new PersonOfContact(line.get(15), line.get(16), line.get(17));
        CenterLocation cl = new CenterLocation(line.get(10), line.get(11), line.get(14),
                line.get(12), line.get(13), centerName);
        Center center = new Center(cl, poc, centerName, line.get(3));
        Facility facility = new Facility(center, line.get(1), line.get(4));

        if (line.get(5) != "")
            facility.setYearOccupied(line.get(5));

        if (line.get(6) != "")
            facility.setStatus(line.get(6));

        if (line.get(7) != "")
            facility.setURL((line.get(7)));

        if (!centerSet.contains(center)) {
            centerSet.add(center);
            bw.write(poc.toRDF());
            bw.write(cl.toRDF());
            bw.write(center.toRDF());
        }

        bw.write(facility.toRDF());

    }

    public void write(String text) throws IOException {
        bw.write(text);
    }

    public void close() throws IOException {
        bw.close();
    }
}
