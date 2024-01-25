package br.com.sunshine.services;

import br.com.sunshine.dto.OrderDTO;
import br.com.sunshine.enums.AmountStatus;
import br.com.sunshine.enums.OrderStatus;
import br.com.sunshine.model.Address;
import br.com.sunshine.model.Order;
import br.com.sunshine.model.Payment;
import br.com.sunshine.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private PaymentService service;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PaymentService paymentService;

    private final ModelMapper mapper = new ModelMapper();

    public OrderDTO create(Order order){
        order.setStatus(OrderStatus.CRIADO);
        order.setDateCreation(LocalDateTime.now());
        order.getAmounts().forEach(amount -> amount.setStatus(AmountStatus.ANEXADO));

        Payment payment = order.getPayment();
        if(payment == null){
            int value = 0;

            Payment payment_ = Payment.builder().paymentValue(value).build();
        }

        return mapper.map(repository.save(order), OrderDTO.class);
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public List<OrderDTO> getAllByUserId(String id){
        return repository.findAllByUserId(id).stream().map(order -> mapper.map(order, OrderDTO.class)).toList();
    }

    private double getOrderValue(Order order){
        double[] value = {0};

        order.getAmounts().forEach(amount -> {
            double[] valueAmount = {0};

            if(amount.getProduct() != null){
                valueAmount[0] += amount.getProduct().getBasePrice();
            }

            amount.getProductAttributes().forEach(attr -> {
                valueAmount[0] += attr.getAttributePrice();
            });

            valueAmount[0] = valueAmount[0] * amount.getQuantity();

            value[0] += valueAmount[0];
        });

        if(order.getAddress() != null){
            Address address = order.getAddress();

        }

        return value[0];
    }
}
