package br.com.sunshine.services;

import br.com.sunshine.dto.AmountDTO;
import br.com.sunshine.model.Amount;
import br.com.sunshine.repository.AmountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmountService {

    @Autowired
    private AmountRepository repository;
    private final ModelMapper mapper = new ModelMapper();

    public AmountDTO create(Amount amount){
        return mapper.map(repository.save(amount), AmountDTO.class);
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public List<AmountDTO> getAllByUserId(String id){
        return repository.findAllByUserId(id).stream().map(amount -> mapper.map(amount, AmountDTO.class)).toList();
    }

    public void decrement(String id){
        repository.decrement(id, 1);
    }

    public void increment(String id){
        Optional<Amount> amount = repository.findById(id);

        if(amount.isPresent()){
            if(amount.get().getQuantity() < amount.get().getProduct().getAvailable()){
                amount.get().setQuantity(amount.get().getQuantity() + 1);
                repository.save(amount.get());
            }
        }
    }
}
