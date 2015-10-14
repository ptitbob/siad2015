package fr.univ.tours.siad.jaxb.model.person;

public class Adherent extends Person {

    /**
     * Date d'adhésion (au format YYYYMMDD)
     */
    private String membershipDate;

    public Adherent() {
    }

    public Adherent(Long id, String name, String surname, String membershipDate) {
        super(id, name, surname);
        this.membershipDate = membershipDate;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    @Override
    public String toString() {
        return "Adherent{" +
                "membershipDate='" + membershipDate + '\'' +
                "} " + super.toString();
    }
}
