package org.url.shorten.messageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.url.shorten.messageservice.model.Link;

import javax.transaction.Transactional;

public interface LinkRepository extends JpaRepository<Link,String> {
    @Modifying
    @Query(value = "UPDATE link l set l.click_count = l.click_count + 1 WHERE l.short_url = :shortUrl ",nativeQuery = true)
    @Transactional
    void incrementClickCountByOne(String message);
}
