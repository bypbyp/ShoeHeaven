package io.technicaAssigment.shoeHeaven.persistence.dao.jpa;

import io.technicaAssigment.shoeHeaven.persistence.dao.ShoeDao;
import io.technicaAssigment.shoeHeaven.persistence.model.Shoe;

public class JpaShoeDao extends GenericJpaDao<Shoe> implements ShoeDao {
    public JpaShoeDao() {
        super(Shoe.class);
    }
}
