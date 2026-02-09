package entities;

import entities.Korisnik;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2026-02-04T22:43:16")
@StaticMetamodel(Uloga.class)
public class Uloga_ { 

    public static volatile SingularAttribute<Uloga, String> naziv;
    public static volatile SingularAttribute<Uloga, Integer> idU;
    public static volatile ListAttribute<Uloga, Korisnik> korisnikList;

}