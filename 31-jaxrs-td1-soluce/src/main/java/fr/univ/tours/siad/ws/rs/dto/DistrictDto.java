package fr.univ.tours.siad.ws.rs.dto;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
public class DistrictDto extends DistrictSimpleDto {

    /**
     * Nom du chef lieu du département
     */
    private String chefLieuName;

    public DistrictDto() {
    }

    public DistrictDto(String inseeId, String name, String chefLieuName) {
        super(inseeId, name);
        this.chefLieuName = chefLieuName;
    }

    public String getChefLieuName() {
        return chefLieuName;
    }

    public void setChefLieuName(String chefLieuName) {
        this.chefLieuName = chefLieuName;
    }
}
