package com.rest.jwt_auth.service.impl;

import static com.rest.jwt_auth.utils.ObjectUtils.getIgnoredProperties;

import com.rest.jwt_auth.domain.Book;
import com.rest.jwt_auth.dto.BookDto;
import com.rest.jwt_auth.exception.BookNotFoundException;
import com.rest.jwt_auth.mapper.BookMapper;
import com.rest.jwt_auth.repository.BookRepository;
import com.rest.jwt_auth.service.BookService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::convertToDto)
                .toList();
    }

    @Override
    public BookDto getBook(Long id) {
        return bookMapper.convertToDto(bookRepository.getReferenceById(id));
    }

    @Override
    public void save(BookDto bookDto) {
        bookRepository.save(bookMapper.convertToDomain(bookDto));
    }

    @Override
    public void update(Long id, BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            Book updatedBook = bookMapper.convertToDomain(bookDto);
            BeanUtils.copyProperties(updatedBook, book, getIgnoredProperties(updatedBook, "id"));
        } else {
            throw new BookNotFoundException();
        }

    }

    @Override
    public void delete(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException();
        }
    }
}
