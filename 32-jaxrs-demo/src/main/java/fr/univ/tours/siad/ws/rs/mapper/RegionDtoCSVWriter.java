package fr.univ.tours.siad.ws.rs.mapper;

import fr.univ.tours.siad.ws.rs.bean.RegionSimpleDto;
import fr.univ.tours.siad.ws.rs.tools.RegionDtoCSV;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.StringJoiner;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Provider
@Produces(RegionDtoCSV.SIAD_CSV)
public class RegionDtoCSVWriter implements MessageBodyWriter<List<RegionSimpleDto>> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        if (List.class.isAssignableFrom(type)) { // verification du type principal
            if (genericType instanceof ParameterizedType) { // est ce que le type générique
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                // recupération du type d'argument
                Type[] actualTypeArgs = (parameterizedType.getActualTypeArguments());
                // Vérification qu'il n'y a qu'un seul type paramétré et que que celui ci est correct
                return (actualTypeArgs.length == 1 && actualTypeArgs[0] == RegionSimpleDto.class);
            }
        }
        return false;
    }

    @Override
    public long getSize(List<RegionSimpleDto> regionSimpleDtos, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(List<RegionSimpleDto> regionSimpleDtoList, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        String csvAsString = "";
        for (RegionSimpleDto regionSimpleDto : regionSimpleDtoList) {
            StringJoiner stringJoiner = new StringJoiner(",", "", "\n");
            stringJoiner.add(regionSimpleDto.getInseeId()).add(regionSimpleDto.getName());
            csvAsString += stringJoiner.toString();
        }
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(entityStream)) {
            outputStreamWriter.write(csvAsString);
        }
    }
}
