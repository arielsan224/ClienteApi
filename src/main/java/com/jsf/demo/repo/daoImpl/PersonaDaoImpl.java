package com.jsf.demo.repo.daoImpl;

import com.jsf.demo.modelo.Persona;
import com.jsf.demo.repo.dao.PersonaDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonaDaoImpl implements PersonaDao {
    @Override
    public List<Persona> obtenerTodo() {
        return null;
    }
}
