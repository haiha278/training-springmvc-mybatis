package com.example.training.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper<D,E> {
    @Autowired
    private ModelMapper modelMapper;

    public D mapToDTO(E entity, Class<D> dtoClass){
        return modelMapper.map(entity, dtoClass);
    }

    public E mapToEntity(D dtoClass, Class<E> entity){
        return modelMapper.map(dtoClass, entity);
    }
}
