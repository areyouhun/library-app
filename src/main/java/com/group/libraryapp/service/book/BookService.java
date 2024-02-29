package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.book.checkout.CheckoutHistory;
import com.group.libraryapp.domain.book.checkout.CheckoutHistoryRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.CheckoutRequest;
import com.group.libraryapp.dto.book.request.ReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CheckoutHistoryRepository checkoutHistoryRepository;
    private final UserRepository userRepository;

    public BookService(
            BookRepository bookRepository,
            CheckoutHistoryRepository checkoutHistoryRepository,
            UserRepository userRepository
    ) {
        this.bookRepository = bookRepository;
        this.checkoutHistoryRepository = checkoutHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void checkoutBook(CheckoutRequest request) {
        Book book = bookRepository.findByName(request.getBookName())
                                .orElseThrow(IllegalArgumentException::new);

        if (checkoutHistoryRepository.existsByBookNameAndIsReturned(book.getName(), false)) {
            throw new IllegalArgumentException("대출 중인 책입니다");
        }

        User user = userRepository.findByName(request.getUserName())
                                .orElseThrow(IllegalArgumentException::new);

        user.checkoutBook(book.getName());
    }

    @Transactional
    public void returnBook(ReturnRequest request) {
        User user = userRepository.findByName(request.getUserName())
                                .orElseThrow(IllegalArgumentException::new);

        user.returnBook(request.getBookName());
    }
}
