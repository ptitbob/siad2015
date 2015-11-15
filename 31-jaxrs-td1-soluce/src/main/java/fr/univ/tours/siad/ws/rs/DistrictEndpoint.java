package fr.univ.tours.siad.ws.rs;

import fr.univ.tours.siad.service.AdministrativeEntityService;
import fr.univ.tours.siad.service.exception.CityNotFoundException;
import fr.univ.tours.siad.service.exception.DistrictNotFoundException;
import fr.univ.tours.siad.util.data.bean.City;
import fr.univ.tours.siad.util.data.bean.District;
import fr.univ.tours.siad.ws.rs.dto.DistrictDto;
import fr.univ.tours.siad.ws.rs.dto.DistrictSimpleDto;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Path("district")
public class DistrictEndpoint {

    public static final String REGION_INSEE_DEFAULT_VALUE = "---";
    @EJB
    private AdministrativeEntityService administrativeEntityService;

    @GET
    @Produces({"application/json", "application/xml"})
    public List<DistrictSimpleDto> getDistrictList(@QueryParam("region") @DefaultValue(REGION_INSEE_DEFAULT_VALUE) String regionInseeId) {
        List<DistrictSimpleDto> districtSimpleDtoList = new ArrayList<>();
        if (REGION_INSEE_DEFAULT_VALUE.equals(regionInseeId)) {
            for (District district : administrativeEntityService.getDistrictList()) {
                districtSimpleDtoList.add(new DistrictSimpleDto(district.getInseeId(), district.getName()));
            }
        } else {
            for (District district : administrativeEntityService.getDistrictList(regionInseeId)) {
                districtSimpleDtoList.add(new DistrictSimpleDto(district.getInseeId(), district.getName()));
            }
        }
        return districtSimpleDtoList;
    }

    @GET
    @Produces({"application/json", "application/xml"})
    @Path("{id:[0-9]*}")
    public DistrictDto getRegion(@PathParam("id") String districtInseeId) throws DistrictNotFoundException, CityNotFoundException {
        District district = administrativeEntityService.getDistrict(districtInseeId);
        City city = administrativeEntityService.getCity(district.getChefLieuId());
        return new DistrictDto(district.getInseeId(), district.getName(), city.getName());
    }

}
