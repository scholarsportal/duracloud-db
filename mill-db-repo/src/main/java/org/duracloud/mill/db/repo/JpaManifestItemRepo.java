/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.mill.db.repo;

import java.util.Date;

import org.duracloud.mill.db.model.ManifestItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Daniel Bernstein
 * 
 */
@Repository(value = "manifestItemRepo")
public interface JpaManifestItemRepo extends
                                    JpaRepository<ManifestItem, Long> {

    /**
     * @param account
     * @param storeId
     * @param spaceId
     * @param pageable
     * @return
     */
    Page<ManifestItem>
    findByAccountAndStoreIdAndSpaceIdAndDeletedFalseOrderByContentIdAsc(String account,
                                                                      String storeId,
                                                                      String spaceId,
                                                                      Pageable pageable);

    /**
     * Same as above produces an unordered list
     * @param account
     * @param storeId
     * @param spaceId
     * @param pageable
     * @return
     */
    Page<ManifestItem>
    findByAccountAndStoreIdAndSpaceIdAndDeletedFalse(String account,
                                                     String storeId,
                                                     String spaceId,
                                                     Pageable pageable);

    /**
     * @param account
     * @param storeId
     * @param spaceId
     * @param contentId
     * @return
     */
    ManifestItem
            findByAccountAndStoreIdAndSpaceIdAndContentId(String account,
                                                          String storeId,
                                                          String spaceId,
                                                          String contentId);

    /**
     * @param expiration
     */
    @Modifying
    @Query(nativeQuery=true,value="delete from manifest_item where deleted = true and modified < ?1 limit 50000")
    int deleteFirst50000ByDeletedTrueAndModifiedBefore(Date expiration);

    void deleteByAccountAndStoreIdAndSpaceId(String account,
                                             String storeId,
                                             String spaceId);

}
