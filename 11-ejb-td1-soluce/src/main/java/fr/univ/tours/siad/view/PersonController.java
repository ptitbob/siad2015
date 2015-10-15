package fr.univ.tours.siad.view;

import fr.univ.tours.siad.service.CityService;
import fr.univ.tours.siad.service.DistrictService;
import fr.univ.tours.siad.service.PersonService;
import fr.univ.tours.siad.util.data.bean.*;
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
import java.util.Set;

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

    @EJB
    private DistrictService districtService;

    @EJB
    private CityService cityService;

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

    private String districtINSEE;

    private District district;

    private List<City> cityList;

    private Set<ZipCode> zipCodeSet;

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
        if (conversation.isTransient()) {
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
    }

    /**
     * Mise à jour ou création de la personne via l'appel a l'EJB PersonService et renvoi d'un outcome pour afficher la liste
     *
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
     *
     * @return liste d'entité personne présente en base.
     */
    public List<Person> getPersonList() {
        return personService.getPersonList();
    }

    public String selectDistrict() {
        logger.debug("Département saisie : " + districtINSEE);
        district = districtService.getByINSEE(districtINSEE);
        if (district != null) {
            cityList = cityService.findFor(district);
            return "selectcity";
        }
        return null;
    }

    public String selectCity(City selectedCity) {
        person.setAddress(new Address());
        person.getAddress().setCity(selectedCity);
        zipCodeSet = selectedCity.getZipCodeSet();
        return "fillAddress";
    }

    /**
     * Test si une personne a été persistée en base
     *
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

    public String getDistrictINSEE() {
        return districtINSEE;
    }

    public void setDistrictINSEE(String districtINSEE) {
        this.districtINSEE = districtINSEE;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public Set<ZipCode> getZipCodeSet() {
        return zipCodeSet;
    }

    public void setZipCodeSet(Set<ZipCode> zipCodeSet) {
        this.zipCodeSet = zipCodeSet;
    }
}
