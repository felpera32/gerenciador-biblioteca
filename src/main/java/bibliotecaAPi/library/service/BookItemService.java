package bibliotecaAPi.library.service;

import bibliotecaAPi.library.model.BookItem;
import bibliotecaAPi.library.repository.BookItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class BookItemService {
    private final BookItemRepository service;

    public BookItemService(BookItemRepository service) {
        this.service = service;
    }

    public List<BookItem> listarLivros(){
        return service.findAll();
    }

    public BookItem listarLivroPorId(Long id){
        Optional<BookItem> book = service.findById(id);
        return book.orElse(null);
    }

    public BookItem adicionarLivro(BookItem book){
        return service.save(book);
    }

    public void removerLivro(Long id){
        service.deleteById(id);
    }


    public BookItem Atualizar(@PathVariable Long id, @RequestBody BookItem bookItem){
        BookItem book = service.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));


        book.setNome(bookItem.getNome());
        book.setDescricao(bookItem.getDescricao());
        book.setAutor(bookItem.getAutor());
        book.setEditora(bookItem.getEditora());
        book.setLink(bookItem.getLink());
        book.setEditora(bookItem.getEditora());

        return service.save(book);
    }

}
