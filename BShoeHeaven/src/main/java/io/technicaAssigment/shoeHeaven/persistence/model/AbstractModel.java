package io.technicaAssigment.shoeHeaven.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractModel implements Model{

    @GeneratedValue
    @Id
    private Integer id;

    @Setter
    @Getter
    @Version
    private Integer version;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId() {
        this.id = id;
    }

    public String toString(){
        return "Model{" +
                "id=" + id +
                "versions=" +
                "}";
    }
}
