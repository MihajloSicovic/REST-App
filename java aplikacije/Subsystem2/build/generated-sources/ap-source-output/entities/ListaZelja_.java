package entities;

import entities.StavkaZelja;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2026-02-16T18:46:16")
@StaticMetamodel(ListaZelja.class)
public class ListaZelja_ { 

    public static volatile SingularAttribute<ListaZelja, Integer> idK;
    public static volatile SingularAttribute<ListaZelja, Date> datumKreiranja;
    public static volatile ListAttribute<ListaZelja, StavkaZelja> stavkaZeljaList;

}