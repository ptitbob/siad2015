/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.tours.siad.jsf.intro;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author robert-f
 */
@Path("test")
public class ServiceAlpha {
    
    @GET
    public String test() {
        return "yo !!";
    }
    
}
