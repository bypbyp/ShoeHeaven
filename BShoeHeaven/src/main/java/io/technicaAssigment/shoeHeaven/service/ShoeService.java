package io.technicaAssigment.shoeHeaven.service;

import io.technicaAssigment.shoeHeaven.exceptions.NoMoreShoeException;
import io.technicaAssigment.shoeHeaven.exceptions.ShoeNotFoundException;
import io.technicaAssigment.shoeHeaven.persistence.model.Shoe;

import java.util.List;

public interface ShoeService {

    Shoe get(Integer id);

    List<Shoe> List();

    Shoe save(Shoe shoe);

    void delete(Integer id) throws ShoeNotFoundException, NoMoreShoeException;
}
