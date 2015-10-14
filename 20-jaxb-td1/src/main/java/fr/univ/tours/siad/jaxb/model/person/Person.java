package fr.univ.tours.siad.jaxb.model.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

public abstract class Person {

    /**
     * Identifiant
     */
    private Long id;

    /**
     * Prénom
     */
    private String name;

    /**
     * Nom de famille
     */
    private String surname;

    /**
     * Liste de numéro de téléphone (sous forme de liste de chaine)
     */
    private List<String> phoneNumberList;

    /**
     * Adresse de la personne
     */
    private Address address;

    // adresse

    public Person() {
    }

    public Person(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<String> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<String> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }
}
