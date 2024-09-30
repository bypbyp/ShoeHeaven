package io.technicaAssigment.shoeHeaven.controller.rest;

import io.technicaAssigment.shoeHeaven.command.ShoeDto;
import io.technicaAssigment.shoeHeaven.command.converter.ShoeDtoToShoe;
import io.technicaAssigment.shoeHeaven.command.converter.ShoeToShoeDto;
import io.technicaAssigment.shoeHeaven.exceptions.NoMoreShoeException;
import io.technicaAssigment.shoeHeaven.exceptions.ShoeNotFoundException;
import io.technicaAssigment.shoeHeaven.persistence.model.Shoe;
import io.technicaAssigment.shoeHeaven.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class RestShoeController {

    private ShoeService shoeService;
    private ShoeToShoeDto shoeToShoeDto;
    private ShoeDtoToShoe shoeDtoToShoe;

    @Autowired
    public void setShoeService(ShoeService shoeService){
        this.shoeService = shoeService;
    }

    @Autowired
    public void setShoeToShoeDto(ShoeToShoeDto shoeToShoeDto){
        this.shoeToShoeDto = shoeToShoeDto;
    }

    @Autowired
    public void setShoeDtoToShoe(ShoeDtoToShoe shoeDtoToShoe) {
        this.shoeDtoToShoe = shoeDtoToShoe;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<ShoeDto>> listShoe(){

        List<ShoeDto> shoeDtoList = shoeService.List().stream()
                .map(shoe -> shoeToShoeDto.convert(shoe))
                .collect(Collectors.toList());
        return new ResponseEntity<>(shoeDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<ShoeDto> showShoe(@PathVariable Integer id){

        Shoe shoe = shoeService.get(id);

        if (shoe == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(shoeToShoeDto.convert(shoe), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<?> addShoe(@Valid @RequestBody ShoeDto shoeDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder){

        if(bindingResult.hasErrors() || shoeDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

        Shoe savedShoe = shoeService.save(shoeDtoToShoe.convert(shoeDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/customer" + savedShoe.getId()).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<ShoeDto> editShoe(@Valid @RequestBody ShoeDto shoeDto, BindingResult bindingResult, @PathVariable Integer id){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(shoeDto.getId() != null && !shoeDto.getId().equals(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(shoeService.get(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        shoeDto.setId(id);

        shoeService.save(shoeDtoToShoe.convert(shoeDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<ShoeDto>deleteShoe(@PathVariable Integer id){

        try{

            shoeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (NoMoreShoeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (ShoeNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
