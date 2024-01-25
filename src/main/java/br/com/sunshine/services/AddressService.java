package br.com.sunshine.services;

import br.com.sunshine.model.Address;
import br.com.sunshine.model.Establishment;
import br.com.sunshine.model.Taxa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private MapsService mapsService;

    public double getTaxaDelivery(Establishment establishment, Address address) {

        var result = mapsService.findDistaceToAddress(establishment.getAddress().getZipCode(), address.getZipCode());
        double valor = switch (establishment.getTypeDelivery()) {
            case FIXA -> {
                yield 0;
            }
            case QUILOMETRAGEM -> {

                yield 0;
            }
            case SEDEX -> {
                yield 0;
            }
        };
        return valor;
    }
}
