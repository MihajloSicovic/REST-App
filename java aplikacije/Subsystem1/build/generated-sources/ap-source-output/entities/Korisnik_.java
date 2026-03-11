package entities;

import entities.Grad;
import entities.Uloga;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2026-02-06T18:33:29")
@StaticMetamodel(Korisnik.class)
public class Korisnik_ { 

    public static volatile SingularAttribute<Korisnik, String> ime;
    public static volatile SingularAttribute<Korisnik, String> prezime;
    public static volatile SingularAttribute<Korisnik, Integer> idK;
    public static volatile SingularAttribute<Korisnik, String> lozinka;
    public static volatile SingularAttribute<Korisnik, Integer> stanje;
    public static volatile ListAttribute<Korisnik, Uloga> ulogaList;
    public static volatile SingularAttribute<Korisnik, String> adresa;
    public static volatile SingularAttribute<Korisnik, String> korisnickoIme;
    public static volatile SingularAttribute<Korisnik, Grad> grad;

}