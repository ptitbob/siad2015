package fr.univ.tours.siad.view;

import fr.univ.tours.siad.service.PersonService;
import fr.univ.tours.siad.util.data.bean.Person;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * Created by francois on 28/09/15.
 */
@Named
@ConversationScoped
public class PersonController implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Chaine servant à stocker l'identifiant de la personne dans la requête HTTP
     */
    private static final String PERSON_ID = "personId";

    /**
     * Chaine servant à la navigation via les outcome
     */
    public static final String UPDATE_SUCCESS = "success";

    /**
     * Entité personne
     */
    private Person person;

    /**
     * Injection de l'EJB (couche métier) concernant la gestion des personnes
     */
    @EJB
    private PersonService personService;

    /**
     * Identifiant de la personne
     */
    private Long personId;

    /**
     * Injection par le framework du contexte de conversation.
     */
    @Inject
    private Conversation conversation;

    /**
     * Injection du logger (module util ;) )
     */
    @Inject
    private Logger logger;

    /**
     * Constructeur
     */
    public PersonController() {
    }

    /**
     * Appel du callback qui est déclenché après l'instanciation du bean
     */
    @PostConstruct
    public void initializePerson() {
        logger.debug("Initialisation du controller PersonController");
        HttpServletRequest request = /* recupération de la requete HTTP */
                (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String sMemberId = request.getParameter(PERSON_ID);
        try {
            personId = sMemberId == null ? null : Long.parseLong(sMemberId);
        } catch (NumberFormatException e) {
            /* Pensez au Exception runtime ! */
            personId = null;
        }
        if (personId == null) {
            logger.debug("Création de personne");
            person = new Person();
        } else {
            logger.debug("Edition de person - Id : " + personId);
            person = personService.getById(personId);
            logger.debug("Personne retrouvée : " + person);
        }
        conversation.begin();
    }

    /**
     * Mise à jour ou création de la personne via l'appel a l'EJB PersonService et renvoi d'un outcome pour afficher la liste
     * @return outcome pour afficher la liste
     */
    public String updatePerson() {
        logger.debug("Mise à jour des données pour " + person);
        if (isPersonPersisted()) {
            personService.update(person);
        } else {
            personService.create(person);
        }
        conversation.end();
        return UPDATE_SUCCESS;
    }

    public void deletePerson(Long personId) {
        logger.debug("Suppression de la personne : " + personId);
        personService.delete(personId);
    }

    /**
     * Renvoi la liste des personne
     * @return liste d'entité personne présente en base.
     */
    public List<Person> getPersonList() {
        return personService.getPersonList();
    }

    /**
     * Test si une personne a été persistée en base
     * @return vrai ou faux
     */
    public boolean isPersonPersisted() {
        return person != null && person.getId() != null;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
