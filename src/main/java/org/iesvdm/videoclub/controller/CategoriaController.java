package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.dto.CategoriaDTO;
import org.iesvdm.videoclub.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = {"", "/"}, params = {"!buscar", "!ordenar", "!pagina", "!tamanio"})
    public List<Categoria> all() {
        log.info("Accediendo a todas las categorías sin parametros");

        return this.categoriaService.all().stream().map(
                categoria -> {
                    int numPelisAs = categoria.getPeliculas().size();
                    return new CategoriaDTO(categoria, numPelisAs);
                }
        ).collect(Collectors.toList());
    }

    // http://localhost:8080/categoria?buscar=campo&order=desc
    @GetMapping(value = {"", "/"}, params = {"!pagina", "!tamanio"})
    public List<Categoria> all(@RequestParam("buscar") Optional<String> buscarOptional,
                               @RequestParam("ordenar") Optional<String> ordenarOptional) {
        log.info("Accediendo a todas las categorías con filtros");
        return this.categoriaService.all(buscarOptional, ordenarOptional);
    }

    // http://localhost:8080/categoria?pagina=0&tamanio=2
    @GetMapping(value = {"", "/"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value = "pagina", defaultValue = "0") int pagina,
                                                   @RequestParam(value = "tamanio", defaultValue = "3") int tamanio) {
        log.info("Accediendo a categorías con paginación");
        Map<String, Object> responseAll = this.categoriaService.all(pagina, tamanio);
        return ResponseEntity.ok(responseAll);
    }

    @PostMapping({"", "/"})
    public Categoria newCategoria(@RequestBody Categoria categoria) {
        return this.categoriaService.save(categoria);
    }

    @GetMapping("/{id}")
    public Categoria one(@PathVariable("id") Long id) {
        return this.categoriaService.one(id);
    }

    @PutMapping("/{id}")
    public Categoria replaceCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        log.info("CATEGORIA 1" + categoria);
        return this.categoriaService.replace(id, categoria);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
    }
}
