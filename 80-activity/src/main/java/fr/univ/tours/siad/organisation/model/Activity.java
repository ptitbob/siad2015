package fr.univ.tours.siad.organisation.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Entité Activité
 */
@Entity
@SequenceGenerator(name = "activity_sequence", sequenceName = "activity_sequence", allocationSize = 2)
@NamedQueries({
        @NamedQuery(name = Activity.FIND_ALL, query = "select a from Activity a")
        , @NamedQuery(name = Activity.FIND_BY_CITY, query = "select a from Activity a where a.location.address.city.inseeId = :" + City.INSEEID)
})
public class Activity implements Serializable {

    /**
     * Requete recupérant toutes les activités
     */
    public static final String FIND_ALL = "Activity.FIND_ALL";
    public static final String ACTIVITY_ID = "ACTIVITY_ID";
    public static final String FIND_BY_CITY = "Activity.FIND_BY_CITY";

    /**
     * Identifiant de l'activité (PK)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_sequence")
    @Column(name = ACTIVITY_ID)
    private Long id;

    @ManyToMany
    private List<Category> categoryList;
    /**
     * Date de debut de l'activité
     */
    @Temporal(TemporalType.DATE)
    private Date dateStart;

    /**
     * Date de fin de l'activité
     */
    @Temporal(TemporalType.DATE)
    private Date dateEnd;

    /**
     * Nom de l'activité
     */
    @Column(length = 100)
    private String name;

    /**
     * Organisateur de l'activité
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Person organizer;

    /**
     * Liste des adhésions
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "activity")
    private List<Membership> membershipList;

    /**
     * Emplacement de l'activité
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION", referencedColumnName = Location.LOCATION_ID)
    private Location location;


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

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public static String getFindAll() {
        return FIND_ALL;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Membership> getMembershipList() {
        return membershipList;
    }

    public void setMembershipList(List<Membership> membershipList) {
        this.membershipList = membershipList;
    }

    public Person getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Person organizer) {
        this.organizer = organizer;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return Objects.equals(getDateStart(), activity.getDateStart()) &&
                Objects.equals(getDateEnd(), activity.getDateEnd()) &&
                Objects.equals(getName(), activity.getName()) &&
                Objects.equals(getOrganizer(), activity.getOrganizer()) &&
                Objects.equals(getLocation(), activity.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateStart(), getDateEnd(), getName(), getOrganizer(), getLocation());
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", organizer=" + organizer +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", location=" + location +
                '}';
    }
}
