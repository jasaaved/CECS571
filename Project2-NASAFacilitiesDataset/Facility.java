import java.util.Objects;

public class Facility {

    private Center location;
    private String agency;
    private String name;
    private String status;
    private String URL;
    private String yearOccupied;
    private String about;

    public Facility(Center location, String agency, String name) {
        this.location = location;
        this.agency = agency;
        this.name = name;
        status = null;
        URL = null;
        yearOccupied = null;
        about = "./NasaLabFacilitiesOntology#" + name.replaceAll(" ", "") + "Facility";
    }

    public String getAbout() {
        return about;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setYearOccupied(String yearOccupied) {
        this.yearOccupied = yearOccupied;
    }

    public String toRDF(){
        String rdf = "    <!-- " + about +"-->\n\n" +
                "    <owl:NamedIndividual rdf:about=\"" + about +"\">\n" +
                "        <rdf:type rdf:resource=\"./NasaLabFacilitiesOntology#Facility\"/>\n" +
                "        <nfl:locatedInCenter rdf:resource=\""+ location.getAbout() +"\"/>\n" +
                "        <nfl:belongsToAgency>"+ agency +"</nfl:belongsToAgency>\n" +
                "        <nfl:name>"+ name +"</nfl:name>\n";

        if (status != null)
            rdf += "        <nfl:status>"+ status +"</nfl:status>\n";

        if (URL != null)
            rdf += "        <nfl:url>"+ URL +"</nfl:url>\n";

        if (yearOccupied != null)
            rdf += "        <nfl:yearOccupied rdf:datatype=\"http://www.w3.org/2001/XMLSchema#integer\">"+ yearOccupied +"</nfl:yearOccupied>\n";


        rdf += "    </owl:NamedIndividual>\n\n";
        return rdf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facility facility = (Facility) o;
        return Objects.equals(location, facility.location) &&
                Objects.equals(agency, facility.agency) &&
                Objects.equals(name, facility.name) &&
                Objects.equals(status, facility.status) &&
                Objects.equals(URL, facility.URL) &&
                Objects.equals(yearOccupied, facility.yearOccupied);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, agency, name, status, URL, yearOccupied);
    }
}

