package fr.univ.tours.siad.organisation.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Entity
@SequenceGenerator(name = "membership_sequence", sequenceName = "membership_sequence")
@NamedQueries({
        @NamedQuery(name = Membership.FIND_BY_PERSON, query = "select ms from Membership ms where ms.person.id = :" + Person.PERSON_ID)
        , @NamedQuery(name = Membership.FIND_BY_ACTIVITY, query = "select ms from Membership ms where ms.activity.id = :" + Activity.ACTIVITY_ID)
        , @NamedQuery(name = Membership.FIND_BY_PERSON_ACTIVITY, query = "select ms from Membership ms where ms.person.id = :" + Person.PERSON_ID + " and ms.activity = :" + Activity.ACTIVITY_ID)
})
public class Membership {

    public static final String FIND_BY_PERSON = "Membership.FIND_BY_PERSON";
    public static final String FIND_BY_ACTIVITY = "Membership.FIND_BY_ACTIVITY";
    public static final String FIND_BY_PERSON_ACTIVITY = "Membership.FIND_BY_PERSON_ACTIVITY";

    @Id
    @GeneratedValue(generator = "membership_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Activity activity;

    /**
     * Date de l'adhésion (debut)
     */
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date membershipDateBegin;

    /**
     * Date de fin de l'adhésion, peut être nulle
     */
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date getMembershipDateEnd;

    /**
     * Personne adhérente
     */
    @ManyToOne
    private Person person;

    public Membership() {
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Date getGetMembershipDateEnd() {
        return getMembershipDateEnd;
    }

    public void setGetMembershipDateEnd(Date getMembershipDateEnd) {
        this.getMembershipDateEnd = getMembershipDateEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMembershipDateBegin() {
        return membershipDateBegin;
    }

    public void setMembershipDateBegin(Date membershipDateBegin) {
        this.membershipDateBegin = membershipDateBegin;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Membership)) return false;
        Membership that = (Membership) o;
        return Objects.equals(getActivity(), that.getActivity()) &&
                Objects.equals(getPerson(), that.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActivity(), getPerson());
    }

    @Override
    public String toString() {
        return "Membership{" +
                "activity=" + activity +
                ", person=" + person +
                ", id=" + id +
                ", membershipDateBegin=" + membershipDateBegin +
                ", getMembershipDateEnd=" + getMembershipDateEnd +
                '}';
    }
}
