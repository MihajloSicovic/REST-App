package entities;

import entities.StavkaNarudzbina;
import entities.Transakcija;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2026-02-21T17:28:23")
@StaticMetamodel(Narudzbina.class)
public class Narudzbina_ { 

    public static volatile SingularAttribute<Narudzbina, Integer> idK;
    public static volatile SingularAttribute<Narudzbina, Integer> idN;
    public static volatile SingularAttribute<Narudzbina, Integer> ukupnaCena;
    public static volatile SingularAttribute<Narudzbina, Date> vremeKreiranja;
    public static volatile SingularAttribute<Narudzbina, String> adresa;
    public static volatile ListAttribute<Narudzbina, Transakcija> transakcijaList;
    public static volatile SingularAttribute<Narudzbina, Integer> idG;
    public static volatile ListAttribute<Narudzbina, StavkaNarudzbina> stavkaNarudzbinaList;

}