package com.backend.itcuniv.domain.feed.repository;

import com.backend.itcuniv.domain.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
