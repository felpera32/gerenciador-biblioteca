package bibliotecaAPi.library.controller;


import bibliotecaAPi.library.model.BookItem;
import bibliotecaAPi.library.service.BookItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/livros/ui")
public class BookItemControllerUI {
    private final BookItemService service;

    public BookItemControllerUI(BookItemService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public String listBooks(Model model){
        List<BookItem> livros = service.listarLivros();
        model.addAttribute("livros", livros);
        return "listarLivros";
    }

    @GetMapping("/listar/{id}")
    public String livroInfos(@PathVariable Long id, Model model){
        BookItem livro = service.listarLivroPorId(id);
            if(livro!=null) {
                model.addAttribute("livro", livro);
                return "livroEspecificacoes";
            }
            else{
                model.addAttribute("mensagem", "Livro não encontrado");
                return "listarLivros";
            }
    }
    @GetMapping("/deletar/{id}")
    public String removerLivro(@PathVariable Long id){
        service.removerLivro(id);
        return "redirect:/livros/ui/listar";
    }

    @GetMapping("/adicionar")
    public String adicionarLivro(Model model){
        model.addAttribute("livros", new BookItem());
        return "adicionarLivro";
    }

    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute BookItem livros, RedirectAttributes redirectAttributes){
        service.adicionarLivro(livros);
        redirectAttributes.addFlashAttribute("mensagem", "O livro cadastrado com sucesso");
        return "redirect:/livros/ui/listar";
    }
}
