package fr.univ.tours.siad.view;

import fr.univ.tours.siad.service.CityService;
import fr.univ.tours.siad.service.DistrictService;
import fr.univ.tours.siad.util.data.bean.City;
import fr.univ.tours.siad.util.data.bean.District;
import fr.univ.tours.siad.util.data.bean.ZipCode;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by francois on 08/10/15.
 */
@Named
@RequestScoped
public class DistrictController {

    private static final String DISTRICT_ID = "districtId";

    private Long districtId;

    @EJB
    private DistrictService districtService;

    @EJB
    private CityService cityService;

    @Inject
    private Logger logger;

    private District district;

    private City districtMainCity;

    @PostConstruct
    public void initialize() {
        HttpServletRequest request = /* recupération de la requete HTTP */
                (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String sDistrictId = request.getParameter(DISTRICT_ID);
        try {
            districtId = sDistrictId == null ? null : Long.parseLong(sDistrictId);
        } catch (NumberFormatException e) {
            /* Pensez au Exception runtime ! */
            districtId = null;
        }
        if (districtId != null) {
            district = districtService.getById(districtId);
            logger.debug("Département retrouvé : " + district);
            districtMainCity = cityService.retrieveCityByInseeId(district.getChefLieuId());
        }
    }

    public long computeAverageElevation(District district) {
        long sum = 0;
        List<City> cityList = getCityList();
        for (City city : cityList) {
            sum += city.getElevation();
        }
        if (cityList.size() > 0) {
            return sum / cityList.size();
        }
        return 0;
    }

    public List<City> getCityList() {
        if (district != null) {
            return cityService.findFor(district);
        }
        return null;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public City getDistrictMainCity() {
        return districtMainCity;
    }

    public void setDistrictMainCity(City districtMainCity) {
        this.districtMainCity = districtMainCity;
    }
}
