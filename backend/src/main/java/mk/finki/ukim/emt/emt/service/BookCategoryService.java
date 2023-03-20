package mk.finki.ukim.emt.emt.service;

import mk.finki.ukim.emt.emt.model.enumerations.BookCategory;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookCategoryService {

    List<String> findAll();
}
