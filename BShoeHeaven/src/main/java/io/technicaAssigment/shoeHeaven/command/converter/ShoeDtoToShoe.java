package io.technicaAssigment.shoeHeaven.command.converter;

import io.technicaAssigment.shoeHeaven.command.ShoeDto;
import io.technicaAssigment.shoeHeaven.persistence.model.Shoe;
import io.technicaAssigment.shoeHeaven.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ShoeDtoToShoe implements Converter<ShoeDto, Shoe> {

    private ShoeService shoeService;

    @Autowired
    public void setShoeService(ShoeService shoeService){
        this.shoeService = shoeService;
    }

    @Override
    public Shoe convert(ShoeDto shoeDto) {
        Shoe shoe = (shoeDto.getId() != null ? shoeService.get(shoeDto.getId()) : new Shoe());

        shoe.setBrand(shoeDto.getBrand());
        shoe.setName(shoeDto.getName());
        shoe.setColor(shoeDto.getColor());
        shoe.setNumber(shoeDto.getNumber());
        shoe.setQuantity(shoeDto.getQuantity());
        shoe.setPrice(shoeDto.getPrice());
        shoe.setImage(shoeDto.getImage());
        return shoe;
    }
}
