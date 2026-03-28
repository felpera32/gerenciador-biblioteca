package bibliotecaAPi.library.controller;
import bibliotecaAPi.library.model.BookItem;
import bibliotecaAPi.library.service.BookItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/livros")
public class BookItemController {

    private BookItemService service;

    public BookItemController(BookItemService service) {
        this.service = service;
    }

    @GetMapping("/msg")
    public String msg(){
        return "Teste de rota";
    }


    @GetMapping("/listar")
    public List<BookItem> listBooks(){
        return service.listarLivros();
    }

    @GetMapping("/listar/{id}")
    public BookItem livro(@PathVariable Long id){
        return service.listarLivroPorId(id);
    }


    @PostMapping("/criar")
    public BookItem adicionarLivro(@RequestBody BookItem bookItem){
        return service.adicionarLivro(bookItem);
    }

    @DeleteMapping("/remover/{id}")
    public void removerLivro(@PathVariable Long id){
        service.removerLivro(id);
    }
}
