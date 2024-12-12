/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.EJERCICIO.SEMANA6.controller;

import com.example.EJERCICIO.SEMANA6.model.Producto;
import com.example.EJERCICIO.SEMANA6.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Método para listar los productos
    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos";  // Debe retornar la vista de productos.html
    }

    // Método para mostrar el formulario de nuevo producto
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "formularioProducto";  // Vista para el formulario de producto
    }

    @PostMapping("/nuevo")
    public String guardarNuevoProducto(@ModelAttribute Producto producto, Model model) {
        try {
            System.out.println("Producto recibido: " + producto.getNombre());
            productoService.save(producto);
            return "redirect:/productos";  // Redirige a la lista de productos
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el producto: " + e.getMessage());
            return "formularioProducto"; // Redirige al formulario en caso de error
        }
    }

    // Método para mostrar el formulario de edición de producto
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarProducto(@PathVariable("id") Long id, Model model) {
        Producto producto = productoService.findById(id);
        model.addAttribute("producto", producto);
        return "formularioProducto";  // Formulario de editar producto
    }

    // Método para guardar cambios en un producto
    @PostMapping("/editar/{id}")
    public String guardarEdicionProducto(@PathVariable("id") Long id, @ModelAttribute Producto producto) {
        producto.setId(id);
        productoService.save(producto);
        return "redirect:/productos";  // Redirige a la lista de productos
    }

    // Método para eliminar un producto
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoService.delete(id);
        return "redirect:/productos";  // Redirige a la lista de productos
    }
}
