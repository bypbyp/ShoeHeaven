package io.technicaAssigment.shoeHeaven.service;

import io.technicaAssigment.shoeHeaven.exceptions.*;
import io.technicaAssigment.shoeHeaven.persistence.dao.ShoeDao;
import io.technicaAssigment.shoeHeaven.persistence.model.Shoe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ShoeServiceImp implements ShoeService {

    private ShoeDao shoeDao;

    @Autowired
    public void setShoeDao(ShoeDao ShoeDao){
        this.shoeDao = shoeDao;
    }

    @Override
    public Shoe get(Integer id) {
        Shoe shoe = shoeDao.findById(id);
        System.out.println("shoe:::" + shoe);

        return shoe;
    }

    @Override
    public List<Shoe> List() {
        return shoeDao.findAll();
    }

    @Transactional
    @Override
    public Shoe save(Shoe shoe) {
        return shoeDao.saveOrUpdate(shoe);
    }

    @Transactional
    @Override
    public void delete(Integer id) throws ShoeNotFoundException, NoMoreShoeException {
        Shoe shoe = Optional.ofNullable(shoeDao.findById(id))
                .orElseThrow(ShoeNotFoundException::new);

        if(shoe.getQuantity() != 0){
            throw new NoMoreShoeException();
        }

        shoeDao.delete(id);
    }
}
