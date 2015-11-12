package fr.univ.tours.siad.ws.rs.mapper;

import fr.univ.tours.siad.ws.rs.bean.RegionDto;
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
public class SingleRegionDtoCSVWriter implements MessageBodyWriter<RegionSimpleDto> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return RegionDto.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(RegionSimpleDto regionSimpleDto, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(RegionSimpleDto regionSimpleDto, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        StringJoiner stringJoiner = new StringJoiner(",", "", "\n");
        stringJoiner.add(regionSimpleDto.getInseeId()).add(regionSimpleDto.getName());
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(entityStream)) {
            outputStreamWriter.write(stringJoiner.toString());
        }
    }
}
