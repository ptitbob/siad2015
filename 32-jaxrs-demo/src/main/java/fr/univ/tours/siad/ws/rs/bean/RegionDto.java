package fr.univ.tours.siad.ws.rs.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RegionDto extends RegionSimpleDto {

    private String chefLieuId;

    private String upperName;

    private Long districtCount;

    public RegionDto() {
    }

    public RegionDto(String inseeId, String name, String chefLieuId, Long districtCount, String upperName) {
        super(inseeId, name);
        this.chefLieuId = chefLieuId;
        this.districtCount = districtCount;
        this.upperName = upperName;
    }

    public String getChefLieuId() {
        return chefLieuId;
    }

    public void setChefLieuId(String chefLieuId) {
        this.chefLieuId = chefLieuId;
    }

    public Long getDistrictCount() {
        return districtCount;
    }

    public void setDistrictCount(Long districtCount) {
        this.districtCount = districtCount;
    }

    public String getUpperName() {
        return upperName;
    }

    public void setUpperName(String upperName) {
        this.upperName = upperName;
    }
}
