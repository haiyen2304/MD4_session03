package com.ra.session03_dm4.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class HandleNameExist implements ConstraintValidator<NameExist,String> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<?> entity;
    private String field;

    @Override
    public void initialize(NameExist constraintAnnotation) {
        this.entity = constraintAnnotation.entityClass();
        this.field = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Long count;
        try (Session session = sessionFactory.openSession()) {

            count = session.createQuery("select count(u) from "+entity.getSimpleName()+" u where u."+field+" = :_check",Long.class)
                    .setParameter("_check",s)
                    .getSingleResult();
            return !(count > 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
