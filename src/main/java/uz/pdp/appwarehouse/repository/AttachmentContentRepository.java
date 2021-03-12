package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appwarehouse.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
    @Query(value = "select * from attachment_content aco join attachment att on aco.attachment_id=att.id\n" +
            "where att.id=:attID",nativeQuery = true)
    Optional<AttachmentContent> findAttachmentContentByAttID(@Param("attID") Integer attID);

}
