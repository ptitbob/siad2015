package fr.univ.tours.siad.jaxb.model.club;

/**
 * Association culturelle
 */
public class Cultural extends Association {

    /**
     * Type d'association culturelle (musique, peinture)
     */
    private String associationTarget;

    public Cultural() {
    }

    public Cultural(String name, String associationTarget) {
        super(name);
        this.associationTarget = associationTarget;
    }

    public String getAssociationTarget() {
        return associationTarget;
    }

    public void setAssociationTarget(String associationTarget) {
        this.associationTarget = associationTarget;
    }

    @Override
    public String toString() {
        return "Cultural{" +
                "associationTarget='" + associationTarget + '\'' +
                "} " + super.toString();
    }
}
