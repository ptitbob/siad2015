package fr.univ.tours.siad.util.data.bean;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Département
 * Created by francois on 12/09/15.
 */
@Entity
@SequenceGenerator(name = "district_sequence", sequenceName = "district_sequence", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = District.FIND_ALL, query = "select d from District d")
        , @NamedQuery(name = District.COUNT, query = "select count(d) from District d")
})
public class District {

    public static final String FIND_ALL = "District.FIND_ALL";
    public static final String COUNT = "District.COUNT";

    @Id @GeneratedValue(strategy = SEQUENCE, generator = "district_sequence")
    public Long id;

    @ManyToOne(fetch = LAZY)
    private Region region;

    @Column(length = 5)
    private String inseeId;

    @Column(length = 5)
    private String chefLieuId;

    @Column(length = 100)
    private String upperName;

    @Column(length = 100)
    private String name;

    public District() {
    }

    public District(String... districtAsString) {

    }

    public String getChefLieuId() {
        return chefLieuId;
    }

    public void setChefLieuId(String chefLieuId) {
        this.chefLieuId = chefLieuId;
    }

    public static String getFindAll() {
        return FIND_ALL;
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

    public String getUpperName() {
        return upperName;
    }

    public void setUpperName(String upperName) {
        this.upperName = upperName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof District)) return false;
        District district = (District) o;
        return Objects.equals(id, district.id) &&
                Objects.equals(region, district.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, region);
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", inseeId='" + inseeId + '\'' +
                ", name='" + name + '\'' +
                ", region=" + region +
                '}';
    }
}
