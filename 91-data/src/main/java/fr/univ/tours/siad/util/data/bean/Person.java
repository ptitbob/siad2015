package fr.univ.tours.siad.util.data.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by francois on 24/09/15.
 */
@Entity
@SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = Person.FIND_ALL, query = "select p from Person p")
        , @NamedQuery(name = Person.FIND_BY_REFERENCE, query = "select p from Person p where p.reference = :" + Person.REFERENCE)
        , @NamedQuery(name = Person.FIND_BY_PARTIAL_SURNAME, query = "select p from Person p where p.surname like :" + Person.PARTIAL_SURNAME)
})
public class Person implements Serializable {

    private static final long serialVersionUID = -3545547914183480156L;
    public static final String FIND_ALL = "Person.FIND_ALL";
    public static final String FIND_BY_REFERENCE = "Person.FIND_BY_REFERENCE";
    public static final String REFERENCE = "reference";
    public static final String FIND_BY_PARTIAL_SURNAME = "Person.FIND_BY_PARTIAL_SURNAME";
    public static final String PARTIAL_SURNAME = "partialSurname";

    /**
     * Identifiant
     */
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
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
     * N° unique
     */
    @Column(length = 7)
    private String reference;

    /**
     * Constructeur
     */
    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getFirstname(), person.getFirstname()) &&
                Objects.equals(getSurname(), person.getSurname()) &&
                Objects.equals(getBirth(), person.getBirth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(), getSurname(), getBirth());
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", id=" + id +
                ", reference='" + reference + '\'' +
                '}';
    }
}
