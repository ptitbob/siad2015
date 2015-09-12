package fr.univ.tours.siad.util.data.bean;

import javax.persistence.*;

import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by francois on 12/09/15.
 */
@Entity
@SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = City.COUNT, query = "select count(c) from City c")
        , @NamedQuery(name = City.FIND_ALL, query = "select c from City c")
})
public class City {

    public static final String COUNT = "City.COUNT";
    public static final String FIND_ALL = "City.FIND_ALL";

    @Id @GeneratedValue(strategy = SEQUENCE, generator = "city_sequence")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Region region;

    @ManyToOne(fetch = LAZY)
    private District district;

    @Column(length = 5)
    private String inseeId;

    @Column(length = 100)
    private String name;

    @Column
    private Double elevation;

    @OneToMany(mappedBy = "city", fetch = LAZY)
    private Set<ZipCode> zipCodeSet;

    public City() {
    }

    public City(String[] cityAsStringArray, Region region, District district) {
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInseeId() {
        return inseeId;
    }

    public void setInseeId(String inseeId) {
        this.inseeId = inseeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Set<ZipCode> getZipCodeSet() {
        return zipCodeSet;
    }

    public void setZipCodeSet(Set<ZipCode> zipCodeSet) {
        this.zipCodeSet = zipCodeSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) &&
                Objects.equals(region, city.region) &&
                Objects.equals(district, city.district) &&
                Objects.equals(inseeId, city.inseeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, region, district, inseeId);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", inseeId='" + inseeId + '\'' +
                ", id=" + id +
                ", district=" + district +
                '}';
    }
}