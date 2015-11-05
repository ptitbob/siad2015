package fr.univ.tours.siad.ws.rs;

import fr.univ.tours.siad.services.RegionServices;
import fr.univ.tours.siad.services.exception.NoRegionFoundException;
import fr.univ.tours.siad.util.data.bean.Region;
import fr.univ.tours.siad.ws.rs.bean.RegionDto;
import fr.univ.tours.siad.ws.rs.bean.RegionSimpleDto;
import fr.univ.tours.siad.ws.rs.exception.IllegalInseeIdPresentationException;
import fr.univ.tours.siad.ws.rs.tools.RegionDtoCSV;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
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

    @Inject
    private Logger logger;

    /**
     * Renvoi de la liste des régions - au format JSON, XML, CSV
     * @return liste region
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, RegionDtoCSV.SIAD_CSV})
    public List<RegionSimpleDto> getRegionList() {
        List<RegionSimpleDto> regionSimpleDtoList = new ArrayList<>();
        for (Region region : regionServices.getRegionList()) {
            regionSimpleDtoList.add(new RegionSimpleDto(region.getInseeId(), region.getName()));
        }
        return regionSimpleDtoList;
    }

    /**
     * Renvoi une région en mode complet
     * @param regionInseeId identifiant INSEE de la region
     * @return Region en mode plein.
     * @throws NoRegionFoundException si la region n'a pas été trouvé.
     */
    @GET
    @Path("/{id:[0-9]{2}[0-9]*}") // utilisation d'une regex
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RegionDto getRegionByInseeId(@PathParam("id") String regionInseeId) throws NoRegionFoundException {
        Region region = regionServices.getRegionByInseeId(regionInseeId);
        Long districtCount = regionServices.getDistrictCountFor(regionInseeId);
        return new RegionDto(region.getInseeId(), region.getName(), region.getChefLieuId(), districtCount, region.getUpperName());
    }

    /**
     * Création d'une region en fournissant le nom complet et le nom normalisé
     * @param name nom complet
     * @param upperName nom normalisé
     * @return reponse
     */
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response createRegion(String name, String upperName) {
        Region region = regionServices.createRegion(name, upperName);
        // Creation de l'URL de la ressource, accessible via une requête GET
        URI regionCreatedUri = UriBuilder
                .fromResource(RegionEndpoint.class) // Pour générer les l'url de base du endpoint
                .path(region.getInseeId()) // Ajout d'un complément à l'URL, dans ce cas, le n° INSEE de la région
                .build(); // Génération, application du pattern Builder
        return Response.created(regionCreatedUri).build(); // Renvoi de la réponse status 201 en renvoyant dans le header l'URL de la ressource créé.
    }

    /**
     * Mise à jour d'une région en ne prenant en compte que les champs nom, nom norlalisé et identifiant du chef lieu.
     * Les données inhérente au n° INSEE et Id ne sont pas mis à jour
     * L'id n° INSEE de l'URL et celui de la region sont vérifiés
     * @param inseeId N° INSEE fournit via l'URL
     * @param regionDto Region (JSON, XML)
     * @return Ok si mis à jour sans problème.
     * @throws NoRegionFoundException si la region à mettre à jours n'existe pas.
     * @throws IllegalInseeIdPresentationException
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id:[0-9]{2}[0-9]*}") // utilisation d'une regex
    public Response updateRegion(@PathParam("id") String inseeId, RegionDto regionDto) throws NoRegionFoundException, IllegalInseeIdPresentationException {
        if (!inseeId.equals(regionDto.getInseeId())) {
            // Vérification de la concordance entre N°INSEE de l'URL et celui des donnée updatée.
            throw new IllegalInseeIdPresentationException(inseeId, regionDto.getInseeId());
        }
        Region region = regionServices.getRegionByInseeId(regionDto.getInseeId());
        region.setName(regionDto.getName());
        region.setUpperName(regionDto.getUpperName());
        region.setChefLieuId(regionDto.getChefLieuId());
        regionServices.updateRegion(region);
        return Response.ok().build();
    }

    /**
     * Suppression d'une région
     * @param inseeId n° INSEE de la région a supprimer
     * @return reponse Ok, car gestion idempotent...—
     */
    @DELETE
    @Path("/{id:[0-9]{2}[0-9]*}") // utilisation d'une regex
    public Response deleteRegion(@PathParam("id") String inseeId) {
        try {
            regionServices.removeRegionById(inseeId);
        } catch (NoRegionFoundException e) {
            logger.warn(String.format("La region n° %s n'existe pas", inseeId));
        }
        return Response.ok().build();
    }
}
