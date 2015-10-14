package fr.univ.tours.siad.jaxb.model.person;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Membre du bureau
 */
public class BoardMember extends Adherent {

    /**
     * Poste dans le bureau de l'association
     */
    private BoardMemberType boardMemberType;

    public BoardMember() {
    }

    public BoardMember(Long id, String name, String surname, String membershipDate, BoardMemberType boardMemberType) {
        super(id, name, surname, membershipDate);
        this.boardMemberType = boardMemberType;
    }

    public BoardMemberType getBoardMemberType() {
        return boardMemberType;
    }

    public void setBoardMemberType(BoardMemberType boardMemberType) {
        this.boardMemberType = boardMemberType;
    }

    @Override
    public String toString() {
        return "BoardMember{" +
                "boardMemberType=" + boardMemberType +
                "} " + super.toString();
    }
}
