package com.bookStore.controller;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookControler {
    @Autowired
    private BookService service;

    @Autowired
    private MyBookService myBookListService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister(){
        return "bookRegister";
    }

    @GetMapping("avialable_books")
    public ModelAndView getAllBook(){
        List<Book> list = service.getAllBook();
//        ModelAndView modelAndView = new  ModelAndView();
//        modelAndView.setViewName("bookList");
//        modelAndView.addObject("book",list);
        return new ModelAndView("booklist","book",list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b){
        service.save(b);
        return "redirect:/avialable_books";
    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model){
        List<MyBookList> list  = myBookListService.getAllMyBooks();
        model.addAttribute("book", list);
        return "myBooks";
    }

    @RequestMapping("mylist/{id}")
    public String getMyList(@PathVariable("id") int id){
        Book b =service.getBookById(id);
        MyBookList mb = new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookListService.saveMyBooks(mb);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Book b = service.getBookById(id);
        model.addAttribute("book",b);
        return "bookEdit";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:/avialable_books";
    }
}
