package com.idealista.infrastructure.persistence;

import com.idealista.application.repository.CrudRepository;
import com.idealista.domain.persistence.AdVO;
import com.idealista.domain.persistence.PictureVO;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class InMemoryPersistence implements CrudRepository {

    private List<AdVO> ads;
    private List<PictureVO> pictures;
    private Map<Integer, AdVO> adsDomain;

    public InMemoryPersistence() {
        ads = new ArrayList<AdVO>();
        ads.add(new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.<PictureVO>emptyList(), 300, null, null, null));
        ads.add(new AdVO(2, "FLAT", "Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo", Arrays.asList(new PictureVO(4, "http://www.idealista.com/pictures/4", "HD")), 300, null, null, null));
        ads.add(new AdVO(3, "CHALET", "", Arrays.asList(new PictureVO(2, "http://www.idealista.com/pictures/2", "HD")), 300, null, null, null));
        ads.add(new AdVO(4, "FLAT", "Ático céntrico muy luminoso y recién reformado, parece nuevo", Arrays.asList(new PictureVO(5, "http://www.idealista.com/pictures/5", "SD")), 300, null, null, null));
        ads.add(new AdVO(5, "FLAT", "Pisazo,", Arrays.asList(new PictureVO(3, "http://www.idealista.com/pictures/3", "SD"), new PictureVO(8, "http://www.idealista.com/pictures/8", "HD")), 300, null, null, null));
        ads.add(new AdVO(6, "GARAGE", "", Arrays.asList(new PictureVO(6, "http://www.idealista.com/pictures/6", "SD")), 300, null, null, null));
        ads.add(new AdVO(7, "GARAGE", "Garaje en el centro de Albacete", Collections.<PictureVO>emptyList(), 300, null, null, null));
        ads.add(new AdVO(8, "CHALET", "Maravilloso chalet situado en lAs afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!", Arrays.asList(new PictureVO(1, "http://www.idealista.com/pictures/1", "SD"), new PictureVO(7, "http://www.idealista.com/pictures/7", "SD")), 300, null, null, null));

        adsDomain = ads.stream().collect(Collectors.toMap(AdVO::getId, Function.identity()));

        pictures = new ArrayList<PictureVO>();
        pictures.add(new PictureVO(1, "http://www.idealista.com/pictures/1", "SD"));
        pictures.add(new PictureVO(2, "http://www.idealista.com/pictures/2", "HD"));
        pictures.add(new PictureVO(3, "http://www.idealista.com/pictures/3", "SD"));
        pictures.add(new PictureVO(4, "http://www.idealista.com/pictures/4", "HD"));
        pictures.add(new PictureVO(5, "http://www.idealista.com/pictures/5", "SD"));
        pictures.add(new PictureVO(6, "http://www.idealista.com/pictures/6", "SD"));
        pictures.add(new PictureVO(7, "http://www.idealista.com/pictures/7", "SD"));
        pictures.add(new PictureVO(8, "http://www.idealista.com/pictures/8", "HD"));
    }

    public List<AdVO> findAll() {
        return adsDomain.values().stream().collect(Collectors.toList());
    }

    public List<AdVO> saveAll(List<AdVO> ads) {
        Map<Integer, AdVO> addsToSave = ads.stream().collect(Collectors.toMap(AdVO::getId,Function.identity()));
        adsDomain.putAll(addsToSave);
        return addsToSave.values().stream().collect(Collectors.toList());
    }

    //TODO crea los métodos que necesites
}
