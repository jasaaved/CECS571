import java.util.Objects;


public class CenterLocation {

    private String address;
    private String city;
    private String country;
    private String state;
    private String zipCode;
    private String name;
    private String about;

    public CenterLocation(String address, String city, String country, String state, String zipCode, String name) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.state = state;
        this.zipCode = zipCode;
        this.name = name;
        about = "./NasaLabFacilitiesOntology#" + name.replaceAll(" ", "") + "CenterLocation";
    }

    public String toRDF() {
        String rdf = "    <!-- " + about +"-->\n" +
                "    <owl:NamedIndividual rdf:about=\"" + about + "\">\n" +
                "        <rdf:type rdf:resource=\"./NasaLabFacilitiesOntology#CenterLocation\"/>\n" +
                "        <nfl:address>" + address + "<nfl:address>\n" +
                "        <nfl:city>" + city + "<nfl:city>\n" +
                "        <nfl:country>" + country + "<nfl:country>\n" +
                "        <nfl:state>" + state + "<nfl:state>\n" +
                "        <nfl:zipCode>" + zipCode + "<nfl:zipCode>\n" +
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
        CenterLocation that = (CenterLocation) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(country, that.country) &&
                Objects.equals(state, that.state) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, country, state, zipCode, name);
    }
}