package com.example.tienda.config; // Ajusta el paquete si es necesario

import com.example.tienda.modelo.Producto; // Asegúrate de que esta es la ruta correcta
import com.example.tienda.repositorio.ProductoRepository; // Asegúrate de que esta es la ruta correcta

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class ProductoDataInitializer implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    // Inyecta SÓLO lo que necesitas para los productos (ProductoRepository)
    // Si quieres combinarlo con tu DataInitializer principal, solo añade ProductoRepository a la lista.
    public ProductoDataInitializer(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional // Es buena práctica usar Transactional si haces operaciones de guardado
    public void run(String... args) throws Exception {
        // Inicialización de Productos
        if (productoRepository.count() == 0) {
            System.out.println(">>> Inicializando datos de la tabla 'productos'...");

            // 1. SMARTPHONE
            Producto p1 = createProducto(
                "Smartphone Flagship X", // nombre
                "Smartphones",         // categoria
                "Nuevo",               // condicion
                "El último modelo con tecnología 5G, cámara de 200MP y pantalla Dynamic AMOLED. Batería de 5000mAh.", // descripcion
                true,                  // envio_gratis
                "/img/IdeaPadSlim5i.png",      // imagen
                new BigDecimal("999.99"), // precio
                50                     // stock
            );
            productoRepository.save(p1);

            // 2. LAPTOP
            Producto p2 = createProducto(
                "Laptop Ultrabook Pro", 
                "Computadoras",
                "Nuevo",
                "Portátil profesional ultraligera con Intel Core i9, 32GB RAM y 1TB SSD. Ideal para desarrolladores y diseñadores.",
                true,
                "/img/galaxytab.png",
                new BigDecimal("1850.00"),
                15
            );
            productoRepository.save(p2);

            // 3. AUDÍFONOS
            Producto p3 = createProducto(
                "Audífonos Inalámbricos ANC",
                "Audio",
                "Nuevo",
                "Auriculares over-ear con cancelación de ruido activa (ANC) de última generación y 40 horas de batería.",
                false,
                "/img/airpodspro.png",
                new BigDecimal("149.95"),
                120
            );
            productoRepository.save(p3);
            
            // 4. SMARTWATCH
            Producto p4 = createProducto(
                "Smartwatch Deportivo",
                "Wearables",
                "Usado",
                "Reloj inteligente con GPS, monitoreo de ritmo cardíaco y más de 30 modos deportivos. Incluye correa extra.",
                false,
                "/img/galaxys24.png",
                new BigDecimal("79.99"),
                30
            );
            productoRepository.save(p4);

            Producto p5 = createProducto(
                "Smartwatch Deportivo",
                "Wearables",
                "Usado",
                "Reloj inteligente con GPS, monitoreo de ritmo cardíaco y más de 30 modos deportivos. Incluye correa extra.",
                false,
                "/img/RogPhone8.png",
                new BigDecimal("79.99"),
                30
            );
            productoRepository.save(p5);
            System.out.println(">>> Productos iniciales cargados: " + productoRepository.count());

        } else {
            System.out.println(">>> La tabla 'productos' ya contiene datos. Inicialización omitida.");
        }
    }

    // Método auxiliar para crear una instancia de Producto de forma limpia
    private Producto createProducto(
            String nombre, 
            String categoria, 
            String condicion, 
            String descripcion, 
            boolean envioGratis,
            String imagen,
            BigDecimal precio, 
            int stock) {
        
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setCategoria(categoria);
        producto.setCondicion(condicion);
        producto.setDescripcion(descripcion);
        producto.setEnvioGratis(envioGratis);
        
        producto.setImagen(imagen);
        producto.setPrecio(precio);
        producto.setStock(stock);
        
        return producto;
    }
}