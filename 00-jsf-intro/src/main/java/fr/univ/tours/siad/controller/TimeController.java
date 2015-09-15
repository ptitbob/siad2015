package fr.univ.tours.siad.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Date;

@Named
@RequestScoped
public class TimeController {

    private Date date = new Date();

    public Date getDate() {
        return date;
    }

}
