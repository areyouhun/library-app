package com.group.libraryapp.domain.book.checkout;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutHistoryRepository extends JpaRepository<CheckoutHistory, Long> {

    boolean existsByBookNameAndIsReturned(String bookName, boolean isReturn);

    Optional<CheckoutHistory> findByUserIdAndBookName(Long userId, String bookName);
}
