package com.jrp.pma.repository;

import com.jrp.pma.entities.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount,Long> {
}
