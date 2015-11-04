package fr.univ.tours.siad.ws.rs;

import fr.univ.tours.siad.services.RegionServices;
import fr.univ.tours.siad.services.exception.NoRegionFoundException;
import fr.univ.tours.siad.util.data.bean.Region;
import fr.univ.tours.siad.ws.rs.bean.RegionDto;
import fr.univ.tours.siad.ws.rs.bean.RegionSimpleDto;
import fr.univ.tours.siad.ws.rs.tools.RegionDtoCSV;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Path("region")
public class RegionEndpoint {

    @EJB
    private RegionServices regionServices;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, RegionDtoCSV.SIAD_CSV})
    public List<RegionSimpleDto> getRegionList() {
        List<RegionSimpleDto> regionSimpleDtoList = new ArrayList<>();
        for (Region region : regionServices.getRegionList()) {
            regionSimpleDtoList.add(new RegionSimpleDto(region.getInseeId(), region.getName()));
        }
        return regionSimpleDtoList;
    }

    @GET
    @Path("/{id:[0-9]{2}[0-9]*}") // utilisation d'une regex
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RegionDto getRegionByInseeId(@PathParam("id") String regionInseeId) throws NoRegionFoundException {
        Region region = regionServices.getRegionByInseeId(regionInseeId);
        Long districtCount = regionServices.getDistrictCountFor(regionInseeId);
        return new RegionDto(region.getInseeId(), region.getName(), region.getChefLieuId(), districtCount, region.getUpperName());
    }
}
