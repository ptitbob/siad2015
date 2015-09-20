package fr.univ.tours.siad.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@RequestScoped
public class TimeController implements Serializable {
    
    private static final long serialVersionUID = -7748708046976719284L;

    private Date date = new Date();

    public Date getDate() {
        return date;
    }

}
