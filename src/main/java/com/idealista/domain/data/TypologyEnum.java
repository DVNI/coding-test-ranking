package com.idealista.domain.data;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Getter
public enum TypologyEnum implements EnumType {

    FLAT("FLAT") {

        public List<Object> getMandatoryFilledAttributes(Ad ad) {
            return Arrays.asList(ad.getDescription(),
                    ad.getPictures() != null ? ad.getPictures().size() !=0 ? ad.getPictures().get(0) : null : null,
                    ad.getHouseSize());
        }
    },
    CHALET("CHALET") {
        public List<Object> getMandatoryFilledAttributes(Ad ad) {
            return Arrays.asList(ad.getDescription(),
                    ad.getPictures() != null ? ad.getPictures().size() !=0 ? ad.getPictures().get(0) : null : null,
                    ad.getHouseSize(),
                    ad.getGardenSize());
        }
    },
    GARAGE("GARAGE") {
        public List<Object> getMandatoryFilledAttributes(Ad ad) {
            return Arrays.asList(ad.getPictures().size() !=0 ? ad.getPictures().get(0) : null);
        }
    };

    private String name;

    TypologyEnum(String name) {
        this.name = name;
    }

    public static Optional<TypologyEnum> fromValue(String name) {
        for(TypologyEnum adTopology: values()) {
            if (adTopology.name().equals(name)) {
                return Optional.ofNullable(adTopology);
            }
        }
        return Optional.empty();
    }

    abstract public List<Object> getMandatoryFilledAttributes(Ad ad);
}
