package entities;

import entities.Narudzbina;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2026-02-21T17:28:23")
@StaticMetamodel(Transakcija.class)
public class Transakcija_ { 

    public static volatile SingularAttribute<Transakcija, Narudzbina> idN;
    public static volatile SingularAttribute<Transakcija, Integer> placenaSuma;
    public static volatile SingularAttribute<Transakcija, Date> vremePlacanja;
    public static volatile SingularAttribute<Transakcija, Integer> idT;

}