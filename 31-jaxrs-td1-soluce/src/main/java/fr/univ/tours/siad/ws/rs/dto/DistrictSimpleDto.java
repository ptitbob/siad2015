package fr.univ.tours.siad.ws.rs.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@XmlRootElement
public class DistrictSimpleDto {

    private String inseeId;

    private String name;

    public DistrictSimpleDto() {
    }

    public DistrictSimpleDto(String inseeId, String name) {
        this.inseeId = inseeId;
        this.name = name;
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
}
