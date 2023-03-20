package mk.finki.ukim.emt.emt.service.impl;

import mk.finki.ukim.emt.emt.model.enumerations.BookCategory;
import mk.finki.ukim.emt.emt.service.BookCategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookCategoryControllerImpl implements BookCategoryService {

    @Override
    public List<String> findAll() {
        return Arrays.stream(BookCategory.values()).map(BookCategory::name).toList();
    }
}
