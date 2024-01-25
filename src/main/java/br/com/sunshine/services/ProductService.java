package br.com.sunshine.services;

import br.com.sunshine.dto.ProductDTO;
import br.com.sunshine.model.Product;
import br.com.sunshine.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    
    private final ModelMapper mapper = new ModelMapper();
    
    public ProductDTO create(Product product){
        return mapper.map(repository.save(product), ProductDTO.class);
    }
    
    public void delete(String id){
        repository.deleteById(id);
    }

    public ProductDTO getById(String id){
        return mapper.map(repository.findById(id), ProductDTO.class);
    }

    public List<ProductDTO> getByOffSet(String establishmentId, int offset){
        return repository.getByOffSet(establishmentId, offset).stream().map(product -> mapper.map(product, ProductDTO.class)).toList();
    }

    public int getSizeByEstablishment(String id){
        return repository.size(id);
    }
}
