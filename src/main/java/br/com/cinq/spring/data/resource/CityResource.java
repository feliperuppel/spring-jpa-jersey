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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Path("/cities")
@Produces(MediaType.APPLICATION_JSON)
public class CityResource {

    @Autowired
    CityRepository cityDao;
    @Autowired
    CountryRepository countryDao;


    public CityResource(){

    }

    @GET
    public Response getCities(@DefaultValue("") @QueryParam("country")  String country){

        List<City> cities;
        if("".equals(country)){
            cities = cityDao.findAll();
        }else{
            List<Country> countries = countryDao.findByNameContainingIgnoreCase(country);
            cities = cityDao.findByCountryIn(countries);
        }

        return Response.ok(cities).build();
    }

    @GET
    @Path("/{id}")
    public Response getCity(@PathParam("id") @DefaultValue("0") String cityId){

        Optional<City> city = cityDao.findById(Integer.valueOf(cityId));

        return Response.ok(city).build();
    }


}
