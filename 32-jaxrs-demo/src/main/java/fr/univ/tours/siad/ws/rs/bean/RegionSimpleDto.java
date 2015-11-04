package fr.univ.tours.siad.ws.rs.bean;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
public class RegionSimpleDto {

    private String inseeId;

    private String name;

    public RegionSimpleDto() {
    }

    public RegionSimpleDto(String inseeId, String name) {
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
