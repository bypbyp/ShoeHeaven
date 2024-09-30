package io.technicaAssigment.shoeHeaven.command.converter;

import io.technicaAssigment.shoeHeaven.command.ShoeDto;
import io.technicaAssigment.shoeHeaven.persistence.model.Shoe;
import org.springframework.stereotype.Component;

@Component
public class ShoeToShoeDto extends AbstractConverter<Shoe, ShoeDto> {

    @Override
    public ShoeDto convert(Shoe shoe) {

        ShoeDto shoeDto = new ShoeDto();

        shoeDto.setBrand(shoe.getBrand());
        shoeDto.setName(shoeDto.getName());
        shoeDto.setColor(shoe.getColor());
        shoeDto.setNumber(shoe.getNumber());
        shoeDto.setQuantity(shoe.getQuantity());
        shoeDto.setPrice(shoe.getPrice());
        shoeDto.setImage(shoe.getImage());

        return shoeDto;
    }
}
