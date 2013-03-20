package ohtu.verkkokauppa;

public interface OstoskoriInterface {

    int hinta();

    void lisaa(Tuote t);

    void poista(Tuote t);
    
}
