/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */

package org.duracloud.mill.manifest.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.duracloud.mill.auditor.AuditLogItem;
import org.duracloud.mill.db.model.BitIntegrityReport;
import org.duracloud.mill.db.model.JpaAuditLogItem;
import org.duracloud.mill.db.repo.JpaAuditLogItemRepo;
import org.duracloud.mill.db.repo.JpaBitIntegrityReportRepo;
import org.duracloud.mill.test.jpa.JpaIntegrationTestBase;
import org.junit.Test;

/**
 * This test class runs tests against a live embedded mysql database.
 *
 * @author Daniel Bernstein
 * Date: August 23, 2017
 */
public class ModelIntegrationTest extends JpaIntegrationTestBase {

    private String account = "account";

    private String storeId = "store-id";

    private String spaceId = "space-id";

    private String contentId = "content-id";

    @Test
    public void testAddBitReport() throws Exception {
        long oneSecondAgo = System.currentTimeMillis() - 1000;
        Date timestamp = new Date(oneSecondAgo);
        BitIntegrityReport report = new BitIntegrityReport();

        report.setAccount(account);
        report.setCompletionDate(timestamp);
        report.setModified(timestamp);
        report.setReportContentId(contentId);
        report.setReportSpaceId(spaceId);
        report.setSpaceId(spaceId);
        report.setStoreId(storeId);

        report = getBitReportRepo().saveAndFlush(report);
        assertEquals(account, report.getAccount());
        assertEquals(storeId, report.getStoreId());
        assertEquals(spaceId, report.getSpaceId());
        assertEquals(spaceId, report.getReportSpaceId());
        assertEquals(contentId, report.getReportContentId());
        assertEquals(timestamp, report.getCompletionDate());
        assertNotNull(report.getId());

        assertEquals(timestamp, report.getModified());
    }

    @Test
    public void testUTF8Reads() throws Exception {

        final JpaAuditLogItem item = new JpaAuditLogItem();
        final String happyNewYear = "新年快乐blankHÃ©lÃ¨nÃ¥JÃ¶r.txt";
        item.setAccount(account);
        item.setAction("action");
        item.setContentMd5("md5");
        item.setContentId(happyNewYear);
        item.setSpaceId(spaceId);
        item.setStoreId(storeId);
        item.setContentSize("0");
        item.setContentProperties("0");
        item.setMimetype("mime");
        item.setSourceContentId(happyNewYear);
        item.setSourceSpaceId("sourcespace");
        item.setTimestamp(System.currentTimeMillis());
        item.setUsername("username");
        item.setSpaceAcls("acls");
        item.setUniqueKey("unique");
        item.setVersion(1);
        item.setWritten(false);
        item.setModified(new Date());

        final JpaAuditLogItemRepo repo = getAuditRepo();
        repo.saveAndFlush(item);
        final AuditLogItem savedItem = repo.findAll().get(0);

        assertNotNull(savedItem);
        assertEquals("UTF8 content id should be match.", happyNewYear, savedItem.getContentId());
        assertEquals("UTF8 source content id should be match.", happyNewYear, savedItem.getSourceContentId());

    }

    private JpaBitIntegrityReportRepo getBitReportRepo() {
        return context.getBean(JpaBitIntegrityReportRepo.class);
    }

    private JpaAuditLogItemRepo getAuditRepo() {
        return context.getBean(JpaAuditLogItemRepo.class);
    }

}
