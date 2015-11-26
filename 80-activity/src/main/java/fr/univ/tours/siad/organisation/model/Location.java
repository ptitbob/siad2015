package fr.univ.tours.siad.organisation.model;

import javax.persistence.*;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Entity
@SequenceGenerator(name = "location_sequence", sequenceName = "location_sequence")
public class Location {

    public static final String LOCATION_ID = "LOCATION_ID";

    @Id
    @GeneratedValue(generator = "location_sequence")
    @Column(name = LOCATION_ID)
    private Long id;

    @Column(length = 100)
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address", referencedColumnName = Address.ADDRESS_ID)
    private Address address;
}
