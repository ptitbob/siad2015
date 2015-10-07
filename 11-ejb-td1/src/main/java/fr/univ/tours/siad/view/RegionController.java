package fr.univ.tours.siad.view;

import fr.univ.tours.siad.service.CityService;
import fr.univ.tours.siad.service.DistrictService;
import fr.univ.tours.siad.service.RegionService;
import fr.univ.tours.siad.util.data.bean.City;
import fr.univ.tours.siad.util.data.bean.District;
import fr.univ.tours.siad.util.data.bean.Person;
import fr.univ.tours.siad.util.data.bean.Region;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by francois on 08/10/15.
 */
@Named
@RequestScoped
public class RegionController {

    @Inject
    private Logger logger;

    private static final String REGION_ID = "regionId";

    @EJB
    private RegionService regionService;

    @EJB
    private CityService cityService;

    @EJB
    private DistrictService districtService;

    private Long regionId;

    private Region region;

    private City regionMainCity;

    @PostConstruct
    public void initialize() {
        HttpServletRequest request = /* recupération de la requete HTTP */
                (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String sRegionId = request.getParameter(REGION_ID);
        try {
            regionId = sRegionId == null ? null : Long.parseLong(sRegionId);
        } catch (NumberFormatException e) {
            /* Pensez au Exception runtime ! */
            regionId = null;
        }
        if (regionId != null) {
            region = regionService.getById(regionId);
            logger.debug("Region retrouvée : " + region);
            regionMainCity = cityService.retrieveCityByInseeId(region.getChefLieuId());
        }
    }

    public List<Region> getRegionList() {
        return regionService.findAll();
    }

    public List<District> getDistrictList() {
        if (region != null) {
        return districtService.findFor(region);
        }
        return null;
    }

    public Region getRegion() {
        return region;
    }

    public City getRegionMainCity() {
        return regionMainCity;
    }
}
