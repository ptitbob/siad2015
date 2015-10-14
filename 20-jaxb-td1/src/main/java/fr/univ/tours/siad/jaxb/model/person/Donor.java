package fr.univ.tours.siad.jaxb.model.person;

/**
 * Donateur
 */
public class Donor extends Person {

    /**
     * Montant de la donnation
     */
    private int donationAmount;

    private DonorType donorType;

    public Donor() {
    }

    public Donor(Long id, String name, String surname, int donationAmount, DonorType donorType) {
        this(id, name, surname, donationAmount, donorType, null);
    }

    public Donor(Long id, String name, String surname, int donationAmount, DonorType donorType, Address address) {
        super(id, name, surname, address);
        setDonationAmount(donationAmount);
        setDonorType(donorType);
    }

    public int getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(int donationAmount) {
        this.donationAmount = donationAmount;
    }

    public DonorType getDonorType() {
        return donorType;
    }

    public void setDonorType(DonorType donorType) {
        this.donorType = donorType;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "donationAmount=" + donationAmount +
                ", donorType=" + donorType +
                "} " + super.toString();
    }
}
