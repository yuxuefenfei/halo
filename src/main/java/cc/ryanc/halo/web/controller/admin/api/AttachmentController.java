package cc.ryanc.halo.web.controller.admin.api;

import cc.ryanc.halo.model.dto.AttachmentOutputDTO;
import cc.ryanc.halo.model.support.UploadResult;
import cc.ryanc.halo.service.AttachmentService;
import cc.ryanc.halo.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Attachment controller.
 *
 * @author johnniang
 * @date 3/21/19
 */
@RestController
@RequestMapping("/admin/api/attachments")
public class AttachmentController {

    private final AttachmentService attachmentService;

    private final FileService fileService;

    public AttachmentController(AttachmentService attachmentService,
                                FileService fileService) {
        this.attachmentService = attachmentService;
        this.fileService = fileService;
    }

    /**
     * List of attachment.
     *
     * @param pageable pageable
     * @return Page<AttachmentOutputDTO>
     */
    @GetMapping
    public Page<AttachmentOutputDTO> pageBy(@PageableDefault(sort = "updateTime", direction = DESC) Pageable pageable) {
        return attachmentService.pageDtosBy(pageable);
    }

    /**
     * Get attachment by id.
     *
     * @param id attachment id
     * @return AttachmentOutputDTO
     */
    @GetMapping("{id:\\d+}")
    @ApiOperation("Get attachment detail by id")
    public AttachmentOutputDTO getBy(@PathVariable("id") Integer id) {
        return new AttachmentOutputDTO().convertFrom(attachmentService.getById(id));
    }

    /**
     * Delete attachment by id
     *
     * @param id id
     */
    @DeleteMapping("{id:\\d+}")
    @ApiOperation("Delete attachment by id")
    public void deletePermanently(@PathVariable("id") Integer id) {
        attachmentService.removeById(id);
    }

    @PostMapping("upload")
    public UploadResult uploadAttachment(@RequestParam("file") MultipartFile file) {
        // TODO Just for test
        return fileService.uploadToLocal(file);
    }
}