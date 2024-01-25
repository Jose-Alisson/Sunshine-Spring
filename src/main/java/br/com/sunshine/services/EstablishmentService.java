package br.com.sunshine.services;

import br.com.sunshine.dto.EstablishmentDTO;
import br.com.sunshine.model.Establishment;
import br.com.sunshine.repository.EstablishmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public EstablishmentDTO create(Establishment establishment){
        return mapper.map(repository.save(establishment),EstablishmentDTO.class);
    }

    public EstablishmentDTO update(EstablishmentDTO dto){
        Optional<Establishment> establishment_ = repository.findByName(dto.getName());

        if(establishment_.isPresent()){
            Establishment establishment = Establishment.builder().
                    id(establishment_.get().getId()).
                    name(dto.getName()).
                    taxas(establishment_.get().getTaxas()).
                    address(dto.getAddress()).
                    isOpen(dto.isOpen()).
                    products(dto.getProducts()).
                    typeDelivery(establishment_.get().getTypeDelivery()).
                    orders(establishment_.get().getOrders()).build();

            return mapper.map(repository.save(establishment), EstablishmentDTO.class);
        }

        return null;
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public void setOpen(String id, boolean isOpen){
        repository.setOpen(id, isOpen);
    }

    public  EstablishmentDTO getByName(String name){
        return mapper.map(repository.findByName(name), EstablishmentDTO.class);
    }
}
