package fr.univ.tours.siad.jaxb.model.club;

import fr.univ.tours.siad.jaxb.model.person.Address;
import fr.univ.tours.siad.jaxb.model.person.Person;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Association {

    /**
     * Identifiant
     */
    private Long id;

    /**
     * Nom de l'association
     */
    private String name;

    /**
     * Liste des personnes adhérant à l'association
     */
    private Set<Person> adherentList;

    /**
     * Nombre d'adhérent
     */
    private int adherentCount = Integer.MIN_VALUE;

    /**
     * Adresse de l'association
     */
    private Address address;

    public Association() {
        this.adherentList = new HashSet<>();
    }

    public Association(String name) {
        this();
        this.name = name;
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

    public Set<Person> getAdherentList() {
        return adherentList;
    }

    public void setAdherentList(Set<Person> adherentList) {
        this.adherentList = adherentList;
    }

    public void addAdherent(Person person) {
        getAdherentCount();
        adherentList.add(person);
        adherentCount++;
    }

    public int getAdherentCount() {
        if (adherentCount == Integer.MIN_VALUE) {
            adherentCount = adherentList.size();
        }
        return adherentCount;
     }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Association)) return false;
        Association that = (Association) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Association{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
