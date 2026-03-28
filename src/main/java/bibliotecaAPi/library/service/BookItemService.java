package bibliotecaAPi.library.service;

import bibliotecaAPi.library.model.BookItem;
import bibliotecaAPi.library.repository.BookItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookItemService {
    private final BookItemRepository bookItemRepository;

    public BookItemService(BookItemRepository bookItemRepository) {
        this.bookItemRepository = bookItemRepository;
    }

    public List<BookItem> listarLivros(){
        return bookItemRepository.findAll();
    }

    public BookItem listarLivroPorId(Long id){
        Optional<BookItem> book = bookItemRepository.findById(id);
        return book.orElse(null);
    }

    public BookItem adicionarLivro(BookItem book){
        return bookItemRepository.save(book);
    }

    public void removerLivro(Long id){
        bookItemRepository.deleteById(id);
    }
}
