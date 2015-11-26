package fr.univ.tours.siad.organisation.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Entity
@Table(name = "PERSONS")
@SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 2)
@NamedQueries({
        @NamedQuery(name = Person.FIND_ALL, query = "select p from Person p")
        , @NamedQuery(name = Person.FIND_BY_CITY, query = "select p from Person p where p.address.city.inseeId = :" + City.INSEEID)
})
public class Person {

    public static final String PERSON_ID = "PERSON_ID";
    public static final String FIND_ALL = "Person.FIND_ALL";
    public static final String FIND_BY_CITY = "Person.FIND_BY_CITY";

    @Id
    @GeneratedValue(generator = "person_sequence")
    @Column(name = PERSON_ID)
    private Long id;

    /**
     * Prénom
     */
    @Column(length = 100)
    private String firstname;

    /**
     * Nom de famille
     */
    @Column(length = 100)
    private String surname;

    /**
     * Date de naissance
     */
    @Temporal(TemporalType.DATE)
    private Date birth;

    /**
     * Adresse
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address", referencedColumnName = Address.ADDRESS_ID)
    private Address address;

    /**
     * Liste des adhésions
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Membership> membershipList;

    /**
     * Liste des activités organisé
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organizer")
    private List<Activity> activityList;

    /**
     * Constructeur
     */
    public Person() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Membership> getMembershipList() {
        return membershipList;
    }

    public void setMembershipList(List<Membership> membershipList) {
        this.membershipList = membershipList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getFirstname(), person.getFirstname()) &&
                Objects.equals(getSurname(), person.getSurname()) &&
                Objects.equals(getBirth(), person.getBirth()) &&
                Objects.equals(getAddress(), person.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(), getSurname(), getBirth(), getAddress());
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", id=" + id +
                ", birth=" + birth +
                '}';
    }
}
