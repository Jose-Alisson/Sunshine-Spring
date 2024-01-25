package br.com.sunshine.services;

import br.com.sunshine.dto.AttributeDTO;
import br.com.sunshine.model.Attribute;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {
    @Autowired
    private AttributeRepository repository;

    private ModelMapper mapper = new ModelMapper();

    public AttributeDTO create (Attribute attribute){
        return mapper.map(repository.save(attribute), AttributeDTO.class);
    }
}
