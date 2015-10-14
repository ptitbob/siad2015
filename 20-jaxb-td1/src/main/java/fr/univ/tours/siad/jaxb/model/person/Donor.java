package fr.univ.tours.siad.jaxb.model.person;

/**
 * Donateur
 */
public class Donor extends Person {

    /**
     * Montant de la donnation
     */
    private int donationAmount;

    public Donor() {
    }

    public Donor(Long id, String name, String surname, int donationAmount) {
        super(id, name, surname);
        this.donationAmount = donationAmount;
    }

    public int getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(int donationAmount) {
        this.donationAmount = donationAmount;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "donationAmount=" + donationAmount +
                "} " + super.toString();
    }
}
