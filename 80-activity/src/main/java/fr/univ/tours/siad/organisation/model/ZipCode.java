package fr.univ.tours.siad.organisation.model;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by francois on 12/09/15.
 */
@Entity
@SequenceGenerator(name = "zipcode_sequence", sequenceName = "zipcode_sequence", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = ZipCode.FIND_ALL, query = "select zc from ZipCode zc")
        , @NamedQuery(name = ZipCode.FIND_BY_ZIPCODE, query = "select zc from ZipCode zc where zc.zipCode = :" + ZipCode.ZIPCODE)
})
public class ZipCode {

    public static final String ZIPCODE_ID = "ID";
    public static final String FIND_ALL = "ZipCode.FIND_ALL";
    public static final String FIND_BY_ZIPCODE = "ZipCode.FIND_BY_ZIPCODE";
    public static final String ZIPCODE = "zipcode";

    /**
     * Identifiant (PK)
     */
    @Id @GeneratedValue(strategy = SEQUENCE, generator = "zipcode_sequence")
    @Column(name = ZIPCODE_ID)
    private Long id;

    /**
     * Code postal
     */
    @Column(length = 6)
    private String zipCode;

    /**
     * ville
     */
    @ManyToOne
    private City city;

    /**
     * constructeur
     */
    public ZipCode() {
    }

    public ZipCode(City city, String zipCode) {
        this.city = city;
        this.zipCode = zipCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZipCode)) return false;
        ZipCode zipCode1 = (ZipCode) o;
        return Objects.equals(zipCode, zipCode1.zipCode) &&
                Objects.equals(city, zipCode1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, city);
    }

    @Override
    public String toString() {
        return "ZipCode{" +
                "zipCode='" + zipCode + '\'' +
                ", id=" + id +
                '}';
    }
}
