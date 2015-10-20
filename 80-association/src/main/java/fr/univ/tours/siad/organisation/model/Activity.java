package fr.univ.tours.siad.organisation.model;

import javax.persistence.*;

/**
 * Entité Activité
 */
@Entity
@SequenceGenerator(name = "activity_sequence", sequenceName = "activity_sequence", allocationSize = 2)
@NamedQueries({
        @NamedQuery(name = Activity.FIND_ALL, query = "select a from Activity a")
})
public class Activity {

    /**
     * Requete recupérant toutes les activités
     */
    public static final String FIND_ALL = "Activity.FIND_ALL";

    /**
     * Identifiant de l'activité (PK)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_sequence")
    private Long id;

    /**
     * Nom de l'activité
     */
    @Column(length = 100)
    private String name;

    /**
     * Constructeur sans paramètres
     */
    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
