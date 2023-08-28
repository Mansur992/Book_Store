package com.bookStore.service;

import com.bookStore.entity.MyBookList;
import com.bookStore.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookService {
    @Autowired
    private MyBookRepository myBookRepository;
    public void saveMyBooks(MyBookList bookList){
        myBookRepository.save(bookList);
    }

    public List<MyBookList> getAllMyBooks(){
        return myBookRepository.findAll();
    }

    public void deleteById(int id){
        myBookRepository.deleteById(id);
    }

}
