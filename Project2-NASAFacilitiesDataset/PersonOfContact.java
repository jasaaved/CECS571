import java.util.Objects;

public class PersonOfContact {

    private String name;
    private String mailStop;
    private String phoneNumber;
    private String about;

    public PersonOfContact(String name, String mailStop, String phoneNumber) {
        this.name = name;
        this.mailStop = mailStop;
        this.phoneNumber = phoneNumber;
        about = "./NasaLabFacilitiesOntology#" + name.replaceAll(" ", "") + "PersonOfContact";
    }


    public String toRDF() {
        String rdf = "    <!-- " + about +"-->\n" +
                "    <owl:NamedIndividual rdf:about=\"" + about + "\">\n" +
                "        <rdf:type rdf:resource=\"./NasaLabFacilitiesOntology#PersonOfContact\"/>\n" +
                "        <nfl:hasMailStop>" + mailStop + "</nfl:hasMailStop>\n" +
                "        <nfl:hasName>" + name + "</nfl:hasName>\n" +
                "        <nfl:hasPhoneNumber>" + phoneNumber + "</nfl:hasPhoneNumber>\n" +
                "    </owl:NamedIndividual>\n\n";

        return rdf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonOfContact that = (PersonOfContact) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(mailStop, that.mailStop) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mailStop, phoneNumber);
    }

    public String getName() {
        return name;
    }

    public String getMailStop() {
        return mailStop;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAbout(){
        return about;
    }
}
