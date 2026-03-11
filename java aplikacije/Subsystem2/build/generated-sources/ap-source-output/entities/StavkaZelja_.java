package entities;

import entities.Artikal;
import entities.ListaZelja;
import entities.StavkaZeljaPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2026-02-16T18:46:16")
@StaticMetamodel(StavkaZelja.class)
public class StavkaZelja_ { 

    public static volatile SingularAttribute<StavkaZelja, Date> datumDodavanja;
    public static volatile SingularAttribute<StavkaZelja, StavkaZeljaPK> stavkaZeljaPK;
    public static volatile SingularAttribute<StavkaZelja, ListaZelja> listaZelja;
    public static volatile SingularAttribute<StavkaZelja, Artikal> artikal;

}