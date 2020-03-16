import java.util.Objects;

public class Center {

    private String about;
    private CenterLocation location;
    private PersonOfContact poc;
    private String name;
    private String searchStatus;

    public Center(CenterLocation location, PersonOfContact poc, String name, String searchStatus) {
        this.location = location;
        this.poc = poc;
        this.name = name;
        this.searchStatus = searchStatus;
        about = "./NasaLabFacilitiesOntology#" + name.replaceAll(" ", "") + "Center";
    }

    public String toRDF() {

        String rdf = "    <!-- " + about +"-->\n" +
                "    <owl:NamedIndividual rdf:about=\"" + about + "\">\n" +
                "        <rdf:type rdf:resource=\"./NasaLabFacilitiesOntology#Center\"/>\n" +
                "        <nfl:hasCenterLocation rdf:resource=\"" + location.getAbout() + "\"/>\n" +
                "        <nfl:hasPOC rdf:resource=\"" + poc.getAbout() + "\"/>\n" +
                "        <nfl:name>" + name + "<nfl:name>\n" +
                "        <nfl:searchStatus>" + searchStatus + "<nfl:searchStatus>\n" +
                "    </owl:NamedIndividual>\n\n";

        return rdf;
    }

    public String getAbout() {
        return about;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Center center = (Center) o;
        return Objects.equals(location, center.location) &&
                Objects.equals(poc, center.poc) &&
                Objects.equals(name, center.name) &&
                Objects.equals(searchStatus, center.searchStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, poc, name, searchStatus);
    }
}
