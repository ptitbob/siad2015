package fr.univ.tours.siad.jaxb.model.club;

/**
 * Association sportive
 */
public class Sport extends Association {

    /**
     * Nom du sport
     */
    private String sport;

    /**
     * Type de sport
     */
    private SportType sportType;

    /**
     * Nombre d'équipe
     */
    private Integer teamCount;

    public Sport() {
    }

    public Sport(String name, String sport, SportType sportType, Integer teamCount) {
        super(name);
        this.sport = sport;
        this.sportType = sportType;
        this.teamCount = teamCount;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public Integer getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(Integer teamCount) {
        this.teamCount = teamCount;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "sportType=" + sportType +
                ", sport='" + sport + '\'' +
                "} " + super.toString();
    }
}
