package com.jason.jpa.idgeneratos;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class CustomRandomIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor si, Object obj) throws HibernateException {
        Random random = new Random();
        int id = 0;
        id = random.nextInt(1000000);
        return (long) id;
    }

}
