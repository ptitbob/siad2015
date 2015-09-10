package fr.univ.tours.siad.util.data.bean;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "region_sequence", sequenceName = "region_sequence", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = Region.FIND_ALL, query = "select r from Region r")
        , @NamedQuery(name = Region.COUNT, query = "select count(r) from Region r")
})
public class Region {
    public static final String FIND_ALL = "Region.FIND_ALL";
    public static final String COUNT = "Region.COUNT";

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_sequence")
    @Column(name = "region_id")
    private Long id;

    @Column(length = 2)
    private String inseeId;

    @Column(length = 5)
    private String chefLieuId;

    @Column(length = 100)
    private String upperName;

    @Column(length = 100)
    private String name;

    public Region() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChefLieuId() {
        return chefLieuId;
    }

    public void setChefLieuId(String dhefLieuId) {
        this.chefLieuId = dhefLieuId;
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

    public String getUpperName() {
        return upperName;
    }

    public void setUpperName(String upperName) {
        this.upperName = upperName;
    }
}
