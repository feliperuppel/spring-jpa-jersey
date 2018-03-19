package br.com.cinq.spring.data.resource;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Path("/countries")
@Produces(MediaType.APPLICATION_JSON)
public class CountryResource {

    @Autowired
    CountryRepository countryDao;
    @Autowired
    CityRepository cityDao;


    public CountryResource(){

    }

    @GET
    public Response getCountries(@DefaultValue("") @QueryParam("name") String name){

        List<Country> countries = "".equals(name)?countryDao.findAll():countryDao.findByNameContainingIgnoreCase(name);

        return Response.ok(countries).build();
    }

    @GET
    @Path("/{id}")
    public Response getCountry(@PathParam("id") String id){

        Optional<Country> Country = countryDao.findById(Integer.valueOf(id));

        return Response.ok(Country).build();
    }

    @GET
    @Path("/{id}/cities")
    public Response getCities(@PathParam("id") String id){

        Optional<Country> country = countryDao.findById(Integer.valueOf(id));
        List<City> cities = cityDao.findByCountryIn(Arrays.asList(country.get()));

        return Response.ok(cities).build();
    }

}
