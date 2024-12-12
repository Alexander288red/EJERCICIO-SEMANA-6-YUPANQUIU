/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.EJERCICIO.SEMANA6.service;

import com.example.EJERCICIO.SEMANA6.model.Producto;
import com.example.EJERCICIO.SEMANA6.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // Guardar un producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // Buscar producto por ID
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);  // Cambia si deseas lanzar una excepción
    }

    // Eliminar un producto
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }
}
