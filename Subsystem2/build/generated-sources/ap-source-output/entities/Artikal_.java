package entities;

import entities.Kategorija;
import entities.StavkaZelja;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2026-02-08T18:21:02")
@StaticMetamodel(Artikal.class)
public class Artikal_ { 

    public static volatile SingularAttribute<Artikal, Integer> idK;
    public static volatile SingularAttribute<Artikal, Kategorija> idKat;
    public static volatile SingularAttribute<Artikal, Integer> idA;
    public static volatile SingularAttribute<Artikal, String> naziv;
    public static volatile SingularAttribute<Artikal, Integer> popust;
    public static volatile SingularAttribute<Artikal, Integer> cena;
    public static volatile SingularAttribute<Artikal, String> opis;
    public static volatile ListAttribute<Artikal, StavkaZelja> stavkaZeljaList;

}